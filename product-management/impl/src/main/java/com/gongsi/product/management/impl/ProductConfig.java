package com.gongsi.product.management.impl;

import com.gongsi.product.management.api.ProductController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    public ProductController productController() {
        return new ProductControllerImpl();
    }

}
