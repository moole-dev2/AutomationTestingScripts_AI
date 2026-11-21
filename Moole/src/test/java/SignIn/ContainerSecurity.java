package SignIn;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
		            searchInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		            searchInput.sendKeys(Keys.BACK_SPACE);
		            
		                 
		            // ================= ACTIONS BUTTON =================


		            WebElement actionsImage = wait.until(
		                    ExpectedConditions.elementToBeClickable(
		                            By.xpath("//button[@aria-label='Actions for mooletaran/my-java-cve-image']")
		                    )
		            );

		            js.executeScript("arguments[0].scrollIntoView({block:'center'});", actionsImage);
		            Thread.sleep(500);

		            actionsImage.click();

		            System.out.println("Actions for mooletaran/my-java-cve-image clicked");

		            Thread.sleep(1000);		              
		         // ================= CLICK REMOVE IMAGE =================

		            WebElement removeImage = wait.until(
		                    ExpectedConditions.elementToBeClickable(
		                            By.xpath("//button[@role='menuitem' and .//span[normalize-space()='Remove image']]")
		                    )
		            );

		            removeImage.click();

		            System.out.println("Remove image clicked");

		            Thread.sleep(1000);

		            // ================= CLICK CANCEL BUTTON =================

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
		                               By.xpath("//button[.//span[normalize-space()='Production'] and .//span[normalize-space()='Add to environment']]")
		                       )
		               );

		               productionButton.click();

		               System.out.println("Add to Production clicked");

		               Thread.sleep(1000);


		               // ================= CLICK REMOVE FROM PRODUCTION ICON =================

		               WebElement removeProductionIcon = wait.until(
		                       ExpectedConditions.elementToBeClickable(
		                               By.xpath("//button[@aria-label='Remove from Production']")
		                       )
		               );

		               removeProductionIcon.click();

		               System.out.println("Remove from Production icon clicked");

		               Thread.sleep(1000);
		               
		            // ================= CONFIRM REMOVE BUTTON =================

		               WebElement removeButton = wait.until(
		                       ExpectedConditions.elementToBeClickable(
		                               By.xpath("//button[normalize-space()='Remove']")
		                       )
		               );

		               removeButton.click();

		               System.out.println("Remove button clicked");

		               Thread.sleep(1000);
		               
		            // ================= CLICK ACTIONS FOR 1.2 =================

		               WebElement actionsForTag = wait.until(
		                       ExpectedConditions.elementToBeClickable(
		                               By.xpath("//button[@aria-label='Actions for 1.2']")
		                       )
		               );

		               actionsForTag.click();

		               System.out.println("Actions for 1.2 clicked");

		               Thread.sleep(1000);


		               // ================= CLICK STOP TRACKING THIS TAG =================

		               WebElement stopTrackingTag = wait.until(
		                       ExpectedConditions.elementToBeClickable(
		                               By.xpath("//button[@role='menuitem' and .//span[normalize-space()='Stop tracking this tag']]")
		                       )
		               );

		               stopTrackingTag.click();

		               System.out.println("Stop tracking this tag clicked");

		               Thread.sleep(1000);


		               // ================= CLICK STOP TRACKING BUTTON =================

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
		               
		               // ================= CLICK ACTIONS FOR 1.2 =================

		               WebElement actionsForTag1 = wait.until(
		                       ExpectedConditions.elementToBeClickable(
		                               By.xpath("//button[@aria-label='Actions for 1.2']")
		                       )
		               );

		               actionsForTag1.click();

		               System.out.println("Actions for 1.2 clicked");

		               Thread.sleep(1000);
		               
		            // ================= CLICK RESCAN MENU ITEM =================

		               WebElement rescanMenu = wait.until(
		                       ExpectedConditions.elementToBeClickable(
		                               By.xpath("//button[@role='menuitem' and .//span[normalize-space()='Rescan']]")
		                       )
		               );

		               rescanMenu.click();

		               System.out.println("Rescan clicked");

		               // ================= WAIT FOR SCAN =================

		               Thread.sleep(15000);

		               System.out.println("Waited 15 seconds for rescan to complete");
		               
		            // ================= CLICK TRACK ANOTHER TAG =================

		               WebElement trackAnotherTag = wait.until(
		                       ExpectedConditions.elementToBeClickable(
		                               By.xpath("//button[contains(.,'Track another tag from') and contains(.,'mooletaran/my-java-cve-image')]")
		                       )
		               );

		               trackAnotherTag.click();

		               System.out.println("Track another tag clicked");

		               Thread.sleep(1000);


		               // ================= ENTER TAG 1.3 =================

		               WebElement tagSearch = wait.until(
		                       ExpectedConditions.visibilityOfElementLocated(
		                               By.xpath("//input[@placeholder='Search tags…']")
		                       )
		               );

		               tagSearch.sendKeys("1.3");

		               System.out.println("Entered tag 1.3");

		               Thread.sleep(1500);


		               // ================= CLEAR SEARCH USING BACKSPACE =================

		               tagSearch.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		               tagSearch.sendKeys(Keys.BACK_SPACE);

		               System.out.println("Search field cleared using Backspace");

		               Thread.sleep(1000);


		               // ================= CLICK TAG ENVIRONMENT DROPDOWN =================

		               WebElement tagEnvironment = wait.until(
		                       ExpectedConditions.elementToBeClickable(
		                               By.xpath("//button[@aria-label='Tag environment…']")
		                       )
		               );

		               tagEnvironment.click();

		               System.out.println("Tag environment dropdown clicked");

		               Thread.sleep(1000);


		               // ================= SELECT PRODUCTION =================

		               WebElement selectProduction = wait.until(
		                       ExpectedConditions.elementToBeClickable(
		                               By.xpath("//div[contains(@class,'cursor-pointer') and .//span[normalize-space()='Production']]")
		                       )
		               );

		               selectProduction.click();

		               System.out.println("Production selected");

		               Thread.sleep(1000);


		               // ================= CLICK TRACK BUTTON =================

		               WebElement trackButton = wait.until(
		                       ExpectedConditions.elementToBeClickable(
		                               By.xpath("//button[.//span[normalize-space()='Track']]")
		                       )
		               );

		               trackButton.click();

		               System.out.println("Track button clicked");

		               Thread.sleep(15000);
		               

		               WebElement actionsForTag11 = wait.until(
		                       ExpectedConditions.elementToBeClickable(
		                               By.xpath("//button[@aria-label='Actions for 1.2']")
		                       )
		               );

		               actionsForTag11.click();

		               System.out.println("Actions for 1.2 clicked");

		               Thread.sleep(1000);
		               

		               // ================= CLICK STOP TRACKING THIS TAG =================

		               WebElement stopTrackingTag1 = wait.until(
		                       ExpectedConditions.elementToBeClickable(
		                               By.xpath("//button[@role='menuitem' and .//span[normalize-space()='Stop tracking this tag']]")
		                       )
		               );

		               stopTrackingTag1.click();

		               System.out.println("Stop tracking this tag clicked");

		               Thread.sleep(1000);


		               // ================= CLICK STOP TRACKING BUTTON =================

		               WebElement stopTrackingButton1 = wait.until(
		                       ExpectedConditions.elementToBeClickable(
		                               By.xpath("//button[@type='submit' and .//span[normalize-space()='Stop tracking']]")
		                       )
		               );

		               stopTrackingButton1.click();

		               System.out.println("Stop tracking confirmed");

		               Thread.sleep(2000);
		               
	            
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            driver.quit();
        }
    }
}
            