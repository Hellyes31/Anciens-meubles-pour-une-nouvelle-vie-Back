package adawardrobe.controller;


import adawardrobe.model.Furniture;
import adawardrobe.model.User;
import adawardrobe.repository.UserRepository;
import adawardrobe.service.FurnitureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/furniture")
@CrossOrigin(origins = "http://localhost:3030")
public class FurnitureController {

    private final FurnitureService furnitureService;
    private final UserRepository userRepository;

    public FurnitureController(FurnitureService furnitureService, UserRepository userRepository) {
        this.furnitureService = furnitureService;
        this.userRepository = userRepository;
    }


    @GetMapping
    public List<Furniture> getAllFurniture(){
        return furnitureService.getAllFurniture();
    }

    @PostMapping
    public Furniture createFurniture(@RequestBody Furniture furniture, Authentication authentication) {
        String username = authentication.getName();

        User seller = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        furniture.setSeller(seller);

        return furnitureService.createFurniture(furniture);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Furniture> getFurnitureById(@PathVariable String id) {
        Optional<Furniture> furniture = furnitureService.getFurnitureById(id);

        if (furniture.isPresent()) {
            return new ResponseEntity<>(furniture.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Furniture> updateFurniture(@PathVariable String id, @RequestBody Furniture furnitureDetails) {
        Optional<Furniture> updatedFurniture = furnitureService.updateFurniture(id, furnitureDetails);

        if (updatedFurniture.isPresent()) {
            return new ResponseEntity<>(updatedFurniture.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFurniture(@PathVariable String id) {
        boolean deleted = furnitureService.deleteFurniture(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
