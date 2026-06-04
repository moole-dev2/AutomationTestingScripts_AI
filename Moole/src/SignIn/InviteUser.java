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

public class InviteUser {

    public static void main(String[] args) {

        // --- ChromeOptions to use existing profile (so cookies/OTP sessions persist if needed) ---
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\psiri\\AppData\\Local\\Google\\Chrome\\User Data");
        options.addArguments("profile-directory=Profile 1");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // --- Step 1: Open Moole.ai and click Sign In ---
            driver.get("https://moole.ai/");
           driver.manage().window().maximize();

           driver.get("https://moole.ai/auth/signin");

            try {
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

            // --- Step 2: Enter Email ---
            WebElement emailField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
            emailField.sendKeys("moole.dev.2@gmail.com");

            WebElement continueBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue')]")));
            continueBtn.click();

            // --- Step 3: Wait for OTP manually ---
            System.out.println("Please enter your OTP manually in the browser, then press Enter here...");
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();  // waits until you press Enter

            // --- Step 4: Navigate directly to Integrations page ---
            driver.get("https://moole.ai/app/settings/project/integrations");
         // ================= FIXED TEAM CREATION =================

         // Wait for dashboard after OTP
         Thread.sleep(5000);
         
      // ================= MEMBERS → INVITE USER FLOW =================

      // Step 1: Click on "Members"
      WebElement membersTab = wait.until(ExpectedConditions.elementToBeClickable(
              By.xpath("//span[normalize-space()='Members']")
      ));
      membersTab.click();

      System.out.println("Clicked Members tab");
      
   // Step 2: Click on Invite Member (Share button)
      WebElement inviteMemberBtn = wait.until(ExpectedConditions.elementToBeClickable(
              By.xpath("//span[@aria-label='Invite Member']")
      ));

      try {
          inviteMemberBtn.click();
      } catch (Exception e) {
          ((JavascriptExecutor) driver).executeScript("arguments[0].click();", inviteMemberBtn);
      }
      Thread.sleep(1000);
      System.out.println("Clicked Invite Member button");
   // Step 3: Enter Email
      WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
              By.xpath("//input[@type='email' and @placeholder='Enter Your Email']")
      ));
      emailInput.clear();
      emailInput.sendKeys("lemici3525@exespay.com");
      Thread.sleep(1000);
      System.out.println("Entered email");

      // Step 4: Click dropdown arrow (SVG inside span)
      WebElement dropdownArrow = wait.until(ExpectedConditions.elementToBeClickable(
              By.xpath("//button[@type='button']//span[contains(@class,'flex items-center')]//*[name()='svg']")
      ));

      try {
          dropdownArrow.click();
      } catch (Exception e) {
          ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownArrow);
      }
      Thread.sleep(2000);
      System.out.println("Clicked dropdown arrow");

      // Step 5: Select "Tester"
      WebElement testerOption = wait.until(ExpectedConditions.elementToBeClickable(
              By.xpath("//div[contains(@class,'cursor-pointer')]//p[text()='Tester']")
      ));

      try {
          testerOption.click();
      } catch (Exception e) {
          ((JavascriptExecutor) driver).executeScript("arguments[0].click();", testerOption);
      }
      Thread.sleep(1000);
      System.out.println("Selected Tester role");

      // Step 6: Click "Invite User" button
      WebElement inviteUserBtn = wait.until(ExpectedConditions.elementToBeClickable(
              By.xpath("//button[@type='submit' and .//span[normalize-space()='Invite User']]")
      ));

      try {
          inviteUserBtn.click();
      } catch (Exception e) {
          ((JavascriptExecutor) driver).executeScript("arguments[0].click();", inviteUserBtn);
      }
      Thread.sleep(1000);
      System.out.println("USER INVITED SUCCESSFULLY!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           driver.quit();
        }
    }
}
