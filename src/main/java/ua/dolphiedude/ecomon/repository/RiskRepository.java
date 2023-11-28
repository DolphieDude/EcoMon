package ua.dolphiedude.ecomon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.dolphiedude.ecomon.entity.Emission;
import ua.dolphiedude.ecomon.entity.Risk;

@Repository
public interface RiskRepository extends JpaRepository<Risk, Long> {

    Risk findByEmission(Emission emission);

}
