package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class ActionUtils {

    WebDriver driver;
    Actions actions;

    public ActionUtils(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    // Hover over element
    public void hover(WebElement element) {
        actions.moveToElement(element).perform();
    }

    // Click using Actions class
    public void click(WebElement element) {
        actions.moveToElement(element).click().perform();
    }

    // Right click
    public void rightClick(WebElement element) {
        actions.contextClick(element).perform();
    }

    // Double click
    public void doubleClick(WebElement element) {
        actions.doubleClick(element).perform();
    }

    // Drag and drop
    public void dragAndDrop(WebElement source, WebElement target) {
        actions.dragAndDrop(source, target).perform();
    }

    // Send keys with Actions
    public void type(WebElement element, String text) {
        actions.moveToElement(element).click().sendKeys(text).perform();
    }
}