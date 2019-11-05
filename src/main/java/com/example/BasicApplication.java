package com.example;

import com.example.domain.Category;
import com.example.domain.City;
import com.example.domain.Product;
import com.example.domain.Region;
import com.example.repositories.CategoryRepository;
import com.example.repositories.CityRepository;
import com.example.repositories.ProductRepository;
import com.example.repositories.RegionRepository;
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
    
    @Autowired
    private RegionRepository stateRepository;
    
    @Autowired
    private CityRepository cityRepository;

    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");

        Product p1 = new Product(null, "Computador", 2000.00);
        Product p2 = new Product(null, "Impressora", 800.00);
        Product p3 = new Product(null, "Mouse", 80.00);

        p1.getCategories().addAll(Arrays.asList(cat1));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2));
        p3.getCategories().addAll(Arrays.asList(cat1));

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p2));

        Region state1 = new Region(null, "Minas Gerais");
        Region state2 = new Region(null, "São Paulo");

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        productRepository.saveAll(Arrays.asList(p1, p2, p3));

        City c1 = new City(null, "Uberlândia", state1);
        City c2 = new City(null, "São Paulo", state2);
        City c3 = new City(null, "Campinas", state2);

        state1.getCities().addAll(Arrays.asList(c1));
        state2.getCities().addAll(Arrays.asList(c2, c3));
        
        stateRepository.saveAll(Arrays.asList(state1, state2));
        cityRepository.saveAll(Arrays.asList(c1, c2, c3));

    }
}
