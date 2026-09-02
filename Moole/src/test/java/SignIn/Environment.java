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



public class Environment {

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
            
            
         // ================= CLICK ENVIRONMENTS =================
            WebElement environments = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[@href='/app/project/environments' and .//span[normalize-space()='Environments']]")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", environments);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", environments);

            System.out.println("Environments clicked");
            Thread.sleep(1500);
            
         // ================= CLICK CREATE ENVIRONMENT =================
            WebElement createEnvironment = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[normalize-space()='Create Environment']]")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", createEnvironment);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", createEnvironment);

            System.out.println("Create Environment clicked");
            Thread.sleep(1000);


            // ================= ENTER ENVIRONMENT NAME =================
            WebElement environmentName = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//input[@name='name' and @placeholder='e.g. UAT']")
                    )
            );

            environmentName.clear();
            environmentName.sendKeys("TESTING");

            System.out.println("Environment name entered: TESTING");
            Thread.sleep(500);


            // ================= SELECT COLOR =================
            // Selecting Green #22c55e
            WebElement colorButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Select color #22c55e']")
                    )
            );

            js.executeScript("arguments[0].click();", colorButton);

            System.out.println("Green color selected");
            Thread.sleep(500);


            // ================= CLICK CREATE =================
            WebElement createButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@type='submit' and .//span[normalize-space()='Create']]")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", createButton);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", createButton);

            System.out.println("Create button clicked");
            Thread.sleep(1500);
            
         // ================= CLICK MORE ACTIONS =================

            WebElement moreActions = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='More actions']")
                    )
            );

            js.executeScript("arguments[0].click();", moreActions);

            System.out.println("More actions clicked");
            Thread.sleep(1000);


            // ================= CLICK EDIT ENVIRONMENT =================

            WebElement editEnvironment = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//p[normalize-space()='Edit environment']]")
                    )
            );

            js.executeScript("arguments[0].click();", editEnvironment);

            System.out.println("Edit environment clicked");
            Thread.sleep(1000);


            // ================= ENTER DESCRIPTION =================

            WebElement description = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//input[@name='description']")
                    )
            );

            description.clear();
            description.sendKeys("Test");

            System.out.println("Description entered as Test");
            Thread.sleep(500);


            // ================= SELECT COLOR =================

            // Select the green color
            WebElement colorButton1 = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Select color #22c55e']")
                    )
            );

            js.executeScript("arguments[0].click();", colorButton1);

            System.out.println("Color selected");
            Thread.sleep(500);


            // ================= CLICK SAVE =================

            WebElement saveButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@type='submit' and .//span[normalize-space()='Save']]")
                    )
            );

            js.executeScript("arguments[0].click();", saveButton);

            System.out.println("Save button clicked");
            Thread.sleep(3000);
            
         // ================= CLICK ENVIRONMENT SORT - DOWN =================
            WebElement environmentSort = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-sort='ascending' and normalize-space()='Environment']")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", environmentSort);
            Thread.sleep(500);

            // First click - DOWN
            js.executeScript("arguments[0].click();", environmentSort);

            System.out.println("Environment sorted DOWN");
            Thread.sleep(1000);


            // ================= CLICK ENVIRONMENT SORT - UP =================
            environmentSort = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-sort='descending' and normalize-space()='Environment']")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", environmentSort);
            Thread.sleep(500);

            // Second click - UP
            js.executeScript("arguments[0].click();", environmentSort);

            System.out.println("Environment sorted UP");
            Thread.sleep(1000);
            
         // ================= CLICK DEVELOPMENT ARROW =================
            By developmentArrow = By.xpath(
                    "//span[normalize-space()='Development']"
                    + "/preceding-sibling::span[1]"
            );

            WebElement arrow = wait.until(
                    ExpectedConditions.presenceOfElementLocated(developmentArrow)
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    arrow
            );

            Thread.sleep(500);

            // Click the span containing the arrow
            js.executeScript("arguments[0].click();", arrow);

            System.out.println("Development arrow clicked");
            Thread.sleep(1000);
            
         // ================= CLICK CONTAINER IMAGES =================
            WebElement containerImages = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[normalize-space()='Container Images']]")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", containerImages);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", containerImages);

            System.out.println("Container Images clicked");
            Thread.sleep(1000);


            // ================= SEARCH CONTAINER IMAGE =================
            WebElement imageSearch = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//input[@placeholder='Search Image, Tag & Digest']")
                    )
            );

            imageSearch.clear();
            imageSearch.sendKeys("Test");

            System.out.println("Test entered in Container Images search");
            Thread.sleep(1000);


            // ================= CLEAR IMAGE SEARCH =================
            imageSearch.clear();

            System.out.println("Container Images search cleared");
            Thread.sleep(500);


            // ================= CLICK REPOSITORIES =================
            WebElement repositories = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[normalize-space()='Repositories']]")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", repositories);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", repositories);

            System.out.println("Repositories clicked");
            Thread.sleep(1000);


            // ================= SEARCH REPOSITORY =================
            // Same input ID is reused, so use the placeholder to identify it
            WebElement repoSearch = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//input[@placeholder='Search Repo, Branchs & Commit']")
                    )
            );

            repoSearch.clear();
            repoSearch.sendKeys("node-test");

            System.out.println("node-test entered in Repository search");
            Thread.sleep(1000);


            // ================= CLICK NODE-TEST =================
            WebElement nodeTest = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[normalize-space()='node-test']]")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", nodeTest);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", nodeTest);

            System.out.println("node-test repository clicked");
            Thread.sleep(1000);


            // ================= CLICK BACK TO ENVIRONMENTS =================
            WebElement backToEnvironments = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[normalize-space()='Back to Environments']")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    backToEnvironments
            );

            Thread.sleep(500);

            js.executeScript(
                    "arguments[0].click();",
                    backToEnvironments
            );

            System.out.println("Back to Environments clicked");
            Thread.sleep(1500);

         // ================= CLICK MORE ACTIONS =================
            WebElement moreActions1 = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='More actions']")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", moreActions1);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", moreActions1);

            System.out.println("More actions clicked");
            Thread.sleep(1000);
        // ================= CLICK MORE ACTIONS =================
            By moreActionsLocator = By.xpath("//button[@aria-label='More actions']");

            WebElement moreActions11 = wait.until(
                    ExpectedConditions.elementToBeClickable(moreActionsLocator)
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", moreActions11);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", moreActions11);

            System.out.println("More actions clicked");
            Thread.sleep(1000);


            // ================= WAIT FOR DELETE OPTION =================
            // Use text() without depending on the Unicode ellipsis
            By deleteEnvironmentLocator = By.xpath(
                    "//button[.//p[contains(normalize-space(),'Delete environment')]]"
            );

            WebElement deleteEnvironment = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(deleteEnvironmentLocator)
            );

            System.out.println("Delete environment option displayed");

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", deleteEnvironment);
            Thread.sleep(300);
            js.executeScript("arguments[0].click();", deleteEnvironment);

            System.out.println("Delete environment clicked");
            Thread.sleep(1000);

            // ================= CLICK CANCEL =================
            WebElement cancelButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@type='button' and normalize-space()='Cancel']")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", cancelButton);
            Thread.sleep(300);
            js.executeScript("arguments[0].click();", cancelButton);

            System.out.println("Cancel button clicked");
            Thread.sleep(1000);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}