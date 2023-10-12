package ua.dolphiedude.ecomon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.dolphiedude.ecomon.entity.Substance;

@Repository
public interface SubstanceRepository extends JpaRepository<Substance, Long> {

}
