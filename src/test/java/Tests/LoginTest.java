package Tests;

import Base.BaseTest;
import Pages.LoginPage;
import org.openqa.selenium.devtools.latest.log.Log;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.DataDriven;
import com.google.gson.JsonObject;


public class LoginTest extends BaseTest {
    private final JsonObject data = DataDriven.jsonReader();

    @DataProvider(name = "validUsers")
    public Object[][] validUsers() {
        return DataDriven.getValidUsers();
    }

    @Test(dataProvider = "validUsers",description = "Verify Successful Login")
    public void verifySuccessfulLogin(String username, String password){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.Login(username,password);
        loginPage.clickLoginButton();

        String expectedUrlPart = data.getAsJsonObject("expected").get("inventoryUrlPart").getAsString();
        Assert.assertTrue(loginPage.getCurrentUrl().contains(expectedUrlPart),
                "User was not redirected to the inventory page. Actual URL: " + loginPage.getCurrentUrl());
    }

    @Test(description = "Verify Invalid Login")
    public void verifyInvalidLogin(){
        JsonObject user = data.getAsJsonObject("invalidUser");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.Login(user.get("username").getAsString(),
                user.get("password").getAsString());
        loginPage.clickLoginButton();

        String expectedError = data.getAsJsonObject("expected").get("invalidLoginError").getAsString();
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedError),
                "Unexpected error message: " + loginPage.getErrorMessage());
    }

    @Test(description = "Verify Login Without Password")
    public void verifyLoginWithoutPassword(){
        JsonObject user = data.getAsJsonObject("noPasswordUser");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.Login(user.get("username").getAsString(),
                user.get("password").getAsString());
        loginPage.clickLoginButton();

        String expectedError = data.getAsJsonObject("expected").get("missingPasswordError").getAsString();
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedError),
                "Unexpected error message: " + loginPage.getErrorMessage());

    }


}
