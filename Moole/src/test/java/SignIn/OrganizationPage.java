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



public class OrganizationPage {

    @Test
    public void OrganizationPageTest() throws InterruptedException {

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
        		WebElement button = wait.until(
        		        ExpectedConditions.elementToBeClickable(
        		                By.xpath("//button[@type='submit' and contains(normalize-space(), 'Messier 83-Ross 24816')]")
        		        )
        		);

        		button.click();

        		System.out.println("Organization clicked successfully");

         // Step 2: Click "Create Project" button
        		WebElement createProjectBtn = wait.until(ExpectedConditions.elementToBeClickable(

        		        By.xpath("//button[@type='button' and .//span[normalize-space()='Create Project']]")

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

        		        By.xpath("//input[@name='resourceName' and @placeholder='Enter Project name']")

        		));

        		projectNameInput.clear();

        		projectNameInput.sendKeys("Moole");

        		Thread.sleep(1000);

        		System.out.println("Entered Project Name: Moole");
         Thread.sleep(1000);

         System.out.println("Entered Project Name: Moole");

         // Step 4: Click "Browse"
         WebElement browseBtn = wait.until(ExpectedConditions.elementToBeClickable(

        	        By.xpath("//div[.//span[normalize-space()='Choose Location *']]//span[normalize-space()='Browse']")

        	));

        	try {

        	    browseBtn.click();

        	} catch (Exception e) {

        	    ((JavascriptExecutor) driver).executeScript(
        	            "arguments[0].click();",
        	            browseBtn
        	    );

        	}

        	Thread.sleep(1000);


         System.out.println("Clicked Browse");
         
         WebElement locationOption = wait.until(ExpectedConditions.elementToBeClickable(

        	        By.xpath("//span[normalize-space()='Messier 83-Ross 248-domain16']")

        	));

        	try {

        	    locationOption.click();

        	} catch (Exception e) {

        	    ((JavascriptExecutor) driver).executeScript(
        	            "arguments[0].click();",
        	            locationOption
        	    );

        	}

        	Thread.sleep(1000);

        	System.out.println("Selected location: Messier 83-Ross 248-domain16");
        	
        	WebElement selectBtn = wait.until(ExpectedConditions.elementToBeClickable(

        	        By.xpath("//button[@type='button' and normalize-space()='Select']")

        	));

        	try {

        	    selectBtn.click();

        	} catch (Exception e) {

        	    ((JavascriptExecutor) driver).executeScript(
        	            "arguments[0].click();",
        	            selectBtn
        	    );

        	}

        	Thread.sleep(1000);

        	System.out.println("Clicked Select successfully");
        	WebElement createBtn = wait.until(ExpectedConditions.elementToBeClickable(

        	        By.xpath("//button[@type='submit' and normalize-space()='Create']")

        	));

        	try {

        	    createBtn.click();

        	} catch (Exception e) {

        	    ((JavascriptExecutor) driver).executeScript(
        	            "arguments[0].click();",
        	            createBtn
        	    );

        	}

        	Thread.sleep(1000);

        	System.out.println("Clicked Create button successfully");
        	
        	// ================= CLICK PROJECT ARROW =================

        	WebElement arrowBtn = wait.until(
        	        ExpectedConditions.elementToBeClickable(
        	                By.xpath("//span[normalize-space()='Messier 83-Ross 24816']/preceding-sibling::button")
        	        )
        	);

        	arrowBtn.click();

        	Thread.sleep(1500);

        	System.out.println("Project arrow clicked successfully");
         
        	WebElement actionsMenuBtn = wait.until(ExpectedConditions.elementToBeClickable(

        	        By.xpath("//button[@type='button' and @aria-label='Open actions menu']")

        	));

        	try {

        	    actionsMenuBtn.click();

        	} catch (Exception e) {

        	    ((JavascriptExecutor) driver).executeScript(
        	            "arguments[0].click();",
        	            actionsMenuBtn
        	    );

        	}

        	Thread.sleep(1000);

        	System.out.println("Clicked Open Actions Menu successfully");
        	WebElement deleteProjectBtn = wait.until(ExpectedConditions.elementToBeClickable(
        	        By.xpath("//button[@role='menuitem' and contains(.,'Delete Project')]")
        	));

        	try {
        	    deleteProjectBtn.click();
        	} catch (Exception e) {
        	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteProjectBtn);
        	}

        	Thread.sleep(1000);

        	System.out.println("Clicked Delete Project");
        	
        	WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
        	        By.xpath("//button[@type='submit' and normalize-space()='Delete']")
        	));

        	try {
        	    deleteBtn.click();
        	} catch (Exception e) {
        	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteBtn);
        	}

        	Thread.sleep(1000);

        	System.out.println("Clicked Delete");
      // ======================================
      // SEARCH INPUT
      // ======================================

        	WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
        	        By.xpath("//input[@placeholder='Search projects and folders']")
        	));

        	searchInput.clear();
        	searchInput.sendKeys("Test");

        	Thread.sleep(2000);

        	System.out.println("Searched: Test");

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
