package com.gongsi.order.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gongsi.order.service.api.OrderController;
import com.gongsi.order.service.api.model.response.OrderResponse;
import com.gongsi.order.service.api.model.response.User;
import com.gongsi.product.management.api.ProductController;
import com.gongsi.product.management.api.model.response.ProductResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.OffsetDateTime;
import java.util.Optional;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {
    private final ProductController productService;
    @Value("${service.path.product.management}")
    private String productManagementPath;

    @Override
    public String getVersion() {
        return Optional.ofNullable(getClass().getPackage())
                .map(Package::getImplementationVersion)
                .orElse("UNKNOWN");
    }

    @Override
    public OrderResponse getOrder(long id) {
        User user = new User(1L, "Name", "e@e.com");

        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpGet request = new HttpGet(String.format("%s/api/products/1", productManagementPath));
            request.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
            String entity;
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                entity = EntityUtils.toString(response.getEntity());
            }
            ObjectMapper objectMapper = new ObjectMapper();
            ProductResponse productResponse = objectMapper.readValue(entity, ProductResponse.class);

            return new OrderResponse(user, OffsetDateTime.now(), productResponse);
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
    }
}
