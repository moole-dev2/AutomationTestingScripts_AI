package SignUp;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BitBucketSignUp {

	public static void main(String[] args) {
	 WebDriver driver = new ChromeDriver();

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
	       
	       // ----------- Sign up with Bitbucket -----------
	       driver.findElement(By.xpath("//button[@aria-label='Sign up with Bitbucket']")).click();
	       try {
	           Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	       
	       // Close browser at the end
	       driver.quit();
	       }

}
