package ua.dolphiedude.ecomon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.dolphiedude.ecomon.entity.Emission;
import ua.dolphiedude.ecomon.entity.Facility;
import ua.dolphiedude.ecomon.entity.Result;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    Result findByResultEmission(Emission emission);

    @Query("select res from Result res where res.resultEmission.emissionFacility = :facility")
    List<Result> findByResultFacility(Facility facility);

    @Query("select res from Result res where res.resultEmission.year = :year")
    List<Result> findByResultYear(Integer year);

    @Query("select res from Result res where res.resultEmission.emissionFacility = :facility and res.resultEmission.year = :year")
    List<Result> findByResultFacilityAndYear(Facility facility, Integer year);
}
