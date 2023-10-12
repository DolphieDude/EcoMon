package ua.dolphiedude.ecomon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.dolphiedude.ecomon.entity.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

}
