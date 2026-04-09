package Home;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class SubscriptionEmail {

	public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        try {
            // Open website
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();

            // Locate email input field
            WebElement emailField = driver.findElement(By.xpath("//input[@id='email-address']"));

            // Enter email
            emailField.sendKeys("testuser123@gmail.com");

            System.out.println("Email entered successfully");

            Thread.sleep(3000);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
	}

}
