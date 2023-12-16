package seleautomation.com.RampUpAuto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchOnGooglePOMStartup {

    @Test
    public void searchOnGoogle() {
        WebDriver driver = null;
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\enoor\\Desktop\\Authomation\\EnasRampUpAuto\\ChromeDriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        SearchOnGooglePOM googlePage = new SearchOnGooglePOM(driver);
        googlePage.openGoogle();
        googlePage.searchFor("Selenium Tutorials");

        String expectedTitle = "Selenium Tutorials - Google Search";
        String actualTitle = googlePage.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        driver.close();
    }
}
