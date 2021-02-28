package com.naukrimilega.facade;

import com.google.inject.Inject;
import com.naukrimilega.models.JobDetails;
import com.naukrimilega.models.query.JobByType;
import com.naukrimilega.models.query.QueryData;
import com.naukrimilega.service.jobs.JobsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/jobs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api("/v1/jobs")
public class JobsFacade {
    private final JobsService jobsService;
    @Inject
    public JobsFacade(JobsService jobsService) {
        this.jobsService = jobsService;
    }


    @ApiOperation("This API will take the job type and return data for that")
    @POST
    public Boolean addJobDetails(JobDetails jobDetails) {
        return jobsService.addJobDetails(jobDetails);
    }

    @Path("/{jobType}/{value}")
    @ApiOperation("This API will take the job type and return data for that")
    @GET
    public List<JobDetails> fetchJobsBy(@PathParam("jobType") JobByType jobByType, @PathParam("value") String value) throws Exception {
        return jobsService.fetchJobsResponse(jobByType, value);
    }

    @Path("/{jobType}")
    @ApiOperation("This API will take the job type and return data for that")
    @DELETE
    public Boolean deleteJobDetails(QueryData queryData) throws Exception {
        return jobsService.deleteJobs(queryData);
    }
}
