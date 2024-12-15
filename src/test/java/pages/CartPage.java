package pages;

import model.Items;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartPage {
    private static final Map<Items, By> CART_ITEMS_IDS = Map.of(
            //Items.BACKPACK, By.ById.id("remove-sauce-labs-backpack"),
            Items.BIKE_LIGHT, By.ById.id("remove-sauce-labs-bike-light"),
            Items.BOLT_TSHIRT, By.ById.id("remove-sauce-labs-bolt-t-shirt"),
            Items.FLEECE_JACKET, By.ById.id("remove-sauce-labs-fleece-jacket")
    );

    private final WebDriver driver;
    private static By CART_ITEMS = By.ByClassName.className("cart_item");
    private static By CONTINUE_SHOPPING = By.ById.id("continue-shopping");
    private static By ALL_ITEMS = By.ByClassName.className("item_pricebar");

    public CartPage(WebDriver driver) {
        this.driver = driver;

    }

    public HomePage contineShopping() {
        driver.findElement(CONTINUE_SHOPPING).click();
        return new HomePage(driver);
    }

    public List<Items> getItemsAdded() {
        List<Items> itemsAdded = new ArrayList<>();

        // Iterar sobre cada elemento del carrito
        for (WebElement cartItem : driver.findElements(CART_ITEMS)) {
            try {
                // Obtener el atributo 'id' del botón dentro de cada 'cart_item_label'
                String id = cartItem.findElement(By.className("cart_item_label"))
                        .findElement(By.tagName("button"))
                        .getAttribute("id");

                // Buscar el id en el mapa y agregar el correspondiente Item a la lista
                for (Map.Entry<Items, By> entry : CART_ITEMS_IDS.entrySet()) {
                    // Verificar si el valor del `By` contiene el `id`
                    if (entry.getValue().toString().contains(id)) {
                        itemsAdded.add(entry.getKey());
                        break; // Salir del loop si se encuentra el mapeo
                    }
                }
            } catch (NoSuchElementException e) {
                // Ignorar si no se encuentra el elemento
                System.out.println("Elemento no encontrado en el carrito: " + cartItem);
            }
        }

        return itemsAdded;
    }


    public Double getTotalPrice() {
        List<WebElement> elements = driver.findElements(ALL_ITEMS);
        double prices = 0.0;

        for (WebElement element : elements) {
            String priceText = element.findElement(By.ByClassName.className("inventory_item_price")).getText();
            double price = Double.parseDouble(priceText.replace("$", ""));
            prices = prices + price;
        }
        return prices;
    }
}
