package ProductsSection;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

public class MavenClick {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // ---------- STEP 0: Open Website ----------
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();
            Thread.sleep(2000);

            // ---------- Handle Privacy Popup ----------
            try {
                WebElement okBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[text()='OK']")
                ));
                js.executeScript("arguments[0].click();", okBtn);
                System.out.println("Privacy popup closed");
            } catch (Exception e) {
                System.out.println("No popup");
            }

         // ---------- STEP 1: Hover Products ----------
            WebElement products = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[@href='/products']/span[text()='Products']")
            ));

            Actions actions = new Actions(driver);
            actions.moveToElement(products).perform();  // hover to reveal submenu
            System.out.println("Hovered on Products");

            // ---------- STEP 2: Click Vulnerability Database ----------
            WebElement vuln = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(text(),'Vulnerability Database') and contains(@class,'font-heading')]")
            ));

            // Scroll into view and click using JS (more reliable for React)
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", vuln);
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", vuln);

            System.out.println("Vulnerability Database clicked");
            Thread.sleep(2000);

            // ---------- STEP 3: Click Analyze Active Risks ----------
            WebElement analyzeBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Analyze Active Risks')]")
            ));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", analyzeBtn);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", analyzeBtn);
            System.out.println("Analyze Active Risks clicked");
            Thread.sleep(3000);

          

         // ---------------- STEP 5: Click Maven ----------------
         // ---------- STEP 5: Click Maven using aria-label ----------
            WebElement mavenLink = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@aria-label='maven']")
            ));

            // Scroll into view
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", mavenLink);

            // Safe click (normal + JS fallback)
            try {
                mavenLink.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", mavenLink);
            }

            System.out.println("Maven clicked");

            // ---------- STEP 6: Click CVE Link ----------
            WebElement cve = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(),'CVE-2025-11419')]")
            ));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", cve);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", cve);
            System.out.println("CVE-2025-11419 clicked");

            Thread.sleep(4000);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // driver.quit();
            System.out.println("Execution completed");
        }
    }
}