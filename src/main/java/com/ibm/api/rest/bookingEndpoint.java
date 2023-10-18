package com.ibm.api.rest;

import com.ibm.api.interfaces.*;
import com.ibm.api.resources.*;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/booking")
public class bookingEndpoint {
  //@Inject @ConfigProperty(name="bookingHost", defaultValue="https://acmeair-booking-service:9443/booking")  
  //static String bookingHost;
  static bookingInterface booking;
  static {
    try{
    booking = RestClientBuilder.newBuilder().baseUrl(new URL("https://acmeair-booking-service:9443/booking")).build(bookingInterface.class);
    } catch(MalformedURLException e){
        throw new RuntimeException(e);
    }
  }

  static bookingConfigurationInterface bookingConfiguration;
  static {
    try{
    bookingConfiguration = RestClientBuilder.newBuilder().baseUrl(new URL("https://acmeair-booking-service.acmeair.svc.cluster.local:9443/booking")).build(bookingConfigurationInterface.class);
    } catch(MalformedURLException e){
        throw new RuntimeException(e);
    }
  }

  static bookingLoaderInterface bookingLoader;
  static {
    try{
    bookingLoader = RestClientBuilder.newBuilder().baseUrl(new URL("https://acmeair-booking-service.acmeair.svc.cluster.local:9443/booking")).build(bookingLoaderInterface.class);
    } catch(MalformedURLException e){
        throw new RuntimeException(e);
    }
  }


  @POST
  @Consumes({ "application/x-www-form-urlencoded" })
  @Path("/bookflights")
  @Produces("text/plain")
  public Response bookFlights(@FormParam("userid") String userid,
      @FormParam("toFlightId") String toFlightId, 
      @FormParam("toFlightSegId") String toFlightSegId,
      @FormParam("retFlightId") String retFlightId, 
      @FormParam("retFlightSegId") String retFlightSegId,
      @FormParam("oneWayFlight") boolean oneWay,
      @HeaderParam("Authorization") String token) {
        return booking.bookFlights(userid, toFlightId, toFlightSegId, retFlightId, retFlightSegId, oneWay, token);
  }
  @GET
  @Path("/byuser/{user}")
  @Produces("text/plain")
  public Response getBookingsByUser(@PathParam("user") String userid, @HeaderParam("Authorization") String token) {
    return booking.getBookingsByUser(userid, token);
  }

  @POST
  @Consumes({ "application/x-www-form-urlencoded" })
  @Path("/cancelbooking")
  @Produces("text/plain")
  public Response cancelBookingsByNumber(@FormParam("number") String number, 
      @FormParam("userid") String userid,
      @HeaderParam("Authorization") String token) {
        return booking.cancelBookingsByNumber(number, userid, token);
  }

  @GET
  public Response status() {
    return booking.status();
  }

  @GET
  @Path("/rewards/customerfailures")
  public Response customerFailures() {
    return booking.customerFailures();
  }

  @GET
  @Path("/rewards/flightfailures")
  public Response flightFailures() {
    return booking.flightFailures();
  }

  @GET
  @Path("/rewards/customersuccesses")
  public Response customerSucceses() {
    return booking.customerSucceses();
  }

  @GET
  @Path("/rewards/flightsuccesses")
  public Response flightSuccesseses() {
    return booking.flightSuccesseses();
  }

  @GET
  @Path("/audit")
  public Response audit() {
    return booking.audit();
  }

  @GET
  @Path("/countBookings")
  @Produces("application/json")
  public Response countBookings() {
    return bookingConfiguration.countBookings();
  }

  @GET
  @Path("/activeDataService")
  @Produces("application/json")
  public Response getActiveDataServiceInfo() {
    return bookingConfiguration.getActiveDataServiceInfo();
  }

  @GET
  @Path("/runtime")
  @Produces("application/json")
  public String getRuntimeInfo() {
    return bookingConfiguration.getRuntimeInfo();
  }

  @GET
  @Path("/load")
  @Produces("text/plain")
  public Response loadDb() {
    return bookingLoader.loadDb();
  }
}
