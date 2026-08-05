
package Home;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.ConfigReader;

public class HomePageN {

    // =========================
    // SAFE CLICK METHOD
    // =========================
    public static void safeClick(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, By locator, String name) {

        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
            Thread.sleep(1500);

            // HIGHLIGHT RED BEFORE CLICK
            js.executeScript("arguments[0].style.border='4px solid red';", element);

            Thread.sleep(800);

            js.executeScript("arguments[0].click();", element);

            System.out.println("CLICKED: " + name);

            Thread.sleep(4000);

        } catch (Exception e) {
            System.out.println("FAILED CLICK: " + name + " | " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {

            // =========================
            // OPEN SITE
            // =========================
        	driver.get(ConfigReader.getProperty("baseUrl"));
            driver.manage().window().maximize();
            Thread.sleep(5000);

            System.out.println("Homepage Loaded");

            // =========================
            // POPUP HANDLING
            // =========================
            try {
                WebElement ok = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'OK')]")));
                js.executeScript("arguments[0].click();", ok);
            } catch (Exception ignored) {}

            // =========================
            // SCROLL HOME
            // =========================
            js.executeScript("window.scrollTo(0,1500)");
            Thread.sleep(2000);

            // =========================
            // EXPLORE PLATFORM
            // =========================
            safeClick(driver, wait, js,
                    By.xpath("//button[contains(.,'Explore the Platform')]"),
                    "Explore The Platform");

            // =========================
            // START TOUR
            // =========================
            safeClick(driver, wait, js,
                    By.xpath("//button[contains(text(),'Start Tour')]"),
                    "Start Tour");

            // NEXT FLOW
            for (int i = 1; i <= 5; i++) {
                safeClick(driver, wait, js,
                        By.xpath("//button[@aria-label='Next']"),
                        "Next " + i);
            }

            for (int i = 1; i <= 2; i++) {
                safeClick(driver, wait, js,
                        By.xpath("//button[@aria-label='Back']"),
                        "Back " + i);
            }

            for (int i = 1; i <= 2; i++) {
                safeClick(driver, wait, js,
                        By.xpath("//button[@aria-label='Next']"),
                        "Next Again " + i);
            }

            safeClick(driver, wait, js,
                    By.xpath("//button[@aria-label='Last']"),
                    "Complete Tour");

            // =========================
            // CLOSE POPUP
            // =========================
            try {
                safeClick(driver, wait, js,
                        By.xpath("//button[@aria-label='Close popup']"),
                        "Close Popup");
            } catch (Exception ignored) {}

            // =========================
            // BACK TO HOME
            // =========================
            driver.get("https://moole.ai/");
            Thread.sleep(5000);

            System.out.println("Back to Home");

            // =========================
            // DEMO PAGE
            // =========================
            safeClick(driver, wait, js,
                    By.xpath("//a[contains(@href,'/demo')]"),
                    "Schedule Demo");

            driver.navigate().back();
            Thread.sleep(5000);

            // =========================
            // ASPM SECTION
            // =========================
            js.executeScript("window.scrollTo(0,4500)");
            Thread.sleep(3000);

            List<WebElement> aspmOptions = driver.findElements(
                    By.xpath("//a[@href='/products']//span"));

            for (int i = 0; i < aspmOptions.size(); i++) {

                aspmOptions = driver.findElements(By.xpath("//a[@href='/products']//span"));

                WebElement option = aspmOptions.get(i);
                String name = option.getText();

                if (name.isEmpty()) continue;

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", option);
                Thread.sleep(1200);

                js.executeScript("arguments[0].style.border='3px solid red';", option);

                js.executeScript("arguments[0].click();", option);

                System.out.println("ASPM CLICKED: " + name);

                Thread.sleep(4000);

                driver.navigate().back();
                Thread.sleep(5000);
            }

            // =========================
            // PRODUCT CARDS
            // =========================
            List<String> products = Arrays.asList(
                    "Moole Discover",
                    "Moole Prioritize",
                    "Moole SBOM",
                    "Moole Detect",
                    "Moole Container"
            );

            for (String name : products) {

                WebElement card = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[contains(text(),'" + name + "')]")));

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", card);

                js.executeScript("arguments[0].style.border='3px solid red';", card);

                Thread.sleep(800);

                js.executeScript("arguments[0].click();", card);

                System.out.println("PRODUCT CLICKED: " + name);

                Thread.sleep(4000);

                driver.get("https://moole.ai/");
                Thread.sleep(5000);
            }

            // =========================
            // EXPLORE PRODUCTS (FIXED ERROR)
            // =========================
            driver.get("https://moole.ai/");
            Thread.sleep(5000);

            WebElement exploreProducts = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//a[contains(@href,'/products') and contains(.,'Explore Products')]")));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", exploreProducts);
            Thread.sleep(1500);

            js.executeScript("arguments[0].style.border='4px solid red';", exploreProducts);
            js.executeScript("arguments[0].click();", exploreProducts);

            System.out.println("Explore Products Clicked");

            Thread.sleep(5000);

            driver.navigate().back();
            Thread.sleep(5000);

            // =========================
            // CASE STUDIES
            // =========================
            safeClick(driver, wait, js,
                    By.xpath("//a[contains(@href,'/case-studies')]"),
                    "Case Studies");

            driver.navigate().back();
            Thread.sleep(5000);

            // =========================
            // EXPERIENCE CENTER
            // =========================
            js.executeScript("window.scrollTo(0,document.body.scrollHeight*0.75)");
            Thread.sleep(2000);

            safeClick(driver, wait, js,
                    By.xpath("//a[contains(@href,'experience-center')]"),
                    "Experience Center");

            driver.navigate().back();
            Thread.sleep(5000);

            // =========================
            // NEWSROOM
            // =========================
            js.executeScript("window.scrollTo(0,document.body.scrollHeight*0.75)");
            Thread.sleep(2000);

            safeClick(driver, wait, js,
                    By.xpath("//a[contains(@href,'newsroom')]"),
                    "Newsroom");

            driver.navigate().back();
            Thread.sleep(5000);

            System.out.println("TEST COMPLETED SUCCESSFULLY");

        } catch (Exception e) {

            System.out.println("GLOBAL ERROR: " + e.getMessage());

        } finally {
            driver.quit();
        }
    }
}