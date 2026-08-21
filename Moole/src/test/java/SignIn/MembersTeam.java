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
                WebElement emailField1 = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//input[@type='email' and @name='email']")
                        )
                );

                emailField1.click();
                emailField1.clear();
                emailField1.sendKeys("moole.dev.2@gmail.com");

                System.out.println("Email entered successfully");

                // Move focus away from email field
                js.executeScript("arguments[0].blur();", emailField1);

                Thread.sleep(500);
                
                WebElement selectRoleDropdown = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[.//span[normalize-space()='Select Role']]")
                        )
                );

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectRoleDropdown);
                System.out.println("Select Role dropdown opened");
                
                WebElement developerOption = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'overflow-y-auto')]//div[.//span[normalize-space()='Developer']]")
                        )
                );

                js.executeScript("arguments[0].click();", developerOption);

                System.out.println("Developer role selected");

                System.out.println("Developer role selected");

           
                // ================= CLICK INVITE USER BUTTON =================
                WebElement sendInviteButton = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[@type='submit' and .//span[normalize-space()='Send invite']]")
                        )
                );

                sendInviteButton.click();
                Thread.sleep(2000);
            // ================= STATUS DROPDOWN =================
             // ================= STATUS DROPDOWN =================

                WebElement statusDropdown = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[@type='button' and @aria-haspopup='listbox' and @aria-label='Status']")
                        )
                );

                js.executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        statusDropdown
                );

                Thread.sleep(500);

                // Open Status dropdown
                js.executeScript("arguments[0].click();", statusDropdown);

                System.out.println("Status dropdown opened");

                Thread.sleep(1000);


                // ================= ACCEPTED =================

                WebElement accepted = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                        "//div[contains(@class,'cursor-pointer') " +
                                        "and .//input[@type='checkbox'] " +
                                        "and .//span[normalize-space()='Accepted']]"
                                )
                        )
                );

                // Accepted ON
                js.executeScript("arguments[0].click();", accepted);
                System.out.println("Accepted selected");
                Thread.sleep(1000);

                // Accepted OFF
                js.executeScript("arguments[0].click();", accepted);
                System.out.println("Accepted unselected");
                Thread.sleep(1000);


                // ================= PENDING =================

                WebElement pending = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                        "//div[contains(@class,'cursor-pointer') " +
                                        "and .//input[@type='checkbox'] " +
                                        "and .//span[normalize-space()='Pending']]"
                                )
                        )
                );

                // Pending ON
                js.executeScript("arguments[0].click();", pending);
                System.out.println("Pending selected");
                Thread.sleep(1000);

                // Pending OFF
                js.executeScript("arguments[0].click();", pending);
                System.out.println("Pending unselected");
                Thread.sleep(1000);


                // ================= BOTH ON =================

                js.executeScript("arguments[0].click();", accepted);
                Thread.sleep(500);

                js.executeScript("arguments[0].click();", pending);

                System.out.println("Accepted and Pending selected");
                Thread.sleep(1500);


                // ================= BOTH OFF =================

                js.executeScript("arguments[0].click();", accepted);
                Thread.sleep(500);

                js.executeScript("arguments[0].click();", pending);

                System.out.println("Accepted and Pending unselected");
                Thread.sleep(1000);


                // ================= CLOSE STATUS DROPDOWN =================

                js.executeScript("arguments[0].click();", statusDropdown);

                System.out.println("Status dropdown completed");
            
             // ================= SELECTED ROLE DROPDOWN =================

                WebElement roleDropdown = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[@type='button' " +
                                         "and @aria-haspopup='listbox' " +
                                         "and @aria-label='Selected Role']")
                        )
                );

                js.executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        roleDropdown
                );

                Thread.sleep(500);

                // Open Selected Role dropdown
                js.executeScript("arguments[0].click();", roleDropdown);

                System.out.println("Selected Role dropdown opened");

                Thread.sleep(1000);


                // ================= API USER CHECKBOX =================

                By apiUserCheckbox = By.xpath(
                        "//div[contains(@class,'cursor-pointer') " +
                        "and .//span[normalize-space()='API User']]//input[@type='checkbox']"
                );


                // ================= CHECK API USER =================

                WebElement apiUserCheck = wait.until(
                        ExpectedConditions.presenceOfElementLocated(apiUserCheckbox)
                );

                js.executeScript("arguments[0].click();", apiUserCheck);

                System.out.println(
                        "API User CHECKED | isSelected: "
                        + apiUserCheck.isSelected()
                );

                Thread.sleep(1500);


                // ================= UNCHECK API USER =================

                WebElement apiUserUncheck = wait.until(
                        ExpectedConditions.presenceOfElementLocated(apiUserCheckbox)
                );

                js.executeScript("arguments[0].click();", apiUserUncheck);

                System.out.println(
                        "API User UNCHECKED | isSelected: "
                        + apiUserUncheck.isSelected()
                );

                Thread.sleep(1500);


                // ================= CLOSE DROPDOWN =================

                js.executeScript("arguments[0].click();", roleDropdown);

                System.out.println("Selected Role dropdown completed");
             // ================= SEARCH BAR OPERATIONS =================
             // ================= SEARCH BAR OPERATIONS =================

                By searchInput = By.xpath(
                        "//input[@placeholder='Search by team, member, or email']"
                );


                // ================= SEARCH 1 =================

                WebElement search1 = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(searchInput)
                );

                search1.clear();
                search1.sendKeys("Sirisha");

                System.out.println("Searched: Sirisha");

                Thread.sleep(1500);

                search1.clear();

                System.out.println("Cleared: Sirisha");

                Thread.sleep(800);


                // ================= SEARCH 2 =================

                WebElement search2 = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(searchInput)
                );

                search2.clear();
                search2.sendKeys("Shree");

                System.out.println("Searched: Shree");

                Thread.sleep(1500);

                search2.clear();

                System.out.println("Cleared: Shree");

                Thread.sleep(800);


                // ================= SEARCH 3 =================

                WebElement search3 = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(searchInput)
                );

                search3.clear();
                search3.sendKeys("moole.dev.2@gmail.com");

                System.out.println("Searched: moole.dev.2@gmail.com");

                Thread.sleep(1500);

                search3.clear();

                System.out.println("Cleared: moole.dev.2@gmail.com");

                Thread.sleep(800);


                // ================= SEARCH 4 =================

                WebElement search4 = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(searchInput)
                );

                search4.clear();
                search4.sendKeys("moole.dev.3@gmail.com");

                System.out.println("Searched: moole.dev.3@gmail.com");

                Thread.sleep(1500);

                search4.clear();

                System.out.println("Cleared: moole.dev.3@gmail.com");

                Thread.sleep(800);


                // ================= CLOSE SEARCH =================

                WebElement searchFinal = wait.until(
                        ExpectedConditions.elementToBeClickable(searchInput)
                );

                searchFinal.sendKeys(Keys.ESCAPE);

                System.out.println("Search closed");

                Thread.sleep(1000);

         // --- Exit search ---
         //search.sendKeys(Keys.ESCAPE);
         System.out.println("Search closed");
         Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}