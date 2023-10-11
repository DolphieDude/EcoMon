package ua.dolphiedude.ecomon.facility;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
    @Query("select id from Facility ")
    Collection<Long> getIds();

}
