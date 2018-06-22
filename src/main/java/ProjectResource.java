
import auth.User;
import io.dropwizard.auth.Auth;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/projects")
public class ProjectResource {

    private ProjectResourceHelper helper;

    public ProjectResource(ProjectResourceHelper helper) {
        this.helper = helper;
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProject(Project project) {
        ApiData data = this.helper.addProject(project);
        ApiResponse response = new ApiResponse();
        response.setData(data);
        return Response.ok(response).build();
    }

    @GET
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getProjects(@Auth User user) {
        ApiData data = this.helper.getProjects();
        ApiResponse response = new ApiResponse();
        response.setData(data);
        return Response.ok(response).build();
    }

    @GET
    @Path("/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjectsByKey(@QueryParam("name") String developerName) {
        ApiData data = this.helper.getProjectByDeveloper(developerName);
        ApiResponse response = new ApiResponse();
        response.setData(data);
        return Response.ok(response).build();
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchProjectByDev(@QueryParam("name") String developerName){
        ApiData data = this.helper.getProjectsByDeveloper(developerName);
        ApiResponse response = new ApiResponse();
        response.setData(data);
        return Response.ok(response).build();
    }
}
