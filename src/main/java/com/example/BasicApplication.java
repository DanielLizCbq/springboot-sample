package com.example;

import com.example.domain.Category;
import com.example.domain.Product;
import com.example.repositories.CategoryRepository;
import com.example.repositories.ProductRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasicApplication implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoriaRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
        Category cat1  = new Category(null, "Informática");
        Category cat2  = new Category(null, "Escritório");
        
        
        Product p1 = new Product(null, "Computador", 2000.00);
        Product p2 = new Product(null, "Impressora", 800.00);
        Product p3 = new Product(null, "Mouse", 80.00);
        
        p1.getCategories().addAll(Arrays.asList(cat1));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2));
        p3.getCategories().addAll(Arrays.asList(cat1));
        
        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p2));
        
        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        productRepository.saveAll(Arrays.asList(p1, p2, p3));
        
    }
}
