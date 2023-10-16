package ua.dolphiedude.ecomon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Substance {

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

    public Substance(String name, String units, Double gdk) {
        this.name = name;
        this.units = units;
        this.gdk = gdk;
    }
}
