package Home;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.ConfigReader;

import org.openqa.selenium.JavascriptExecutor;

public class ResourcesLinksTest {

    public static void main(String[] args) throws Exception {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
        	driver.get(ConfigReader.getProperty("baseUrl"));
            driver.manage().window().maximize();

            // -------- Privacy Popup --------
            WebElement privacyOk = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space()='OK']")));
            js.executeScript("arguments[0].click();", privacyOk);
            System.out.println("Clicked Privacy OK");

            Thread.sleep(1000);


            // -------- FUNCTION: SLOW SCROLL --------
            for (int i = 0; i <= 1500; i += 100) {
                js.executeScript("window.scrollBy(0,200)");
                Thread.sleep(1000);
            }
            // ================================
            // STEP 1: The Signal
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(2000);
            WebElement theSignal = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//footer//a[normalize-space(text())='The Signal']")
            ));
            js.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", theSignal);
            Thread.sleep(1000);
            js.executeScript("arguments[0].click();", theSignal);
            System.out.println("Clicked 'The Signal' link");

            // Scroll page
            for (int i = 0; i <= 2000; i += 100) {
                js.executeScript("window.scrollBy(0,200)");
                Thread.sleep(1000);
            }

            // ================================
            // STEP 2: Moole Field Notes
            // ================================
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(1000);

            WebElement fieldNotes = driver.findElement(
                    By.xpath("//footer//a[contains(@href,'case-studies')]")
            );
            js.executeScript("arguments[0].click();", fieldNotes);
            System.out.println("Clicked Moole Field Notes");

            Thread.sleep(1000);

            for (int i = 0; i <= 2000; i += 100) {
                js.executeScript("window.scrollBy(0,200)");
                Thread.sleep(1000);
            }

            // ================================
            // STEP 3: The Moole Runbook
            // ================================
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(2000);

            WebElement runbook = driver.findElement(
                    By.xpath("//footer//a[normalize-space()='The Moole Runbook']")
            );
            js.executeScript("arguments[0].click();", runbook);
            System.out.println("Clicked Runbook");

         // Scroll inside Contact page
            for (int i = 0; i <= 1500; i += 100) {
                js.executeScript("window.scrollBy(0,100)");
                Thread.sleep(500);
            }

            Thread.sleep(500);

            // ================================
            // STEP 4: FAQs
            // ================================
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(1000);

            WebElement faqs = driver.findElement(
                    By.xpath("//footer//a[contains(@href,'faqs')]")
            );
            js.executeScript("arguments[0].click();", faqs);
            System.out.println("Clicked FAQs");

         // Scroll inside Contact page
            for (int i = 0; i <= 1500; i += 100) {
                js.executeScript("window.scrollBy(0,100)");
                Thread.sleep(500);
            }

            Thread.sleep(500);

            // ================================
            // STEP 5: Newsroom
            // ================================
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(1000);

            WebElement news = driver.findElement(
                    By.xpath("//footer//a[contains(@href,'newsroom')]")
            );
            js.executeScript("arguments[0].click();", news);
            System.out.println("Clicked Newsroom");

         // Scroll inside Contact page
            for (int i = 0; i <= 1500; i += 100) {
                js.executeScript("window.scrollBy(0,100)");
                Thread.sleep(500);
            }

            Thread.sleep(500);

            // ================================
            // STEP 6: Zero Day Dictionary
            // ================================
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(1000);

            WebElement glossary = driver.findElement(
                    By.xpath("//footer//a[contains(@href,'glossary')]")
            );
            js.executeScript("arguments[0].click();", glossary);
            System.out.println("Clicked Dictionary");

         // Scroll inside Contact page
            for (int i = 0; i <= 1500; i += 100) {
                js.executeScript("window.scrollBy(0,100)");
                Thread.sleep(500);
            }

            Thread.sleep(1000);

            // -------- BACK TO HOME --------
            driver.get("https://moole.ai/");
            System.out.println("Back to Home");

            Thread.sleep(2000);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
    }
}