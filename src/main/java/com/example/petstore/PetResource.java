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

@Path("/v1/pets")
@Produces("application/json")
public class PetResource {

	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	public Response getPets() {
		return Response.ok(DB.getPetTable()).build();
	}

	//search a pet by pet ID =========================================================
	@Path("{petId}")
	@GET
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	public Response searchPet(@PathParam("petId") int id) {
		Pet pet = null;
		for (Pet pet1 : DB.getPetTable()) {
			if (pet1.getPetId() == id ){
				pet=pet1;
			}
		}
		return Response.ok(pet).build();
	}

	//Add new pet =========================================================
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet added", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "Pet adding failed.") })
	@POST
	@Path("addpet")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPet(@RequestBody Pet pet) {

		Pet savedPet= DB.savePet(pet);

		return Response.ok(savedPet).build();
	}

	//update a pet =========================================================
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet updated", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "Pet updating failed.") })
	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePet(@RequestBody Pet pet) {
		Pet updatedPet = null;
		for (Pet pet1 : DB.getPetTable()) {
			if (pet1.getPetId() == pet.getPetId()){
				updatedPet= DB.updatePet(pet1, pet);
			}
		}
		return Response.ok(updatedPet).build();
	}

	//delete a pet =========================================================
	@DELETE
	@Path("delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void deletePet(@RequestBody Pet pet) {
		Pet deletedPet = null;
		for (Pet pet1 : DB.getPetTable()) {
			if (pet1.getPetId() == pet.getPetId()){
				DB.deletePet(pet1);
			}
		}
	}
}
