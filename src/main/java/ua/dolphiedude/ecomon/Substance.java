package ua.dolphiedude.ecomon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Substance {

    @Id
    @GeneratedValue
    @Column(name = "id_substance")
    private Long id;

    private String name;
    private String units;

    private double gdk;


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

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public double getGdk() {
        return gdk;
    }

    public void setGdk(double gdk) {
        this.gdk = gdk;
    }
}
