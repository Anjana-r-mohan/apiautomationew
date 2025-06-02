package com.Retailer.Endpoints;

import com.Retailer.Utilities.Generate_Token;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.response.Response;
import org.apache.http.params.CoreConnectionPNames;

import static io.restassured.RestAssured.given;

public class Outlets {

    public static Response Getoutlets() {

        //prerequisite
        Response response = given()
                //request
                .when().log().all()
                .get(Routes.GET_OUTLET_AND_CHILD_OUTLET);
        return response;
    }public static Response GetOutletByIdWithTimeout() {
        Response response = given()
                .config(RestAssured.config().httpClient(HttpClientConfig.httpClientConfig()
                        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000)
                        .setParam(CoreConnectionPNames.SO_TIMEOUT, 5000)))
                .when()
                .get(Routes.GET_OUTLET_AND_CHILD_OUTLET);

        return response;
    }

}
