package ua.dolphiedude.ecomon.facility;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
}
