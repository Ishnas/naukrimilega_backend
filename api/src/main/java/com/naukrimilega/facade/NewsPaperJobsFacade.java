package com.naukrimilega.facade;

import com.google.inject.Inject;
import com.naukrimilega.models.NewspaperJobs;
import com.naukrimilega.service.NewsPaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/jobs/news")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api("/v1/jobs/news")
public class NewsPaperJobsFacade {

  private final NewsPaperService newsPaperService;
  @Inject
  public NewsPaperJobsFacade(NewsPaperService newsPaperService){
    this.newsPaperService = newsPaperService;
  }

  @ApiOperation("This API will take the newspaperjobs and save it in db")
  @POST
  public Boolean addNewsPaperJobsDetails(NewspaperJobs newspaperJobs) {
    return newsPaperService.addNewsPaperJobsDetails(newspaperJobs);
  }

  @ApiOperation("This API will fetch data")
  @GET
  public List<NewspaperJobs> fetchNewsPaperJobs() {
    return newsPaperService.fetchNewsPaperJobsResponse();
  }

  @Path("/{date}")
  @ApiOperation("This API will fetch for a particular date")
  @GET
  public List<NewspaperJobs> fetchNewsPaperJobsByDate(@PathParam("date") String date) {
    return newsPaperService.fetchNewsPaperJobsByDateResponse(date);
  }
}
