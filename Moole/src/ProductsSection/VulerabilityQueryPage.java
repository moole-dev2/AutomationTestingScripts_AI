package ProductsSection;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class VulerabilityQueryPage {

    public static void main(String[] args) {

        // ---------- Chrome Setup ----------
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Javascript Executor
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
        	// ---------- Open Home Page ----------
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();
            Thread.sleep(2000);

            // ---------- Handle Privacy Popup ----------
            try {
                WebElement okBtn = driver.findElement(By.xpath("//button[normalize-space()='OK']"));
                js.executeScript("arguments[0].click();", okBtn);
                Thread.sleep(1000);
                System.out.println("Privacy popup closed");
            } catch (Exception e) {
                System.out.println("No popup present");
            }
            

            WebElement productsDropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[contains(@href,'/products')]")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    productsDropdown
            );

            Thread.sleep(1000);

            // move mouse to products dropdown
            org.openqa.selenium.interactions.Actions actions =
                    new org.openqa.selenium.interactions.Actions(driver);

            actions.moveToElement(productsDropdown).perform();

            System.out.println("Hovered on Products dropdown");

            Thread.sleep(3000);

            // ================= CLICK VULNERABILITY DATABASE =================

            WebElement vulnerabilityDatabase = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//a[contains(@href,'vulnerability-database') and .//span[contains(text(),'Vulnerability Database')]]"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    vulnerabilityDatabase
            );

            Thread.sleep(1000);

            js.executeScript(
                    "arguments[0].click();",
                    vulnerabilityDatabase
            );

            System.out.println("Clicked Vulnerability Database");


            // ================= SWITCH TO NEW TAB =================

            for (String windowHandle : driver.getWindowHandles()) {
                driver.switchTo().window(windowHandle);
            }

            System.out.println("Switched to Vulnerability Database tab");


            // ================= WAIT FOR PAGE LOAD =================

            wait.until(
                    ExpectedConditions.urlContains("vulnerability-database")
            );

            System.out.println("Successfully opened Vulnerability Database page");

            Thread.sleep(5000);
    

     /*    // ---------- STEP 1 : Open URL ----------
            driver.get("https://advisory.moole.ai/vulnerability-database");

            System.out.println("Opened Vulnerability Database Page");
            Thread.sleep(2000);*/

            // ---------- STEP 2 : Wait for npm button ----------
            WebElement npmButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[@aria-label='npm']")
                    )
            );

            // ---------- STEP 3 : Click npm ----------
            js.executeScript("arguments[0].click();", npmButton);

            System.out.println("Clicked npm button");

            // ---------- STEP 4 : Wait for navigation ----------
            wait.until(ExpectedConditions.urlContains("/query/npm"));

            System.out.println("Successfully navigated to npm query page");

            Thread.sleep(5000);

          // ---------- Get filters ----------
            List<WebElement> filters = driver.findElements(By.xpath("//a[@role='radio']"));

            System.out.println("Total filters: " + filters.size());

            // ---------- Loop filters ----------
            for (int i = 0; i < filters.size(); i++) {

                filters = driver.findElements(By.xpath("//a[@role='radio']"));

                WebElement filter = filters.get(i);

                String name = filter.getText().trim();
                if (name.isEmpty()) name = "Filter " + i;

                System.out.println("Clicking: " + name);

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", filter);
                Thread.sleep(800);

                js.executeScript("arguments[0].click();", filter);

                Thread.sleep(2000);

                // ---------- AFTER clicking debian ----------
                if (name.equalsIgnoreCase("debian")) {

                    System.out.println("Debian clicked → now clicking ALL");

                    WebElement allBtn = wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    By.xpath("//a[@aria-label='Filter by All']")
                            )
                    );

                    js.executeScript("arguments[0].scrollIntoView({block:'center'});", allBtn);
                    Thread.sleep(800);

                    js.executeScript("arguments[0].click();", allBtn);

                    Thread.sleep(3000);

                    System.out.println("Clicked ALL after Debian");

                    break; // stop loop after All
                }

                // small scroll for visibility
                js.executeScript("window.scrollBy(0, 200);");
                Thread.sleep(1000);
            }
             
            
            
            // ================= PAGINATION =================

            // NEXT 3 TIMES (desktop only)
            for (int i = 0; i < 3; i++) {

                WebElement nextBtn = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'md:flex')]//a[@aria-label='Next page']")
                        )
                );

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", nextBtn);
                Thread.sleep(800);

                js.executeScript("arguments[0].click();", nextBtn);

                System.out.println("Clicked NEXT: " + (i + 1));
                Thread.sleep(3000);
            }

            // PREVIOUS 2 TIMES
            for (int i = 0; i < 3; i++) {

                WebElement prevBtn = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'md:flex')]//a[@aria-label='Previous page']")
                        )
                );

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", prevBtn);
                Thread.sleep(800);

                js.executeScript("arguments[0].click();", prevBtn);

                System.out.println("Clicked PREVIOUS: " + (i + 1));
                Thread.sleep(3000);
            }
            
         // ================= CLICK CVE =================
            WebElement cve = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//span[contains(text(),'CVE-2026-2518')]")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", cve);
            Thread.sleep(800);

            js.executeScript("arguments[0].click();", cve);

            System.out.println("Clicked CVE-2026-2518");

            // ================= WAIT FOR DETAIL PAGE =================
            Thread.sleep(3000);

            // ================= SCROLL DETAIL PAGE =================
            for (int i = 0; i < 8; i++) {
                js.executeScript("window.scrollBy(0, 300);");
                Thread.sleep(500);
            }

            System.out.println("Scrolled CVE detail page");
            

            // ================= GO BACK =================
            driver.navigate().back();

            wait.until(ExpectedConditions.urlContains("/vulnerability-database/query"));

            Thread.sleep(3000);

            System.out.println("Returned to query page");
            
            
         // ================= SEARCH FUNCTIONALITY =================

            System.out.println("Starting Search Validation");

            // wait for search bar
            WebElement searchBar = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@id='searchQuery']")
                    )
            );

            // scroll to search bar
            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    searchBar
            );

            Thread.sleep(1500);
            // click search field
            searchBar.click();

            Thread.sleep(1000);

            // clear existing text
            searchBar.clear();

            Thread.sleep(1000);

            // enter text
            searchBar.sendKeys("database");

            System.out.println("Entered search keyword: database");

            Thread.sleep(2000);

            // PRESS ENTER
            searchBar.sendKeys(org.openqa.selenium.Keys.ENTER);

            System.out.println("Pressed ENTER");

            Thread.sleep(5000);

            // optional scroll to see results
            js.executeScript("window.scrollBy(0,300)");

            Thread.sleep(3000);

            System.out.println("Search validation completed");
        
         // ================= CLEAR SEARCH BUTTON =================

            WebElement clearSearchBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Clear search']")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    clearSearchBtn
            );

            Thread.sleep(1500);

            // click clear button
            js.executeScript(
                    "arguments[0].click();",
                    clearSearchBtn
            );

            System.out.println("Clicked Clear Search Button");

            Thread.sleep(3000);
         

            // ================= GO BACK =================
            driver.navigate().back();

            wait.until(ExpectedConditions.urlContains("/vulnerability-database/query"));

            Thread.sleep(3000);

            System.out.println("Returned to query page");
            
         // ================= URL PATH VALIDATION =================

            System.out.println("Opening URL with database path");

            // open URL with database added at end
            driver.navigate().to(
                "https://advisory.moole.ai/vulnerability-database/query/database"
            );

            System.out.println(
                "Navigated to: https://advisory.moole.ai/vulnerability-database/query/database"
            );

            // wait so tester can see URL clearly
            Thread.sleep(8000);

            // optional validation using current URL
            String currentPageUrl = driver.getCurrentUrl();

            if(currentPageUrl.contains("/database")) {

                System.out.println("Database path added successfully in URL");

            } else {

                System.out.println("Database path NOT added in URL");
            }

            Thread.sleep(5000);
            
         // ================= NAVIGATE BACK TO QUERY PAGE =================

            driver.navigate().to(
                "https://advisory.moole.ai/vulnerability-database/query/npm"
            );

            wait.until(
                    ExpectedConditions.urlContains("/query/npm")
            );

            Thread.sleep(3000);

            System.out.println("Returned to Query Page");              

            // ================= SEARCH CVE ID =================

            WebElement searchBox = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@id='searchQuery']")
                    )
            );

            // scroll to search field
            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    searchBox
            );

            Thread.sleep(1500);

            // click search field
            searchBox.click();

            Thread.sleep(1000);

            // clear existing text
            searchBox.clear();

            Thread.sleep(1000);

            // enter CVE ID
            searchBox.sendKeys("CVE-2026-22810");

            System.out.println("Entered CVE ID: CVE-2026-22810");

            Thread.sleep(2000);

            // press ENTER
            searchBox.sendKeys(org.openqa.selenium.Keys.ENTER);

            System.out.println("Pressed ENTER for CVE search");

            Thread.sleep(5000);
            // scroll down
            for (int i = 0; i < 5; i++) {

                js.executeScript("window.scrollBy(0,400)");

                Thread.sleep(1000);
            }

            // ================= VALIDATE RESULT =================

            List<WebElement> cveResults = driver.findElements(
                    By.xpath("//*[contains(text(),'CVE-2026-22810')]")
            );

            if (cveResults.size() > 0) {

                System.out.println("CVE-2026-22810 FOUND successfully");

            } else {

                System.out.println("CVE-2026-22810 NOT FOUND");

            }

            Thread.sleep(3000);
            
         // ================= CLEAR SEARCH BUTTON =================

            WebElement clearBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Clear search']")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    clearBtn
            );

            Thread.sleep(1000);

            // click clear button
            js.executeScript(
                    "arguments[0].click();",
                    clearBtn
            );

            System.out.println("Search bar cleared successfully");

            Thread.sleep(3000);
            
            

            // ================= SEARCH SECOND CVE =================

            WebElement secondSearchBox = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@id='searchQuery']")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    secondSearchBox
            );

            Thread.sleep(1000);

            secondSearchBox.click();

            Thread.sleep(1000);

            // enter second CVE ID
            secondSearchBox.sendKeys("CVE-2026-22811");

            System.out.println("Entered CVE ID: CVE-2026-22811");

            Thread.sleep(2000);

            // press ENTER
            secondSearchBox.sendKeys(org.openqa.selenium.Keys.ENTER);

            System.out.println("Pressed ENTER for second CVE search");

            Thread.sleep(5000);
            // scroll down
            for (int i = 0; i < 5; i++) {

                js.executeScript("window.scrollBy(0,400)");

                Thread.sleep(1000);
            }

            // ================= VALIDATE SECOND RESULT =================

            List<WebElement> secondResults = driver.findElements(
                    By.xpath("//*[contains(text(),'CVE-2026-22811')]")
            );

            if (secondResults.size() > 0) {

                System.out.println("CVE-2026-22811 FOUND successfully");

            } else {

                System.out.println("CVE-2026-22811 NOT FOUND");

            }

            Thread.sleep(3000);

         // ================= NAVIGATE TO QUERY PAGE =================


            driver.navigate().to(
                    "https://advisory.moole.ai/vulnerability-database/query"
                );

            Thread.sleep(5000);

            System.out.println("Opened Vulnerability Database Page");

            // ================= SCROLL LITTLE DOWN =================

            js.executeScript("window.scrollBy(0,400)");

            Thread.sleep(2000);

            // ================= LOCATE MAVEN BUTTON =================

            WebElement mavenBtn = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//a[contains(@href,'/query/maven')]")
                    )
            );

            // scroll to maven
            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    mavenBtn
            );

            Thread.sleep(2000);

            // click using JS
            js.executeScript(
                    "arguments[0].click();",
                    mavenBtn
            );

            System.out.println("Clicked Maven Button");

            // ================= WAIT FOR MAVEN PAGE =================

            wait.until(
                    ExpectedConditions.urlContains("/query/maven")
            );

            Thread.sleep(4000);

            System.out.println("Navigated to Maven Query Page");
            // ================= SEARCH CVE IN MAVEN =================

            WebElement mavenSearchBar = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@id='searchQuery']")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    mavenSearchBar
            );

            Thread.sleep(1000);

            mavenSearchBar.click();

            Thread.sleep(1000);

            // clear if anything exists
            mavenSearchBar.clear();

            Thread.sleep(1000);

            // enter CVE
            mavenSearchBar.sendKeys("CVE-2026-22810");

            System.out.println("Entered CVE ID in Maven Search: CVE-2026-22810");

            Thread.sleep(2000);

            // press ENTER
            mavenSearchBar.sendKeys(org.openqa.selenium.Keys.ENTER);

            System.out.println("Pressed ENTER for Maven CVE Search");

            Thread.sleep(5000);

            // ================= VALIDATE RESULT =================

            List<WebElement> mavenResults = driver.findElements(
                    By.xpath("//*[contains(text(),'CVE-2026-22810')]")
            );

            if (mavenResults.size() > 0) {

                System.out.println("CVE-2026-22810 FOUND in Maven");

            } else {

                System.out.println("CVE-2026-22810 NOT FOUND in Maven");

            }

            Thread.sleep(3000);

         // ================= SCROLL PAGE AFTER SEARCH =================

         // scroll down
         for (int i = 0; i < 5; i++) {

             js.executeScript("window.scrollBy(0,400)");

             Thread.sleep(1000);
         }
         
         // ================= NAVIGATE TO HOME PAGE =================

         driver.navigate().to(
                 "https://advisory.moole.ai/vulnerability-database/query"
             );


         Thread.sleep(5000);

         System.out.println("Navigated to Home Page");
         

            // ================= CLOSE BROWSER =================

            driver.quit();

            System.out.println("Browser closed successfully");
            
            

            System.out.println("Automation completed successfully");
        } catch (Exception e) {

            System.out.println("ERROR OCCURRED:");

            e.printStackTrace();

        } finally {

            driver.quit();

            System.out.println("Browser closed");
        }
    }
} 