package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class NotificationOrg {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public NotificationOrg(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
    }

    public void openNotifications() {
        driver.get("https://moole.ai/app/settings/organization/notifications");
    }

    public void clickNotificationsMenu() {
        WebElement notifications = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[@href='/app/settings/organization/notifications']")
                )
        );
        js.executeScript("arguments[0].click();", notifications);
    }

    public void ensureNotificationEnabled() {

        By toggleLocator = By.xpath("//button[@role='switch']");

        WebElement toggle = wait.until(
                ExpectedConditions.presenceOfElementLocated(toggleLocator)
        );

        String state = toggle.getAttribute("aria-checked");

        System.out.println("Current toggle state: " + state);

        if (state != null && state.equals("false")) {

            System.out.println("Toggle is OFF → turning ON");

            js.executeScript("arguments[0].click();", toggle);

            // IMPORTANT: re-fetch after DOM update
            wait.until(ExpectedConditions.attributeToBe(toggleLocator, "aria-checked", "true"));

            System.out.println("Toggle is now ON");

        } else {
            System.out.println("Toggle already ON → skipping");
        }
    }
    public void clickUpdate() {
        WebElement update = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[normalize-space()='Update']")
                )
        );
        js.executeScript("arguments[0].click();", update);
    }

    public void openActionsMenu() {
        WebElement menu = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@aria-label='Open actions menu']")
                )
        );
        js.executeScript("arguments[0].click();", menu);
    }

    public void editEmail() {
        WebElement edit = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@role='menuitem' and .//span[text()='Edit Email channels']]")
                )
        );
        js.executeScript("arguments[0].click();", edit);
    }

    public void enterEmails(String emails) {
        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("ncc-cc"))
        );
        input.clear();
        input.sendKeys(emails);
    }

    public void clickSave() {
        WebElement save = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[.//span[normalize-space()='Save']]")
                )
        );
        js.executeScript("arguments[0].click();", save);
    }

    public void clickCancel() {
        WebElement cancel = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[normalize-space()='Cancel']")
                )
        );
        js.executeScript("arguments[0].click();", cancel);
    }
}