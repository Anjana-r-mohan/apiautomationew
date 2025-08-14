package com.Retailer.Utilities;

import io.restassured.response.Response;
import org.testng.Assert;

import java.net.URL;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTestUtils {
    public static void assertStatusCode200(Response response) {
        try {
            response.then().log().all();
            Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");
            System.out.println(" Status code is 200");
        } catch (AssertionError e) {
            System.err.println(" Failed: Status code validation failed - " + e.getMessage());
            throw e;
        }
    }

    public static void assertResponseWithinTimeout(Response response, long maxTimeMs) {try {
        long responseTime = response.getTime();
        Assert.assertTrue(responseTime <= maxTimeMs, "API took longer than " + maxTimeMs + "ms");
        System.out.println("API responded within " + maxTimeMs + "ms: " + responseTime + "ms");
    } catch (AssertionError e) {
        System.err.println(" Failed: API timeout exceeded - " + e.getMessage());
        throw e;
    }
    }

    public static void validateJsonSchema(Response response, String schemaPath) {
        try {
            URL schemaUrl = ApiTestUtils.class.getClassLoader().getResource(schemaPath);
            Assert.assertNotNull(schemaUrl, "Schema file '" + schemaPath + "' not found in classpath");

            response.then().statusCode(200)
                    .body(matchesJsonSchemaInClasspath(schemaPath));
            System.out.println("JSON schema validation successful");
        } catch (AssertionError e) {
            System.err.println(" Failed: Schema validation failed - " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println(" Failed: Error loading schema - " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
