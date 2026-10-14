package Tests;

import Base.BaseTest;
import Pages.InventoryPage;
import Pages.LoginPage;
import com.google.gson.JsonObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.DataDriven;

public class InventoryTest extends BaseTest {
    private final JsonObject data = DataDriven.jsonReader();

    @DataProvider(name = "validUsers")
    public Object[][] validUsers() {
        return DataDriven.getValidUsers();
    }
    @Test(dataProvider = "validUsers",description = "Verify Inventory Page Elements After Login")
    public void verifyInventoryPageElements(String usenname, String password){
        JsonObject expected = data.getAsJsonObject("expected");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.Login(usenname,password);
        var inventoryPage =loginPage.clickLoginButton();

        Assert.assertEquals(inventoryPage.getPageTittle(),expected.get("pageTitle").getAsString(),
                "Page title is incorrect for user: " + usenname);

        Assert.assertTrue(inventoryPage.isCartIconDisplayed(),
                "Cart icon is not displayed for user: " + usenname);

        Assert.assertEquals(inventoryPage.getProductsCount(), expected.get("productCount").getAsInt(),
                "Number of products is incorrect for user: " + usenname);
    }
}
