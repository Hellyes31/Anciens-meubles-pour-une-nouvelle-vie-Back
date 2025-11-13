package adawardrobe.security;

import io.imagekit.sdk.ImageKit;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; // annotation Spring

@Configuration
public class ImageKitConfig {

    @Bean
    public ImageKit imageKit() {
        // Charge les variables depuis le fichier .env
        Dotenv dotenv = Dotenv.load();
        String publicKey = dotenv.get("IMAGEKIT_PUBLIC_KEY");
        String privateKey = dotenv.get("IMAGEKIT_PRIVATE_KEY");
        String urlEndpoint = dotenv.get("IMAGEKIT_URL_ENDPOINT");

        // Utilisation du nom complet pour la Configuration du SDK
        io.imagekit.sdk.config.Configuration config =
                new io.imagekit.sdk.config.Configuration(publicKey, privateKey, urlEndpoint);

        // Récupération de l'instance ImageKit et application de la config
        ImageKit imageKit = ImageKit.getInstance();
        imageKit.setConfig(config);

        return imageKit;
    }
}
