package com.example.petstore;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.petstore.db.DB;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/pettypes")
@Produces("application/json")
public class PetTypeResource {

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "All Pet Types", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))) })
    @GET
    public Response getPetTypes() {
        return Response.ok(DB.getTypeTable()).build();
    }

    //search a pet Type by Type ID =========================================================
    @Path("{petTypeId}")
    @GET
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "PetType for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
            @APIResponse(responseCode = "404", description = "No PetType found for the id.") })
    public Response searchPetType(@PathParam("petTypeId") int id) {
        PetType type = null;
        for (PetType temp : DB.getTypeTable()) {
            if (temp.getPetTypeId() == id ){
                type=temp;
            }
        }
        return Response.ok(type).build();
    }

    //Add new pet Type=========================================================
    @Path("addtype")
    @POST
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet Type added", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
            @APIResponse(responseCode = "404", description = "Pet Type adding failed.") })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPetType(@RequestBody PetType type) {
        PetType savedType = DB.savePetType(type);
        return Response.ok(savedType).build();
    }

    //update a pet Type =========================================================
    @Path("updatetype")
    @PUT
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet Type updated", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
            @APIResponse(responseCode = "404", description = "Pet Type updating failed.") })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePetType(@RequestBody PetType type) {
        PetType updatedPetType = null;
        for (PetType temp : DB.getTypeTable()) {
            if (temp.getPetTypeId() == type.getPetTypeId()){
                updatedPetType = DB.updateType(temp, type);
            }
        }
        return Response.ok(updatedPetType).build();
    }

    //delete a pet Type=========================================================
    @Path("deletetype")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deletePet(@RequestBody PetType type) {
        for (PetType temp : DB.getTypeTable()) {
            if (temp.getPetTypeId() == type.getPetTypeId()){
                DB.deleteType(temp);
            }
        }
    }

}
