package com.Retailer.Test;

import com.Retailer.Endpoints.EntityRule;

import com.Retailer.Utilities.ApiTestUtils;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.response.Response;
import org.apache.http.params.CoreConnectionPNames;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(com.Retailer.Utilities.TestListener.class)
public class EntityRuleTest {
    private Response response;

    @BeforeClass
    public void setup() {
        // Set connection and socket timeout to 5000ms (5 seconds)
        RestAssured.config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000)
                        .setParam(CoreConnectionPNames.SO_TIMEOUT, 5000));

        response = EntityRule.GetEntityRuleDetails();
    }

    @Test(priority = 1)
    public void GetEntityRuleDetails() {
        ApiTestUtils.assertStatusCode200(response);
    }

    @Test
    public void validateEntitySchema() {
        ApiTestUtils.validateJsonSchema(response, "schemas/EntityRule.json");

    }


    @Test
    public void validateApiTimeoutWithinFiveSeconds() {
        ApiTestUtils.assertResponseWithinTimeout(response, 5000);
    }
}
