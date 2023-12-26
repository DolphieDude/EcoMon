package ua.dolphiedude.ecomon.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.dolphiedude.ecomon.entity.Emission;
import ua.dolphiedude.ecomon.entity.Loss;
import ua.dolphiedude.ecomon.entity.Risk;

import java.util.List;

@Repository
public interface LossRepository extends EntityOfEmissionRepository<Loss> {
    Loss findByEmission(Emission emission);

    List<Loss> findAll(Specification<Loss> spec);

}
