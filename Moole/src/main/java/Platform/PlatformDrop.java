package Platform;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

public class PlatformDrop {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        try {

            // =========================================================
            // OPEN WEBSITE
            // =========================================================
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();
            Thread.sleep(3000);
         // =========================================================
            // HANDLE POPUP
            // =========================================================
            try {
                WebElement ok = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'OK')]")));
                js.executeScript("arguments[0].click();", ok);
                System.out.println("Popup closed");
            } catch (Exception e) {
                System.out.println("No popup found");
            }

            Thread.sleep(2000);

            // =========================================================
            // PLATFORM HOVER
            // =========================================================
            WebElement platform = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//button[.//span[text()='Platform']]")
                    )
            );

            actions.moveToElement(platform).perform();
            Thread.sleep(1500);

            // =========================================================
            // CLICK SWITCHBOARD
            // =========================================================
            WebElement switchboard = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//span[contains(text(),'The Switchboard')]")
                    )
            );

            js.executeScript("arguments[0].click();", switchboard);
            System.out.println("Switchboard clicked");

            Thread.sleep(4000);

            // =========================================================
            // SCROLL PAGE
            // =========================================================
            scroll(js);

            // =========================================================
            // CATEGORY LIST
            // =========================================================
            List<WebElement> categories = driver.findElements(
                    By.xpath("//ul/li/button")
            );

            System.out.println("Total categories: " + categories.size());

            for (int i = 0; i < categories.size(); i++) {

                categories = driver.findElements(By.xpath("//ul/li/button"));
                WebElement cat = categories.get(i);

                String name = cat.getText();

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", cat);
                Thread.sleep(1000);

                js.executeScript("arguments[0].click();", cat);
                System.out.println("Clicked Category: " + name);

                Thread.sleep(2000);

                // =========================================================
                // SEARCH ACTIONS
                // =========================================================
                if (name.contains("Source Code Management")) {

                    search(driver, wait, js, "gitlab");

                } else if (name.contains("CI/CD Pipelines")) {

                    search(driver, wait, js, "gitlab");

                } else if (name.contains("Container Registries")) {

                    search(driver, wait, js, "nexus");

                } else {

                    System.out.println("No search required for: " + name);
                }

                Thread.sleep(2000);
            }

            // =========================================================
            // SCROLL FINAL
            // =========================================================
            scroll(js);

            // =========================================================
            // BACK TO HOME
            // =========================================================
            driver.get("https://moole.ai/");
            Thread.sleep(3000);

            System.out.println("Returned to Home Page");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
    }

    // =========================================================
    // SEARCH FUNCTION
    // =========================================================
    public static void search(WebDriver driver, WebDriverWait wait,
                              JavascriptExecutor js, String value) {

        try {

            WebElement searchBox = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@type='text' or @placeholder='Search']")
                    )
            );

            searchBox.clear();
            searchBox.sendKeys(value);
            searchBox.sendKeys(Keys.ENTER);

            System.out.println("Searched: " + value);

            Thread.sleep(3000);

        } catch (Exception e) {
            System.out.println("Search failed for: " + value);
        }
    }

    // =========================================================
    // SCROLL FUNCTION
    // =========================================================
    public static void scroll(JavascriptExecutor js) throws InterruptedException {

        for (int i = 0; i <= 2000; i += 300) {
            js.executeScript("window.scrollBy(0,300)");
            Thread.sleep(300);
        }

        for (int i = 0; i <= 2000; i += 300) {
            js.executeScript("window.scrollBy(0,-300)");
            Thread.sleep(300);
        }

        System.out.println("Scrolled page");
    }
}