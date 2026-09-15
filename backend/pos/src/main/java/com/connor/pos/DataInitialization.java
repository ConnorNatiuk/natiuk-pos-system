package com.connor.pos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.connor.pos.model.Products;
import com.connor.pos.repository.ProductsRepository;

@Component
public class DataInitialization implements CommandLineRunner {

    private final ProductsRepository productsRepository;

    public DataInitialization(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productsRepository.count() == 0) {
            Products product1 = new Products();
            product1.setSku("COF-001");
            product1.setName("coffee");
            product1.setDisplayName("House Coffee");
            product1.setPrice(new java.math.BigDecimal("2.99"));
            product1.setQuantity(50);

            Products product2 = new Products();
            product2.setSku("TEA-001");
            product2.setName("tea");
            product2.setDisplayName("Earl Grey Tea");
            product2.setPrice(new java.math.BigDecimal("3.49"));
            product2.setQuantity(35);

            Products product3 = new Products();
            product3.setSku("MUF-001");
            product3.setName("muffin");
            product3.setDisplayName("Blueberry Muffin");
            product3.setPrice(new java.math.BigDecimal("4.25"));
            product3.setQuantity(20);

            productsRepository.save(product1);
            productsRepository.save(product2);
            productsRepository.save(product3);

            System.out.println("Bootstrap data LOADED");
        }
    }

}
