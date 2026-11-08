
package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Utils.ConfigReader;

public class CompanyLinksTest {

    @Test
    public void ResourcesLinksTestTest() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(20));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        String homeUrl = ConfigReader.getProperty("baseUrl");

        try {

            driver.get(homeUrl);
            driver.manage().window().maximize();

            // -------- PRIVACY POPUP --------

            WebElement privacyOk = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[normalize-space()='OK']")));

            js.executeScript("arguments[0].click();", privacyOk);

            System.out.println("Clicked Privacy OK");

            Thread.sleep(800);


            // =====================================
            // STEP 1: ABOUT US
            // =====================================

            clickLink(driver, wait, js,
                    "//footer//a[contains(@href,'/company/about-us')]",
                    "About Us", "/company/about-us");


            // =====================================
            // STEP 2: LEADERSHIP
            // =====================================

            clickLink(driver, wait, js,
                    "//footer//a[contains(@href,'/company/leadership')]",
                    "Leadership", "/company/leadership");


            // =====================================
            // STEP 3: USE CASES
            // =====================================

            clickLink(driver, wait, js,
                    "//footer//a[contains(@href,'/company/use-cases')]",
                    "Use Cases", "/company/use-cases");


            // =====================================
            // STEP 4: CAREERS
            // =====================================

            clickLink(driver, wait, js,
                    "//footer//a[contains(@href,'/company/careers')]",
                    "Careers", "/company/careers");


            // =====================================
            // STEP 5: CONTACT US
            // =====================================

            clickLink(driver, wait, js,
                    "//footer//a[contains(@href,'/company/contact-us')]",
                    "Contact Us", "/company/contact-us");


            // =====================================
            // STEP 6: EXPERIENCE CENTER
            // =====================================

            clickLink(driver, wait, js,
                    "//footer//a[contains(@href,'/company/experience-center')]",
                    "Moole Experience Center",
                    "/company/experience-center");


            // =====================================
            // STEP 7: TRUST CENTER
            // =====================================

            clickLink(driver, wait, js,
                    "//footer//a[contains(@href,'/company/trust-center')]",
                    "Trust Center", "/company/trust-center");


            // =====================================
            // STEP 8: PRICING
            // =====================================

            clickLink(driver, wait, js,
                    "//footer//a[contains(@href,'/pricing')]",
                    "Pricing", "/pricing");


            // =====================================
            // FINAL: BACK TO HOME
            // =====================================

            driver.get(homeUrl);

            wait.until(ExpectedConditions.urlContains("moole.ai"));

            System.out.println("Successfully returned to Home Page");

            Thread.sleep(1000);

        }

        catch (Exception e) {

            System.out.println("Error: " + e.getMessage());

            e.printStackTrace();

        }

        finally {

            driver.quit();

            System.out.println("Browser closed");

        }

    }


    // =====================================
    // REUSABLE METHOD: CLICK FOOTER LINK
    // =====================================

    public void clickLink(
            WebDriver driver,
            WebDriverWait wait,
            JavascriptExecutor js,
            String xpath,
            String linkName,
            String expectedPath) throws InterruptedException {

        // Scroll current page slowly to bottom
        slowScroll(driver, js);

        // Locate next footer link
        WebElement link = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath(xpath)));

        // Scroll footer link into view
        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                link);

        // Wait until clickable
        wait.until(ExpectedConditions.elementToBeClickable(link));

        // Normal Selenium click
        link.click();

        System.out.println("Clicked " + linkName);

        // Wait for destination URL
        wait.until(ExpectedConditions.urlContains(expectedPath));

        System.out.println("Opened " + linkName + " Page");

        // Short pause after navigation
        Thread.sleep(800);

        // Do not return home here.
        // Continue from the current page.

    }


    // =====================================
    // REUSABLE METHOD: SLOW SCROLL
    // =====================================

    public void slowScroll(
            WebDriver driver,
            JavascriptExecutor js) throws InterruptedException {

        long lastHeight = 0;

        for (int i = 0; i < 60; i++) {

            // Scroll down gradually
            js.executeScript("window.scrollBy(0, 600);");

            Thread.sleep(200);

            long currentPosition = ((Number) js.executeScript(
                    "return window.pageYOffset;")).longValue();

            long pageHeight = ((Number) js.executeScript(
                    "return document.body.scrollHeight;")).longValue();

            long screenHeight = ((Number) js.executeScript(
                    "return window.innerHeight;")).longValue();

            // Check if bottom is reached
            if (currentPosition + screenHeight >= pageHeight) {

                Thread.sleep(500);

                long updatedHeight = ((Number) js.executeScript(
                        "return document.body.scrollHeight;")).longValue();

                if (updatedHeight == pageHeight &&
                        lastHeight == updatedHeight) {

                    break;
                }

                lastHeight = updatedHeight;
            }

        }

        System.out.println("Scrolling completed");

    }

}
