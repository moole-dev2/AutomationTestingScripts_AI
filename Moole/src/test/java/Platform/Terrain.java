package Platform;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import Utils.ConfigReader;

import org.testng.annotations.Test;



public class Terrain {

    @Test
    public void TerrainTest() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Actions actions = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // =========================================================
            // OPEN WEBSITE
            // =========================================================
        	driver.get(ConfigReader.getProperty("baseUrl"));
            driver.manage().window().maximize();
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
            // HOVER PLATFORM
            // =========================================================
            WebElement platform = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//button[.//span[text()='Platform']]")));

            actions.moveToElement(platform).perform();
            Thread.sleep(1500);

            // =========================================================
            // CLICK TERRAIN
            // =========================================================
            WebElement terrain = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(text(),'The Terrain')]")));

            js.executeScript("arguments[0].click();", terrain);
            System.out.println("Terrain opened");

            Thread.sleep(3000);

            // =========================================================
            // SCROLL SLIGHTLY
            // =========================================================
            js.executeScript("window.scrollBy(0,300)");
            Thread.sleep(1000);

            // =========================================================
            // CLICK CATEGORIES ONE BY ONE
            // =========================================================

            clickCategory(driver, wait, js, "All");
            clickCategory(driver, wait, js, "Cloud Registries");
            clickCategory(driver, wait, js, "Container Registries");
            clickCategory(driver, wait, js, "Private Registries");

            // =========================================================
            // SEARCH BAR ACTIONS
            // =========================================================
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@type='text' or contains(@placeholder,'Search')]")));

            // -------- GitHub Search --------
            searchBox.clear();
            searchBox.sendKeys("github");
            Thread.sleep(2000);
            System.out.println("Searched GitHub");

            // -------- Nexus Search --------
            searchBox.clear();
            searchBox.sendKeys("nexus");
            Thread.sleep(2000);
            System.out.println("Searched Nexus");

            // =========================================================
            // FINAL SCROLL (USER BEHAVIOR)
            // =========================================================
            for (int i = 0; i < 5; i++) {
                js.executeScript("window.scrollBy(0,300)");
                Thread.sleep(500);
                
            }

            // =========================================================
            // BACK TO HOME
            // =========================================================
            driver.navigate().to("https://moole.ai/");
            System.out.println("Returned to Home Page");

            Thread.sleep(3000);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
    }

    // =========================================================
    // REUSABLE CATEGORY CLICK METHOD (VERY IMPORTANT FIX)
    // =========================================================
    public static void clickCategory(WebDriver driver, WebDriverWait wait,
                                     JavascriptExecutor js, String category) {

        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[.//span[text()='" + category + "']]")));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
            Thread.sleep(800);

            js.executeScript("arguments[0].click();", btn);

            System.out.println("Clicked: " + category);
            Thread.sleep(1500);

        } catch (Exception e) {
            System.out.println("Failed clicking category: " + category);
        }
    }
}