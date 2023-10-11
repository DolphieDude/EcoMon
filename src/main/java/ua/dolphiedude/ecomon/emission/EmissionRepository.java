package ua.dolphiedude.ecomon.emission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface EmissionRepository extends JpaRepository<Emission, Long> {

}
