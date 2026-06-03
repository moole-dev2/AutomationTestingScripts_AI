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

public class CreatTeam {

    public static void main(String[] args) {

        //  ChromeOptions to use existing profile (so cookies/OTP sessions persist if needed)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\psiri\\AppData\\Local\\Google\\Chrome\\User Data");
        options.addArguments("profile-directory=Profile 1");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // --- Step 1: Open Moole.ai and click Sign In ---
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();

            driver.get("https://moole.ai/auth/signin");

            try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }

            // --- Step 2: Enter Email ---
            WebElement emailField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
            emailField.sendKeys("moole.dev.2@gmail.com");

            WebElement continueBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue')]")));
            continueBtn.click();

            // --- Step 3: Wait for OTP manually ---
            System.out.println("Please enter your OTP manually in the browser, then press Enter here...");
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            Thread.sleep(2000);
            
            // --- Step 4: Navigate directly to Integrations page ---
            driver.get("https://moole.ai/settings/project/integrations");
            Thread.sleep(5000);
            
      // Step 1: Click Teams icon
      WebElement teamsIcon = wait.until(ExpectedConditions.elementToBeClickable(
              By.xpath("//img[@alt='Teams icon']")
      ));
      teamsIcon.click();
      try {
          Thread.sleep(5000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      System.out.println("Clicked Teams icon");

      // Step 2: WAIT until "Create New Team" button is PRESENT in DOM
      WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(40));

      WebElement createTeamBtn = longWait.until(driver1 -> {
          try {
              WebElement el = driver1.findElement(By.xpath("//button[contains(.,'Create New Team')]"));
              return (el.isDisplayed()) ? el : null;
          } catch (Exception e) {
              return null;
          }
      });

      System.out.println("Create Team button FOUND");

      // Step 3: Scroll to button
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", createTeamBtn);

      // Step 4: Click Create Team
      try {
          createTeamBtn.click();
      } catch (Exception e) {
          ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createTeamBtn);
      }
      try {
          Thread.sleep(3000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }

      System.out.println("Clicked Create Team button");
     
      // Step 5: Wait for input field
      WebElement teamInput = longWait.until(ExpectedConditions.visibilityOfElementLocated(
              By.xpath("//input[@id='name']")
      ));
      try {
          Thread.sleep(3000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      
      System.out.println("Input field visible");

      // Step 6: Enter team name
      teamInput.clear();
      teamInput.sendKeys("Testing");
      try {
          Thread.sleep(3000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      System.out.println("Entered team name");

      // Step 7: Click final Create Team button
      WebElement finalCreateBtn = longWait.until(ExpectedConditions.elementToBeClickable(
              By.xpath("//button[contains(.,'Create Team')]")
      ));

      try {
          finalCreateBtn.click();
      } catch (Exception e) {
          ((JavascriptExecutor) driver).executeScript("arguments[0].click();", finalCreateBtn);
      }

      System.out.println("TEAM CREATED SUCCESSFULLY!");

      // 1. Click on the team name "Testing"
      WebElement teamName = wait.until(ExpectedConditions.elementToBeClickable(
              By.xpath("//h2[contains(@class,'font-semibold') and text()='Testing']")
      ));
      teamName.click();

      // 2. Click on "Add Repository"
      WebElement addRepository = wait.until(ExpectedConditions.elementToBeClickable(
              By.xpath("//span[text()='Add Repository']")
      ));
      addRepository.click();

      // 3. Click on "Add" button
      WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
              By.xpath("//button[@type='submit' and normalize-space()='Add']")
      ));
      addButton.click();
      System.out.println("ADDED THE REPOSITORY SUCCESSFULLY!");

      // =====================================================
      // DELETE REPOSITORY FROM TEAM
      // =====================================================

      Thread.sleep(4000);

      // Click Delete Repository button
      WebElement deleteRepoBtn = wait.until(
              ExpectedConditions.elementToBeClickable(
                      By.xpath("//button[@aria-label='Remove repository from team']")
              )
      );

      try {
          deleteRepoBtn.click();
      } catch (Exception e) {
          ((JavascriptExecutor) driver).executeScript(
                  "arguments[0].click();",
                  deleteRepoBtn
          );
      }

      System.out.println("Clicked Delete Repository Button");

      Thread.sleep(3000);

      // Click Remove button
      WebElement removeBtn = wait.until(
              ExpectedConditions.elementToBeClickable(
                      By.xpath("//button[@type='submit' and normalize-space()='Remove']")
              )
      );

      try {
          removeBtn.click();
      } catch (Exception e) {
          ((JavascriptExecutor) driver).executeScript(
                  "arguments[0].click();",
                  removeBtn
          );
      }

      System.out.println("Repository Removed Successfully!");

      Thread.sleep(3000);


      } catch (Exception e) {
            e.printStackTrace();
        } finally {
           driver.quit();
        }
    }
}
