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

public class IntegrationGL {

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

            Thread.sleep(5000);

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
            Thread.sleep(1000);
            // --- Step 4: Navigate directly to Integrations page ---
            driver.get("https://moole.ai/app/settings/project/integrations");
            // --- Step 5: Click GitLab Integration ---
            WebElement gitlabBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//img[@alt='GitLab']")));
            gitlabBtn.click();
            Thread.sleep(5000);

            // --- Step 6: Click Next ---
            WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Next')]")));
            nextBtn.click();
            
            WebElement userName = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("username")
                    )
            );

            userName.clear();
            userName.sendKeys("Moole");

            System.out.println("Entered username: Moole");

            WebElement gitlabToken = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("personalAccessToken")
            ));
            gitlabToken.clear();
 gitlabToken.sendKeys("glpat-8kDQ37D5CdcoQb9BYUe0u2M6MQpvOjEKdTpsMmlmdg8.01.1713cfbrl");
 			
			//--- Click Save Button ---
			WebElement saveBtn = wait.until(
			      ExpectedConditions.elementToBeClickable(
			              By.xpath("//button[@type='submit' and contains(.,'Save')]")
			      )
			);
			
			saveBtn.click();
			
			System.out.println("Clicked Save Button");
			
			Thread.sleep(3000);
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

            // --- Step 10b: Click "Import Repositories" ---
            WebElement importRepo = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//p[text()='Import Repositories']")
            ));
            importRepo.click();
            System.out.println("Import Repositories page opened!");

            WebElement gitlabBtn2 = wait.until(
            	    ExpectedConditions.elementToBeClickable(
            	        By.xpath("//button[.//text()[contains(.,'GitLab')] or contains(.,'GitLab')]")
            	    )
            	);
            	gitlabBtn2.click();
                System.out.println("GitLab repository option clicked!");

            // --- Step 10d: Click user "moole-dev-2" inside GitLab repositories ---
            WebElement gitlabUser = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='moole-dev-2']")
            ));
            gitlabUser.click();
            System.out.println("GitLab user 'moole-dev-2' clicked!");

            // --- Step 10e: Click repository "node-test" ---
            WebElement nodeTestRepo = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='node-test']")
            ));
            nodeTestRepo.click();
            System.out.println("Repository 'node-test' clicked!");

            // --- Step 10f: Click "Import & Scan" button ---
            WebElement importScanBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(.,'Import & Scan')]")
            ));
            Thread.sleep(15000);
            importScanBtn.click();
            System.out.println("'Import & Scan' button clicked!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close driver
            driver.quit();
        }
    }
}