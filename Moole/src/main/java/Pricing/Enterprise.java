package Pricing;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

public class Enterprise {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // -------- Open Website --------
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();
            Thread.sleep(2000);

            // -------- Handle Privacy Popup --------
            try {
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[text()='OK']"))).click();
            } catch (Exception e) {
                System.out.println("No popup");
                
            }

            // -------- Click Pricing --------
            WebElement pricing = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/pricing']")));
            pricing.click();
            Thread.sleep(2000);

            // scroll DOWN slowly
            for (int i = 0; i < 8; i++) {
                js.executeScript("window.scrollBy(0,300)");
                Thread.sleep(700);
            }

            // pause like user reading
            Thread.sleep(1500);

            // scroll UP slowly
            for (int i = 0; i < 8; i++) {
                js.executeScript("window.scrollBy(0,-300)");
                Thread.sleep(700);
            }
            // -------- Click Label[2] --------
            WebElement label = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//label)[2]")));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", label);
            js.executeScript("arguments[0].click();", label);
            Thread.sleep(2000);
            

            // scroll DOWN slowly
            for (int i = 0; i < 2; i++) {
                js.executeScript("window.scrollBy(0,300)");
                Thread.sleep(700);
            }

            // -------- Click Contact Sales --------
            WebElement contactSales = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(.,'Contact Sales')]")));
            js.executeScript("arguments[0].click();", contactSales);
            Thread.sleep(3000);

           
         // Email 
            WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//input[@type='email' or contains(@placeholder,'Email')]")
            ));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", email);
            wait.until(ExpectedConditions.elementToBeClickable(email));
            email.clear();
            email.sendKeys("tayas18348@algarr.com");

         // First Name
            WebElement firstName = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//input[@name='firstName' or @placeholder='First Name' or contains(@placeholder,'First')]")
            ));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", firstName);
            wait.until(ExpectedConditions.elementToBeClickable(firstName));
            firstName.clear();
            firstName.sendKeys("john");

            // Last Name 
            WebElement lastName = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//input[@name='lastName' or @placeholder='Last Name' or contains(@placeholder,'Last')]")
            ));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", lastName);
            wait.until(ExpectedConditions.elementToBeClickable(lastName));
            lastName.clear();
            lastName.sendKeys("lee");

            System.out.println("Entered form details");

            Thread.sleep(2000);

            // -------- Click CNAPP --------
            WebElement cnapp = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[.//span[text()='CNAPP']]")));
            js.executeScript("arguments[0].click();", cnapp);

            System.out.println("Selected CNAPP");

            Thread.sleep(2000);

            // -------- Click Let's Connect --------
            WebElement letsConnect = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@type='submit' and contains(.,'Lets Connect')]")));
            js.executeScript("arguments[0].click();", letsConnect);

            System.out.println("Clicked Let's Connect");

            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           // driver.quit();
            System.out.println("Browser closed");
        }
    }
}