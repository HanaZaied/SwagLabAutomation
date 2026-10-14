package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private WebDriver driver;
    private final By cartIconLoc = By.className("shopping_cart_link");
    private final By inventoryItemLoc = By.cssSelector("div[class=\"inventory_item\"]");


    public InventoryPage(WebDriver driver){
        this.driver = driver;
    }


    public String getPageTittle(){
        return driver.getTitle();
    }
    public boolean isCartIconDisplayed(){
       return driver.findElement(cartIconLoc).isDisplayed();
    }
    public int getProductsCount() {

        return driver.findElements(inventoryItemLoc).size();
    }



}
