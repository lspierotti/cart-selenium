import model.Items;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.List;

import static model.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

public class AssignmentTest {

    @Test
    void sauceDemoTest() throws InterruptedException {
        // This is a test case for the assignment, and it uses the SauceLabs platform
        // The test case is designed to test the basic skills of the applicant with Selenium WebDriver
        // In this test case, the applicant is expected to perform the following tasks:
        // 1. Login to the SauceLabs demo platform
        // 2. Add items to the cart (Bike Light, Bolt T-Shirt, and Fleece Jacket)
        // 3. Confirm that the items are added to the cart
        // 4. Confirm that the total price of the items in the cart is correct
        // 5. Remove an item from the cart (Bike Light)
        // 6. Confirm that the item is removed from the cart
        // 7. Confirm that the total price of the items in the cart is correct
        // 8. Add another item to the cart (Onesie)
        // 9. Confirm that the item is added to the cart
        // 10. Confirm that the total price of the items in the cart is correct
        // Extra credit: Generate a report of the JUnit tests. You can use the plugin maven-surefire-report-plugin (for example) to generate a report.
        // mvn test surefire-report:report

        WebDriver driver = new ChromeDriver();
        driver.get(URL_APP);
        //Login as user:standard_user/pwd:secret_sauce
        // Write your code here

        HomePage homePage = new LoginPage(driver).login(USERNAME,PASSWORD);
        homePage.addItemsToCart(List.of(Items.BIKE_LIGHT, Items.BOLT_TSHIRT, Items.FLEECE_JACKET));

        CartPage cartPage = homePage.goToCart();
        //cartPage.getItemsAdded();
        List<Items> itemsInCart = cartPage.getItemsAdded();


        // Punto 3
        assertEquals(
                List.of(Items.BIKE_LIGHT, Items.BOLT_TSHIRT, Items.FLEECE_JACKET),
                itemsInCart,
                "The elements in the cart does not match."
        );

        // Punto 4
        homePage = cartPage.contineShopping();
        Double totalPrice = homePage.getPriceOfProductAdded(List.of(Items.BIKE_LIGHT, Items.BOLT_TSHIRT, Items.FLEECE_JACKET));
        cartPage = homePage.goToCart();
        Double totalPriceCart = cartPage.getTotalPrice();

        assertEquals(totalPrice, totalPriceCart);

        // Punto 5

        cartPage.removeItemFromCart(List.of(Items.BIKE_LIGHT));
        itemsInCart = cartPage.getItemsAdded();

        // Punto 6
        assertFalse(itemsInCart.contains(Items.BIKE_LIGHT), "The BIKE_LIGHT is still in the cart");
        Thread.sleep(6000);

        // Punto 7

        homePage = cartPage.contineShopping();
        Double totalPriceProductsRemoved = homePage.getPriceOfProductsRemoved(List.of(Items.BIKE_LIGHT));
        cartPage = homePage.goToCart();
        totalPriceCart = cartPage.getTotalPrice();
        assertEquals(totalPrice-totalPriceProductsRemoved, totalPriceCart);

        // Punto 8 Y 9

        homePage = cartPage.contineShopping();
        homePage.addItemsToCart(List.of(Items.SAUCE_ONESIE));
        cartPage = homePage.goToCart();
        itemsInCart = cartPage.getItemsAdded();

        assertTrue(itemsInCart.contains(Items.SAUCE_ONESIE), "El elemento SAUCE ONESIE está en el carrito");

        // PUNTO 10
        homePage = cartPage.contineShopping();
        totalPrice = homePage.getPriceOfProductAdded(List.of(Items.BOLT_TSHIRT, Items.FLEECE_JACKET, Items.SAUCE_ONESIE));
        cartPage = homePage.goToCart();
        totalPriceCart = cartPage.getTotalPrice();

        assertEquals(totalPrice, totalPriceCart);
        driver.quit();
    }
}
