package tests;

import org.testng.annotations.*;

import io.qameta.allure.Description;
import org.testng.Assert;
import pages.BasePage;
import pages.Imdb250MovieChartPage;
import pages.MovieDetailPage;
import pages.SignInPage;


public class Imdb250MovieChartTest extends BaseTest {

    Imdb250MovieChartPage imdbMviePageObj;
    SignInPage signInPageObj;
    MovieDetailPage detailMoviePageObj;
    BasePage basePageObj;

    @BeforeClass
    public void initializeObjects() {
        getDriver();
        imdbMviePageObj = new Imdb250MovieChartPage(driver);
        signInPageObj = new SignInPage(driver);
        detailMoviePageObj = new MovieDetailPage(driver);
        basePageObj = new BasePage();
    }

    @Test
    @Description("Verify navigation to IMDB 250 Top Rated Movies page ")
    public void navigationToCorrectUrl() {
       String expectedUrl = "https://www.imdb.com/chart/top?ref_=login";
       Assert.assertEquals(driver.getCurrentUrl(),expectedUrl);
    }

    @Test
    @Description("Verify top rated movies heading and body text")
    public void headerAndBodyTextOf250Movies() {
        Assert.assertEquals(imdbMviePageObj.getImdbChartHeading(),"IMDb Charts");
        Assert.assertEquals(imdbMviePageObj.getTopMovieRatingHeading(),"Top Rated Movies");
        Assert.assertEquals(imdbMviePageObj.getTopMovieRatingBody(),"Top 250 as rated by IMDb Users");
        Assert.assertEquals(imdbMviePageObj.get250TitlesLabel(),"Showing 250 Titles");
    }

    @Test
    @Description("Sort Top 250 movies by different categories")
    public void sortingTop250MoviesByDifferentCategories() {
        imdbMviePageObj.selectValueFromSortByDropDown("IMDb Rating");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.imdb.com/chart/top?sort=ir,desc&mode=simple&page=1");
        imdbMviePageObj.selectValueFromSortByDropDown("Release Date");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.imdb.com/chart/top?sort=us,desc&mode=simple&page=1");
        imdbMviePageObj.selectValueFromSortByDropDown("Number of Ratings");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.imdb.com/chart/top?sort=nv,desc&mode=simple&page=1");
        imdbMviePageObj.selectValueFromSortByDropDown("Your Rating");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.imdb.com/chart/top?sort=ur,desc&mode=simple&page=1");
        imdbMviePageObj.selectValueFromSortByDropDown("Ranking");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.imdb.com/chart/top?sort=rk,asc&mode=simple&page=1");
    }

    @Test
    @Description("Sort top 250 movies by ascending and descending order")
    public void sortOrderOfTop250Movies() {
        Assert.assertEquals(imdbMviePageObj.clickSortingIcon(),"Descending order");
        Assert.assertEquals(imdbMviePageObj.clickSortingIcon(),"Ascending order");
    }

    @Test
    @Description("Add movie to watch list and remove the same movie from watch list")
    public void addMovieToWatchList() throws InterruptedException {
        imdbMviePageObj.clickOnSignInMenuBar();
        signInPageObj.clickSignInWithImdbButton();
        signInPageObj.loginWithImdb();
        Assert.assertEquals(imdbMviePageObj.getAttributeValueOfWatchList(0), "Click to add to watchlist");
        imdbMviePageObj.addMovieToWatchList(0);
        Thread.sleep(1000);
        Assert.assertEquals(imdbMviePageObj.getAttributeValueOfWatchList(0),"Click to remove from watchlist");
        imdbMviePageObj.clickOnMovieTitle(0);
        Assert.assertEquals(detailMoviePageObj.getAttributeValue(),"Click to remove from watchlist" );
        detailMoviePageObj.clickOnWatchListIcon();
        driver.navigate().back();
        Assert.assertEquals(imdbMviePageObj.getAttributeValueOfWatchList(0), "Click to add to watchlist");
    }

    @Test
    @Description("Add and remove rating from the list")
    public void addRemoverating() throws InterruptedException {
        imdbMviePageObj.clickFirstMovieToMarkSeen();
        Thread.sleep(20);
        Assert.assertEquals(imdbMviePageObj.getSeenTextOfFirstMovie(),"Seen");
        imdbMviePageObj.rateFirstMovie4();
        Assert.assertEquals(imdbMviePageObj.getRatingOfFirstMovie(),"4");
        imdbMviePageObj.clickOnMovieTitle(0);
        Assert.assertEquals(detailMoviePageObj.getYourRatingValue(),"4");
        driver.navigate().back();
        imdbMviePageObj.clickRatingOfFirstMovie();
        imdbMviePageObj.deleteRatingOfFirstMovie();
    }




    @AfterClass
    public void tearDown() {
        imdbMviePageObj =null;
        basePageObj = null;
        detailMoviePageObj = null;
        signInPageObj = null;
        quitBrowser();
    }



}
