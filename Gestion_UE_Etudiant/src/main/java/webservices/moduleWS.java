package webservices;

import entities.Module;
import metiers.ModuleBusiness;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/module")
public class moduleWS {
    static ModuleBusiness helper=new ModuleBusiness();
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response
                .status(200)
                .entity(helper.getAllModules())
                .build();
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addBS(Module md){
        if (helper.addModule(md)) {
            return  Response
                    .status(201)
                    .entity("object added")
                    .build();
        } else {
            return Response.status(400).entity("object no added").build();
        }
    }

    @GET
    @Path("/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByMatricule(@PathParam("matricule") String matricule) {

        Module m = helper.getModuleByMatricule(matricule);

        if (m != null) {
            return Response.ok(m).build();
        }
        return Response.status(404).entity("Module not found").build();
    }

    @GET
    @Path("/type/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByType(@PathParam("type") String type) {

        try {
            Module.TypeModule enumType = Module.TypeModule.valueOf(type.toUpperCase());
            return Response.ok(helper.getModulesByType(enumType)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(400).entity("Invalid module type").build();
        }
    }

    @PUT
    @Path("/update/{matricule}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateModule(@PathParam("matricule") String matricule, Module updatedModule) {

        boolean ok = helper.updateModule(matricule, updatedModule);

        if (ok) {
            return Response.ok("module updated").build();
        }
        return Response.status(404).entity("Module not found").build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteModule(@PathParam("id") String matricule) {

        if (helper.deleteModule(matricule)) {
            return Response.ok("module deleted").build();
        }
        return Response.status(404).entity("Module not found").build();
    }


}
