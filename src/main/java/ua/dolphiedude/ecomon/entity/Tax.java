package ua.dolphiedude.ecomon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tax {

    @Id
    @GeneratedValue
    @Column(name = "id_tax")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_substance")
    private Substance taxSubstance;

    private BigDecimal rate;

    public Tax(Substance taxSubstance, BigDecimal rate) {
        this.taxSubstance = taxSubstance;
        this.rate = rate;
    }

    public Tax(BigDecimal rate) {
        this.rate = rate;
    }
}
