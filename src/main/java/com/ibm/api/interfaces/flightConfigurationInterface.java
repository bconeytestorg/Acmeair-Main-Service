package com.ibm.api.interfaces;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/config")
public interface flightConfigurationInterface {
    
  @GET
  @Path("/countFlights")
  @Produces("application/json")
  public Response countFlights();

  @GET
  @Path("/countFlightSegments")
  @Produces("application/json")
  public Response countFlightSegments();

  @GET
  @Path("/countAirports")
  @Produces("application/json")
  public Response countAirports();

  @GET
  @Path("/activeDataService")
  @Produces("application/json")
  public Response getActiveDataServiceInfo();

  @GET
  @Path("/runtime")
  @Produces("application/json")
  public String getRuntimeInfo();
}
