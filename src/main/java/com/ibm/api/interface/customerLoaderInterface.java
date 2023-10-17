package com.ibm.api;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/loader")
public interface customerLoaderInterface {
    
  @GET
  @Path("/query")
  @Produces("text/plain")
  public Response queryLoader();

  @GET
  @Path("/load")
  @Produces("text/plain")
  public Response loadDb(@DefaultValue("-1") @QueryParam("numCustomers") long numCustomers);
}
