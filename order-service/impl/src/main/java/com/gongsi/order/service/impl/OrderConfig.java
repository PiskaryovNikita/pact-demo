package com.gongsi.order.service.impl;

import com.gongsi.order.service.api.OrderController;
import com.gongsi.product.management.api.ProductController;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    @Bean
    public ProductController productService(@Value("${service.path.product.management}") String productManagementPath) {
        return JAXRSClientFactory.create(productManagementPath, ProductController.class);
    }

    @Bean
    public OrderController orderController(ProductController productController) {
        return new OrderControllerImpl(productController);
    }

}
