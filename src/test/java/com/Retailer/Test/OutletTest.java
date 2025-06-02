package com.Retailer.Test;

import com.Retailer.Endpoints.Outlets;
import com.Retailer.Utilities.DbValidation;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.response.Response;
import org.apache.http.params.CoreConnectionPNames;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class OutletTest {
    private Response response;

    @BeforeClass
    public void setup() {
        // Set connection and socket timeout to 5000ms (5 seconds)
        RestAssured.config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 5000)
                        .setParam(CoreConnectionPNames.SO_TIMEOUT, 5000));

        response = Outlets.Getoutlets();
    }

    @Test(priority = 1)
    public void GetoutletTest() {
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("✅ GetoutletTest passed: Status code is 200");
    }

    @Test
    public void validateOutletSchema() {
        URL schemaUrl = getClass().getClassLoader().getResource("schemas/outlet_schema.json");
        Assert.assertNotNull(schemaUrl, "Schema file 'schemas/outlet_schema.json' not found in classpath");

        // Perform schema validation
        response.then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/outlet_schema.json"));
                 System.out.println("✅ Schema validation successful");

    }


    @Test
    public void validateApiTimeoutWithinFiveSeconds() {
        Assert.assertEquals(response.getStatusCode(), 200);

        long responseTime = response.getTime(); // in ms
        Assert.assertTrue(responseTime <= 5000, "Response took longer than 5 seconds");
        System.out.println("✅ API responded within 5 seconds: " + responseTime + "ms");
    }
}
