package ua.dolphiedude.ecomon;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
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

import java.util.List;


@Route("")
public class View extends VerticalLayout {

    private final TextField facilityName = new TextField("Facility Name");
    private final TextField activity = new TextField("Activity");
    private final TextField ownership = new TextField("Ownership");
    private final TextField ecologicalDescription = new TextField("Ecological Description");
    private final Binder<Facility> facilityBinder = new Binder<>(Facility.class);
    private final Grid<Facility> facilityGrid = new Grid<>(Facility.class);


    private final TextField substanceName = new TextField("Substance Name");
    private final TextField gdk = new TextField("GDK");
    private final TextField units = new TextField("Units");
    private final Binder<Substance> substanceBinder = new Binder<>(Substance.class);
    private final Grid<Substance> substanceGrid = new Grid<>(Substance.class);

    private final ComboBox<Long> idFacility = new ComboBox<>("ID of facility");
    private final ComboBox<Long> idSubstance = new ComboBox<>("ID of substance");
    private final TextField year = new TextField("Year");
    private final TextField amount = new TextField("Amount");
    private final Binder<Emission> emissionBinder = new Binder<>(Emission.class);
    private final Grid<Emission> emissionGrid = new Grid<>(Emission.class);


    public View(FacilityRepository facilityRepository, SubstanceRepository substanceRepository,
                EmissionRepository emissionRepository) {

        add(new H3("Facility"));
        facilityBinder.bind(facilityName, "name");
        var facilityLayout = new HorizontalLayout();
        facilityLayout.add(facilityName, activity, ownership, ecologicalDescription);
        add(getForm(facilityLayout, facilityBinder, facilityRepository, Facility.class));

        facilityGrid.setColumns("id", "name", "activity", "ownership", "ecologicalDescription");
        facilityGrid.setItems(facilityRepository.findAll());
        add(facilityGrid);
        add(new H3("\n"));

        add(new H3("Substance"));
        substanceBinder.bind(substanceName, "name");
        var substanceLayout = new HorizontalLayout();
        substanceLayout.add(substanceName, gdk, units);
        add(getForm(substanceLayout, substanceBinder, substanceRepository, Substance.class));

        substanceGrid.setColumns("id", "name", "gdk", "units");
        substanceGrid.setItems(substanceRepository.findAll());
        add(substanceGrid);
        add(new H3("\n"));


        add(new H3("Emission"));
        idFacility.setAllowCustomValue(true);
        idFacility.setItems(facilityRepository.getIds());
        idFacility.setItemLabelGenerator(id -> {
            List<Facility> list = facilityRepository.findAll();
            for (Facility facility: list) {
                if (facility.getId().equals(id)) return facility.getName();
            }
            return null;
        });




        idSubstance.setAllowCustomValue(true);
        idSubstance.setItems(substanceRepository.getIds());
        idSubstance.setItemLabelGenerator(id -> {
            List<Substance> list = substanceRepository.findAll();
            for (Substance substance: list) {
                if (substance.getId().equals(id)) return substance.getName();
            }
            return null;
        });

        var emissionLayout = new HorizontalLayout();
        emissionLayout.add(idFacility, idSubstance, year, amount);
        add(getForm(emissionLayout, emissionBinder, emissionRepository, Emission.class));

        emissionGrid.setColumns("id", "facility", "substance", "year", "amount");
        emissionGrid.setItems(emissionRepository.findAll());
        add(emissionGrid);
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
