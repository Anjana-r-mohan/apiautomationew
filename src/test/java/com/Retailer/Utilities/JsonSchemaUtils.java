package com.Retailer.Utilities;

import io.restassured.response.ValidatableResponse;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchemaUtils {
    public static void validateSchema(ValidatableResponse response, String schemaPath) {
        response.body(matchesJsonSchemaInClasspath(schemaPath));
    }
}
