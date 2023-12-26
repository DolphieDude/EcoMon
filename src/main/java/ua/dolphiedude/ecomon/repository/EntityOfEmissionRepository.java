package ua.dolphiedude.ecomon.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import ua.dolphiedude.ecomon.entity.EntityOfEmission;
import ua.dolphiedude.ecomon.entity.Facility;
import ua.dolphiedude.ecomon.entity.Substance;

import java.util.List;

@NoRepositoryBean
public interface EntityOfEmissionRepository<T extends EntityOfEmission> extends JpaRepository<T, Long> {
    List<T> findAll(Specification<T> spec);

}
