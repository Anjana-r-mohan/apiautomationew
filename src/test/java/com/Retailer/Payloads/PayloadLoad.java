package com.Retailer.Payloads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PayloadLoad {
    public static String loadPayload(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
