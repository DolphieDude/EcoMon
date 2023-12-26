    package ua.dolphiedude.ecomon.entity;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotNull;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.math.BigDecimal;

    @Entity
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Table(name = "loss")
    public class Loss extends EntityOfEmission {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Long id;

        @NotNull
        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "emission_id", nullable = false, referencedColumnName = "id")
        private Emission emission;

        @NotNull
        @Column(name = "loss_value", nullable = false, precision = 38, scale = 2)
        private BigDecimal lossValue;

    }
