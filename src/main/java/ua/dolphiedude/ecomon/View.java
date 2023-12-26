package ua.dolphiedude.ecomon;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.dolphiedude.ecomon.entity.*;
import ua.dolphiedude.ecomon.repository.*;
import ua.dolphiedude.ecomon.service.LossService;
import ua.dolphiedude.ecomon.service.ResultService;
import ua.dolphiedude.ecomon.service.RiskService;

import java.util.ArrayList;
import java.util.List;


@Route("")
public class View extends VerticalLayout {
    private final ResultRepository resultRepository;

    private final ResultService resultService;

    private final RiskRepository riskRepository;

    private final ComboBox<Facility> filterForResultFacility = new ComboBox<>("Facility");
    private final TextField filterForResultYear = new TextField("Year");

    private List<Result> filteredResult;

    private final TextField sumField = new TextField("Total sum");

    private final Grid<Result> resultGrid = new Grid<>(Result.class);

    private final ComboBox<Facility> filterForRiskFacility = new ComboBox<>("Facility");
    private final ComboBox<Substance> filterForRiskSubstance = new ComboBox<>("Substance");
    private final IntegerField filterForRiskYear = new IntegerField("Year");
    private List<Risk> filteredRisk;

    private final Grid<Risk> riskGrid = new Grid<>(Risk.class);

    private final LossService lossService;

    private final ComboBox<Facility> filterForLossFacility = new ComboBox<>("Facility");
    private final ComboBox<Substance> filterForLossSubstance = new ComboBox<>("Substance");
    private final IntegerField filterForLossYear = new IntegerField("Year");

    private List<Loss> filteredLoss;

    private final Grid<Loss> lossGrid = new Grid<>(Loss.class);



    public View(FacilityRepository facilityRepository, SubstanceRepository substanceRepository,
                EmissionRepository emissionRepository, TaxRepository taxRepository,
                ResultRepository resultRepository, RiskRepository riskRepository,
                LossRepository lossRepository, ResultService resultService, RiskService riskService,
                LossService lossService) {
        this.resultRepository = resultRepository;
        this.resultService = resultService;
        this.lossService = lossService;

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

        NumberField massConsumption = new NumberField("Mass Consumption");
        substanceBinder.bind(massConsumption, "massConsumption");

        TextField units = new TextField("Units");
        substanceBinder.bind(units, "units");

        NumberField refConcentration = new NumberField("Referenced Concentration");
        substanceBinder.bind(refConcentration, "refConcentration");

        NumberField tlv = new NumberField("Threshold Limit Value");
        substanceBinder.bind(tlv, "tlv");

        IntegerField massFlowRate = new IntegerField("Mass Flow Rate");
        substanceBinder.bind(massFlowRate, "massFlowRate");

        substanceLayout.add(substanceName, massConsumption, units,
                refConcentration, tlv, massFlowRate);
        add(getForm(substanceLayout, substanceBinder, substanceRepository, Substance.class));

        Grid<Substance> substanceGrid = new Grid<>(Substance.class);
        substanceGrid.setColumns("id", "name", "massConsumption", "units", "refConcentration",
                "slopeFactor", "tlv", "massFlowRate", "toxicityClass");
        substanceGrid.setItems(substanceRepository.findAll());
        add(substanceGrid);
        add(new H3("\n"));


        add(new H3("Emission"));
        final Binder<Emission> emissionBinder = new Binder<>(Emission.class);
        final ComboBox<Facility> emissionFacility = new ComboBox<>("Facility of emission");
        emissionBinder.bind(emissionFacility, "facility");
        emissionFacility.setItems(facilityRepository.findAll());
        emissionFacility.setClearButtonVisible(true);

/*
        Good decision for cases when need to show in combobox something different from actual items in it:

        idFacility.setItemLabelGenerator(id -> {
            List<Facility> list = facilityRepository.findAll();
            for (Facility facility: list) {
                if (facility.getId().equals(id)) return facility.getName();
            }
            return null;
        });
*/

        final ComboBox<Substance> emissionSubstance = new ComboBox<>("Substance of emission");
        emissionBinder.bind(emissionSubstance, "substance");
        emissionSubstance.setItems(substanceRepository.findAll());
        emissionSubstance.setClearButtonVisible(true);

        var emissionLayout = new HorizontalLayout();
        final IntegerField year = new IntegerField("Year");
        emissionBinder.bind(year, "year");
        final BigDecimalField amount = new BigDecimalField("Amount");
        emissionBinder.bind(amount, "amount");

        emissionLayout.add(emissionFacility, emissionSubstance, year, amount);
        add(getForm(emissionLayout, emissionBinder, emissionRepository, Emission.class));

        final Grid<Emission> emissionGrid = new Grid<>(Emission.class);
        emissionGrid.setColumns("id", "facility", "substance", "year", "amount", "concentration");
        emissionGrid.setItems(emissionRepository.findAll());
        add(emissionGrid);
        add(new H3("\n"));

        add(new H3("Tax"));
        final Binder<Tax> taxBinder = new Binder<>(Tax.class);

        final ComboBox<Substance> taxSubstance = new ComboBox<>("ID of substance");
        taxSubstance.setItems(substanceRepository.findAll());
        taxSubstance.setClearButtonVisible(true);
        taxBinder.bind(taxSubstance, "substance");

        final BigDecimalField rate = new BigDecimalField("Tax rate");
        taxBinder.bind(rate, "rate");

        var taxLayout = new HorizontalLayout();
        taxLayout.add(taxSubstance, rate);
        add(getForm(taxLayout, taxBinder, taxRepository, Tax.class));

        final Grid<Tax> taxGrid = new Grid<>(Tax.class);
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
        filterRiskButton.addClickListener(clicked -> {
            chooseFilterForEntityOfEmission(filteredRisk, riskRepository,
                    filterForRiskFacility.getValue(), filterForRiskSubstance.getValue(), filterForRiskYear.getValue());
            riskGrid.setItems(filteredRisk);
            riskGrid.getDataProvider().refreshAll();
        });


        riskGrid.setColumns("id", "emission.facility", "emission.substance",
                "emission.year", "nonCarcinogenRisk", "nonCarcinogenMessage",
                "carcinogenRisk", "carcinogenMessage");
        riskGrid.setItems(filteredRisk);

        add(riskLayout, riskGrid);
        add(new H3("\n"));

        add(new H3("Loss"));
        var lossLayout = new HorizontalLayout();

        Button calculateLossesButton = new Button("Calculate Losses");
        calculateLossesButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        calculateLossesButton.addClickListener(clicked -> {
            lossService.calculateAndCreateLosses();
            lossGrid.getDataProvider().refreshAll();
        });
        add(calculateLossesButton);


        filterForLossFacility.setItems(facilityRepository.findAll());
        filterForLossFacility.setClearButtonVisible(true);

        filterForLossSubstance.setItems(substanceRepository.findAll());
        filterForLossSubstance.setClearButtonVisible(true);

        Button filterLossButton = new Button("Filter");
        filterLossButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        lossLayout.setAlignItems(Alignment.BASELINE);
        lossLayout.add(filterForLossFacility, filterForLossSubstance,
                filterForLossYear, filterLossButton);

        filteredLoss = lossRepository.findAll();
        filterLossButton.addClickListener(clicked -> chooseFilterForEntityOfEmission(filteredLoss, lossRepository,
                filterForLossFacility.getValue(), filterForLossSubstance.getValue(), filterForLossYear.getValue()));
        lossGrid.setItems(filteredLoss);
        lossGrid.getDataProvider().refreshAll();

        lossGrid.setColumns("id", "emission.facility", "emission.substance",
                "emission.year", "lossValue");
        lossGrid.setItems(filteredLoss);

        add(lossLayout, lossGrid);
    }

    private <ENTITY, T extends JpaRepository<ENTITY, Long>> HorizontalLayout getForm(HorizontalLayout layout, Binder<ENTITY> binder, T repository, Class<ENTITY> beanType) {
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

    private <T extends EntityOfEmission, RepositoryT extends EntityOfEmissionRepository<T>> void chooseFilterForEntityOfEmission(
            List<T> filtered, RepositoryT repository, Facility facilityValue,
            Substance substanceValue, Integer yearValue) {
        filtered.clear();
        filtered.addAll(repository.findAll((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<Risk, Emission> emissionJoin = root.join("emission");

            if (facilityValue != null) {
                predicates.add(builder.equal(emissionJoin.get("facility"), facilityValue));
            }

            if (substanceValue != null) {
                predicates.add(builder.equal(emissionJoin.get("substance"), substanceValue));
            }

            if (yearValue != null) {
                predicates.add(builder.equal(emissionJoin.get("year"), yearValue));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        }));
    }



}
