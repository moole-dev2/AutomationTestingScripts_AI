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

public class OrganizationPage {

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
	            Thread.sleep(5000);
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
            driver.get("https://moole.ai/settings/project/integrations");
         // ================= FIXED TEAM CREATION =================

         // Wait for dashboard after OTP
         Thread.sleep(5000);
         
      // Step: Click Profile button (SP) on Integrations page
         WebElement profileBtn = wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("//button[contains(@aria-label,'Profile menu for Sirisha Peddaboini')]")
         ));

         try {
             profileBtn.click();
          } catch (Exception e) {
             // Use JS click as fallback in case React blocks normal click
             ((JavascriptExecutor) driver).executeScript("arguments[0].click();", profileBtn);
         }

         System.out.println("Clicked Profile button (SP)");
         // Step 2: Click "Organizations"
         WebElement orgBtn = wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("//button[@type='submit' and .//text()[contains(.,'Organizations')]]")
         ));

         try {
             orgBtn.click();
         } catch (Exception e) {
             ((JavascriptExecutor) driver).executeScript("arguments[0].click();", orgBtn);
         }

         System.out.println("Clicked Organizations");
         
      // Step 1: Click the organization "Andromeda-cyan1169"
         WebElement orgButton = wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("//button[@type='submit' and contains(.,'Andromeda-cyan1169')]")
         ));
         Thread.sleep(1000);

         try {
             orgButton.click();
         } catch (Exception e) {
             ((JavascriptExecutor) driver).executeScript("arguments[0].click();", orgButton);
         }
         Thread.sleep(1000);

         System.out.println("Clicked Organization: Andromeda-cyan1169");

         // Step 2: Click "Create Project" button
         WebElement createProjectBtn = wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("//button[contains(@class,'flex items-center cursor-pointer') and .//text()[contains(.,'Create Project')]]")
         ));

         try {
             createProjectBtn.click();
         } catch (Exception e) {
             ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createProjectBtn);
         }
         Thread.sleep(1000);

         System.out.println("Clicked Create Project");

         // Step 3: Enter Project Name
         WebElement projectNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                 By.xpath("//input[@placeholder='Project name' and @name='resourceName']")
         ));
         projectNameInput.clear();
         projectNameInput.sendKeys("Moole");
         Thread.sleep(1000);

         System.out.println("Entered Project Name: Moole");

         // Step 4: Click "Browse"
         WebElement browseBtn = wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("//span[text()='Browse']")
         ));

         try {
             browseBtn.click();
         } catch (Exception e) {
             ((JavascriptExecutor) driver).executeScript("arguments[0].click();", browseBtn);
         }
         Thread.sleep(1000);

         System.out.println("Clicked Browse");

         // Step 5: Select existing project "Andromeda-cyan1169" in browse list
         WebElement selectProject = wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("//span[@class='truncate select-none' and text()='Andromeda-cyan1169']")
         ));

         try {
             selectProject.click();
         } catch (Exception e) {
             ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectProject);
         }
         Thread.sleep(1000);

         System.out.println("Selected project in Browse");

         // Step 6: Click "Select" button to confirm project selection
         WebElement selectBtn = wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("//button[contains(@class,'px-3 py-2.5 text-sm rounded') and text()='Select']")
         ));

         try {
             selectBtn.click();
         } catch (Exception e) {
             ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectBtn);
         }
         Thread.sleep(1000);

         System.out.println("Clicked Select button, project assigned successfully!");
      // Step 7: Click the "Create" button to finalize project creation
        WebElement createBtn = wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("//button[@type='submit' and contains(@class,'bg-indigo') and text()='Create']")
         ));

         try {
             createBtn.click();
         } catch (Exception e) {
             // JS fallback click for React UI
             ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createBtn);
         }

         System.out.println("Clicked Create button, project created successfully!");
   
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}

         