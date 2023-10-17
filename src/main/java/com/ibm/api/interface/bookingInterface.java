package com.ibm.api;

import org.eclipse.microprofile.metrics.annotation.Timed;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

public interface bookingInterface {
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
      @HeaderParam("Authorization") String token);

  @GET
  @Path("/byuser/{user}")
  @Produces("text/plain")
  public Response getBookingsByUser(@PathParam("user") String userid, @HeaderParam("Authorization") String token);

  @POST
  @Consumes({ "application/x-www-form-urlencoded" })
  @Path("/cancelbooking")
  @Produces("text/plain")
  @RolesAllowed({"user"})
  public Response cancelBookingsByNumber(@FormParam("number") String number, 
      @FormParam("userid") String userid,
      @HeaderParam("Authorization") String token);

  @GET
  public Response status();
  
  @GET
  @Path("/rewards/customerfailures")
  public Response customerFailures();

  @GET
  @Path("/rewards/flightfailures")
  public Response flightFailures();

  @GET
  @Path("/rewards/customersuccesses")
  public Response customerSucceses();

  @GET
  @Path("/rewards/flightsuccesses")
  public Response flightSuccesseses();

  @GET
  @Path("/audit")
  public Response audit();
}
