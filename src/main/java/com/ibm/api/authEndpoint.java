package com.ibm.api;

import java.net.URL;
import java.net.MalformedURLException;

import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
@Path("/auth")
public class authEndpoint {
   /*  @POST
    @Consumes({ "application/x-www-form-urlencoded" })
    @Produces("text/plain")
    @Path("/login")
    public Response login(@FormParam("login") String login, @FormParam("password") String password) throws IOException, InterruptedException{
        String auth = "auth";
        HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(auth))
                .GET()
                .build();
            HttpResponse<InputStream> response = null;
            response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            int responseCode = response.statusCode();
            int expectedResponseCode = HttpURLConnection.HTTP_OK;
            if (responseCode == expectedResponseCode){
                return Response.ok(response.body()).build();
            }
            return Response.status(responseCode).build();
    }
     */
  static authInterface auth;
  static {
    try{
    auth = RestClientBuilder.newBuilder().baseUrl(new URL("https://acmeair-auth-service.acmeair.svc.cluster.local:9443/auth")).build(authInterface.class);
    } catch(MalformedURLException e){
        throw new RuntimeException(e);
    }
  }
  static authSessionInterface authSession;
  static {
    try{
    authSession = RestClientBuilder.newBuilder().baseUrl(new URL("https://acmeair-auth-service.acmeair.svc.cluster.local:9443/auth")).build(authSessionInterface.class);
    } catch(MalformedURLException e){
        throw new RuntimeException(e);
    }
  }
  @POST
  @Consumes({ "application/x-www-form-urlencoded" })
  @Produces("text/plain")
  @Path("/login")
  public Response login(@FormParam("login") String login, @FormParam("password") String password){
    return auth.login(login, password);
  }
  @GET
  @Produces("application/json")
  @Path("/getJwk")
  public Response getJwk(){
    return auth.getJwk();
  }

  @GET
  public Response status() {
    return auth.status();
  }

  @GET
  @Path("/runtime")
  @Produces("application/json")
  public String getRuntimeInfo(){
    return authSession.getRuntimeInfo();
  }
}
