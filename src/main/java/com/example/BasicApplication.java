package com.example;

import com.example.domain.Category;
import com.example.repositories.CategoryRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasicApplication implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoriaRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
        Category cat1  = new Category(null, "Informática");
        Category cat2  = new Category(null, "Escritório");
        
        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        
    }
}
