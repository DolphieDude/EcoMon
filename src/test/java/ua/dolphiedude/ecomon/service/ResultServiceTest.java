package ua.dolphiedude.ecomon.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.dolphiedude.ecomon.entity.Emission;
import ua.dolphiedude.ecomon.entity.Result;
import ua.dolphiedude.ecomon.entity.Substance;
import ua.dolphiedude.ecomon.entity.Tax;
import ua.dolphiedude.ecomon.repository.EmissionRepository;
import ua.dolphiedude.ecomon.repository.ResultRepository;
import ua.dolphiedude.ecomon.repository.TaxRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = ServiceTestConfig.class)
class ResultServiceTest {
    @Autowired
    private ResultService service;
    @MockBean
    private ResultRepository mockResultRepo;
    @MockBean
    private EmissionRepository mockEmissionRepo;
    @MockBean
    private TaxRepository mockTaxRepo;

    @Test
    void shouldSaveCalculatedResult() {
        Substance substance = new Substance();

        BigDecimal amount = new BigDecimal("20.75");
        Emission emission = new Emission(substance, amount);

        BigDecimal rate = new BigDecimal("15.25");
        Tax tax = new Tax(substance, rate);

        Mockito.when(mockEmissionRepo.findAll()).thenReturn(List.of(emission));
        Mockito.when(mockResultRepo.findByEmission(emission)).thenReturn(null);
        Mockito.when(mockTaxRepo.findBySubstance(substance)).thenReturn(tax);

        service.calculateAndCreateResults();

        Result expected = new Result(emission, amount.multiply(rate));
        Mockito.verify(mockResultRepo).save(expected);
    }

    @Test
    public void shouldReturnSumOfTaxesValuesOfResultsList() {
        List<Result> resultList = new ArrayList<>();

        resultList.add(new Result(new BigDecimal("10.5")));
        resultList.add(new Result(new BigDecimal("15.25")));
        resultList.add(new Result(new BigDecimal("20.75")));

        BigDecimal expected = new BigDecimal("10.5").add(new BigDecimal("15.25"))
                .add(new BigDecimal("20.75"));
        BigDecimal actual = service.getSumOfResult(resultList);
        assertEquals(expected, actual);
    }
}
