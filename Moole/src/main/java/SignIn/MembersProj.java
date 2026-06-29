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

import java.time.Duration;
import java.util.Scanner;

public class MembersProj {

    public static void main(String[] args) {

        // --- ChromeOptions to use existing profile (so cookies/OTP sessions persist if needed) ---
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\psiri\\AppData\\Local\\Google\\Chrome\\User Data");
        options.addArguments("profile-directory=Profile 1");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;


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
            scanner.nextLine();
            Thread.sleep(1000);// waits until you press Enter

            // --- Step 4: Navigate directly to Integrations page ---
            driver.get("https://moole.ai/app/settings/project/integrations");
         // ================= FIXED TEAM CREATION =================

         // Wait for dashboard after OTP
         Thread.sleep(5000);
         
      // ================= MEMBERS → INVITE USER FLOW =================

      // Step 1: Click on "Members"
         By membersMenu = By.cssSelector("a[href='/app/settings/project/members']");

         WebElement members = wait.until(
             ExpectedConditions.elementToBeClickable(membersMenu));

         ((JavascriptExecutor) driver).executeScript(
             "arguments[0].click();", members);

         System.out.println("Clicked Members");
      
   // Step 2: Click on Invite Member (Share button)
         By inviteMemberBtn = By.xpath("//button[.//span[normalize-space()='Invite Member']]");

         WebElement inviteMember = wait.until(
                 ExpectedConditions.elementToBeClickable(inviteMemberBtn));

         ((JavascriptExecutor) driver).executeScript(
                 "arguments[0].scrollIntoView({block:'center'});", inviteMember);

         ((JavascriptExecutor) driver).executeScript(
                 "arguments[0].click();", inviteMember);

         System.out.println("Clicked Invite Member");
   // Step 3: Enter Email
      WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
              By.xpath("//input[@type='email' and @placeholder='Enter Your Email']")
      ));
      emailInput.clear();
      emailInput.sendKeys("lemici3525@exespay.com");
      Thread.sleep(1000);
      System.out.println("Entered email");

      // Step 4: Click dropdown arrow (SVG inside span)
     
      By selectRoleDropdown = By.xpath(
    	        "//button[@aria-haspopup='listbox' and .//span[normalize-space()='Select Role']]"
    	);

    	WebElement dropdown = wait.until(
    	        ExpectedConditions.elementToBeClickable(selectRoleDropdown)
    	);

    	((JavascriptExecutor) driver).executeScript(
    	        "arguments[0].scrollIntoView({block:'center'});", dropdown);

    	((JavascriptExecutor) driver).executeScript(
    	        "arguments[0].click();", dropdown);

    	System.out.println("Select Role dropdown clicked");
      
      //---tester Option
    	
    	By testerOption = By.xpath("//div[@role='option' and .//span[normalize-space()='Tester']]");

    	WebElement option = wait.until(
    	        ExpectedConditions.elementToBeClickable(testerOption));

    	((JavascriptExecutor) driver).executeScript(
    	        "arguments[0].scrollIntoView({block:'center'});", option);

    	((JavascriptExecutor) driver).executeScript(
    	        "arguments[0].click();", option);

    	System.out.println("Tester role selected");

      // Step 6: Click "Invite User" button
      WebElement inviteUserBtn = wait.until(ExpectedConditions.elementToBeClickable(
              By.xpath("//button[@type='submit' and .//span[normalize-space()='Invite User']]")
      ));

      try {
          inviteUserBtn.click();
      } catch (Exception e) {
          ((JavascriptExecutor) driver).executeScript("arguments[0].click();", inviteUserBtn);
      }
      Thread.sleep(2000);
      System.out.println("USER INVITED SUCCESSFULLY!");
    
      
   // Open Status dropdown only once
      WebElement statusDropdown = wait.until(
              ExpectedConditions.presenceOfElementLocated(
                      By.xpath("//button[@aria-label='Sort by Status']")));

      js.executeScript("arguments[0].click();", statusDropdown);

      Thread.sleep(2000);

      // Locate Accepted
      WebElement accepted = wait.until(
              ExpectedConditions.presenceOfElementLocated(
                      By.xpath("//*[contains(text(),'Accepted')]")));

      // Locate Pending
      WebElement pending = wait.until(
              ExpectedConditions.presenceOfElementLocated(
                      By.xpath("//*[contains(text(),'Pending')]")));

      // Select Accepted
      js.executeScript("arguments[0].click();", accepted);
      System.out.println("Accepted Selected");
      Thread.sleep(2000);

      // Unselect Accepted
      js.executeScript("arguments[0].click();", accepted);
      System.out.println("Accepted Unselected");
      Thread.sleep(2000);

      // Select Pending
      js.executeScript("arguments[0].click();", pending);
      System.out.println("Pending Selected");
      Thread.sleep(2000);

      // Unselect Pending
      js.executeScript("arguments[0].click();", pending);
      System.out.println("Pending Unselected");
      Thread.sleep(2000);

      // Select both
      js.executeScript("arguments[0].click();", accepted);
      Thread.sleep(1000);

      js.executeScript("arguments[0].click();", pending);
      System.out.println("Accepted and Pending Selected");
      Thread.sleep(2000);

      // Unselect both
      js.executeScript("arguments[0].click();", accepted);
      Thread.sleep(1000);

      js.executeScript("arguments[0].click();", pending);
      System.out.println("Accepted and Pending Unselected");
      Thread.sleep(2000);

      // Close dropdown at the end
      js.executeScript("arguments[0].click();", statusDropdown);
      
      
   // Open dropdown
      WebElement roleDropdown1 = wait.until(
              ExpectedConditions.elementToBeClickable(
                      By.xpath("//button[@aria-label='Sort by Select Role']")));
      roleDropdown1.click();
      Thread.sleep(1500);

      // Target the actual checkbox input inside the Developer row
      By developerCheckbox = By.xpath(
              "//div[.//p[normalize-space()='Developer']]//input[@type='checkbox']");

      // ================= CHECK =================
      WebElement checkboxToCheck = wait.until(
              ExpectedConditions.presenceOfElementLocated(developerCheckbox));
      // Use JavaScript click to bypass overlay/visibility issues
      js.executeScript("arguments[0].click();", checkboxToCheck);
      System.out.println("Developer CHECKED | isSelected: " + checkboxToCheck.isSelected());
      Thread.sleep(2000);

      // ================= UNCHECK =================
      WebElement checkboxToUncheck = driver.findElement(developerCheckbox); // re-fetch
      js.executeScript("arguments[0].click();", checkboxToUncheck);
      System.out.println("Developer UNCHECKED | isSelected: " + checkboxToUncheck.isSelected());
      Thread.sleep(2000);
      // Close dropdown
      roleDropdown1.click();
      System.out.println("Dropdown closed");
      
      
   // ================= SEARCH BAR OPERATIONS =================

      By searchInput = By.xpath("//input[@placeholder='Search by name or email']");

   // --- Search: Sirisha ---
   WebElement search1 = wait.until(
           ExpectedConditions.visibilityOfElementLocated(searchInput));
   search1.clear();
   search1.sendKeys("Sirisha");
   System.out.println("Searched: Sirisha");

   Thread.sleep(1500);
   search1.clear();
   System.out.println("Cleared: Sirisha");

   Thread.sleep(800);

   // --- Search: Shree ---
   WebElement search2 = wait.until(
           ExpectedConditions.visibilityOfElementLocated(searchInput));
   search2.clear();
   search2.sendKeys("Shree");
   System.out.println("Searched: Shree");

   Thread.sleep(1500);
   search2.clear();
   System.out.println("Cleared: Shree");

   Thread.sleep(800);

   // --- Search: email 1 ---
   WebElement search3 = wait.until(
           ExpectedConditions.visibilityOfElementLocated(searchInput));
   search3.clear();
   search3.sendKeys("moole.dev.2@gmail.com");
   System.out.println("Searched: email 1");

   Thread.sleep(1500);
   search3.clear();
   System.out.println("Cleared: email 1");

   Thread.sleep(800);

   // --- Search: email 2 ---
   WebElement search4 = wait.until(
           ExpectedConditions.visibilityOfElementLocated(searchInput));
   search4.clear();
   search4.sendKeys("moole.dev.3@gmail.com");
   System.out.println("Searched: email 2");

   Thread.sleep(1500);
   search4.clear();
   System.out.println("Cleared: email 2");

   Thread.sleep(800);

   // --- Close search ---
   WebElement searchFinal = wait.until(
           ExpectedConditions.elementToBeClickable(searchInput));
   searchFinal.sendKeys(Keys.ESCAPE);

   System.out.println("Search closed");
      
      
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           driver.quit();
        }
    }
}
