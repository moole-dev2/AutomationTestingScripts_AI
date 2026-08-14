package Home;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utils.ConfigReader;
import org.testng.annotations.Test;



public class SubscriptionEmail {

    @Test
    public void SubscriptionEmailTest() throws InterruptedException {



        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            // Open website
        	driver.get(ConfigReader.getProperty("baseUrl"));
            driver.manage().window().maximize();
            
            // =========================
            // POPUP HANDLING
            // =========================
            try {
                WebElement ok = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'OK')]")));
                js.executeScript("arguments[0].click();", ok);
            } catch (Exception ignored) {}


            // Locate email input field
            WebElement emailField = driver.findElement(By.xpath("//input[@id='email-address']"));

            // Enter email
            emailField.sendKeys("testuser123@gmail.com");

            System.out.println("Email entered successfully");

            Thread.sleep(3000);
            
            WebElement subscribeBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@type='submit' and text()='Subscribe']")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", subscribeBtn);

            js.executeScript("arguments[0].click();", subscribeBtn);

            System.out.println("Clicked Subscribe Button");
            Thread.sleep(5000);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
	}

}
