package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {
    private final WebDriver driver;
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector("form[aria-label=\"Login\"] #login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver){
        this.driver=driver;
    }

    public void Login(String username, String password){
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
    }

    public InventoryPage clickLoginButton(){
        driver.findElement(loginButton).click();
        return new InventoryPage(driver);
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


}
