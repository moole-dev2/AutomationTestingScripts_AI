package Home;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GetDemo {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;


        try {
           driver.get("https://moole.ai/");
            driver.manage().window().maximize();
            
            // ---------------- Handle Privacy Popup ----------------

            WebElement privacyOk = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[contains(@class,'rounded-sm bg-indigo') and text()='OK']")));
            js.executeScript("arguments[0].click();", privacyOk);
            System.out.println("Clicked Privacy OK button");
            Thread.sleep(1000);

            //Click "Get a Demo"
            List<WebElement> demoButtons = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(
                            By.xpath("//a[normalize-space()='Get a Demo']")
                    )
            );

            WebElement demoBtn = demoButtons.get(0);

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", demoBtn);
            Thread.sleep(1500);

            try {
                demoBtn.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", demoBtn);
            }

            System.out.println("Clicked Get a Demo");

            //Wait for page
            wait.until(ExpectedConditions.urlContains("demo"));

         // Select SAST (FIXED)
            WebElement sastOption = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//button[.//span[text()='SAST']]")
                    )
            );

            // Scroll to center (important)
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", sastOption);

            // Small wait for UI animation
            Thread.sleep(1500);

            // Use JS click (bypass interception issue)
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", sastOption);

            System.out.println("SAST selected");

            // Fill Business Email (1st input)
            WebElement email = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("(//input)[1]")
                    )
            );
            email.sendKeys("moole.dev.2@gmail.com");

            // Fill First Name (2nd input)
            WebElement firstName = driver.findElement(By.xpath("(//input)[2]"));
            firstName.sendKeys("John");

            //Fill Last Name (3rd input)
            WebElement lastName = driver.findElement(By.xpath("(//input)[3]"));
            lastName.sendKeys("Lee");

            System.out.println("Filled form fields");
            js.executeScript("window.scrollBy(0,300)");

            // Click "Let's Connect"
            WebElement connectBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(.,'Lets Connect')]")
                    )
            );

            js.executeScript("arguments[0].click();", connectBtn);

            System.out.println("Clicked Let's Connect");

            Thread.sleep(3000);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
	}

}
