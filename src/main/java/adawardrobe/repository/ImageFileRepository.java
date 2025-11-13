package adawardrobe.repository;

import adawardrobe.model.ImageKit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ImageFileRepository extends JpaRepository<ImageKit, Long> {
    List<ImageKit> findByUserId(String userId); //
}

