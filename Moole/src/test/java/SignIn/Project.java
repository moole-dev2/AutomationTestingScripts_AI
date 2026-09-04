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
import org.testng.annotations.Test;



public class Project {

    @Test
    public void ProjectTest() throws InterruptedException {

    	 WebDriver driver = new ChromeDriver();
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
         JavascriptExecutor js = (JavascriptExecutor) driver;

         try {
         	driver.get(ConfigReader.getProperty("baseUrl"));
             driver.manage().window().maximize();

             // ---------------- LOGIN ----------------
             driver.get("https://moole.ai/auth/signin");
             driver.manage().window().maximize();
             Thread.sleep(3000);

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

             Thread.sleep(3000);

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

       /*     // --- Step 5: Click first button inside the picker ---
            WebElement firstButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("/html/body/div[2]/main/div[3]/div[2]/div[2]/div/div[1]/button")));
            Thread.sleep(5000); 
            firstButton.click();
            System.out.println("First button inside picker clicked!");*/

            // --- Step 6: Click the "All" button in the picker ---
            
         // ================= CLICK CURRENT PROJECT =================
         // ================= CLICK CURRENT PROJECT - FIRST TIME =================
            WebElement currentProject = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[contains(@class,'truncate') and @aria-label]")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", currentProject);
            Thread.sleep(500);

            String projectName = currentProject.getAttribute("aria-label");
            System.out.println("Current project: " + projectName);

            js.executeScript("arguments[0].click();", currentProject);

            System.out.println("Current project clicked - First time");
            Thread.sleep(1000);


            // ================= CLICK CURRENT PROJECT - SECOND TIME =================
            currentProject = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[contains(@class,'truncate') and @aria-label]")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", currentProject);
            Thread.sleep(500);

            js.executeScript("arguments[0].click();", currentProject);

            System.out.println("Current project clicked - Second time");
            Thread.sleep(1000);

         // ================= SEARCH RECENT PROJECTS - MOOLE =================
            WebElement searchProject = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//input[@placeholder='Search recent projects...']")
                    )
            );

            searchProject.clear();
            searchProject.sendKeys("moole");

            System.out.println("moole entered in recent projects search");
            Thread.sleep(1000);


            // ================= CLEAR MOOLE =================
            searchProject = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//input[@placeholder='Search recent projects...']")
                    )
            );

            searchProject.clear();

            System.out.println("moole search cleared");
            Thread.sleep(1000);


            // ================= SEARCH AGAIN - MOLE =================
            searchProject = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//input[@placeholder='Search recent projects...']")
                    )
            );

            searchProject.sendKeys("mole");

            System.out.println("mole entered in recent projects search");
            Thread.sleep(1000);

        // ================= CLEAR MOLE =================
         // ================= CLEAR MOLE =================
            searchProject.click();
            searchProject.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
            searchProject.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);

            System.out.println("mole search cleared");
            Thread.sleep(1000);

            // ================= CLICK ALL =================
            WebElement allButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[normalize-space()='All']")
                    )
            );

            js.executeScript("arguments[0].click();", allButton);

            System.out.println("All button clicked");
            Thread.sleep(1500);
        
            
         // ======================================
         // ARROW BUTTON
         // ======================================

         // Wait for page to load completely
         Thread.sleep(5000);

         // Scroll down slightly
         ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
         Thread.sleep(2000);

         // Click Arrow button
      // ================= CLICK DOWN ARROW =================
         WebElement downArrow = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//span[normalize-space()='Messier 83-Ross 24816']/ancestor::div[contains(@class,'flex items-center py-3')]//button")
                 )
         );

         js.executeScript("arguments[0].scrollIntoView({block:'center'});", downArrow);
         Thread.sleep(500);
         js.executeScript("arguments[0].click();", downArrow);

         System.out.println("Down arrow clicked");
         Thread.sleep(1000);
      // ======================================
   /*   // SECOND ARROW BUTTON
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
      
  */
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
   Thread.sleep(5000);
   
   
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Keep browser open for demo/presentation
            driver.quit();
        }
    }
}
