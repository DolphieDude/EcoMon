package ua.dolphiedude.ecomon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
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

}
