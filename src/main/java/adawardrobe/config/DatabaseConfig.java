package adawardrobe.config;

import com.zaxxer.hikari.HikariDataSource;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    private String dbUrl;
    private String dbUser;
    private String dbPass;

    public DatabaseConfig() {
        Dotenv dotenv = Dotenv.load();
        dbUrl = dotenv.get("DB_URL");
        dbUser = dotenv.get("DB_USERNAME");
        dbPass = dotenv.get("DB_PASSWORD");
    }

    @Bean
    public DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(dbUrl);
        ds.setUsername(dbUser);
        ds.setPassword(dbPass);
        return ds;
    }
}

