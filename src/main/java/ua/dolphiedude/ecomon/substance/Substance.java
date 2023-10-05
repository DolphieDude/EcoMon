package ua.dolphiedude.ecomon.substance;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data public class Substance {
    @Id
    @GeneratedValue
    @Column(name = "id_substance")
    private Long id;

    private String name;
    private String units;

    private Double gdk;

    @Override
    public String toString() {
        return name;
    }

}
