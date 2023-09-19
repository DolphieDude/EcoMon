package ua.dolphiedude.ecomon;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.ThemableLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

@Route("")
public class View extends VerticalLayout {
    private FacilityRepository facilityRepository;
    private TextField facilityName = new TextField("Name");
    private TextField activity = new TextField("Activity");
    private TextField ownership = new TextField("Ownership");
    private TextField ecologicalDescription = new TextField("Ecological Description");
    private Binder facilityBinder = new Binder<>(Facility.class);

    private SubstanceRepository substanceRepository;
    private TextField substanceName = new TextField("Substance Name");
    private TextField gdk = new TextField("GDK");
    private TextField units = new TextField("Units");
    private Binder substanceBinder = new Binder<>(Substance.class);


    public View(FacilityRepository repository, SubstanceRepository substanceRepository) {
        facilityBinder.bind(facilityName, "name");
        this.facilityRepository = repository;
        var facilityLayout = new HorizontalLayout();
        facilityLayout.add(facilityName, activity, ownership, ecologicalDescription);
        add(getForm(facilityLayout, facilityBinder, facilityRepository, Facility.class));

        substanceBinder.bind(substanceName, "name");
        this.substanceRepository = substanceRepository;
        var substanceLayout = new HorizontalLayout();
        substanceLayout.add(substanceName, gdk, units);
        add(getForm(substanceLayout, substanceBinder, substanceRepository, Substance.class));


    }

//    private Component getFacilityForm() {
//        var layout = new HorizontalLayout();
//        layout.setAlignItems(Alignment.BASELINE);
//        Button addButton = new Button("Add");
//        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        layout.add(facilityName, activity, ownership, ecologicalDescription, addButton);
//        facilityBinder.bindInstanceFields(this);
//
//        addButton.addClickListener(add -> {
//            try {
//                Facility facility = new Facility();
//                facilityBinder.writeBean(facility);
//                facilityRepository.save(facility);
//                facilityBinder.readBean(new Facility());
//            } catch (ValidationException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        return layout;
//    }
//
//    private Component getSubstanceForm() {
//        var layout = new HorizontalLayout();
//        layout.setAlignItems(Alignment.BASELINE);
//        Button addButton = new Button("Add");
//        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        layout.add(substanceName, gdk, units, addButton);
//        substanceBinder.bindInstanceFields(this);
//
//        addButton.addClickListener(add -> {
//            try {
//                Substance substance = new Substance();
//                substanceBinder.writeBean(substance);
//                substanceRepository.save(substance);
//                substanceBinder.readBean(new Substance());
//            } catch (ValidationException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        return layout;
//    }

    private HorizontalLayout getForm(HorizontalLayout layout, Binder binder, JpaRepository repository, Class beanType) {
        binder.bindInstanceFields(this);

        layout.setAlignItems(Alignment.BASELINE);
        Button addButton = new Button("Add");
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layout.add(addButton);
        addButton.addClickListener(add  -> {
            try {
                Object bean = beanType.getDeclaredConstructor().newInstance();
                binder.writeBean(bean);
                repository.save(bean);
                binder.readBean(beanType.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return layout;
    }
}
