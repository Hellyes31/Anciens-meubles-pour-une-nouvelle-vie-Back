package adawardrobe.service;


import adawardrobe.model.Color;
import adawardrobe.model.Photo;
import adawardrobe.repository.PhotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {
    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public List<Photo> getAllPhoto() {
        return photoRepository.findAll();
    }

    public Photo createPhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    public Optional<Photo> getPhotoById(String id) {
        return photoRepository.findById(Long.parseLong(id));
    }

    public Optional<Photo> updatePhoto(String id, Photo photoDetails) {
        Optional<Photo> photo = photoRepository.findById(Long.parseLong(id));

        if (photo.isPresent()) {
            Photo existingPhoto = photo.get();
            existingPhoto.setPhoto(photoDetails.getPhoto());

            Photo updatedPhoto = photoRepository.save(existingPhoto);
            return Optional.of(updatedPhoto);
        }

        return Optional.empty();
    }

    public boolean deletePhoto(String id) {
        Optional<Photo> photo = photoRepository.findById(Long.parseLong(id));

        if (photo.isPresent()) {
            photoRepository.delete(photo.get());
            return true;
        }
        return false;
    }
}
