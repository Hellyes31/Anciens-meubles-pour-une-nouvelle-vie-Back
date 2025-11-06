package adawardrobe.controller;


import adawardrobe.model.Type;
import adawardrobe.service.TypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/type")
@CrossOrigin(origins = "http://localhost:3030")
public class TypeController {

    private final TypeService typeService;

    public TypeController(TypeService typeService){
        this.typeService = typeService;
    }


    @GetMapping
    public List<Type> getAllType(){
        return typeService.getAllType();
    }

    @PostMapping
    public ResponseEntity<Type> createType(@RequestBody Type type){
        Type typeCreated = typeService.createType(type);
        return new ResponseEntity<>(typeCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type> getTypeById(@PathVariable String id) {
        Optional<Type> type = typeService.getTypeById(id);

        if (type.isPresent()) {
            return new ResponseEntity<>(type.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Type> updateType(@PathVariable String id, @RequestBody Type typeDetails) {
        Optional<Type> updatedType = typeService.updateType(id, typeDetails);

        if (updatedType.isPresent()) {
            return new ResponseEntity<>(updatedType.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable String id) {
        boolean deleted = typeService.deleteType(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
