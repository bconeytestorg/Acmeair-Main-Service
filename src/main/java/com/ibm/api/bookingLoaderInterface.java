package com.ibm.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/loader")
public interface bookingLoaderInterface {
   
  @GET
  @Path("/load")
  @Produces("text/plain")
  public Response loadDb();
}
