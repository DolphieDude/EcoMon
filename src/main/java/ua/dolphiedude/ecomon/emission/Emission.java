package ua.dolphiedude.ecomon.emission;

import jakarta.persistence.*;
import ua.dolphiedude.ecomon.facility.Facility;
import ua.dolphiedude.ecomon.substance.Substance;

@Entity
public class Emission {

    @Id
    @GeneratedValue
    @Column(name = "id_emission")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_facility")
    private Facility facility;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_substance")
    private Substance substance;

    private Integer year;

    private Double amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public Substance getSubstance() {
        return substance;
    }

    public void setSubstance(Substance substance) {
        this.substance = substance;
    }


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
