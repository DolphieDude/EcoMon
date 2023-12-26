package ua.dolphiedude.ecomon.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.dolphiedude.ecomon.entity.*;

import java.util.List;

@Repository
public interface RiskRepository extends EntityOfEmissionRepository<Risk> {

    Risk findByEmission(Emission emission);

    List<Risk> findAll(Specification<Risk> spec);

    List<Risk> findByEmissionFacilityAndEmissionSubstanceAndEmissionYear(Facility facility, Substance substance, Integer year);

    List<Risk> findByEmissionFacilityAndEmissionSubstance(Facility facility, Substance substance);

    List<Risk> findByEmissionFacilityAndEmissionYear(Facility facility, Integer year);

    List<Risk> findByEmissionSubstanceAndEmissionYear(Substance substance, Integer year);

    List<Risk> findByEmissionFacility(Facility facility);

    List<Risk> findByEmissionSubstance(Substance substance);

    List<Risk> findByEmissionYear(Integer year);

    List<Risk> findByEmissionFacilityId(Long id);

}
