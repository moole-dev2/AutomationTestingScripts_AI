package SignUp;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EnterpriseSignUp {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
 
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();

            // Click Sign In
            driver.get("https://moole.ai/auth/signin"); 
            try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
            // Click Sign Up
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/auth/signup']"))).click();
            try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

            // Click Enterprise button
            WebElement enterpriseBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[text()='Enterprise']"))
            );
            enterpriseBtn.click();

            // -------- Fill Email --------
            WebElement emailField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//label[contains(text(),'Email')]/following::input[1]"))
            );
            emailField.sendKeys("testuser13@example.com");
            try {
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

            // -------- Fill Organization Name --------
            WebElement orgField = driver.findElement(
                    By.xpath("//label[contains(text(),'Organization Name')]/following::input[1]")
            );
            orgField.sendKeys("Tst");
            try {
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

            // -------- Fill Domain Name --------
            WebElement domainField = driver.findElement(
                    By.xpath("//label[contains(text(),'Domain Name')]/following::input[1]")
            );
            domainField.sendKeys("tstd.com");
            try {
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

            // Click Sign Up button
            WebElement signUpBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@type='submit']"))
            );
            signUpBtn.click();

            System.out.println("Enterprise signup form submitted successfully.");
            try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

        } finally {
            // Always close browser
            driver.quit();
            System.out.println("Browser closed.");
        }
	}

}
