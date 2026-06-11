package SignUp;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignUpButton{

	public static void main(String[] args) {
	
		 WebDriver driver = new ChromeDriver();
	       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	        driver.get("https://moole.ai/");
	        driver.manage().window().maximize();
	        
	        // Click on Sign In button
	        driver.get("https://moole.ai/auth/signin"); 
	        
	        try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	        // Click on Sign Up button
	       driver.findElement(By.xpath("//a[@href='/auth/signup']")).click();
	       try {
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    // -------- Enter Email --------
	        WebElement emailField = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                        By.xpath("//input[@type='email']")
	                )
	        );
	        emailField.sendKeys("socoti3073@muncloud.com");
	        
	        // -------- Click Continue --------
	        WebElement SignUpBtn = wait.until(
	                ExpectedConditions.elementToBeClickable(
	                        By.xpath("//button[contains(text(),'Sign Up')]")
	                )
	        );
	       SignUpBtn.click();
	       
	}

}
