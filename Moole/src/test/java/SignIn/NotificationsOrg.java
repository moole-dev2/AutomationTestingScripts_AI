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
import java.util.Scanner;
import org.testng.annotations.Test;



public class NotificationsOrg {

    @Test
    public void NotificationsOrgTest() throws InterruptedException {

        // --- ChromeOptions to use existing profile ---
        

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        
        JavascriptExecutor js = (JavascriptExecutor) driver;


        try {
            // --- Step 1: Open Moole.ai and click Sign In ---
        	driver.get(ConfigReader.getProperty("baseUrl"));
            driver.manage().window().maximize();

            driver.get("https://moole.ai/auth/signin");

            Thread.sleep(5000);

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
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            Thread.sleep(2000);

            // --- Step 4: Navigate directly to Integrations page ---
            driver.get("https://moole.ai/app/settings/project/integrations");
            Thread.sleep(500);
            
            WebElement notifications = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[@href='/app/settings/organization/notifications']")));

            js.executeScript("arguments[0].scrollIntoView(true);", notifications);
            js.executeScript("arguments[0].click();", notifications);

            System.out.println("Notifications page clicked successfully.");
            Thread.sleep(2000);
            
            WebElement notificationToggle = wait.until(
            	    ExpectedConditions.presenceOfElementLocated(
            	        By.xpath("//button[@role='switch' and @aria-label='Enable notifications']")
            	    )
            	);

            	((JavascriptExecutor) driver).executeScript(
            	    "arguments[0].scrollIntoView({block:'center'});",
            	    notificationToggle
            	);

            	Thread.sleep(1000);

            	((JavascriptExecutor) driver).executeScript(
            	    "arguments[0].click();",
            	    notificationToggle
            	);
            
       /*     WebElement allDropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Sort by All']")));

            js.executeScript("arguments[0].click();", allDropdown);
            Thread.sleep(2000);
            WebElement critical = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[normalize-space()='Critical']")));

            critical.click();
            Thread.sleep(2000);*/
            	
         /*   	// Click the "Sort by" dropdown
            	

            	// Find dropdown
            	WebElement dropdown = wait.until(
            	        ExpectedConditions.presenceOfElementLocated(
            	                By.xpath("//button[contains(@aria-label,'Sort by')]")));

            	js.executeScript("arguments[0].scrollIntoView({block:'center'});", dropdown);
            	Thread.sleep(1000);

            	// Highlight (optional)
            	js.executeScript("arguments[0].style.border='3px solid red';", dropdown);

            	// Click using JavaScript
            	js.executeScript("arguments[0].click();", dropdown);

            	Thread.sleep(3000);

            	// Now locate Critical
            	WebElement critical = wait.until(
            	        ExpectedConditions.presenceOfElementLocated(
            	                By.xpath("//p[text()='Critical']")));

            	js.executeScript("arguments[0].scrollIntoView({block:'center'});", critical);

            	Thread.sleep(1000);

            	js.executeScript("arguments[0].click();", critical);*/
            	
            	// Click Severity dropdown
            	WebElement severityDropdown = wait.until(
            	        ExpectedConditions.elementToBeClickable(
            	                By.xpath("//button[@aria-label='Severity']")));

            	js.executeScript(
            	        "arguments[0].scrollIntoView({block:'center'});",
            	        severityDropdown);

            	Thread.sleep(1000);

            	// Highlight Severity dropdown
            	js.executeScript(
            	        "arguments[0].style.border='3px solid red';",
            	        severityDropdown);

            	// Click Severity dropdown
            	js.executeScript(
            	        "arguments[0].click();",
            	        severityDropdown);

            	Thread.sleep(2000);

            	WebElement critical = wait.until(
            	        ExpectedConditions.elementToBeClickable(
            	                By.xpath("//div[contains(@class,'cursor-pointer') and .//span[normalize-space()='Critical']]")
            	        )
            	);

            	js.executeScript(
            	        "arguments[0].scrollIntoView({block:'center'});",
            	        critical
            	);

            	Thread.sleep(1000);

            	// Highlight Critical
            	js.executeScript(
            	        "arguments[0].style.border='3px solid red';",
            	        critical
            	);

            	// Click Critical
            	js.executeScript(
            	        "arguments[0].click();",
            	        critical
            	);

            	Thread.sleep(2000);

            	System.out.println("Critical selected successfully.");
            	
            
            	// ================= UPDATE BUTTON =================

            	WebElement updateBtn = wait.until(
            	        ExpectedConditions.elementToBeClickable(
            	                By.xpath("//button[@type='button' and normalize-space()='Update']")
            	        )
            	);

            	js.executeScript(
            	        "arguments[0].scrollIntoView({block:'center'});",
            	        updateBtn
            	);

            	Thread.sleep(1000);

            	// Highlight Update button
            	js.executeScript(
            	        "arguments[0].style.border='3px solid red';",
            	        updateBtn
            	);

            	// Click Update
            	js.executeScript(
            	        "arguments[0].click();",
            	        updateBtn
            	);

            	Thread.sleep(2000);

            	System.out.println("Update button clicked successfully.");
       /*     WebElement notificationToggle1 = wait.until(
            	    ExpectedConditions.presenceOfElementLocated(
            	        By.xpath("//button[@role='switch' and @aria-label='Enable notifications']")
            	    )
            	);

            	((JavascriptExecutor) driver).executeScript(
            	    "arguments[0].scrollIntoView({block:'center'});",
            	    notificationToggle1
            	);

            	Thread.sleep(1000);

            	((JavascriptExecutor) driver).executeScript(
            	    "arguments[0].click();",
            	    notificationToggle1
            	);*/
            	
            	// Open Actions Menu
            	WebElement actionsMenu = wait.until(
            	        ExpectedConditions.elementToBeClickable(
            	                By.xpath("//button[@aria-label='Open actions menu']")));


            	js.executeScript("arguments[0].scrollIntoView({block:'center'});", actionsMenu);
            	Thread.sleep(1000);

            	// Click
            	js.executeScript("arguments[0].click();", actionsMenu);

            	Thread.sleep(2000);

            	System.out.println("Actions menu opened successfully.");
            
            	WebElement editEmailChannels = wait.until(
            	        ExpectedConditions.elementToBeClickable(
            	                By.xpath("//button[@role='menuitem' and .//span[text()='Edit Email channels']]")));

            	((JavascriptExecutor) driver).executeScript("arguments[0].click();", editEmailChannels);

            	Thread.sleep(2000);

            
            WebElement ccInput = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("ncc-cc")));

            ccInput.clear();
            ccInput.sendKeys("moole.dev@gmail.com",",","moole.dev.@gmail.com");
            Thread.sleep(2000);

            
            WebElement saveBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[normalize-space()='Save']]")));

            saveBtn.click();

            System.out.println("Email updated and saved successfully.");
            Thread.sleep(2000);
            
         // Open Actions Menu
        	WebElement actionsMenu1 = wait.until(
        	        ExpectedConditions.elementToBeClickable(
        	                By.xpath("//button[@aria-label='Open actions menu']")));


        	js.executeScript("arguments[0].scrollIntoView({block:'center'});", actionsMenu1);
        	Thread.sleep(1000);

        	// Click
        	js.executeScript("arguments[0].click();", actionsMenu1);

        	Thread.sleep(2000);

        	System.out.println("Actions menu opened successfully.");
        
        	WebElement editEmailChannels1 = wait.until(
        	        ExpectedConditions.elementToBeClickable(
        	                By.xpath("//button[@role='menuitem' and .//span[text()='Edit Email channels']]")));

        	((JavascriptExecutor) driver).executeScript("arguments[0].click();", editEmailChannels1);

        	Thread.sleep(2000);
            
         // Click Cancel button
            WebElement cancelBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[normalize-space()='Cancel']")));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", cancelBtn);
            Thread.sleep(1000);

            // Highlight (optional)
            js.executeScript("arguments[0].style.border='3px solid red';", cancelBtn);

            // Click using JavaScript
            js.executeScript("arguments[0].click();", cancelBtn);

            Thread.sleep(2000);

            System.out.println("Cancel button clicked successfully.");
            	
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}