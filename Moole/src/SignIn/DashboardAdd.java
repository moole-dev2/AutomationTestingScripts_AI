package SignIn;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

public class DashboardAdd {

    public static void main(String[] args) {

        // ChromeOptions to use existing profile  ---
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
            Thread.sleep(2000);// waits until you press Enter

            // --- Step 4: Navigate directly to Integrations page ---
            driver.get("https://moole.ai/settings/project/integrations");
            
           // Locate Search Bar
           
            WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@placeholder='Search Settings']")
                )
            );

            // Enter Search Text
            searchBox.clear();
            searchBox.sendKeys("Integrations");

            // optional small wait for filtering
            Thread.sleep(2000);

             // Locate Search Bar
            WebElement searchBox1 = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@placeholder='Search Settings']")
                    )
            );

            // Enter Text
            String searchText = "Integrations";
            searchBox1.clear();
            searchBox1.sendKeys(searchText);

            Thread.sleep(2000);

            // Validate Results
            List<WebElement> results = driver.findElements(
                    By.xpath("//*[contains(text(),'Integrations')]")
            );

            if (results.size() > 0) {
                System.out.println("First Search working");
            } else {
                System.out.println("No results found");
            }

            // Simulate BACKSPACE
            
            for (int i = 0; i < searchText.length(); i++) {
                searchBox1.sendKeys(Keys.BACK_SPACE);
                Thread.sleep(200); // small delay like human typing
            }

            System.out.println("Cleared search using backspace");

            Thread.sleep(2000);

            // SECOND SEARCH BAR
            
            WebElement secondSearchBox = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("searchQuery")   // ✅ BEST locator
                    )
            );

            String secondSearch = "git";
            secondSearchBox.clear();
            secondSearchBox.sendKeys(secondSearch);

            Thread.sleep(2000);

            List<WebElement> results2 = driver.findElements(
                    By.xpath("//*[contains(.,'" + secondSearch + "') or contains(.,'Git')]")
            );

            if (!results2.isEmpty()) {
                System.out.println("Second search working");
            } else {
                System.out.println("Second search failed");
            }

            // Backspace (second search)
            for (int i = 0; i < secondSearch.length(); i++) {
                secondSearchBox.sendKeys(Keys.BACK_SPACE);
                Thread.sleep(150);
            }

            System.out.println("Second search cleared");

            Thread.sleep(2000);

            // CLICK TARGET PATH (your absolute XPath)
            WebElement target1 = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("/html/body/div[2]/main/div[2]/div[2]/div/div[2]/div/div/div/div[2]/form/div[1]/div[1]")
                    )
            );

            target1.click();
            System.out.println("Clicked All Integrations");

            Thread.sleep(2000);

            // CLICK "Container Registry"
            
            WebElement containerRegistry = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//p[text()='Container Registry']")
                    )
            );

            containerRegistry.click();
            System.out.println("Clicked Container Registry");

            Thread.sleep(2000);
            
         // STEP 7: USE SECOND SEARCH BAR (IMPORTANT FIX)
         WebElement secondSearchBar = wait.until(
                 ExpectedConditions.visibilityOfElementLocated(
                         By.id("searchQuery")
                 )
         );

         // ---- SEARCH git ----
         secondSearchBar.click();
         secondSearchBar.clear();
         secondSearchBar.sendKeys("git");

         Thread.sleep(2000);

         List<WebElement> gitResults = driver.findElements(
                 By.xpath("//*[contains(.,'git') or contains(.,'Git')]")
         );

         System.out.println(!gitResults.isEmpty() ? "Git search in SECOND bar OK" : "No Git results");

         // clear properly
         for (int i = 0; i < "git".length(); i++) {
             secondSearchBar.sendKeys(Keys.BACK_SPACE);
             Thread.sleep(100);
         
         }

            // CLICK "Scm"
            
            WebElement scm = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//p[text()='Scm']")
                    )
            );

            scm.click();
            System.out.println("Clicked SCM");

            Thread.sleep(2000);
            
         // SEARCH docker 
         WebElement secondSearchBar2 = wait.until(
                 ExpectedConditions.visibilityOfElementLocated(
                         By.id("searchQuery")
                 )
         );

         secondSearchBar2.click();
         secondSearchBar2.clear();
         secondSearchBar2.sendKeys("docker");

         Thread.sleep(2000);

         List<WebElement> dockerResults = driver.findElements(
                 By.xpath("//*[contains(.,'docker') or contains(.,'Docker')]")
         );

         System.out.println(!dockerResults.isEmpty() ? "Docker search in SECOND bar OK" : "Docker search failed");

         // clear docker
         for (int i = 0; i < "docker".length(); i++) {
             secondSearchBar2.sendKeys(Keys.BACK_SPACE);
             Thread.sleep(100);
         }

         // STEP 10: NAVIGATE BACK
         driver.navigate().back();
         System.out.println("Navigated back after Docker search");

         // =====================================================
            // 🔘 CLICK SIDEBAR PIN / UNPIN (FIXED)
            // =====================================================

            By sidebarLocator = By.xpath(
                    "//button[contains(@class,'absolute') and contains(@class,'w-12') and contains(@class,'cursor-pointer')]"
            );

            WebElement sideBarButton = wait.until(
                    ExpectedConditions.elementToBeClickable(sidebarLocator)
            );

            sideBarButton.click();
            System.out.println("Sidebar TOGGLED (PIN/UNPIN)");

            Thread.sleep(500);
            
         // FINAL SIDEBAR PIN / UNPIN 

         By sidebarBtn = By.xpath("/html/body/div[2]/main/div[1]/div/aside/div[1]/button[1]");

         // wait for button presence
         WebElement pinButton = wait.until(
                 ExpectedConditions.presenceOfElementLocated(sidebarBtn)
         );

         // FORCE CLICK (bypasses logo overlay completely)
         ((org.openqa.selenium.JavascriptExecutor) driver)
                 .executeScript("arguments[0].click();", pinButton);

         System.out.println("Sidebar PIN/UNPIN clicked");

         // small wait for animation
         Thread.sleep(1500);

         // click again to revert
         pinButton = wait.until(
                 ExpectedConditions.presenceOfElementLocated(sidebarBtn)
         );

         ((org.openqa.selenium.JavascriptExecutor) driver)
                 .executeScript("arguments[0].click();", pinButton);

         System.out.println("Sidebar toggled back");
      // ---------------- CLICK NOTIFICATIONS ----------------
         By notificationsBtn = By.xpath("//button[@aria-label='Notifications']");

         WebElement notify = wait.until(
                 ExpectedConditions.presenceOfElementLocated(notificationsBtn)
         );

         ((JavascriptExecutor) driver)
                 .executeScript("arguments[0].click();", notify);

         System.out.println("Clicked Notifications");

         Thread.sleep(2000);

      
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
        }
    }
}