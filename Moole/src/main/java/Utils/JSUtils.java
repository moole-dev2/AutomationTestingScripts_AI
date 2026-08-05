package Utils;

import org.openqa.selenium.*;

public class JSUtils {

    WebDriver driver;

    public JSUtils(WebDriver driver) {
        this.driver = driver;
    }

    // Click using JS
    public void click(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    // Scroll into view
    public void scrollTo(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    // Highlight element (debugging)
    public void highlight(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].style.border='3px solid red'", element);
    }

    // Set value using JS (fast input)
    public void setValue(WebElement element, String value) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].value='" + value + "';", element);
    }
}