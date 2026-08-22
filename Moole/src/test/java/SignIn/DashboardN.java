package SignIn;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utils.ConfigReader;
import java.time.Duration;
import java.util.Scanner;
import org.testng.annotations.Test;



public class DashboardN {

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

            // ================= NAVIGATE =================
            WebElement dashboard = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//a[@href='/app/project/dashboard']")));

            js.executeScript("arguments[0].click();", dashboard);

            System.out.println("Dashboard clicked successfully");
     
            Thread.sleep(2000);
            
         // Scroll down slowly
            for (int i = 0; i < 10; i++) {
                js.executeScript("window.scrollBy(0, 500);");
                Thread.sleep(1000);
            }

            // Scroll back up slowly
            for (int i = 0; i < 10; i++) {
                js.executeScript("window.scrollBy(0, -500);");
                Thread.sleep(1000);
            }
         // =========================================================
         // TOP GRID - CLICK ALL 3 CARDS ONE AFTER ANOTHER
         // =========================================================

         // ---------- 1. REPOSITORIES ----------
         WebElement repositories = wait.until(
                 ExpectedConditions.presenceOfElementLocated(
                         By.xpath("//button[.//span[normalize-space()='Repositories']]")
                 )
         );

         // Scroll to Repositories
         js.executeScript(
                 "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
                 repositories
         );

         Thread.sleep(1500);

         // Click Repositories
         wait.until(
                 ExpectedConditions.elementToBeClickable(repositories)
         ).click();

         System.out.println("Repositories clicked");

         Thread.sleep(2000);


         // ---------- RETURN / SCROLL TO GRID ----------
         js.executeScript("window.scrollBy(0, 400);");
         Thread.sleep(1000);


         // ---------- 2. CONTAINER IMAGES ----------
         WebElement containerImages = wait.until(
                 ExpectedConditions.presenceOfElementLocated(
                         By.xpath("//button[.//span[normalize-space()='Container images']]")
                 )
         );

         // Scroll to Container Images
         js.executeScript(
                 "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
                 containerImages
         );

         Thread.sleep(1500);

         // Click Container Images
         wait.until(
                 ExpectedConditions.elementToBeClickable(containerImages)
         ).click();

         System.out.println("Container images clicked");

         Thread.sleep(2000);


         // ---------- RETURN / SCROLL TO GRID ----------
         js.executeScript("window.scrollBy(0, 400);");
         Thread.sleep(1000);


         // ---------- 3. ENVIRONMENTS ----------
         WebElement environments = wait.until(
                 ExpectedConditions.presenceOfElementLocated(
                         By.xpath(
                                 "//div[.//span[normalize-space()='Environments']" +
                                 " and contains(@class,'rounded-2xl')]"
                         )
                 )
         );

         // Scroll to Environments
         js.executeScript(
                 "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
                 environments
         );

         Thread.sleep(1500);

         // Click Environments
         // It is a DIV, so use JavaScript click
         js.executeScript(
                 "arguments[0].click();",
                 environments
         );

         System.out.println("Environments clicked");

         Thread.sleep(2000);


         // =========================================================
         // AFTER ALL THREE - SCROLL BACK TO TOP
         // =========================================================

         js.executeScript(
                 "window.scrollTo({top: 0, behavior: 'smooth'});"
         );

         Thread.sleep(2000);

         System.out.println("Scrolled back to top");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}