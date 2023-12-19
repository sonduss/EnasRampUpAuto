package seleautomation.com.RampUpAuto;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchOnGooglePOM {
    private WebDriver driver;
    private By searchBox = By.name("q");

    public SearchOnGooglePOM(WebDriver driver) {
        this.driver = driver;
    }

    public void openGoogle() {
        driver.get("https://www.google.com/");
    }

    public void searchFor(String searchText) {
        driver.findElement(searchBox).sendKeys(searchText);
        driver.findElement(searchBox).sendKeys(Keys.ENTER);
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
