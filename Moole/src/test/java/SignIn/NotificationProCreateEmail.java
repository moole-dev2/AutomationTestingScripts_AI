package SignIn;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.ConfigReader;

import java.time.Duration;
import java.util.Scanner;

public class NotificationProCreateEmail {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
        	
        	driver.get(ConfigReader.getProperty("baseUrl"));
            driver.manage().window().maximize();

            // ================= LOGIN =================
            driver.get("https://moole.ai/auth/signin");
            Thread.sleep(5000);

            WebElement emailField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@type='email']")));

            emailField.sendKeys("moole.dev.2@gmail.com");

            WebElement continueBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(text(),'Continue')]")));

            continueBtn.click();

            System.out.println("Enter OTP manually and press Enter...");
            new Scanner(System.in).nextLine();
            Thread.sleep(2000);

            // ================= NAVIGATE =================
            driver.get("https://moole.ai/app/settings/project/integrations");
            Thread.sleep(2000);

            WebElement notificationsLink = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[@href='/app/settings/project/notifications']")));

            js.executeScript("arguments[0].click();", notificationsLink);
            System.out.println("Notifications page opened");
            
            // Create Email Channel
            
            By createEmailChannelBtn = By.xpath(
                    "//button[.//span[normalize-space()='Create Email Channel']]"
            );

            WebElement btn = wait.until(
                    ExpectedConditions.elementToBeClickable(createEmailChannelBtn)
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", btn);

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", btn);

            System.out.println("Clicked: Create Email Channel");
            
            //---To Email
            
            By toEmailInput = By.xpath("//input[@id='create-ncc-to']");

            WebElement input = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(toEmailInput)
            );

            input.clear();
            input.sendKeys("moole.dev.2@gmail.com");
            Thread.sleep(1000);
            System.out.println("Entered email: moole.dev.2@gmail.com");
            
            // CC email 
            
            By ccEmailInput = By.xpath("//input[@id='create-ncc-cc']");

            WebElement ccInput = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(ccEmailInput)
            );

            ccInput.clear();
            ccInput.sendKeys("moole.dev.3@gmail.cm");

            System.out.println("Entered CC email: moole.dev.3@gmail.com");
            
            // Dropdown ---
            
            By allDropdown = By.xpath("//button[@aria-label='Sort by All']");

            WebElement dropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(allDropdown)
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", dropdown);

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", dropdown);
            Thread.sleep(1000);
            System.out.println("Clicked: All dropdown");
            
            // Select Low 
            
            By lowOption = By.xpath("//*[normalize-space()='Low']");

            WebElement low = wait.until(
                    ExpectedConditions.elementToBeClickable(lowOption)
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", low);

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", low);
            Thread.sleep(1000);
            System.out.println("Selected: Low");
            
            // Create Button 
            
            By createBtn = By.xpath("//button[@type='submit' and .//span[normalize-space()='Create']]");

            WebElement button = wait.until(
                    ExpectedConditions.elementToBeClickable(createBtn)
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", button);

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", button);

            System.out.println("Clicked: Create button");
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}