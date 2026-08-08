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
            WebElement devTestFAQ = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//h3[text()='How do you map Dev/Test/Staging/Prod?']/ancestor::button")));
            clickVisible(driver, js, devTestFAQ);
            System.out.println("Clicked FAQ: Dev/Test/Staging/Prod");
            Thread.sleep(1000);

            // -------- 6. FAQ 2: "How quickly can we onboard..." --------
            WebElement onboardingFAQ = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//h3[contains(text(),'How quickly can we onboard')]/ancestor::button")));
            clickVisible(driver, js, onboardingFAQ);
            System.out.println("Clicked FAQ: How quickly can we onboard");
            Thread.sleep(1000);

            // -------- 7. Static Application Security Testing --------
            WebElement staticSAST = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[text()='Static Application Security Testing']")));
            clickVisible(driver, js, staticSAST);
            System.out.println("Clicked Static Application Security Testing");
            Thread.sleep(1000);

            // -------- 8. FAQ under SAST --------
            WebElement sastFAQ = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//h3[contains(text(),'Will Moole SAST slow down our CI/CD pipelines')]/ancestor::button")));
            clickVisible(driver, js, sastFAQ);
            System.out.println("Clicked FAQ: Will Moole SAST slow down CI/CD");
            Thread.sleep(1000);

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

            // -------- 12. Navigate back to Home --------
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