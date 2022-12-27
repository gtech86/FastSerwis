package pl.grabowski.fastserwis.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
public class AppConfiguration {

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
