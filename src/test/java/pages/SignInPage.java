package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage extends BasePage{

    //////////***************PageObject Variables********************************
    By signInWithImdb = By.xpath("//*[@id='signin-options']/div/div[1]/a[1]");
    By email = By.id("ap_email");
    By password = By.id("ap_password");
    By signInButton = By.id("signInSubmit");

    //***********************Methods********************************************

    @Step("Click on sign in with IMDb button")
    public void clickSignInWithImdbButton() {
        explicitWait(signInWithImdb);
        driver.findElement(signInWithImdb).click();
    }

    @Step("Enter input in email field")
    public void enterTextInEmailField(String inputEmail) {
        explicitWait(email);
        driver.findElement(email).sendKeys(inputEmail);
    }

    @Step("Enter input in password field")
    public void enterTextInPassowrdField(String inputPassword) {
        driver.findElement(password).sendKeys(inputPassword);
    }

    @Step("Click on sign in button")
    public void clickOnSignInButton() {
        driver.findElement(signInButton).click();
    }


    public void loginWithImdb() {
        readFile();
        enterTextInEmailField(prop.getProperty("email"));
        enterTextInPassowrdField(prop.getProperty("password"));
        clickOnSignInButton();
    }

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }
}
