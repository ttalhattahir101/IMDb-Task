package pages;

import io.qameta.allure.Step;
import org.apache.maven.settings.building.SettingsSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Imdb250MovieChartPage extends BasePage{
    WebElement movieTable;
    List<WebElement> movieList;

    //////////***************PageObject Variables********************************

    By imdbChartHeading = By.cssSelector("#main > div > span > div > div > h3");
    By topMovieRatingHeading = By.cssSelector("#main > div > span > div > div > h1");
    By shareIcon = By.id("social-sharing-widget");
    By topMovieRatingBody = By.className("byline");
    By sortByDropDown = By.id("lister-sort-by-options");
    By sortingIcon = By.xpath("//*[@id='main']/div/span/div/div/div[3]/div/div/div[1]/span");
    By moviesTable = By.className("lister-list");
    By showing250TitlesLabel = By.xpath("//*[@id='main']/div/span/div/div/div[3]/div/div/div[2]");
    By signInMenuBar = By.xpath("//*[@id='imdbHeader']/div[2]/div[5]/a/div");
    By watchListColumn = By.className("watchlistColumn");
    By watchListIcon = By.cssSelector("div[class*='wl-ribbon standalone retina']");
    By watchListMenuBar = By.xpath("//*[@id='imdbHeader']/div[2]/div[4]/a/div");
    By movieTitleColumn = By.className("titleColumn");
    By markSeen = By.xpath("//*[@id='main']/div/span/div/div/div[3]/table/tbody/tr[1]/td[4]/div/div[2]/div[3]");
    By seenText = By.xpath("//div[@class='seen-widget seen-widget-tt0111161 seen']//div[@class='seen'][contains(text(),'Seen')]");
    By ratingForFirstMovie = By.xpath("//*[@id='main']/div/span/div/div/div[3]/table/tbody/tr[1]/td[4]/div/div[2]/div[4]");
    By rateFirstMovie = By.xpath("//div[@class='seen-widget seen-widget-tt0111161 seen']//li[contains(text(),'4')]");
    By ratingNumberOfFirstMovie = By.xpath("//*[@id='main']/div/span/div/div/div[3]/table/tbody/tr[1]/td[4]/div/div[2]/div[4]");
    By deleteRatingForFirstMovie = By.xpath("//*[@id='main']/div/span/div/div/div[3]/table/tbody/tr[1]/td[4]/div/div[1]/div/span");
    //***********************Methods********************************************

    @Step("Verify IMDb chart heading")
    public String getImdbChartHeading() {
        return driver.findElement(imdbChartHeading).getText();
    }

    @Step("Verify top movie rating heading")
    public String getTopMovieRatingHeading() {
        return driver.findElement(topMovieRatingHeading).getText();
    }

    @Step("Verify top movie rating body content")
    public String getTopMovieRatingBody() {
        return driver.findElement(topMovieRatingBody).getText();
    }

    public String get250TitlesLabel() {
        return driver.findElement(showing250TitlesLabel).getText();
    }

    @Step("Sort movies on different categories")
    public void selectValueFromSortByDropDown(String value) {
        explicitWait(sortByDropDown);
        Select drpSort = new Select(driver.findElement(sortByDropDown));
        drpSort.selectByVisibleText(value);
    }

    @Step("Sort movies in ascending and descending order")
    public String clickSortingIcon() {
        WebElement sortingOrder = driver.findElement(sortingIcon);
        sortingOrder.click();
        return sortingOrder.getAttribute("title");
    }

    @Step("Click on sign in option from top menu bar")
    public void clickOnSignInMenuBar() {
        driver.findElement(signInMenuBar).click();
    }

    @Step("Click on any movie title to navigate to movie detail page")
    public void clickOnMovieTitle(int inputMovie) {
       getRowsAndColumnsOfMovieTable();
       movieList.get(inputMovie).findElement(movieTitleColumn).findElement(By.tagName("a")).click();
    }

    @Step("Add any movie to watch list")
    public void addMovieToWatchList(int inputMovieWatch) {
        getRowsAndColumnsOfMovieTable();
        explicitWait(watchListIcon);
        movieList.get(inputMovieWatch).findElement(watchListColumn).findElement(watchListIcon).click();
    }

    @Step("Mark first movie as seen from the list")
    public void clickFirstMovieToMarkSeen() {
        explicitWait(markSeen);
        driver.findElement(markSeen).click();
    }

    public String getSeenTextOfFirstMovie() {
        explicitWait(seenText);
       return   driver.findElement(seenText).getText();
    }

    public String getRatingOfFirstMovie() {
        explicitWait(ratingForFirstMovie);
     return driver.findElement(ratingForFirstMovie).getText();
    }

    @Step("Rate first movie from the list")
    public void rateFirstMovie4() {
        driver.findElement(rateFirstMovie).click();
    }

    public void clickRatingOfFirstMovie() {
        explicitWait(ratingNumberOfFirstMovie);
        driver.findElement(ratingNumberOfFirstMovie).click();
    }

    @Step("Delete rating of first movie from the list")
    public void deleteRatingOfFirstMovie() {
        driver.findElement(deleteRatingForFirstMovie).click();
    }

    public Imdb250MovieChartPage(WebDriver driver) {
        this.driver=driver;
    }

    public String getAttributeValueOfWatchList(int inputMovieWatch) {
        explicitWait(watchListIcon);
        getRowsAndColumnsOfMovieTable();
        return movieList.get(inputMovieWatch).findElement(watchListColumn).findElement(watchListIcon).getAttribute("title");
    }

    public void getRowsAndColumnsOfMovieTable() {
        explicitWait(moviesTable);
        movieTable = driver.findElement(moviesTable);
        movieList = movieTable.findElements(By.tagName("tr"));
    }

    public void clickShareIcon() {
        driver.findElement(shareIcon).click();
    }

    }
