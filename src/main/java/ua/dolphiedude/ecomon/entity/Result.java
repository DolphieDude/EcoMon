package ua.dolphiedude.ecomon.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    @Id
    @GeneratedValue
    @Column(name = "id_result")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_emission")
    private Emission resultEmission;

    private BigDecimal taxesValue;

    public Result(BigDecimal taxesValue) {
        this.taxesValue = taxesValue;
    }

    public Result(Emission resultEmission, BigDecimal taxesValue) {
        this.resultEmission = resultEmission;
        this.taxesValue = taxesValue;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(id, result.id) &&
                Objects.equals(resultEmission, result.resultEmission)&& Objects.equals(taxesValue, result.taxesValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, resultEmission, taxesValue);
    }
}
