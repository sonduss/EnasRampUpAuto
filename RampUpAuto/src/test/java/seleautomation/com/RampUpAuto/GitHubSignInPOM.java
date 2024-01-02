package seleautomation.com.RampUpAuto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GitHubSignInPOM {
    private WebDriver driver;
    private By usernameBox = By.id("login_field");
    private By passwordBox = By.id("password");
    private By signInButton = By.name("commit");
    private By errorBox =By.className("js-flash-alert");

    public GitHubSignInPOM(WebDriver driver) {
        this.driver = driver;
    }

    public void openGitHubLoginPage() {
        driver.get("https://github.com/login");
    }

    public void enterUserName(String username) {
        driver.findElement(usernameBox).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordBox).sendKeys(password);
    }

    public void pressLogin() {
        driver.findElement(signInButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorBox).getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
