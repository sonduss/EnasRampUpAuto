package seleautomation.com.RampUpAuto;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class App {
    public static void main(String[] args) {
        WebDriver driver = null;
        WebDriverManager.chromedriver().browserVersion("120.0.6099.71").setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("Selenium Tutorials");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        if (driver.findElement(By.name("q")).getText().equals("Selenium Tutorials")) {
            System.out.println("Text Assertion Passed");
        } else {
            System.out.println("Text Assertion Failed");
        }

        driver.close();
    }
}
