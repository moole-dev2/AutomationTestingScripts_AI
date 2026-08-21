package SignIn;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.ConfigReader;
import org.testng.annotations.Test;



public class SignIn {

    @Test
    public void SignInTest() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    	driver.get(ConfigReader.getProperty("baseUrl"));
        driver.manage().window().maximize();

        driver.get("https://moole.ai/auth/signin"); 
       try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // -------- Enter Email --------
        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@type='email']")
                )
        );
        emailField.sendKeys("moole.dev.2@gmail.com");
        
        // -------- Click Continue --------
        WebElement signIn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@data-tour='signup-submit']")
                )
        );

        signIn.click();

        System.out.println("Sign in button clicked");
        driver.quit();
    }
}
