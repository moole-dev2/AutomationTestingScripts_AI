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



public class DashboardPage {

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
            
            
       /*    WebElement importRepoBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(.,'Import Repositories')]")));

            importRepoBtn.click();

            System.out.println("Import Repositories clicked successfully");
            Thread.sleep(2000);
            
            WebElement closePopupBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Close popup']")));

            closePopupBtn.click();

            System.out.println("Popup closed successfully");
            Thread.sleep(2000);
            
            
            WebElement addRepositoryBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//p[normalize-space()='Add Repository']]")));

            addRepositoryBtn.click();

            System.out.println("Add Repository button clicked successfully");
            Thread.sleep(2000);
            
            WebElement viewReposBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[normalize-space()='View Repos']")));

            viewReposBtn.click();

            System.out.println("View Repos button clicked successfully");
            Thread.sleep(2000);
            
            WebElement addBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@type='submit' and normalize-space()='Add']")));

            addBtn.click();

            System.out.println("Add button clicked successfully");
            Thread.sleep(2000);
            
            WebElement repositories = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[@href='/app/project/list-repos']")));

            js.executeScript("arguments[0].click();", repositories);

            System.out.println("Repositories clicked successfully");*/
            Thread.sleep(2000);
            
         // Open Filter Dropdown
            WebElement filterDropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Sort by Filter']")));

            js.executeScript("arguments[0].click();", filterDropdown);

            System.out.println("Filter dropdown opened");
            Thread.sleep(2000);

            // Close Filter Dropdown
            js.executeScript("arguments[0].click();", filterDropdown);

            System.out.println("Filter dropdown closed");
            Thread.sleep(2000);
            
            
            WebElement searchInput = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//input[@placeholder='Search Repositories']")
                    )
            );

            searchInput.sendKeys("node-test");
            js.executeScript("arguments[0].dispatchEvent(new Event('input'));", searchInput);

            Thread.sleep(3000);

            WebElement viewDetails = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[contains(@aria-label,'View details')]")
                    )
            );

            js.executeScript("arguments[0].click();", viewDetails);

            wait.until(ExpectedConditions.urlContains("/project/report/repository"));

            System.out.println("Report page opened");
           

            // ================= FIX AVAILABLE (FIXED PART) =================

            Thread.sleep(3000);

            js.executeScript("window.scrollBy(0, 400);");
            Thread.sleep(1500);

            WebElement fixAvailableBtn = wait.until(driver1 -> {
                try {
                    WebElement el = driver1.findElement(
                            By.xpath("//button[contains(@aria-label,'Fix Available')]")
                    );
                    return (el.isDisplayed() && el.isEnabled()) ? el : null;
                } catch (Exception e) {
                    return null;
                }
            });

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", fixAvailableBtn);
            Thread.sleep(1000);

            js.executeScript("arguments[0].click();", fixAvailableBtn);

            System.out.println("Fix Available dropdown opened");

            Thread.sleep(1500);

            // click checkbox inside dropdown
            WebElement fixCheckbox = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[contains(@class,'cursor-pointer') and contains(@class,'text-white')]")
                    )
            );

            js.executeScript("arguments[0].click();", fixCheckbox);

            System.out.println("Checkbox clicked");

            Thread.sleep(1000);

            // close dropdown
            js.executeScript("arguments[0].click();", fixAvailableBtn);

            System.out.println("Fix Available dropdown closed");

            // ================= DEPENDENCY TYPE =================
         // ================= WAIT FOR PAGE STABILITY =================
            Thread.sleep(3000);

            // wait for overlays (React loader fix)
            try {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'loading')] | //div[contains(@class,'spinner')] | //div[contains(@class,'backdrop')]")
                ));
            } catch (Exception ignored) {}

            js.executeScript("window.scrollBy(0, 400);");
            Thread.sleep(2000);

            // ================= FIND DEPENDENCY BUTTON (RETRY SAFE) =================
            WebElement depTypeBtn = null;

            String[] xpaths = new String[] {
                    "//button[contains(@aria-label,'Dependency')]",
                    "//button[contains(.,'Dependency')]",
                    "//button[contains(text(),'Dependency')]"
            };

            // retry loop (VERY IMPORTANT)
            for (int i = 0; i < 5; i++) {
                try {
                    for (String xp : xpaths) {
                        try {
                            depTypeBtn = driver.findElement(By.xpath(xp));
                            if (depTypeBtn.isDisplayed()) {
                                break;
                            }
                        } catch (Exception ignored) {}
                    }

                    if (depTypeBtn != null && depTypeBtn.isDisplayed()) {
                        break;
                    }

                } catch (Exception ignored) {}

                Thread.sleep(1000);
            }

            // ================= FINAL VALIDATION =================
            if (depTypeBtn == null) {
                throw new RuntimeException("Dependency Type button NOT FOUND in DOM");
            }

            // ================= CLICK SAFELY =================
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", depTypeBtn);
            Thread.sleep(1000);

            try {
                depTypeBtn.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", depTypeBtn);
            }

            System.out.println("Dependency dropdown opened");

            // ================= SELECT OPTIONS =================
            Thread.sleep(1500);

            for (int i = 1; i <= 2; i++) {
                WebElement option = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("(//div[contains(@class,'cursor-pointer')])[ " + i + " ]")
                        )
                );

                js.executeScript("arguments[0].click();", option);
                Thread.sleep(500);
            }

            System.out.println("Dependency options selected");

            // ================= CLOSE DROPDOWN =================
            try {
                depTypeBtn.click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", depTypeBtn);
            }
      /*      // ================= CLEAR FILTERS =================
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Clear Filters')]")
            )).click();

            System.out.println("Filters cleared");*/

         // ================= OPEN FILTER =================
         WebElement filterBtn = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//button[contains(@aria-label,'Filter') or .//span[text()='Filter']]")
                 )
         );

         js.executeScript("arguments[0].click();", filterBtn);
         System.out.println("Filter opened");

         Thread.sleep(1500);

      // ================= SELECT HIGH =================
         WebElement highOption = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//p[normalize-space()='high']/ancestor::div[contains(@class,'cursor-pointer')]")
                 )
         );

         js.executeScript("arguments[0].click();", highOption);
         System.out.println("High selected");

         Thread.sleep(1500);

         // ================= UNSELECT HIGH =================
         WebElement highOptionAgain = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//p[normalize-space()='high']/ancestor::div[contains(@class,'cursor-pointer')]")
                 )
         );

         js.executeScript("arguments[0].click();", highOptionAgain);
         System.out.println("High unchecked");

         Thread.sleep(1500);
         

         // STEP 1: find dropdown more safely
         WebElement sastDropdown = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//*[contains(text(),'SAST')]/ancestor::*[self::button or self::div][1]"))
         );

         sastDropdown.click();
         System.out.println("Dropdown opened");

         // STEP 2: wait for option
         WebElement sastOption = wait.until(
                 ExpectedConditions.visibilityOfElementLocated(
                         By.xpath("//p[normalize-space()='SAST']"))
         );

         ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sastOption);
         System.out.println("SAST clicked");

         // ================= CLOSE FILTER =================
         js.executeScript("arguments[0].click();", filterBtn);
         System.out.println("Filter closed");

         Thread.sleep(2000);

            // ================= NAV BACK =================
            WebElement repoBreadcrumb = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[contains(@href,'list-repos')]")
                    )
            );

            js.executeScript("arguments[0].click();", repoBreadcrumb);

            System.out.println("Back to repositories");
            
            WebElement importRepoBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//p[normalize-space()='Import Repositories']]")
                    )
            );

            try {
                importRepoBtn.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", importRepoBtn);
            }

            System.out.println("Import Repositories button clicked");
            Thread.sleep(2000);


         // ================= OPEN FILTER =================
         WebElement filterBtn1 = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//button[@aria-label='Sort by Filter']")
                 )
         );

         js.executeScript("arguments[0].click();", filterBtn1);
         System.out.println("Filter opened");

         // IMPORTANT: wait for dropdown animation/render
         Thread.sleep(2000);

         // ================= SELECT CRITICAL =================
         // click by label container (NOT input, NOT text-only div)
         WebElement criticalOption = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//*[contains(.,'Critical') and @role='button'] | //label[contains(.,'Critical')] | //div[contains(.,'Critical')]")
                 )
         );

         js.executeScript("arguments[0].click();", criticalOption);
         System.out.println("Critical selected");

         Thread.sleep(1500);

         // ================= UNSELECT CRITICAL =================
         WebElement criticalOptionAgain = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//*[contains(.,'Critical') and @role='button'] | //label[contains(.,'Critical')] | //div[contains(.,'Critical')]")
                 )
         );

         js.executeScript("arguments[0].click();", criticalOptionAgain);
         System.out.println("Critical unchecked");

         Thread.sleep(1500);

         // ================= CLOSE FILTER =================
         js.executeScript("arguments[0].click();", filterBtn1);
         System.out.println("Filter closed");
    

            // ================= RESCAN =================
            WebElement rescan = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//*[name()='svg' and @aria-label='Rescan Repository']")
                    )
            );

            new Actions(driver).moveToElement(rescan).click().perform();
            System.out.println("Rescan clicked");

            Thread.sleep(5000);
         
      // ================= RESCAN TOGGLE =================
         WebElement rescanToggle = wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("//button[@role='switch' and contains(@aria-checked,'false') or contains(@aria-checked,'true')]")
         ));

         js.executeScript("arguments[0].scrollIntoView({block:'center'});", rescanToggle);
         Thread.sleep(1000);

         // click ON/OFF toggle
         js.executeScript("arguments[0].click();", rescanToggle);
         System.out.println("Rescan toggle clicked");

         Thread.sleep(2000);

         // optional second toggle (ON/OFF cycle)
         js.executeScript("arguments[0].click();", rescanToggle);
         System.out.println("Rescan toggle toggled again");

            // ================= REMOVE REPO =================
            WebElement remove = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//*[name()='svg' and @aria-label='Remove Repository']")
                    )
            );

            new Actions(driver).moveToElement(remove).click().perform();
            System.out.println("Remove repo clicked");

            Thread.sleep(5000);
      
        
            // ================= VIEW REPORT =================
            WebElement view = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("(//a[@aria-label='View Report'])[1]")
                    )
            );

            js.executeScript("arguments[0].click();", view);

            System.out.println("View report clicked");
            
            WebElement historyBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[normalize-space()='History']")
                    )
            );

            try {
                historyBtn.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", historyBtn);
            }

            System.out.println("History button clicked");
            Thread.sleep(2000);
            
            WebElement scmBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[normalize-space()='Software Composition']")
                    )
            );

            try {
                scmBtn.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", scmBtn);
            }

            System.out.println("Software Composition button clicked");
            Thread.sleep(2000);
            
            WebElement componentsBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[normalize-space()='Components']")
                    )
            );

            try {
                componentsBtn.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", componentsBtn);
            }

            System.out.println("Components button clicked");
            Thread.sleep(2000);
           
            WebElement overviewBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("(//button[normalize-space()='Overview'])[last()]")
                    )
            );

            overviewBtn.click();
            System.out.println("Overview clicked (last match)");
            Thread.sleep(2000);
            
            WebElement rawBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[normalize-space()='Raw']")
                    )
            );

            rawBtn.click();
            System.out.println("Raw button clicked");
            Thread.sleep(2000);

            // ================= EXPORT SBOM =================
            WebElement exportPdfBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Export report as PDF']")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", exportPdfBtn);
            Thread.sleep(1000);

            js.executeScript("arguments[0].click();", exportPdfBtn);

            System.out.println("Export Report PDF clicked");
            Thread.sleep(1000); 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}