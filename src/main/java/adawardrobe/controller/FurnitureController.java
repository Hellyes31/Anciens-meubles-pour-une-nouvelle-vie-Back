package adawardrobe.controller;


import adawardrobe.model.Furniture;
import adawardrobe.service.FurnitureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/furniture")
@CrossOrigin(origins = "http://localhost:3030")
public class FurnitureController {

    private final FurnitureService furnitureService;

    public FurnitureController(FurnitureService furnitureService){
        this.furnitureService = furnitureService;
    }


    @GetMapping
    public List<Furniture> getAllFurniture(){
        return furnitureService.getAllFurniture();
    }

    @PostMapping
    public ResponseEntity<Furniture> createFurniture(@RequestBody Furniture furniture){
        Furniture furnitureCreated = furnitureService.createFurniture(furniture);
        return new ResponseEntity<>(furnitureCreated, HttpStatus.CREATED);
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
