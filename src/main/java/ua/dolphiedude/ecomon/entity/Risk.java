package ua.dolphiedude.ecomon.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "risk_to_health")
@Getter
@Setter
@NoArgsConstructor
public class Risk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "emission_id", nullable = false, referencedColumnName = "id")
    private Emission emission;

    @Size(max = 45)
    @Column(name = "non_carcinogen_message", length = 45)
    private String nonCarcinogenMessage;

    @Size(max = 45)
    @Column(name = "carcinogen_message", length = 45)
    private String carcinogenMessage;

    @Column(name = "non_carcinogen_risk", columnDefinition = "double(0, 0) UNSIGNED")
    private Double nonCarcinogenRisk;

    @Column(name = "carcinogen_risk", columnDefinition = "double(0, 0) UNSIGNED")
    private Double carcinogenRisk;

}
