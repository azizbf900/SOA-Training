package webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response; //JAX-RS

@Path("/hi")
public class helloWS {
    //ws:1 retourner msg hello
    @Path("/hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayhello(){
        return Response
                .status(200)
                .entity("Hello")
                .build();
    }
}
