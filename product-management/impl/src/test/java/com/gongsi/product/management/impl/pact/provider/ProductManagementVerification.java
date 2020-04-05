package com.gongsi.product.management.impl.pact.provider;

import com.gongsi.product.management.ProductManagementApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductManagementApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductManagementVerification {

    @LocalServerPort
    private int port;

    @Test
    public void testHelloRequest() throws Exception {
        System.out.println(port);
    }

}
