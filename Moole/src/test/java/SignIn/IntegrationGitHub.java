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
            Thread.sleep(1000);

            // --- Step 4: Navigate directly to Integrations page ---
            driver.get("https://moole.ai/app/settings/project/integrations");

     /*       // --- Step 5: Click GitHub Integration ---
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
             githubToken.sendKeys("ghp_YOUR_GITHUB_PERSONAL_ACCESS_TOKEN");*/
             
             
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
             
             // --- Step  Click "ADD Repositories" ---
             WebElement addRepository = wait.until(
                     ExpectedConditions.elementToBeClickable(
                             By.xpath("//button[@type='button' and .//span[text()='Add Repository']]")));

             ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addRepository);


       /*     // --- Step 10b: Click "Import Repositories" ---
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
            System.out.println("GitHub user 'moole-dev-2' clicked!");*/
             
             By repoSearch = By.id("repo-filter");

             WebElement searchBox = wait.until(
                     ExpectedConditions.visibilityOfElementLocated(repoSearch));

             searchBox.clear();
             searchBox.sendKeys("node-test");

             System.out.println("Repository name entered: node-test");
             
             By repoCheckbox = By.xpath(
            	        "//div[contains(@class,'cursor-pointer')][.//span[contains(.,'node-test')]]");

            	WebElement repo = wait.until(
            	        ExpectedConditions.elementToBeClickable(repoCheckbox));

            	((JavascriptExecutor) driver).executeScript(
            	        "arguments[0].scrollIntoView({block:'center'});", repo);

            	((JavascriptExecutor) driver).executeScript(
            	        "arguments[0].click();", repo);

            	System.out.println("Repository selected: node-test");
            	Thread.sleep(1000);


            // --- Step 10f: Click "Import & Scan" button ---
            
            	By importRepository = By.xpath(
            	        "//button[contains(normalize-space(.),'Import') and contains(normalize-space(.),'repository')]");

            	WebElement importBtn = wait.until(
            	        ExpectedConditions.elementToBeClickable(importRepository));

            	((JavascriptExecutor) driver).executeScript(
            	        "arguments[0].scrollIntoView({block:'center'});", importBtn);

            	((JavascriptExecutor) driver).executeScript(
            	        "arguments[0].click();", importBtn);

            	System.out.println("Import Repository button clicked successfully.");
            	Thread.sleep(1000);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}