package ua.dolphiedude.ecomon.emission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import ua.dolphiedude.ecomon.facility.FacilityRepository;
import ua.dolphiedude.ecomon.substance.SubstanceRepository;

public class EmissionWithNames extends Emission {
    String facility;
    String substance;

    public EmissionWithNames(FacilityRepository facilityRepository, SubstanceRepository substanceRepository, Emission original) {
        facility = facilityRepository.getReferenceById(original.getIdFacility()).getName();
        substance = substanceRepository.getReferenceById(original.getIdSubstance()).getName();
        this.setId(original.getId());
        this.setYear(original.getYear());
        this.setAmount(original.getAmount());
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getSubstance() {
        return substance;
    }

    public void setSubstance(String substance) {
        this.substance = substance;
    }
}
