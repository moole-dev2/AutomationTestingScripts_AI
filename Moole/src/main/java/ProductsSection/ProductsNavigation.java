package ProductsSection;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.ConfigReader;

import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsNavigation {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        try {
            // ---------- Open Home Page ----------
        	driver.get(ConfigReader.getProperty("baseUrl"));
            driver.manage().window().maximize();
            Thread.sleep(2000);

            // ---------- Handle Privacy Popup ----------
            try {
                WebElement okBtn = driver.findElement(By.xpath("//button[normalize-space()='OK']"));
                js.executeScript("arguments[0].click();", okBtn);
                Thread.sleep(1000);
                System.out.println("Privacy popup closed");
            } catch (Exception e) {
                System.out.println("No popup present");
            }

            // ---------- Products submenu links ----------
            String[][] products = {
                    {"SCA", "//a[normalize-space()='SCA']"},
                    {"Container Security", "//a[normalize-space()='Container Security']"},
                    {"SAST", "//a[normalize-space()='SAST']"},
                    {"Vulnerability Database",  "//a[contains(@href,'vulnerability-database')]"}
            };

            for (String[] product : products) {
                String name = product[0];
                String xpath = product[1];

                // Hover Products each time before clicking submenu
                WebElement productsMenu = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//span[normalize-space()='Products']"))
                );
                actions.moveToElement(productsMenu).perform();
                Thread.sleep(500);

                // Re-locate submenu element (prevents stale element)
                WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

                // Click using JS for reliability
                js.executeScript("arguments[0].click();", productLink);
                System.out.println("Opened " + name);
                Thread.sleep(2000);

                // Scroll page
                long pageHeight = (long) js.executeScript("return document.body.scrollHeight");
                for (int i = 0; i < pageHeight; i += 300) {
                    js.executeScript("window.scrollBy(0,300)");
                    Thread.sleep(500);
                }
                System.out.println("Scrolled bottom of " + name);

            }

            // ---------- Return to Home ----------
            driver.get("https://moole.ai/");
            Thread.sleep(2000);
            System.out.println("Back to Home Page");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
    }
}