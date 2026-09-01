package SignIn;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utils.ConfigReader;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import org.testng.annotations.Test;



public class Findings {

    @Test
    public void DashboardPageTest() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\psiri\\AppData\\Local\\Google\\Chrome\\User Data");
        options.addArguments("profile-directory=Profile 1");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
        	driver.get(ConfigReader.getProperty("baseUrl"));
            driver.manage().window().maximize();
            // ================= LOGIN =================
            driver.get("https://moole.ai/auth/signin");
            driver.manage().window().maximize();

            WebElement emailField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']"))
            );
            emailField.sendKeys("moole.dev.2@gmail.com");

            WebElement signIn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@data-tour='signup-submit']")
                    )
            );

            signIn.click();

            System.out.println("Sign in button clicked");

            System.out.println("Enter OTP manually...");
            new Scanner(System.in).nextLine();
            Thread.sleep(2000);
            
         // ================= CLICK FINDINGS =================

            WebElement findingsLink = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//a[@href='/app/project/findings' and .//span[normalize-space()='Findings']]")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    findingsLink
            );

            Thread.sleep(500);

            js.executeScript(
                    "arguments[0].click();",
                    findingsLink
            );

            System.out.println("Findings clicked");

            Thread.sleep(1500);
            
         // ================= SCROLL PAGE FROM TOP TO BOTTOM =================

            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(500);

            js.executeScript(
                    "window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'});"
            );

            Thread.sleep(2000);

            System.out.println("Page scrolled from top to bottom");
            
         // ================= PAGE NAVIGATION =================

         // Scroll to bottom where pagination is located
         js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
         Thread.sleep(1500);

         System.out.println("Scrolled to pagination");

         // ================= NAVIGATE THROUGH ALL PAGES =================

         while (true) {

             // Re-find Next Page every time because DOM refreshes after navigation
             List<WebElement> nextPageList = driver.findElements(
                     By.xpath("//*[@aria-label='Next page']")
             );

             if (nextPageList.isEmpty()) {
                 System.out.println("Next Page not found. Stopping navigation.");
                 break;
             }

             WebElement nextPage = nextPageList.get(0);

             String tagName = nextPage.getTagName();
             String className = nextPage.getAttribute("class");

             System.out.println(
                     "Next Page found - Tag: " + tagName
             );

             // If Next Page is a disabled span, we reached the last page
             boolean isDisabled =
                     "span".equalsIgnoreCase(tagName)
                     || className.contains("cursor-not-allowed")
                     || className.contains("opacity-40")
                     || "true".equals(nextPage.getAttribute("disabled"));

             if (isDisabled) {

                 System.out.println("Reached the last page.");
                 break;
             }

             // Scroll Next Page into view
             js.executeScript(
                     "arguments[0].scrollIntoView({block:'center'});",
                     nextPage
             );

             Thread.sleep(700);

             // Click Next Page
             js.executeScript(
                     "arguments[0].click();",
                     nextPage
             );

             System.out.println("Next page clicked");

             // Wait for page content to refresh
             Thread.sleep(1500);

             // Scroll back to bottom after page refresh
             js.executeScript(
                     "window.scrollTo(0, document.body.scrollHeight);"
             );

             Thread.sleep(1000);
         }

         System.out.println("Completed navigation through all pages");


         // ================= RETURN TO FIRST PAGE =================

         // Scroll to bottom again
         js.executeScript(
                 "window.scrollTo(0, document.body.scrollHeight);"
         );

         Thread.sleep(1000);

         // Find Page 1 again after the DOM refresh
         WebElement firstPage = wait.until(
                 ExpectedConditions.presenceOfElementLocated(
                         By.xpath("//button[@aria-label='Page 1']")
                 )
         );

         js.executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 firstPage
         );

         Thread.sleep(700);

         // Click Page 1 using JavaScript to avoid header/overlay interception
         js.executeScript(
                 "arguments[0].click();",
                 firstPage
         );

         System.out.println("Returned to Page 1");

         Thread.sleep(1500);
         
         // ================= CLICK LICENSES =================

         WebElement licensesButton = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//button[.//span[normalize-space()='Licenses']]")
                 )
         );

         js.executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 licensesButton
         );

         Thread.sleep(800);

         licensesButton.click();

         System.out.println("Licenses clicked");

         Thread.sleep(1500);
         
      // ================= SEARCH LICENSES =================

         By searchLicensesLocator =
                 By.xpath("//input[@placeholder='Search licenses…']");

         WebElement searchLicenses = wait.until(
                 ExpectedConditions.elementToBeClickable(searchLicensesLocator)
         );

         js.executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 searchLicenses
         );

         Thread.sleep(500);

         searchLicenses.click();
         searchLicenses.sendKeys("testing");

         System.out.println("Typed 'testing' in Search licenses");

         Thread.sleep(1500);


         // ================= CLEAR SEARCH =================

         // Re-locate after React/UI update
         searchLicenses = wait.until(
                 ExpectedConditions.elementToBeClickable(searchLicensesLocator)
         );

         searchLicenses.clear();

         System.out.println("Search licenses cleared");

         Thread.sleep(1000);
         // ================= CLICK VULNERABILITIES =================

         WebElement vulnerabilitiesButton = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//button[.//span[normalize-space()='Vulnerabilities']]")
                 )
         );

         js.executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 vulnerabilitiesButton
         );

         Thread.sleep(800);

         // Re-locate before clicking to avoid stale element
         vulnerabilitiesButton = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//button[.//span[normalize-space()='Vulnerabilities']]")
                 )
         );

         vulnerabilitiesButton.click();

         System.out.println("Vulnerabilities clicked");

         Thread.sleep(1500);
         
         
      // ================= CHECK AND UNCHECK SAST =================

         WebElement sastCheckbox = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//button[.//span[contains(@class,'truncate') and normalize-space()='SAST']]")
                 )
         );

         js.executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 sastCheckbox
         );

         Thread.sleep(500);

         js.executeScript("arguments[0].click();", sastCheckbox);

         System.out.println("SAST checkbox checked");

         Thread.sleep(1000);

         // Uncheck SAST
         sastCheckbox = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//button[.//span[contains(@class,'truncate') and normalize-space()='SAST']]")
                 )
         );

         js.executeScript("arguments[0].click();", sastCheckbox);

         System.out.println("SAST checkbox unchecked");

         Thread.sleep(1000);
         
      // ================= CHECK DEVELOPMENT =================

         By developmentButtonLocator = By.xpath(
                 "//button[.//span[normalize-space()='Development']]"
         );

         WebElement developmentButton = wait.until(
                 ExpectedConditions.elementToBeClickable(developmentButtonLocator)
         );

         js.executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 developmentButton
         );

         Thread.sleep(500);

         // Click the entire Development button
         js.executeScript(
                 "arguments[0].click();",
                 developmentButton
         );

         System.out.println("Development checkbox clicked");

         Thread.sleep(1500);


         // ================= CLICK RESET =================

         // Re-locate Reset after the UI re-renders
         By resetButtonLocator = By.xpath(
                 "//button[normalize-space()='Reset']"
         );

         WebElement resetButton = wait.until(
                 ExpectedConditions.elementToBeClickable(resetButtonLocator)
         );

         js.executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 resetButton
         );

         Thread.sleep(500);

         js.executeScript(
                 "arguments[0].click();",
                 resetButton
         );

         System.out.println("Reset clicked");

         Thread.sleep(1500);



         // ================= CHECK AND UNCHECK NODE-TEST =================

         WebElement nodeTestCheckbox = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath(
                             "//button[.//span[contains(@class,'truncate') and normalize-space()='node-test']]"
                         )
                 )
         );

         js.executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 nodeTestCheckbox
         );

         Thread.sleep(500);

         js.executeScript("arguments[0].click();", nodeTestCheckbox);

         System.out.println("node-test checkbox checked");

         Thread.sleep(1000);

         // Uncheck node-test
         nodeTestCheckbox = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath(
                             "//button[.//span[contains(@class,'truncate') and normalize-space()='node-test']]"
                         )
                 )
         );

         js.executeScript("arguments[0].click();", nodeTestCheckbox);

         System.out.println("node-test checkbox unchecked");

         Thread.sleep(1000);
         
      // ================= SEARCH FINDINGS =================

         WebElement findingsSearch1 = wait.until(
                 ExpectedConditions.presenceOfElementLocated(
                         By.xpath("//input[@placeholder='Search findings…']")
                 )
         );

         js.executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 findingsSearch1
         );

         Thread.sleep(800);

         // Re-locate before clicking to avoid stale element
         findingsSearch1 = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//input[@placeholder='Search findings…']")
                 )
         );

         findingsSearch1.click();
         findingsSearch1.sendKeys("script/run-gn-format.py:12");

         System.out.println("script/run-gn-format.py:12 in Search findings");

         Thread.sleep(1500);


         // ================= CLEAR SEARCH =================

         findingsSearch1 = wait.until(
                 ExpectedConditions.presenceOfElementLocated(
                         By.xpath("//input[@placeholder='Search findings…']")
                 )
         );

         findingsSearch1.clear();

         System.out.println("Search findings cleared");

         Thread.sleep(1500);
         
         WebElement findingsSearch11 = wait.until(
                 ExpectedConditions.presenceOfElementLocated(
                         By.xpath("//input[@placeholder='Search findings…']")
                 )
         );

         js.executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 findingsSearch11
         );

         Thread.sleep(800);

         // Re-locate before clicking to avoid stale element
         findingsSearch11 = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//input[@placeholder='Search findings…']")
                 )
         );

         findingsSearch11.click();
         findingsSearch11.sendKeys("script/run-gn-format.py:18");

         System.out.println("script/run-gn-format.py:18 in Search findings");

         Thread.sleep(1500);
         
      // ================= CLICK CLEAR ALL =================

         WebElement clearAll = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//button[normalize-space()='Clear all']")
                 )
         );

         js.executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 clearAll
         );

         Thread.sleep(500);

         clearAll.click();

         System.out.println("Clear all clicked");

         Thread.sleep(1000);
         
            
            driver.quit();

            System.out.println("Browser closed");
            
                } catch (Exception e) {
               e.printStackTrace();
           } finally {
               driver.quit();
           }
       }
   }