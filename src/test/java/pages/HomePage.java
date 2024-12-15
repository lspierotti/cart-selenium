package pages;

import model.Items;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class HomePage {
    private static final Map<Items, By> ITEMS_IDS = Map.of(
            Items.BACKPACK, By.ById.id("add-to-cart-sauce-labs-backpack"),
            Items.BIKE_LIGHT, By.ById.id("add-to-cart-sauce-labs-bike-light"),
            Items.BOLT_TSHIRT, By.ById.id("add-to-cart-sauce-labs-bolt-t-shirt"),
            Items.FLEECE_JACKET, By.ById.id("add-to-cart-sauce-labs-fleece-jacket"),
            Items.SAUCE_ONESIE, By.ById.id("add-to-cart-sauce-labs-onesie")
            );
    private final WebDriver driver;
    private static final By CART_BUTTON_CLASS = By.ByClassName.className("shopping_cart_link");

    public HomePage(WebDriver driver) {
        this.driver = driver;

    }

    public void addItemsToCart(List<Items> items) {
        items.forEach(itemToAdd -> driver.findElement(ITEMS_IDS.get(itemToAdd)).click());
    }

    public CartPage goToCart() {
        driver.findElement(CART_BUTTON_CLASS).click();
        return new CartPage(driver);
    }

    public Double getPriceOfProductAdded(List<Items> items) {
        double totalPrice = 0.0;

        for (Items item : items) {
            String idValue = String.valueOf(ITEMS_IDS.get(item));

            String updatedIdRemove = idValue.replace("By.id: add-to-cart", "remove");
            By updatedLocatorRemove = By.ById.id(updatedIdRemove);
            WebElement element = driver.findElement(updatedLocatorRemove);
            WebElement parentDiv = element.findElement(By.xpath("./ancestor::div[contains(@class, 'pricebar')]"));
            String priceElement = parentDiv.findElement(By.className("inventory_item_price")).getText();
            double price = Double.parseDouble(priceElement.replace("$", ""));

            totalPrice = totalPrice + price;

        }
        return totalPrice;
    }

    public Double getPriceOfProductsRemoved(List<Items> items) {
        double totalPrice = 0.0;

        for (Items item : items) {
            WebElement element = driver.findElement(ITEMS_IDS.get(item));
            WebElement parentDiv = element.findElement(By.xpath("./ancestor::div[contains(@class, 'pricebar')]"));
            String priceElement = parentDiv.findElement(By.className("inventory_item_price")).getText();
            double price = Double.parseDouble(priceElement.replace("$", ""));

            totalPrice = totalPrice + price;

        }
        return totalPrice;
    }
}
