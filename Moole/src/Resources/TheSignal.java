package Resources;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

public class TheSignal {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // -------- Open Website --------
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();
            Thread.sleep(2000);
         // ---------------- Handle Privacy Popup ----------------
            try {
                WebElement privacyOk = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[contains(@class,'rounded-sm bg-indigo') and text()='OK']")));
                js.executeScript("arguments[0].click();", privacyOk);
                System.out.println("Clicked Privacy OK button");
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Privacy popup not found, continuing...");
            }


            // -------- Click Resources --------
            WebElement resources = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[normalize-space()='Resources']")));
            resources.click();
            Thread.sleep(2000);

            // -------- Click The Signal --------
            WebElement blog = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/resources/blogs']")));
            blog.click();
            System.out.println("Opened The Signal page");
            Thread.sleep(3000);

            // -------- Scroll Down & Up --------
            scrollDownUp(js);

            // -------- Click First Read More --------
            WebElement readMore1 = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//button[contains(.,'Read More')])[1]")));

            js.executeScript("arguments[0].scrollIntoView(true);", readMore1);
            Thread.sleep(2000);
            js.executeScript("arguments[0].click();", readMore1);

            System.out.println("Opened Article Page");
            Thread.sleep(3000);
            // -------- Scroll Down & Up --------
            scrollDownUp(js);

            // -------- Click "Why It Matters" --------
            WebElement whyItMatters = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[.//span[text()='Why It Matters']]")));

            js.executeScript("arguments[0].scrollIntoView(true);", whyItMatters);
            Thread.sleep(2000);
            whyItMatters.click();

            System.out.println("Clicked Why It Matters");
            Thread.sleep(2000);

            // -------- Click "What Teams Should Watch" --------
            WebElement teamsWatch = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//span[text()='What Teams Should Watch']")));

            js.executeScript("arguments[0].scrollIntoView(true);", teamsWatch);
            Thread.sleep(2000);
            teamsWatch.click();

            System.out.println("Clicked What Teams Should Watch");
            Thread.sleep(2000);

            // -------- Scroll Down & Up --------
            scrollDown(js);

            // -------- Click Bottom Read More --------
            WebElement readMore2 = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//button[contains(.,'Read More')])[last()]")));

            js.executeScript("arguments[0].scrollIntoView(true);", readMore2);
            Thread.sleep(2000);
            js.executeScript("arguments[0].click();", readMore2);

            System.out.println("Clicked second Read More");
            Thread.sleep(4000);
            scrollDownUp(js);


            // -------- Navigate Back --------
            driver.navigate().back();
            Thread.sleep(3000);
            scrollDownUp(js);


            driver.navigate().back();
            Thread.sleep(3000);
            scrollDownUp(js);


            System.out.println("Returned to The Signal page");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
    }

    @SuppressWarnings("unused")
	private static void scrollUp(JavascriptExecutor js) {
		// TODO Auto-generated method stub
		
	}

	private static void scrollDown(JavascriptExecutor js) {
		// TODO Auto-generated method stub
		
	}

	// -------- Simple Scroll Down & Up --------
    public static void scrollDownUp(JavascriptExecutor js) throws InterruptedException {

        long height = (long) js.executeScript("return document.body.scrollHeight");

        // Scroll Down
        for (int i = 0; i < height; i += 300) {
            js.executeScript("window.scrollBy(0,300)");
            Thread.sleep(500);
        }

        // Scroll Up
        for (int i = 0; i < height; i += 300) {
            js.executeScript("window.scrollBy(0,-300)");
            Thread.sleep(500);
        }

        System.out.println("Scrolled down and up");
    }
}