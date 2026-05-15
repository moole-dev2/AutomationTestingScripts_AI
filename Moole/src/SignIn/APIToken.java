package SignIn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Scanner;

public class APIToken {

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
            driver.get("https://moole.ai/settings/project/integrations");
            
         // --- Step 5: Click API Token menu ---
            WebElement apiTokenMenu = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/settings/developer/api-token']//span[text()='API Token']")));
            apiTokenMenu.click();
            Thread.sleep(2000);


            // --- Step 6: Enter Token Name ---
            WebElement tokenName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@name='tokenName']")));
            tokenName.sendKeys("BITBUCKET");
            Thread.sleep(2000);
            
         // --- Step 6.1: Click "Choose Type" dropdown ---
            WebElement chooseType = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[.//span[contains(text(),'Choose Type')]]")));
            chooseType.click();

            // --- Step 7: Click Organization Type ---
            WebElement orgType = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[text()='Organization']")));
            orgType.click();
            Thread.sleep(2000);

            // --- Step 8: Click Organization Dropdown ---
            WebElement orgDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[.//span[contains(text(),'Choose organization')]]")));
            orgDropdown.click();

            // --- Step 9: Select Organization ---
            WebElement selectOrg = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(text(),'Milky Way-Barnards Star1205')]")));
            selectOrg.click();
            Thread.sleep(2000);

            // --- Step 10: Click Generate Button ---
            WebElement generateBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@type='submit' and contains(.,'Generate')]")));
            generateBtn.click();

            System.out.println("API Token Generated");
         // --- Step 12: Wait after generation ---
            System.out.println("Waiting 20 seconds after token generation...");
            Thread.sleep(2000);

            // --- Step 13: Click Regenerate Token ---
            WebElement regenerateBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@aria-label='Regenerate Token']")));
            regenerateBtn.click();
            Thread.sleep(2000);

            System.out.println("Clicked Regenerate Token");

         // --- Step 14: Click Regenerate confirm button ---
         WebElement regenerateConfirm = wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("//button[@type='submit']//span[text()='Regenerate']")));
         regenerateConfirm.click();

         System.out.println("Clicked Regenerate Confirm");

         // --- Step 15: Click Cancel button ---
         WebElement cancelBtn = wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("//button[text()='Cancel']")));
         cancelBtn.click();
         Thread.sleep(2000);

         System.out.println("Clicked Cancel");

            // --- Step 14: Wait another 10 seconds ---
            System.out.println("Waiting 20 seconds after regeneration...");
            Thread.sleep(10000);

            // --- Step 15: Click Delete Token ---
            WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@aria-label='Delete Token']")));
            deleteBtn.click();
            Thread.sleep(2000);
            System.out.println("Clicked Delete Token");

         // --- Step 16: Click Cancel after Delete ---
            WebElement cancelAfterDelete = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Cancel')]")));
            cancelAfterDelete.click();

            System.out.println("Clicked Cancel after Delete");
            
            // --- Step 11: Click Copy Button ---
            WebElement copyBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[.//svg[@aria-label='Copy']]")));
            copyBtn.click();

            System.out.println("Token Copied Successfully");
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // driver.quit(); // optional
        }
    }
}
            
            