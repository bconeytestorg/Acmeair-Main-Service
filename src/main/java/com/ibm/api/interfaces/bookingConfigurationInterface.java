package com.ibm.api.interfaces;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/config")
public interface bookingConfigurationInterface {
    
  @GET
  @Path("/countBookings")
  @Produces("application/json")
  public Response countBookings();

  @GET
  @Path("/activeDataService")
  @Produces("application/json")
  public Response getActiveDataServiceInfo();

  @GET
  @Path("/runtime")
  @Produces("application/json")
  public String getRuntimeInfo();
}
