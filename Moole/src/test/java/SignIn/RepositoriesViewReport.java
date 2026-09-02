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



public class RepositoriesViewReport {

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
            
         // ================= CLICK REPOSITORIES =================

            By repositories = By.xpath(
                    "//a[@href='/app/project/list-repos' and .//span[text()='Repositories']]"
            );

            WebElement repo = wait.until(
                    ExpectedConditions.presenceOfElementLocated(repositories)
            );

            js.executeScript("arguments[0].click();", repo);

            System.out.println("Repositories clicked successfully");

            Thread.sleep(1500);
            // ================= ACTIONS BUTTON =================


            WebElement actionsMain = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Actions for main']")
                    )
            );

            actionsMain.click();

            System.out.println("Actions for main clicked");

            Thread.sleep(1000);
          // ================= CLICK VIEW REPORT =================

             WebElement viewReport = wait.until(
                     ExpectedConditions.elementToBeClickable(
                             By.xpath("//a[@role='menuitem' and .//span[normalize-space()='View Report']]")
                     )
             );

             js.executeScript("arguments[0].scrollIntoView({block:'center'});", viewReport);
             Thread.sleep(500);
             viewReport.click();

             System.out.println("View Report clicked");
             Thread.sleep(2000);
             
             // ================= SCROLL DOWN =================

             js.executeScript("window.scrollBy(0, 500);");

             System.out.println("Scrolled down");

             Thread.sleep(1000);
             


             // ================= CLICK FILTER =================

             WebElement filterButton = wait.until(
                     ExpectedConditions.elementToBeClickable(
                             By.xpath("//button[@aria-label='Filter']")
                     )
             );

             filterButton.click();

             System.out.println("Filter clicked");
             Thread.sleep(1000);


             // ================= CHECK FILTER BOX =================

             // Since the checkbox itself is represented by a div,
             // locate the checkbox using the peer-checked class.
             WebElement filterCheckbox = wait.until(
                     ExpectedConditions.presenceOfElementLocated(
                             By.xpath("//div[contains(@class,'peer-checked:bg-gradient-to-r')]")
                     )
             );

             js.executeScript("arguments[0].click();", filterCheckbox);

             System.out.println("Filter checkbox checked");
             Thread.sleep(1000);


             // ================= UNCHECK SAME FILTER BOX =================

             js.executeScript("arguments[0].click();", filterCheckbox);

             System.out.println("Filter checkbox unchecked");
             Thread.sleep(1000);


             // ================= CLOSE FILTER DROPDOWN =================

             filterButton = wait.until(
                     ExpectedConditions.elementToBeClickable(
                             By.xpath("//button[@aria-label='Filter']")
                     )
             );

             filterButton.click();

             System.out.println("Filter dropdown closed");
             Thread.sleep(1000);


             // ================= SEARCH SQL INJECTION =================

             WebElement searchFindings = wait.until(
                     ExpectedConditions.elementToBeClickable(
                             By.xpath("//input[@placeholder='Search findings']")
                     )
             );

             searchFindings.click();
             searchFindings.sendKeys("SQL Injection");

             System.out.println("Searched for SQL Injection");
             Thread.sleep(2000);


             // ================= BACKSPACE ONE BY ONE =================

             // Remove "SQL Injection" one character at a time
             for (int i = 0; i < "SQL Injection".length(); i++) {
                 searchFindings.sendKeys(Keys.BACK_SPACE);
                 Thread.sleep(150);
             }

             System.out.println("Search text removed using Backspace");
             Thread.sleep(1000);


          // ================= CLICK TYPE DROPDOWN =================


             WebElement typeButton = wait.until(
                     ExpectedConditions.elementToBeClickable(
                             By.xpath("//button[@aria-label='Type']")
                     )
             );

             typeButton.click();

             System.out.println("Type dropdown clicked");

             Thread.sleep(1000);


             // ================= CHECK TYPE CHECKBOX =================

             WebElement typeCheckbox = wait.until(
                     ExpectedConditions.presenceOfElementLocated(
                             By.xpath("//div[contains(@class,'peer-checked:bg-gradient-to-r')]")
                     )
             );

             js.executeScript("arguments[0].click();", typeCheckbox);

             System.out.println("Type checkbox checked");
             Thread.sleep(1000);


             // ================= UNCHECK TYPE CHECKBOX =================

             js.executeScript("arguments[0].click();", typeCheckbox);

             System.out.println("Type checkbox unchecked");
             Thread.sleep(1000);


             // ================= CLOSE TYPE DROPDOWN =================

             typeButton = wait.until(
                     ExpectedConditions.elementToBeClickable(
                             By.xpath("//button[@aria-label='Type']")
                     )
             );

             typeButton.click();

             System.out.println("Type dropdown closed");
             Thread.sleep(1000);
             
          // ================= CLICK PLUS BUTTON =================

             WebElement plusButton = wait.until(
                     ExpectedConditions.elementToBeClickable(
                             By.xpath("//span[normalize-space()='+']")
                     )
             );

             plusButton.click();

             System.out.println("Plus (+) button clicked");

             Thread.sleep(1000);
             
          // ================= SCROLL DOWN =================

             js.executeScript("window.scrollBy(0, 500);");

             System.out.println("Scrolled down");

             Thread.sleep(1000);
             
          // ================= CLICK FINDING =================

             WebElement finding = wait.until(
                     ExpectedConditions.elementToBeClickable(
                             By.xpath("//li[.//span[normalize-space()='script/verify-mksnapshot.py'] and .//span[normalize-space()='[33:8]']]")
                     )
             );

             js.executeScript(
                     "arguments[0].scrollIntoView({block:'center'});",
                     finding
             );

             Thread.sleep(500);

             finding.click();

             System.out.println("Finding script/verify-mksnapshot.py clicked");

             Thread.sleep(1500);


             // ================= SCROLL DOWN =================

             js.executeScript("window.scrollBy(0, 500);");

             System.out.println("Scrolled down");

             Thread.sleep(1000);


             // ================= CLICK verify-mksnapshot.py =================

             WebElement verifyFile = wait.until(
                     ExpectedConditions.elementToBeClickable(
                             By.xpath("//span[normalize-space()='verify-mksnapshot.py']")
                     )
             );

             js.executeScript(
                     "arguments[0].scrollIntoView({block:'center'});",
                     verifyFile
             );

             Thread.sleep(500);

             verifyFile.click();

             System.out.println("verify-mksnapshot.py clicked");

             Thread.sleep(1500);


             // ================= CLICK CLOSE =================

             WebElement closeButton = wait.until(
                     ExpectedConditions.elementToBeClickable(
                             By.xpath("//button[@aria-label='Close']")
                     )
             );

             closeButton.click();

             System.out.println("Close button clicked");

             Thread.sleep(1000);


             // ================= CLICK MINUS BUTTON =================

             WebElement minusButton = wait.until(
            	        ExpectedConditions.presenceOfElementLocated(
            	                By.xpath("//span[normalize-space()='−']")
            	        )
            	);

            	js.executeScript("arguments[0].scrollIntoView({block:'center'});", minusButton);

            	Thread.sleep(500);

            	js.executeScript("arguments[0].click();", minusButton);

            	System.out.println("Minus button clicked");
            	Thread.sleep(500);
            	
            	By sastButton = By.xpath(
            	        "//button[.//span[normalize-space()='SAST']]"
            	);

            	WebElement sast = wait.until(
            	        ExpectedConditions.elementToBeClickable(sastButton)
            	);

            	js.executeScript(
            	        "arguments[0].scrollIntoView({block:'center'});",
            	        sast
            	);

            	Thread.sleep(500);

            	js.executeScript("arguments[0].click();", sast);

            	System.out.println("SAST selected/opened");

            	Thread.sleep(1500);


            	// ================= CLOSE SAST =================

            	WebElement sastClose = wait.until(
            	        ExpectedConditions.elementToBeClickable(sastButton)
            	);

            	js.executeScript("arguments[0].click();", sastClose);

            	System.out.println("SAST closed");

            	Thread.sleep(1000);
            	
          /*  	
            	WebElement copyButton = wait.until(
            	        ExpectedConditions.elementToBeClickable(
            	                By.xpath("//button[.//svg[@aria-label='Copy']]")
            	        )
            	);

            	js.executeScript("arguments[0].scrollIntoView({block:'center'});", copyButton);

            	js.executeScript("arguments[0].click();", copyButton);

            	System.out.println("Copy button clicked");
            */
            driver.quit();

            System.out.println("Browser closed");
            
               
                   } catch (Exception e) {
                  e.printStackTrace();
              } finally {
                  driver.quit();
              }
          }
      }