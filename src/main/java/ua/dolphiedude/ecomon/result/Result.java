package ua.dolphiedude.ecomon.result;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import ua.dolphiedude.ecomon.emission.Emission;

@Entity
@Getter
@Setter
public class Result {
    @Id
    @GeneratedValue
    @Column(name = "id_result")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_emission")
    private Emission resultEmission;

    private Double taxesValue;
}
