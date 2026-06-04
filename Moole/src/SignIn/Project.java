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

public class Project {

    public static void main(String[] args) throws InterruptedException {

        // --- ChromeOptions to use existing profile ---
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\psiri\\AppData\\Local\\Google\\User Data");
        options.addArguments("profile-directory=Profile 1");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // --- Step 1: Open Moole.ai and Sign In ---
            driver.get("https://moole.ai/auth/signin");
            driver.manage().window().maximize();

            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@type='email']")));
            emailField.sendKeys("moole.dev.2@gmail.com");

            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Continue')]")));
            continueBtn.click();

            // --- Step 2: Wait for OTP manually ---
            System.out.println("Enter OTP manually in the browser, then press Enter here...");
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            Thread.sleep(2000);

            // --- Step 3: Navigate directly to Integrations page ---
            driver.get("https://moole.ai/app/settings/project/integrations");
            Thread.sleep(5000); // pause to let page load
            System.out.println("Integrations page opened!");

            // --- Step 4: Click on "My Project" (project picker) ---
            WebElement projectPicker = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@aria-label='Open resource picker']")));
            Thread.sleep(5000); 
            projectPicker.click();
            System.out.println("Project picker clicked!");

            // --- Step 5: Click first button inside the picker ---
            WebElement firstButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[2]/main/div[3]/div[2]/div[2]/div/div[1]/button")));
            Thread.sleep(5000); 
            firstButton.click();
            System.out.println("First button inside picker clicked!");

            // --- Step 6: Click the "All" button in the picker ---
            WebElement allButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[2]/main/div[3]/div[2]/div[4]/button[2]")));
            Thread.sleep(5000);
            allButton.click();
            System.out.println("'All' button clicked!");
            
         // ======================================
         // ARROW BUTTON
         // ======================================

         // Wait for page to load completely
         Thread.sleep(5000);

         // Scroll down slightly
         ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
         Thread.sleep(2000);

         // Click Arrow button
         WebElement arrowBtn = wait.until(
                 ExpectedConditions.presenceOfElementLocated(
                         By.xpath("//button[contains(@class,'w-8 h-8') and contains(@class,'rounded-full') and contains(@class,'shrink-0')]")
                 )
         );
         ((JavascriptExecutor) driver).executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 arrowBtn
         );
         Thread.sleep(2000);
         ((JavascriptExecutor) driver).executeScript(
                 "arguments[0].click();",
                 arrowBtn
         );
         System.out.println("Clicked Arrow Button");
         Thread.sleep(3000);
         
      // ======================================
      // SECOND ARROW BUTTON
      // ======================================

      // Wait for page to load completely
      Thread.sleep(5000);

      // Scroll down slightly
      ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
      Thread.sleep(2000);

      // Click Second Arrow button
      WebElement arrowBtn2 = wait.until(
              ExpectedConditions.presenceOfElementLocated(
                      By.xpath("(//button[contains(@class,'w-8 h-8') and contains(@class,'rounded-full') and contains(@class,'shrink-0')])[2]")
              )
      );
      ((JavascriptExecutor) driver).executeScript(
              "arguments[0].scrollIntoView({block:'center'});",
              arrowBtn2
      );
      Thread.sleep(2000);
      ((JavascriptExecutor) driver).executeScript(
              "arguments[0].click();",
              arrowBtn2
      );
      System.out.println("Clicked Second Arrow Button");
      Thread.sleep(3000);
      
  
   // ======================================
   // CLICK MOOLE PROJECT
   // ======================================

   // Wait for page to load completely
   Thread.sleep(5000);

   // Scroll down slightly
   ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
   Thread.sleep(2000);

   // Click Moole project
   WebElement mooleProject = wait.until(
           ExpectedConditions.presenceOfElementLocated(
                   By.xpath("//span[normalize-space()='Moole']")
           )
   );
   ((JavascriptExecutor) driver).executeScript(
           "arguments[0].scrollIntoView({block:'center'});",
           mooleProject
   );
   Thread.sleep(2000);
   ((JavascriptExecutor) driver).executeScript(
           "arguments[0].click();",
           mooleProject
   );
   System.out.println("Clicked Moole Project");
   Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Keep browser open for demo/presentation
            driver.quit();
        }
    }
}
