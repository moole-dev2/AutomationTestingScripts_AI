package ProductsSection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ZeroDayLink {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            // Open website
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();

            // Scroll to bottom (footer)
            ((JavascriptExecutor) driver).executeScript(
                    "window.scrollTo(0, document.body.scrollHeight);"
            );

            Thread.sleep(3000);

            // Locate the glossary link
            WebElement glossaryLink = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//a[contains(@href,'glossary')]")
                    )
            );

            // Scroll to element
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", glossaryLink
            );

            Thread.sleep(2000);

            // 🔥 Click using JavaScript (safe click)
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", glossaryLink
            );

            System.out.println("The Zero Day Dictionary link clicked");

            // Verify navigation
            wait.until(ExpectedConditions.urlContains("glossary"));
            Thread.sleep(3000);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
	}

}
