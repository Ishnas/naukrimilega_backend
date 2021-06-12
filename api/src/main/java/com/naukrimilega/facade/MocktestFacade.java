package com.naukrimilega.facade;

import com.google.inject.Inject;
import com.naukrimilega.models.MocktestDetails;
import com.naukrimilega.service.MocktestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/mocktest")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api("/v1/mocktest")
public class MocktestFacade {

  private final MocktestService mocktestService;
  @Inject
  public MocktestFacade(MocktestService mocktestService){
    this.mocktestService = mocktestService;
  }

  @ApiOperation("This API will take the mocktest and save it in db")
  @POST
  public Boolean addMocktestDetails(MocktestDetails mocktestDetails) {
    return mocktestService.addMocktestDetails(mocktestDetails);
  }

  @Path("/{subject}/{questionpaper}")
  @ApiOperation("This API will take the mocktest type and return data for that")
  @GET
  public List<MocktestDetails> fetchMocktestBy(@PathParam("subject") String subject, @PathParam("questionpaper") String questionpaper) {
    return mocktestService.fetchMocktestResponse(subject, questionpaper);
  }
}
