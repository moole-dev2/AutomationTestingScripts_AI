package Company;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OperationsTheater {

	public static void main(String[] args) {

		 WebDriver driver = new ChromeDriver();
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        try {
	            // ---------------- Open Home Page ----------------
	            driver.get("https://moole.ai/");
	            driver.manage().window().maximize();
	            Thread.sleep(2000);
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
	            Thread.sleep(2000);

	            // ---------------- Click Operations Theater by Span ----------------
	            WebElement operationsTheater = wait.until(
	                    ExpectedConditions.elementToBeClickable(
	                            By.xpath("//span[text()='Adaptive Use Cases for the Frontline.']")
	                    )
	            );
	            js.executeScript("arguments[0].scrollIntoView({block:'center'});", operationsTheater);
	            operationsTheater.click();
	            System.out.println("Operations Theater clicked");
	            Thread.sleep(3000);

	            // ---------------- Scroll Slowly Like User ----------------
	            long pageHeight = (long) js.executeScript("return document.body.scrollHeight");
	            for (int i = 0; i < pageHeight; i += 300) {
	                js.executeScript("window.scrollBy(0,300)");
	                Thread.sleep(1000); // user reading pause
	            }
	            System.out.println("Reached bottom of Operations Theater page");
	            Thread.sleep(1000);

	            // ---------------- Scroll Back Up ----------------
	            for (int i = 0; i < pageHeight; i += 300) {
	                js.executeScript("window.scrollBy(0,-300)");
	                Thread.sleep(1000); // user reading pause
	            }
	            System.out.println("Scrolled back to top");

	            // ---------------- Return to Home ----------------
	            driver.navigate().back();
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
