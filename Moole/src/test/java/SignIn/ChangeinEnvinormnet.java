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



public class ChangeinEnvinormnet {

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
        
         // ================= CLICK ENV BUTTON =================

         WebElement envButton = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//button[normalize-space()='env']")
                 )
         );

         js.executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 envButton
         );

         Thread.sleep(500);

         js.executeScript(
                 "arguments[0].click();",
                 envButton
         );

         System.out.println("env button clicked");

         Thread.sleep(1000);


         // ================= SELECT PRODUCTION =================

         By productionEnvironmentLocator = By.xpath(
                 "//button[.//span[normalize-space()='Production'] and .//p[normalize-space()='Add to environment']]"
         );

         WebElement productionEnvironment = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         productionEnvironmentLocator
                 )
         );

         js.executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 productionEnvironment
         );

         Thread.sleep(500);

         js.executeScript(
                 "arguments[0].click();",
                 productionEnvironment
         );

         System.out.println("Production environment selected");

         Thread.sleep(1000);
        
       
        		// ================= CLICK DASHBOARD =================

        		WebElement dashboard = wait.until(
        		        ExpectedConditions.elementToBeClickable(
        		                By.xpath("//a[@href='/app/project/dashboard']//span[normalize-space()='Dashboard']")
        		        )
        		);

        		js.executeScript(
        		        "arguments[0].scrollIntoView({block:'center'});",
        		        dashboard
        		);

        		Thread.sleep(500);

        		js.executeScript(
        		        "arguments[0].click();",
        		        dashboard
        		);

        		System.out.println("Dashboard clicked");

        		Thread.sleep(1500);


        	    

                // ================= CLICK FILTER =================

                   WebElement filterButton = wait.until(
                           ExpectedConditions.elementToBeClickable(
                                   By.xpath("//button[@aria-label='Filter']")
                           )
                   );

                   filterButton.click();

                   Thread.sleep(1000);
                   
                // ================= PRODUCTION CHECKBOX =================

                   By productionCheckboxLocator = By.xpath(
                       "//span[normalize-space()='Production']" +
                       "/ancestor::div[contains(@class,'cursor-pointer')][1]" +
                       "//input[@type='checkbox']"
                   );

                   WebElement productionCheckbox = wait.until(
                       ExpectedConditions.presenceOfElementLocated(
                           productionCheckboxLocator
                       )
                   );

                   js.executeScript(
                       "arguments[0].scrollIntoView({block:'center'});",
                       productionCheckbox
                   );

                   Thread.sleep(500);

                   // CHECK PRODUCTION
                   js.executeScript(
                       "arguments[0].click();",
                       productionCheckbox
                   );

                   System.out.println("Production checkbox checked");

                   Thread.sleep(1000);


                   // ================= PRODUCTION UNCHECK =================

                   productionCheckbox = wait.until(
                       ExpectedConditions.presenceOfElementLocated(
                           productionCheckboxLocator
                       )
                   );

                   js.executeScript(
                       "arguments[0].click();",
                       productionCheckbox
                   );

                   System.out.println("Production checkbox unchecked");

                   Thread.sleep(1000);
           		

                // ================= CLOSE FILTER =================

                filterButton = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[@aria-label='Filter']")
                        )
                );

                filterButton.click();

                System.out.println("Filter dropdown closed");

                Thread.sleep(1500);
                
                // ================= CLICK 2ND FILTER =================

                WebElement secondFilter = wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                By.xpath("(//button[@aria-label='Filter'])[2]")
                        )
                );

                js.executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        secondFilter
                );

                Thread.sleep(1000);

                js.executeScript("arguments[0].click();", secondFilter);

                System.out.println("2nd Filter clicked");

                Thread.sleep(1000);
                
                By productionCheckboxLocator1 = By.xpath(
                	    "//span[normalize-space()='Production']" +
                	    "/ancestor::div[contains(@class,'cursor-pointer')][1]" +
                	    "//input[@type='checkbox']"
                	);

                	WebElement productionCheckbox1 = wait.until(
                	    ExpectedConditions.elementToBeClickable(productionCheckboxLocator1)
                	);

                	js.executeScript("arguments[0].click();", productionCheckbox1);

                	System.out.println("Production checked");

                	Thread.sleep(2000);

                	productionCheckbox1 = wait.until(
                	    ExpectedConditions.elementToBeClickable(productionCheckboxLocator1)
                	);

                	js.executeScript("arguments[0].click();", productionCheckbox1);

                	System.out.println("Production unchecked");

                	Thread.sleep(2000);


                // ================= CLOSE 2ND FILTER =================

                WebElement openFilter = wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                By.xpath("//button[@aria-label='Filter' and @aria-expanded='true']")
                        )
                );

                // JavaScript avoids sticky header interception
                js.executeScript("arguments[0].click();", openFilter);

                System.out.println("2nd Filter closed");

                Thread.sleep(1000);
                
             // ============================================================
             // CLICK FINDINGS
             // ============================================================

             By findingsLocator = By.xpath(
                 "//a[@href='/app/project/findings']"
             );

             WebElement findings = wait.until(
                 ExpectedConditions.elementToBeClickable(findingsLocator)
             );

             js.executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 findings
             );

             Thread.sleep(500);

             js.executeScript(
                 "arguments[0].click();",
                 findings
             );

             System.out.println("Findings clicked");

             Thread.sleep(1500);


             // ============================================================
             // UAT - CHECK
             // ============================================================

             By uatLocator = By.xpath(
                 "//button[.//span[normalize-space()='UAT']]"
             );

             WebElement uat = wait.until(
                 ExpectedConditions.elementToBeClickable(uatLocator)
             );

             js.executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 uat
             );

             Thread.sleep(500);

             // CHECK UAT
             js.executeScript(
                 "arguments[0].click();",
                 uat
             );

             System.out.println("UAT checked");

             Thread.sleep(1000);


             // ============================================================
             // UAT - UNCHECK
             // ============================================================

             uat = wait.until(
                 ExpectedConditions.elementToBeClickable(uatLocator)
             );

             js.executeScript(
                 "arguments[0].click();",
                 uat
             );

             System.out.println("UAT unchecked");

             Thread.sleep(1000);


             // ============================================================
             // DEVELOPMENT - CHECK
             // ============================================================

             By developmentLocator = By.xpath(
                 "//button[.//span[normalize-space()='Development']]"
             );

             WebElement development = wait.until(
                 ExpectedConditions.elementToBeClickable(developmentLocator)
             );

             js.executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 development
             );

             Thread.sleep(500);

             // CHECK DEVELOPMENT
             js.executeScript(
                 "arguments[0].click();",
                 development
             );

             System.out.println("Development checked");

             Thread.sleep(1000);


             // ============================================================
             // DEVELOPMENT - UNCHECK
             // ============================================================

             development = wait.until(
                 ExpectedConditions.elementToBeClickable(developmentLocator)
             );

             js.executeScript(
                 "arguments[0].click();",
                 development
             );

             System.out.println("Development unchecked");

             Thread.sleep(1000);
             
             By nodeTestLocator = By.xpath(
            		    "//button[.//span[normalize-space()='node-test']]"
            		);

            		// Check node-test
            		WebElement nodeTest = wait.until(
            		    ExpectedConditions.elementToBeClickable(nodeTestLocator)
            		);

            		js.executeScript(
            		    "arguments[0].scrollIntoView({block:'center'});",
            		    nodeTest
            		);

            		Thread.sleep(500);

            		js.executeScript("arguments[0].click();", nodeTest);

            		System.out.println("node-test checked");

            		Thread.sleep(1000);

            		// Uncheck node-test
            		nodeTest = wait.until(
            		    ExpectedConditions.elementToBeClickable(nodeTestLocator)
            		);

            		js.executeScript("arguments[0].click();", nodeTest);

            		System.out.println("node-test unchecked");

            		Thread.sleep(1000);
            		  // ================= CLICK REPOSITORIES =================

                    By repositories1 = By.xpath(
                            "//a[@href='/app/project/list-repos' and .//span[text()='Repositories']]"
                    );

                    WebElement repo1 = wait.until(
                            ExpectedConditions.presenceOfElementLocated(repositories1)
                    );

                    js.executeScript("arguments[0].click();", repo1);

                    System.out.println("Repositories clicked successfully");

                    Thread.sleep(1500);
                
                    By trackAnotherBranchLocator = By.xpath(
                    	    "//button[.//span[contains(normalize-space(), 'Track another branch from')]" +
                    	    " and .//span[normalize-space()='node-test']]"
                    	);

                    	WebElement trackAnotherBranch = wait.until(
                    	    ExpectedConditions.elementToBeClickable(trackAnotherBranchLocator)
                    	);

                    	js.executeScript(
                    	    "arguments[0].scrollIntoView({block:'center'});",
                    	    trackAnotherBranch
                    	);

                    	Thread.sleep(500);

                    	js.executeScript("arguments[0].click();", trackAnotherBranch);

                    	System.out.println("Track another branch from node-test clicked");

                    	Thread.sleep(2000);
                    
 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}