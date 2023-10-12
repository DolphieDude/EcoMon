package ua.dolphiedude.ecomon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.dolphiedude.ecomon.entity.Facility;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {

}
