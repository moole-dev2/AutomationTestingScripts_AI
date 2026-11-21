
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
import Utils.ConfigReader;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import org.testng.annotations.Test;

public class ContainerSecurityViewReport {

    // Pause between actions without Thread.sleep()
    private void waitBetweenActions(WebDriver driver, int seconds) {
        long endTime = System.currentTimeMillis() + (seconds * 1000L);

        new WebDriverWait(driver, Duration.ofSeconds(seconds + 2))
                .until(d -> System.currentTimeMillis() >= endTime);
    }

    @Test
    public void IntegrationGitHubTest() throws InterruptedException {

        // --- ChromeOptions to use existing profile ---
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=C:\\Users\\psiri\\AppData\\Local\\Google\\User Data");
        options.addArguments("profile-directory=Profile 1");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {

            // --- Step 1: Open Moole.ai and click Sign In ---
            driver.get(ConfigReader.getProperty("baseUrl"));
            driver.manage().window().maximize();
            waitBetweenActions(driver, 2);

            driver.get("https://moole.ai/auth/signin");
            waitBetweenActions(driver, 2);

            // --- Step 2: Enter Email ---
            WebElement emailField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@type='email']")));

            emailField.sendKeys("moole.dev.2@gmail.com");
            waitBetweenActions(driver, 2);

            WebElement signIn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@data-tour='signup-submit']")));

            signIn.click();
            waitBetweenActions(driver, 2);

            System.out.println("Sign in button clicked");

            // --- Step 3: Wait for OTP manually ---
            System.out.println("Please enter your OTP manually in the browser, then press Enter here...");

            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();

            waitBetweenActions(driver, 2);

            // --- Step 4: Navigate directly to Integrations page ---
            driver.get("https://moole.ai/app/settings/project/integrations");
            waitBetweenActions(driver, 2);

            // ================= CLICK CONTAINER IMAGES =================
            WebElement containerImages = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[@href='/app/project/container-security' and .//span[normalize-space()='Container Images']]")));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", containerImages);
            waitBetweenActions(driver, 2);

            js.executeScript("arguments[0].click();", containerImages);
            waitBetweenActions(driver, 2);

            System.out.println("Container Images clicked");

            // ================= CLICK ACTIONS FOR TAG 1.1 =================
            By actionsForTag11 = By.xpath("//button[@aria-label='Actions for 1.1']");

            WebElement actionsButton = wait.until(
                    ExpectedConditions.elementToBeClickable(actionsForTag11));

            actionsButton.click();
            waitBetweenActions(driver, 2);

            System.out.println("Clicked Actions for 1.1");

            // ================= CLICK VIEW REPORT =================
            WebElement viewReport = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[@role='menuitem' and contains(@href,'/app/project/report/container-security') and .//span[normalize-space()='View Report']]")));

            viewReport.click();
            waitBetweenActions(driver, 2);

            System.out.println("View Report clicked");

            // ================= WAIT FOR REPORT PAGE =================
            wait.until(ExpectedConditions.urlContains("/app/project/report/container-security"));

            wait.until(ExpectedConditions.jsReturnsValue(
                    "return document.readyState === 'complete';"));

            waitBetweenActions(driver, 2);

            System.out.println("Report page loaded");

           // ================= SCROLL DOWN =================
            js.executeScript("window.scrollBy({top: 200, behavior: 'smooth'});");

            wait.until(ExpectedConditions.jsReturnsValue(
                    "return window.scrollY > 0;"));

            waitBetweenActions(driver, 2);

            js.executeScript("window.scrollBy({top: 200, behavior: 'smooth'});");
            waitBetweenActions(driver, 2);

            System.out.println("Scrolled down");

           // ================= SCROLL UP =================
            js.executeScript("window.scrollBy({top: -200, behavior: 'smooth'});");
            waitBetweenActions(driver, 2);

            wait.until(ExpectedConditions.jsReturnsValue(
                    "return window.scrollY >= 0;"));

            js.executeScript("window.scrollBy({top: -200, behavior: 'smooth'});");
            waitBetweenActions(driver, 2);

            System.out.println("Scrolled up");

            // ================= CLICK ALL FOUR CVE TABS =================
            String[] tabNames = {
                    "Application CVEs",
                    "OS CVEs",
                    "License Policy",
                    "All CVEs"
            };

            for (String tabName : tabNames) {

                WebElement tab = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//div[contains(@class,'group') and .//p[normalize-space()='" + tabName + "']]")));

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", tab);
                waitBetweenActions(driver, 2);

                tab.click();
                waitBetweenActions(driver, 2);

                System.out.println(tabName + " clicked");

                wait.until(ExpectedConditions.attributeContains(
                        tab, "class", "from-button-gradient-1"));

                waitBetweenActions(driver, 2);
            }

            System.out.println("All four tabs clicked. Returned to All CVEs.");

            // ============================================
            // 1. FIX AVAILABLE - Select No, then unselect No
            // ============================================
            By fixAvailableDropdown = By.xpath("//button[@aria-label='Fix Available']");

            WebElement fixButton = wait.until(
                    ExpectedConditions.elementToBeClickable(fixAvailableDropdown));

            fixButton.click();
            waitBetweenActions(driver, 2);

            By noOption = By.xpath(
                    "//div[contains(@class,'cursor-pointer') and .//span[normalize-space()='No']]");

            WebElement noElement = wait.until(
                    ExpectedConditions.elementToBeClickable(noOption));

            noElement.click();
            waitBetweenActions(driver, 2);

            System.out.println("Fix Available - No selected");

            noElement = wait.until(
                    ExpectedConditions.elementToBeClickable(noOption));

            noElement.click();
            waitBetweenActions(driver, 2);

            System.out.println("Fix Available - No unselected");

            // ============================================
            // 2. DEPENDENCY TYPE - Check first, uncheck it,
            //    then check second checkbox
            // ============================================
            By dependencyDropdown = By.xpath("//button[@aria-label='Dependency Type']");

            WebElement dependencyButton = wait.until(
                    ExpectedConditions.elementToBeClickable(dependencyDropdown));

            dependencyButton.click();
            waitBetweenActions(driver, 2);

            By dependencyOptions = By.xpath(
                    "//input[@type='checkbox']/ancestor::div[contains(@class,'cursor-pointer')]");

            List<WebElement> dependencyList = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(dependencyOptions));

            if (dependencyList.size() >= 2) {

                dependencyList.get(0).click();
                waitBetweenActions(driver, 2);

                System.out.println("Dependency Type - First option checked");

                dependencyList = driver.findElements(dependencyOptions);
                dependencyList.get(0).click();
                waitBetweenActions(driver, 2);

                System.out.println("Dependency Type - First option unchecked");

                dependencyList = driver.findElements(dependencyOptions);
                dependencyList.get(1).click();
                waitBetweenActions(driver, 2);

                System.out.println("Dependency Type - Second option checked");

            } else {
                System.out.println("Dependency Type options not found");
            }

            // ============================================
            // 3. CLOSE DEPENDENCY TYPE DROPDOWN
            // ============================================
            dependencyButton = wait.until(
                    ExpectedConditions.elementToBeClickable(dependencyDropdown));

            dependencyButton.click();
            waitBetweenActions(driver, 2);

            // ============================================
            // 4. FILTER - Check and uncheck first checkbox
            // ============================================
            By filterDropdown = By.xpath("//button[@aria-label='Filter']");

            WebElement filterButton = wait.until(
                    ExpectedConditions.elementToBeClickable(filterDropdown));

            filterButton.click();
            waitBetweenActions(driver, 2);

            By filterOptions = By.xpath(
                    "//input[@type='checkbox']/ancestor::div[contains(@class,'cursor-pointer')]");

            List<WebElement> filterList = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(filterOptions));

            if (!filterList.isEmpty()) {

                filterList.get(0).click();
                waitBetweenActions(driver, 2);

                System.out.println("Filter - First option checked");

                filterList = driver.findElements(filterOptions);
                filterList.get(0).click();
                waitBetweenActions(driver, 2);

                System.out.println("Filter - First option unchecked");

            } else {
                System.out.println("Filter options not found");
            }

            // ============================================
            // 5. CLOSE FILTER DROPDOWN
            // ============================================
            filterButton = wait.until(
                    ExpectedConditions.elementToBeClickable(filterDropdown));

            filterButton.click();
            waitBetweenActions(driver, 2);

            // ============================================
            // 6. REMOVE FIX AVAILABLE SELECTION
            // ============================================
            fixButton = wait.until(
                    ExpectedConditions.elementToBeClickable(fixAvailableDropdown));

            fixButton.click();
            waitBetweenActions(driver, 2);

            noElement = wait.until(
                    ExpectedConditions.elementToBeClickable(noOption));

            noElement.click();
            waitBetweenActions(driver, 2);

            System.out.println("Fix Available selection removed");

            // ============================================
            // 7. REMOVE DEPENDENCY TYPE SELECTION
            // ============================================
            dependencyButton = wait.until(
                    ExpectedConditions.elementToBeClickable(dependencyDropdown));

            dependencyButton.click();
            waitBetweenActions(driver, 2);

            dependencyList = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(dependencyOptions));

            if (dependencyList.size() >= 2) {

                dependencyList.get(1).click();
                waitBetweenActions(driver, 2);

                System.out.println("Dependency Type selection removed");
            }

            // Close Dependency Type dropdown
            dependencyButton = wait.until(
                    ExpectedConditions.elementToBeClickable(dependencyDropdown));

            dependencyButton.click();
            waitBetweenActions(driver, 2);

            System.out.println("All filter operations completed");
            
         // ============================================
         // 6. REMOVE FIX AVAILABLE SELECTION
         // ============================================

         fixButton = wait.until(
                 ExpectedConditions.elementToBeClickable(fixAvailableDropdown));

         fixButton.click();
         waitBetweenActions(driver, 2);

         noElement = wait.until(
                 ExpectedConditions.elementToBeClickable(noOption));

         noElement.click();
         waitBetweenActions(driver, 2);

         System.out.println("Fix Available - No selection removed");

         // Close Fix Available dropdown
         fixButton = wait.until(
                 ExpectedConditions.elementToBeClickable(fixAvailableDropdown));

         fixButton.click();
         waitBetweenActions(driver, 2);
            
            
      // ============================================
      // 8. CLICK ANY VISIBLE MORE INFORMATION BUTTON
      // ============================================

            By moreInfoPath = By.xpath(
            	    "//button[.//span[normalize-space()='+'] and contains(normalize-space(.),'More Information')]"
            	);

            	List<WebElement> moreInfoButtons = wait.until(
            	    ExpectedConditions.presenceOfAllElementsLocatedBy(moreInfoPath)
            	);

            	WebElement moreInfo = null;

            	for (WebElement button : moreInfoButtons) {
            	    if (button.isDisplayed() && button.isEnabled()) {
            	        moreInfo = button;
            	        break;
            	    }
            	}

            	if (moreInfo != null) {

            	    wait.until(ExpectedConditions.elementToBeClickable(moreInfo)).click();
            	    System.out.println("More Information clicked");

            	    waitBetweenActions(driver, 2);

            	    // Click the same button again to collapse it
            	    wait.until(ExpectedConditions.elementToBeClickable(moreInfo)).click();
            	    System.out.println("More Information unclicked");
            	    



            	} else {
            	    System.out.println("No visible More Information button found");
            	}

            	// ============================================
            	// CLICK CVE SEARCH INPUT AND ENTER CVE ID
            	// ============================================

            	By searchCvePath = By.xpath(
            	    "//input[@placeholder='Search CVE ID or Package']"
            	);

            	WebElement searchInput = wait.until(
            	    ExpectedConditions.elementToBeClickable(searchCvePath)
            	);

            	searchInput.click();
            	waitBetweenActions(driver, 2);

            	// Enter CVE ID
            	searchInput.sendKeys("CVE-2021-45046");

            	System.out.println("CVE-2021-45046 entered in search input");

            	waitBetweenActions(driver, 3);

            	// ============================================
            	// BACKSPACE TO CLEAR THE INPUT
            	// ============================================

            	searchInput.sendKeys(Keys.CONTROL, "a");
            	searchInput.sendKeys(Keys.BACK_SPACE);

            	System.out.println("CVE ID cleared using Backspace");

            	waitBetweenActions(driver, 2);
            	
            	// ============================================
            	// CLICK SEARCH INPUT AGAIN AND ENTER SECOND CVE
            	// ============================================

            	searchInput = wait.until(
            	    ExpectedConditions.elementToBeClickable(searchCvePath)
            	);

            	searchInput.click();
            	waitBetweenActions(driver, 2);

            	searchInput.sendKeys("CVE-2021-45045");

            	System.out.println("CVE-2021-45045 entered in search input");

            	waitBetweenActions(driver, 3);
            	// ============================================
            	// BACKSPACE TO CLEAR THE INPUT
            	// ============================================

            	searchInput.sendKeys(Keys.CONTROL, "a");
            	searchInput.sendKeys(Keys.BACK_SPACE);

            	System.out.println("CVE ID cleared using Backspace");

            	waitBetweenActions(driver, 2);
            	
            	// ============================================
            	// 1. CLICK APPLICATION CVEs
            	// ============================================

            	By applicationCvesPath = By.xpath(
            	    "//div[contains(@class,'group') and .//p[normalize-space()='Application CVEs']]"
            	);

            	WebElement applicationCves = wait.until(
            	    ExpectedConditions.elementToBeClickable(applicationCvesPath)
            	);

            	applicationCves.click();
            	System.out.println("Application CVEs clicked");

            	waitBetweenActions(driver, 2);

            	// ============================================
            	// 2. CLICK OS CVEs
            	// ============================================

            	By osCvesPath = By.xpath(
            		    "//div[contains(@class,'group') and .//p[normalize-space()='OS CVEs']]"
            		);

            		WebElement osCves = wait.until(
            		    ExpectedConditions.elementToBeClickable(osCvesPath)
            		);

            		osCves.click();
            		System.out.println("OS CVEs clicked");
            		waitBetweenActions(driver, 3);

            	// ============================================
            	// 3. CLICK LICENSE POLICY
            	// ============================================

            	By licensePolicyPath = By.xpath(
            	    "//div[contains(@class,'group') and .//p[normalize-space()='License Policy']]"
            	);

            	WebElement licensePolicy = wait.until(
            	    ExpectedConditions.elementToBeClickable(licensePolicyPath)
            	);

            	licensePolicy.click();
            	System.out.println("License Policy clicked");

            	waitBetweenActions(driver, 2);

            	// ============================================
            	// 4. CLICK VIEW DEPENDENCIES
            	// ============================================

            	By viewDependenciesPath = By.xpath(
            	    "//button[.//span[normalize-space()='+'] and contains(normalize-space(.),'View dependencies')]"
            	);

            	WebElement viewDependencies = wait.until(
            	    ExpectedConditions.elementToBeClickable(viewDependenciesPath)
            	);

            	viewDependencies.click();
            	System.out.println("View dependencies clicked");

            	waitBetweenActions(driver, 2);


            	// ============================================
            	// 5. CLICK SAME BUTTON AGAIN
            	// ============================================

            	viewDependencies = wait.until(
            	    ExpectedConditions.elementToBeClickable(viewDependenciesPath)
            	);

            	viewDependencies.click();
            	System.out.println("View dependencies unclicked");

            	waitBetweenActions(driver, 2);
            	
            	// Search GPL-3.0-only
            	By licenseSearchPath = By.xpath(
            	    "//input[@placeholder='Search licenses or packages']"
            	);

            	WebElement licenseSearch = wait.until(
            	    ExpectedConditions.elementToBeClickable(licenseSearchPath)
            	);

            	licenseSearch.click();
            	licenseSearch.sendKeys("GPL-3.0-only");
            	waitBetweenActions(driver, 3);

            	// Clear using Backspace
            	licenseSearch.sendKeys(Keys.CONTROL, "a");
            	licenseSearch.sendKeys(Keys.BACK_SPACE);
            	waitBetweenActions(driver, 2);

            	// Search GPL-2.0-only
            	licenseSearch.click();
            	licenseSearch.sendKeys("GPL-1.0-only");
            	waitBetweenActions(driver, 3);

            	// Clear using Backspace
            	licenseSearch.sendKeys(Keys.CONTROL, "a");
            	licenseSearch.sendKeys(Keys.BACK_SPACE);
            	waitBetweenActions(driver, 2);

            	By allCvesPath = By.xpath(
            		    "//div[contains(@class,'group') and .//p[normalize-space()='All CVEs']]"
            		);

            		WebElement allCves = wait.until(
            		    ExpectedConditions.elementToBeClickable(allCvesPath)
            		);

            		allCves.click();
            		System.out.println("All CVEs clicked");
            		waitBetweenActions(driver, 3);
            		
            		
            		// 1. Click History
            		By historyPath = By.xpath(
            		    "//button[.//span[normalize-space()='History']]"
            		);

            		WebElement historyButton = wait.until(
            		    ExpectedConditions.elementToBeClickable(historyPath)
            		);

            		historyButton.click();
            		System.out.println("History clicked");
            		waitBetweenActions(driver, 3);


            		// 2. Click Software Composition
            		By softwareCompositionPath = By.xpath(
            		    "//button[.//span[normalize-space()='Software Composition']]"
            		);

            		WebElement softwareCompositionButton = wait.until(
            		    ExpectedConditions.elementToBeClickable(softwareCompositionPath)
            		);

            		softwareCompositionButton.click();
            		System.out.println("Software Composition clicked");
            		waitBetweenActions(driver, 3);


            		// 3. Click Export Report
            		By exportReportPath = By.xpath(
            		    "//button[@aria-label='Export container report as PDF']"
            		);

            		WebElement exportReportButton = wait.until(
            		    ExpectedConditions.elementToBeClickable(exportReportPath)
            		);

            		exportReportButton.click();
            		System.out.println("Export Report clicked");
            		waitBetweenActions(driver, 5);
            		
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }
}