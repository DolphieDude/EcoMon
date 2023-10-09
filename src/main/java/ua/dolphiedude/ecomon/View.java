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
import ua.dolphiedude.ecomon.tax.Tax;
import ua.dolphiedude.ecomon.tax.TaxRepository;


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

    private final ComboBox<Facility> emissionFacility = new ComboBox<>("ID of facility");
    private final ComboBox<Substance> emissionSubstance = new ComboBox<>("ID of substance");
    private final TextField year = new TextField("Year");
    private final TextField amount = new TextField("Amount");
    private final Binder<Emission> emissionBinder = new Binder<>(Emission.class);
    private final Grid<Emission> emissionGrid = new Grid<>(Emission.class);

    private final ComboBox<Substance> taxSubstance = new ComboBox<>("ID of substance");
    private final TextField rate = new TextField("Tax rate");
    private final Binder<Tax> taxBinder = new Binder<>(Tax.class);
    private final Grid<Tax> taxGrid = new Grid<>(Tax.class);


    public View(FacilityRepository facilityRepository, SubstanceRepository substanceRepository,
                EmissionRepository emissionRepository, TaxRepository taxRepository) {

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
        emissionFacility.setItems(facilityRepository.findAll());

//        Good decision for cases when need to show in combobox something different from actual items in it:
//
//        idFacility.setItemLabelGenerator(id -> {
//            List<Facility> list = facilityRepository.findAll();
//            for (Facility facility: list) {
//                if (facility.getId().equals(id)) return facility.getName();
//            }
//            return null;
//        });



        emissionSubstance.setItems(substanceRepository.findAll());

        var emissionLayout = new HorizontalLayout();
        emissionLayout.add(emissionFacility, emissionSubstance, year, amount);
        add(getForm(emissionLayout, emissionBinder, emissionRepository, Emission.class));

        emissionGrid.setColumns("id", "emissionFacility", "emissionSubstance", "year", "amount");
        emissionGrid.setItems(emissionRepository.findAll());
        add(emissionGrid);
        add(new H3("\n"));

        add(new H3("Tax"));
        taxSubstance.setItems(substanceRepository.findAll());

        var taxLayout = new HorizontalLayout();
        taxLayout.add(taxSubstance, rate);
        add(getForm(taxLayout, taxBinder, taxRepository, Tax.class));

        taxGrid.setColumns("id", "taxSubstance", "rate");
        taxGrid.setItems(taxRepository.findAll());
        add(taxGrid);
    }

    private <ENTITY, T extends JpaRepository<ENTITY, Long>> HorizontalLayout getForm(HorizontalLayout layout, Binder<ENTITY> binder, T repository, Class<ENTITY> beanType) {
        binder.bindInstanceFields(this);

        layout.setAlignItems(Alignment.BASELINE);
        Button addButton = new Button("Add");
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layout.add(addButton);
        addButton.addClickListener(add  -> {
            try {
                ENTITY bean = beanType.getDeclaredConstructor().newInstance();
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
