package pages;

import model.Items;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

public class HomePage {
    private static final Map<Items, By> ITEMS_IDS = Map.of(
            Items.BACKPACK, By.ById.id("add-to-cart-sauce-labs-backpack"),
            Items.BIKE_LIGHT, By.ById.id("add-to-cart-sauce-labs-backpack"),
            Items.BOLT_TSHIRT, By.ById.id("add-to-cart-sauce-labs-backpack"),
            Items.FLEECE_JACKET, By.ById.id("add-to-cart-sauce-labs-backpack")
            );
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;

    }

    public void addItemsToCart(List<Items> items) {
        items.forEach(itemToAdd -> driver.findElement(ITEMS_IDS.get(itemToAdd)).click());
    }

    public CartPage goToCart() {
        return null;
    }
}
