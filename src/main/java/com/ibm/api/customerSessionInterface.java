package com.ibm.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/config")
public interface customerSessionInterface {
    
  @GET
  @Path("/countCustomers")
  @Produces("application/json")
  public Response countCustomer();

  @GET
  @Path("/activeDataService")
  @Produces("application/json")
  public Response getActiveDataServiceInfo();

  @GET
  @Path("/runtime")
  @Produces("application/json")
  public String getRuntimeInfo();
}
