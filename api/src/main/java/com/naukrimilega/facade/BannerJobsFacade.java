package com.naukrimilega.facade;

import com.google.inject.Inject;
import com.naukrimilega.models.BannerJobs;
import com.naukrimilega.service.BannerJobsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/jobs/bannerjobs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api("/v1/jobs/bannerjobs")
public class BannerJobsFacade {

  private final BannerJobsService bannerJobsService;
  @Inject
  public BannerJobsFacade(BannerJobsService bannerJobsService){
    this.bannerJobsService = bannerJobsService;
  }

  @ApiOperation("This API will take the bannerjobs and save it in db")
  @POST
  public Boolean addBannerJobs(BannerJobs bannerJobs) {
    return bannerJobsService.addBannerJobs(bannerJobs);
  }

  @ApiOperation("This API will fetch data")
  @GET
  public List<BannerJobs> fetchBannerJobs() {
    return bannerJobsService.fetchBannerJobsResponse();
  }
}
