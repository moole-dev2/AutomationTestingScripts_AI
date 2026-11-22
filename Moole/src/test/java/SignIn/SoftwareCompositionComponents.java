
package SignIn;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Utils.ConfigReader;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import org.testng.annotations.Test;

public class SoftwareCompositionComponents {

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

        	// --- Step 1: Open Moole.ai homepage in Tab 1 ---
        	driver.get(ConfigReader.getProperty("baseUrl"));
        	driver.manage().window().maximize();
        	waitBetweenActions(driver, 2);

        	String homeWindow = driver.getWindowHandle();

        	System.out.println("Moole.ai homepage opened in Tab 1");

        	// --- Step 2: Open Sign In page in Tab 2 ---
        	driver.switchTo().newWindow(WindowType.TAB);
        	driver.get("https://moole.ai/auth/signin");
        	waitBetweenActions(driver, 2);

        	String signInWindow = driver.getWindowHandle();

        	System.out.println("Sign In page opened in Tab 2");

        	// --- Step 3: Enter Email ---
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
        	
        	waitBetweenActions(driver, 2);

        	WebElement otpBox = wait.until(
        	    ExpectedConditions.elementToBeClickable(
        	        By.xpath("//input[@maxlength='1']")
        	    )
        	);

        	otpBox.click();

        	// --- Step 4: Wait for OTP manually ---
        	driver.switchTo().window(signInWindow);

        	System.out.println("Please enter your OTP in Tab 2 (Sign In), then press Enter here...");

        	@SuppressWarnings("resource")
        	Scanner scanner = new Scanner(System.in);
        	scanner.nextLine();

        	waitBetweenActions(driver, 2);

        	// Make sure automation continues in Tab 2
        	driver.switchTo().window(signInWindow);

            // ================= CLICK CONTAINER IMAGES =================
            WebElement containerImages = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[@href='/app/project/container-security' and .//span[normalize-space()='Container Images']]")));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", containerImages);
            waitBetweenActions(driver, 2);

            js.executeScript("arguments[0].click();", containerImages);
            waitBetweenActions(driver, 2);

            System.out.println("Container Images clicked");

         // Click Actions for 1.2
            By actionsButtonPath = By.xpath("//button[@aria-label='Actions for 1.2']");

            WebElement actionsButton = wait.until(
                ExpectedConditions.elementToBeClickable(actionsButtonPath)
            );

            actionsButton.click();

            System.out.println("Actions for 1.2 clicked successfully");
            waitBetweenActions(driver, 2);

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
    		
    		// Click Components tab
    		By componentsTabPath = By.xpath("//button[normalize-space()='Components']");

    		WebElement componentsTab = wait.until(
    		    ExpectedConditions.elementToBeClickable(componentsTabPath)
    		);

    		componentsTab.click();

    		System.out.println("Components tab clicked successfully");
    		waitBetweenActions(driver, 2); 
    		
    		// ================= DEBIAN - ADDUSER =================

    		// Expand Debian adduser row
    		By adduserExpandPath = By.xpath(
    		    "//tr[.//div[@aria-label='adduser']]//button[@aria-label='Expand row']"
    		);

    		WebElement adduserExpand = wait.until(
    		    ExpectedConditions.elementToBeClickable(adduserExpandPath)
    		);

    		adduserExpand.click();
    		System.out.println("Debian adduser row expanded");
    		waitBetweenActions(driver, 2);

    		// Collapse Debian adduser row
    		By adduserCollapsePath = By.xpath(
    		    "//tr[.//div[@aria-label='adduser']]//button[@aria-label='Collapse row']"
    		);

    		WebElement adduserCollapse = wait.until(
    		    ExpectedConditions.elementToBeClickable(adduserCollapsePath)
    		);

    		adduserCollapse.click();
    		System.out.println("Debian adduser row collapsed");
    		waitBetweenActions(driver, 2);


    		// ================= MAVEN - APP =================

    		// Expand Maven app row
    		By mavenAppExpandPath = By.xpath(
    		    "//tr[.//div[@aria-label='app']]//button[@aria-label='Expand row']"
    		);

    		WebElement mavenAppExpand = wait.until(
    		    ExpectedConditions.elementToBeClickable(mavenAppExpandPath)
    		);

    		mavenAppExpand.click();
    		System.out.println("Maven app row expanded");
    		waitBetweenActions(driver, 2);


    		// Click globe link inside expanded Maven app row
    		By globeLinkPath = By.xpath(
    		    "//tr[.//div[@aria-label='app']]" +
    		    "/following-sibling::tr[1]//a[@target='_blank']"
    		);

    		WebElement globeLink = wait.until(
    		    ExpectedConditions.elementToBeClickable(globeLinkPath)
    		);

    		// Store original tab
    		String originalWindow = driver.getWindowHandle();

    		// Click globe link
    		globeLink.click();
    		System.out.println("Globe link clicked");
    		waitBetweenActions(driver, 3);

    		// Switch to newly opened tab
    		for (String window : driver.getWindowHandles()) {
    		    if (!window.equals(originalWindow)) {
    		        driver.switchTo().window(window);
    		        break;
    		    }
    		}

    		System.out.println("Switched to newly opened tab");
    		waitBetweenActions(driver, 3);

    		// Close new tab and return to original tab
    		driver.close();
    		driver.switchTo().window(originalWindow);

    		System.out.println("Returned to original tab");
    		waitBetweenActions(driver, 2);


    		// Collapse Maven app row
    		By mavenAppCollapsePath = By.xpath(
    		    "//tr[.//div[@aria-label='app']]//button[@aria-label='Collapse row']"
    		);

    		WebElement mavenAppCollapse = wait.until(
    		    ExpectedConditions.elementToBeClickable(mavenAppCollapsePath)
    		);

    		mavenAppCollapse.click();

    		System.out.println("Maven app row collapsed");
    		waitBetweenActions(driver, 2);
    		
    		// ================= SCROLL DOWN TO INIT-SYSTEM-HELPERS =================

    		By initSystemRowPath = By.xpath(
    		    "//tr[.//div[@aria-label='init-system-helpers']]"
    		);

    		WebElement initSystemRow = wait.until(
    		    ExpectedConditions.presenceOfElementLocated(initSystemRowPath)
    		);

    		((JavascriptExecutor) driver).executeScript(
    		    "arguments[0].scrollIntoView({block:'center'});",
    		    initSystemRow
    		);

    		waitBetweenActions(driver, 2);


    		// ================= EXPAND INIT-SYSTEM-HELPERS =================

    		By initSystemExpandPath = By.xpath(
    		    "//tr[.//div[@aria-label='init-system-helpers']]" +
    		    "//button[@aria-label='Expand row']"
    		);

    		WebElement initSystemExpand = wait.until(
    		    ExpectedConditions.elementToBeClickable(initSystemExpandPath)
    		);

    		initSystemExpand.click();
    		System.out.println("init-system-helpers expanded");
    		waitBetweenActions(driver, 2);


    		// ================= COLLAPSE INIT-SYSTEM-HELPERS =================

    		By initSystemCollapsePath = By.xpath(
    		    "//tr[.//div[@aria-label='init-system-helpers']]" +
    		    "//button[@aria-label='Collapse row']"
    		);

    		WebElement initSystemCollapse = wait.until(
    		    ExpectedConditions.elementToBeClickable(initSystemCollapsePath)
    		);

    		initSystemCollapse.click();
    		System.out.println("init-system-helpers collapsed");
    		waitBetweenActions(driver, 2);


    		// ================= PAGINATION - FORWARD TO LAST PAGE =================

    		// ========== FORWARD PAGINATION ==========
    		System.out.println("========== FORWARD PAGINATION ==========");


    		By nextPageLocator = By.xpath(
    		    "//button[@aria-label='Next page' and not(@disabled)]"
    		);

    		By previousPageLocator = By.xpath(
    		    "//button[@aria-label='Previous page' and not(@disabled)]"
    		);


    		// Navigate forward until the Next button is no longer available or visible
    		while (true) {

    		    List<WebElement> nextButtons = driver.findElements(nextPageLocator);

    		    WebElement visibleNextButton = null;

    		    for (WebElement button : nextButtons) {
    		        if (button.isDisplayed() && button.isEnabled()) {
    		            visibleNextButton = button;
    		            break;
    		        }
    		    }

    		    if (visibleNextButton == null) {
    		        System.out.println("Reached the last page.");
    		        break;
    		    }

    		    js.executeScript(
    		        "arguments[0].scrollIntoView({block:'center'});",
    		        visibleNextButton
    		    );

    		    String oldFirstRow = driver.findElement(
    		        By.xpath("(//tbody/tr[td])[1]")
    		    ).getText();

    		    js.executeScript("arguments[0].click();", visibleNextButton);

    		    wait.until(d -> {
    		        List<WebElement> rows = d.findElements(
    		            By.xpath("(//tbody/tr[td])[1]")
    		        );

    		        return !rows.isEmpty() && !rows.get(0).getText().equals(oldFirstRow);
    		    });

    		    System.out.println("Moved to next page successfully.");

    		    waitBetweenActions(driver, 2);
    		}


    		// ========== BACKWARD PAGINATION ==========
    		System.out.println("========== BACKWARD PAGINATION ==========");

    		while (true) {

    		    List<WebElement> previousButtons = driver.findElements(
    		        previousPageLocator
    		    );

    		    WebElement visiblePreviousButton = null;

    		    for (WebElement button : previousButtons) {
    		        if (button.isDisplayed() && button.isEnabled()) {
    		            visiblePreviousButton = button;
    		            break;
    		        }
    		    }

    		    if (visiblePreviousButton == null) {
    		        System.out.println("Reached the first page.");
    		        break;
    		    }

    		    js.executeScript(
    		        "arguments[0].scrollIntoView({block:'center'});",
    		        visiblePreviousButton
    		    );

    		    String oldFirstRow = driver.findElement(
    		        By.xpath("(//tbody/tr[td])[1]")
    		    ).getText();

    		    js.executeScript("arguments[0].click();", visiblePreviousButton);

    		    wait.until(d -> {
    		        List<WebElement> rows = d.findElements(
    		            By.xpath("(//tbody/tr[td])[1]")
    		        );

    		        return !rows.isEmpty() && !rows.get(0).getText().equals(oldFirstRow);
    		    });

    		    System.out.println("Moved to previous page successfully.");

    		    waitBetweenActions(driver, 2);
    		}

    		System.out.println("Pagination completed successfully.");

    		// ================= SCROLL BACK TO TOP =================

    		((JavascriptExecutor) driver).executeScript(
    		    "window.scrollTo({top: 0, behavior: 'smooth'});"
    		);

    		waitBetweenActions(driver, 3);

    		System.out.println("Scrolled back to the top");
    		
    		// ========== PACKAGE SORTING ==========
    		System.out.println("========== PACKAGE SORTING ==========");

    		By packageHeader = By.xpath(
    		    "//thead//th[.//button[normalize-space()='Package']]"
    		);

    		By packageButton = By.xpath(
    		    "//thead//th[.//button[normalize-space()='Package']]/button"
    		);

    		// Ensure Package is ascending
    		String packageSort = driver.findElement(packageHeader).getAttribute("aria-sort");

    		if (!"ascending".equals(packageSort)) {
    		    wait.until(ExpectedConditions.elementToBeClickable(packageButton)).click();

    		    wait.until(ExpectedConditions.attributeToBe(
    		        packageHeader, "aria-sort", "ascending"
    		    ));
    		}

    		System.out.println("Package sorted in ASCENDING order.");

    		waitBetweenActions(driver, 2);

    		// Click Package for descending
    		wait.until(ExpectedConditions.elementToBeClickable(packageButton)).click();

    		wait.until(ExpectedConditions.attributeToBe(
    		    packageHeader, "aria-sort", "descending"
    		));

    		System.out.println("Package sorted in DESCENDING order.");

    		waitBetweenActions(driver, 2);


    		// ========== VERSION SORTING ==========
    		System.out.println("========== VERSION SORTING ==========");

    		By versionHeader = By.xpath(
    		    "//thead//th[.//button[normalize-space()='Version']]"
    		);

    		By versionButton = By.xpath(
    		    "//thead//th[.//button[normalize-space()='Version']]/button"
    		);

    		// Ensure Version is ascending
    		String versionSort = driver.findElement(versionHeader).getAttribute("aria-sort");

    		if (!"ascending".equals(versionSort)) {
    		    wait.until(ExpectedConditions.elementToBeClickable(versionButton)).click();

    		    wait.until(ExpectedConditions.attributeToBe(
    		        versionHeader, "aria-sort", "ascending"
    		    ));
    		}

    		System.out.println("Version sorted in ASCENDING order.");

    		waitBetweenActions(driver, 2);

    		// Click Version for descending
    		wait.until(ExpectedConditions.elementToBeClickable(versionButton)).click();

    		wait.until(ExpectedConditions.attributeToBe(
    		    versionHeader, "aria-sort", "descending"
    		));

    		System.out.println("Version sorted in DESCENDING order.");

    		waitBetweenActions(driver, 2);

    		System.out.println("Package and Version sorting completed successfully.");
    		
    		// Click Type dropdown
    		WebElement typeButton = wait.until(
    		        ExpectedConditions.elementToBeClickable(
    		                By.xpath("//button[@aria-label='Type']")
    		        )
    		);
    		typeButton.click();

    		waitBetweenActions(driver, 2);

    		// Check and uncheck library
    		WebElement libraryOption = wait.until(
    		        ExpectedConditions.elementToBeClickable(
    		                By.xpath("//div[contains(@class,'cursor-pointer') and .//span[normalize-space()='library']]")
    		        )
    		);

    		// Check library
    		libraryOption.click();
    		waitBetweenActions(driver, 2);

    		// Uncheck library
    		libraryOption.click();
    		waitBetweenActions(driver, 2);

    		// Check and uncheck operating-system
    		WebElement operatingSystemOption = wait.until(
    		        ExpectedConditions.elementToBeClickable(
    		                By.xpath("//div[contains(@class,'cursor-pointer') and .//span[normalize-space()='operating-system']]")
    		        )
    		);

    		// Check operating-system
    		operatingSystemOption.click();
    		waitBetweenActions(driver, 2);

    		// Uncheck operating-system
    		operatingSystemOption.click();
    		waitBetweenActions(driver, 2);

    		// Close Type dropdown
    		typeButton = wait.until(
    		        ExpectedConditions.elementToBeClickable(
    		                By.xpath("//button[@aria-label='Type']")
    		        )
    		);
    		typeButton.click();

    		waitBetweenActions(driver, 2);
    		
    		// Click Ecosystem dropdown
    		WebElement ecosystemButton = wait.until(
    		        ExpectedConditions.elementToBeClickable(
    		                By.xpath("//button[@aria-label='Ecosystem']")
    		        )
    		);
    		ecosystemButton.click();

    		waitBetweenActions(driver, 2);

    		// Select debian
    		WebElement debianOption = wait.until(
    		        ExpectedConditions.elementToBeClickable(
    		                By.xpath("//div[contains(@class,'cursor-pointer') and .//span[normalize-space()='debian']]")
    		        )
    		);
    		debianOption.click();

    		waitBetweenActions(driver, 2);

    		// Select maven
    		WebElement mavenOption = wait.until(
    		        ExpectedConditions.elementToBeClickable(
    		                By.xpath("//div[contains(@class,'cursor-pointer') and .//span[normalize-space()='maven']]")
    		        )
    		);
    		mavenOption.click();

    		waitBetweenActions(driver, 2);

    		// Unselect debian
    		debianOption.click();

    		waitBetweenActions(driver, 2);

    		// Unselect maven
    		mavenOption.click();

    		waitBetweenActions(driver, 2);

    		// Close Ecosystem dropdown
    		ecosystemButton = wait.until(
    		        ExpectedConditions.elementToBeClickable(
    		                By.xpath("//button[@aria-label='Ecosystem']")
    		        )
    		);
    		ecosystemButton.click();

    		waitBetweenActions(driver, 2);
    		
    		// Locate package search input
    		WebElement searchInput = wait.until(
    		        ExpectedConditions.elementToBeClickable(
    		                By.xpath("//input[@placeholder='Search packages, purl, paths…']")
    		        )
    		);

    		// Enter **adduser
    		searchInput.sendKeys("adduser");
    		waitBetweenActions(driver, 2);

    		// Backspace the previous input
    		searchInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    		searchInput.sendKeys(Keys.BACK_SPACE);
    		waitBetweenActions(driver, 2);

    		// Enter addiser
    		searchInput.sendKeys("addiser");
    		waitBetweenActions(driver, 2);

    		// Clear using Backspace
    		searchInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    		searchInput.sendKeys(Keys.BACK_SPACE);
    		waitBetweenActions(driver, 2);

    		// Enter pkg:maven/app/app (with a leading tab)
    		searchInput.sendKeys("pkg:maven/app/app");
    		waitBetweenActions(driver, 2);

    		// Clear using Backspace
    		searchInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    		searchInput.sendKeys(Keys.BACK_SPACE);
    		waitBetweenActions(driver, 2);

    		// Enter pvg:maven/app/app (with two leading tabs)
    		searchInput.sendKeys("pvg:maven/app/app");
    		waitBetweenActions(driver, 2);

    		// Backspace the final input
    		searchInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    		searchInput.sendKeys(Keys.BACK_SPACE);
    		waitBetweenActions(driver, 2);
    		
    		// Click Raw tab
    		WebElement rawButton = wait.until(
    		        ExpectedConditions.elementToBeClickable(
    		                By.xpath("//button[normalize-space()='Raw']")
    		        )
    		);
    		rawButton.click();

    		waitBetweenActions(driver, 2);

    		// Click Download
    		WebElement downloadButton = wait.until(
    		        ExpectedConditions.elementToBeClickable(
    		                By.xpath("//button[.//span[normalize-space()='Download']]")
    		        )
    		);
    		downloadButton.click();

    		waitBetweenActions(driver, 3);

    		// Click Export Report as PDF
    		WebElement exportReportButton = wait.until(
    		        ExpectedConditions.elementToBeClickable(
    		                By.xpath("//button[@aria-label='Export container report as PDF']")
    		        )
    		);
    		exportReportButton.click();

    		waitBetweenActions(driver, 3);

    		
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        } finally {
            driver.quit();
        }

    } 

}