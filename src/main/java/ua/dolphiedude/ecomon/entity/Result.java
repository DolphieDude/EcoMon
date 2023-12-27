package ua.dolphiedude.ecomon.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Result extends EntityOfEmission {

    @Column(name = "taxes_value")
    private BigDecimal taxesValue;

    public Result(Emission emission, BigDecimal taxesValue) {
        setEmission(emission);
        this.taxesValue = taxesValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(super.getId(), result.getId()) &&
                Objects.equals(getEmission(), result.getEmission()) && Objects.equals(taxesValue, result.taxesValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmission(), taxesValue);
    }
}
