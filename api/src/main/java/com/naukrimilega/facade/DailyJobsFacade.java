package com.naukrimilega.facade;

import com.google.inject.Inject;
import com.naukrimilega.models.DailyJobs;
import com.naukrimilega.service.DailyJobsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/jobs/quickjobs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api("/v1/jobs/quickjobs")
public class DailyJobsFacade {

  private final DailyJobsService dailyJobsService;
  @Inject
  public DailyJobsFacade(DailyJobsService dailyJobsService){
    this.dailyJobsService = dailyJobsService;
  }

  @ApiOperation("This API will take the dailyjobs and save it in db")
  @POST
  public Boolean addDailyJobsDetails(DailyJobs dailyJobs) {
    return dailyJobsService.addDailyJobsDetails(dailyJobs);
  }

  @ApiOperation("This API will fetch data")
  @GET
  public List<DailyJobs> fetchDailyJobs() {
    return dailyJobsService.fetchDailyJobsResponse();
  }
}
