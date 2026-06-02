package Home;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FooterLinksTest {

    public static void main(String[] args) throws Exception {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {

            // =========================================================
            // OPEN HOME PAGE
            // =========================================================
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();

            Thread.sleep(4000);
            System.out.println("Homepage opened");

            // =========================================================
            // CLOSE POPUP (SAFE)
            // =========================================================
            try {
                WebElement okBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'OK')]")));
                js.executeScript("arguments[0].click();", okBtn);
                System.out.println("Popup closed");
            } catch (Exception e) {
                System.out.println("No popup found");
            }

            // =========================================================
            // SCROLL TO FOOTER (IMPORTANT FIX)
            // =========================================================
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(3000);

            // =========================================================
            // GET ALL COMPANY FOOTER LINKS
            // =========================================================
            List<WebElement> companyLinks = driver.findElements(
                    By.xpath("//footer//a"));

            System.out.println("Total footer links found: " + companyLinks.size());

            // =========================================================
            // LOOP THROUGH LINKS SAFELY
            // =========================================================
            for (int i = 0; i < companyLinks.size(); i++) {

                try {

                    // REFRESH ELEMENT LIST (FIXES STALE ELEMENT ISSUE)
                    companyLinks = driver.findElements(By.xpath("//footer//a"));

                    WebElement link = companyLinks.get(i);

                    String name = link.getText().trim();

                    if (name.isEmpty()) {
                        continue;
                    }

                    System.out.println("\nClicked: " + name);

                    // SCROLL INTO VIEW
                    js.executeScript(
                            "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
                            link);

                    Thread.sleep(1500);

                    // HIGHLIGHT IN RED BEFORE CLICK
                    js.executeScript(
                            "arguments[0].style.border='4px solid red';",
                            link);

                    Thread.sleep(1000);

                    // CLICK (JS CLICK FOR STABILITY)
                    js.executeScript("arguments[0].click();", link);

                    Thread.sleep(4000);

                    System.out.println("Navigated URL: " + driver.getCurrentUrl());

                    // =========================================================
                    // SCROLL INSIDE PAGE
                    // =========================================================
                    for (int j = 0; j <= 2500; j += 300) {
                        js.executeScript("window.scrollTo(0," + j + ")");
                        Thread.sleep(400);
                    }

                    for (int j = 2500; j >= 0; j -= 300) {
                        js.executeScript("window.scrollTo(0," + j + ")");
                        Thread.sleep(400);
                    }

                    // =========================================================
                    // BACK TO HOME
                    // =========================================================
                    driver.navigate().back();
                    Thread.sleep(5000);

                    // SCROLL AGAIN TO FOOTER
                    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                    Thread.sleep(3000);

                } catch (Exception e) {

                    System.out.println("FAILED LINK: " + e.getMessage());

                    driver.get("https://moole.ai/");
                    Thread.sleep(4000);

                    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                    Thread.sleep(3000);
                }
            }

            System.out.println("\nALL FOOTER COMPANY LINKS TEST COMPLETED");

        } catch (Exception e) {

            System.out.println("GLOBAL ERROR: " + e.getMessage());

        } finally {

            driver.quit();
            System.out.println("Browser closed");
        }
    }
}