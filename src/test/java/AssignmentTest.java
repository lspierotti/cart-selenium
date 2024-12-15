import model.Items;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        //Login as user:standard_user/pwd:secret_sauce
        // Write your code here

        HomePage homePage = new LoginPage(driver).login("standard_user","secret_sauce");
        homePage.addItemsToCart(List.of(Items.BIKE_LIGHT, Items.BOLT_TSHIRT, Items.FLEECE_JACKET));

        CartPage cartPage = homePage.goToCart();
        cartPage.getItemsAdded();
        List<Items> itemsInCart = cartPage.getItemsAdded();


        // Punto 3
        assertEquals(
                List.of(Items.BIKE_LIGHT, Items.BOLT_TSHIRT, Items.FLEECE_JACKET),
                itemsInCart,
                "Los elementos en el carrito no coinciden con los esperados."
        );

        // Punto 4
        homePage = cartPage.contineShopping();
        Double totalPrice = homePage.getPriceOfProductAdded(List.of(Items.BIKE_LIGHT, Items.BOLT_TSHIRT, Items.FLEECE_JACKET));
        cartPage = homePage.goToCart();
        Double totalPriceCart = cartPage.getTotalPrice();

        assertEquals(totalPrice, totalPriceCart);


        Thread.sleep(6000);




        driver.quit();
    }
}
