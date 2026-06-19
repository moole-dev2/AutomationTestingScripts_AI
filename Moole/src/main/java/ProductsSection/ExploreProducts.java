package ProductsSection;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExploreProducts {

    public static void main(String[] args) throws Exception {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {

            // =====================================================
            // STEP 1: OPEN WEBSITE
            // =====================================================

            driver.get("https://moole.ai/");
            driver.manage().window().maximize();
            Thread.sleep(4000);

            System.out.println("Website Opened");

            // =====================================================
            // HANDLE POPUP
            // =====================================================

            try {
                WebElement okBtn = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[text()='OK']")));

                highlight(driver, okBtn);
                safeClick(driver, js, okBtn);

                System.out.println("Popup Closed");
                Thread.sleep(2000);

            } catch (Exception e) {
                System.out.println("No Popup");
            }

            // =====================================================
            // OPEN PRODUCTS PAGE
            // =====================================================

            driver.get("https://moole.ai/products");
            Thread.sleep(5000);

            performPageScroll(driver);

            // =====================================================
            // EXPLORE MORE 1-4
            // Each "Explore More" button is identified by its UNIQUE
            // gradient color class (not by position), since position-based
            // indexing ((//span[...])[1], [2], [3]) can point to the same
            // element twice if the DOM order shifts after scroll/navigation.
            // =====================================================

            String[][] exploreButtons = {
                    { "//span[contains(@class,'from-[#2282fa]') and .//span[normalize-space()='Explore More']]", "Explore More 1 (Blue)" },
                    { "//span[contains(@class,'from-[#00b974]') and .//span[normalize-space()='Explore More']]", "Explore More 2 (Green)" },
                    { "//span[contains(@class,'from-[#a643ff]') and .//span[normalize-space()='Explore More']]", "Explore More 3 (Purple)" },
                    { "//span[contains(@class,'from-[#00b4aa]') and .//span[normalize-space()='Explore More']]", "Explore More 4 (Teal)" }
            };

            for (String[] btn : exploreButtons) {

                String xpath = btn[0];
                String name = btn[1];

                // 1. Click the Explore More button
                clickElement(driver, js, wait, xpath, name);

                // 2. Scroll that page
                performPageScroll(driver);

                // 3. Navigate back to Products page
                driver.navigate().back();
                Thread.sleep(3000);
            }

            System.out.println("Completed Explore More 1-4");

            // =====================================================
            // AFTER EXPLORE MORE 1-4 → BACK TO PRODUCTS PAGE
            // =====================================================

            driver.get("https://moole.ai/products");
            Thread.sleep(4000);

            System.out.println("Returned to Products Page after Explore More 1-4");

            // =====================================================
            // CLICK EXPLORE INTEGRATIONS
            // =====================================================

            clickElement(driver, js, wait,
                    "//button[@type='button' and contains(.,'Explore Integrations')]",
                    "Explore Integrations");

            // =====================================================
            // SCROLL INTEGRATIONS PAGE
            // =====================================================

            performPageScroll(driver);

            Thread.sleep(2000);

            // =====================================================
            // BACK TO PRODUCTS PAGE (NO SCROLL)
            // =====================================================

            driver.get("https://moole.ai/products");
            Thread.sleep(4000);

            System.out.println("Back to Products Page (No Scroll)");

            // =====================================================
            // GO TO HOME PAGE
            // =====================================================

            driver.get("https://moole.ai/");
            Thread.sleep(4000);

            System.out.println("Navigated to Home Page");

        } catch (Exception e) {

            System.out.println("Exception Occurred: " + e.getMessage());

        } finally {

            Thread.sleep(3000);
            driver.quit();
            System.out.println("Browser Closed");
        }
    }

    // =====================================================
    // SAFE CLICK METHOD
    // =====================================================

    private static void highlight(WebDriver driver, WebElement okBtn) {
        // TODO Auto-generated method stub
    }

    public static void safeClick(WebDriver driver, JavascriptExecutor js, WebElement element) {

        try {
            element.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", element);
        }
    }

    // =====================================================
    // CLICK METHOD
    // =====================================================

    public static void clickElement(WebDriver driver,
                                    JavascriptExecutor js,
                                    WebDriverWait wait,
                                    String xpath,
                                    String name) {

        try {

            WebElement element = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    element);

            Thread.sleep(1200);

            js.executeScript("arguments[0].style.border='3px solid red'", element);
            Thread.sleep(800);

            safeClick(driver, js, element);

            System.out.println("Clicked: " + name);

            Thread.sleep(3000);

        } catch (Exception e) {
            System.out.println("Failed clicking " + name + " : " + e.getMessage());
        }
    }

    // =====================================================
    // SCROLL METHOD
    // =====================================================

    public static void performPageScroll(WebDriver driver) throws Exception {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        long height = (long) js.executeScript("return document.body.scrollHeight");

        for (int i = 0; i <= height; i += 300) {
            js.executeScript("window.scrollBy(0,300)");
            Thread.sleep(700);
        }
    }
}
