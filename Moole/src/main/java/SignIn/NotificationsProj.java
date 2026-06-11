package SignIn;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Scanner;

public class NotificationsProj {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {

            // ================= LOGIN =================
            driver.get("https://moole.ai/auth/signin");
            driver.manage().window().maximize();
            Thread.sleep(5000);

            WebElement emailField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@type='email']")));

            emailField.sendKeys("moole.dev.2@gmail.com");

            WebElement continueBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(text(),'Continue')]")));

            continueBtn.click();

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
             
            

         // ================= OPEN DROPDOWN =================
         WebElement allDropdown = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//button[.//span[normalize-space()='All']]")));

         js.executeScript("arguments[0].click();", allDropdown);
         Thread.sleep(1000);

         WebElement medium = wait.until(
        	        ExpectedConditions.elementToBeClickable(
        	                By.xpath("//*[normalize-space()='Medium']")));

        	medium.click();
        	Thread.sleep(2000);
        	
         // ================= RE-FETCH UPDATE BUTTON =================
         WebElement updateBtn = wait.until(
                 ExpectedConditions.visibilityOfElementLocated(
                         By.xpath("//button[contains(normalize-space(),'Update')]")));

         wait.until(ExpectedConditions.elementToBeClickable(updateBtn));

         js.executeScript("arguments[0].click();", updateBtn);

         System.out.println("Update clicked successfully");

            // ================= EDIT EMAIL =================
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

            System.out.println("Email channel created successfully");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}