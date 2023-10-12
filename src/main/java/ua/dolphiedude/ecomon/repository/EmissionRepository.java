package ua.dolphiedude.ecomon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.dolphiedude.ecomon.entity.Emission;

@Repository
public interface EmissionRepository extends JpaRepository<Emission, Long> {

}