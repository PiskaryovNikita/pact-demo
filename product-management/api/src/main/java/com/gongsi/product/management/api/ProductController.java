package com.gongsi.product.management.api;

import com.gongsi.product.management.api.model.request.ProductRequest;
import com.gongsi.product.management.api.model.response.ProductResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Api
@Produces(MediaType.APPLICATION_JSON)
public interface ProductController {

    @GET
    @Path("version")
    @ApiOperation("get project version")
    String getVersion();

    @GET
    @Path("products/{id}")
    @ApiOperation("get product")
    ProductResponse getProduct(@PathParam("id") long id);

    @POST
    @Path("products")
    @ApiOperation("create product")
    ProductResponse createProduct(ProductRequest request);

}
