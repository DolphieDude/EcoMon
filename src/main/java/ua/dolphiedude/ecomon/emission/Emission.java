package ua.dolphiedude.ecomon.emission;

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

    private Integer year;

    private Double amount;

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
