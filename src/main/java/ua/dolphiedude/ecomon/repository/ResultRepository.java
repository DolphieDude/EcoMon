package ua.dolphiedude.ecomon.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.dolphiedude.ecomon.entity.*;

import java.util.List;

@Repository
public interface ResultRepository extends EntityOfEmissionRepository<Result> {
    Result findByEmission(Emission emission);

    List<Result> findAll(Specification<Result> spec);

    @Query("select res from Result res where res.emission.facility = :facility")
    List<Result> findByFacility(Facility facility);

    @Query("select res from Result res where res.emission.year = :year")
    List<Result> findByYear(Integer year);

    @Query("select res from Result res where res.emission.facility = :facility and res.emission.year = :year")
    List<Result> findByFacilityAndYear(Facility facility, Integer year);
}
