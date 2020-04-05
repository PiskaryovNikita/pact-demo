package com.gongsi.product.management.impl;

import com.gongsi.product.management.api.ProductController;
import com.gongsi.product.management.api.model.request.ProductRequest;
import com.gongsi.product.management.api.model.response.ProductResponse;
import java.util.Optional;

public class ProductControllerImpl implements ProductController {

    @Override
    public String getVersion() {
        return Optional.ofNullable(getClass().getPackage())
                .map(Package::getImplementationVersion)
                .orElse("UNKNOWN");
    }

    @Override
    public ProductResponse getProduct(final long id) {
        return new ProductResponse(1L, "Keyboard", 2241.6);
    }

    @Override
    public ProductResponse createProduct(final ProductRequest request) {
        // do some business logic, insert object in database
        return new ProductResponse(1L, "Keyboard", 2241.6);
    }
}
