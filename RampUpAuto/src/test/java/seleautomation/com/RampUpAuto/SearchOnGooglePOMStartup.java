package seleautomation.com.RampUpAuto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchOnGooglePOMStartup {
    SearchOnGooglePOM googlePage;
    WebDriver driver = null;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\enoor\\Desktop\\Authomation\\EnasRampUpAuto\\ChromeDriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        googlePage = new SearchOnGooglePOM(driver);
        googlePage.openGoogle();
    }

    @Test
    public void searchOnGoogle() {

        googlePage.searchFor("Selenium Tutorials");

        String expectedTitle = "Selenium Tutorials - Google Search";
        String actualTitle = googlePage.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }

}
