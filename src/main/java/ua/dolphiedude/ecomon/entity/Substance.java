package ua.dolphiedude.ecomon.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "substance")
@Getter
@Setter
@NoArgsConstructor
public class Substance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "units")
    private String units;

    @Column(name = "mass_consumption")
    private Double massConsumption;

    @NotNull
    @Column(name = "reference_concentration")
    private Double refConcentration;

    @Column(name = "slope_factor")
    private Double slopeFactor;
    @NotNull
    @Column(name = "threshold_limit_value", nullable = false)
    private Double tlv;

    @NotNull
    @Column(name = "mass_flow_rate", nullable = false)
    private Integer massFlowRate;

    @Column(name = "toxicity_class")
    private Integer toxicityClass;

    @OneToOne(mappedBy = "substance")
    private Tax tax;


    @Override
    public String toString() {
        return name;
    }
}
