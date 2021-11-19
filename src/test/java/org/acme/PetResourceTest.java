package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.jose4j.json.internal.json_simple.JSONObject;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class PetResourceTest {

	@Test
    public void testPetEndpoint() {
        given()
          .when().get("/v1/pets")
          .then()
             .statusCode(200);
//             .body(hasItem(
// 		            allOf(
//    		                hasEntry("pet_id", "1"),
//    		                hasEntry("pet_type", "Dog"),
//    		                hasEntry("pet_name", "Boola"),
//    		                hasEntry("pet_age", "3")
//    		            )
//    		      )
//    		 );
    }

    @Test
    void getPets() {
        given()
                .when().get("/v1/pets")
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()",notNullValue());
    }

    @Test
    void searchPet() {
        given()
                .when().get("/v1/pets/2")
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()",notNullValue());
    }

    @Test
    void addPet() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("petType", "Tiger");


        given()
                .contentType("application/json")  //another way to specify content type
                .body(requestParams.toString())   // use jsonObj toString method
                .when()
                .post("/v1/pettypes/addpettype")
                .then()
                .assertThat()
                .statusCode(201)
                .body("size()",notNullValue());
    }


}