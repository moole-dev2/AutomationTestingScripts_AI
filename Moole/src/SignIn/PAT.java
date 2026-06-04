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

public class PAT {

    public static void main(String[] args) {

        // --- ChromeOptions to use existing profile ---
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\psiri\\AppData\\Local\\Google\\User Data");
        options.addArguments("profile-directory=Profile 1");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // --- Step 1: Open Moole.ai and click Sign In ---
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();
            driver.get("https://moole.ai/auth/signin");
            Thread.sleep(2000);

            // --- Step 2: Enter Email ---
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@type='email']")));
            emailField.sendKeys("moole.dev.2@gmail.com");

            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                   By.xpath("//button[contains(text(),'Continue')]")));
            continueBtn.click();

            // --- Step 3: Wait for OTP manually ---
            System.out.println("Please enter your OTP manually in the browser, then press Enter here...");
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            Thread.sleep(2000);

            // --- Step 4: Navigate directly to Integrations page ---
            driver.get("https://moole.ai/app/settings/project/integrations");
            // --- Step 5: Click on PAT menu ---
            WebElement patMenu = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@href,'developer/pat')]//span[text()='PAT']")));
            patMenu.click();

            // --- Step 6: Enter Token Name ---
            WebElement tokenInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@name='tokenName']")));
            tokenInput.sendKeys("ATATT3xFfGF0NQe_BmhVoHFA0UObX3O2OOmTzC_YPcVN24CkAGa8ZznWKZtzmJrJ-HhbdzyTpL0uuEHdcYr0y8lksRIX5rWBhpNIrM0WpO5CoNHEgFQNw6vM1jewL6DsYWELv4M5vgFnWlO5uTtu7vbc5Bios4pLK2-B3wJr9KQOL1n84onh46I=9FAE8D37");
            Thread.sleep(2000);

            // --- Step 7: Click duration dropdown ---
            WebElement durationDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@type='button' and .//span[contains(text(),'30 days')]]")));
            durationDropdown.click();
            Thread.sleep(2000);

            // --- Step 8: Select 30 days option ---
            WebElement select30Days = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(text(),'30 days')]")));
            select30Days.click();
            Thread.sleep(2000);

            // --- Step 9: Click Generate button ---
            WebElement generateBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@type='submit' and contains(.,'Generate')]")));
            generateBtn.click();
            Thread.sleep(2000);

            System.out.println("PAT Token generation flow completed.");
         // --- Step 10: Click Regenerate Token icon ---
            WebElement regenerateIcon = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@aria-label='Regenerate Token']")));
            regenerateIcon.click();
            Thread.sleep(2000);

            // --- Step 11: Click Regenerate confirmation button ---
            WebElement regenerateConfirm = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@type='submit']//span[text()='Regenerate']")));
            regenerateConfirm.click();

            System.out.println("Token regeneration started...");

            // --- Step 12: Wait for 20 seconds (as requested) ---
            Thread.sleep(2000);
            
            

            By deleteIcon = By.xpath("//button[@aria-label='Delete Token']");
            By removeBtn = By.xpath("//button[@type='submit']//span[text()='Remove']");

		// ==========================
		// SAFE CLICK METHOD
		// ==========================
		for (int i = 0; i < 3; i++) {
		    try {
		        WebElement del = wait.until(ExpectedConditions.elementToBeClickable(deleteIcon));
		        del.click();
		        System.out.println("Clicked Delete Token");
		        break;
		    } catch (org.openqa.selenium.StaleElementReferenceException e) {
		        System.out.println("Retrying Delete click due to stale element...");
		    }
		}
		
		Thread.sleep(1500); // allow modal to appear
		
		// ==========================
		// CLICK REMOVE SAFELY
		// ==========================
		for (int i = 0; i < 3; i++) {
		    try {
		        WebElement rm = wait.until(ExpectedConditions.elementToBeClickable(removeBtn));
		        rm.click();
		        System.out.println("Clicked Remove");
		        break;
		    } catch (org.openqa.selenium.StaleElementReferenceException e) {
		        System.out.println("Retrying Remove click due to stale element...");
		    }
		}

		System.out.println("Token deleted successfully.");
		Thread.sleep(2000);
		
		// =====================================================
		// FINAL FLOW: DELETE AGAIN + CANCEL
		// =====================================================

		try {

		    // Click Delete Token again
		    for (int i = 0; i < 3; i++) {
		        try {
		            WebElement deleteAgain = wait.until(
		                    ExpectedConditions.elementToBeClickable(
		                            By.xpath("//button[@aria-label='Delete Token']")
		                    )
		            );

		            deleteAgain.click();
		            System.out.println("Clicked Delete Token (Again)");
		            break;

		        } catch (org.openqa.selenium.StaleElementReferenceException e) {
		            System.out.println("Retrying Delete click due to stale element...");
		        }
		    }

		    Thread.sleep(1500); // wait for modal to open

		    // Click Cancel button (your provided path)
		    WebElement cancelBtn = wait.until(
		            ExpectedConditions.elementToBeClickable(
		                    By.xpath("//button[normalize-space()='Cancel' or contains(.,'Cancel')]")
		            )
		    );

		    cancelBtn.click();

		    System.out.println("Clicked Cancel Button Successfully");

		    Thread.sleep(2000);

		} catch (Exception e) {
		    System.out.println("Cancel Flow Failed: " + e.getMessage());
		}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Optional: keep browser open for verification
             driver.quit();
        }
    }
}
            
            
            