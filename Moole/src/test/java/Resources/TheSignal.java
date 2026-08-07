package Resources;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import Utils.ConfigReader;
import org.testng.annotations.Test;



public class TheSignal {

    @Test
    public void TheSignalTest() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        try {

            // =========================================================
            // OPEN HOME PAGE
            // =========================================================
        	driver.get(ConfigReader.getProperty("baseUrl"));
            driver.manage().window().maximize();
            Thread.sleep(3000);

            // =========================================================
            // CLOSE POPUP (SAFE)
            // =========================================================
            safeClick(driver, wait, js,
                    "//button[contains(text(),'OK')]",
                    "Privacy Popup");

            Thread.sleep(2000);

            // =========================================================
            // OPEN RESOURCES (HOVER + CLICK FIX)
            // =========================================================
            WebElement resources = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//span[normalize-space()='Resources']")));

            actions.moveToElement(resources).perform();
            Thread.sleep(1500);

            js.executeScript("arguments[0].click();", resources);
            System.out.println("Resources opened");

            Thread.sleep(3000);

            // =========================================================
            // CLICK THE SIGNAL (BLOGS)
            // =========================================================
            safeClick(driver, wait, js,
                    "//a[contains(@href,'/resources/blogs')]",
                    "The Signal");

            Thread.sleep(5000);

            scroll(js);

            // =========================================================
            // CLICK READ MORE (FIRST CARD)
            // =========================================================
            safeClick(driver, wait, js,
                    "(//button[contains(.,'Read More')])[1]",
                    "Read More");

            Thread.sleep(4000);

            scroll(js);

            // =========================================================
            // WHY IT MATTERS (FIXED LOCATOR)
            // =========================================================
            safeClick(driver, wait, js,
                    "//button[.//span[contains(.,'Why It Matters')]]",
                    "Why It Matters");

            Thread.sleep(3000);

            // =========================================================
            // SECOND READ MORE
            // =========================================================
            safeClick(driver, wait, js,
                    "(//button[contains(.,'Read More')])[last()]",
                    "Second Read More");

            Thread.sleep(4000);

            scroll(js);

            // =========================================================
            // NAVIGATE BACK STEPS SAFELY
            // =========================================================
            driver.navigate().back();
            Thread.sleep(4000);

            scroll(js);

            driver.navigate().back();
            Thread.sleep(4000);

            scroll(js);

            System.out.println("Returned to Home Page");

        } catch (Exception e) {
            System.out.println("GLOBAL ERROR: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
    }

    // =========================================================
    // SAFE CLICK METHOD (FIXES STALE + NOT VISIBLE ISSUES)
    // =========================================================
    public static void safeClick(WebDriver driver, WebDriverWait wait,
                                 JavascriptExecutor js, String xpath, String name) {

        try {
            WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            Thread.sleep(1000);

            js.executeScript("arguments[0].style.border='3px solid red';", el);

            js.executeScript("arguments[0].click();", el);

            System.out.println("Clicked: " + name);

        } catch (Exception e) {
            System.out.println("FAILED: " + name + " -> " + e.getMessage());
        }
    }

    // =========================================================
    // SCROLL FUNCTION
    // =========================================================
    public static void scroll(JavascriptExecutor js) throws InterruptedException {

        for (int i = 0; i <= 2000; i += 300) {
            js.executeScript("window.scrollBy(0,300)");
            Thread.sleep(400);
        }

        for (int i = 0; i <= 2000; i += 300) {
            js.executeScript("window.scrollBy(0,-300)");
            Thread.sleep(400);
        }

        System.out.println("Scrolled page");
    }
}