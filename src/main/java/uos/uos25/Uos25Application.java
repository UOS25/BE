package uos.uos25;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Uos25Application {

    public static void main(String[] args) {
        SpringApplication.run(Uos25Application.class, args);
    }
}
