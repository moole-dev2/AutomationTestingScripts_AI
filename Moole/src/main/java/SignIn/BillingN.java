package SignIn;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

public class BillingN {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {

            // -------------------------------------------------
            // OPEN WEBSITE
            // -------------------------------------------------

            driver.get("https://moole.ai/");

            driver.manage().window().maximize();

            driver.get("https://moole.ai/auth/signin");

            Thread.sleep(5000);

            // -------------------------------------------------
            // LOGIN EMAIL
            // -------------------------------------------------

            WebElement emailField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@type='email']")
                    )
            );

            emailField.sendKeys("moole.dev.2@gmail.com");

            WebElement continueBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(text(),'Continue')]")
                    )
            );

            continueBtn.click();

            // -------------------------------------------------
            // OTP MANUAL ENTRY
            // -------------------------------------------------

            System.out.println("Enter OTP manually then press ENTER...");

            Scanner scanner = new Scanner(System.in);

            scanner.nextLine();

            Thread.sleep(3000);

            // -------------------------------------------------
            // OPEN BILLING PAGE
            // -------------------------------------------------

            driver.get("https://moole.ai/app/settings/project/integrations");

            Thread.sleep(3000);

            WebElement activePlan = driver.findElement(
                    By.xpath("//a[contains(@href,'plan-details')]")
            );

            activePlan.click();

            System.out.println("Clicked Active Plan");

            Thread.sleep(2000);

            // -------------------------------------------------
            // HUMAN SCROLL
            // -------------------------------------------------

            for (int i = 0; i < 30; i++) {

                js.executeScript("window.scrollBy(0,100)");

                Thread.sleep(150);
            }

            // -------------------------------------------------
            // BILLING ACTIVITY
            // -------------------------------------------------

            WebElement billingActivity = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[text()='Billing Activity']]")
                    )
            );

            js.executeScript("arguments[0].click();", billingActivity);

            Thread.sleep(2000);

            // -------------------------------------------------
            // ADD CARD
            // -------------------------------------------------

            WebElement addCard = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[contains(.,'Add Card')]]")
                    )
            );

            js.executeScript("arguments[0].click();", addCard);

            System.out.println("Clicked Add Card");

            Thread.sleep(5000);

            // -------------------------------------------------
            // CARD DETAILS
            // -------------------------------------------------

            List<WebElement> frames = driver.findElements(By.tagName("iframe"));

            boolean cardEntered = false;

            for (int i = 0; i < frames.size(); i++) {

                try {

                    driver.switchTo().defaultContent();

                    driver.switchTo().frame(i);

                    WebElement cardNumber = wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.name("number")
                            )
                    );

                    cardNumber.sendKeys("5555555555554444");

                    WebElement expiry = driver.findElement(By.name("expiry"));

                    expiry.sendKeys("07/31");

                    WebElement cvc = driver.findElement(By.name("cvc"));

                    cvc.sendKeys("270");

                    System.out.println("Card Entered");

                    cardEntered = true;

                    break;

                } catch (Exception e) {

                    System.out.println("Frame " + i + " skipped");
                }
            }

            driver.switchTo().defaultContent();

            if (!cardEntered) {

                System.out.println("Card Fields Not Found");

                return;
            }

            Thread.sleep(4000);

            // -------------------------------------------------
            // EMAIL FIELD
            // -------------------------------------------------

            List<WebElement> billingFrames = driver.findElements(By.tagName("iframe"));

            for (int i = 0; i < billingFrames.size(); i++) {

                try {

                    driver.switchTo().defaultContent();

                    driver.switchTo().frame(i);

                    List<WebElement> emails = driver.findElements(
                            By.xpath("//input[contains(@autocomplete,'email') or @type='email']")
                    );

                    if (!emails.isEmpty()) {

                        WebElement email = emails.get(0);

                        email.sendKeys("moole@testing.com");

                        System.out.println("Billing Email Entered");

                        break;
                    }

                } catch (Exception e) {

                    System.out.println("Email frame skipped");
                }
            }

            // -------------------------------------------------
            // PHONE NUMBER
            // -------------------------------------------------

            driver.switchTo().defaultContent();

            List<WebElement> phoneFrames = driver.findElements(By.tagName("iframe"));

            for (int i = 0; i < phoneFrames.size(); i++) {

                try {

                    driver.switchTo().defaultContent();

                    driver.switchTo().frame(i);

                    List<WebElement> phones = driver.findElements(
                            By.xpath("//input[@name='linkMobilePhone']")
                    );

                    if (!phones.isEmpty()) {

                        WebElement phone = phones.get(0);

                        phone.sendKeys("2015550123");

                        System.out.println("Phone Entered");

                        break;
                    }

                } catch (Exception e) {

                    System.out.println("Phone frame skipped");
                }
            }

            // -------------------------------------------------
            // FULL NAME
            // -------------------------------------------------

            driver.switchTo().defaultContent();

            List<WebElement> nameFrames = driver.findElements(By.tagName("iframe"));

            for (int i = 0; i < nameFrames.size(); i++) {

                try {

                    driver.switchTo().defaultContent();

                    driver.switchTo().frame(i);

                    List<WebElement> names = driver.findElements(
                            By.xpath("//input[contains(@autocomplete,'billing name') or @name='name']")
                    );

                    if (!names.isEmpty()) {

                        WebElement fullName = names.get(0);

                        fullName.sendKeys("John Cena");

                        System.out.println("Full Name Entered");

                        break;
                    }

                } catch (Exception e) {

                    System.out.println("Name frame skipped");
                }
            }

            // -------------------------------------------------
            // ADDRESS
            // -------------------------------------------------

            driver.switchTo().defaultContent();

            List<WebElement> addressFrames = driver.findElements(By.tagName("iframe"));

            for (int i = 0; i < addressFrames.size(); i++) {

                try {

                    driver.switchTo().defaultContent();

                    driver.switchTo().frame(i);

                    List<WebElement> addresses = driver.findElements(
                            By.xpath("//input[contains(@autocomplete,'address-line1')]")
                    );

                    if (!addresses.isEmpty()) {

                        WebElement address = addresses.get(0);

                        address.sendKeys("1125 Miller Lane");

                        System.out.println("Address Entered");

                        break;
                    }

                } catch (Exception e) {

                    System.out.println("Address frame skipped");
                }
            }

         // -------------------------------------------------
         // CITY FIELD (LOCALITY INPUT)
         // -------------------------------------------------

         try {

             WebElement cityField = wait.until(
                     ExpectedConditions.visibilityOfElementLocated(
                             By.xpath("//input[@id='billingAddress-localityInput' or @name='locality']")
                     )
             );

             js.executeScript(
                     "arguments[0].scrollIntoView({block:'center'});",
                     cityField
             );

             Thread.sleep(1000);

             js.executeScript("arguments[0].click();", cityField);

             cityField.clear();

             cityField.sendKeys("Buffalo Grove");

             // Trigger JS events (important for Stripe-style forms)
             js.executeScript(
                     "arguments[0].value='Highwood';" +
                     "arguments[0].dispatchEvent(new Event('input',{bubbles:true}));" +
                     "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));",
                     cityField
             );

             System.out.println("City Entered Successfully");

         } catch (Exception e) {

             System.out.println("City field not found");

             e.printStackTrace();
         }

            // -------------------------------------------------
            // STATE
            // -------------------------------------------------

            driver.switchTo().defaultContent();

            List<WebElement> stateFrames = driver.findElements(By.tagName("iframe"));

            for (int i = 0; i < stateFrames.size(); i++) {

                try {

                    driver.switchTo().defaultContent();

                    driver.switchTo().frame(i);

                    List<WebElement> states = driver.findElements(
                            By.xpath("//select[@id='billingAddress-administrativeAreaInput']")
                    );

                    if (!states.isEmpty()) {

                        Select select = new Select(states.get(0));

                        select.selectByVisibleText("Illinois");

                        System.out.println("Illinois Selected");

                        break;
                    }

                } catch (Exception e) {

                    System.out.println("State frame skipped");
                }
            }

            // -------------------------------------------------
            // ZIP CODE
            // -------------------------------------------------

            driver.switchTo().defaultContent();

            List<WebElement> zipFrames = driver.findElements(By.tagName("iframe"));

            for (int i = 0; i < zipFrames.size(); i++) {

                try {

                    driver.switchTo().defaultContent();

                    driver.switchTo().frame(i);

                    List<WebElement> zips = driver.findElements(
                            By.xpath("//input[@id='billingAddress-postalCodeInput']")
                    );

                    if (!zips.isEmpty()) {

                        WebElement zip = zips.get(0);

                        zip.clear();

                        zip.sendKeys("60089");

                        System.out.println("ZIP Entered");

                        break;
                    }
                    
                 // -------------------------------------------------
                 // BILLING PHONE (AFTER ZIP)
                 // -------------------------------------------------

                 driver.switchTo().defaultContent();

                 Thread.sleep(2000);

                 List<WebElement> phoneFrames1 = driver.findElements(By.tagName("iframe"));

                 boolean phoneEntered = false;

                 for (int i1 = 0; i1 < phoneFrames1.size(); i1++) {

                     try {

                         driver.switchTo().defaultContent();
                         driver.switchTo().frame(i1);

                         List<WebElement> phones = driver.findElements(
                                 By.xpath("//input[@id='billingAddress-phoneInput' or @name='phone']")
                         );

                         if (!phones.isEmpty()) {

                             WebElement phone = phones.get(0);

                             js.executeScript(
                                     "arguments[0].scrollIntoView({block:'center'});",
                                     phone
                             );

                             Thread.sleep(1000);

                             js.executeScript("arguments[0].click();", phone);

                             phone.clear();

                             phone.sendKeys("2015550123");

                             // fallback trigger (Stripe-like forms need this)
                             js.executeScript(
                                     "arguments[0].value='2015550123';" +
                                     "arguments[0].dispatchEvent(new Event('input',{bubbles:true}));" +
                                     "arguments[0].dispatchEvent(new Event('change',{bubbles:true}));",
                                     phone
                             );

                             System.out.println("Billing Phone Entered");

                             phoneEntered = true;

                             break;
                         }

                     } catch (Exception e) {

                         System.out.println("Phone frame " + i1 + " skipped");
                     }
                 }

                 driver.switchTo().defaultContent();

                 if (!phoneEntered) {
                     System.out.println("Billing phone NOT FOUND");
                 }

                } catch (Exception e) {

                    System.out.println("ZIP frame skipped");
                }
            }

            // -------------------------------------------------
            // SAVE BUTTON
            // -------------------------------------------------

            driver.switchTo().defaultContent();

            Thread.sleep(3000);

            WebElement saveButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(.,'Save')]")
                    )
            );

            js.executeScript("arguments[0].click();", saveButton);

            System.out.println("Save Button Clicked");

            Thread.sleep(5000);
            
            WebElement transactionActivity = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//button[.//span[contains(text(),'Transaction Activity')]]")));
			transactionActivity.click();
			System.out.println("Clicked Transaction Activity");
			Thread.sleep(2000);

			
			  WebElement downloadInvoice = driver.findElement(
			  By.xpath("//a[@aria-label='Download Invoice']") );
			  
			  ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
			  downloadInvoice);
			 
			  System.out.println("Clicked Download Invoice"); Thread.sleep(2000);
			 

			
			  WebElement downloadReceipt = driver.findElement(
			  By.xpath("//a[@aria-label='Download Receipt']") );
			  
			  downloadReceipt.click(); System.out.println("Clicked Download Receipt");
			  Thread.sleep(2000);
			 

			WebElement billingActivity1 = wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//button[.//span[text()='Billing Activity']]")));

			js.executeScript("arguments[0].click();", billingActivity1);
			Thread.sleep(2000);

            System.out.println("Automation Completed Successfully");

        } catch (Exception e) {

            System.out.println("ERROR: " + e.getMessage());

            e.printStackTrace();

        } finally {

           // driver.quit();

            System.out.println("Browser Closed");
        }
    }
}