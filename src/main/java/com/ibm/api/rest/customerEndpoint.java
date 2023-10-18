package com.ibm.api.rest;

import com.ibm.api.interfaces.*;
import com.ibm.api.resources.*;
import java.net.URL;
import java.net.MalformedURLException;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
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
  //@Inject @ConfigProperty(name="customerHost", defaultValue="https://acmeair-customer-service:9443/customer")

  static customerInterface customerInst;
  static {
    try{
    customerInst = RestClientBuilder.newBuilder().baseUrl(new URL("https://acmeair-customer-service.acmeair.svc.cluster.local:9443/customer")).build(customerInterface.class);
    } catch(MalformedURLException e){
        throw new RuntimeException(e);
    }
  }

  static customerSessionInterface customerSessionInst;
  static {
    try{
    customerSessionInst = RestClientBuilder.newBuilder().baseUrl(new URL("https://acmeair-customer-service.acmeair.svc.cluster.local:9443/customer")).build(customerSessionInterface.class);
    } catch(MalformedURLException e){
        throw new RuntimeException(e);
    }
  }

  static customerLoaderInterface customerLoaderInst;
  static {
    try{
    customerLoaderInst = RestClientBuilder.newBuilder().baseUrl(new URL("https://acmeair-customer-service.acmeair.svc.cluster.local:9443/customer")).build(customerLoaderInterface.class);
    } catch(MalformedURLException e){
        throw new RuntimeException(e);
    }
  }
  @GET
  @Path("/byid/{custid}")
  @Produces("text/plain")
  @RolesAllowed({"user"})
  public Response getCustomer(@PathParam("custid") String customerid) {
    return customerInst.getCustomer(customerid);
  }

  @POST
  @Path("/byid/{custid}")
  @Produces("text/plain")
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

  @GET
  @Path("/countCustomers")
  @Produces("application/json")
  public Response countCustomer() {
    return customerSessionInst.countCustomer();
  }

  @GET
  @Path("/activeDataService")
  @Produces("application/json")
  public Response getActiveDataServiceInfo() {
    return customerSessionInst.getActiveDataServiceInfo();
  }

  @GET
  @Path("/runtime")
  @Produces("application/json")
  public String getRuntimeInfo() {
    return customerSessionInst.getRuntimeInfo();
  }

}
