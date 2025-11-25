package adawardrobe.controller;

import io.imagekit.sdk.ImageKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/imagekit")
public class ImageKitController {

    @Autowired
    private ImageKit imageKit;

    @GetMapping("/generate-auth-params")
    public ResponseEntity<Map<String, Object>> generateAuthParams() {
        Map<String, String> authParams = imageKit.getAuthenticationParameters();

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.putAll(authParams);

        return ResponseEntity.ok(response);
    }
}

