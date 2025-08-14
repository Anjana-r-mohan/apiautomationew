package com.Retailer.Endpoints;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.response.Response;
import org.apache.http.params.CoreConnectionPNames;

import static io.restassured.RestAssured.given;

public class EntityRule {
    public static Response GetEntityRuleDetails() {

        //prerequisite
        Response response = given()
                //request
                .when().log().all()
                .get(Routes.getEntityRuleDetails("1"));
        return response;
    }public static Response GetOutletByIdWithTimeout() {
        Response response = given()
                .config(RestAssured.config().httpClient(HttpClientConfig.httpClientConfig()
                        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000)
                        .setParam(CoreConnectionPNames.SO_TIMEOUT, 5000)))
                .when()
                .get(Routes.getEntityRuleDetails("1"));

        return response;
    }

}
