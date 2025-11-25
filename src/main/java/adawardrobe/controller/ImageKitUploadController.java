package adawardrobe.controller;

import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.models.FileCreateRequest;
import io.imagekit.sdk.models.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@RestController
@RequestMapping("/api/imagekit")
public class ImageKitUploadController {

    @Autowired
    private ImageKit imageKit; // ton bean ImageKitConfig

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Créer le FileCreateRequest directement avec le contenu et le nom
            FileCreateRequest request = new FileCreateRequest(file.getBytes(), file.getOriginalFilename());

            // Upload
            Result result = imageKit.upload(request);

            // Retourner url et fileId
            return ResponseEntity.ok(Map.of(
                    "url", result.getUrl(),
                    "fileId", result.getFileId()
            ));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Upload échoué"));
        }
    }
}
