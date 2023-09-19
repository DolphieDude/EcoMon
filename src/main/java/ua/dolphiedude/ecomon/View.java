package ua.dolphiedude.ecomon;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.dolphiedude.ecomon.emission.Emission;
import ua.dolphiedude.ecomon.emission.EmissionRepository;
import ua.dolphiedude.ecomon.facility.Facility;
import ua.dolphiedude.ecomon.facility.FacilityRepository;
import ua.dolphiedude.ecomon.substance.Substance;
import ua.dolphiedude.ecomon.substance.SubstanceRepository;

@Route("")
public class View extends VerticalLayout {
    private TextField facilityName = new TextField("Name");
    private TextField activity = new TextField("Activity");
    private TextField ownership = new TextField("Ownership");
    private TextField ecologicalDescription = new TextField("Ecological Description");
    private Binder facilityBinder = new Binder<>(Facility.class);

    private TextField substanceName = new TextField("Substance Name");
    private TextField gdk = new TextField("GDK");
    private TextField units = new TextField("Units");
    private Binder substanceBinder = new Binder<>(Substance.class);

    private TextField idFacility = new TextField("ID of facility");
    private TextField idSubstance = new TextField("ID of substance");
    private TextField year = new TextField("Year");
    private TextField amount = new TextField("Amount");
    private Binder emissionBinder = new Binder<>(Emission.class);


    public View(FacilityRepository facilityRepository, SubstanceRepository substanceRepository,
                EmissionRepository emissionRepository) {
        facilityBinder.bind(facilityName, "name");
        var facilityLayout = new HorizontalLayout();
        facilityLayout.add(facilityName, activity, ownership, ecologicalDescription);
        add(getForm(facilityLayout, facilityBinder, facilityRepository, Facility.class));

        substanceBinder.bind(substanceName, "name");
        var substanceLayout = new HorizontalLayout();
        substanceLayout.add(substanceName, gdk, units);
        add(getForm(substanceLayout, substanceBinder, substanceRepository, Substance.class));

        var emissionLayout = new HorizontalLayout();
        emissionLayout.add(idFacility, idSubstance, year, amount);
        add(getForm(emissionLayout, emissionBinder, emissionRepository, Emission.class));
    }

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
