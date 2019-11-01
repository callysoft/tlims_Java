package com.tlimskech.marketplace.initializer;

import com.tlimskech.marketplace.global.category.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryService;

    public DataInitializer(CategoryRepository categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Done================");
    }
}
