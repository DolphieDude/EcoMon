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

        @NotNull
        @Column(name = "loss_value", nullable = false, precision = 38, scale = 2)
        private BigDecimal lossValue;

    }
