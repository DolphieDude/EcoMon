package ua.dolphiedude.ecomon.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Loss {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "emission_id", nullable = false)
    private Long emissionId;

    @NotNull
    @Column(name = "loss_value", nullable = false, precision = 38, scale = 2)
    private BigDecimal lossValue;

}
