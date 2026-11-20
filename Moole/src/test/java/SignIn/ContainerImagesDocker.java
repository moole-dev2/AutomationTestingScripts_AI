package SignIn;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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



public class ContainerImagesDocker {

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
            driver.get("https://moole.ai/auth/signin");
            Thread.sleep(2000);

            // --- Step 2: Enter Email ---
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@type='email']")));
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
            Thread.sleep(1000);

            // --- Step 4: Navigate directly to Integrations page ---
            driver.get("https://moole.ai/app/settings/project/integrations");
            
       /*     WebElement connectButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@name='provider' and @value='docker']")
                )
            );

            connectButton.click();
            

            WebElement configName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("configName"))
            );

            configName.clear();
            configName.sendKeys("Testing");
            
            WebElement nextButton = wait.until(
            	    ExpectedConditions.elementToBeClickable(
            	        By.xpath("//button[@type='submit' and normalize-space()='Next']")
            	    )
            	);

            	nextButton.click();
            	
					//  Enter Docker Hub username
					WebElement username = wait.until(
					    ExpectedConditions.visibilityOfElementLocated(By.id("username"))
					);
					username.click();
					username.sendKeys("mooletaran");
					
					WebElement accessToken = wait.until(
						    ExpectedConditions.visibilityOfElementLocated(
						        By.id("accessToken")
						    )
						);

						accessToken.click();
						accessToken.sendKeys("dckr_pat_xz47ouCnp0mk79ZCtfrKRo0yMy8");

						System.out.println("Docker access token entered successfully");
				
					// Wait for Save button to become clickable
					WebElement saveButton = wait.until(
					    ExpectedConditions.elementToBeClickable(
					        By.xpath("//button[@type='submit' and normalize-space()='Save']")
					    )
					);
					
					//  Click Save
					saveButton.click(); */
            
            // ================= CLICK CONTAINER IMAGES =================
                    WebElement containerImages = wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    By.xpath("//a[@href='/app/project/container-security' and .//span[normalize-space()='Container Images']]")
                            )
                    );

                    js.executeScript("arguments[0].scrollIntoView({block:'center'});", containerImages);
                    Thread.sleep(500);
                    js.executeScript("arguments[0].click();", containerImages);

                    System.out.println("Container Images clicked");
                    Thread.sleep(1500);
            

            // ================= CLICK ADD IMAGE =================
            WebElement addImage = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[normalize-space()='Add Image']]")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", addImage);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", addImage);

            System.out.println("Add Image clicked");
            Thread.sleep(1000);

            
            // ================= CLICK EXISTING IN ORG =================
            WebElement existingInOrg = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[normalize-space()='Existing in org']]")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", existingInOrg);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", existingInOrg);

            System.out.println("Existing in org clicked");
            Thread.sleep(1000);

            // ================= CLICK MOOLE ARROW - FIRST TIME =================
            WebElement mooleSection = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//div[contains(@class,'bg-accordian-close') and .//h5[normalize-space()='Moole']]")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", mooleSection);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", mooleSection);

            System.out.println("Moole arrow clicked - expanded");
            Thread.sleep(1000);

            // ================= CLICK CONTAINER REGISTRY =================
            WebElement containerRegistry = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[normalize-space()='Container Registry']]")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", containerRegistry);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", containerRegistry);

            System.out.println("Container Registry clicked");
            Thread.sleep(1000);
            
            By publicButton = By.xpath("//button[@type='button' and .//span[normalize-space()='Public']]");

            WebElement publicElement = wait.until(
                    ExpectedConditions.elementToBeClickable(publicButton));

            publicElement.click();
            Thread.sleep(500);
            System.out.println("Public button clicked");
            
         // Click Private
            By privateButton = By.xpath(
                "//button[@type='button' and .//span[normalize-space()='Private']]"
            );

            WebElement privateElement = wait.until(
                ExpectedConditions.elementToBeClickable(privateButton)
            );

            privateElement.click();
            Thread.sleep(500);
            System.out.println("Private button clicked");
         
            By publicButton1 = By.xpath("//button[@type='button' and .//span[normalize-space()='Public']]");

            WebElement publicElement1 = wait.until(
                    ExpectedConditions.elementToBeClickable(publicButton1));

            publicElement1.click();
            Thread.sleep(500);

            System.out.println("Public button clicked");
            
            By allButton = By.xpath(
            	    "//button[@type='button' and .//span[normalize-space()='All']]"
            	);

            	WebElement allElement = wait.until(
            	    ExpectedConditions.elementToBeClickable(allButton)
            	);

            	allElement.click();
                Thread.sleep(500);
            	System.out.println("All button clicked");
            	
            	// ================= ENTER IMAGE NAME IN SEARCH FIELD =================

            	By imageSearch = By.xpath(
            	    "//input[@placeholder='Find an image to scan']"
            	);

            	WebElement searchInput = wait.until(
            	    ExpectedConditions.visibilityOfElementLocated(imageSearch)
            	);

            	// Enter image name
            	searchInput.sendKeys("my-java-cve-image");

            	System.out.println("Entered my-java-cve-image in search field");

            	Thread.sleep(2000);

            	// ================= CLEAR SEARCH FIELD =================

            	searchInput.clear();

            	System.out.println("Search field cleared successfully");
            	
            	// ================= CLICK DOCKER ARROW NEXT TO MOOLETARAN =================

            	By dockerArrow = By.xpath(
            	    "//span[normalize-space()='mooletaran']/preceding::*[name()='svg' and " +
            	    ".//*[name()='path' and contains(@d,'285.476 272.971')]][1]"
            	);

            	WebElement arrowElement = wait.until(
            	    ExpectedConditions.visibilityOfElementLocated(dockerArrow)
            	);

            	WebElement clickableParent = arrowElement.findElement(
            	    By.xpath("./ancestor::*[self::button or self::div][1]")
            	);

            	wait.until(ExpectedConditions.elementToBeClickable(clickableParent));

            	js.executeScript(
            	    "arguments[0].scrollIntoView({block:'center'});",
            	    clickableParent
            	);

            	clickableParent.click();
            	Thread.sleep(2000);

            	System.out.println("Docker arrow next to mooletaran clicked");
            	
      /*     	// ================= CLICK FIRST RIGHT ARROW =================

            	By firstArrow = By.xpath(
            	    "//*[name()='svg']//*[name()='path' and contains(@d,'285.476 272.971')]"
            	);

            	WebElement firstArrowElement = wait.until(
            	    ExpectedConditions.visibilityOfElementLocated(firstArrow)
            	);

            	WebElement firstClickableParent = firstArrowElement.findElement(
            	    By.xpath("./ancestor::*[self::button or self::div][1]")
            	);

            	wait.until(ExpectedConditions.elementToBeClickable(firstClickableParent));
            	firstClickableParent.click();

            	System.out.println("First arrow clicked");
            	*/
            	


            	// ================= CLICK SECOND DOWN ARROW =================

            	// ================= CLICK ARROW NEXT TO my-java-cve-image =================

            	By myJavaCveArrow = By.xpath(
            	    "//div[contains(@class,'relative flex cursor-pointer')]" +
            	    "[.//span[normalize-space()='my-java-cve-image']]" +
            	    "/*[name()='svg' and @viewBox='0 0 320 512']" +
            	    "[.//*[name()='path' and starts-with(@d,'M285.476 272.971')]]"
            	);

            	WebElement arrowElement1 = wait.until(
            	    ExpectedConditions.elementToBeClickable(myJavaCveArrow)
            	);

            	js.executeScript(
            	    "arguments[0].scrollIntoView({block:'center'});",
            	    arrowElement1
            	);

            	arrowElement1.click();

            	Thread.sleep(1500);

            	System.out.println("my-java-cve-image expanded successfully");

            	
            	// ================= SELECT CHECKBOX FOR my-java-cve-image =================

            	By imageCheckbox = By.xpath(
            	    "//div[contains(@class,'relative flex cursor-pointer')]" +
            	    "[.//span[normalize-space()='my-java-cve-image']]" +
            	    "/button[.//div[contains(@class,'peer-checked:bg-gradient-to-r')]]"
            	);

            	WebElement checkboxButton = wait.until(
            	    ExpectedConditions.elementToBeClickable(imageCheckbox)
            	);

            	js.executeScript(
            	    "arguments[0].scrollIntoView({block:'center'});",
            	    checkboxButton
            	);

            	checkboxButton.click();

            	System.out.println("Checkbox selected for my-java-cve-image");
            	
            	// ================= CLICK SHOW MORE =================

            	By showMoreButton = By.xpath(
            	    "//button[@type='button' and .//span[normalize-space()='Show more']]"
            	);

            	WebElement showMoreElement = wait.until(
            	    ExpectedConditions.elementToBeClickable(showMoreButton)
            	);

            	js.executeScript(
            	    "arguments[0].scrollIntoView({block:'center'});",
            	    showMoreElement
            	);

            	showMoreElement.click();
            	Thread.sleep(2000);
            	System.out.println("Show more button clicked");
            	
            	// ================= CLICK IMPORT 1 IMAGE =================

            	By importImageButton = By.xpath(
            	    "//button[@type='button' and normalize-space()='Import 1 image']"
            	);

            	WebElement importElement = wait.until(
            	    ExpectedConditions.elementToBeClickable(importImageButton)
            	);

            	js.executeScript(
            	    "arguments[0].scrollIntoView({block:'center'});",
            	    importElement
            	);

            	importElement.click();
            	Thread.sleep(2000);

            	System.out.println("Import 1 image button clicked");
            	
					
            	Thread.sleep(8000);
					 // ================= CLICK FILTER DROPDOWN =================

		            WebElement filterButton = wait.until(
		                    ExpectedConditions.elementToBeClickable(
		                            By.xpath("//button[@aria-label='Filter']")
		                    )
		            );

		            filterButton.click();
		            

		            Thread.sleep(1000);

		            System.out.println("Filter dropdown opened");


		            // ================= CLICK CHECKBOX =================

		            WebElement checkbox1 = wait.until(
		                    ExpectedConditions.elementToBeClickable(
		                            By.xpath("//div[contains(@class,'peer-checked:bg-gradient-to-r')]")
		                    )
		            );

		            js.executeScript(
		                    "arguments[0].scrollIntoView({block:'center'});",
		                    checkbox1
		            );

		            Thread.sleep(500);

		            checkbox1.click();

		            System.out.println("Checkbox clicked");

		            Thread.sleep(1000);


		            // ================= CLOSE FILTER =================

		            filterButton.click();

		            System.out.println("Filter dropdown closed");

		            Thread.sleep(1000);

				


        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            driver.quit();
        }
    }
}