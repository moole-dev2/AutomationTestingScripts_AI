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

public class IntegrationGitHub {

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

            // --- Step 4: Navigate directly to Integrations page ---
            driver.get("https://moole.ai/settings/project/integrations");

            // --- Step 5: Click GitHub Integration ---
            WebElement githubBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//img[@alt='GitHub']")));
            githubBtn.click();
            Thread.sleep(5000);

            // --- Step 6: Click Next ---
            WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Next')]")));
            nextBtn.click();

            // --- Step 7: Enter GitHub Name ---
            WebElement githubName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@class='col-start-1 row-start-1 w-full pl-10 pr-10 py-4 text-base font-medium rounded-sm transition-all bg-bg duration-200 focus:outline-none focus:ring-2']")));
            githubName.sendKeys("moole");

            // --- Step 8: Enter GitHub Token ---
            WebElement githubToken = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@class='col-start-1 row-start-1 w-full pl-10 pr-10 py-4 text-base font-medium rounded-sm transition-all bg-bg duration-200 focus:outline-none focus:ring-2']")));
           // githubToken.sendKeys("ghp_YOUR_GITHUB_PERSONAL_ACCESS_TOKEN");
            // githubToken.sendKeys("ghp_YOUR_GITHUB_PERSONAL_ACCESS_TOKEN");

            // --- Step 9: Save GitHub Integration ---
            WebElement updateBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Update')]")));
            updateBtn.click();
            Thread.sleep(5000);

            // --- Step 10a: Click Repositories menu ---
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
            Thread.sleep(5000);
            System.out.println("Import Repositories page opened!");

            // --- Step 10c: Click GitHub inside Import Repositories ---
            WebElement githubRepo = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='GitHub']")
            ));
            githubRepo.click();
            Thread.sleep(5000);
            System.out.println("GitHub repository option clicked!");

            // --- Step 10d: Click user "moole-dev-2" inside GitHub repositories ---
            WebElement githubUser = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='moole-dev-2']")
            ));
            githubUser.click();
            System.out.println("GitHub user 'moole-dev-2' clicked!");

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
            Thread.sleep(5000);
            importScanBtn.click();
            System.out.println("'Import & Scan' button clicked!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}