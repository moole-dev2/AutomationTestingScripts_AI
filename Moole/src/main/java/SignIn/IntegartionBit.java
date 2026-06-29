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
            scanner.nextLine();
            Thread.sleep(1000); // waits until you press Enter

            // --- Step 4: Navigate directly to Integrations page ---
            driver.get("https://moole.ai/app/settings/project/integrations");

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
            bbEmail.sendKeys("");

            // --- Step 8: Enter API Token ---
            WebElement tokenField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@type='password']")));

            tokenField.sendKeys("ATATT3xFfGF0gxrUF8NF_uA0ZfNCSHlfCHCSvK2hlq2SUZzz8QOgOw85LMHTp9NlAiFcpYy0GheF3NiG1liOCjM8LLduZP8oTXMRX0Ia02HKpuVRGDqOYnjzqo5zxkFWtLmwwSzUvpHNvxFygYhbsk-td36jedTgPf87-orvz6EUD8BQGZNTq4I=991D4EED");  // replace with your token
          //  tokenField.sendKeys("");  // replace with your token
//>>>>>>> branch 'main' of https://github.com/moole-dev2/Moole.AI_AutomationTesting.git

     /*       // --- Step 9: Save Integration ---
      * 
         // Click Save button
         // Click Save button
            WebElement saveButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[normalize-space()='Save']]")));

            JavascriptExecutor js = (JavascriptExecutor) driver;

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", saveButton);
            Thread.sleep(1000);

            // Highlight (Optional)
            js.executeScript("arguments[0].style.border='3px solid red';", saveButton);

            // Click
            js.executeScript("arguments[0].click();", saveButton);

            Thread.sleep(2000);

            System.out.println("Save button clicked successfully.");*/
            
            WebElement updateBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(.,'Update')]")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", updateBtn);
         // ================= WAIT AFTER UPDATE =================
            Thread.sleep(5000);

            // wait for page stability
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'loading') or contains(@class,'spinner') or contains(@class,'backdrop')]")
            ));

            // ================= CLICK REPOSITORIES =================
            WebElement repoMenu = wait.until(driver1 -> {
                try {
                    return driver1.findElement(
                            By.xpath("//a[@href='/project/list-repos' or .//img[contains(@alt,'Repositories')]]")
                    );
                } catch (Exception e) {
                    return null;
                }
            });

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", repoMenu);
            Thread.sleep(1000);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", repoMenu);

            System.out.println("Repositories clicked");
            
            // --- Step 10b: Click "ADD Repositories" ---
            WebElement addRepository = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@type='button' and .//span[text()='Add Repository']]")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addRepository);
            
            WebElement repoSearch = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@placeholder='Search your repositories']")));

            repoSearch.clear();
            repoSearch.sendKeys("node-test");
            
            WebElement repoRow = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//span[contains(text(),'node-test')]/ancestor::div[contains(@class,'cursor-pointer')]")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", repoRow);
            
            WebElement importRepoBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(.,'Import') and contains(.,'repository')]")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", importRepoBtn);
            Thread.sleep(3000);

            
            
      
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Optional: close driver
           driver.quit();
        }
    }
}