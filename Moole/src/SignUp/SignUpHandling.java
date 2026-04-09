package SignUp;


import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

public class SignUpHandling {

	public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();

            Thread.sleep(2000);

            // Click Sign Up
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/auth/signup']")
            )).click();

            Thread.sleep(2000);

            // Enter Email
            WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@type='email']")
            ));

            email.sendKeys("testuser" + System.currentTimeMillis() + "@gmail.com");

            // Click Sign Up
            driver.findElement(By.xpath("//button[contains(text(),'Sign Up')]")).click();

            System.out.println("Sign Up clicked");

            // DEBUG
            System.out.println("Current URL: " + driver.getCurrentUrl());

            // Wait for OTP inputs (generic way)
            Thread.sleep(5000);

            java.util.List<WebElement> otpFields = driver.findElements(
                    By.xpath("//input[@type='text']")
            );

            if (otpFields.size() == 6) {

                for (int i = 0; i < otpFields.size(); i++) {
                    otpFields.get(i).sendKeys("1");
                }

                System.out.println("OTP entered");

            } else {
                System.out.println("OTP fields not found → Signup not successful");
            }

            Thread.sleep(5000);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
                 
	}
}
