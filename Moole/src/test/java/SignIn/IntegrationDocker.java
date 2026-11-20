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
import java.util.List;
import java.util.Scanner;
import org.testng.annotations.Test;



public class IntegrationDocker {

    @Test
    public void IntegrationGitHubTest() throws InterruptedException {

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
            Thread.sleep(1000);

            // --- Step 4: Navigate directly to Integrations page ---
            driver.get("https://moole.ai/app/settings/project/integrations");
            
            WebElement connectButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@name='provider' and @value='docker']")
                )
            );

            connectButton.click();
            

            WebElement configName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("configName"))
            );

            configName.clear();
            configName.sendKeys("Testing");
            
            WebElement nextButton = wait.until(
            	    ExpectedConditions.elementToBeClickable(
            	        By.xpath("//button[@type='submit' and normalize-space()='Next']")
            	    )
            	);

            	nextButton.click();
				Thread.sleep(500);

            	
					//  Enter Docker Hub username
					WebElement username = wait.until(
					    ExpectedConditions.visibilityOfElementLocated(By.id("username"))
					);
					username.click();
					Thread.sleep(500);

					username.sendKeys("mooletaran");
					
					WebElement accessToken = wait.until(
						    ExpectedConditions.visibilityOfElementLocated(
						        By.id("accessToken")
						    )
						);

						accessToken.click();
						//accessToken.sendKeys("dckr_pat_xz47ouCnp0mk79ZCtfrKRo0yMy8");
						Thread.sleep(500);


						System.out.println("Docker access token entered successfully");
				
					// Wait for Save button to become clickable
					WebElement saveButton = wait.until(
					    ExpectedConditions.elementToBeClickable(
					        By.xpath("//button[@type='submit' and normalize-space()='Save']")
					    )
					);
					
					//  Click Save
					saveButton.click();
					Thread.sleep(500);
					

        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            driver.quit();
        }
    }
}