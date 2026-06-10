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

            // -------- Scroll Down --------
            js.executeScript("window.scrollTo(0, document.body.scrollHeight * 0.6)");
            Thread.sleep(2000);

            // -------- Click Label[2] --------
            WebElement label = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//label)[2]")));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", label);
            js.executeScript("arguments[0].click();", label);
            Thread.sleep(2000);

            // -------- Click Contact Sales --------
            WebElement contactSales = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(.,'Contact Sales')]")));
            js.executeScript("arguments[0].click();", contactSales);
            Thread.sleep(3000);

            // -------- Fill Form Fields --------

            // Email
            WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("(//input[contains(@class,'col-start-1')])[1]")));
            email.sendKeys("tayas18348@algarr.com");

            // First Name
            WebElement firstName = driver.findElement(
                    By.xpath("(//input[contains(@class,'col-start-1')])[2]"));
            firstName.sendKeys("john");

            // Last Name
            WebElement lastName = driver.findElement(
                    By.xpath("(//input[contains(@class,'col-start-1')])[3]"));
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