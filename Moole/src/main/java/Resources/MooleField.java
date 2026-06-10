package Resources;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MooleField {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        try {

            // =========================================================
            // OPEN WEBSITE
            // =========================================================
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();
            Thread.sleep(3000);

            // =========================================================
            // HANDLE POPUP
            // =========================================================
            try {
                WebElement ok = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'OK')]")));
                js.executeScript("arguments[0].click();", ok);
                System.out.println("Popup closed");
            } catch (Exception e) {
                System.out.println("No popup found");
            }

            Thread.sleep(2000);

            // =========================================================
            // OPEN RESOURCES DROPDOWN
            // =========================================================
            WebElement resources = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[normalize-space()='Resources']")));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", resources);
            Thread.sleep(1000);

            actions.moveToElement(resources).perform();
            Thread.sleep(1500);

            js.executeScript("arguments[0].click();", resources);
            System.out.println("Resources opened");

            Thread.sleep(3000);

            // =========================================================
            // CLICK CASE STUDIES
            // =========================================================
            WebElement caseStudies = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/resources/case-studies']")));

            highlight(js, caseStudies, "red");
            js.executeScript("arguments[0].click();", caseStudies);

            System.out.println("Clicked Case Studies");
            Thread.sleep(5000);

            scroll(js);

            // =========================================================
            // CLICK READ MORE
            // =========================================================
            WebElement readMore = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//button[contains(.,'Read More')])[1]")));

            highlight(js, readMore, "blue");
            js.executeScript("arguments[0].click();", readMore);

            Thread.sleep(3000);

            scroll(js);

            // =========================================================
            // CLICK SCENARIO
            // =========================================================
            WebElement scenario = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[.//span[text()='Scenario']]")));

            highlight(js, scenario, "green");
            js.executeScript("arguments[0].click();", scenario);

            Thread.sleep(2000);

            // =========================================================
            // CLICK IMPACT
            // =========================================================
            WebElement impact = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='Impact']")));

            highlight(js, impact, "orange");
            js.executeScript("arguments[0].click();", impact);

            Thread.sleep(2000);

            // =========================================================
            // CLICK CASE STUDY CARD
            // =========================================================
            WebElement caseStudy = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[.//h3[contains(.,'CV')]]")));

            highlight(js, caseStudy, "red");
            js.executeScript("arguments[0].click();", caseStudy);

            Thread.sleep(4000);

            scroll(js);

            // =========================================================
            // NAV BACK
            // =========================================================
            driver.navigate().back();
            Thread.sleep(3000);

            driver.navigate().back();
            Thread.sleep(3000);

            System.out.println("Back to Home Page");

        } catch (Exception e) {

            System.out.println("GLOBAL ERROR: " + e.getMessage());

        } finally {

            driver.quit();
            System.out.println("Browser closed");
        }
    }

    // =========================================================
    // SCROLL METHOD
    // =========================================================
    public static void scroll(JavascriptExecutor js) throws InterruptedException {

        for (int i = 0; i <= 2000; i += 300) {
            js.executeScript("window.scrollBy(0,300)");
            Thread.sleep(400);
        }

        for (int i = 0; i <= 2000; i += 300) {
            js.executeScript("window.scrollBy(0,-300)");
            Thread.sleep(400);
        }
    }

    // =========================================================
    // HIGHLIGHT METHOD
    // =========================================================
    public static void highlight(JavascriptExecutor js, WebElement element, String color) {

        js.executeScript("arguments[0].style.border='4px solid " + color + "'", element);
    }
}