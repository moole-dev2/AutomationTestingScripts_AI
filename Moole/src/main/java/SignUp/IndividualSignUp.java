package SignUp;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IndividualSignUp {

	public static void main(String[] args) {
		
		 WebDriver driver = new ChromeDriver();
	       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	        driver.get("https://moole.ai/");
	        driver.manage().window().maximize();
	        
	        // Click on Sign In button
	        driver.get("https://moole.ai/auth/signin"); 	        
	        try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	        // Click on Sign Un button
	       driver.findElement(By.xpath("//a[@href='/auth/signup']")).click();
	       try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	       //EmailField
	       WebElement emailField = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']"))
	        );
	        emailField.sendKeys("testuser123@example.com");

	        // Click on Continue / Submit button
	        WebElement continueBtn = driver.findElement(By.xpath("//button[@type='submit']"));
	        continueBtn.click();
	        
	        try {
		           Thread.sleep(5000);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		       
		       // Close browser at the end
		       driver.quit();
	}

}
