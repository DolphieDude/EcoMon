package ua.dolphiedude.ecomon;

import com.vaadin.flow.component.UI;
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
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.dolphiedude.ecomon.entity.*;
import ua.dolphiedude.ecomon.repository.*;
import ua.dolphiedude.ecomon.service.ResultService;
import ua.dolphiedude.ecomon.service.RiskService;

import java.util.List;


@Route("")
@Transactional
public class View extends VerticalLayout {
    private final ResultRepository resultRepository;

    private final ResultService resultService;

    private final RiskRepository riskRepository;


    private final ComboBox<Facility> emissionFacility = new ComboBox<>("Facility of emission");
    private final ComboBox<Substance> emissionSubstance = new ComboBox<>("Substance of emission");
    private final TextField year = new TextField("Year");
    private final TextField amount = new TextField("Amount");
    private final Binder<Emission> emissionBinder = new Binder<>(Emission.class);
    private final Grid<Emission> emissionGrid = new Grid<>(Emission.class);

    private final ComboBox<Substance> taxSubstance = new ComboBox<>("ID of substance");
    private final TextField rate = new TextField("Tax rate");
    private final Binder<Tax> taxBinder = new Binder<>(Tax.class);
    private final Grid<Tax> taxGrid = new Grid<>(Tax.class);

    private final ComboBox<Facility> filterForResultFacility = new ComboBox<>("Facility");
    private final TextField filterForResultYear = new TextField("Year");

    private List<Result> filteredResult;

    private final TextField sumField = new TextField("Total sum");

    private final Grid<Result> resultGrid = new Grid<>(Result.class);

    private final ComboBox<Facility> filterForRiskFacility = new ComboBox<>("Facility");
    private final ComboBox<Substance> filterForRiskSubstance = new ComboBox<>("Substance");
    private final TextField filterForRiskYear = new TextField("Year");
    private List<Risk> filteredRisk;

    private final Grid<Risk> riskGrid = new Grid<>(Risk.class);




    public View(FacilityRepository facilityRepository, SubstanceRepository substanceRepository,
                EmissionRepository emissionRepository, TaxRepository taxRepository,
                ResultRepository resultRepository, RiskRepository riskRepository,
                ResultService resultService, RiskService riskService) {
        this.resultRepository = resultRepository;
        this.resultService = resultService;

        this.riskRepository = riskRepository;

        var facilityLayout = new HorizontalLayout();
        Binder<Facility> facilityBinder = new Binder<>(Facility.class);
        Grid<Facility> facilityGrid = new Grid<>(Facility.class);
        add(new H3("Facility"));

        TextField facilityName = new TextField("Name");
        facilityBinder.bind(facilityName, "name");

        TextField activity = new TextField("Activity");
        facilityBinder.bind(activity, "activity");

        TextField ownership = new TextField("Ownership");
        facilityBinder.bind(ownership, "ownership");

        TextField ecologicalDescription = new TextField("Ecological Description");
        facilityBinder.bind(ecologicalDescription, "ecologicalDescription");

        facilityLayout.add(facilityName, activity, ownership, ecologicalDescription);
        add(getForm(facilityLayout, facilityBinder, facilityRepository, Facility.class));

        facilityGrid.setColumns("id", "name", "activity", "ownership", "ecologicalDescription");
        facilityGrid.setItems(facilityRepository.findAll());
        add(facilityGrid);
        add(new H3("\n"));

        add(new H3("Substance"));
        var substanceLayout = new HorizontalLayout();
        Binder<Substance> substanceBinder = new Binder<>(Substance.class);

        TextField substanceName = new TextField("Name");
        substanceBinder.bind(substanceName, "name");

        TextField massConsumption = new TextField("Mass Consumption");
        substanceBinder.bind(massConsumption, "massConsumption");

        TextField units = new TextField("Units");
        substanceBinder.bind(units, "units");

        substanceLayout.add(substanceName, massConsumption, units);
        add(getForm(substanceLayout, substanceBinder, substanceRepository, Substance.class));

        Grid<Substance> substanceGrid = new Grid<>(Substance.class);
        substanceGrid.setColumns("id", "name", "massConsumption", "units", "refConcentration",
                "slopeFactor", "tlv", "massFlowRate", "toxicityClass");
        substanceGrid.setItems(substanceRepository.findAll());
        add(substanceGrid);
        add(new H3("\n"));


        add(new H3("Emission"));
        emissionFacility.setItems(facilityRepository.findAll());
        emissionFacility.setClearButtonVisible(true);

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
        emissionSubstance.setClearButtonVisible(true);

        var emissionLayout = new HorizontalLayout();
        emissionLayout.add(emissionFacility, emissionSubstance, year, amount);
        add(getForm(emissionLayout, emissionBinder, emissionRepository, Emission.class));

        emissionGrid.setColumns("id", "facility", "substance", "year", "amount", "concentration");
        emissionGrid.setItems(emissionRepository.findAll());
        add(emissionGrid);
        add(new H3("\n"));

        add(new H3("Tax"));
        taxSubstance.setItems(substanceRepository.findAll());
        taxSubstance.setClearButtonVisible(true);

        var taxLayout = new HorizontalLayout();
        taxLayout.add(taxSubstance, rate);
        add(getForm(taxLayout, taxBinder, taxRepository, Tax.class));

        taxGrid.setColumns("id", "substance", "rate");
        taxGrid.setItems(taxRepository.findAll());
        add(taxGrid);
        add(new H3("\n"));

        add(new H3("Result"));
        var resultLayout = new HorizontalLayout();

        Button calculateResultsButton = new Button("Calculate Results");
        calculateResultsButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        calculateResultsButton.addClickListener(clicked -> resultService.calculateAndCreateResults());
        add(calculateResultsButton);


        filterForResultFacility.setItems(facilityRepository.findAll());
        filterForResultFacility.setClearButtonVisible(true);

        Button filterResultButton = new Button("Filter");
        filterResultButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        resultLayout.setAlignItems(Alignment.BASELINE);
        resultLayout.add(filterForResultFacility, filterForResultYear, filterResultButton);

        sumField.setReadOnly(true);
        sumField.getStyle().set("font-size", "24px");

        filteredResult = resultRepository.findAll();
        filterResultButton.addClickListener(clicked -> chooseFilter());

        resultGrid.setColumns("id", "emission.facility", "emission.substance",
                "emission.year", "taxesValue");
        resultGrid.setItems(filteredResult);
        sumField.setValue(resultService.getSumOfResult(filteredResult) + " ₴");

        add(resultLayout, sumField, resultGrid);
        add(new H3("\n"));

        add(new H3("Risk"));
        var riskLayout = new HorizontalLayout();

        Button calculateRisksButton = new Button("Calculate Risks");
        calculateRisksButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        calculateRisksButton.addClickListener(clicked -> riskService.calculateAndCreateRisks());
        add(calculateRisksButton);


        filterForRiskFacility.setItems(facilityRepository.findAll());
        filterForRiskFacility.setClearButtonVisible(true);

        filterForRiskSubstance.setItems(substanceRepository.findAll());
        filterForRiskSubstance.setClearButtonVisible(true);

        Button filterRiskButton = new Button("Filter");
        filterRiskButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        riskLayout.setAlignItems(Alignment.BASELINE);
        riskLayout.add(filterForRiskFacility, filterForRiskSubstance,
                filterForRiskYear, filterRiskButton);

        filteredRisk = riskRepository.findAll();
        filterRiskButton.addClickListener(clicked -> chooseFilterForRisk());

        riskGrid.setColumns("id", "emission.facility", "emission.substance",
                "emission.year", "nonCarcinogenRisk", "nonCarcinogenMessage",
                "carcinogenRisk", "carcinogenMessage");
        riskGrid.setItems(filteredRisk);

        add(riskLayout, riskGrid);
    }

    private <ENTITY, T extends JpaRepository<ENTITY, Long>> HorizontalLayout getForm(HorizontalLayout layout, Binder<ENTITY> binder, T repository, Class<ENTITY> beanType) {
        binder.bindInstanceFields(this);

        layout.setAlignItems(Alignment.BASELINE);
        Button addButton = new Button("Add");
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layout.add(addButton);
        addButton.addClickListener(add -> {
            try {
                ENTITY bean = beanType.getDeclaredConstructor().newInstance();
                binder.writeBean(bean);
                repository.save(bean);
                binder.readBean(beanType.getDeclaredConstructor().newInstance());
                UI.getCurrent().getPage().reload();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return layout;
    }

    private void chooseFilter() {
        Facility facilityValue = filterForResultFacility.getValue();
        Integer yearValue;
        try {
            yearValue = Integer.valueOf(filterForResultYear.getValue());
        } catch (NumberFormatException exception) {
            yearValue = null;
        }

        if (facilityValue == null) {
            filteredResult = resultRepository.findByYear(yearValue);
        } else if (yearValue == null) {
            filteredResult = resultRepository.findByFacility(facilityValue);
        } else {
            filteredResult = resultRepository.findByFacilityAndYear(facilityValue, yearValue);
        }
        resultGrid.setItems(filteredResult);
        resultGrid.getDataProvider().refreshAll();

        sumField.setValue(resultService.getSumOfResult(filteredResult) + " ₴");
    }

    private void chooseFilterForRisk() {
        Facility facilityValue = filterForRiskFacility.getValue();
        Substance substanceValue = filterForRiskSubstance.getValue();
        Integer yearValue;
        try {
            yearValue = Integer.valueOf(filterForRiskYear.getValue());
        } catch (NumberFormatException exception) {
            yearValue = null;
        }

        if (facilityValue == null && substanceValue == null) {
            filteredRisk = riskRepository.findByEmissionYear(yearValue);
        } else if (yearValue == null && substanceValue == null) {
            filteredRisk = riskRepository.findByEmissionFacility(facilityValue);
        } else if (facilityValue == null && yearValue == null) {
            filteredRisk = riskRepository.findByEmissionSubstance(substanceValue);
        } else {
            filteredRisk = riskRepository.findByEmissionFacilityAndEmissionSubstanceAndEmissionYear
                    (facilityValue, substanceValue, yearValue);
        }
        riskGrid.setItems(filteredRisk);
        riskGrid.getDataProvider().refreshAll();
    }
}
