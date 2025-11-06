package adawardrobe.repository;

import adawardrobe.model.Color;
import adawardrobe.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
