package ua.dolphiedude.ecomon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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

    private BigDecimal rate;

}
