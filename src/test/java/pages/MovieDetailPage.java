package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MovieDetailPage extends BasePage{

    //////////***************PageObject Variables********************************

    By watchListIcon = By.xpath("//*[@id='title-overview-widget']/div[1]/div[2]/div/div[2]/div[1]/div[1]/div");
    By yourMovieRating = By.className("star-rating-value");

    public String getAttributeValue() {
        explicitWait(watchListIcon);
        return driver.findElement(watchListIcon).getAttribute("title");
    }

    @Step("Click on watch list icon to add/remove movie from watch list")
    public void clickOnWatchListIcon() {
        explicitWait(watchListIcon);
        driver.findElement(watchListIcon).click();
    }
    public String getYourRatingValue() {
        explicitWait(yourMovieRating);
        return driver.findElement(yourMovieRating).getText();
    }
    public MovieDetailPage(WebDriver driver) {
        this.driver=driver;
    }
}
