package com.Retailer.Test;

import com.Retailer.Endpoints.skunits;
import com.Retailer.Utilities.ApiTestUtils;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.response.Response;
import org.apache.http.params.CoreConnectionPNames;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Skunits_BrandTest {
    private Response response;
    @BeforeClass
    public void setup() {

        RestAssured.config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000)
                        .setParam(CoreConnectionPNames.SO_TIMEOUT, 5000));

        response = skunits.getSkudetailsByproperties();
    }

    @Test(priority = 1)
    public void getBrandWiseWarehouseSkusTest() {
        ApiTestUtils.assertStatusCode200(response);
    }

    @Test
    public void validateOutletSchema() {
        ApiTestUtils.validateJsonSchema(response, "schemas/Brandwiseskus_schema.json");

    }

    @Test
    public void validateApiTimeoutWithinFiveSeconds() {
        ApiTestUtils.assertResponseWithinTimeout(response, 5000);
    }
}
