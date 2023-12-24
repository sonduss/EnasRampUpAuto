package seleautomation.com.RampUpAuto;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GitHubSignInPOMDataProviderStartup {
    WebDriver driver = null;
    GitHubSignInPOM gitHubSignInPOM;

    @BeforeClass
    @Parameters("browser")
    public void beforeClass(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    "C:\\Users\\enoor\\Desktop\\Authomation\\EnasRampUpAuto\\ChromeDriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver",
                    "C:\\Users\\enoor\\Desktop\\Authomation\\EnasRampUpAuto\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver",
                    "C:\\Users\\enoor\\Desktop\\Authomation\\EnasRampUpAuto\\msedgedriver.exe");
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Invalid browser value provided");
        }

        gitHubSignInPOM = new GitHubSignInPOM(driver);


    }

    @DataProvider(name = "github-signin")
    public Object[][] githubSigninDb(Method m) {

        switch (m.getName()) {
            case "validSignIn":
                return new Object[][] {
                        { "asaltest19@gmail.com", "password123456789s*" }
                };
            case "inValidSignIn":
                return new Object[][] {
                        { "asaltest1646449@gmail.com", "password123456789s*" },
                        { "asaltest19@gmail.com", "password123456789sgg" },
                        { "", "" }
                };
        }
        return null;

    };

    @BeforeMethod
    public void beforeTest() {
        gitHubSignInPOM.openGitHubLoginPage();
    }

    @Test(dataProvider = "github-signin")
    public void validSignIn(String email, String password) {
        gitHubSignInPOM.enterUserName(email);
        gitHubSignInPOM.enterPassword(password);
        gitHubSignInPOM.pressLogin();
        String currentUrl = gitHubSignInPOM.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://github.com");
    }

    @Test(dataProvider = "github-signin")
    public void inValidSignIn(String email, String password) {
        gitHubSignInPOM.enterUserName(email);
        gitHubSignInPOM.enterPassword(password);
        gitHubSignInPOM.pressLogin();
        String errorMessage = gitHubSignInPOM.getErrorMessage();
        String expectedResult = "Incorrect username or password.";
        Assert.assertEquals(errorMessage, expectedResult);

    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }
}
