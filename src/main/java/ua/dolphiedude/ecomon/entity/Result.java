package ua.dolphiedude.ecomon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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

    private BigDecimal taxesValue;

}
