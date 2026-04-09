package Resources;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

public class ZeroDayDictionary {

    public static void main(String[] args) throws InterruptedException {

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
            // -------- Open Glossary Page --------
            driver.get("https://moole.ai/resources/glossary");
            driver.manage().window().maximize();
            Thread.sleep(2000);

            // -------- Loop through letters A-Z --------
            char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

            for (char ch : letters) {
                String letter = String.valueOf(ch);

                // Refetch clickable letters each time (avoids stale element)
                List<WebElement> alphabetLinks = driver.findElements(
                        By.xpath("//main/section[2]/div[2]/a"));

                WebElement targetLetter = null;

                for (WebElement link : alphabetLinks) {
                    if (link.getText().equalsIgnoreCase(letter)) {
                        // Check if this letter is already active
                        String ariaCurrent = link.getAttribute("aria-current");
                        if (ariaCurrent == null || !ariaCurrent.equalsIgnoreCase("page")) {
                            targetLetter = link;
                        }
                        break;
                    }
                }

                if (targetLetter != null) {
                    System.out.println("Clicking letter: " + letter);

                    // -------- Scroll to top first --------
                    js.executeScript("window.scrollTo(0, 0);");
                    Thread.sleep(500); // small pause

                    // -------- Scroll letter into middle of page --------
                    js.executeScript("arguments[0].scrollIntoView({block:'center'});", targetLetter);
                    Thread.sleep(500); // small pause

                    // -------- Click letter --------
                    js.executeScript("arguments[0].click();", targetLetter);

                    // Wait for URL hash to update
                    wait.until(ExpectedConditions.urlContains("#section-" + letter.toUpperCase()));

                    Thread.sleep(1000); // small pause for UI update
                } else {
                    System.out.println("Letter " + letter + " is already active. Skipping.");
                }
            }

            System.out.println("Finished clicking all clickable letters.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
    }
}