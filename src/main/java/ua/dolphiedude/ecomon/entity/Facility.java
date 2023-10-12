package ua.dolphiedude.ecomon.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Facility {

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
