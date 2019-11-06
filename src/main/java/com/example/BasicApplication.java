package com.example;

import com.example.domain.Address;
import com.example.domain.BillPayment;
import com.example.domain.CardPayment;
import com.example.domain.Category;
import com.example.domain.City;
import com.example.domain.Customer;
import com.example.domain.Order;
import com.example.domain.Product;
import com.example.domain.Region;
import com.example.domain.enums.CustomerType;
import com.example.domain.enums.PaymentStatus;
import com.example.repositories.AddressRepository;
import com.example.repositories.CategoryRepository;
import com.example.repositories.CityRepository;
import com.example.repositories.CustomerRepository;
import com.example.repositories.OrderRepository;
import com.example.repositories.PaymentRepository;
import com.example.repositories.ProductRepository;
import com.example.repositories.RegionRepository;
import java.text.SimpleDateFormat;
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
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private PaymentRepository paymentRepository;

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
        
        Customer ct1 = new Customer(null, "Maria Silva", "maria@gmail.com", "123456789987", CustomerType.INDIVIDUALENTITY);
        ct1.getPhones().addAll(Arrays.asList("27987456123", "997845632"));
        
        Address a1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", ct1, c1);
        Address a2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", ct1, c2);
        
        ct1.getAddresses().addAll(Arrays.asList(a1, a2));
        
        customerRepository.saveAll(Arrays.asList(ct1));
        addressRepository.saveAll(Arrays.asList(a1, a2));
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        Order o1 = new Order(null, sdf.parse("30/09/2017 10:32"), ct1, a1);
        Order o2 = new Order(null, sdf.parse("10/10/2017 19:35"), ct1, a2);
        
        CardPayment payment1 = new CardPayment(null, PaymentStatus.SETTLED, o1, 6);
        o1.setPayment(payment1);
        BillPayment payment2 = new BillPayment(null, PaymentStatus.PENDING, o2, sdf.parse("20/10/2017 00:00"), null);
        o2.setPayment(payment2);
        
        ct1.getOrders().addAll(Arrays.asList(o1, o2));
        
        orderRepository.saveAll(Arrays.asList(o1, o2));
        paymentRepository.saveAll(Arrays.asList(payment1, payment2));
        
    }
}
