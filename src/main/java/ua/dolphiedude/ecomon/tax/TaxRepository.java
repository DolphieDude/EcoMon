package ua.dolphiedude.ecomon.tax;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.dolphiedude.ecomon.substance.Substance;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Long> {

    Tax findByTaxSubstance(Substance substance);

}
