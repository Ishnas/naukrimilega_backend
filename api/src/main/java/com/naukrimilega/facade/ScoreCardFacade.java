package com.naukrimilega.facade;

import com.google.inject.Inject;
import com.naukrimilega.models.ScoreCardDetails;
import com.naukrimilega.service.ScoreCardService;
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

@Path("/v1/mocktest/scorecard")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api("/v1/mocktest/scorecard")
public class ScoreCardFacade {

  private final ScoreCardService scoreCardService;
  @Inject
  public ScoreCardFacade(ScoreCardService scoreCardService){
    this.scoreCardService = scoreCardService;
  }

  @ApiOperation("This API will take the mocktest and save it in db")
  @POST
  public Boolean addScoreCardDetails(ScoreCardDetails scoreCardDetails) {
    return scoreCardService.addScoreCardDetails(scoreCardDetails);
  }

  @Path("/{userId}/{subject}/{paperNo}/scorecard")
  @ApiOperation("This API will take the subject and paper number and return data for that")
  @GET
  public List<ScoreCardDetails> fetchScoreCardBy(@PathParam("subject") String subject, @PathParam("paperNo") String paperNo, @PathParam("userId") String userId) {
    return scoreCardService.fetchScoreCardResponse(subject, paperNo, userId);
  }
}
