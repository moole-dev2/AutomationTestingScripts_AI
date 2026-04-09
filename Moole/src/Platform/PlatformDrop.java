package Platform;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

public class PlatformDrop {

	public static void main(String[] args) {
		 WebDriver driver = new ChromeDriver();
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        Actions actions = new Actions(driver);
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        try {
	            driver.get("https://moole.ai/");
	            driver.manage().window().maximize();

	            // ===== Hover Platform =====
	            WebElement platform = wait.until(
	                    ExpectedConditions.presenceOfElementLocated(
	                            By.xpath("//button[.//span[text()='Platform']]")
	                    )
	            );

	            actions.moveToElement(platform).perform();
	            Thread.sleep(2000);

	            // ===== Click Switchboard =====
	            WebElement switchboard = wait.until(
	                    ExpectedConditions.presenceOfElementLocated(
	                            By.xpath("//span[contains(text(),'The Switchboard')]")
	                    )
	            );

	            js.executeScript("arguments[0].click();", switchboard);
	            System.out.println("Switchboard clicked");

	            Thread.sleep(3000);

	            // ===== Scroll Down =====
	            long height = (long) js.executeScript("return document.body.scrollHeight");

	            for (int i = 0; i < height; i += 300) {
	                js.executeScript("window.scrollBy(0,300)");
	                Thread.sleep(500);
	            }

	            System.out.println("Scrolled down");

	            // ===== Scroll Up =====
	            for (int i = 0; i < height; i += 300) {
	                js.executeScript("window.scrollBy(0,-300)");
	                Thread.sleep(500);
	            }

	            System.out.println("Scrolled up");

	            Thread.sleep(2000);

	            // ===== Back to Home =====
	            driver.navigate().back();
	            Thread.sleep(2000);

	            // ===== Hover Platform again =====
	            WebElement platform2 = wait.until(
	                    ExpectedConditions.presenceOfElementLocated(
	                            By.xpath("//button[.//span[text()='Platform']]")
	                    )
	            );

	            actions.moveToElement(platform2).perform();
	            Thread.sleep(2000);

	            // ===== Click The Terrain =====
	            WebElement terrain = wait.until(
	                    ExpectedConditions.presenceOfElementLocated(
	                            By.xpath("//span[contains(text(),'The Terrain')]")
	                    )
	            );

	            js.executeScript("arguments[0].click();", terrain);
	            System.out.println("The Terrain clicked");

	            Thread.sleep(3000);

	            // ===== Scroll Down =====
	            long height2 = (long) js.executeScript("return document.body.scrollHeight");

	            for (int i = 0; i < height2; i += 300) {
	                js.executeScript("window.scrollBy(0,300)");
	                Thread.sleep(500);
	            }

	            System.out.println("Scrolled down (Terrain)");

	            // ===== Scroll Up =====
	            for (int i = 0; i < height2; i += 300) {
	                js.executeScript("window.scrollBy(0,-300)");
	                Thread.sleep(500);
	            }

	            System.out.println("Scrolled up (Terrain)");

	            Thread.sleep(2000);

	            // ===== Back to Home =====
	            driver.navigate().back();

	            System.out.println("Returned to Home Page");

	            Thread.sleep(3000);

	        } catch (Exception e) {
	            System.out.println("Error: " + e.getMessage());
	        } finally {
	            driver.quit();
	            System.out.println("Browser closed");
	        }
	}

}
