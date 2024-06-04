package com.project.RK.tests;

import com.project.RK.ApiConfig;
import com.project.RK.pojos.Activity;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class DeleteActivities {
    private static final String BASE_URI = ApiConfig.getBaseUri();
    private static final String BASE_PATH = ApiConfig.getBasePath();
    private static final String ACTIVITY_PATH = ApiConfig.getActivityCommonBasePath();

    private ValidatableResponse setupDELETERequest(String path, Map<String, Object> pathParameters){
        return  given()
                //Base URL of the GitHub API
                .baseUri(BASE_URI)
                //Path to the comments of a specific repo
                .basePath(BASE_PATH + ACTIVITY_PATH + path)
                //Path Parameters id
                .pathParams(pathParameters)
                .when()
                .log().all()
                .delete()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

            }
    @Test
    @DisplayName("Delete activity for a ID")
        //This test method checks if the API returns exactly one comment for the repo.
    void deleteActivityForID() {
        //The number of comments returned by the API
        setupDELETERequest("/{id}",
                Map.of("id", 100
                ))
                .extract();

    }
}
