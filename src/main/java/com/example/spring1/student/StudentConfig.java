package com.example.spring1.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
        Student chento=new Student(
                "Chento",
                "chento@gmail.coom",
                LocalDate.of(2002, Month.JANUARY,18)
            );
            Student heng=new Student(
                    "heng",
                    "heng@gmail.coom",
                    LocalDate.of(2004, Month.JANUARY,18)
            );
            repository.saveAll(
                    List.of(chento,heng)
            );
        };
    }

}
