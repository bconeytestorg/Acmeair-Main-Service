package com.ibm.api;

import java.net.URL;
import java.net.MalformedURLException;

import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
@Path("/customer")
public class customerEndpoint {

  static customerInterface customerInst;
  static {
    try{
    customerInst = RestClientBuilder.newBuilder().baseUrl(new URL("https://app-coney-0822-aacust.staging.us-east-1.c1.appflow.dev.ibmappdomain.cloud/customer")).build(customerInterface.class);
    } catch(MalformedURLException e){
        throw new RuntimeException(e);
    }
  }

  static customerSessionInterface customerSessionInst;
  static {
    try{
    customerSessionInst = RestClientBuilder.newBuilder().baseUrl(new URL("https://app-coney-0822-aacust.staging.us-east-1.c1.appflow.dev.ibmappdomain.cloud/customer/config")).build(customerSessionInterface.class);
    } catch(MalformedURLException e){
        throw new RuntimeException(e);
    }
  }

  static customerLoaderInterface customerLoaderInst;
  static {
    try{
    customerLoaderInst = RestClientBuilder.newBuilder().baseUrl(new URL("https://app-coney-0822-aacust.staging.us-east-1.c1.appflow.dev.ibmappdomain.cloud/customer/loader")).build(customerLoaderInterface.class);
    } catch(MalformedURLException e){
        throw new RuntimeException(e);
    }
  }
  @GET
  @Path("/byid/{custid}")
  @Produces("text/plain")
  @Timed(name="com.acmeair.web.CustomerServiceRest.getCustomer", tags = "app=acmeair-customerservice-java")
  @RolesAllowed({"user"})
  public Response getCustomer(@PathParam("custid") String customerid) {
    return customerInst.getCustomer(customerid);
  }

  @POST
  @Path("/byid/{custid}")
  @Produces("text/plain")
  @Timed(name="com.acmeair.web.CustomerServiceRest.putCustomer", tags = "app=acmemair-customerservice-java")
  @RolesAllowed({"user"})
  public Response putCustomer(CustomerInfo customer, @PathParam("custid") String customerid ){
    return customerInst.putCustomer(customer, customerid);
  }

  @GET
  public Response status() {
    return customerInst.status();
  }

  @GET
  @Path("/query")
  @Produces("text/plain")
  public Response queryLoader() {
    return customerLoaderInst.queryLoader();
  }

  @GET
  @Path("/load")
  @Produces("text/plain")
  public Response loadDb(@DefaultValue("-1") @QueryParam("numCustomers") long numCustomers) {
    return customerLoaderInst.loadDb(numCustomers);
  }

}