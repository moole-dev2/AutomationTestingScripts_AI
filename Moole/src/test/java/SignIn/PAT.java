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



public class PAT {

    @Test
    public void PATTest() throws InterruptedException {

        // --- ChromeOptions to use existing profile ---
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\psiri\\AppData\\Local\\Google\\User Data");
        options.addArguments("profile-directory=Profile 1");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;


        try {
            // --- Step 1: Open Moole.ai and click Sign In ---
        	driver.get(ConfigReader.getProperty("baseUrl"));
            driver.manage().window().maximize();
            driver.get("https://moole.ai/auth/signin");
            Thread.sleep(2000);

            // --- Step 2: Enter Email ---
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@type='email']")));
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
            Thread.sleep(2000);

            // --- Step 4: Navigate directly to Integrations page ---
            driver.get("https://moole.ai/app/settings/project/integrations");
            
            // --- Step 5: Click on PAT menu ---
            
            By patMenu = By.xpath("//a[@href='/app/settings/developer/pat' and .//p[normalize-space()='PAT']]");

            WebElement pat = wait.until(
                    ExpectedConditions.elementToBeClickable(patMenu)
            );

            // scroll into view
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", pat);

            // click safely
            js.executeScript("arguments[0].click();", pat);

            System.out.println("PAT page clicked successfully");
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
            
            // Done
            By doneBtn = By.xpath("//button[normalize-space()='Done']");

            WebElement done = wait.until(
                    ExpectedConditions.elementToBeClickable(doneBtn)
            );

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", done);

            System.out.println("Clicked Done button");
            
            // Action Menu
            
            By actionsMenuBtn = By.xpath("//button[@aria-label='Open actions menu']");

            WebElement actionsMenu = wait.until(
                    ExpectedConditions.elementToBeClickable(actionsMenuBtn)
            );

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actionsMenu);

            System.out.println("Actions menu clicked");
            
         // --- Step 10: Click Regenerate Token icon ---
            By regenerateTokenBtn = By.xpath("//button[@role='menuitem' and .//span[normalize-space()='Regenerate Token']]");

            WebElement regenBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(regenerateTokenBtn)
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    regenBtn
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    regenBtn
            );

            System.out.println("Regenerate Token clicked");

            // --- Step 11: Click Regenerate confirmation button ---
            WebElement regenerateConfirm = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@type='submit']//span[text()='Regenerate']")));
            regenerateConfirm.click();

            System.out.println("Token regeneration started...");

            // --- Step 12: Wait for 20 seconds (as requested) ---
            Thread.sleep(2000);
            By actionsMenuBtn1 = By.xpath("//button[@aria-label='Open actions menu']");

         // ================= ACTION MENU (REFRESH SAFE) =================
            By actionsMenuBtn11 = By.xpath("//button[@aria-label='Open actions menu']");

            for (int i = 0; i < 3; i++) {
                try {
                    WebElement actionsMenu1 = wait.until(
                            ExpectedConditions.elementToBeClickable(actionsMenuBtn11));

                    js.executeScript("arguments[0].scrollIntoView({block:'center'});", actionsMenu1);
                    js.executeScript("arguments[0].click();", actionsMenu1);

                    System.out.println("Actions menu clicked");
                    break;

                } catch (org.openqa.selenium.StaleElementReferenceException e) {
                    System.out.println("Retrying Actions menu click...");
                }
            }

            Thread.sleep(1500);

         // ================= DELETE TOKEN =================
         // ================= DELETE TOKEN =================
            By deleteToken = By.xpath("//button[@role='menuitem' and .//span[normalize-space()='Delete Token']]");

            WebElement deleteBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(deleteToken)
            );

            // scroll into view (important for menu items)
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", deleteBtn);

            // click using JS to avoid overlay/stale issues
            js.executeScript("arguments[0].click();", deleteBtn);
            Thread.sleep(1000);
            System.out.println("Clicked Delete Token successfully");

            // ================= REMOVE BUTTON =================
         // ================= CLICK REMOVE BUTTON =================
            By removeBtn = By.xpath("//button[@type='submit' and .//span[normalize-space()='Remove']]");

            WebElement remove = wait.until(
                    ExpectedConditions.elementToBeClickable(removeBtn)
            );

            // optional: scroll into view (helps in modals)
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", remove);

            // click safely using JS (avoids overlay/stale issues)
            js.executeScript("arguments[0].click();", remove);
            Thread.sleep(1000);

            System.out.println("Clicked Remove button successfully");
            
            //Action
            
            By actionsMenuBtn111 = By.xpath("//button[@aria-label='Open actions menu']");

            for (int i = 0; i < 3; i++) {
                try {
                    WebElement actionsMenu1 = wait.until(
                            ExpectedConditions.elementToBeClickable(actionsMenuBtn111));

                    js.executeScript("arguments[0].scrollIntoView({block:'center'});", actionsMenu1);
                    js.executeScript("arguments[0].click();", actionsMenu1);

                    System.out.println("Actions menu clicked");
                    break;

                } catch (org.openqa.selenium.StaleElementReferenceException e) {
                    System.out.println("Retrying Actions menu click...");
                }
            }

            Thread.sleep(1500);

         // ================= DELETE TOKEN =================
         // ================= DELETE TOKEN =================
            By deleteToken1 = By.xpath("//button[@role='menuitem' and .//span[normalize-space()='Delete Token']]");

            WebElement deleteBtn1 = wait.until(
                    ExpectedConditions.elementToBeClickable(deleteToken1)
            );

            // scroll into view (important for menu items)
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", deleteBtn1);

            // click using JS to avoid overlay/stale issues
            js.executeScript("arguments[0].click();", deleteBtn1);
            Thread.sleep(1000);

            System.out.println("Clicked Delete Token successfully");
            
         // ================= CLICK CANCEL BUTTON =================
            By cancelBtn = By.xpath("//button[normalize-space()='Cancel']");

            WebElement cancel = wait.until(
                    ExpectedConditions.elementToBeClickable(cancelBtn)
            );

            // scroll into view (important for modal dialogs)
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", cancel);

            // click safely using JS (handles overlays + styling issues)
            js.executeScript("arguments[0].click();", cancel);
            Thread.sleep(1000);
            System.out.println("Clicked Cancel button successfully");
            

		} catch (Exception e) {
		    System.out.println("Cancel Flow Failed: " + e.getMessage());
		}
         finally {
            // Optional: keep browser open for verification
             driver.quit();
        }
    }
}
            
            
            