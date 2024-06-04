package com.project.RK.tests;

import com.project.RK.ApiConfig;
import com.project.RK.pojos.Activity;
import com.project.RK.records.UpdatedActivity;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PutActivities {
    private static final String BASE_URI = ApiConfig.getBaseUri();
    private static final String BASE_PATH = ApiConfig.getBasePath();
    private static final String ACTIVITY_PATH = ApiConfig.getActivityCommonBasePath();

    private ValidatableResponse setupPUTRequest(String path, Map<String, Object> pathParameters, UpdatedActivity activity){
        return  given()
                    //Base URL
                    .baseUri(BASE_URI)
                    //Path to the comments of a specific repo
                    .basePath(BASE_PATH + ACTIVITY_PATH + path)
                    //Path Parameters id
                    .pathParams(pathParameters)
                    .body(activity)
                    .contentType("application/json")
                .when()
                    .log().all()
                    .put()
                .then()
                    .log().all()
                    .assertThat()
                    .statusCode(200)
                    .assertThat()
                    .contentType(ContentType.JSON);
    }

    @Test
    @DisplayName("Checking PUT activity  for a given ID and update title in the body")
    void checkPutActivity(){
        UpdatedActivity activitybody = new UpdatedActivity(1000,"changed name", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),true);

        Activity activity = setupPUTRequest("/{id}",
                            Map.of("id", 1000),
                             activitybody)
                            .extract()
                            .as(Activity.class);

        MatcherAssert.assertThat(activity.getTitle(), Matchers.is(activitybody.title()));

    }
}
