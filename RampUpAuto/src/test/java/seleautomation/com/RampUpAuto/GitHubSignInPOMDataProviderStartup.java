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
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GitHubSignInPOMDataProviderStartup {
    WebDriver driver;
    GitHubSignInPOM gitHubSignInPOM;

    @BeforeClass
    @Parameters("browser")
    public void beforeClass(@Optional("chrome") String browser) {
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
        return new Object[][] {
                { "asaltest19@gmail.com", "password123456789s*", true },
                { "asaltest1646449@gmail.com", "password123456789s*", false },
                { "asaltest19@gmail.com", "password123456789sgg", false },
                { "", "", false }
        };
    };

    @BeforeMethod
    public void beforeTest() {
        gitHubSignInPOM.openGitHubLoginPage();
    }

    @Test(dataProvider = "github-signin")
    public void githubSignIn(String email, String password, boolean valid) {
        gitHubSignInPOM.enterUserName(email);
        gitHubSignInPOM.enterPassword(password);
        gitHubSignInPOM.pressLogin();
        if (valid) {
            String currentUrl = gitHubSignInPOM.getCurrentUrl();
            Assert.assertEquals(currentUrl, "https://github.com/session");
        } else {
            String errorMessage = gitHubSignInPOM.getErrorMessage();
            String expectedResult = "Incorrect username or password.";
            Assert.assertEquals(errorMessage.trim(), expectedResult);
        }
    }

    @AfterClass
    public void afterClass() {
        driver.close();
    }
}
