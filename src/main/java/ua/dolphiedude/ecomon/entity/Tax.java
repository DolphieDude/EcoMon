package ua.dolphiedude.ecomon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tax {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "substance_id")
    private Substance substance;

    private BigDecimal rate;

    public Tax(Substance substance, BigDecimal rate) {
        this.substance = substance;
        this.rate = rate;
    }

    public Tax(BigDecimal rate) {
        this.rate = rate;
    }
}
