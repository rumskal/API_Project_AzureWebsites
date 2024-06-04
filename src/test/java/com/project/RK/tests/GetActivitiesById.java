package com.project.RK.tests;

import com.project.RK.ApiConfig;
import com.project.RK.pojos.Activity;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetActivitiesById {
    private static final String BASE_URI = ApiConfig.getBaseUri();
    private static final String BASE_PATH = ApiConfig.getBasePath();
    private static final String ACTIVITY_PATH = ApiConfig.getActivityCommonBasePath();

    private ValidatableResponse setupGETRequest(String path, Map<String, Object> pathParameters){
        return  given()
                //Base URL of the GitHub API
                    .baseUri(BASE_URI)
                    //Path to the comments of a specific repo
                    .basePath(BASE_PATH + ACTIVITY_PATH + path)
                    //Path Parameters id
                    .pathParams(pathParameters)
                .when()
                    //.log().all()
                    .get()
                .then()
                    //.log().all()
                    .assertThat()
                    .statusCode(200)
                    .assertThat()
                    .contentType(ContentType.JSON);
    }
    @Test
    @DisplayName("Get activity for the ID")
        //This test method checks if the API returns exactly one comment for the repo.
    void getActivityForID() {
        //The number of comments returned by the API
        Activity activity = setupGETRequest("/{id}",
                Map.of("id", 19
                ))
                .extract()
                .as(Activity.class);

        //Assert that ID is 19
        MatcherAssert.assertThat(activity.getId(), Matchers.is(19));
        //Aseert that name is Activity 19
        MatcherAssert.assertThat(activity.getTitle(), Matchers.is("Activity 19"));
        //Assert that Date is 18th April 2024
        LocalDate expectedDate = LocalDate.now().plusDays(1);
        MatcherAssert.assertThat(activity.getDueDateFormatted().toLocalDate(), Matchers.is(expectedDate));
        //Assert that Completed = false
        MatcherAssert.assertThat(activity.getCompleted(),Matchers.is(false));
    }
}


