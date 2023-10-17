package ua.dolphiedude.ecomon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.dolphiedude.ecomon.entity.Substance;
import ua.dolphiedude.ecomon.entity.Tax;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Long> {

    Tax findBySubstance(Substance substance);

}
