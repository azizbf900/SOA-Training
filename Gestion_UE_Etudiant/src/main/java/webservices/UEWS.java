package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ue")
public class UEWS {
    static UniteEnseignementBusiness helper=new UniteEnseignementBusiness();
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response
                .status(200)
                .entity(helper.getListeUE())
                .build();
    }

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUE(UniteEnseignement ue){
        if (helper.addUniteEnseignement(ue)) {
            return  Response
                    .status(201)
                    .entity("object added")
                    .build();
        } else {
            return Response.status(400).entity("object no added").build();
        }
    }

    // GET BY CODE
    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCode(@PathParam("code") int code) {
        UniteEnseignement ue = helper.getUEByCode(code);

        if (ue != null) {
            return Response.ok(ue).build();
        }
        return Response.status(404).entity("UE not found").build();
    }

    // GET BY DOMAINE
    @GET
    @Path("/domaine/{domaine}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByDomaine(@PathParam("domaine") String domaine) {

        return Response.ok(helper.getUEByDomaine(domaine)).build();
    }

    // GET BY SEMESTRE
    @GET
    @Path("/semestre/{sem}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBySemestre(@PathParam("sem") int sem) {

        return Response.ok(helper.getUEBySemestre(sem)).build();
    }

    // UPDATE UE
    @PUT
    @Path("/update/{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUE(
            @PathParam("code") int code,
            UniteEnseignement updatedUE) {

        boolean ok = helper.updateUniteEnseignement(code, updatedUE);

        if (ok) {
            return Response.ok("UE updated").build();
        }

        return Response.status(404).entity("UE not found").build();
    }

    // DELETE UE
    @DELETE
    @Path("/delete/{code}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUE(@PathParam("code") int code) {

        if (helper.deleteUniteEnseignement(code)) {
            return Response.ok("UE deleted").build();
        }
        return Response.status(404).entity("UE not found").build();
    }
}
