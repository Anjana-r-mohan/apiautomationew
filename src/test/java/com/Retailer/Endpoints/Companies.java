package com.Retailer.Endpoints;

import com.Retailer.Payloads.PayloadLoad;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.params.CoreConnectionPNames;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Companies {
    public static Response Getinfo() {

        //prerequisite
        Response response = given()
                //request
                .when().log().all()
                .get(Routes.getInfo());
        return response;
    }public static Response GetinfoTimeout() {
        Response response = given()
                .config(RestAssured.config().httpClient(HttpClientConfig.httpClientConfig()
                        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000)
                        .setParam(CoreConnectionPNames.SO_TIMEOUT, 5000)))
                .when()
                .get(Routes.getInfo());

        return response;
    }

    public static Response GetCompanyKycForm() throws IOException {
        String filePath = "src/test/resources/payloads/Kyc_form.json";

        // Simple load
        String payload = PayloadLoad.loadPayload(filePath);
        //prerequisite
        Response response = given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .get(Routes.getCompanyKyc() );
        return response;
    }public static Response GetCompanyKycFormTimeout() {

        Response response = given()
                .config(RestAssured.config().httpClient(HttpClientConfig.httpClientConfig()
                        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000)
                        .setParam(CoreConnectionPNames.SO_TIMEOUT, 5000)))
                .when()
                .get(Routes.getCompanyKyc() );

        return response;
    }
}
