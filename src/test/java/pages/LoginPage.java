package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*@Log4j*/
public class LoginPage {
    private static final By USER_NAME_ID = By.ById.id("user-name");
    private static final By PASSWORD_ID = By.ById.id("password");
    private static final By BUTTON_LOGIN_ID = By.ById.id("login-button");
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage login(String user, String password) {
        driver.findElement(USER_NAME_ID).sendKeys(user);
        driver.findElement(PASSWORD_ID).sendKeys(password);
        driver.findElement(BUTTON_LOGIN_ID).click();
        //log.
        return new HomePage(driver);
    }
}
