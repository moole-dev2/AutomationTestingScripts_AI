package Resources;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.ConfigReader;

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
        	driver.get(ConfigReader.getProperty("baseUrl"));
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
            
            WebElement proposedSolutionBtn =
                    wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[text()='Proposed Solution']]")));

            highlight(js, proposedSolutionBtn, "purple");
            js.executeScript("arguments[0].click();", proposedSolutionBtn);

            Thread.sleep(2000);

            WebElement impactBtn =
                    wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[text()='Impact']]")
                    ));

            // highlight BEFORE click
            highlight(js, impactBtn, "orange");

            // click using JS
            js.executeScript("arguments[0].click();", impactBtn);

            Thread.sleep(2000);
            

         // =========================================================
         // CLICK CASE STUDY 1 (CVE INTELLIGENCE)
         // =========================================================

         WebElement cveCard = wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("//h4[contains(text(),'Turning CVE Intelligence into Operational Security Decisions')]")
         ));

         js.executeScript("arguments[0].scrollIntoView({block:'center'});", cveCard);
         Thread.sleep(1000);

         highlight(js, cveCard, "purple");
         js.executeScript("arguments[0].click();", cveCard);

         System.out.println("Clicked CVE Case Study");

         Thread.sleep(4000);

         // scroll inside page
         scroll(js);
         Thread.sleep(2000);

         // =========================================================
         // CLICK PROPOSED SOLUTION
         // =========================================================

         WebElement proposedSolution =
                 wait.until(ExpectedConditions.elementToBeClickable(
                         By.xpath("//button[.//span[text()='Proposed Solution']]")
                 ));

         highlight(js, proposedSolution, "orange");
         js.executeScript("arguments[0].click();", proposedSolution);

         System.out.println("Clicked Proposed Solution");

         Thread.sleep(2000);

         // =========================================================
         // CLICK CASE STUDY 2 (SUPPLY CHAIN)
         // =========================================================

         WebElement supplyChainCard = wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("//h4[contains(text(),'Preventing Supply-Chain Attacks in Containerized Infrastructure')]")
         ));

         js.executeScript("arguments[0].scrollIntoView({block:'center'});", supplyChainCard);
         Thread.sleep(1000);

         highlight(js, supplyChainCard, "red");
         js.executeScript("arguments[0].click();", supplyChainCard);

         System.out.println("Clicked Supply Chain Case Study");

         Thread.sleep(4000);

         // scroll page
         scroll(js);
         Thread.sleep(2000);

         // =========================================================
         // CLOSE / GO BACK
         // =========================================================

         driver.navigate().back();
         Thread.sleep(2000);

         driver.navigate().back();
         Thread.sleep(2000);

         System.out.println("Flow completed and returned back");

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