package adawardrobe.controller;


import adawardrobe.model.Color;
import adawardrobe.service.ColorService;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/color")
@CrossOrigin(origins = "http://localhost:3030")
public class ColorController {

    private final ColorService colorService;

    public ColorController(ColorService colorService){
        this.colorService = colorService;
    }


    @GetMapping
    public List<Color> getAllColor(){
        return colorService.getAllColor();
    }

    @PostMapping
    public ResponseEntity<Color> createColor(@RequestBody Color color){
        Color colorCreated = colorService.createColor(color);
        return new ResponseEntity<>(colorCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Color> getColorById(@PathVariable String id) {
        Optional<Color> color = colorService.getColorById(id);

        if (color.isPresent()) {
            return new ResponseEntity<>(color.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Color> updateColor(@PathVariable String id, @RequestBody Color colorDetails) {
        Optional<Color> updatedColor = colorService.updateColor(id, colorDetails);

        if (updatedColor.isPresent()) {
            return new ResponseEntity<>(updatedColor.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColor(@PathVariable String id) {
        boolean deleted = colorService.deleteColor(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

