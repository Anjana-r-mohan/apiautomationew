package com.Retailer.Utilities;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
public class Generate_Token {
    private static final String TOKEN_FILE_PATH = "src/Logfiles/token.txt";
    private static String accessToken;

    public static String getNewAccessToken() {

        Response response = given()
                .header("Content-Type", "application/xml")
                .body("<User><username>bizomqa</username><password>mobisy</password></User>")
                .when()
                .post("https://devapi.bizomdev.in/oauth/directLogin/xml");

        response.then().statusCode(200);
        String token = response.xmlPath().getString("Response.Token");
        System.out.println("Token: " + token);

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Access token is missing or empty");
        }
        logTokenToFile(token);
        return token;
    }

    private static void logTokenToFile(String token) {
        try (FileWriter writer = new FileWriter(TOKEN_FILE_PATH)) {
            writer.write(token);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write token to file", e);
        }
    }
}

