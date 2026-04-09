package Resources;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

public class Newsroom {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

       
            // -------- Open Website --------
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();
           // Thread.sleep(2000);

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
            //Thread.sleep(2000);


        try {
            // -------- Open Website --------
            driver.get("https://moole.ai/resources/newsroom");
            driver.manage().window().maximize();

            // -------- Loop through Pages (2 → 8) --------
            for (int i = 2; i <= 9; i++) {

                // Get fresh pagination
                WebElement pagination = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[contains(@class,'items-center gap-1')]")));

                WebElement page = pagination.findElement(
                        By.xpath(".//a[@aria-label='Page " + i + "']"));

                // Click page
                js.executeScript("arguments[0].click();", page);

                // Wait for URL change
                wait.until(ExpectedConditions.urlContains("page=" + i));

                System.out.println("Navigated to Page " + i);

                // -------- SCROLL LOGIC --------

                // Scroll to TOP
                js.executeScript("window.scrollTo(0, 0)");

                // Scroll to 70% of page
                js.executeScript("window.scrollTo(0, document.body.scrollHeight * 0.5)");

                // Small wait for visibility (optional but helps visually)
                Thread.sleep(1500);
            }

            // -------- NEXT BUTTON --------
            System.out.println("Testing Next button...");

            for (int i = 0; i < 2; i++) {

                WebElement pagination = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[contains(@class,'items-center gap-1')]")));

                WebElement next = pagination.findElement(
                        By.xpath(".//a[@aria-label='Next page']"));

                js.executeScript("arguments[0].click();", next);

                wait.until(ExpectedConditions.urlContains("page="));

                System.out.println("Clicked Next");

                // Scroll behavior
                js.executeScript("window.scrollTo(0, 0)");
                js.executeScript("window.scrollTo(0, document.body.scrollHeight * 0.5)");

                Thread.sleep(1500);
            }

            // -------- PREVIOUS BUTTON --------
            System.out.println("Testing Previous button...");

            WebElement pagination = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[contains(@class,'items-center gap-1')]")));

            WebElement prev = pagination.findElement(
                    By.xpath(".//a[@aria-label='Previous page']"));

            js.executeScript("arguments[0].click();", prev);

            wait.until(ExpectedConditions.urlContains("page="));

            System.out.println("Clicked Previous");

            // Scroll behavior
            js.executeScript("window.scrollTo(0, 0)");
            js.executeScript("window.scrollTo(0, document.body.scrollHeight * 0.5)");

            Thread.sleep(1500);

            // -------- Go Back to Home --------
            driver.navigate().to("https://moole.ai/");
            System.out.println("Returned to Home Page");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
        }
}

