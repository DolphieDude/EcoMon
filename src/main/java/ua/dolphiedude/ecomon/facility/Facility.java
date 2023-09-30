package ua.dolphiedude.ecomon.facility;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getEcologicalDescription() {
        return ecologicalDescription;
    }

    public void setEcologicalDescription(String ecologicalDescription) {
        this.ecologicalDescription = ecologicalDescription;
    }
}
