package ua.dolphiedude.ecomon.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.dolphiedude.ecomon.entity.Result;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ResultServiceTest {


    @Autowired
    ResultService resultService;

    @Test
    public void shouldReturnSumOfTaxesValuesOfResultsList() {
        List<Result> resultList = new ArrayList<>();

        resultList.add(new Result(new BigDecimal("10.5")));
        resultList.add(new Result(new BigDecimal("15.25")));
        resultList.add(new Result(new BigDecimal("20.75")));

        BigDecimal expected = new BigDecimal("10.5").add(new BigDecimal("15.25"))
                            .add(new BigDecimal("20.75"));
        BigDecimal actual = resultService.getSumOfResult(resultList);
        System.out.println("hello");
        assertEquals(expected, actual);
    }
}
