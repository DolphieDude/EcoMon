package ua.dolphiedude.ecomon.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ua.dolphiedude.ecomon.entity.EntityOfEmission;

import java.util.List;

@NoRepositoryBean
public interface EntityOfEmissionRepository<T extends EntityOfEmission> extends JpaRepository<T, Long> {
    List<T> findAll(Specification<T> spec);

}
