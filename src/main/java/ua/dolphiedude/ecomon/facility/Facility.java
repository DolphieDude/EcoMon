package ua.dolphiedude.ecomon.facility;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data public class Facility {

    @Id
    @GeneratedValue
    @Column(name = "id_facility")
    private Long id;

    private String name;
    private String activity;
    private String ownership;
    private String ecologicalDescription;

    @Override
    public String toString() {
        return name;
    }

}
