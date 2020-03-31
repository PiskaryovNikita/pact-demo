package com.gongsi.product.management.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
@Api("/")
public interface ProductController {

    @Path("version")
    @ApiOperation("project version")
    @GET
    String getVersion();

}
