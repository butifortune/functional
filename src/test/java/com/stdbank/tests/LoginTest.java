package com.stdbank.tests;

import com.stdbank.helper.LoginDataSet;

import com.stdbank.helper.TestListener;
import com.stdbank.pages.LoginPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.testng.log4testng.Logger;

import java.time.Duration;

@Listeners(TestListener.class)
public class LoginTest extends LoginDataSet {

    public LoginPage loginPage;
    private SoftAssert softAssert;
    private static Logger log = Logger.getLogger(LoginTest.class);

    @BeforeMethod
   // @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        log.info("application started");

        setupWebDriver(getConfigPropertyValue("./Framework.properties", "Chrome"));
        String baseURL = getConfigPropertyValue("./Framework.properties", "URL");
        driver.get(baseURL);
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        softAssert = new SoftAssert();
    }


    @AfterMethod
    public void closeBrowser() {
        teardown();
    }

    public void timeoutCounter(int sec) {
        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(sec)
        );
    }

    @Test(priority = 1, groups = {"regression"}, dataProvider = "standard_user")
    public void verifyLoginWithValidUserNameAndPasswordTest(String username, String password) throws InterruptedException {

        String expectedTittle = driver.getTitle();
        softAssert.assertEquals(loginPage.actualTitle, expectedTittle);
        loginPage.login(username, password);
        softAssert.assertEquals(loginPage.actualTitle, expectedTittle);
        softAssert.assertEquals(loginPage.title.getText(), loginPage.titleMessage);
        loginPage.logout();
        softAssert.assertEquals(loginPage.actualTitle, expectedTittle);
        softAssert.assertAll();

    }

    @Test(priority = 2, groups = {"regression"}, dataProvider = "locked_out_user")
    public void verifyLockedOutUserTest(String username, String password) {
        loginPage.login(username, password);
        String expectedLockedOutErrorMessage = loginPage.loginError.getText();
        Assert.assertEquals(expectedLockedOutErrorMessage, loginPage.actualLockedOutErrorMessage);

    }

    @Test(priority = 3, dataProvider = "problem_user")
    public void verifyProblemUserTest(String username, String password) {
        String expectedTittle = driver.getTitle();
        softAssert.assertEquals(loginPage.actualTitle, expectedTittle);
        loginPage.login(username, password);
        softAssert.assertEquals(loginPage.actualTitle, expectedTittle);
        softAssert.assertAll();

    }

    @Test(priority = 4, dataProvider = "performance_glitch_user")
    public void verifyPerformanceGlitchUserTest(String username, String password) {
        String expectedTittle = driver.getTitle();
        softAssert.assertEquals(loginPage.actualTitle, expectedTittle);
        loginPage.login(username, password);
        timeoutCounter(30);
        softAssert.assertEquals(loginPage.actualTitle, expectedTittle);
        softAssert.assertAll();

    }

    @Test(priority = 5, dataProvider = "error_user")
    public void verifyErrorUserTest(String username, String password) {
        String expectedTittle = driver.getTitle();
        softAssert.assertEquals(loginPage.actualTitle, expectedTittle);
        loginPage.login(username, password);
        softAssert.assertEquals(loginPage.actualTitle, expectedTittle);
        softAssert.assertAll();

    }

    @Test(priority = 6, dataProvider = "visual_user")
    public void verifyVisualUserTest(String username, String password) {
        String expectedTittle = driver.getTitle();
        softAssert.assertEquals(loginPage.actualTitle, expectedTittle);
        loginPage.login(username, password);
        softAssert.assertEquals(loginPage.actualTitle, expectedTittle);
        softAssert.assertAll();

    }

    @Test(priority = 7, dataProvider = "invalid_user")
    public void verifyLoginWithInvalidUserNameAndPasswordTest(String username, String password) {
        loginPage.login(username, password);
        String expectedLoginErrorMessage = loginPage.loginError.getText();
        Assert.assertEquals(expectedLoginErrorMessage, loginPage.actualLoginErrorMessage);

    }

    @Test(priority = 8, dataProvider = "filter_a_z")
    public void filterAtoZTest(String username, String password, String filter) {
        loginPage.login(username, password);
        String filterValue = loginPage.activeFilterAtoZ.getText();

        String firstProduct = loginPage.firstProduct.getText();
        String secondProduct = loginPage.secondProduct.getText();

        if (filterValue.equals(filter)) {

            int result = firstProduct.compareTo(secondProduct);
            Assert.assertTrue(result < 0);
        } else {
            Assert.assertTrue(false);

        }


    }

    @Test(priority = 9, groups = {"regression"}, dataProvider = "filter_z_a")
    public void filterZtoATest(String username, String password, String filter){
        loginPage.login(username,password);

        loginPage.filter.click();

        loginPage.filter.sendKeys("Name (Z to A)");
        String filterValue = loginPage.activeFilterAtoZ.getText();

        String firstProduct = loginPage.firstProduct.getText();
        String secondProduct = loginPage.secondProduct.getText();
        if(filterValue.equals("Name (Z to A)")) {

            int result = secondProduct.compareToIgnoreCase(firstProduct);
            Assert.assertTrue(result < 0);
        }
        else {
            Assert.assertTrue(false);

        }

    }

    @Test(priority = 10, dataProvider = "filter_low_high")
    public void filterLowToHighTest(String username, String password, String filter) {
        loginPage.login(username,password);


        loginPage.filter.click();
        loginPage.filter.sendKeys("Price (low to high)");
        String filterValue = loginPage.activeFilterAtoZ.getText();

        String firstProductPrice = loginPage.firstProductPrice.getText();
        String secondProductPrice = loginPage.secondProductPrice.getText();

        float amount1 = Float.parseFloat(firstProductPrice.substring(1));
        float amount2 = Float.parseFloat(secondProductPrice.substring(1));

        if(filterValue.equals("Price (low to high)")) {
            Assert.assertTrue(amount1 < amount2);

        }else{
            Assert.assertTrue(false);

        }

    }

    @Test(priority = 11, dataProvider = "filter_high_low")
    public void filterHighToLowTest(String username, String password, String filter) {
        loginPage.login(username,password);


        loginPage.filter.click();
        loginPage.filter.sendKeys("Price (high to low)");
        String filterValue = loginPage.activeFilterAtoZ.getText();

        String firstProductPrice = loginPage.firstProductPrice.getText();
        String secondProductPrice = loginPage.secondProductPrice.getText();

        float amount1 = Float.parseFloat(firstProductPrice.substring(1));
        float amount2 = Float.parseFloat(secondProductPrice.substring(1));

        if(filterValue.equals("Price (high to low)")) {
            Assert.assertTrue(amount1 > amount2);

        }else{
            Assert.assertTrue(false);
        }

    }

}
