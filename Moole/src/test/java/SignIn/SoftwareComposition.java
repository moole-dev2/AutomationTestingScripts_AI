
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

public class SoftwareComposition {

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
    		
    		// Click on JAVA accordion
    		// Click on JAVA accordion

    		WebElement javaAccordion = wait.until(
    		    ExpectedConditions.elementToBeClickable(
    		        By.xpath("//button[contains(@class,'w-full') and .//span[normalize-space()='JAVA']]")
    		    )
    		);

    		javaAccordion.click();

    		Thread.sleep(2000);

    		System.out.println("JAVA accordion clicked successfully");
    		
    		// Step 8: Click on CVE-2021-45046
    		String originalWindow2 = driver.getWindowHandle();

    		WebElement cveLink2 = driver.findElement(
    		    By.xpath("//a[@href='https://advisory.moole.ai/vulnerability-database/CVE-2021-45046']")
    		);
    		cveLink2.click();

    		Thread.sleep(3000);

    		// Step 9: Switch to newly opened tab
    		for (String windowHandle : driver.getWindowHandles()) {
    		    if (!windowHandle.equals(originalWindow2)) {
    		        driver.switchTo().window(windowHandle);
    		        break;
    		    }
    		}

    		// Step 10: Close the opened tab
    		driver.close();

    		// Step 11: Switch back to original tab
    		driver.switchTo().window(originalWindow2);

    		Thread.sleep(2000);

    		// Step 2: Click on +2 more / 7 CVEs button
    		WebElement moreCvesButton = driver.findElement(
    		    By.xpath("//button[contains(., '7 CVEs') or contains(., '+2 more')]")
    		);
    		moreCvesButton.click();

    		Thread.sleep(2000);

    		String originalWindow = driver.getWindowHandle();

    		// ================= CLICK ANY ONE CVE LINK =================

    		String originalWindow1 = driver.getWindowHandle();
    		int originalWindowCount = driver.getWindowHandles().size();

    		By cveLinksPath = By.xpath(
    		    "//div[contains(@class,'sm:flex') and contains(@class,'flex-col')]//a[contains(@href,'/vulnerability-database/CVE-')]"
    		);

    		WebElement cveLink = wait.until(d -> {
    		    List<WebElement> links = d.findElements(cveLinksPath);

    		    for (WebElement link : links) {
    		        if (link.isDisplayed() && link.isEnabled()) {
    		            return link;
    		        }
    		    }
    		    return null;
    		});

    		String selectedCVE = cveLink.getText();

    		cveLink.click();

    		System.out.println(selectedCVE + " clicked successfully");

    		// Wait for the new tab
    		wait.until(d -> d.getWindowHandles().size() > originalWindowCount);

    		// Switch to the new tab
    		for (String handle : driver.getWindowHandles()) {
    		    if (!handle.equals(originalWindow1)) {
    		        driver.switchTo().window(handle);
    		        break;
    		    }
    		}

    		System.out.println("New CVE tab opened successfully");

    		waitBetweenActions(driver, 2);

    		// Close the new tab
    		driver.close();

    		// Switch back to original tab
    		driver.switchTo().window(originalWindow1);

    		System.out.println("CVE tab closed successfully");
    		
    		// Step 7: Click on 7 CVEs button again
    		// Click on 7 CVEs / +2 more button

    		WebElement moreCvesButton1 = wait.until(
    		    ExpectedConditions.elementToBeClickable(
    		        By.xpath("//button[.//span[normalize-space()='7 CVEs' or normalize-space()='+2 more']]")
    		    )
    		);

    		moreCvesButton1.click();

    		waitBetweenActions(driver, 2);

    		System.out.println("7 CVEs / +2 more button clicked successfully");
    		
    		
    		WebElement javaAccordion1 = wait.until(
        		    ExpectedConditions.elementToBeClickable(
        		        By.xpath("//button[contains(@class,'w-full') and .//span[normalize-space()='JAVA']]")
        		    )
        		);

        		javaAccordion1.click();

        		Thread.sleep(2000);

        		System.out.println("JAVA accordion clicked successfully");
    		
    		
        		// ================= STEP 1: EXPAND ubuntu:18.04 =================

        		By ubuntuAccordionPath = By.xpath(
        		    "//button[.//span[normalize-space()='ubuntu:18.04']]"
        		);

        		WebElement ubuntuAccordion = wait.until(
        		    ExpectedConditions.elementToBeClickable(ubuntuAccordionPath)
        		);

        		ubuntuAccordion.click();
        		waitBetweenActions(driver, 2);

        		System.out.println("ubuntu:18.04 accordion expanded successfully");

        		// Step 1: Locate Avahi dependency
        		By avahiDependencyPath = By.xpath(
        		    "//div[contains(@class,'font-semibold')]" +
        		    "[.//div[normalize-space()='avahi/libavahi-common3']]"
        		);

        		// Step 2: Find the visible Avahi element and scroll to it
        		List<WebElement> avahiElements = driver.findElements(avahiDependencyPath);

        		WebElement avahiDependency = null;

        		for (WebElement element : avahiElements) {
        		    if (element.isDisplayed()) {
        		        avahiDependency = element;
        		        break;
        		    }
        		}

        		if (avahiDependency == null) {
        		    throw new RuntimeException("Avahi dependency not visible");
        		}

        		((JavascriptExecutor) driver).executeScript(
        		    "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
        		    avahiDependency
        		);

        		waitBetweenActions(driver, 2);

        		// Step 3: Expand Avahi
        		avahiDependency.click();

        		System.out.println("avahi/libavahi-common3 expanded successfully");

        		waitBetweenActions(driver, 3);


        		// Step 4: Find the visible Avahi element again
        		avahiElements = driver.findElements(avahiDependencyPath);
        		avahiDependency = null;

        		for (WebElement element : avahiElements) {
        		    if (element.isDisplayed()) {
        		        avahiDependency = element;
        		        break;
        		    }
        		}

        		if (avahiDependency == null) {
        		    throw new RuntimeException("Avahi dependency not visible after expansion");
        		}

        		// Step 5: Scroll to Avahi and click again to collapse
        		((JavascriptExecutor) driver).executeScript(
        		    "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
        		    avahiDependency
        		);

        		waitBetweenActions(driver, 2);

        		avahiDependency.click();

        		System.out.println("avahi/libavahi-common3 collapsed successfully");

        		waitBetweenActions(driver, 3);

        		// Scroll up to Ubuntu accordion
        		By ubuntuAccordionPath1 = By.xpath(
        		    "//button[.//span[normalize-space()='ubuntu:18.04']]"
        		);

        		WebElement ubuntuAccordion1 = wait.until(
        		    ExpectedConditions.visibilityOfElementLocated(ubuntuAccordionPath1)
        		);

        		((JavascriptExecutor) driver).executeScript(
        		    "window.scrollTo({top: 0, behavior: 'smooth'});"
        		);

        		waitBetweenActions(driver, 2);

        		// Scroll Ubuntu button into the center
        		((JavascriptExecutor) driver).executeScript(
        		    "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
        		    ubuntuAccordion1
        		);

        		waitBetweenActions(driver, 2);

        		// Collapse Ubuntu accordion
        		ubuntuAccordion1 = wait.until(
        		    ExpectedConditions.elementToBeClickable(ubuntuAccordionPath1)
        		);

        		ubuntuAccordion1.click();

        		System.out.println("ubuntu:18.04 accordion collapsed successfully");

        		waitBetweenActions(driver, 2);
        		
        		By packageFilterPath = By.xpath(
        			    "//input[@placeholder='Filter by package, version, or CVE']"
        			);

        			WebElement packageFilter = wait.until(
        			    ExpectedConditions.visibilityOfElementLocated(packageFilterPath)
        			);

        			// Search for java, then backspace character by character
        			packageFilter.sendKeys("java");
        			System.out.println("Entered: java");
        			waitBetweenActions(driver, 2);

        			for (int i = 0; i < "java".length(); i++) {
        			    packageFilter.sendKeys(Keys.BACK_SPACE);
        			    waitBetweenActions(driver, 1);
        			}

        			// Search for jj, then backspace character by character
        			packageFilter.sendKeys("jj");
        			System.out.println("Entered: jj");
        			waitBetweenActions(driver, 2);

        			for (int i = 0; i < "jj".length(); i++) {
        			    packageFilter.sendKeys(Keys.BACK_SPACE);
        			    waitBetweenActions(driver, 1);
        			}

        			System.out.println("jj search and backspace completed successfully.");
        			
        			// Search for ubuntu, then backspace character by character
        			packageFilter.sendKeys("ubuntu");
        			System.out.println("Entered: ubuntu");
        			waitBetweenActions(driver, 2);

        			for (int i = 0; i < "ubuntu".length(); i++) {
        			    packageFilter.sendKeys(Keys.BACK_SPACE);
        			    waitBetweenActions(driver, 1);
        			}

        			// Search for demo:cve-app, then backspace character by character
        			packageFilter.sendKeys("demo:cve-app");
        			System.out.println("Entered: demo:cve-app");
        			waitBetweenActions(driver, 2);

        			for (int i = 0; i < "demo:cve-app".length(); i++) {
        			    packageFilter.sendKeys(Keys.BACK_SPACE);
        			    waitBetweenActions(driver, 1);
        			}

        			// Search for det, then backspace character by character
        			packageFilter.sendKeys("det");
        			System.out.println("Entered: det");
        			waitBetweenActions(driver, 2);

        			for (int i = 0; i < "det".length(); i++) {
        			    packageFilter.sendKeys(Keys.BACK_SPACE);
        			    waitBetweenActions(driver, 1);
        			}

        			System.out.println("All package filter search and backspace checks completed successfully.");
        			
        			// ================= FILTER DROPDOWN CHECK =================

        			By filterButtonPath = By.xpath("//button[@aria-label='Filter']");

        			// Open Filter dropdown
        			WebElement filterButton = wait.until(
        			    ExpectedConditions.elementToBeClickable(filterButtonPath)
        			);

        			filterButton.click();
        			waitBetweenActions(driver, 1);

        			// Check and uncheck Critical
        			By criticalPath = By.xpath(
        			    "//div[contains(@class,'cursor-pointer') and .//span[normalize-space()='critical']]"
        			);

        			WebElement critical = wait.until(
        			    ExpectedConditions.elementToBeClickable(criticalPath)
        			);

        			critical.click();
        			System.out.println("Critical filter selected");
        			waitBetweenActions(driver, 2);

        			critical.click();
        			System.out.println("Critical filter unselected");
        			waitBetweenActions(driver, 2);

        			// Check and uncheck Low
        			By lowPath = By.xpath(
        			    "//div[contains(@class,'cursor-pointer') and .//span[normalize-space()='low']]"
        			);

        			WebElement low = wait.until(
        			    ExpectedConditions.elementToBeClickable(lowPath)
        			);

        			low.click();
        			System.out.println("Low filter selected");
        			waitBetweenActions(driver, 2);

        			low.click();
        			System.out.println("Low filter unselected");
        			waitBetweenActions(driver, 2);

        			// Close Filter dropdown
        			filterButton.click();
        			waitBetweenActions(driver, 2);

        			System.out.println("Critical and Low filter checks completed");


        			// Expand All and Collapse All

        			By expandCollapseAllPath = By.xpath(
        			    "//button[.//span[normalize-space()='Expand All' or normalize-space()='Collapse All']]"
        			);

        			// Click Expand All
        			WebElement expandCollapseButton = wait.until(
        			    ExpectedConditions.elementToBeClickable(expandCollapseAllPath)
        			);

        			expandCollapseButton.click();
        			System.out.println("Expand All clicked successfully");
        			waitBetweenActions(driver, 3);

        			// Click Collapse All
        			expandCollapseButton = wait.until(
        			    ExpectedConditions.elementToBeClickable(expandCollapseAllPath)
        			);

        			expandCollapseButton.click();
        			System.out.println("Collapse All clicked successfully");
        			waitBetweenActions(driver, 3);

        			// ================= CATCH AND FINALLY =================

        			        } catch (Exception e) {
        			            e.printStackTrace();
        			            throw new RuntimeException(e);

        			        } finally {
        			            driver.quit();
        			        }

        			    } 

        			}