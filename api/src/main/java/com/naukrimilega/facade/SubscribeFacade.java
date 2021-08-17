package com.naukrimilega.facade;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.naukrimilega.service.SubscribeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/subscribe")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api("/v1/subscribe")
public class SubscribeFacade {

  private final SubscribeService subscribeService;
  @Inject
  public SubscribeFacade(SubscribeService subscribeService) {
    this.subscribeService = subscribeService;
  }


  @ApiOperation("This API will subscribe numbers")
  @POST
  @Timed
  @Path("/{mobileno}")
  public Boolean addSubscriptionDetails(@PathParam("mobileno") String mobileno) {
    return subscribeService.addSubscriptionDetails(mobileno);
  }

//  @Path("/{number}")
//  @ApiOperation("This API will take subscribed numbers")
//  @GET
//  @Timed
//  public List<JobDetails> fetchSubscriptionBy(@PathParam("number") String number) throws Exception {
//    return subscribeService.fetchSubscriptionResponse(number);
//  }
//
//  @Path("/{number}")
//  @ApiOperation("This API will delete subscribe numbers")
//  @DELETE
//  public Boolean deleteSubscribeDetails(QueryData queryData) throws Exception {
//    return subscribeService.deleteSubscription(queryData);
//  }
}
