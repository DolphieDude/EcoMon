package ua.dolphiedude.ecomon.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import ua.dolphiedude.ecomon.entity.Emission;
import ua.dolphiedude.ecomon.entity.Risk;

import java.util.List;

@Repository
public interface RiskRepository extends EntityOfEmissionRepository<Risk> {

    Risk findByEmission(Emission emission);

    List<Risk> findAll(Specification<Risk> spec);

}
