package com.ibm.api.rest;

import com.ibm.api.interfaces.*;
import com.ibm.api.resources.*;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/flight")
public class flightEndpoint {
  //@Inject @ConfigProperty(name="flight", defaultValue="https://acmeair-flight-service:9443/flight")
  static flightInterface flight;
  static {
    try{
    flight = RestClientBuilder.newBuilder().baseUrl(new URL("https://acmeair-flight-service.acmeair.svc.cluster.local:9443/flight")).build(flightInterface.class);
    } catch(MalformedURLException e){
        throw new RuntimeException(e);
    }
  }

  static flightConfigurationInterface flightConfiguration;
  static {
    try{
    flightConfiguration = RestClientBuilder.newBuilder().baseUrl(new URL("https://acmeair-flight-service.acmeair.svc.cluster.local:9443/flight")).build(flightConfigurationInterface.class);
    } catch(MalformedURLException e){
        throw new RuntimeException(e);
    }
  }

  static flightLoaderInterface flightLoader;
  static {
    try{
    flightLoader = RestClientBuilder.newBuilder().baseUrl(new URL("https://acmeair-flight-service.acmeair.svc.cluster.local:9443/flight")).build(flightLoaderInterface.class);
    } catch(MalformedURLException e){
        throw new RuntimeException(e);
    }
  }

  @POST
  @Path("/queryflights")
  @Consumes({"application/x-www-form-urlencoded"})
  @Produces("application/json")
  public JsonObject getTripFlights(
      @FormParam("fromAirport") String fromAirport,
      @FormParam("toAirport") String toAirport,
      @FormParam("fromDate") String fromDate,
      @FormParam("returnDate") String returnDate,
      @FormParam("oneWay") boolean oneWay) {
        
    return flight.getTripFlights(fromAirport,toAirport,fromDate,returnDate,oneWay);
  }

  @POST
  @Path("/getrewardmiles")
  @Consumes({"application/x-www-form-urlencoded"})
  @Produces("application/json")
  public MilesResponse getRewardMiles(
      @FormParam("flightSegment") String segmentId){
        return flight.getRewardMiles(segmentId);
      }

  @GET
  public Response status() {
    return flight.status();
  }

  @GET
  @Path("/searches")
  public Response searches() {
    return flight.searches();
  }

  @GET
  @Path("/successes")
  public Response successes() {
    return flight.successes();
  }

  @GET
  @Path("/successratio")
  public Response successratio() {
      return flight.successratio();
    }
  
  @GET
  @Path("/audit")
  public Response audit() {
    return flight.audit();
  }

  @GET
  @Path("/query")
  @Produces("text/plain")
  public Response queryLoader() {
    return flightLoader.queryLoader();
  }

  @GET
  @Path("/load")
  @Produces("text/plain")
  public Response loadDb(@DefaultValue("5") @QueryParam("daysToLoad") int daysToLoad) {
    return flightLoader.loadDb(daysToLoad);
  }

  @GET
  @Path("/countFlights")
  @Produces("application/json")
  public Response countFlights() {
    return flightConfiguration.countFlights();
  }

  @GET
  @Path("/countFlightSegments")
  @Produces("application/json")
  public Response countFlightSegments() {
    return flightConfiguration.countFlightSegments();
  }

  @GET
  @Path("/countAirports")
  @Produces("application/json")
  public Response countAirports() {
    return flightConfiguration.countAirports();
  }

  @GET
  @Path("/activeDataService")
  @Produces("application/json")
  public Response getActiveDataServiceInfo() {
    return flightConfiguration.getActiveDataServiceInfo();
  }

  @GET
  @Path("/runtime")
  @Produces("application/json")
  public String getRuntimeInfo() {
	return flightConfiguration.getRuntimeInfo();
  }

}
