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

public class DashboardPage {

    public static void main(String[] args) {

        // --- ChromeOptions to use existing profile (so cookies/OTP sessions persist if needed) ---
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\psiri\\AppData\\Local\\Google\\Chrome\\User Data");
        options.addArguments("profile-directory=Profile 1");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;


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
            
            // 4️. Search Repository
            WebElement searchInput = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//input[@placeholder='Search Repositories']")
                    )
            );

            searchInput.click();
            searchInput.clear();
            searchInput.sendKeys("node-test");

            // Trigger JS input event for filtering
            js.executeScript("arguments[0].dispatchEvent(new Event('input'));", searchInput);

            System.out.println("Repository searched: node-test");

            // Wait for filtering
            Thread.sleep(3000);

         // Locate the correct "View details" button
            WebElement viewDetails = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[@aria-label='View details' and contains(@href,'/project/report/repository')]")
                    )
            );

            // Scroll into view (VERY IMPORTANT for React apps)
            js.executeScript("arguments[0].scrollIntoView(true);", viewDetails);

            // Small wait for stability
            Thread.sleep(1000);

            // Click using JavaScript (avoids overlay issues)
            js.executeScript("arguments[0].click();", viewDetails);

            System.out.println("Clicked View Details successfully!");

            // Wait for report page to load
            wait.until(ExpectedConditions.urlContains("/project/report/repository"));

            System.out.println("Report page opened successfully!");

            // Open Fix Available dropdown and click checkbox
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@aria-label='Sort by Fix Available']"))).click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='px-3 py-2 rounded-md cursor-pointer text-white text-sm font-medium mt-2 transition-colors flex items-center gap-2 text-nowrap']"))).click();

            // Close the dropdown by clicking button again
            driver.findElement(By.xpath("//button[@aria-label='Sort by Fix Available']")).click();
            Thread.sleep(1000);

            System.out.println("Clicked checkbox and closed Fix Available dropdown");
            
            // --- DEPENDENCY TYPE DROPDOWN ---
            WebElement depTypeBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@aria-label='Sort by Dependency Type']")));
            depTypeBtn.click();
            Thread.sleep(1000);

            // Click first three checkboxes in Dependency Type
            for (int i = 1; i <= 2; i++) {
                WebElement depCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("(//div[@class='px-3 py-2 rounded-md cursor-pointer text-white text-sm font-medium mt-2 transition-colors flex items-center gap-2 text-nowrap'])[" + i + "]")));
                depCheckbox.click();
                Thread.sleep(500); // small wait to ensure click is registered
            }

            // Close Dependency Type dropdown
            depTypeBtn.click();
            System.out.println("Dependency Type checkboxes clicked and dropdown closed");
            Thread.sleep(500);

            // --- CLICK CLEAR FILTERS ---
            WebElement clearFiltersBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Clear Filters')]")));
            clearFiltersBtn.click();
            Thread.sleep(1000);
            System.out.println("Clear Filters button clicked successfully");
            
            // --- CLICK REPOSITORIES ON BREADCRUMB ---
            WebElement repoBreadcrumb = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@href,'/project/list-repos')]"))); // Adjust if href differs
            js.executeScript("arguments[0].scrollIntoView(true);", repoBreadcrumb);
            js.executeScript("arguments[0].click();", repoBreadcrumb);

            System.out.println("Clicked on Repositories breadcrumb successfully!");
            
            // 2️ Click Ascending / Descending toggle
            WebElement sortOrder = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@aria-label='Switch to descending order' or @aria-label='Switch to ascending order']")
            ));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", sortOrder);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", sortOrder);

            System.out.println("Clicked Sort Order (Ascending/Descending)");
      
            // --- Step: Click Export SBOM (Download icon) ---

            WebElement exportBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[name()='svg' and @aria-label='Export SBOM']")
                    )
            );

            // Scroll into view (important for visibility)
            org.openqa.selenium.interactions.Actions actions =
                    new org.openqa.selenium.interactions.Actions(driver);

            actions.moveToElement(exportBtn).pause(Duration.ofSeconds(1)).click().perform();

            System.out.println("Export SBOM (Download) clicked!");

            // Wait so you can SEE download happening
            Thread.sleep(8000);
            
            // --- Step: Click Rescan Repository ---

            WebElement rescanBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[name()='svg' and @aria-label='Rescan Repository']")
                    )
            );

            // Move + click (important for SVG)
            org.openqa.selenium.interactions.Actions actions1 =
                    new org.openqa.selenium.interactions.Actions(driver);

            actions1.moveToElement(rescanBtn)
                   .pause(Duration.ofSeconds(1))
                   .click()
                   .perform();

            System.out.println("Rescan Repository clicked!");

            // Wait so tester can clearly see action
            Thread.sleep(8000);
            // ---------- STEP 5: Click Rescan ----------
            WebElement rescanBtn1 = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[normalize-space()='Rescan']")
                    )
            );
            rescanBtn1.click();

            System.out.println("Rescan clicked");
            Thread.sleep(8000);

            // Wait so tester can clearly see
            Thread.sleep(8000);
            
            // ---------- STEP: Click Remove Repository (Trash Icon) ----------

         // Wait for any overlay to disappear (VERY IMPORTANT)
         wait.until(ExpectedConditions.invisibilityOfElementLocated(
                 By.xpath("//div[contains(@class,'backdrop-blur')]")
         ));

         WebElement removeBtn = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//*[name()='svg' and @aria-label='Remove Repository']")
                 )
         );

         // Use Actions (best for SVG)
         new org.openqa.selenium.interactions.Actions(driver)
                 .moveToElement(removeBtn)
                 .pause(Duration.ofSeconds(1))
                 .click()
                 .perform();

         System.out.println("Remove Repository icon clicked");

         // Pause so tester can see
         Thread.sleep(5000);
         
            // Locate View button (FIRST row)
            WebElement viewBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//a[@aria-label='View Report'])[1]")
                )
            );

            // Scroll into view
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", viewBtn);
            Thread.sleep(1000);

            // Click using JS (React-safe)
            js.executeScript("arguments[0].click();", viewBtn);

            System.out.println("View Report clicked!");

            // Wait for navigation
            wait.until(ExpectedConditions.urlContains("/project/report/repository"));

            System.out.println("Navigated to Report page successfully!");

         
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
            
            

