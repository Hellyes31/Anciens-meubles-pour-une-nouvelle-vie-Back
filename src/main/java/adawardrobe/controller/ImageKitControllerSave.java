package adawardrobe.controller;

import adawardrobe.model.FileMetadataDto;
import adawardrobe.model.ImageKit;
import adawardrobe.repository.ImageFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/imagekit")
public class ImageKitControllerSave {

    @Autowired
    private ImageFileRepository repository;

    @PostMapping("/save-metadata")
    public ResponseEntity<Map<String, Object>> saveMetadata(@RequestBody FileMetadataDto dto) {
        if (dto.getFileId() == null || dto.getUrl() == null) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "error", "fileId et url requis"));
        }

        ImageKit imageKit = new ImageKit();
        imageKit.setFileId(dto.getFileId());
        imageKit.setFileUrl(dto.getUrl());
        imageKit.setUserId(dto.getUserId() != null ? dto.getUserId() : "anonymous");
        imageKit.setUploadTimestamp(Instant.now());

        repository.save(imageKit);

        return ResponseEntity.ok(Map.of("success", true));
    }
}

