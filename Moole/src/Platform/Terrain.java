package Platform;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

public class Terrain {

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

            // ===== Click The Terrain =====
            WebElement terrain = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//span[contains(text(),'The Terrain')]")
                    )
            );
            js.executeScript("arguments[0].click();", terrain);
            System.out.println("Terrain opened");
            Thread.sleep(3000);

            // ===== Scroll little =====
            js.executeScript("window.scrollBy(0,300)");
            Thread.sleep(1500);

            // ===== Click "All" =====
            WebElement allBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[text()='All']]")
                    )
            );
            js.executeScript("arguments[0].click();", allBtn);
            System.out.println("Clicked All");
            Thread.sleep(2000);

            // ===== Click "Cloud Registries" =====
            WebElement cloudBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[text()='Cloud Registries']]")
                    )
            );
            js.executeScript("arguments[0].click();", cloudBtn);
            System.out.println("Clicked Cloud Registries");
            Thread.sleep(2000);

            // ===== Click "Container Registries" =====
            WebElement containerBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[text()='Container Registries']]")
                    )
            );
            js.executeScript("arguments[0].click();", containerBtn);
            System.out.println("Clicked Container Registries");
            Thread.sleep(2000);

            // ===== Scroll again =====
            js.executeScript("window.scrollBy(0,300)");
            Thread.sleep(1500);

            // ===== Click "Private Registries" =====
            WebElement privateBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[text()='Private Registries']]")
                    )
            );
            js.executeScript("arguments[0].click();", privateBtn);
            System.out.println("Clicked Private Registries");

            // ===== Final Scroll (user exploring) =====
            for (int i = 0; i < 5; i++) {
                js.executeScript("window.scrollBy(0,300)");
                Thread.sleep(500);
            }

            System.out.println("User explored page");

            Thread.sleep(3000);
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
