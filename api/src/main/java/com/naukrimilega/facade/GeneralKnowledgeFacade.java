package com.naukrimilega.facade;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.naukrimilega.models.GkDetails;
import com.naukrimilega.models.query.QueryData;
import com.naukrimilega.service.GeneralKnowledgeService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public class GeneralKnowledgeFacade {

  private final GeneralKnowledgeService generalKnowledgeService;
  @Inject
  public GeneralKnowledgeFacade(GeneralKnowledgeService generalKnowledgeService) {
    this.generalKnowledgeService = generalKnowledgeService;
  }

  @ApiOperation("This API will add general knowledge data")
  @POST
  @Timed
  public Boolean addGkDetails(GkDetails gkDetails) {
    return generalKnowledgeService.addGkDetails(gkDetails);
  }

  @Path("/{value}")
  @ApiOperation("This API will Gk details")
  @GET
  @Timed
  public List<GkDetails> fetchGkDetails(@PathParam("value") String value) throws Exception {
    return generalKnowledgeService.fetchGkDetails(value);
  }

  @Path("/{jobType}")
  @ApiOperation("This API will take the job type and return data for that")
  @DELETE
  public Boolean deleteJobDetails(QueryData queryData) throws Exception {
    return generalKnowledgeService.deleteGkDetails(queryData);
  }
}
