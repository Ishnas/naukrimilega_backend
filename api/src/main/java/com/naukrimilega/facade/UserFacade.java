package com.naukrimilega.facade;

import com.google.inject.Inject;
import com.naukrimilega.models.JobDetails;
import com.naukrimilega.models.UserDetails;
import com.naukrimilega.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/v1/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api("/v1/users")
public class UserFacade {
    private final UserService userService;
    @Inject
    public UserFacade(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("This API will take the job type and return data for that")
    @POST
    public Boolean addUser(UserDetails userDetails) {
        System.out.println(userDetails);
        return userService.addUser(userDetails);
    }

    @ApiOperation("This API will take the job type and return data for that")
    @GET
    public UserDetails fetchUser() {
        String email = "shawishita18@gmail.com";
        System.out.println(email);
        return userService.fetchUser(email);
    }
}
