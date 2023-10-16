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
public class Emission {

    @Id
    @GeneratedValue
    @Column(name = "id_emission")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_facility")
    private Facility emissionFacility;

    @ManyToOne
    @JoinColumn(name = "id_substance")
    private Substance emissionSubstance;

    private Integer year;

    private BigDecimal amount;

    public Emission(Facility emissionFacility, Substance emissionSubstance, Integer year, BigDecimal amount) {
        this.emissionFacility = emissionFacility;
        this.emissionSubstance = emissionSubstance;
        this.year = year;
        this.amount = amount;
    }

    public Emission(Substance emissionSubstance, BigDecimal amount) {
        this.emissionSubstance = emissionSubstance;
        this.amount = amount;
    }
}
