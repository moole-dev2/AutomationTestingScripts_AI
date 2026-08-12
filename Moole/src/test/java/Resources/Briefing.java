package Resources;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import Utils.ConfigReader;
import org.testng.annotations.Test;



public class Briefing {

    @Test
    public void BriefingTest() throws InterruptedException {

    	 WebDriver driver = new ChromeDriver();
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
         JavascriptExecutor js = (JavascriptExecutor) driver;

         try {
             // -------- Open Website --------
	        	driver.get(ConfigReader.getProperty("baseUrl"));
             driver.manage().window().maximize();
             Thread.sleep(2000);
             // -------- Handle Privacy Popup --------
	            try {
	                wait.until(ExpectedConditions.elementToBeClickable(
	                        By.xpath("//button[text()='OK']"))).click();
	            } catch (Exception e) {
	                System.out.println("No popup");
	            }

             // -------- Click Resources --------
             WebElement resources = wait.until(ExpectedConditions.elementToBeClickable(
                     By.xpath("//span[normalize-space()='Resources']")));
             resources.click();
             Thread.sleep(2000);

            // -------- 4. Click "The Briefing" --------
            WebElement theBriefingSpan = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//span[text()='The Briefing']")));
            clickVisible(driver, js, theBriefingSpan);
            System.out.println("Clicked 'The Briefing'");
            Thread.sleep(2000);

            // -------- 5. FAQ 1: "How do you map Dev/Test/Staging/Prod?" --------
         // =========================================================
         // FAQ: How do you map Dev/Test/Staging/Prod?
         // =========================================================

         System.out.println("Looking for Dev/Test/Staging/Prod FAQ...");

         WebElement devTestFAQ = wait.until(
                 ExpectedConditions.presenceOfElementLocated(
                         By.xpath("//button[contains(., 'How do you map Dev/Test/Staging/Prod')]")
                 )
         );

         System.out.println("Found Dev/Test/Staging/Prod FAQ");

         scrollToElementWithOffset(js, devTestFAQ, -100);

         Thread.sleep(1000);

         js.executeScript(
                 "arguments[0].click();",
                 devTestFAQ
         );

         System.out.println("Clicked FAQ: Dev/Test/Staging/Prod");

         Thread.sleep(1500);

            // -------- 6. FAQ 2: "How quickly can we onboard..." --------
         // =========================================================
         // FAQ: What about permissions and data security?
         // =========================================================

         WebElement dataSecurityFAQ = wait.until(
                 ExpectedConditions.presenceOfElementLocated(
                         By.xpath("//button[contains(., 'What about permissions and data security?')]")
                 )
         );

         System.out.println("Found Data Security FAQ");

         scrollToElementWithOffset(js, dataSecurityFAQ, -100);

         Thread.sleep(1000);

         js.executeScript(
                 "arguments[0].click();",
                 dataSecurityFAQ
         );

         System.out.println("Clicked FAQ: What about permissions and data security?");

         Thread.sleep(1500);

            // -------- 7. Static Application Security Testing --------
            WebElement staticSAST = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[text()='Static Application Security Testing']")));
            clickVisible(driver, js, staticSAST);
            System.out.println("Clicked Static Application Security Testing");
            Thread.sleep(1000);

            // -------- 8. FAQ under SAST --------
            WebElement sastFAQ = wait.until(
            	    ExpectedConditions.elementToBeClickable(
            	        By.xpath("//button[.//h6[contains(normalize-space(), 'Will Moole SAST slow down our CI/CD pipelines?')]]")
            	    )
            	);

            	scrollToElementWithOffset(js, sastFAQ, -100);

            	Thread.sleep(1000);

            	js.executeScript("arguments[0].click();", sastFAQ);

            	System.out.println("Clicked FAQ: Will Moole SAST slow down our CI/CD pipelines?");

            // -------- 9. Container Security --------
            WebElement containerSec = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[text()='Container Security']")));
            clickVisible(driver, js, containerSec);
            System.out.println("Clicked Container Security");
            Thread.sleep(1000);

            // -------- 10. Vulnerability Database --------
            WebElement vulnDB = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[text()='Vulnerability Database']")));
            clickVisible(driver, js, vulnDB);
            System.out.println("Clicked Vulnerability Database");
            Thread.sleep(1000);

            // -------- 11. Navigate back to Resources --------
            driver.navigate().back();
            Thread.sleep(2000);
          
            System.out.println("Returned to Home Page");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
    }

    // ------------------ Helper Methods ------------------

    // Click element after scrolling it into view + a small offset for visibility
    public static void clickVisible(WebDriver driver, JavascriptExecutor js, WebElement element) throws InterruptedException {
        scrollToElementWithOffset(js, element, -100); // scroll slightly above to see full element
        Thread.sleep(2000); // small wait to simulate reading
        js.executeScript("arguments[0].click();", element);
        Thread.sleep(2000); // wait after click
        scrollDownUp(js); // simulate user reading page
    }

    // Scroll element into view with offset
    public static void scrollToElementWithOffset(JavascriptExecutor js, WebElement element, int offset) {
        js.executeScript("var rect = arguments[0].getBoundingClientRect();" +
                         "window.scrollBy(0, rect.top + " + offset + ");", element);
    }

    // Scroll down and up to simulate user reading
    public static void scrollDownUp(JavascriptExecutor js) throws InterruptedException {
        js.executeScript("window.scrollBy(0, 300)"); // scroll down
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0, -300)"); // scroll up
        Thread.sleep(2000);
    }
}