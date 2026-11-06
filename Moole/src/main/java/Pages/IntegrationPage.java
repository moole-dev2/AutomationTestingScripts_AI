package Pages;

import org.openqa.selenium.*;
import Utils.*;

public class IntegrationPage {

    WebDriver driver;
    WaitUtils wait;
    JSUtils js;

    public IntegrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver);
        this.js = new JSUtils(driver);
    }

    By bitbucket = By.xpath("//img[@alt='Bitbucket']");
    By nextBtn = By.xpath("//button[contains(text(),'Next')]");
    By email = By.xpath("//input[@type='email']");
    By token = By.xpath("//input[@type='password']");
    By updateBtn = By.xpath("//button[contains(.,'Update')]");

    public void openBitbucket() {
        js.click(wait.clickable(bitbucket));
    }

    public void clickNext() {
        js.click(wait.clickable(nextBtn));
    }

    public void enterToken(String apiToken) {
        wait.visible(token).sendKeys(apiToken);
    }

    public void update() {
        js.click(wait.clickable(updateBtn));
    }
}