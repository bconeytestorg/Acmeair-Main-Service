package com.ibm.api.resources;

public class MilesResponse {

  private Long miles;


  public MilesResponse() {}

  public MilesResponse(Long miles) {
      this.setMiles(miles);

  }

  public Long getMiles() {
    return miles;
  }

  public void setMiles(Long miles) {
    this.miles = miles;
  }

}
