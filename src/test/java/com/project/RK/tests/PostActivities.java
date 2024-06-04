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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostActivities {
    private static final String BASE_URI = ApiConfig.getBaseUri();
    private static final String BASE_PATH = ApiConfig.getBasePath();
    private static final String ACTIVITY_PATH = ApiConfig.getActivityCommonBasePath();

    private ValidatableResponse setupPOSTRequest(UpdatedActivity activity){
        return  given()
                    //Base URL
                    .baseUri(BASE_URI)
                    //Path to the comments of a specific repo
                    .basePath(BASE_PATH + ACTIVITY_PATH )
                .body(activity)
                .contentType("application/json")
                .when()
                    //.log().all()
                    .post()
                .then()
                    //.log().all()
                    .assertThat()
                    .statusCode(200)
                    .assertThat()
                    .contentType(ContentType.JSON);
    }

    @Test
    @DisplayName("Checking POST activity with body")
        void checkPostActivity() {
        UpdatedActivity activitybody = new UpdatedActivity(100,"rope",LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),true);
        //The number of comments returned by the API
        Activity activity = setupPOSTRequest(activitybody)
                .extract()
                .as(Activity.class);

        //Assert that ID is 19
        MatcherAssert.assertThat(activity.getId(), Matchers.is(activitybody.id()));
        //Assert that name is Activity 19
        MatcherAssert.assertThat(activity.getTitle(), Matchers.is(activitybody.title()));
        //Assert that Date is 18th April 2024
        LocalDateTime dueDateFormatted = LocalDateTime.parse(activitybody.dueDate().substring(0,16));
        MatcherAssert.assertThat(activity.getDueDateFormatted().toLocalDate(), Matchers.is(dueDateFormatted.toLocalDate()));
        //Assert that Completed = false
        MatcherAssert.assertThat(activity.getCompleted(),Matchers.is(activitybody.completed()));
    }
    }
