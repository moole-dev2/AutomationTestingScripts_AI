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

public class IntegartionBit {

    public static void main(String[] args) {

        // ChromeOptions to use existing profile (so cookies/OTP sessions persist if needed) ---
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

            // --- Step 5: Click Bitbucket Integration ---
            WebElement bitbucketBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//img[@alt='Bitbucket']")));
            bitbucketBtn.click();
            try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

            // --- Step 6: Click Next ---
            WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Next')]")));
            nextBtn.click();

            // --- Step 7: Enter Bitbucket Email ---
            WebElement bbEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@type='email']")));
            bbEmail.sendKeys(" ");

            // --- Step 8: Enter API Token ---
            WebElement tokenField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@type='password']")));
            tokenField.sendKeys("ATATT3xFfGF0NQe_BmhVoHFA0UObX3O2OOmTzC_YPcVN24CkAGa8ZznWKZtzmJrJ-HhbdzyTpL0uuEHdcYr0y8lksRIX5rWBhpNIrM0WpO5CoNHEgFQNw6vM1jewL6DsYWELv4M5vgFnWlO5uTtu7vbc5Bios4pLK2-B3wJr9KQOL1n84onh46I=9FAE8D37");  // replace with your token


            // --- Step 9: Save Integration ---
            WebElement updateBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Update')]")));
            updateBtn.click();
            try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
         
         // --- Step 10a: Click the Repositories menu ---
            WebElement repoMenu = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@href='/project/list-repos' and .//img[@alt='Repositories icon']]")
            ));
            repoMenu.click();
            System.out.println("Repositories menu clicked!");
            
            // --- Step 10b: Click "Import Repositories" ---
            WebElement importRepo = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//p[text()='Import Repositories']")
                    
            ));
            importRepo.click();
            try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
            System.out.println("Import Repositories page opened!");

            // --- Step 10c: Click Bitbucket inside Import Repositories ---
            WebElement bitbucketRepo = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='Bitbucket']")
            ));
            bitbucketRepo.click();
            try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
            Thread.sleep(500);
            System.out.println("Bitbucket repository option clicked!");

         // --- Wait for and click the user "moole-dev-2" ---
            WebElement userElement = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[.//span[text()='moole-dev-2']]")
            ));
            userElement.click();
            System.out.println("User 'moole-dev-2' clicked!");

            // --- Wait for and click the repository "node-test" ---
            WebElement repoElement = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[.//span[text()='node-test']]")
            ));
            repoElement.click();
            System.out.println("Repository 'node-test' clicked!");

            // --- Wait for "Import & Scan" button and click ---
            WebElement importScanBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(.,'Import & Scan')]")
            ));
            Thread.sleep(5000); // optional pause to visualize the step
            importScanBtn.click();
            System.out.println("'Import & Scan' clicked!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Optional: close driver
           //driver.quit();
        }
    }
}