package ar.edu.itba.paw.webapp.controller;

import ar.edu.itba.paw.interfaces.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


@Path("users")
@Component
public class UserController {
    @Autowired
    private UserService us;
//    @Context
//    private UriInfo uriInfo;

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response listUsers() {
        return Response.ok(us.getById(1).getEmail()).build();
    }

    /*@POST
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response createUser(final UserDTO userDto) {
        final User user = us.register(userDto.getUsername(), userDto.getPassword());
        final URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(user.getId())).build();
        return Response.created(uri).build();
    }

    @GET
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response getById(@PathParam("id") final long id) {
        final User user = us.getById(id);
        if (user != null) {
            return Response.ok(new UserDTO(user)).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON,})
    public Response deleteById(@PathParam("id") final long id) {
        us.deleteById(id);
        return Response.noContent().build();
    }*/
}