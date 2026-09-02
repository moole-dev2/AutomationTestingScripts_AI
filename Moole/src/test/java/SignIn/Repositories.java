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



public class Repositories {

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
         
         // ================= CLICK FILTER DROPDOWN =================

            WebElement filterButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Filter']")
                    )
            );

            filterButton.click();

            Thread.sleep(1000);

            System.out.println("Filter dropdown opened");


            // ================= CLICK CHECKBOX =================

            WebElement checkbox = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[contains(@class,'peer-checked:bg-gradient-to-r')]")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    checkbox
            );

            Thread.sleep(500);

            checkbox.click();

            System.out.println("Checkbox clicked");

            Thread.sleep(1000);


            // ================= CLOSE FILTER =================

            filterButton.click();

            System.out.println("Filter dropdown closed");

            Thread.sleep(1000);






            // ================= OPEN FILTER AGAIN =================

            filterButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Filter']")
                    )
            );

            filterButton.click();

            Thread.sleep(1000);

            System.out.println("Filter dropdown opened again");


            // ================= UNCHECK FILTER =================

            checkbox = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[contains(@class,'peer-checked:bg-gradient-to-r')]")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    checkbox
            );

            Thread.sleep(500);

            checkbox.click();

            System.out.println("Filter unchecked");

            Thread.sleep(1000);


            // ================= CLOSE FILTER =================

            filterButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Filter']")
                    )
            );

            filterButton.click();

            System.out.println("Filter dropdown closed after unchecking");

            Thread.sleep(1000);

            // ================= SEARCH REPOSITORY =================

            WebElement searchRepository = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//input[@placeholder='Search your repository']")
                    )
            );

            searchRepository.click();

            searchRepository.clear();

            searchRepository.sendKeys("node_test");

            System.out.println("Typed 'node_test' in Search Repository");

            Thread.sleep(2000);

         // ================= CLEAR SEARCH USING BACKSPACE =================

            searchRepository.click();

            searchRepository.sendKeys(Keys.CONTROL, "a");
            Thread.sleep(500);

            searchRepository.sendKeys(Keys.BACK_SPACE);

            System.out.println("Search cleared using Backspace");

            Thread.sleep(3000);
            
         // ================= ACTIONS BUTTON =================

            WebElement actionsButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Actions for node-test']")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    actionsButton
            );

            Thread.sleep(500);

            actionsButton.click();

            System.out.println("Actions button clicked");

            Thread.sleep(1000);
         // ================= REMOVE REPOSITORY =================

            WebElement removeRepository = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@role='menuitem' and .//span[normalize-space()='Remove repository']]")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    removeRepository
            );

            Thread.sleep(500);

            removeRepository.click();

            System.out.println("Remove repository clicked");

            Thread.sleep(1000);
            
         // ================= CANCEL BUTTON =================

            WebElement cancelButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[normalize-space()='Cancel']")
                    )
            );

            cancelButton.click();

            System.out.println("Cancel button clicked");

            Thread.sleep(1000);
            
         // ================= CLICK ENV =================

            WebElement envButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[normalize-space()='env']")
                    )
            );

            envButton.click();

            System.out.println("env button clicked");

            Thread.sleep(1000);


            // ================= CLICK ADD TO PRODUCTION =================

            WebElement productionButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//p[normalize-space()='Add to environment'] and .//span[normalize-space()='Production']]")
                    )
            );

            productionButton.click();

            System.out.println("Add to Production clicked");

            Thread.sleep(1000);


            // ================= REMOVE FROM PRODUCTION ICON =================

            WebElement removeProductionIcon = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Remove from Production']")
                    )
            );

            removeProductionIcon.click();

            System.out.println("Remove from Production icon clicked");

            Thread.sleep(1000);


            // ================= CONFIRM REMOVE FROM PRODUCTION =================

            WebElement removeProductionButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[normalize-space()='Remove from Production']")
                    )
            );

            removeProductionButton.click();

            System.out.println("Remove from Production confirmed");

            Thread.sleep(1500);
            
         // ================= ACTIONS FOR MAIN =================

            WebElement actionsMain = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Actions for main']")
                    )
            );

            actionsMain.click();

            System.out.println("Actions for main clicked");

            Thread.sleep(1000);


            // ================= STOP TRACKING THIS BRANCH =================

            WebElement stopTrackingBranch = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@role='menuitem' and .//span[normalize-space()='Stop tracking this branch']]")
                    )
            );

            stopTrackingBranch.click();

            System.out.println("Stop tracking this branch clicked");

            Thread.sleep(1000);


            // ================= STOP TRACKING CONFIRMATION =================

            WebElement stopTrackingButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@type='submit' and .//span[normalize-space()='Stop tracking']]")
                    )
            );

            stopTrackingButton.click();

            System.out.println("Stop tracking confirmed");

            Thread.sleep(1000);


            // ================= CLOSE POPUP =================

            WebElement closePopup = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Close popup']")
                    )
            );

            closePopup.click();

            System.out.println("Popup closed");

            Thread.sleep(1000);
            
         // ================= ACTIONS FOR MAIN AGAIN =================

            WebElement actionsMainAgain = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Actions for main']")
                    )
            );

            actionsMainAgain.click();

            System.out.println("Actions for main clicked again");

            Thread.sleep(1000);


            // ================= CLICK RESCAN MENU ITEM =================

            WebElement rescanMenu = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@role='menuitem' and .//span[normalize-space()='Rescan']]")
                    )
            );

            rescanMenu.click();

            System.out.println("Rescan menu item clicked");

            Thread.sleep(1000);


            // ================= CLICK TOGGLE =================

            WebElement rescanToggle = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@role='switch']")
                    )
            );

            rescanToggle.click();

            System.out.println("Rescan toggle clicked");

            Thread.sleep(1000);


            // ================= CLICK RESCAN BUTTON =================

            WebElement rescanButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[normalize-space()='Rescan']")
                    )
            );

            rescanButton.click();

            System.out.println("Rescan button clicked");

            Thread.sleep(5000);
            
         // ================= TRACK ANOTHER BRANCH =================

            WebElement trackAnotherBranch = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[contains(normalize-space(),'Track another branch from')]]")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    trackAnotherBranch
            );

            Thread.sleep(500);

            trackAnotherBranch.click();

            System.out.println("Track another branch button clicked");

            Thread.sleep(1000);
            
         // ================= CLOSE DRAWER =================

            WebElement closeDrawer = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Close drawer']")
                    )
            );

            closeDrawer.click();

            System.out.println("Close drawer clicked");

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