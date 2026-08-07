package SignIn;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utils.ConfigReader;
import java.time.Duration;
import java.util.Scanner;
import org.testng.annotations.Test;



public class MembersTeam {

    @Test
    public void MembersTeamTest() throws InterruptedException {
    	
    	 // --- ChromeOptions to use existing profile (so cookies/OTP sessions persist if needed) ---
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\psiri\\AppData\\Local\\Google\\Chrome\\User Data");
        options.addArguments("profile-directory=Profile 1");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

            try {
                // --- Step 1: Open Moole.ai and click Sign In ---
            	driver.get(ConfigReader.getProperty("baseUrl"));
               driver.manage().window().maximize();

               driver.get("https://moole.ai/auth/signin");

                try {
    	            Thread.sleep(2000);
    	        } catch (InterruptedException e) {
    	            e.printStackTrace();
    	        }

                // --- Step 2: Enter Email ---
                WebElement emailField = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
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
                Thread.sleep(1000);// waits until you press Enter

                // --- Step 4: Navigate directly to Integrations page ---
                driver.get("https://moole.ai/app/settings/project/integrations");
                
                WebElement members = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//a[@href='/app/settings/teams/members']")));

                js.executeScript("arguments[0].click();", members);
                System.out.println("Clicked on Members");

                Thread.sleep(2000);

             // ================= CLICK INVITE MEMBER ================
                By inviteMemberBtn = By.xpath("//button[.//span[normalize-space()='Invite Member']]");
                WebElement inviteMember = wait.until(
                        ExpectedConditions.elementToBeClickable(inviteMemberBtn));

                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block:'center'});", inviteMember);

                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].click();", inviteMember);

                System.out.println("Clicked Invite Member");
                // ================= ENTER EMAIL =================
                WebElement emailInput = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//input[@type='email' and @name='email']")
                        )
                );

                emailInput.clear();
                emailInput.sendKeys("moole.dev.2@gmail.com");
                System.out.println("Email entered successfully");
                
                WebElement selectRoleDropdown = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[.//span[normalize-space()='Select Role']]")
                        )
                );

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectRoleDropdown);
                System.out.println("Select Role dropdown opened");
                
                By developerOption = By.xpath(
                        "//div[@role='option' and .//span[normalize-space()='Developer']]"
                );

                WebElement dev = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(developerOption));

                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block:'center'});", dev);

                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].click();", dev);

                System.out.println("Developer option selected");

           
                // ================= CLICK INVITE USER BUTTON =================
                By inviteButton = By.xpath(
                        "//button[@type='submit' and .//span[normalize-space()='Invite User']]");

                WebElement inviteUser = wait.until(
                        ExpectedConditions.elementToBeClickable(inviteButton));
                inviteUser.click();
                System.out.println("Invite User button clicked");
                Thread.sleep(2000);
            // ================= STATUS DROPDOWN =================
            WebElement statusDropdown = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//button[@aria-label='Sort by Status']")));

            js.executeScript("arguments[0].click();", statusDropdown);
            Thread.sleep(2000);

            WebElement accepted = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//*[contains(text(),'Accepted')]")));

            WebElement pending = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//*[contains(text(),'Pending')]")));

            // Accepted ON/OFF
            js.executeScript("arguments[0].click();", accepted);
            Thread.sleep(1000);
            js.executeScript("arguments[0].click();", accepted);
            Thread.sleep(1000);

            // Pending ON/OFF
            js.executeScript("arguments[0].click();", pending);
            Thread.sleep(1000);
            js.executeScript("arguments[0].click();", pending);
            Thread.sleep(1000);

            // Both ON
            js.executeScript("arguments[0].click();", accepted);
            js.executeScript("arguments[0].click();", pending);
            Thread.sleep(1500);

            // Both OFF
            js.executeScript("arguments[0].click();", accepted);
            js.executeScript("arguments[0].click();", pending);

            js.executeScript("arguments[0].click();", statusDropdown);
            System.out.println("Status dropdown completed");
            
            WebElement roleDropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Sort by Selected Role']")
                    )
            );

            js.executeScript("arguments[0].click();", roleDropdown);
            System.out.println("Role dropdown opened");
            Thread.sleep(1500);

            // Target the actual checkbox input inside the Developer row
            By developerCheckbox = By.xpath(
                    "//div[.//p[normalize-space()='API User']]//input[@type='checkbox']");

            // ================= CHECK =================
            WebElement checkboxToCheck = wait.until(
                    ExpectedConditions.presenceOfElementLocated(developerCheckbox));
            // Use JavaScript click to bypass overlay/visibility issues
            js.executeScript("arguments[0].click();", checkboxToCheck);
            System.out.println("API User CHECKED | isSelected: " + checkboxToCheck.isSelected());
            Thread.sleep(2000);

            // ================= UNCHECK =================
            WebElement checkboxToUncheck = driver.findElement(developerCheckbox); // re-fetch
            js.executeScript("arguments[0].click();", checkboxToUncheck);
            System.out.println("API User UNCHECKED | isSelected: " + checkboxToUncheck.isSelected());
            Thread.sleep(2000);
            // Close dropdown
            roleDropdown.click();
            System.out.println("Dropdown closed");
            
            // ================= SEARCH BAR OPERATIONS =================

            By searchInput = By.xpath("//input[contains(@placeholder,'Search by team')]");

         // --- Search 1 ---
         WebElement search = wait.until(
                 ExpectedConditions.visibilityOfElementLocated(searchInput)
         );

         search.clear();
         search.sendKeys("Sirisha");
         System.out.println("Searched: Sirisha");
         Thread.sleep(2000);

         search.clear();
         System.out.println("Cleared search");
         Thread.sleep(1000);

         // --- Search 2 ---
         search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
         search.clear();
         search.sendKeys("Shree");
         System.out.println("Searched: Shree");
         Thread.sleep(2000);

         search.clear();
         System.out.println("Cleared search");
         Thread.sleep(1000);

         // --- Search 3 ---
         search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
         search.clear();
         search.sendKeys("moole.dev.2@gmail.com");
         System.out.println("Searched email 1");
         Thread.sleep(2000);

         search.clear();
         System.out.println("Cleared search");
         Thread.sleep(1000);

         // --- Search 4 ---
         search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
         search.clear();
         search.sendKeys("moole.dev.3@gmail.com");
         System.out.println("Searched email 2");
         Thread.sleep(2000);

         search.clear();
         System.out.println("Cleared search");
         Thread.sleep(1000);

         // --- Exit search ---
         search.sendKeys(Keys.ESCAPE);
         System.out.println("Search closed");
         Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}