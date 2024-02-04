package com.stdbank.pages;

import com.stdbank.tests.LoginTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

public class LoginPage {
    public String actualTitle = "Swag Labs";
    public String actualLoginErrorMessage = "Epic sadface: Username and password do not match any user in this service";
    public String actualLockedOutErrorMessage = "Epic sadface: Sorry, this user has been locked out.";
    public String titleMessage = "Products";
    @FindBy(how = How.ID, using = "user-name")
    public WebElement username;
    @FindBy(how = How.ID, using = "password")
    public WebElement password;
    @FindBy(how = How.ID, using = "login-button")
    public WebElement loginBtn;
    @FindBy(how = How.XPATH, using = "//*[@id=\"login_button_container\"]/div/form/div[3]")
    public WebElement loginError;

    @FindBy(how = How.ID, using = "react-burger-menu-btn")
    public WebElement burgerMenuBtn;
    @FindBy(how = How.ID, using = "logout_sidebar_link")
    public WebElement logout;

    @FindBy(how = How.CLASS_NAME, using = "title")
    public WebElement title;

    @FindBy(how = How.CLASS_NAME, using = "product_sort_container")
    public WebElement filter;

    @FindBy(how = How.CLASS_NAME, using = "active_option")
    public WebElement activeFilterAtoZ;

    @FindBy(how = How.XPATH, using = "/html/body/div/div/div/div[2]/div/div/div/div[1]/div[2]/div[1]/a/div")
    public WebElement firstProduct;

    @FindBy(how = How.XPATH, using = "/html/body/div/div/div/div[2]/div/div/div/div[2]/div[2]/div[1]/a/div")
    public WebElement secondProduct;

    @FindBy(how = How.XPATH, using = "/html/body/div/div/div/div[2]/div/div/div/div[1]/div[2]/div[2]/div")
    public WebElement firstProductPrice;

    @FindBy(how = How.XPATH, using = "/html/body/div/div/div/div[2]/div/div/div/div[2]/div[2]/div[2]/div")
    public WebElement secondProductPrice;


    public void login(String name, String pass){

        username.sendKeys(name);
        password.sendKeys(pass);
        loginBtn.click();
    }

    public void logout() throws InterruptedException {
        burgerMenuBtn.click();
        Thread.sleep(70); // to be removed
        logout.click();
    }


}
