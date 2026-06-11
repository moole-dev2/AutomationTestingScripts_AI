	package ProductsSection;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class npm {

	public static void main(String[] args) {

		 WebDriver driver = new ChromeDriver();
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	        try {
	            driver.get("https://moole.ai/");
	            driver.manage().window().maximize();

	            // STEP 1: Products
	            driver.findElement(By.xpath("//span[normalize-space()='Products']")).click();

	            // STEP 2: Vulnerability Database
	           WebElement vuln = wait.until(
	                    ExpectedConditions.elementToBeClickable(
	                            By.xpath("//span[normalize-space()='Vulnerability Database']")
	                    )
	            );
	            vuln.click();

	            // STEP 3: Click Analyze Active Risks
	            WebElement analyzeBtn = wait.until(
	                    ExpectedConditions.elementToBeClickable(
	                            By.xpath("//button[normalize-space()='Analyze Active Risks']")
	                    )
	            );

	            ((JavascriptExecutor) driver).executeScript(
	                    "arguments[0].scrollIntoView({block:'center'});", analyzeBtn);

	            analyzeBtn.click();

	            System.out.println("Analyze button clicked");

	            // ---------------- VALIDATION ----------------
	            
	            wait.until(ExpectedConditions.visibilityOfElementLocated(
	            	    By.xpath("//button[contains(.,'Analyze Active Risks')]")
	            	    
	            	));
	            
	            try {
	 	           Thread.sleep(5000);
	 	        } catch (InterruptedException e) {
	 	            e.printStackTrace();
	 	        }

	           
	            WebElement npm = driver.findElement(
	                    By.xpath("//a[@aria-label='npm']")
	            );

	            // Click the npm icon
	            npm.click();

	            System.out.println("npm icon clicked");

	            // Click npm
	            try {
	 	           Thread.sleep(5000);
	 	        } catch (InterruptedException e) {
	 	            e.printStackTrace();
	 	        }

	            System.out.println("npm link clicked");



	        } catch (Exception e) {
	            System.out.println("Error: " + e.getMessage());
	        } finally {
	            driver.quit();
	            System.out.println("Browser closed");
	        }
	}

}
