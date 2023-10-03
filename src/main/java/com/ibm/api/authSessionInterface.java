package com.ibm.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/config")
public interface authSessionInterface {
  @GET
  @Path("/runtime")
  @Produces("application/json")
  public String getRuntimeInfo();
}
