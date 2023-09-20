package ua.dolphiedude.ecomon.emission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.dolphiedude.ecomon.facility.Facility;

import java.util.Collection;

public interface EmissionRepository extends JpaRepository<Emission, Long> {
    @Query("select f from Facility f")
    Collection<Facility> getFacilities();

    @Query("select id from Facility ")
    Collection<Long> getFacilityIds();

    @Query("select name from Facility ")
    Collection<String> getFacilityNames();

//    @Query("select Facility.name from Facility join Emission on Facility.id = Emission.idFacility")
//    Collection<String> getFacilityNamesAndIds();

    @Query("select id from Substance ")
    Collection<Long> getSubstanceIds();

}
