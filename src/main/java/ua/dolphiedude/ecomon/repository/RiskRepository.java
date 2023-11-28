package ua.dolphiedude.ecomon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.dolphiedude.ecomon.entity.*;

import java.util.List;

@Repository
public interface RiskRepository extends JpaRepository<Risk, Long> {

    Risk findByEmission(Emission emission);

    List<Risk> findByEmissionFacility(Facility facility);

    List<Risk> findByEmissionYear(Integer year);

    List<Risk> findByEmissionSubstance(Substance substance);

    @Query("select risk from Risk risk where risk.emission.facility = :facility and risk.emission.year = :year")
    List<Risk> findByEmissionFacilityAndEmissionYear(Facility facility, Integer year);


    List<Risk> findByEmissionFacilityAndEmissionSubstanceAndEmissionYear
            (Facility facility, Substance substance, Integer year);
}
