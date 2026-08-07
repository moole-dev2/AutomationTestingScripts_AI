package Resources;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import Utils.ConfigReader;
import org.testng.annotations.Test;



public class Runbook {

    @Test
    public void RunbookTest() throws InterruptedException {


        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        try {

            // =========================================================
            // OPEN WEBSITE
            // =========================================================
        	driver.get(ConfigReader.getProperty("baseUrl"));
            driver.manage().window().maximize();
            Thread.sleep(3000);

            // =========================================================
            // HANDLE POPUP
            // =========================================================
            safeClick(driver, wait, js,
                    By.xpath("//button[contains(text(),'OK')]"),
                    "Privacy Popup");

            Thread.sleep(2000);

            // =========================================================
            // OPEN RESOURCES DROPDOWN
            // =========================================================
            WebElement resources = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//span[normalize-space()='Resources']"))
            );

            actions.moveToElement(resources).perform();
            Thread.sleep(1500);

            js.executeScript("arguments[0].click();", resources);
            System.out.println("Resources opened");

            Thread.sleep(2000);

            // =========================================================
            // CLICK RUNBOOK
            // =========================================================
            WebElement runbook = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//span[contains(text(),'The Runbook')]"))
            );

            highlight(js, runbook);
            js.executeScript("arguments[0].click();", runbook);

            System.out.println("Clicked Runbook");
            Thread.sleep(4000);

            // =========================================================
            // SCROLL PAGE LIKE USER
            // =========================================================
            smoothScroll(js);

            // =========================================================
            // CATEGORY CHECKBOXES
            // =========================================================
            List<WebElement> categories = driver.findElements(
                    By.xpath("//ul/li//label"));

            System.out.println("Total Categories: " + categories.size());

            for (int i = 0; i < categories.size(); i++) {

                try {

                    categories = driver.findElements(By.xpath("//ul/li//label"));
                    WebElement label = categories.get(i);

                    js.executeScript("arguments[0].scrollIntoView({block:'center'});", label);
                    Thread.sleep(1000);

                    highlight(js, label);

                    js.executeScript("arguments[0].click();", label);
                    System.out.println("Checked: " + label.getText());

                    Thread.sleep(1500);

                    // UNCHECK
                    js.executeScript("arguments[0].click();", label);
                    System.out.println("Unchecked: " + label.getText());

                    Thread.sleep(1500);

                } catch (Exception e) {
                    System.out.println("Category click failed: " + e.getMessage());
                }
            }

            // =========================================================
            // CLICK SIGN-UP GOOGLE
            // =========================================================
            safeClick(driver, wait, js,
                    By.xpath("//h1[contains(text(),'Sign-Up with Google')]/parent::div"),
                    "Sign-Up Google");

            Thread.sleep(3000);

            // =========================================================
            // CLOSE POPUP
            // =========================================================
            safeClick(driver, wait, js,
                    By.xpath("//button[@aria-label='Close popup']"),
                    "Close Popup");

            Thread.sleep(2000);

            // =========================================================
            // CLICK MOOLE ACCESS TOKENS
            // =========================================================
            safeClick(driver, wait, js,
                    By.xpath("//h1[contains(text(),'Moole Access Tokens')]/parent::div"),
                    "Access Tokens");

            Thread.sleep(3000);

            // =========================================================
            // CLICK TAB (CI/CD Jenkins)
            // =========================================================
            safeClick(driver, wait, js,
                    By.xpath("//button[contains(text(),'Jenkins')]"),
                    "Jenkins Tab");

            Thread.sleep(2000);

            smoothScroll(js);

            // =========================================================
            // BACK TO RUNBOOK
            // =========================================================
            driver.navigate().back();
            Thread.sleep(3000);

            System.out.println("Back to Runbook");

            smoothScroll(js);

            // =========================================================
            // BACK TO HOME
            // =========================================================
            driver.get("https://moole.ai/");
            Thread.sleep(3000);

            System.out.println("Back to Home");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
    }

    // =========================================================
    // SAFE CLICK METHOD (FIXES STALE + VISIBILITY ISSUES)
    // =========================================================
    public static void safeClick(WebDriver driver, WebDriverWait wait,
                                 JavascriptExecutor js, By locator, String name) {

        try {
            WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            Thread.sleep(1000);

            highlight(js, el);

            js.executeScript("arguments[0].click();", el);

            System.out.println("Clicked: " + name);

        } catch (Exception e) {
            System.out.println("Failed: " + name + " -> " + e.getMessage());
        }
    }

    // =========================================================
    // HIGHLIGHT ELEMENT (RED BORDER CLICK VISUAL)
    // =========================================================
    public static void highlight(JavascriptExecutor js, WebElement el) {
        js.executeScript("arguments[0].style.border='3px solid red';", el);
    }

    // =========================================================
    // HUMAN-LIKE SCROLL
    // =========================================================
    public static void smoothScroll(JavascriptExecutor js) throws InterruptedException {
        for (int i = 0; i <= 3000; i += 300) {
            js.executeScript("window.scrollBy(0,300)");
            Thread.sleep(400);
        }
        for (int i = 0; i <= 3000; i += 300) {
            js.executeScript("window.scrollBy(0,-300)");
            Thread.sleep(400);
        }
    }
}