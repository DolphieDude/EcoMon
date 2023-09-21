package ua.dolphiedude.ecomon.substance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface SubstanceRepository extends JpaRepository<Substance, Long> {
    @Query("select id from Substance")
    Collection<Long> getIds();
}
