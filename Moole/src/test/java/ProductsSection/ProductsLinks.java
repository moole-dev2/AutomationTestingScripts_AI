package ProductsSection;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.ConfigReader;

public class ProductsLinks {

    public static void main(String[] args) throws Exception {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {

            // =====================================================
            // STEP 1: OPEN WEBSITE
            // =====================================================
        	driver.get(ConfigReader.getProperty("baseUrl"));
            driver.manage().window().maximize();
            Thread.sleep(3000);

            System.out.println("Website Opened");

            // =====================================================
            // CLOSE POPUP
            // =====================================================
            try {
                WebElement ok = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[normalize-space()='OK']")));
                js.executeScript("arguments[0].click();", ok);
                System.out.println("Popup Closed");
            } catch (Exception e) {
                System.out.println("No popup");
            }

            // =====================================================
            // STEP 2: SCROLL HOME PAGE
            // =====================================================
            scroll(driver, js);

         // =====================================================
         // STEP: SCROLL FULL HOME PAGE FIRST
         // =====================================================
         for (int i = 0; i < 2500; i += 300) {
             js.executeScript("window.scrollBy(0,300)");
             Thread.sleep(600);
         }

         // small pause for lazy load
         Thread.sleep(2000);

         // =====================================================
         // STEP: CLICK SCA 
         // =====================================================
         By scaLocator = By.xpath("//a[contains(@href,'software-composition-analysis')]");

         WebElement sca = wait.until(ExpectedConditions.presenceOfElementLocated(scaLocator));

         // IMPORTANT: force scroll into center
         js.executeScript("arguments[0].scrollIntoView({block:'center'});", sca);
         Thread.sleep(1500);

      // IMPORTANT: sometimes React re-renders -> re-fetch element
         sca = driver.findElement(scaLocator);

         // scroll again (double safety)
         js.executeScript("arguments[0].scrollIntoView({block:'center'});", sca);
         Thread.sleep(1000);

         // =====================================================
         // STEP: CLICK USING JS (MOST STABLE)
         // =====================================================
         js.executeScript("arguments[0].click();", sca);

         System.out.println("Clicked SCA");

         Thread.sleep(5000);
         
	      // =====================================================
	      // SCROLL SCA PAGE
	      // =====================================================
	      for (int i = 0; i < 3000; i += 250) {
	          js.executeScript("window.scrollBy(0,250)");
	          Thread.sleep(500);
	      }
	
	      System.out.println("Scrolled SCA page");

            // =====================================================
            // STEP 4: FOOTER FLOW (ALL PRODUCT LINKS)
            // =====================================================

            clickFooterProduct(driver, wait, js,
                    "Container Security",
                    "//*[contains(text(),'Container Security')]");

            clickFooterProduct(driver, wait, js,
                    "SAST",
                    "//*[contains(text(),'SAST')]");

            clickFooterProduct(driver, wait, js,
                    "Vulnerability Database",
                    "//*[contains(text(),'Vulnerability')]");

            // =====================================================
            // BACK TO HOME
            // =====================================================
            driver.get("https://moole.ai/");
            Thread.sleep(3000);

            System.out.println("Back to Home");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
    }

    // =====================================================
    // CLICK METHOD (SAFE + DYNAMIC)
    // =====================================================
    public static void click(WebDriver driver, WebDriverWait wait, JavascriptExecutor js,
                             String xpath, String name) {

        try {
            WebElement el = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            Thread.sleep(1000);

            js.executeScript("arguments[0].click();", el);

            System.out.println("Clicked: " + name);
            Thread.sleep(4000);

        } catch (Exception e) {
            System.out.println("Failed clicking " + name + " -> " + e.getMessage());
        }
    }

    // =====================================================
    // FOOTER CLICK METHOD (IMPORTANT FIX)
    // =====================================================
    public static void clickFooterProduct(WebDriver driver,
                                          WebDriverWait wait,
                                          JavascriptExecutor js,
                                          String name,
                                          String xpath) throws Exception {

        // scroll to footer every time (VERY IMPORTANT)
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(2000);

        WebElement el = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

        js.executeScript("arguments[0].scrollIntoView(true);", el);
        Thread.sleep(1000);

        js.executeScript("arguments[0].click();", el);

        System.out.println("Clicked Footer: " + name);
        Thread.sleep(4000);

        // scroll inside page
        scroll(driver, js);

        // go back to SCA page again
        driver.navigate().back();
        Thread.sleep(4000);
    }

    // =====================================================
    // SCROLL METHOD
    // =====================================================
    public static void scroll(WebDriver driver, JavascriptExecutor js) throws Exception {

        for (int i = 0; i < 2000; i += 300) {
            js.executeScript("window.scrollBy(0,300)");
            Thread.sleep(500);
        }
    }
}