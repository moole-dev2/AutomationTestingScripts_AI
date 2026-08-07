package Pages;

import org.openqa.selenium.*;
import Utils.WaitUtils;

public class LoginPage {

    WebDriver driver;
    WaitUtils wait;

    By email = By.xpath("//input[@type='email']");
    By continueBtn = By.xpath("//button[contains(text(),'Continue')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    public void login(String userEmail) {
        wait.visible(email).sendKeys(userEmail);
        wait.clickable(continueBtn).click();
    }
}