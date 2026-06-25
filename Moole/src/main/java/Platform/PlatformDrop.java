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
            Thread.sleep(2000);

            // =========================================================
            // HANDLE POPUP
            // =========================================================
            try {
                WebElement ok = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[contains(text(),'OK')]")
                        )
                );
                js.executeScript("arguments[0].click();", ok);
                System.out.println("Popup closed");
            } catch (Exception e) {
                System.out.println("No popup found");
            }

            Thread.sleep(1500);

            // =========================================================
            // PLATFORM HOVER
            // =========================================================
            WebElement platform = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//button[.//span[text()='Platform']]")
                    )
            );

            actions.moveToElement(platform).perform();
            Thread.sleep(1000);

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

            Thread.sleep(2500);

            // =========================================================
            // SCROLL PAGE
            // =========================================================
            scroll(js);

            /// ===================== MAIN LOOP =====================
            int index = 0;

            while (true) {

                List<WebElement> categories =
                        driver.findElements(By.xpath("//ul/li/button"));

                if (index >= categories.size()) break;

                WebElement cat = categories.get(index);
                String name = cat.getText().trim();

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", cat);
                Thread.sleep(800);

                js.executeScript("arguments[0].click();", cat);

                System.out.println("Clicked Category: " + name);

                Thread.sleep(2000); // wait UI refresh

              // ===================== SEARCH LOGIC =====================
                String searchValue = null;

                if (name.toLowerCase().contains("source code management") ||
                        name.toLowerCase().contains("nexus")) {
                    searchValue = "Nexus";

                } else if (name.toLowerCase().contains("ci") ||
                           name.toLowerCase().contains("jenkins")) {
                    searchValue = "jenkins";

                } else if (name.toLowerCase().contains("container") ||
                           name.toLowerCase().contains("nexus")) {
                    searchValue = "nexus";
                }

                if (searchValue != null) {
                    search(driver, wait, js, searchValue);
                } else {
                    System.out.println("No search needed for: " + name);
                }

                Thread.sleep(1500);

                index++;
            }

            System.out.println("Completed all categories");

            // ---------------- Return to Home ----------------
            driver.navigate().back();
            System.out.println("Returned to Home Page");
            Thread.sleep(1000);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
    }
    // =========================================================
    // SEARCH FUNCTION (FIXED + RELIABLE)
    // =========================================================
    public static void search(WebDriver driver, WebDriverWait wait,
            JavascriptExecutor js, String value) {

        try {

            WebElement searchBox = wait.until(d -> {
                List<WebElement> matches = d.findElements(
                        By.xpath("//input[@placeholder='Search Integrations']"));
                for (WebElement el : matches) {
                    if (el.isDisplayed()) {
                        return el;
                    }
                }
                return null;
            });

            // Scroll into view
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", searchBox);
            Thread.sleep(800);

            // IMPORTANT: click via JS (avoids overlay issue)
            js.executeScript("arguments[0].click();", searchBox);
            Thread.sleep(500);

            // clear properly
            searchBox.sendKeys(Keys.CONTROL + "a");
            searchBox.sendKeys(Keys.BACK_SPACE);

            Thread.sleep(500);

            // type slowly (prevents React input issues)
            for (char c : value.toCharArray()) {
                searchBox.sendKeys(String.valueOf(c));
                Thread.sleep(150);
            }

            Thread.sleep(800);

            searchBox.sendKeys(Keys.ENTER);

            System.out.println("Searched: " + value);

            Thread.sleep(2500);

            // Clear the search box before moving to the next category, so leftover
            // text doesn't carry over and affect filtering on the next category panel.
            searchBox.sendKeys(Keys.CONTROL + "a");
            searchBox.sendKeys(Keys.BACK_SPACE);

            String remaining = searchBox.getAttribute("value");
            if (remaining != null && !remaining.isEmpty()) {
                js.executeScript(
                        "var el=arguments[0]; el.value=''; " +
                        "el.dispatchEvent(new Event('input',{bubbles:true}));",
                        searchBox);
            }

            System.out.println("Cleared search box after: " + value);

            Thread.sleep(500);

        } catch (Exception e) {
            System.out.println("Search failed for: " + value);
            System.out.println("Reason: " + e.getMessage());
        }
    }
    // =========================================================
    // SCROLL FUNCTION (OPTIMIZED)
    // =========================================================
    public static void scroll(JavascriptExecutor js) throws InterruptedException {

        for (int i = 0; i <= 1500; i += 300) {
            js.executeScript("window.scrollBy(0,300)");
            Thread.sleep(200);
        }

        for (int i = 0; i <= 1500; i += 300) {
            js.executeScript("window.scrollBy(0,-300)");
            Thread.sleep(200);
        }

        System.out.println("Scrolled page");
    }
}