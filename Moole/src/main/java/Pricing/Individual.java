package Pricing;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

public class Individual {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // ---------- Open Website ----------
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();
            Thread.sleep(2000);

            // ---------- Handle Popup ----------
            try {
                WebElement okBtn = driver.findElement(By.xpath("//button[normalize-space()='OK']"));
                highlight(driver, okBtn);
                js.executeScript("arguments[0].click();", okBtn);
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("No popup");
            }

            // ---------- Click Pricing ----------
            WebElement pricing = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[@href='/pricing']")
                    )
            );
            highlight(driver, pricing);
            js.executeScript("arguments[0].click();", pricing);

            System.out.println("Opened Pricing Page");
            Thread.sleep(3000);

            // ---------- Select Individual ----------
            WebElement individual = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//input[@value='INDIVIDUAL']")
                    )
            );
            highlight(driver, individual);
            js.executeScript("arguments[0].click();", individual);

            System.out.println("Selected Individual Plan");
            Thread.sleep(2000);

            // ---------- Scroll to Plan ----------
            WebElement planBtn = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("(//button[contains(.,'Get Started') or contains(.,'Start')])[1]")
                    )
            );
            js.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", planBtn);
            Thread.sleep(1000);

            WebElement planBtn1 = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("(//button[contains(.,'Get Started') or contains(.,'Start')])[1]")
                    )
            );
            highlight(driver, planBtn1);
            js.executeScript("arguments[0].click();", planBtn1);

            System.out.println("Clicked Plan Button");
            Thread.sleep(3000);

            // ---------- Enter Email ----------
            WebElement emailField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@type='email']")
                    )
            );
            emailField.sendKeys("moole.dev.2@gmail.com");

            System.out.println("Entered Email");
            Thread.sleep(1000);

            // ---------- Continue ----------
            WebElement continueBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(text(),'Continue')]")
                    )
            );
            highlight(driver, continueBtn);
            continueBtn.click();

            System.out.println("Clicked Continue");

            // ---------- OTP ----------
            System.out.println("Enter OTP manually then press ENTER...");
            new Scanner(System.in).nextLine();

            Thread.sleep(3000);

            // ---------- Go to Pricing ----------
            driver.get("https://moole.ai/pricing");
            Thread.sleep(4000);

            WebElement individual1 = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//input[@value='INDIVIDUAL']")
                    )
            );
            highlight(driver, individual1);
            js.executeScript("arguments[0].click();", individual1);

            Thread.sleep(2000);

            // ==========================================================
            // BUY PLAN LOOP (WITH HIGHLIGHT FIX)
            // ==========================================================

            List<WebElement> buyPlans = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(
                            By.xpath("//button[normalize-space()='Buy Plan']")
                    )
            );

            System.out.println("Total Buy Plans: " + buyPlans.size());

            for (int i = 0; i < buyPlans.size(); i++) {

                buyPlans = wait.until(
                        ExpectedConditions.presenceOfAllElementsLocatedBy(
                                By.xpath("//button[normalize-space()='Buy Plan']")
                        )
                );

                WebElement btn = buyPlans.get(i);

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", btn);
                Thread.sleep(1000);

                highlight(driver, btn);
                btn.click();

                System.out.println("Clicked Buy Plan " + (i + 1));

                Thread.sleep(5000);

                if (driver.getWindowHandles().size() > 1) {

                    String original = driver.getWindowHandles().iterator().next();

                    for (String win : driver.getWindowHandles()) {
                        if (!win.equals(original)) {
                            driver.switchTo().window(win);
                            driver.close();
                        }
                    }

                    driver.switchTo().window(original);

                } else {
                    driver.navigate().back();
                }

                Thread.sleep(4000);
            }

            // ---------- Scroll ----------
            long pageHeight = (Long) js.executeScript("return document.body.scrollHeight;");

            for (int i = 0; i <= pageHeight; i += 300) {
                js.executeScript("window.scrollTo(0," + i + ");");
                Thread.sleep(300);
            }

            for (long i = pageHeight; i >= 0; i -= 300) {
                js.executeScript("window.scrollTo(0," + i + ");");
                Thread.sleep(200);
            }

            System.out.println("Completed scrolling Pricing Page");

            // ---------- Contact Sales ----------
            WebElement label = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("(//label)[2]"))
            );
            highlight(driver, label);
            js.executeScript("arguments[0].click();", label);

            Thread.sleep(2000);

            WebElement contactSales = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(.,'Contact Sales')]")
                    )
            );
            highlight(driver, contactSales);
            js.executeScript("arguments[0].click();", contactSales);

         // ---------- Enter Email (FIXED STALE ELEMENT ISSUE) ----------
            By emailLocator = By.xpath("//input[@type='email' or contains(@placeholder,'Email')]");

            WebElement emailField1 = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(emailLocator)
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", emailField1);
            Thread.sleep(500);

            wait.until(ExpectedConditions.elementToBeClickable(emailLocator));

            // re-find element again (IMPORTANT FIX)
            emailField1 = driver.findElement(emailLocator);

            emailField1.clear();
            emailField1.sendKeys("moole.dev.2@gmail.com");

            System.out.println("Entered Email");
            Thread.sleep(1000);
            WebElement firstName = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//input[@name='firstName' or contains(@placeholder,'First')]")
                    )
            );
            highlight(driver, firstName);
            firstName.sendKeys("john");
            Thread.sleep(1000);


            WebElement lastName = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//input[@name='lastName' or contains(@placeholder,'Last')]")
                    )
            );
            highlight(driver, lastName);
            lastName.sendKeys("lee");
            Thread.sleep(1000);


            WebElement cnapp = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[text()='CNAPP']]")
                    )
            );
            highlight(driver, cnapp);
            cnapp.click();
            Thread.sleep(1000);


            WebElement letsConnect = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(.,'Lets Connect')]")
                    )
            );
            highlight(driver, letsConnect);
            letsConnect.click();
            Thread.sleep(2000);


            System.out.println("Completed Flow");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
    }

    // ==========================================================
    // HIGHLIGHT METHOD
    // ==========================================================
    public static void highlight(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].style.border='3px solid red'; arguments[0].style.background='yellow';",
                element
        );

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}