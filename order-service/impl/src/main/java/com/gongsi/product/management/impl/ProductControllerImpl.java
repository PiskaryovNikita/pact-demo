package com.gongsi.product.management.impl;

import com.gongsi.product.management.api.ProductController;
import java.util.Optional;

public class ProductControllerImpl implements ProductController {

    @Override
    public String getVersion() {
        return Optional.ofNullable(getClass().getPackage())
                .map(Package::getImplementationVersion)
                .orElse("UNKNOWN");
    }
}
