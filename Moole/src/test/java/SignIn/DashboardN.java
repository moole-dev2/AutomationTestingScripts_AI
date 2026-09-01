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



public class DashboardN {

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
            
         // Scroll down slowly
            for (int i = 0; i < 10; i++) {
                js.executeScript("window.scrollBy(0, 500);");
                Thread.sleep(1000);
            }

            // Scroll back up slowly
            for (int i = 0; i < 10; i++) {
                js.executeScript("window.scrollBy(0, -500);");
                Thread.sleep(1000);
            }
            
         // ================= CLICK REPOSITORIES =================

            WebElement repositoriesButton = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//button[.//span[normalize-space()='Repositories']]")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    repositoriesButton
            );

            wait.until(ExpectedConditions.visibilityOf(repositoriesButton));

            try {
                wait.until(ExpectedConditions.elementToBeClickable(repositoriesButton)).click();
            } catch (Exception e) {
                System.out.println("Normal click failed for Repositories. Using JavaScript click.");
                js.executeScript("arguments[0].click();", repositoriesButton);
            }

            System.out.println("Repositories clicked successfully");

            Thread.sleep(2000);


            // ================= CLICK CONTAINER IMAGES =================

            WebElement containerImagesButton = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//button[.//span[normalize-space()='Container images']]")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    containerImagesButton
            );

            wait.until(ExpectedConditions.visibilityOf(containerImagesButton));

            try {
                wait.until(ExpectedConditions.elementToBeClickable(containerImagesButton)).click();
            } catch (Exception e) {
                System.out.println("Normal click failed for Container images. Using JavaScript click.");
                js.executeScript("arguments[0].click();", containerImagesButton);
            }

            System.out.println("Container images clicked successfully");

            Thread.sleep(2000);

/*
            // ================= CLICK ENVIRONMENTS =================

            WebElement environmentsCard = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath(
                                "//span[normalize-space()='Environments']" +
                                "/ancestor::div[contains(@class,'rounded-2xl')][1]"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    environmentsCard
            );

            wait.until(ExpectedConditions.visibilityOf(environmentsCard));

            try {
                wait.until(ExpectedConditions.elementToBeClickable(environmentsCard)).click();
            } catch (Exception e) {
                System.out.println("Normal click failed for Environments. Using JavaScript click.");
                js.executeScript("arguments[0].click();", environmentsCard);
            }

            System.out.println("Environments clicked successfully");*/
            
         // ================= CLICK 6 BUTTONS ONE BY ONE =================

            String[] buttons = {
                    "Overview",
                    "SCA",
                    "SAST",
                    "Container Security",
                    "DockerFile",
                    "Licenses"
            };

            for (String buttonName : buttons) {

                WebElement button = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[.//span[normalize-space()='" + buttonName + "']]")
                        )
                );

                js.executeScript(
                        "arguments[0].scrollIntoView({block:'center', inline:'center'});",
                        button
                );

                Thread.sleep(1500);

                button.click();

                System.out.println(buttonName + " clicked");

                Thread.sleep(2000);
            }


            // ================= CLICK OVERVIEW AGAIN =================

            WebElement overview = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[normalize-space()='Overview']]")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center', inline:'center'});",
                    overview
            );

            Thread.sleep(1500);

            overview.click();

            System.out.println("Overview clicked again");

            Thread.sleep(2000);
            
         // ================= CLICK DEVELOPMENT ENVIRONMENT =================

            WebElement developmentButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[normalize-space()='Development']]")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    developmentButton
            );

            Thread.sleep(1500);

            developmentButton.click();

            System.out.println("Development Environment clicked");

            Thread.sleep(2000);
            
         // ================= CLICK ALL ENVIRONMENTS =================

            WebElement allEnvironments = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//p[normalize-space()='All Environments']]")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    allEnvironments
            );

            Thread.sleep(1500);

            allEnvironments.click();

            System.out.println("All Environments clicked");

            Thread.sleep(2000);
            

         // ================= CLICK FILTER =================

            WebElement filterButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Filter']")
                    )
            );

            filterButton.click();

            Thread.sleep(1000);


            // ================= CLICK DEVELOPMENT CHECKBOX =================

            WebElement developmentCheckbox = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                "//span[normalize-space()='Development']" +
                                "/preceding-sibling::div[contains(@class,'peer-checked')]"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    developmentCheckbox
            );

            Thread.sleep(1000);

            developmentCheckbox.click();

            System.out.println("Development checkbox clicked");

            Thread.sleep(1500);


            // ================= CLOSE FILTER =================

            filterButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Filter']")
                    )
            );

            filterButton.click();

            System.out.println("Filter dropdown closed");

            Thread.sleep(1500);
            
         // ================= SEARCH ENVIRONMENT =================

            WebElement searchEnvironment = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//input[@placeholder='Search environment']")
                    )
            );

            searchEnvironment.click();

            searchEnvironment.clear();

            searchEnvironment.sendKeys("testing");

            System.out.println("Typed 'testing' in Search Environment");

            Thread.sleep(2000);

            System.out.println("Filter dropdown closed");

            Thread.sleep(1500);
            
            searchEnvironment.clear();
            
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


            // ================= CLICK CHECKBOX =================

            WebElement checkbox = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath(
                                "//button[@aria-label='Filter' and @aria-expanded='true']" +
                                "/following::input[@type='checkbox'][1]" +
                                "/following-sibling::div[1]"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    checkbox
            );

            Thread.sleep(500);

            js.executeScript("arguments[0].click();", checkbox);

            System.out.println("Checkbox clicked");

            Thread.sleep(1000);


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


            // ================= REPOSITORIES & BRANCHES SEARCH =================

            WebElement repositorySearch = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//input[@placeholder='Repositories & branches']")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    repositorySearch
            );

            Thread.sleep(1000);

            js.executeScript(
                    "arguments[0].click();",
                    repositorySearch
            );

            repositorySearch.sendKeys("node-test");

            System.out.println("node-test in Repositories & branches");

            Thread.sleep(1500);


            // ================= CLEAR SEARCH =================

            repositorySearch.clear();

            System.out.println("Repositories & branches search cleared");

            Thread.sleep(1500);
            
            // ================= SEARCH CONTAINER IMAGES & TAGS =================
            // Locate search AFTER filter has completely closed

            WebElement containerSearch = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath(
                                "//input[@placeholder='Container images & tags']"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    containerSearch
            );

            Thread.sleep(800);


            // Re-locate one more time before clicking
            containerSearch = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                "//input[@placeholder='Container images & tags']"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].click();",
                    containerSearch
            );

            containerSearch.sendKeys("testing");

            System.out.println("Typed testing in Container images & tags");

            Thread.sleep(1500);


            // ================= CLEAR SEARCH =================

            containerSearch = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath(
                                "//input[@placeholder='Container images & tags']"
                            )
                    )
            );

            containerSearch.clear();

            System.out.println("Container images & tags search cleared");

            Thread.sleep(1500);
            
            
            
         // ================= CLICK 3RD FILTER =================

            WebElement thirdFilter = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("(//button[@aria-label='Filter'])[3]")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    thirdFilter
            );

            Thread.sleep(1000);

            js.executeScript("arguments[0].click();", thirdFilter);

            System.out.println("3rd Filter clicked");

            Thread.sleep(1000);


            // ================= CLICK CHECKBOX =================

            WebElement thirdCheckbox = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath(
                                "//button[@aria-label='Filter' and @aria-expanded='true']" +
                                "/following::input[@type='checkbox'][1]" +
                                "/following-sibling::div[1]"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    thirdCheckbox
            );

            Thread.sleep(500);

            js.executeScript("arguments[0].click();", thirdCheckbox);

            System.out.println("3rd Filter checkbox clicked");

            Thread.sleep(1000);


            // ================= CLOSE 3RD FILTER =================

            WebElement openThirdFilter = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath(
                                "//button[@aria-label='Filter' and @aria-expanded='true']"
                            )
                    )
            );

            js.executeScript("arguments[0].click();", openThirdFilter);

            System.out.println("3rd Filter closed");

            Thread.sleep(1000);


            // ================= SEARCH FINDINGS =================

            WebElement searchFindings = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//input[@placeholder='Search Findings']")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    searchFindings
            );

            Thread.sleep(1000);

            js.executeScript(
                    "arguments[0].click();",
                    searchFindings
            );

            searchFindings.sendKeys("script/lib/npm.py:18");

            System.out.println("Tscript/lib/npm.py:18 in Search Findings");

            Thread.sleep(1500);


            // ================= CLEAR SEARCH =================

            searchFindings.clear();

            System.out.println("Search Findings cleared");

            Thread.sleep(1500);
            
         
         
      // ================= CLICK VIEW ALL =================

         WebElement viewAll = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//a[contains(@href,'/app/project/findings') and .//span[contains(normalize-space(),'View all')]]")
                 )
         );

         js.executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 viewAll
         );

         Thread.sleep(800);

         viewAll.click();

         System.out.println("View all clicked");

         Thread.sleep(1500);
         
      // ================= CLICK LICENSES =================

         WebElement licensesButton = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//button[.//span[normalize-space()='Licenses']]")
                 )
         );

         js.executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 licensesButton
         );

         Thread.sleep(800);

         licensesButton.click();

         System.out.println("Licenses clicked");

         Thread.sleep(1500);
         
      // ================= CLICK VULNERABILITIES =================

         WebElement vulnerabilitiesButton = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//button[.//span[normalize-space()='Vulnerabilities']]")
                 )
         );

         js.executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 vulnerabilitiesButton
         );

         Thread.sleep(800);

         // Re-locate before clicking to avoid stale element
         vulnerabilitiesButton = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//button[.//span[normalize-space()='Vulnerabilities']]")
                 )
         );

         vulnerabilitiesButton.click();

         System.out.println("Vulnerabilities clicked");

         Thread.sleep(1500);
         
      // ================= SEARCH FINDINGS =================

         WebElement findingsSearch = wait.until(
                 ExpectedConditions.presenceOfElementLocated(
                         By.xpath("//input[@placeholder='Search findings…']")
                 )
         );

         js.executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 findingsSearch
         );

         Thread.sleep(800);

         // Re-locate before clicking to avoid stale element
         findingsSearch = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//input[@placeholder='Search findings…']")
                 )
         );

         findingsSearch.click();
         findingsSearch.sendKeys("script/run-gn-format.py:12");

         System.out.println("script/run-gn-format.py:12 in Search findings");

         Thread.sleep(1500);


         // ================= CLEAR SEARCH =================

         findingsSearch = wait.until(
                 ExpectedConditions.presenceOfElementLocated(
                         By.xpath("//input[@placeholder='Search findings…']")
                 )
         );

         findingsSearch.clear();

         System.out.println("Search findings cleared");

         Thread.sleep(1500);
         
         WebElement findingsSearch1 = wait.until(
                 ExpectedConditions.presenceOfElementLocated(
                         By.xpath("//input[@placeholder='Search findings…']")
                 )
         );

         js.executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 findingsSearch1
         );

         Thread.sleep(800);

         // Re-locate before clicking to avoid stale element
         findingsSearch1 = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//input[@placeholder='Search findings…']")
                 )
         );

         findingsSearch1.click();
         findingsSearch1.sendKeys("script/run-gn-format.py:18");

         System.out.println("script/run-gn-format.py:18 in Search findings");

         Thread.sleep(1500);
         
      // ================= CLICK CLEAR ALL =================

         WebElement clearAll = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//button[normalize-space()='Clear all']")
                 )
         );

         js.executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 clearAll
         );

         Thread.sleep(500);

         clearAll.click();

         System.out.println("Clear all clicked");

         Thread.sleep(1000);
         
         
         
         
      // ================= NAVIGATE BACK =================

         driver.navigate().back();

         System.out.println("Navigated back");

         Thread.sleep(2000);
      // ================= SCROLL TO BOTTOM =================

         js.executeScript(
                 "window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'});"
         );

         Thread.sleep(2000);

         System.out.println("Scrolled to bottom");


         // ================= CLOSE BROWSER =================

         driver.quit();

         System.out.println("Browser closed");
         
             } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}