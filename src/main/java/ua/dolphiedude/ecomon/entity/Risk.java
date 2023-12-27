package ua.dolphiedude.ecomon.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "risk_to_health")
@NoArgsConstructor
public class Risk extends EntityOfEmission {

    @Size(max = 45)
    @Column(name = "non_carcinogen_message", length = 45)
    private String nonCarcinogenMessage;

    @Size(max = 45)
    @Column(name = "carcinogen_message", length = 45)
    private String carcinogenMessage;

    @Column(name = "non_carcinogen_risk")
    private Double nonCarcinogenRisk;

    @Column(name = "carcinogen_risk")
    private Double carcinogenRisk;

}
