package SignIn;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Scanner;

public class PlanAndBilling {

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {

            // =========================
            // LOGIN PAGE
            // =========================
            driver.get("https://moole.ai/auth/signin");
            slowMotion(3);

            WebElement email = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']"))
            );

            highlight(driver, email);
            email.sendKeys("moole.dev.2@gmail.com");

            slowMotion(2);

            WebElement continueBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Continue')]"))
            );

            visibleClick(driver, continueBtn);

            // =========================
            // OTP FLOW
            // =========================
            System.out.println("Enter OTP manually and press ENTER...");
            new Scanner(System.in).nextLine();

            slowMotion(10);

            // =========================
            // NAVIGATION
            // =========================
            driver.get("https://moole.ai/app/settings/project/integrations");
            slowMotion(5);

            // =========================
            // USAGE REPORT
            // =========================
            WebElement usageReport = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Usage Report')]"))
            );

            visibleClick(driver, usageReport);
            System.out.println("Clicked Usage Report");

            slowMotion(3);

            // =========================
            // ACTIVE PLAN
            // =========================
            WebElement activePlan = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Active Plan')]"))
            );

            visibleClick(driver, activePlan);
            System.out.println("Opened Active Plan");

            slowMotion(3);

            // =========================
            // TRANSACTION
            // =========================
            WebElement transaction = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[contains(text(),'Transaction')]]")
                    )
            );

            visibleClick(driver, transaction);
            slowMotion(3);

            // =========================
            // BILLING
            // =========================
            WebElement billing = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[contains(text(),'Billing')]]")
                    )
            );

            visibleClick(driver, billing);
            slowMotion(3);

         

            // =========================
            // CANCEL SUBSCRIPTION
            // =========================
            WebElement cancel = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Cancel Subscription')]"))
            );

            visibleClick(driver, cancel);
            System.out.println("Clicked Cancel Subscription");

            slowMotion(4);

            // =========================
            // KEEP PLAN
            // =========================
            WebElement keepPlan = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Keep Plan')]"))
            );

            visibleClick(driver, keepPlan);
            System.out.println("Clicked Keep Plan");

            slowMotion(4);
         // =========================
         // UPGRADE PLAN
         // =========================

         WebElement upgradePlan = wait.until(
                 ExpectedConditions.presenceOfElementLocated(
                         By.xpath("//a[@href='/app/settings/billing/upgrade-plan' or contains(.,'Upgrade Plan')]")
                 )
         );

         js.executeScript("arguments[0].scrollIntoView({block:'center'});", upgradePlan);
         slowMotion(2);

         highlight(driver, upgradePlan);
         slowMotion(2);

         visibleClick(driver, upgradePlan);

         System.out.println("Clicked Upgrade Plan");
         
      // =========================
      // SWITCH PLAN
      // =========================

      WebElement switchPlan = wait.until(
              ExpectedConditions.presenceOfElementLocated(
                      By.xpath("//a[@href='/app/billing/checkout' or contains(.,'Switch Plan')]")
              )
      );

      js.executeScript("arguments[0].scrollIntoView({block:'center'});", switchPlan);
      slowMotion(2);

      highlight(driver, switchPlan);
      slowMotion(2);

      visibleClick(driver, switchPlan);

      System.out.println("Clicked Switch Plan");
      
      WebElement checkbox = wait.until(
          ExpectedConditions.visibilityOfElementLocated(
              By.xpath("(//div[contains(@class,'rounded-full')])[1]")
          )
      );

      ((JavascriptExecutor) driver).executeScript(
          "arguments[0].click();", checkbox
      );
      // Click Confirm & Pay button
      WebElement confirmPayBtn = wait.until(
              ExpectedConditions.presenceOfElementLocated(
                      By.xpath("//button[contains(.,'Confirm') and contains(.,'Pay')]")
              )
      );
      ((JavascriptExecutor) driver).executeScript(
              "arguments[0].scrollIntoView({block:'center'});",
              confirmPayBtn
      );
      Thread.sleep(2000);
      ((JavascriptExecutor) driver).executeScript(
              "arguments[0].click();",
              confirmPayBtn
      );
      System.out.println("Clicked Confirm & Pay");
      Thread.sleep(4000);
      
      

     /*       // =========================
            // BACK TO DASHBOARD
            // =========================
            WebElement dashboard = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@href,'dashboard')]"))
            );

            visibleClick(driver, dashboard);
            System.out.println("Back to Dashboard");

            slowMotion(5);*/

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    // =========================
    // SLOW MOTION VIEW (RENAMED - AS YOU WANTED)
    // =========================
    public static void slowMotion(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // =========================
    // HIGHLIGHT ELEMENT
    // =========================
    public static void highlight(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].style.border='3px solid red';" +
                        "arguments[0].style.background='yellow';",
                element
        );
    }

    // =========================
    // VISUAL CLICK
    // =========================
    public static void visibleClick(WebDriver driver, WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
            slowMotion(2);

            highlight(driver, element);
            slowMotion(2);

            element.click();

        } catch (Exception e) {
            js.executeScript("arguments[0].click();", element);
        }

        slowMotion(2);
    }
}