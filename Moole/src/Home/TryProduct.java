package Home;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TryProduct {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();

            Thread.sleep(2000);

            // Click "Try Product For Free"
            WebElement tryBtn = driver.findElement(By.xpath("//a[contains(text(),'Try Product')]"));
            tryBtn.click();

            System.out.println("Clicked Try Product");

            Thread.sleep(3000);

            // Enter Email
            WebElement email = driver.findElement(By.xpath("//input[@type='email']"));
            email.sendKeys("testuser@example.com");

            System.out.println("Entered Email");

            Thread.sleep(2000);

            // Click Sign Up
            WebElement signUp = driver.findElement(By.xpath("//button[contains(text(),'Sign')]"));
            signUp.click();

            System.out.println("Clicked Sign Up");

            Thread.sleep(3000);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        } 
	       
	}

}
