package SignIn;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utils.ConfigReader;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import org.testng.annotations.Test;



public class ViewReportSCA {

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
            
         // ================= CLICK REPOSITORIES =================

            By repositories = By.xpath(
                    "//a[@href='/app/project/list-repos' and .//span[text()='Repositories']]"
            );

            WebElement repo = wait.until(
                    ExpectedConditions.presenceOfElementLocated(repositories)
            );

            js.executeScript("arguments[0].click();", repo);

            System.out.println("Repositories clicked successfully");

            Thread.sleep(1500);
            // ================= ACTIONS BUTTON =================


            WebElement actionsMain = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Actions for main']")
                    )
            );

            actionsMain.click();

            System.out.println("Actions for main clicked");

            Thread.sleep(1000);
          // ================= CLICK VIEW REPORT =================

             WebElement viewReport = wait.until(
                     ExpectedConditions.elementToBeClickable(
                             By.xpath("//a[@role='menuitem' and .//span[normalize-space()='View Report']]")
                     )
             );

             js.executeScript("arguments[0].scrollIntoView({block:'center'});", viewReport);
             Thread.sleep(500);
             viewReport.click();

             System.out.println("View Report clicked");
             Thread.sleep(2000);
             
             // ================= SCROLL DOWN =================

             js.executeScript("window.scrollBy(0, 500);");

             System.out.println("Scrolled down");

             Thread.sleep(1000);
             

             WebElement sca = wait.until(
            	        ExpectedConditions.presenceOfElementLocated(
            	                By.xpath("//p[normalize-space()='SCA']/parent::div")
            	        )
            	);

            	js.executeScript("arguments[0].scrollIntoView({block:'center'});", sca);

            	js.executeScript("arguments[0].click();", sca);

            	System.out.println("SCA clicked");
            	
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

                 WebElement highCheckbox = wait.until(
                         ExpectedConditions.presenceOfElementLocated(
                                 By.xpath("//div[contains(@class,'peer-checked:bg-gradient-to-r')]")
                         )
                 );

                 js.executeScript("arguments[0].click();", highCheckbox);

                 System.out.println("High selected");

                 Thread.sleep(500);


                 // ================= UNSELECT HIGH =================

                 js.executeScript("arguments[0].click();", highCheckbox);

                 System.out.println("High unselected");

                 Thread.sleep(500);


                 // ================= CLOSE FILTER =================

                 js.executeScript("arguments[0].click();", filterBtn);

                 System.out.println("Filter closed");

                 Thread.sleep(1000);

                 WebElement clearFilters = wait.until(
                	        ExpectedConditions.elementToBeClickable(
                	                By.xpath("//button[normalize-space()='Clear Filters']")
                	        )
                	);

                	clearFilters.click();

                	System.out.println("Clear Filters clicked");
                    Thread.sleep(1000);
                    
                    js.executeScript("window.scrollBy(0, -500);");

                    System.out.println("Scrolled up");

                    Thread.sleep(1000);
                    
                 // Click History
                    WebElement history = wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    By.xpath("//button[.//span[normalize-space()='History']]")
                            )
                    );

                    js.executeScript("arguments[0].click();", history);

                    System.out.println("History clicked");

                    Thread.sleep(1000);


                    // Scroll down
                    js.executeScript("window.scrollBy(0, 500);");

                    System.out.println("Page scrolled");

                    Thread.sleep(1000);


                    // Click Software Composition
                    WebElement softwareComposition = wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    By.xpath("//button[.//span[normalize-space()='Software Composition']]")
                            )
                    );

                    js.executeScript(
                            "arguments[0].scrollIntoView({block:'center'});",
                            softwareComposition
                    );

                    js.executeScript("arguments[0].click();", softwareComposition);

                    System.out.println("Software Composition clicked");

                    Thread.sleep(1000);


                    // Scroll down again
                    js.executeScript("window.scrollBy(0, 500);");

                    System.out.println("Page scrolled again");

                    Thread.sleep(1000);
                    
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
                    
                 // Click Copy
                    WebElement copyButton = wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    By.xpath("//button[.//span[normalize-space()='Copy']]")
                            )
                    );

                    js.executeScript("arguments[0].click();", copyButton);

                    System.out.println("Copy clicked");

                    Thread.sleep(1000);


                    // Click Download SBOM
                    WebElement downloadSBOM = wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    By.xpath("//button[.//span[normalize-space()='Download SBOM']]")
                            )
                    );

                    js.executeScript("arguments[0].click();", downloadSBOM);

                    System.out.println("Download SBOM clicked");

                    Thread.sleep(1000);


                    // Click Export Report
                    WebElement exportReport = wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    By.xpath("//button[@aria-label='Export report as PDF']")
                            )
                    );

                    js.executeScript("arguments[0].click();", exportReport);

                    System.out.println("Export Report clicked");

                    Thread.sleep(1000);

            
            	
            	
            	
             driver.quit();

             System.out.println("Browser closed");
             
                
                    } catch (Exception e) {
                   e.printStackTrace();
               } finally {
                   driver.quit();
               }
           }
       }