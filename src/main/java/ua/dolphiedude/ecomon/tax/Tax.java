package ua.dolphiedude.ecomon.tax;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import ua.dolphiedude.ecomon.result.ResultRepository;
import ua.dolphiedude.ecomon.substance.Substance;

@Entity
@Getter
@Setter
public class Tax {
    @Id
    @GeneratedValue
    @Column(name = "id_tax")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_substance")
    private Substance taxSubstance;

    private Double rate;
}
