package org.acme;

import org.junit.jupiter.api.Test;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

class PetTypeResourceTest {

    public void checkEquality(Map<String, Object> expected, JsonPath actual){
        for(String path : expected.keySet()){
            Assertions.assertEquals(expected.get(path), actual.get(path));
        }
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
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("petId",5);
        jsonAsMap.put("petName","Pie");
        jsonAsMap.put("petAge",8);
        jsonAsMap.put("petName", "Tiger");

        Response response = given().contentType("application/json").body(jsonAsMap).when().post("/v1/pets/addpet");
        Assertions.assertEquals(200, response.statusCode());
        MatcherAssert.assertThat("application/json", equalTo(response.contentType()));
        JsonPath jsonPath = response.jsonPath();
        checkEquality(jsonAsMap, jsonPath);
        System.out.println(response.jsonPath().prettyPrint());
    }

    @Test
    void updatePet() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("petId",1);
        jsonAsMap.put("petName","Boola");
        jsonAsMap.put("petAge",10);
        jsonAsMap.put("petType", "Dog");

        Response response = given().contentType("application/json").body(jsonAsMap).when().post("/v1/pets/update");
        Assertions.assertEquals(200, response.statusCode());
        MatcherAssert.assertThat("application/json", equalTo(response.contentType()));
        JsonPath jsonPath = response.jsonPath();
        checkEquality(jsonAsMap, jsonPath);
        System.out.println(response.jsonPath().prettyPrint());
    }
}