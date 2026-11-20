package SignIn;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utils.ConfigReader;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import org.testng.annotations.Test;



public class ContainerSecurity {

    @Test
    public void IntegrationGitHubTest() throws InterruptedException {

        // --- ChromeOptions to use existing profile ---
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\psiri\\AppData\\Local\\Google\\User Data");
        options.addArguments("profile-directory=Profile 1");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;


        try {
            // --- Step 1: Open Moole.ai and click Sign In ---
        	driver.get(ConfigReader.getProperty("baseUrl"));
            driver.manage().window().maximize();
            driver.get("https://moole.ai/auth/signin");
            Thread.sleep(2000);

            // --- Step 2: Enter Email ---
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@type='email']")));
            emailField.sendKeys("moole.dev.2@gmail.com");

            WebElement signIn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@data-tour='signup-submit']")
                    )
            );

            signIn.click();

            System.out.println("Sign in button clicked");
            // --- Step 3: Wait for OTP manually ---
            System.out.println("Please enter your OTP manually in the browser, then press Enter here...");
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            Thread.sleep(1000);

            // --- Step 4: Navigate directly to Integrations page ---
            driver.get("https://moole.ai/app/settings/project/integrations");
           
            
            // ================= CLICK CONTAINER IMAGES =================
                    WebElement containerImages = wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    By.xpath("//a[@href='/app/project/container-security' and .//span[normalize-space()='Container Images']]")
                            )
                    );

                    js.executeScript("arguments[0].scrollIntoView({block:'center'});", containerImages);
                    Thread.sleep(500);
                    js.executeScript("arguments[0].click();", containerImages);

                    System.out.println("Container Images clicked");
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

		            WebElement checkbox1 = wait.until(
		                    ExpectedConditions.elementToBeClickable(
		                            By.xpath("//div[contains(@class,'peer-checked:bg-gradient-to-r')]")
		                    )
		            );

		            js.executeScript(
		                    "arguments[0].scrollIntoView({block:'center'});",
		                    checkbox1
		            );

		            Thread.sleep(500);

		            checkbox1.click();

		            System.out.println("Checkbox clicked");

		            Thread.sleep(1000);


		            // ================= CLOSE FILTER =================

		            filterButton.click();

		            System.out.println("Filter dropdown closed");

		            Thread.sleep(1000);
		            
		        // ================= SEARCH IMAGE TAG 1.2 =================

		            By imageTagSearch = By.xpath(
		                "//input[@placeholder='Search by image name and tag']"
		            );

		            WebElement searchInput = wait.until(
		                ExpectedConditions.visibilityOfElementLocated(imageTagSearch)
		            );

		            // Enter 1.2
		            searchInput.sendKeys("1.2");

		            System.out.println("Entered image tag 1.2");

		            Thread.sleep(2000);

		            // ================= CLEAR SEARCH FIELD =================

		            searchInput.clear();

		            System.out.println("Search field cleared");

		            // ================= SEARCH IMAGE TAG 1.3 =================

		            searchInput.sendKeys("1.3");

		            System.out.println("Entered image tag 1.3");

		            Thread.sleep(2000);
		            

		            // ================= CLEAR SEARCH FIELD AGAIN =================

		            searchInput.clear();

		            System.out.println("Search field cleared again");
		                 
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

		               Thread.sleep(15000);
		               
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
		               
                    
                    
                    
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            driver.quit();
        }
    }
}
            