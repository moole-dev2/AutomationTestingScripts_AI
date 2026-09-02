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



public class ContainerImages {

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
            
         // ================= CLICK CONTAINER IMAGES =================
            WebElement containerImages = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[@href='/app/project/container-security' and .//span[normalize-space()='Container Images']]")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", containerImages);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", containerImages);

            System.out.println("Container Images clicked");
            Thread.sleep(1500);


            // ================= CLICK ADD IMAGE =================
            WebElement addImage = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[normalize-space()='Add Image']]")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", addImage);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", addImage);

            System.out.println("Add Image clicked");
            Thread.sleep(1000);


            // ================= CLICK EXISTING IN ORG =================
            WebElement existingInOrg = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[normalize-space()='Existing in org']]")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", existingInOrg);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", existingInOrg);

            System.out.println("Existing in org clicked");
            Thread.sleep(1000);


            // ================= CLICK MOOLE ARROW - FIRST TIME =================
            WebElement mooleSection = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[contains(@class,'bg-accordian-close') and .//h5[normalize-space()='Moole']]")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", mooleSection);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", mooleSection);

            System.out.println("Moole arrow clicked - expanded");
            Thread.sleep(1000);

            // ================= CLICK CONTAINER REGISTRY =================
            WebElement containerRegistry = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[normalize-space()='Container Registry']]")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", containerRegistry);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", containerRegistry);

            System.out.println("Container Registry clicked");
            Thread.sleep(1000);


            // ================= CLICK CONNECT INTEGRATION =================
            WebElement connectIntegration = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[@href='/app/settings/project/integrations' and .//span[normalize-space()='Connect Integration']]")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", connectIntegration);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", connectIntegration);

            System.out.println("Connect Integration clicked");
            Thread.sleep(1500);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}