package seleautomation.com.RampUpAuto;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App {
    public static void main(String[] args) {

        WebDriver driver = null;
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\enoor\\Desktop\\Authomation\\EnasSelenium\\ChromeDriver.exe");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("Selenium Tutorials");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        String expectedTitle = "Selenium Tutorials - Google Search";
        String actualTitle = driver.getTitle();
        assert actualTitle.equals(expectedTitle)
                : "Text Assertion Failed! Expected title: " + expectedTitle + ", Actual title: " + actualTitle;
        driver.close();
    }
}
