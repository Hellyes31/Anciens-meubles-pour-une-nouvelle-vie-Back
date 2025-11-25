package adawardrobe.service;

import adawardrobe.model.Color;
import adawardrobe.model.Furniture;
import adawardrobe.model.Photo;
import adawardrobe.model.User;
import adawardrobe.repository.ColorRepository;
import adawardrobe.repository.FurnitureRepository;
import adawardrobe.repository.PhotoRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class FurnitureService {
    private final FurnitureRepository furnitureRepository;
    private final ColorRepository colorRepository;
    private final PhotoRepository photoRepository;

    public FurnitureService(FurnitureRepository furnitureRepository,
                            ColorRepository colorRepository,
                            PhotoRepository photoRepository) {
        this.furnitureRepository = furnitureRepository;
        this.colorRepository = colorRepository;
        this.photoRepository = photoRepository;
    }

    public List<Furniture> getAllFurniture() {
        return furnitureRepository.findAll();
    }

    public Furniture createFurniture(Furniture furniture) {

        // --- gérer les couleurs ---
        if (furniture.getColor() != null) {
            Color attachedColor = colorRepository.findById(furniture.getColor().getId())
                    .orElseThrow(() -> new RuntimeException("Color introuvable: " + furniture.getColor().getId()));
            furniture.setColor(attachedColor);
        }
        Furniture savedFurniture = furnitureRepository.save(furniture);
        if (furniture.getPhotos() != null) {
            for (Photo photo : furniture.getPhotos()) {
                photo.setFurniture(savedFurniture);  // lie la photo au meuble existant
                photoRepository.save(photo);
            }
        }

        return savedFurniture;    // sauvegarde le meuble
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
