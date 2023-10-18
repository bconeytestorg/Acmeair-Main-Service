package com.ibm.api.interfaces;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/loader")
public interface flightLoaderInterface {
    
  @GET
  @Path("/query")
  @Produces("text/plain")
  public Response queryLoader();

  @GET
  @Path("/load")
  @Produces("text/plain")
  public Response loadDb(@DefaultValue("5") @QueryParam("daysToLoad") int daysToLoad);
}
