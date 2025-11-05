package adawardrobe.controller;


import adawardrobe.model.Color;
import adawardrobe.model.Photo;
import adawardrobe.service.ColorService;
import adawardrobe.service.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/photo")
@CrossOrigin(origins = "http://localhost:3030")
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService){
        this.photoService = photoService;
    }


    @GetMapping
    public List<Photo> getAllPhoto(){
        return photoService.getAllPhoto();
    }

    @PostMapping
    public ResponseEntity<Photo> createPhoto(@RequestBody Photo photo){
        Photo photoCreated = photoService.createPhoto(photo);
        return new ResponseEntity<>(photoCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Photo> getPhotoById(@PathVariable String id) {
        Optional<Photo> photo = photoService.getPhotoById(id);

        if (photo.isPresent()) {
            return new ResponseEntity<>(photo.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Photo> updatePhoto(@PathVariable String id, @RequestBody Photo photoDetails) {
        Optional<Photo> updatedPhoto = photoService.updatePhoto(id, photoDetails);

        if (updatedPhoto.isPresent()) {
            return new ResponseEntity<>(updatedPhoto.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable String id) {
        boolean deleted = photoService.deletePhoto(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
