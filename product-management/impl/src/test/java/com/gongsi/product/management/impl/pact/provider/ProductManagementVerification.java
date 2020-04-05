package com.gongsi.product.management.impl.pact.provider;

import au.com.dius.pact.provider.junit.Consumer;
import au.com.dius.pact.provider.junit.IgnoreNoPactsToVerify;
import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import com.gongsi.product.management.api.ProductController;
import com.gongsi.product.management.impl.ProductControllerImpl;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

@IgnoreNoPactsToVerify
@RunWith(PactRunner.class)
@Provider("product-management")
@Consumer("order-service")
@PactBroker(host = "${pactbroker.host:localhost}", port = "${pactbroker.port:80}", tags = {"${pactbroker.tags:master}"})
public class ProductManagementVerification {

    @TestTarget
    public final Target target = new HttpTarget(JaxRsServer.PORT);

    // here can be some @Mock to stub downstream dependencies
    @InjectMocks
    private ProductController productController = new ProductControllerImpl();

    @BeforeClass
    public static void init() {
        JaxRsServer.startServer();
    }

    @AfterClass
    public static void cleanup() {
        JaxRsServer.stopServer();
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        JaxRsServer.deploy("/api", productController);
    }

    @State("get order state")
    public void setupBeforeGetOrder() {

    }

    @State("create order state")
    public void setupBeforeCreateOrder() {

    }

}
