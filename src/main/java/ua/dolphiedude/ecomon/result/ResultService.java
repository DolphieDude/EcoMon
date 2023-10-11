package ua.dolphiedude.ecomon.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.dolphiedude.ecomon.emission.EmissionRepository;
import ua.dolphiedude.ecomon.tax.TaxRepository;
import ua.dolphiedude.ecomon.emission.Emission;

import java.util.Collection;
import java.util.List;

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

        for (Emission emission: emissionCollection) {
            Result result = new Result();
            Double rate = taxRepository.findByTaxSubstance(emission.getEmissionSubstance()).getRate();
            Double amount = emission.getAmount();

            result.setResultEmission(emission);

            Double taxesValue = amount * rate;
            result.setTaxesValue(taxesValue);

            resultRepository.save(result);
        }
    }

}
