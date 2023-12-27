package ua.dolphiedude.ecomon.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.dolphiedude.ecomon.entity.Emission;
import ua.dolphiedude.ecomon.entity.Loss;
import ua.dolphiedude.ecomon.repository.EmissionRepository;
import ua.dolphiedude.ecomon.repository.LossRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
@AllArgsConstructor
public class LossService {

    private final EmissionRepository emissionRepository;

    private final LossRepository lossRepository;

    public void calculateAndCreateLosses() {
        List<Emission> emissionCollection = emissionRepository.findAll();

        for (Emission emission : emissionCollection) {
            Loss loss;
            Loss foundLoss = lossRepository.findByEmission(emission);
            loss = Objects.requireNonNullElseGet(foundLoss, Loss::new);

            Double concentration = emission.getConcentration();
            BigDecimal amount = emission.getAmount();
            double tlv = emission.getSubstance().getTlv();
            int massFlowRate = emission.getSubstance().getMassFlowRate();

            BigDecimal lossValue = new BigDecimal(0);

            if (amount.compareTo(BigDecimal.valueOf(0)) > 0 && massFlowRate > 0 &&
                    tlv > 0d && amount.compareTo(BigDecimal.valueOf(massFlowRate / 114.1552)) > 0) {
                BigDecimal minWage = BigDecimal.valueOf(6700);
                BigDecimal Ai = BigDecimal.valueOf(tlv > 1 ? 10 / tlv : 1 / tlv);
                BigDecimal Knas = BigDecimal.valueOf(1.80);
                BigDecimal Kf = BigDecimal.valueOf(1.25);
                BigDecimal Kt = Knas.multiply(Kf);
                BigDecimal Kzi = BigDecimal.valueOf(1);

                if (concentration > 0 && concentration > tlv) {
                    Kzi = BigDecimal.valueOf(concentration / tlv);
                }
                lossValue = amount.multiply(BigDecimal.valueOf(0.031709)).subtract(BigDecimal.valueOf(massFlowRate / 3600));
                lossValue = lossValue.multiply(BigDecimal.valueOf(3.6 * Math.pow(10, -3) * 8760));
                lossValue = lossValue.multiply(minWage).multiply(Ai).multiply(Kt).multiply(Kzi);
            }

            loss.setEmission(emission);
            loss.setLossValue(lossValue);

            lossRepository.save(loss);
        }
    }

    public BigDecimal getSumOfLoss(List<Loss> losses) {
        BigDecimal sum = new BigDecimal(0);
        for (Loss loss : losses) {
            sum = sum.add(loss.getLossValue());
        }
        return sum;
    }

}
