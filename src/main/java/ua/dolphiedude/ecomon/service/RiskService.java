package ua.dolphiedude.ecomon.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.dolphiedude.ecomon.entity.Emission;
import ua.dolphiedude.ecomon.entity.Risk;
import ua.dolphiedude.ecomon.entity.Substance;
import ua.dolphiedude.ecomon.repository.*;

import java.util.List;
import java.util.Objects;

@Transactional
@AllArgsConstructor
@Service
public class RiskService {

    private final EmissionRepository emissionRepository;

    private final RiskRepository riskRepository;

    public void calculateAndCreateRisks() {
        List<Emission> emissionCollection = emissionRepository.findAll();

        for (Emission emission : emissionCollection) {
            Risk risk;
            Risk foundRisk = riskRepository.findByEmission(emission);
            risk = Objects.requireNonNullElseGet(foundRisk, Risk::new);
            risk.setEmission(emission);

            Double nonCarcinogenRisk = calculateNonCarcinogen(emission);
            risk.setNonCarcinogenRisk(nonCarcinogenRisk);

            String nonCarcinogenMessage;
            if (nonCarcinogenRisk > 1) {
                nonCarcinogenMessage = "It may pose a risk, depends on HQ";
            } else if (nonCarcinogenRisk == 1.) {
                nonCarcinogenMessage = "Allowed risk but cannot be considered acceptable";
            } else {
                nonCarcinogenMessage = "No significant danger";
            }
            risk.setNonCarcinogenMessage(nonCarcinogenMessage);



            Double carcinogenRisk = calculateCarcinogen(emission);
            risk.setCarcinogenRisk(carcinogenRisk);

            String carcinogenMessage;
            if (carcinogenRisk == null) {
                carcinogenMessage = null;
            } else if (carcinogenRisk > Math.pow(10, -3)) {
                carcinogenMessage = "High";
            } else if (Math.pow(10, -4) < carcinogenRisk && carcinogenRisk <= Math.pow(10, -3)) {
                carcinogenMessage = "Average";
            } else if (Math.pow(10, -6) < carcinogenRisk && carcinogenRisk <= Math.pow(10, -4)) {
                carcinogenMessage = "Low";
            } else {
                carcinogenMessage = "Minimum";
            }
            risk.setCarcinogenMessage(carcinogenMessage);

            riskRepository.save(risk);
        }
    }

    private Double calculateNonCarcinogen(Emission emission) {
        Substance substance = emission.getSubstance();

        Double RfC = substance.getRefConcentration();
        Double concentration = emission.getConcentration();

        return concentration / RfC;
    }

    private Double calculateCarcinogen(Emission emission) {
        Substance substance = emission.getSubstance();

        Double sf = substance.getSlopeFactor();
        if (sf == null) return null;
        Double concentration = emission.getConcentration();

        double bw = 65., ef = 350., tout = 5.3, tin = 18.7, at = 70., vout = 1.4, vin = 0.63, ed = 30.;
        double addLadd = (((concentration * tout * vout) + (concentration * tin * vin)) * ef * ed) / (bw * at * 365);

        return addLadd * sf;
    }
}
