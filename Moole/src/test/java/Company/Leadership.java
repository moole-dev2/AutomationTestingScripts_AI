package Company;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utils.ConfigReader;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;


public class Leadership {

	  @Test
	    public void verifyAboutPage() {


	WebDriver driver = new ChromeDriver();
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	
	        try {
	            // ---------------- Open Home Page ----------------
	        	driver.get(ConfigReader.getProperty("baseUrl"));
	        	driver.manage().window().maximize();
	        	wait.until(ExpectedConditions.jsReturnsValue(
	        	        "return document.readyState === 'complete'"
	        	));
	            // -------- Handle Privacy Popup --------
	            try {
	                wait.until(ExpectedConditions.elementToBeClickable(
	                        By.xpath("//button[text()='OK']"))).click();
	            } catch (Exception e) {
	                System.out.println("No popup");
	            }
	            
	            // ---------------- Click Company ----------------
	               WebElement companyBtn = wait.until(
	                       ExpectedConditions.elementToBeClickable(
	                               By.xpath("//button[.//span[text()='Company']]")
	                       )
	               );
	               companyBtn.click();
	               System.out.println("Company menu clicked");
	               wait.until(ExpectedConditions.visibilityOfElementLocated(
	            	        By.xpath("//a[@href='/company/leadership']")
	            	));
	               
	               WebElement leadership = wait.until(
	                   ExpectedConditions.elementToBeClickable(
	                       By.xpath("//a[@href='/company/leadership']")
	                   )
	               );

	               leadership.click();
	               wait.until(ExpectedConditions.urlContains("/company/leadership"));
	            

	            // ---------------- Scroll Slowly Like a Real User ----------------

	            // ---------------- Human-like Scroll Down ----------------

	               Actions actions = new Actions(driver);

	               long pageHeight = (long) js.executeScript(
	                       "return document.body.scrollHeight"
	               );

	               long viewportHeight = (long) js.executeScript(
	                       "return window.innerHeight"
	               );

	               for (long i = 0; i < pageHeight - viewportHeight; i += 300) {

	                   actions.scrollByAmount(0, 300)
	                          .pause(Duration.ofMillis(500))
	                          .perform();
	               }

	               System.out.println("Reached bottom of page");

	               // ---------------- Human-like Scroll Up ----------------

	               for (long i = 0; i < pageHeight - viewportHeight; i += 300) {

	                   actions.scrollByAmount(0, -300)
	                          .pause(Duration.ofMillis(500))
	                          .perform();
	               }

	               System.out.println("Scrolled back to top");
	            

	            // ---------------- Scroll to Meet the Team Button ----------------

	               WebElement meetTheTeam = wait.until(
	                       ExpectedConditions.presenceOfElementLocated(
	                               By.xpath("//a[@href='#team' and contains(.,'Meet the Team')]")
	                       )
	               );

	               // Scroll gradually until the button is visible
	               while (!meetTheTeam.isDisplayed()) {
	                   js.executeScript("window.scrollBy(0, 200);");
	                   Thread.sleep(300);
	               }

	               // Bring button to the center of the screen
	               js.executeScript(
	                       "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
	                       meetTheTeam
	               );

	               // Wait until button is clickable
	               wait.until(ExpectedConditions.elementToBeClickable(meetTheTeam));

	               // Click Meet the Team
	               meetTheTeam.click();

	               System.out.println("Meet the Team button clicked");

	               // Verify that the Team section is reached
	               wait.until(ExpectedConditions.urlContains("#team"));

	               System.out.println("Successfully navigated to Team section");

	               Thread.sleep(2000);
	            
	         // ---------------- Click SAST ----------------

	            WebElement sast = wait.until(
	                    ExpectedConditions.elementToBeClickable(
	                            By.xpath("//button[normalize-space()='SAST']")
	                    )
	            );

	            js.executeScript("arguments[0].scrollIntoView({block:'center'});", sast);
	            sast.click();

	            System.out.println("SAST button clicked");
	            Thread.sleep(1500);


	            // ---------------- Click SCA ----------------

	            WebElement sca = wait.until(
	                    ExpectedConditions.elementToBeClickable(
	                            By.xpath("//button[normalize-space()='SCA']")
	                    )
	            );

	            js.executeScript("arguments[0].scrollIntoView({block:'center'});", sca);
	            sca.click();

	            System.out.println("SCA button clicked");
	            Thread.sleep(1500);


	            // ---------------- Click IaC ----------------

	            WebElement iac = wait.until(
	                    ExpectedConditions.elementToBeClickable(
	                            By.xpath("//button[normalize-space()='IaC']")
	                    )
	            );

	            js.executeScript("arguments[0].scrollIntoView({block:'center'});", iac);
	            iac.click();

	            System.out.println("IaC button clicked");
	            Thread.sleep(1500);


	            // ---------------- Click Container ----------------

	            WebElement container = wait.until(
	                    ExpectedConditions.elementToBeClickable(
	                            By.xpath("//button[normalize-space()='Container']")
	                    )
	            );

	            js.executeScript("arguments[0].scrollIntoView({block:'center'});", container);
	            container.click();

	            System.out.println("Container button clicked");
	            Thread.sleep(1500);


	            // ---------------- Click DAST ----------------

	            WebElement dast = wait.until(
	                    ExpectedConditions.elementToBeClickable(
	                            By.xpath("//button[normalize-space()='DAST']")
	                    )
	            );

	            js.executeScript("arguments[0].scrollIntoView({block:'center'});", dast);
	            dast.click();

	            System.out.println("DAST button clicked");
	            Thread.sleep(1500);
	            
	         // ---------------- Scroll Slowly to Bottom ----------------

	            long pageHeight1 = (long) js.executeScript(
	                    "return document.body.scrollHeight"
	            );

	            for (int i = 0; i < pageHeight1; i += 300) {

	                js.executeScript("window.scrollBy(0,300)");

	                Thread.sleep(500);
	            }

	            System.out.println("Reached bottom of page");

	            Thread.sleep(2000);


	            // ---------------- Scroll Slowly Back to Top ----------------

	            for (int i = 0; i < pageHeight1; i += 300) {

	                js.executeScript("window.scrollBy(0,-300)");

	                Thread.sleep(500);
	            }

	            System.out.println("Scrolled back to top");

	            Thread.sleep(2000);


	         // ---------------- Return to Home Page ----------------

	            driver.get(ConfigReader.getProperty("baseUrl"));

	            wait.until(ExpectedConditions.urlToBe(
	                    ConfigReader.getProperty("baseUrl")
	            ));

	            System.out.println("Successfully returned to Home Page");
	            System.out.println("Returned to Home Page");

	            Thread.sleep(2000);

	        } catch (Exception e) {
	            System.out.println("Error: " + e.getMessage());
	        } finally {
	            driver.quit();
	            System.out.println("Browser closed");
	        }
}

}