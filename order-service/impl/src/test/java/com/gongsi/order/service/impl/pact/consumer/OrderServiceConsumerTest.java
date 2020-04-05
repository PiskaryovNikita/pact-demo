package com.gongsi.order.service.impl.pact.consumer;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslJsonRootValue;
import au.com.dius.pact.consumer.dsl.PactDslRootValue;
import au.com.dius.pact.consumer.junit.DefaultRequestValues;
import au.com.dius.pact.consumer.junit.DefaultResponseValues;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.consumer.junit.PactVerifications;
import au.com.dius.pact.consumer.dsl.PactDslRequestWithoutPath;
import au.com.dius.pact.consumer.dsl.PactDslResponse;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.matchingrules.MatchingRule;
import com.gongsi.order.service.impl.OrderServiceConfig;
import com.gongsi.order.service.impl.ProductService;
import com.gongsi.product.management.api.model.response.ProductResponse;
import org.apache.http.client.HttpResponseException;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {OrderServiceConfig.class})
@TestPropertySource(locations = "/application-test.properties")
public class OrderServiceConsumerTest {
    @Autowired
    private ProductService productService;

    @Rule
    public PactProviderRule mockTestProvider = new PactProviderRule("product-management", "localhost", 8112,
            this);

    @Pact(consumer="order-service", provider = "product-management")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        PactDslJsonBody body = new PactDslJsonBody()
                .numberType("id")
                .stringType("name")
                .numberType("price");

        return builder
                .given("get order state")
                .uponReceiving("get order request")
                .path("/api/products/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(body)
                .toPact();
    }



    @Test
    @PactVerification
    public void verifyGetProductRequest() {
        final ProductResponse product = productService.getProduct(1L);

        //nonsense assertion, since this was specified when creating pact fragment
        assertNotNull(product);
    }
}

