package ua.dolphiedude.ecomon.emission;

import jakarta.persistence.*;
import lombok.Data;
import ua.dolphiedude.ecomon.facility.Facility;
import ua.dolphiedude.ecomon.substance.Substance;

@Entity
@Data public class Emission {

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

    private Double amount;

}
