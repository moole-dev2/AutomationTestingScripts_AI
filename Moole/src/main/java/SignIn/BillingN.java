package SignIn;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import Utils.ConfigReader;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

public class BillingN {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
        	driver.get(ConfigReader.getProperty("baseUrl"));
            driver.manage().window().maximize();

            driver.manage().window().maximize();

            // ---------------- LOGIN ----------------
            driver.get("https://moole.ai/auth/signin");
            Thread.sleep(4000);

            WebElement email = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']"))
            );
            email.sendKeys("moole.dev.2@gmail.com");
            WebElement signIn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@data-tour='signup-submit']")
                    )
            );

            signIn.click();

            System.out.println("Sign in button clicked");

            System.out.println("Enter OTP manually...");
            new Scanner(System.in).nextLine();

            Thread.sleep(3000);

            // ---------------- BILLING PAGE ----------------
            driver.get("https://moole.ai/app/settings/project/integrations");
            Thread.sleep(4000);

            WebElement activePlan = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[contains(@href,'plan-details')]")
                    )
            );
            activePlan.click();

            System.out.println("Clicked Active Plan");

            Thread.sleep(3000);

            js.executeScript("window.scrollTo(0, document.body.scrollHeight/2)");

            // ---------------- BILLING ACTIVITY ----------------
            WebElement billingActivity = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[text()='Billing Activity']]")
                    )
            );
            js.executeScript("arguments[0].click();", billingActivity);

            Thread.sleep(3000);

            // =====================================================
            // ✅ FIXED ADD CARD (ROBUST + RETRY + SCROLL)
            // =====================================================

            WebElement addCard = null;

            for (int i = 0; i < 10; i++) {

                List<WebElement> btns = driver.findElements(
                        By.xpath("//button[contains(.,'Add Card') or .//span[contains(.,'Add Card')]]")
                );

                if (!btns.isEmpty()) {
                    addCard = btns.get(0);
                    break;
                }

                js.executeScript("window.scrollBy(0,200)");
                Thread.sleep(1000);
            }

            if (addCard == null) {
                throw new RuntimeException("Add Card button NOT found after retries");
            }

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", addCard);
            Thread.sleep(1000);

            js.executeScript("arguments[0].click();", addCard);

            System.out.println("Clicked Add Card");

            Thread.sleep(4000);

            // =====================================================
            // CARD DETAILS (SAFE STRIPE HANDLING)
            // =====================================================

            fillCard(driver, "number", "5555555555554444");
            fillCard(driver, "expiry", "07/31");
            fillCard(driver, "cvc", "270");

            System.out.println("Card Entered");

            // ---------------- EMAIL ----------------
            fill(driver, wait,
                    "//input[contains(@autocomplete,'email') or @type='email']",
                    "moole@testing.com",
                    "Email Entered"
            );

            // ---------------- PHONE ----------------
            fill(driver, wait,
                    "//input[@name='linkMobilePhone']",
                    "2015550123",
                    "Phone Entered"
            );

            // ---------------- NAME ----------------
            fill(driver, wait,
                    "//input[contains(@autocomplete,'name') or @name='name']",
                    "John Cena",
                    "Name Entered"
            );

            // ---------------- ADDRESS ----------------
            fill(driver, wait,
                    "//input[contains(@autocomplete,'address-line1')]",
                    "1125 Miller Lane",
                    "Address Entered"
            );

            // ---------------- CITY ----------------
            WebElement city = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[contains(@id,'locality') or @name='locality']")
                    )
            );

            js.executeScript("arguments[0].scrollIntoView(true);", city);
            city.click();
            city.clear();
            city.sendKeys("Buffalo Grove");

            js.executeScript("arguments[0].dispatchEvent(new Event('input',{bubbles:true}));", city);

            System.out.println("City Entered");

            // ---------------- STATE ----------------
            Select state = new Select(
                    wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//select[contains(@id,'administrativeArea')]")
                    ))
            );

            state.selectByVisibleText("Illinois");
            System.out.println("State Selected");

            // ---------------- ZIP ----------------
            fill(driver, wait,
                    "//input[contains(@id,'postalCode')]",
                    "60089",
                    "ZIP Entered"
            );

            // ---------------- SAVE ----------------
            WebElement save = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(.,'Save')]")
                    )
            );

            js.executeScript("arguments[0].click();", save);
            System.out.println("Save Clicked");

            Thread.sleep(5000);

            // ---------------- TRANSACTION ----------------
            WebElement transaction = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[contains(text(),'Transaction Activity')]]")
                    )
            );

            transaction.click();
            System.out.println("Transaction Activity Clicked");

            Thread.sleep(2000);

            driver.findElement(By.xpath("//a[@aria-label='Download Invoice']")).click();
            System.out.println("Invoice Downloaded");

            Thread.sleep(2000);

            driver.findElement(By.xpath("//a[@aria-label='Download Receipt']")).click();
            System.out.println("Receipt Downloaded");

            System.out.println("Automation Completed Successfully");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Browser Closed");
            // driver.quit();
        }
    }

    // =====================================================
    // CARD FIELD HANDLER (STRIPE SAFE)
    // =====================================================
    public static void fillCard(WebDriver driver, String field, String value) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> frames = driver.findElements(By.tagName("iframe"));

        for (WebElement frame : frames) {
            try {

                driver.switchTo().defaultContent();
                driver.switchTo().frame(frame);

                List<WebElement> el = driver.findElements(By.name(field));

                if (!el.isEmpty()) {

                    WebElement input = el.get(0);

                    js.executeScript("arguments[0].scrollIntoView(true);", input);
                    input.click();
                    input.clear();
                    input.sendKeys(value);

                    driver.switchTo().defaultContent();
                    System.out.println(field + " Entered");
                    return;
                }

            } catch (Exception ignored) {
                driver.switchTo().defaultContent();
            }
        }

        driver.switchTo().defaultContent();
        System.out.println(field + " NOT FOUND");
    }

    // =====================================================
    // GENERIC INPUT HANDLER
    // =====================================================
    public static void fill(WebDriver driver, WebDriverWait wait,
                            String xpath, String value, String log) {

        try {
            WebElement el = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))
            );

            el.clear();
            el.sendKeys(value);

            System.out.println(log);

        } catch (Exception e) {
            System.out.println("Not found: " + xpath);
        }
    }
}