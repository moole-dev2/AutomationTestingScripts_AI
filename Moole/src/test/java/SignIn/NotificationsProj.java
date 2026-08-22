package SignIn;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utils.ConfigReader;
import java.time.Duration;
import java.util.Scanner;
import org.testng.annotations.Test;



public class NotificationsProj {

    @Test
    public void NotoficationsProjTest() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
        	driver.get(ConfigReader.getProperty("baseUrl"));

            // ================= LOGIN =================
            driver.get("https://moole.ai/auth/signin");
            driver.manage().window().maximize();
            Thread.sleep(5000);

            WebElement emailField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@type='email']")));

            emailField.sendKeys("moole.dev.2@gmail.com");

            WebElement signIn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@data-tour='signup-submit']")
                    )
            );

            signIn.click();

            System.out.println("Sign in button clicked");

            System.out.println("Enter OTP manually and press Enter...");
            new Scanner(System.in).nextLine();
            Thread.sleep(2000);

            // ================= NAVIGATE =================
            driver.get("https://moole.ai/app/settings/project/integrations");
            Thread.sleep(2000);

            WebElement notificationsLink = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[@href='/app/settings/project/notifications']")));

            js.executeScript("arguments[0].click();", notificationsLink);
            System.out.println("Notifications page opened");

            // ================= TOGGLE =================
            
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
            	
            	// Find dropdown
            	// ================= SEVERITY DROPDOWN =================

            	By severityDropdown = By.xpath(
            	        "//button[@type='button' " +
            	        "and @aria-haspopup='listbox' " +
            	        "and @aria-label='Severity']"
            	);

            	WebElement severity = wait.until(
            	        ExpectedConditions.elementToBeClickable(severityDropdown)
            	);

            	js.executeScript(
            	        "arguments[0].scrollIntoView({block:'center'});",
            	        severity
            	);

            	Thread.sleep(500);

            	// Highlight dropdown
            	js.executeScript(
            	        "arguments[0].style.border='3px solid red';",
            	        severity
            	);

            	Thread.sleep(500);

            	// Click dropdown
            	js.executeScript(
            	        "arguments[0].click();",
            	        severity
            	);

            	System.out.println("Severity dropdown clicked successfully");

            	Thread.sleep(1000);
            	// Now locate Critical
            	// ================= SELECT CRITICAL =================

            	By criticalOption = By.xpath(
            	        "//div[contains(@class,'cursor-pointer') and .//span[normalize-space()='Critical']]"
            	);

            	WebElement critical = wait.until(
            	        ExpectedConditions.elementToBeClickable(criticalOption)
            	);

            	js.executeScript(
            	        "arguments[0].scrollIntoView({block:'center'});",
            	        critical
            	);

            	Thread.sleep(500);

            	// Highlight Critical
            	js.executeScript(
            	        "arguments[0].style.border='3px solid red';",
            	        critical
            	);

            	Thread.sleep(500);

            	// Click Critical
            	js.executeScript(
            	        "arguments[0].click();",
            	        critical
            	);

            	System.out.println("Critical selected successfully");

            	Thread.sleep(1000);
            
            	
        	
         // ================= RE-FETCH UPDATE BUTTON =================
         WebElement updateBtn = wait.until(
                 ExpectedConditions.visibilityOfElementLocated(
                         By.xpath("//button[contains(normalize-space(),'Update')]")));

         wait.until(ExpectedConditions.elementToBeClickable(updateBtn));

         js.executeScript("arguments[0].click();", updateBtn);

         System.out.println("Update clicked successfully");

      /*      // ================= EDIT EMAIL =================
            WebElement editBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Edit configured emails']")));

            js.executeScript("arguments[0].click();", editBtn);

            WebElement toField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("create-ncc-to")));

            toField.clear();
            toField.sendKeys("moole.dev.2@gmail.com");

            WebElement ccField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("create-ncc-cc")));

            ccField.clear();
            ccField.sendKeys("moole.dev.2@gmail.com");

            WebElement createBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[normalize-space()='Create']]")));

            js.executeScript("arguments[0].click();", createBtn);

            System.out.println("Email channel created successfully");*/
         
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