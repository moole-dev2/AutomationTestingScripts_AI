package Company;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Utils.ConfigReader;

public class Contact {

    @Test
    public void contactTest() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(20));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {

            // -------- Open Website --------
            driver.get(ConfigReader.getProperty("baseUrl"));

            driver.manage().window().maximize();

            Thread.sleep(2000);

            // -------- Handle Privacy Popup --------
            try {

                WebElement okButton = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[normalize-space()='OK']")));

                okButton.click();

                System.out.println("Privacy popup closed");

            } catch (Exception e) {

                System.out.println("No privacy popup found");

            }
            
         // ---------------- Click Company ----------------
            WebElement companyBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[text()='Company']]")
                    )
            );
            companyBtn.click();
            System.out.println("Company menu clicked");
            Thread.sleep(2000);

            // -------- Click Contact Us --------
            WebElement contactUs = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//span[normalize-space()='Contact Us']")));

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    contactUs);

            Thread.sleep(1000);

            contactUs.click();

            System.out.println("Clicked Contact Us");

            Thread.sleep(2000);

            // -------- Scroll Down Slowly --------
            long height = (long) js.executeScript(
                    "return document.body.scrollHeight");

            for (int i = 0; i < height; i += 400) {

                js.executeScript("window.scrollBy(0,400)");

                Thread.sleep(500);
            }

            System.out.println("Scrolled to bottom");

            // -------- Scroll Up --------
            for (int i = 0; i < height; i += 400) {

                js.executeScript("window.scrollBy(0,-400)");

                Thread.sleep(500);
            }

            System.out.println("Scrolled back to top");

            // -------- Locate Google Maps iframe --------
            WebElement mapFrame = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//iframe[contains(@src,'google.com/maps')]")));

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    mapFrame);

            Thread.sleep(2000);

            System.out.println("Google Maps iframe found");

            // -------- Capture Parent Window --------
            String parentWindow = driver.getWindowHandle();

            // -------- Switch to Map iframe --------
            driver.switchTo().frame(mapFrame);

            System.out.println("Inside Google Maps iframe");

            // -------- Find Map Link --------
            WebElement mapLink = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a")));

            mapLink.click();

            System.out.println("Clicked map link");

            Thread.sleep(3000);

            // -------- Get All Windows --------
            Set<String> allWindows = driver.getWindowHandles();

            // -------- Switch to New Tab --------
            for (String window : allWindows) {

                if (!window.equals(parentWindow)) {

                    driver.switchTo().window(window);

                    break;
                }
            }

            System.out.println("Switched to new tab");

            Thread.sleep(3000);

            // -------- Validate Google Maps --------
            String currentUrl = driver.getCurrentUrl();

            System.out.println("Current URL: " + currentUrl);

            if (currentUrl.contains("google.com/maps")) {

                System.out.println(
                        "Google Maps opened successfully");

            } else {

                System.out.println(
                        "Google Maps URL not detected");

            }

            Thread.sleep(2000);

            // -------- Close New Tab --------
            driver.close();

            System.out.println("New tab closed");

            // -------- Switch Back to Parent --------
            driver.switchTo().window(parentWindow);

            // -------- Exit iframe --------
            driver.switchTo().defaultContent();

            System.out.println(
                    "Returned to Contact Us page");

        } catch (Exception e) {

            System.out.println(
                    "Contact test failed: " + e.getMessage());

            e.printStackTrace();

        } finally {

            driver.quit();

            System.out.println("Browser closed");
        }
    }
}