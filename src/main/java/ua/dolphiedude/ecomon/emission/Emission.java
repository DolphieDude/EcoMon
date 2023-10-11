package ua.dolphiedude.ecomon.emission;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ua.dolphiedude.ecomon.facility.Facility;
import ua.dolphiedude.ecomon.substance.Substance;

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
