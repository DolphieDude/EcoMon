package ua.dolphiedude.ecomon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Emission {

    @Id
    @GeneratedValue
    @Column(name = "id_emission")
    private Long id;

    private Long idFacility;
    private Long idSubstance;

    private int year;

    private double amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdFacility() {
        return idFacility;
    }

    public void setIdFacility(Long idFacility) {
        this.idFacility = idFacility;
    }

    public Long getIdSubstance() {
        return idSubstance;
    }

    public void setIdSubstance(Long idSubstance) {
        this.idSubstance = idSubstance;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
