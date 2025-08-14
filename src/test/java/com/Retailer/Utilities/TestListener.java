package com.Retailer.Utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TestListener implements ITestListener {
    private static  int passedCount = 0;
    private static  int failedCount = 0;
    private static int skippedCount = 0;

    @Override
    public void onTestSuccess(ITestResult result) {
        passedCount++;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        failedCount++;
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        skippedCount++;
    }

    @Override
    public void onFinish(ITestContext context)  {
        String report = "Test Execution Summary\n" +
                "----------------------\n" +
                "Passed: " + passedCount + "\n" +
                "Failed: " + failedCount + "\n" +
                "Skipped: " + skippedCount + "\n";

        System.out.println(report);

        // Save the summary to a file for Jenkins to archive or email
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("com/Retailer/Testoutput/TestReport.txt"))) {
            writer.write(report);
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
