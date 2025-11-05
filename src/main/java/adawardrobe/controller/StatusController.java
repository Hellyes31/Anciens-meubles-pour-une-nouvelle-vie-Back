package adawardrobe.controller;


import adawardrobe.model.Status;
import adawardrobe.service.StatusService;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/status")
@CrossOrigin(origins = "http://localhost:3030")
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService){
        this.statusService = statusService;
    }


    @GetMapping
    public List<Status> getAllStatus(){
        return statusService.getAllStatus();
    }

    @PostMapping
    public ResponseEntity<Status> createStatus(@RequestBody Status status){
        Status statusCreated = statusService.createStatus(status);
        return new ResponseEntity<>(statusCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> getStatusById(@PathVariable String id) {
        Optional<Status> status = statusService.getStatusById(id);

        if (status.isPresent()) {
            return new ResponseEntity<>(status.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // PUT update
    @PutMapping("/{id}")
    public ResponseEntity<Status> updateStatus(@PathVariable String id, @RequestBody Status statusDetails) {
        Optional<Status> updatedStatus = statusService.updateStatus(id, statusDetails);

        if (updatedStatus.isPresent()) {
            return new ResponseEntity<>(updatedStatus.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable String id) {
        boolean deleted = statusService.deleteStatus(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
