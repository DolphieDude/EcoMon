package ua.dolphiedude.ecomon.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.dolphiedude.ecomon.entity.Emission;
import ua.dolphiedude.ecomon.entity.Result;
import ua.dolphiedude.ecomon.repository.EmissionRepository;
import ua.dolphiedude.ecomon.repository.ResultRepository;
import ua.dolphiedude.ecomon.repository.TaxRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
public class ResultService {
    private final EmissionRepository emissionRepository;

    private final TaxRepository taxRepository;

    private final ResultRepository resultRepository;

    public ResultService(EmissionRepository emissionRepository, TaxRepository taxRepository,
                         ResultRepository resultRepository) {
        this.emissionRepository = emissionRepository;
        this.taxRepository = taxRepository;
        this.resultRepository = resultRepository;
    }

    public void calculateAndCreateResults() {
        List<Emission> emissionCollection = emissionRepository.findAll();

        for (Emission emission : emissionCollection) {
            Result result;
            Result foundResult = resultRepository.findByEmission(emission);
            result = Objects.requireNonNullElseGet(foundResult, Result::new);

            BigDecimal rate = taxRepository.findBySubstance(emission.getSubstance()).getRate();
            BigDecimal amount = emission.getAmount();

            result.setEmission(emission);

            BigDecimal taxesValue = amount.multiply(rate);
            result.setTaxesValue(taxesValue);

            resultRepository.save(result);
        }
    }

    public BigDecimal getSumOfResult(List<Result> results) {
        BigDecimal sum = new BigDecimal(0);
        for (Result result : results) {
            sum = sum.add(result.getTaxesValue());
        }
        return sum;
    }
}
