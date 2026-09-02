package SignIn;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utils.ConfigReader;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import org.testng.annotations.Test;



public class AddingRepositories {

    @Test
    public void DashboardPageTest() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\psiri\\AppData\\Local\\Google\\Chrome\\User Data");
        options.addArguments("profile-directory=Profile 1");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
        	driver.get(ConfigReader.getProperty("baseUrl"));
            driver.manage().window().maximize();
            // ================= LOGIN =================
            driver.get("https://moole.ai/auth/signin");
            driver.manage().window().maximize();

            WebElement emailField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']"))
            );
            emailField.sendKeys("moole.dev.2@gmail.com");

            WebElement signIn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@data-tour='signup-submit']")
                    )
            );

            signIn.click();

            System.out.println("Sign in button clicked");

            System.out.println("Enter OTP manually...");
            new Scanner(System.in).nextLine();
            Thread.sleep(2000);
            
         // ================= CLICK REPOSITORIES =================

            By repositories = By.xpath(
                    "//a[@href='/app/project/list-repos' and .//span[text()='Repositories']]"
            );

            WebElement repo = wait.until(
                    ExpectedConditions.presenceOfElementLocated(repositories)
            );

            js.executeScript("arguments[0].click();", repo);

            System.out.println("Repositories clicked successfully");

            Thread.sleep(1500);
            
            // --- Step  Click "ADD Repositories" ---
            WebElement addRepository = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@type='button' and .//span[text()='Add Repository']]")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addRepository);
            
            System.out.println("Repositories Clicked  successfully");
            Thread.sleep(1500);

            
         // Click GitLab
            WebElement gitLab = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[normalize-space()='GitLab']]")
                    )
            );
            js.executeScript("arguments[0].click();", gitLab);
            System.out.println("GitLab clicked");

            Thread.sleep(1000);


            // Select GitLab checkbox
            By gitCheckbox = By.xpath(
                    "//button[.//input[@type='checkbox']]//div[contains(@class,'peer-checked:bg-gradient-to-r') or contains(@class,'bg-card-secondary')]"
            );

            WebElement checkbox = wait.until(
                    ExpectedConditions.presenceOfElementLocated(gitCheckbox)
            );

            js.executeScript("arguments[0].click();", checkbox);

            System.out.println("Git checkbox selected");
            Thread.sleep(1000);



            // Click GitHub
            WebElement gitHub = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[normalize-space()='GitHub']]")
                    )
            );
            js.executeScript("arguments[0].click();", gitHub);
            System.out.println("GitHub clicked");

            Thread.sleep(1000);


         

            System.out.println("Search cleared");

            Thread.sleep(1000);


            // Click Existing in org
            WebElement existingInOrg = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[normalize-space()='Existing in org']]")
                    )
            );
            js.executeScript("arguments[0].click();", existingInOrg);

            System.out.println("Existing in org clicked");

            Thread.sleep(1000);

            By mooleSection = By.xpath(
                    "//div[contains(@class,'bg-accordian-close')][.//h5[normalize-space()='Moole']]"
            );

            WebElement moole = wait.until(
                    ExpectedConditions.presenceOfElementLocated(mooleSection)
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", moole);
            Thread.sleep(500);

            js.executeScript("arguments[0].click();", moole);

            System.out.println("Moole section expanded");
            Thread.sleep(1000);

            // Click Source provider
            WebElement sourceProvider = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[normalize-space()='Source provider']]")
                    )
            );
            js.executeScript("arguments[0].click();", sourceProvider);

            System.out.println("Source provider clicked");

            Thread.sleep(1000);

            
            // Click GitHub
            WebElement gitHubagain = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[normalize-space()='GitHub']]")
                    )
            );
            js.executeScript("arguments[0].click();", gitHubagain);
            System.out.println("GitHub clicked");

            Thread.sleep(1000);
            
            // Search go-test
            WebElement searchRepo = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.id("repo-filter")
                    )
            );
            searchRepo.sendKeys("go-test");

            System.out.println("Searched for go-test");

            Thread.sleep(1000);


            // Select go-test checkbox
            WebElement goTestCheckbox = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//div[contains(@class,'peer-checked:bg-gradient-to-r')]")
                    )
            );
            js.executeScript("arguments[0].click();", goTestCheckbox);

            System.out.println("go-test selected");

            Thread.sleep(500);


            // Clear search box
            searchRepo.clear();

            // Import selected repositories
            WebElement importButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[starts-with(normalize-space(),'Import ')]")
                    )
            );

            js.executeScript("arguments[0].click();", importButton);

            System.out.println("Import button clicked");
         
            Thread.sleep(1500);


            
            
            driver.quit();

            System.out.println("Browser closed");
            
               
                   } catch (Exception e) {
                  e.printStackTrace();
              } finally {
                  driver.quit();
              }
          }
      }