package thaitay.com.fashion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@CacheConfig
public class FashionApplication {

    public static void main(String[] args) {

//        System.setProperty("server.port", "8000");
        SpringApplication.run(FashionApplication.class, args);

    }
}
