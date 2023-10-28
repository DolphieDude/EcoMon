package ua.dolphiedude.ecomon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "activity")
    private String activity;
    @Column(name = "ownership")
    private String ownership;
    @Column(name = "ecological_description")
    private String ecologicalDescription;

    public Facility(String name, String activity, String ownership, String ecologicalDescription) {
        this.name = name;
        this.activity = activity;
        this.ownership = ownership;
        this.ecologicalDescription = ecologicalDescription;
    }

    @Override
    public String toString() {
        return name;
    }
}
