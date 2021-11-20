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
    void getPetTypes() {
        given()
                .when().get("/v1/pettypes")
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()",notNullValue());
    }

    @Test
    void searchPetType() {
        given()
                .when().get("/v1/pettypes/2")
                .then()
                .assertThat()
                .statusCode(200)
                .body("size()",notNullValue());
    }

    @Test
    void addPetType() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("petTypeId",5);
        jsonAsMap.put("petTypeName", "Tiger");

        Response response = given().contentType("application/json").body(jsonAsMap).when().post("/v1/petTypes/addtype");
        Assertions.assertEquals(200, response.statusCode());
        MatcherAssert.assertThat("application/json", equalTo(response.contentType()));
        JsonPath jsonPath = response.jsonPath();
        checkEquality(jsonAsMap, jsonPath);
        System.out.println(response.jsonPath().prettyPrint());
    }

    @Test
    void updatePetType() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("petTypeId",1);
        jsonAsMap.put("petTypeName", "Dog");

        Response response = given().contentType("application/json").body(jsonAsMap).when().post("/v1/petTypes/updatetype");
        Assertions.assertEquals(200, response.statusCode());
        MatcherAssert.assertThat("application/json", equalTo(response.contentType()));
        JsonPath jsonPath = response.jsonPath();
        checkEquality(jsonAsMap, jsonPath);
        System.out.println(response.jsonPath().prettyPrint());
    }
}