package ua.dolphiedude.ecomon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.dolphiedude.ecomon.entity.Loss;

@Repository
public interface LossRepository extends JpaRepository<Loss, Long> {
}
