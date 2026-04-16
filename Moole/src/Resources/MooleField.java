package Resources;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

public class MooleField {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // -------- Open Website --------
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();
            Thread.sleep(2000);

            // -------- Handle Privacy Popup --------
            try {
                WebElement privacyOk = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[contains(@class,'rounded-sm bg-indigo') and text()='OK']")));
                js.executeScript("arguments[0].click();", privacyOk);
                System.out.println("Clicked Privacy OK button");
            } catch (Exception e) {
                System.out.println("Privacy popup not found");
            }

            // -------- Click Resources --------
            WebElement resources = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[normalize-space()='Resources']")));
            resources.click();
            Thread.sleep(2000);

            // -------- Click "Hard Lessons..." --------
            WebElement hardLessons = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(),'Hard Lessons from the Digital Frontline')]")));
            js.executeScript("arguments[0].scrollIntoView(true);", hardLessons);
            Thread.sleep(2000);
            hardLessons.click();

            System.out.println("Opened Hard Lessons page");
            Thread.sleep(3000);

            // -------- Scroll Page --------
            scrollDownUp(js);

            // -------- Click Read More --------
            WebElement readMore = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//button[contains(.,'Read More')])[3]")));
            js.executeScript("arguments[0].scrollIntoView(true);", readMore);
            Thread.sleep(2000);
            js.executeScript("arguments[0].click();", readMore);

            System.out.println("Clicked Read More");
            Thread.sleep(3000);

            // -------- Scroll --------
            scrollDownUp(js);

         // -------- Click Scenario (FIXED) --------
            WebElement scenario = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[.//span[text()='Scenario']]")));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", scenario);
            Thread.sleep(1000);
            js.executeScript("window.scrollBy(0,100)");
            Thread.sleep(1000);
            js.executeScript("arguments[0].click();", scenario);

            System.out.println("Clicked Scenario");
            Thread.sleep(2000);

         // Locate Impact button
         WebElement impact = wait.until(ExpectedConditions.presenceOfElementLocated(
                 By.xpath("//span[text()='Impact']")));

         js.executeScript("arguments[0].scrollIntoView({block:'center'});", impact);

         js.executeScript("window.scrollBy(0, 50);");

         js.executeScript("arguments[0].click();", impact);

         System.out.println("Clicked Impact successfully");
         Thread.sleep(2000);
         
      // -------- Click Case Study under Impact --------
         WebElement caseStudy = wait.until(ExpectedConditions.presenceOfElementLocated(
                 By.xpath("//h3[text()='Turning CVE Intelligence into Operational Security Decisions']/ancestor::a")));

         js.executeScript("arguments[0].scrollIntoView({block:'center'});", caseStudy);

         js.executeScript("window.scrollBy(0,50);");

         js.executeScript("arguments[0].click();", caseStudy);

         System.out.println("Clicked Case Study successfully");

         // Optional: wait for page to load
         Thread.sleep(3000);

            // -------- Scroll --------
            scrollDownUp(js);

            // -------- Navigate Back --------
            driver.navigate().back();
            Thread.sleep(3000);
            scrollDownUp(js);

            driver.navigate().back();
            Thread.sleep(3000);
            scrollDownUp(js);

            System.out.println("Returned to Home Page");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
    }

    // -------- Scroll Down & Up --------
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