package ua.dolphiedude.ecomon.substance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SubstanceRepository extends JpaRepository<Substance, Long> {

}
