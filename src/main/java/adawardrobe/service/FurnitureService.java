package adawardrobe.service;

import adawardrobe.model.Furniture;
import adawardrobe.model.Photo;
import adawardrobe.model.User;
import adawardrobe.repository.FurnitureRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class FurnitureService {
    private final FurnitureRepository furnitureRepository;

    public FurnitureService(FurnitureRepository furnitureRepository) {
        this.furnitureRepository = furnitureRepository;
    }

    public List<Furniture> getAllFurniture() {
        return furnitureRepository.findAll();
    }

    public Furniture createFurniture(Furniture furniture) {
        return furnitureRepository.save(furniture);
    }

    public Optional<Furniture> getFurnitureById(String id) {
        return furnitureRepository.findById(Long.parseLong(id));
    }

    public Optional<Furniture> updateFurniture(String id, Furniture furnitureDetails) {
        Optional<Furniture> furniture = furnitureRepository.findById(Long.parseLong(id));

        if (furniture.isPresent()) {
            Furniture existingFurniture = furniture.get();
            existingFurniture.setTitle(furnitureDetails.getTitle());
            existingFurniture.setDescription(furnitureDetails.getDescription());
            existingFurniture.setPrice(furnitureDetails.getPrice());

            Furniture updatedFurniture = furnitureRepository.save(existingFurniture);
            return Optional.of(updatedFurniture);
        }
        return Optional.empty();
    }

    public boolean deleteFurniture(String id) {
        Optional<Furniture> furniture = furnitureRepository.findById(Long.parseLong(id));

        if (furniture.isPresent()) {
            furnitureRepository.delete(furniture.get());
            return true;
        }
        return false;
    }
}
