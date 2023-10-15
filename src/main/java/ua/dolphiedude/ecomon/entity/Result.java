package ua.dolphiedude.ecomon.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

}
