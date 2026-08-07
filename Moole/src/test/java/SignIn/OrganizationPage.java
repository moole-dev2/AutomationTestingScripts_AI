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
        	driver.get(ConfigReader.getProperty("baseUrl"));
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
            Thread.sleep(2000);// waits until you press Enter

            // --- Step 4: Navigate directly to Integrations page ---
            driver.get("https://moole.ai/app/settings/project/integrations");
         // ================= FIXED TEAM CREATION =================

         // Wait for dashboard after OTP
         Thread.sleep(5000);
         
         WebElement profileBtn = wait.until(
        		    ExpectedConditions.elementToBeClickable(
        		        By.xpath("//button[contains(@aria-label,'Profile menu')]")
        		    )
        		);

        		((JavascriptExecutor)driver).executeScript(
        		        "arguments[0].click();", profileBtn);
        		
        		WebElement orgBtn = wait.until(
        		        ExpectedConditions.elementToBeClickable(
        		                By.xpath("//button[contains(.,'Organizations')]")
        		        )
        		);

        		orgBtn.click();
        		System.out.println("Organizations clicked");
        		// Wait for organization button (robust)
        		WebElement orgButton = wait.until(driver1 -> {
        		    try {
        		        WebElement el = driver1.findElement(
        		                By.xpath("//button[contains(normalize-space(),'Milky Way-Barnards Star')]")
        		        );
        		        return el.isDisplayed() ? el : null;
        		    } catch (Exception e) {
        		        return null;
        		    }
        		});

        		// Scroll + click safely
        		((JavascriptExecutor) driver)
        		        .executeScript("arguments[0].scrollIntoView({block:'center'});", orgButton);

        		Thread.sleep(1000);

        		try {
        		    orgButton.click();
        		} catch (Exception e) {
        		    ((JavascriptExecutor) driver)
        		            .executeScript("arguments[0].click();", orgButton);
        		}

        		System.out.println("Organization clicked successfully");

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
         
       
         JavascriptExecutor js = (JavascriptExecutor) driver;

         WebElement arrowBtn = wait.until(ExpectedConditions.elementToBeClickable(
        	        By.xpath("//button[contains(@class,'w-8 h-8') and contains(@class,'rounded-full') and contains(@class,'shrink-0')]")));

        	js.executeScript("arguments[0].scrollIntoView({block:'center'});", arrowBtn);
        	Thread.sleep(1000);
        	js.executeScript("arguments[0].click();", arrowBtn);
        	System.out.println("Arrow button clicked!");
        	Thread.sleep(1000);

      // 2. Wait for "Testing" to appear
      WebElement testing = wait.until(ExpectedConditions.visibilityOfElementLocated(
              By.xpath("//span[normalize-space()='Testing']")
      ));

      // 3. Click Testing
      js.executeScript("arguments[0].click();", testing);
      Thread.sleep(1000);

      // 4. Click Select button
      WebElement selectBtn = wait.until(ExpectedConditions.elementToBeClickable(
              By.xpath("//button[normalize-space()='Select']")
      ));

      js.executeScript("arguments[0].click();", selectBtn);
      Thread.sleep(1000);
      System.out.println("SUCCESS: Testing selected and clicked Select");

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
         
      // Click first arrow
         WebElement arrow1 = wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("(//button[contains(@class,'shrink-0') and contains(@class,'rounded-full') and not(contains(@class,'w-8'))])[1]")));
         js.executeScript("arguments[0].scrollIntoView({block:'center'});", arrow1);
         Thread.sleep(1000);
         js.executeScript("arguments[0].click();", arrow1);
         System.out.println("First arrow clicked!");
         Thread.sleep(1500);

         // Click second arrow
         WebElement arrow2 = wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("(//button[contains(@class,'shrink-0') and contains(@class,'rounded-full') and not(contains(@class,'w-8'))])[2]")));
         js.executeScript("arguments[0].scrollIntoView({block:'center'});", arrow2);
         Thread.sleep(1000);
         js.executeScript("arguments[0].click();", arrow2);
         System.out.println("Second arrow clicked!");
         
      // ======================================
      // SEARCH INPUT
      // ======================================

      // Wait for page to load completely
      Thread.sleep(5000);

      // Scroll down slightly
      ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
      Thread.sleep(2000);

      // Locate Search Input
      WebElement searchInput = wait.until(
              ExpectedConditions.visibilityOfElementLocated(
                      By.xpath("//input[@placeholder='Search projects and folders']")
              )
      );
      ((JavascriptExecutor) driver).executeScript(
              "arguments[0].scrollIntoView({block:'center'});",
              searchInput
      );
      Thread.sleep(2000);

      // Type "Test"
      searchInput.clear();
      searchInput.sendKeys("Test");
      System.out.println("Searched: Test");
      Thread.sleep(3000);

      // Clear search
      searchInput.clear();
      System.out.println("Cleared Search");
      Thread.sleep(2000);

      // Type "jjjj"
      searchInput.sendKeys("jjjj");
      System.out.println("Searched: jjjj");
      Thread.sleep(3000);

      // Clear search
      searchInput.clear();
      System.out.println("Cleared Search");
      Thread.sleep(2000);

      // Close — click outside the search box
      ((JavascriptExecutor) driver).executeScript(
              "document.activeElement.blur();"
      );
      System.out.println("Closed Search");
      Thread.sleep(2000);
         Thread.sleep(1500);
         
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
