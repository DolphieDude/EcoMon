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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "emission_id")
    private Emission emission;

    @Column(name = "taxes_value")
    private BigDecimal taxesValue;

    public Result(BigDecimal taxesValue) {
        this.taxesValue = taxesValue;
    }

    public Result(Emission emission, BigDecimal taxesValue) {
        this.emission = emission;
        this.taxesValue = taxesValue;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(id, result.id) &&
                Objects.equals(emission, result.emission)&& Objects.equals(taxesValue, result.taxesValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emission, taxesValue);
    }
}
