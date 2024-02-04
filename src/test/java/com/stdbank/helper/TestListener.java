package com.stdbank.helper;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import static org.testng.Assert.assertTrue;


public class TestListener extends Utils implements ITestListener {


    private static ExtentReports extent = new ExtentReports();
    private ExtentSparkReporter reporter = new ExtentSparkReporter("./reports/ExtentReportResults.html");
    protected static Logger log =  LogManager.getLogger();


    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        String screenshotsDir = "./passed_tests/";
        String screenshotsPathWordDoc = screenshotsDir + "screenshots.docx";
        String passedTest = iTestResult.getName();

        log.info("Test  " + passedTest + " has passed and a screenshot was taken.");
        reporter.viewConfigurer().viewOrder().as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST}).apply();
        extent.attachReporter(reporter);
        extent.createTest(passedTest)
                .addScreenCaptureFromPath(screenshotsDir + passedTest + ".png")
                .assignCategory(iTestResult.getMethod().getGroups())
                .log(Status.PASS, iTestResult.getThrowable())
                .info(iTestResult.getMethod().getDescription())
                .info(Arrays.toString(iTestResult.getMethod().getGroups()));

        extent.flush();

       TakesScreenshot screenshot = (TakesScreenshot) driver;
       File file = screenshot.getScreenshotAs(OutputType.FILE);

       try {
            Path outputDirectory = Path.of(screenshotsDir);
            if (!Files.exists(outputDirectory)) {
                assertTrue(new File(String.valueOf(outputDirectory)).mkdirs(), "Unable to create output directory");
            }

            XWPFDocument document;
            Path screenshotsDocumentPath = Path.of(screenshotsPathWordDoc);
            if (!Files.exists(screenshotsDocumentPath)) {
                document = new XWPFDocument();
            } else {
                document = new XWPFDocument(Files.newInputStream(Paths.get(screenshotsPathWordDoc)));
            }

            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            if (Files.exists(screenshotsDocumentPath)) {
                run.addCarriageReturn();
                run.addCarriageReturn();
            }
            run.setText("Test name: " + passedTest);
            run.addCarriageReturn();
            run.addCarriageReturn();

            File image = new File(String.valueOf(file));
            FileInputStream imageData = new FileInputStream(image);

            int imageType = XWPFDocument.PICTURE_TYPE_JPEG;
            String imageFileName = image.getName();

            int imageWidth = 500;
            int imageHeight = 250;

            FileOutputStream fos = new FileOutputStream(screenshotsPathWordDoc);

            run.addPicture(imageData, imageType, imageFileName, Units.toEMU(imageWidth), Units.toEMU(imageHeight));
            document.write(fos);

            fos.close();
            document.close();
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }

    }


    @Override
    public synchronized void onTestFailure(ITestResult iTestResult) {
        String screenshotsDir = "./failed_tests/";
        String screenshotsPathWordDoc = screenshotsDir + "screenshots.docx";
        String failedTest = iTestResult.getName();

        log.error("Test '" + failedTest + "' has failed and a screenshot was taken.");
        reporter.viewConfigurer().viewOrder().as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST}).apply();
        extent.attachReporter(reporter);
        extent.createTest(failedTest)
                .addScreenCaptureFromPath(screenshotsDir + failedTest + ".png")
                .assignCategory(iTestResult.getMethod().getGroups())
                .log(Status.FAIL, iTestResult.getThrowable())
                .info(iTestResult.getMethod().getDescription())
                .info(Arrays.toString(iTestResult.getMethod().getGroups()));
        extent.flush();


        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File file = screenshot.getScreenshotAs(OutputType.FILE);

        try {
            Path outputDirectory = Path.of(screenshotsDir);
            if (!Files.exists(outputDirectory)) {
                assertTrue(new File(String.valueOf(outputDirectory)).mkdirs(), "Unable to create output directory");
            }

            XWPFDocument document;
            Path screenshotsDocumentPath = Path.of(screenshotsPathWordDoc);
            if (!Files.exists(screenshotsDocumentPath)) {
                document = new XWPFDocument();
            } else {
                document = new XWPFDocument(Files.newInputStream(Paths.get(screenshotsPathWordDoc)));
            }

            XWPFParagraph paragraph = document.createParagraph();

            XWPFRun run = paragraph.createRun();
            if (Files.exists(screenshotsDocumentPath)) {
                run.addCarriageReturn();
                run.addCarriageReturn();
            }
            run.setText("Test name: " + failedTest);
            run.addCarriageReturn();
            run.addCarriageReturn();

            File image = new File(String.valueOf(file));
            FileInputStream imageData = new FileInputStream(image);

            int imageType = XWPFDocument.PICTURE_TYPE_JPEG;
            String imageFileName = image.getName();

            int imageWidth = 500;
            int imageHeight = 250;

            FileOutputStream fos = new FileOutputStream(screenshotsPathWordDoc);
            run.addPicture(imageData, imageType, imageFileName, Units.toEMU(imageWidth), Units.toEMU(imageHeight));
            document.write(fos);

            fos.close();
            document.close();
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {



    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
