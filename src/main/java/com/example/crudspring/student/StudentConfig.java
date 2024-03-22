package com.example.crudspring.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository
    ) {
        return args -> {
            Student mariam = new Student(
                    "Maria Db",
                    "mariadb.something@gmail.com",
                    LocalDate.of(2000, Month.MAY, 1)
            );
            Student alex = new Student(
                    "Alex The Lion",
                    "alex.lion@gmail.com",
                    LocalDate.of(2000, Month.MAY, 1)
            );

            repository.saveAll(List.of(mariam, alex));
        };
    }
}
