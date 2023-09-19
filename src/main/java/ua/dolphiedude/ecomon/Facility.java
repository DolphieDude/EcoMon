package ua.dolphiedude.ecomon;

import jakarta.persistence.*;

@Entity
public class Facility {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String activity;
    private String ownership;
    private String ecologicalDescription;

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
