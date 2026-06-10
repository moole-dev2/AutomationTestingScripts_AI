package SignIn;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Scanner;

public class NotificationsOrg {

    public static void main(String[] args) {

        // --- ChromeOptions to use existing profile ---
        

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        
        JavascriptExecutor js = (JavascriptExecutor) driver;


        try {
            // --- Step 1: Open Moole.ai and click Sign In ---
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();

            driver.get("https://moole.ai/auth/signin");

            Thread.sleep(5000);

            // --- Step 2: Enter Email ---
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@type='email']")));
            emailField.sendKeys("moole.dev.2@gmail.com");

            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Continue')]")));
            continueBtn.click();

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
            
            WebElement allDropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Sort by All']")));

            js.executeScript("arguments[0].click();", allDropdown);
            Thread.sleep(2000);
            WebElement critical = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[normalize-space()='Critical']")));

            critical.click();
            Thread.sleep(2000);
            
            WebElement updateBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[normalize-space()='Update']")));

            js.executeScript("arguments[0].click();", updateBtn);

            System.out.println("Critical selected and Update clicked successfully.");
            Thread.sleep(2000);
            
            
            WebElement editBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Edit configured emails']")));

            editBtn.click();
            Thread.sleep(2000);

            
            WebElement ccInput = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("ncc-cc")));

            ccInput.clear();
            ccInput.sendKeys("moole.dev.7@gmail.com",",","moole.dev.3@gmail.com");
            Thread.sleep(2000);

            
            WebElement saveBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[normalize-space()='Save']]")));

            saveBtn.click();

            System.out.println("Email updated and saved successfully.");
            Thread.sleep(2000);
            
            
            WebElement editBtn1 = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Edit configured emails']")));

            editBtn1.click();
            Thread.sleep(2000);
            
            WebElement cancelBtn = wait.until(
            	    ExpectedConditions.elementToBeClickable(
            	        By.xpath("//button[normalize-space()='Cancel']")
            	    )
            	);

            	cancelBtn.click();
                System.out.println("Cancel button clicked successfully");
            	Thread.sleep(1000);
            	
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}