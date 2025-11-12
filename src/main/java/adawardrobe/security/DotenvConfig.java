package adawardrobe.security;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig {
    @Bean
    public String secretKey() {
        Dotenv dotenv = Dotenv.load();
        return dotenv.get("SECRET_KEY");
    }
}
