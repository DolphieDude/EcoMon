package ua.dolphiedude.ecomon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Substance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "units")
    private String units;

    @Column(name = "mass_consumption")
    private Double massConsumption;

    @Override
    public String toString() {
        return name;
    }
}
