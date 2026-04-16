package Pricing;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

public class Individual {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // ---------- Open Website ----------
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();
            Thread.sleep(2000);

            // ---------- Handle Popup ----------
            try {
                WebElement okBtn = driver.findElement(By.xpath("//button[normalize-space()='OK']"));
                js.executeScript("arguments[0].click();", okBtn);
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("No popup");
            }

            // ---------- Click Pricing ----------
            WebElement pricing = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[@href='/pricing']")
                    )
            );
            js.executeScript("arguments[0].click();", pricing);
            System.out.println("Opened Pricing Page");
            Thread.sleep(3000);

            // ---------- Select Individual ----------
            WebElement individual = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//input[@value='INDIVIDUAL']")
                    )
            );
            js.executeScript("arguments[0].click();", individual);
            System.out.println("Selected Individual Plan");
            Thread.sleep(2000);
         // ---------- Scroll down to Plan Button ----------
            WebElement planBtn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("(//button[contains(.,'Get Started') or contains(.,'Start')])[1]")
                    )
            );
            js.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", planBtn);
            Thread.sleep(1000);

            // ---------- Click Plan Button ----------
            WebElement planBtn1 = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("(//button[contains(.,'Get Started') or contains(.,'Start')])[1]")
                    )
            );
            js.executeScript("arguments[0].click();", planBtn1);
            System.out.println("Clicked Plan Button");
            Thread.sleep(3000);

            // ---------- Enter Email ----------
            WebElement emailField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@type='email']")
                    )
            );
            emailField.sendKeys("moole.dev.2@gmail.com");
            System.out.println("Entered Email");
            Thread.sleep(1000);

            // ---------- Click Continue ----------
            WebElement continueBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(.,'Continue')]")
                    )
            );
            js.executeScript("arguments[0].click();", continueBtn);
            System.out.println("Clicked Continue");

            // ---------- OTP Step ----------
            System.out.println("Enter OTP manually...");
            Thread.sleep(20000); // wait for manual OTP entry

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
    }
}