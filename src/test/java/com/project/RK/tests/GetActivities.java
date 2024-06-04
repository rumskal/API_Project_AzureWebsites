package com.project.RK.tests;

import com.project.RK.ApiConfig;
import com.project.RK.pojos.Activity;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class GetActivities {
    private static final String BASE_URI = ApiConfig.getBaseUri();
    private static final String BASE_PATH = ApiConfig.getBasePath();
    private static final String ACTIVITY_PATH = ApiConfig.getActivityCommonBasePath();

    private static RequestSpecBuilder getRequestSpecBuilder() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .addHeaders(Map.of(
                        "Accept", "application/json"
                ));
    }
    private static ResponseSpecification getJsonResponseWithStatus(Integer status) {
        ResponseSpecification responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
        return responseSpec;
    }
    @Test
    @DisplayName("Check if the number of ID are 30")
    public void testForNumberofIds(){
        RequestSpecification requestSpec = getRequestSpecBuilder()
                .setBasePath(BASE_PATH + ACTIVITY_PATH)
                .build();

        Integer numberOfID =
                        given(requestSpec)
                        .when()
                            .log().all()
                            .get()
                        .then()
                            .spec(getJsonResponseWithStatus(200))
                            .log().all()
                            .extract()
                            .jsonPath()
                            .getList("$")
                            .size();

        MatcherAssert.assertThat(numberOfID, Matchers.is(30));

    }
}


