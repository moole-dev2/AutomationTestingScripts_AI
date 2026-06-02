package SignIn;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

public class ActivePlan {

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        Actions actions = new Actions(driver);

        try {

            // =========================================
            // LOGIN
            // =========================================

            driver.get("https://moole.ai/auth/signin");

            WebElement email = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@type='email']")
                    )
            );

            email.sendKeys("moole.dev.2@gmail.com");

            WebElement continueBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(.,'Continue')]")
                    )
            );

            continueBtn.click();

            System.out.println("Complete OTP manually and press ENTER");

            new Scanner(System.in).nextLine();

            Thread.sleep(3000);

            // =========================================
            // OPEN BILLING PAGE DIRECTLY
            // =========================================

            driver.get("https://moole.ai/app/settings/billing/plan-details");

            wait.until(
                    webDriver ->
                            ((JavascriptExecutor) webDriver)
                                    .executeScript("return document.readyState")
                                    .equals("complete")
            );

            Thread.sleep(5000);

            // =========================================
            // HANDLE ACTIVE PLAN
            // =========================================

            try {

                List<WebElement> activePlanList = driver.findElements(
                        By.xpath("//*[contains(text(),'Active Plan')]")
                );

                if (activePlanList.size() > 0) {

                    WebElement activePlan = activePlanList.get(0);

                    js.executeScript(
                            "arguments[0].scrollIntoView({block:'center'});",
                            activePlan
                    );

                    Thread.sleep(1500);

                    js.executeScript("arguments[0].click();", activePlan);

                    System.out.println("Opened Active Plan");

                } else {

                    System.out.println("Already inside Active Plan page");

                }

            } catch (Exception e) {

                System.out.println("Active Plan section not clickable");

            }

            Thread.sleep(3000);

            // =========================================
            // TRANSACTION ACTIVITY
            // =========================================

            WebElement txnBtn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//button//*[contains(text(),'Transaction')]")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    txnBtn
            );

            Thread.sleep(1000);

            js.executeScript("arguments[0].click();", txnBtn);

            System.out.println("Clicked Transaction Activity");

            Thread.sleep(3000);

            // =========================================
            // BILLING ACTIVITY
            // =========================================

            WebElement billingBtn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//button//*[contains(text(),'Billing')]")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    billingBtn
            );

            Thread.sleep(1000);

            js.executeScript("arguments[0].click();", billingBtn);

            System.out.println("Clicked Billing Activity");

            Thread.sleep(3000);

            // =========================================
            // SCROLL TOP
            // =========================================

            js.executeScript("window.scrollTo(0,0)");

            Thread.sleep(2000);

            // =========================================
            // CLICK BANKS
            // =========================================

            WebElement banksTab = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//*[contains(text(),'Banks')]")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    banksTab
            );

            Thread.sleep(1000);

            js.executeScript("arguments[0].click();", banksTab);

            System.out.println("Clicked Banks");

            Thread.sleep(3000);

            // =========================================
            // CLICK CARDS
            // =========================================

            WebElement cardsTab = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//*[contains(text(),'Cards')]")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    cardsTab
            );

            Thread.sleep(1000);

            js.executeScript("arguments[0].click();", cardsTab);

            System.out.println("Clicked Cards");

            Thread.sleep(3000);

            // =========================================
            // CLICK ADD CARD
            // =========================================

            WebElement addCardBtn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath(
                                    "//button[contains(.,'Add') and contains(.,'Card')]"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    addCardBtn
            );

            Thread.sleep(1000);

            js.executeScript(
                    "arguments[0].style.border='3px solid red'",
                    addCardBtn
            );

            Thread.sleep(500);

            js.executeScript("arguments[0].click();", addCardBtn);

            System.out.println("Clicked Add Card");

            Thread.sleep(5000);

            // =========================================
            // SWITCH TO STRIPE IFRAME
            // =========================================

            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));

            boolean switched = false;

            for (WebElement frame : iframes) {

                driver.switchTo().frame(frame);

                List<WebElement> cardFields = driver.findElements(
                        By.cssSelector("input[name='number']")
                );

                if (cardFields.size() > 0) {

                    switched = true;

                    System.out.println("Switched to Stripe iframe");

                    break;
                }

                driver.switchTo().defaultContent();
            }

            if (!switched) {

                System.out.println("Stripe iframe not found");

            }

            // =========================================
            // CARD NUMBER
            // =========================================

            WebElement cardNumber = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("input[name='number']")
                    )
            );

            cardNumber.click();

            cardNumber.sendKeys("4000056655665556");

            System.out.println("Entered Card Number");

            // =========================================
            // EXPIRY
            // =========================================

            WebElement expiry = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("input[name='expiry']")
                    )
            );

            expiry.sendKeys("1027");

            System.out.println("Entered Expiry");

            // =========================================
            // CVV
            // =========================================

            WebElement cvv = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("input[name='cvc']")
                    )
            );

            cvv.sendKeys("777");

            System.out.println("Entered CVV");

            // =========================================
            // NAME
            // =========================================

            List<WebElement> nameFields = driver.findElements(
                    By.cssSelector("input[name='name']")
            );

            if (nameFields.size() > 0) {

                nameFields.get(0).sendKeys("Test User");

                System.out.println("Entered Name");

            }

            // =========================================
            // SWITCH BACK
            // =========================================

            driver.switchTo().defaultContent();

            Thread.sleep(2000);

            // =========================================
            // SAVE BUTTON
            // =========================================

            WebElement saveBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(.,'Save')]")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    saveBtn
            );

            Thread.sleep(1000);

            js.executeScript("arguments[0].click();", saveBtn);

            System.out.println("Card Saved Successfully");

            Thread.sleep(5000);

        } catch (Exception e) {

            System.out.println("TEST FAILED");

            e.printStackTrace();

        } finally {

            driver.quit();

        }
    }
}