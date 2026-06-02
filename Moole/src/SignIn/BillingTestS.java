package SignIn;

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
import java.util.Scanner;

public class BillingTestS {

    public static void main(String[] args) {

        // --- ChromeOptions to use existing profile ---
        

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        
        JavascriptExecutor js = (JavascriptExecutor) driver;


        try {
            // --- Step 1: Open Moole.ai and click Sign In ---
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();

            driver.get("https://moole.ai/auth/signin");

            Thread.sleep(5000);

            // --- Step 2: Enter Email ---
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@type='email']")));
            emailField.sendKeys("moole.dev.2@gmail.com");

            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Continue')]")));
            continueBtn.click();

            // --- Step 3: Wait for OTP manually ---
            System.out.println("Please enter your OTP manually in the browser, then press Enter here...");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            Thread.sleep(2000);

            // --- Step 4: Navigate directly to Integrations page ---
            driver.get("https://moole.ai/app/settings/project/integrations");
            Thread.sleep(500);
            
            WebElement activePlan = driver.findElement(
                    By.xpath("//a[contains(@href,'plan-details')]")
            );

            activePlan.click();
            System.out.println("Clicked Active Plan");
            Thread.sleep(1000);
            
            for (int i = 0; i < 30; i++) {
                js.executeScript("window.scrollBy(0, 100);");  // small movement
                Thread.sleep(150); // slow like human scroll
            }

            System.out.println("Human-like scroll completed");
           
            WebElement transactionActivity = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[contains(text(),'Transaction Activity')]]")
                    )
            );
            transactionActivity.click();
            System.out.println("Clicked Transaction Activity");
            Thread.sleep(2000);
            
       /*     WebElement downloadInvoice = driver.findElement(
                    By.xpath("//a[@aria-label='Download Invoice']")
            );

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", downloadInvoice);

            System.out.println("Clicked Download Invoice");
            Thread.sleep(2000);*/
            
 /*         WebElement downloadReceipt = driver.findElement(
            By.xpath("//a[@aria-label='Download Receipt']")
    );

    	downloadReceipt.click();
    	System.out.println("Clicked Download Receipt");
    	Thread.sleep(2000);*/
    
            
            WebElement billingActivity = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//button[.//span[text()='Billing Activity']]")
                    )
            );

            js.executeScript("arguments[0].click();", billingActivity);
            Thread.sleep(2000);
            
            WebElement addCard = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//button[.//span[contains(.,'Add Card')]]")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", addCard);
            Thread.sleep(1000);

            // safe click (JS click avoids overlay issues)
            js.executeScript("arguments[0].click();", addCard);
            System.out.println("Clicked Add Card");
            Thread.sleep(4000);
           
         // STEP 8 - HANDLE IFRAMES
            List<WebElement> frames = driver.findElements(By.tagName("iframe"));

            System.out.println("Total Frames Found: " + frames.size());

            boolean cardEntered = false;

            for (int i = 0; i < frames.size(); i++) {

                driver.switchTo().defaultContent();

                driver.switchTo().frame(i);

                try {

                    // CARD NUMBER
                    WebElement cardNumber = wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.name("number"))
                    );

                    cardNumber.sendKeys("4342376023162046");

                    System.out.println("Entered Card Number");

                    // EXPIRY DATE
                    WebElement expiryDate = driver.findElement(By.name("expiry"));

                    expiryDate.sendKeys("07/31");

                    System.out.println("Entered Expiry Date");

                    // CVV
                    WebElement cvc = driver.findElement(By.name("cvc"));

                    cvc.sendKeys("270");

                    System.out.println("Entered CVV");

                    cardEntered = true;

                    break;

                } catch (Exception e) {

                    System.out.println("Frame " + i + " skipped");
                }
            }

            driver.switchTo().defaultContent();

            if (!cardEntered) {

                System.out.println("Card fields not found");

            } else {

                System.out.println("Card details entered successfully");
            }

            Thread.sleep(4000);

            // -------------------------------------------------
            // SAVE MY INFORMATION SECTION (EMAIL + MOBILE)
           
            boolean emailFound = false;

            List<WebElement> frames2 = driver.findElements(By.tagName("iframe"));

            for (int i = 0; i < frames2.size(); i++) {

                driver.switchTo().defaultContent();
                driver.switchTo().frame(i);

                List<WebElement> emails = driver.findElements(By.xpath("//input[@type='email']"));

                if (!emails.isEmpty()) {

                    WebElement emailField1 = emails.get(0);

                    js.executeScript("arguments[0].scrollIntoView({block:'center'});", emailField1);
                    emailField1.click();

                    js.executeScript(
                            "arguments[0].value='sudheer@testing.com';" +
                                    "arguments[0].dispatchEvent(new Event('input', {bubbles:true}));",
                            emailField1
                    );

                    System.out.println("Email entered in iframe: " + i);

                    emailFound = true;
                    break;
                }
            }

            driver.switchTo().defaultContent();

            if (!emailFound) {
                throw new RuntimeException("Email field not found in any iframe");
            }
         try {

             // FULL NAME
             WebElement fullName = wait.until(
                     ExpectedConditions.visibilityOfElementLocated(
                             By.xpath("//input[contains(@name,'name') or contains(@placeholder,'Name')]"))
             );

             fullName.sendKeys("John Cena");

             System.out.println("Entered Full Name");

         } catch (Exception e) {

             System.out.println("Full Name field not found");
         }

            try {

                // Click country dropdown
                WebElement countryDropdown = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[contains(.,'Country') or contains(.,'Select Country')] | //div[contains(@class,'select')]"))
                );

                js.executeScript("arguments[0].click();", countryDropdown);

                System.out.println("Opened Country Dropdown");

                Thread.sleep(2000);

                // Select United States option
                WebElement usaOption = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//*[text()='USA' or text()='United States' or text()='United States of America']"))
                );

                js.executeScript("arguments[0].click();", usaOption);

                System.out.println("Selected USA");

            } catch (Exception e) {

                System.out.println("Country dropdown not found");
            }

            try {

                // ADDRESS
                WebElement address = driver.findElement(
                        By.xpath("//input[contains(@name,'address') or contains(@placeholder,'Address')]")
                );

                address.sendKeys("1-2-34, Buffalo Grove, IL, 60089");

                System.out.println("Entered Address");

            } catch (Exception e) {

                System.out.println("Address field not found");
            }

            Thread.sleep(3000);

            // -------------------------------------------------
            // STEP 10 - CLICK SAVE BUTTON
            // -------------------------------------------------

            try {

                WebElement saveButton = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[contains(.,'Save')]"))
                );

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", saveButton);

                Thread.sleep(1000);

                js.executeScript("arguments[0].click();", saveButton);

                System.out.println("Clicked Save Button");

            } catch (Exception e) {

                System.out.println("Save button not found");
            }

            Thread.sleep(5000);

            System.out.println("Automation Completed Successfully");

        } catch (Exception e) {

            System.out.println("ERROR: " + e.getMessage());

            e.printStackTrace();

        } finally {

            driver.quit();

            System.out.println("Browser Closed");
        }
    }
}