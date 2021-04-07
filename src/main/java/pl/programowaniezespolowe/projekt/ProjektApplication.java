package pl.programowaniezespolowe.projekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import pl.programowaniezespolowe.projekt.repository.AnswerRepository;

import java.util.Collections;

@SpringBootApplication
public class ProjektApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjektApplication.class, args);

    }
}
