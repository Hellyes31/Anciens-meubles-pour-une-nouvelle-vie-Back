package adawardrobe.service;


import adawardrobe.model.Status;
import adawardrobe.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    public Status createStatus(Status status) {
        return statusRepository.save(status);
    }

    public Optional<Status> getStatusById(String id) {
        return statusRepository.findById(Long.parseLong(id));
    }

    public Optional<Status> updateStatus(String id, Status statusDetails) {
        Optional<Status> status = statusRepository.findById(Long.parseLong(id));

        if (status.isPresent()) {
            Status existingStatus = status.get();
            existingStatus.setStatus(statusDetails.getStatus());

            Status updatedStatus = statusRepository.save(existingStatus);
            return Optional.of(updatedStatus);
        }
        return Optional.empty();
    }

    public boolean deleteStatus(String id) {
        Optional<Status> status = statusRepository.findById(Long.parseLong(id));

        if(status.isPresent()){
            statusRepository.delete(status.get());
            return true;
        }
        return false;
    }
}
