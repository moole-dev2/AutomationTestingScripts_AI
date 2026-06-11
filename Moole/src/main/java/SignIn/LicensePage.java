package SignIn;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Scanner;

public class LicensePage {

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        try {
        	
        	driver.get("https://moole.ai/");
            driver.manage().window().maximize();
            Thread.sleep(2000);
            System.out.println("Opened the Webiste");


            // ---------- Handle Privacy Popup ----------
            try {
                WebElement okBtn = driver.findElement(By.xpath("//button[normalize-space()='OK']"));
                js.executeScript("arguments[0].click();", okBtn);
                Thread.sleep(1000);
                System.out.println("Privacy popup closed");
            } catch (Exception e) {
                System.out.println("No popup present");
            }
            

            // ================= LOGIN =================
            driver.get("https://moole.ai/auth/signin");

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@type='email']")))
                    .sendKeys("moole.dev.2@gmail.com");

            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(.,'Continue')]")))
                    .click();

            System.out.println("Complete OTP and press ENTER");
            new Scanner(System.in).nextLine();

            sleep(2000);

            // ================= OPEN LICENSE PAGE =================
            driver.get("https://moole.ai/app/settings/organization/license-policy");

            sleep(5000);

            // =====================================================
            // CASE 1: MEDIUM → CLEAR → ADD NOTES → APPLY
            // =====================================================
            System.out.println("CASE 1 START");

            selectPolicy(wait, js, "Medium");
            sleep(2000);

            openNotes(wait, js);
            sleep(1500);

            clearNotes(wait);
            sleep(1000);

            enterNotes(wait, actions, "First entry - Medium");
            sleep(1000);

            clickAddNotes(wait, js);
            sleep(1500);

            clickApply(wait, js);
            sleep(4000);

            System.out.println("CASE 1 DONE");

            // =====================================================
            // CASE 2: CRITICAL → CLEAR ONLY → CLOSE → APPLY
            // =====================================================
            System.out.println("CASE 2 START");

            selectPolicy(wait, js, "Critical");
            sleep(2000);

            openNotes(wait, js);
            sleep(1500);

            clearNotes(wait);
            sleep(1000);

            clickCloseNotes(wait, js);
            sleep(1500);

            clickApply(wait, js);
            sleep(3000);

            System.out.println("CASE 2 DONE");
            System.out.println("SEARCH 1 START");
            
         // ================= LICENSE INFO ICON CLICK =================
            WebElement licenseInfo = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//*[contains(@aria-label,'License Info')]")
                    )
            );

            js.executeScript(
                    "arguments[0].dispatchEvent(new MouseEvent('click',{bubbles:true}))",
                    licenseInfo
            );
            Thread.sleep(1000);

            System.out.println("License Info clicked using JS event");
            
         // ================= CLOSE POPUP BUTTON =================

            WebElement closePopup = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Close popup']")
                    )
            );

            // scroll into view (React UI safety)
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", closePopup);
            Thread.sleep(1000);

            // click using JS (most reliable for SVG buttons)
            js.executeScript("arguments[0].click();", closePopup);

            System.out.println("Popup closed successfully");

            Thread.sleep(1500);

         // Correct License Policy search box
         WebElement search1 = wait.until(
                 ExpectedConditions.presenceOfElementLocated(
                         By.xpath("//input[@id='searchQuery' and contains(@placeholder,'License Policy')]")
                 )
         );

         // bring into view
         js.executeScript("arguments[0].scrollIntoView({block:'center'});", search1);
         sleep(800);

         // force focus
         js.executeScript("arguments[0].click();", search1);
         sleep(500);

         // clear properly (React-safe)
         search1.sendKeys(Keys.CONTROL + "a");
         search1.sendKeys(Keys.BACK_SPACE);

         // type slowly (prevents interactable issue)
         search1.sendKeys("AGPL-3.0-or-later");
         sleep(500);

         search1.sendKeys(Keys.ENTER);

         System.out.println("SEARCH 1 DONE");
         
      // ================= CLEAR =================
         search1 = wait.until(
                 ExpectedConditions.presenceOfElementLocated(
                         By.xpath("//input[@id='searchQuery' and contains(@placeholder,'License Policy')]")
                 )
         );

         js.executeScript("arguments[0].click();", search1);
         Thread.sleep(500);

         search1.sendKeys(Keys.CONTROL + "a");
         search1.sendKeys(Keys.BACK_SPACE);

         Thread.sleep(1000);

         System.out.println("Final clear done for Search 1");

         // ================= SEARCH 2: LGPL =================
         search1.sendKeys("JJJJ");
         search1.sendKeys(Keys.ENTER);

         Thread.sleep(2000);

         System.out.println("JJJJ");
         
         System.out.println("JJJJ not found");

         System.out.println("SEARCH 1 COMPLETE");
         
         
         // ================= CLEAR =================
            search1 = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//input[@id='searchQuery' and contains(@placeholder,'License Policy')]")
                    )
            );

            js.executeScript("arguments[0].click();", search1);
            Thread.sleep(500);

            search1.sendKeys(Keys.CONTROL + "a");
            search1.sendKeys(Keys.BACK_SPACE);

            Thread.sleep(1000);

            System.out.println("Final clear done for Search 1");
         

            // =====================================================
            // SEARCH 2 (Project Search)
            // =====================================================
            System.out.println("PROJECT SEARCH START");

            search(wait, js, actions,
                    "//input[@id='searchQuery' and contains(@placeholder,'override')]",
                    "Project");

            sleep(2000);
            System.out.println("WAITING FOR PROJECT DROPDOWN");

         // wait for dropdown list
         WebElement dropdownList = wait.until(
                 ExpectedConditions.visibilityOfElementLocated(
                         By.xpath("//ul[contains(@class,'absolute') and contains(@class,'overflow-y-auto')]")
                 )
         );

         Thread.sleep(1000);

       // click the FIRST matching "My Project" item
         WebElement projectItem = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//ul[contains(@class,'overflow-y-auto')]//li[contains(.,'Project')]")
                 )
         );

         js.executeScript("arguments[0].scrollIntoView({block:'center'});", projectItem);
         Thread.sleep(500);

         js.executeScript("arguments[0].click();", projectItem);

         System.out.println("PROJECT CLICKED");
         
         Thread.sleep(2000);

         
      // IMPORTANT: re-locate element (prevents stale)
         WebElement projectToggle = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         By.xpath("//input[@type='checkbox' and following-sibling::div[contains(@class,'peer')]]")
                 )
         );

         // toggle OFF
         js.executeScript("arguments[0].click();", projectToggle);

         System.out.println("PROJECT UNSELECTED");

         Thread.sleep(2000);
         
         
         // ================= SEARCH JJJJ =================

         WebElement projectSearch = wait.until(
                 ExpectedConditions.presenceOfElementLocated(
                         By.xpath("//input[@id='searchQuery' and contains(@placeholder,'override')]")
                 )
         );

         js.executeScript("arguments[0].click();", projectSearch);
         Thread.sleep(500);

         projectSearch.sendKeys(Keys.CONTROL + "a");
         projectSearch.sendKeys(Keys.BACK_SPACE);

         Thread.sleep(1000);

         projectSearch.sendKeys("JJJJ");
         projectSearch.sendKeys(Keys.ENTER);

         System.out.println("SEARCHED: JJJJ");

         Thread.sleep(2000);

         // ================= CLEAR SEARCH =================

         projectSearch.sendKeys(Keys.CONTROL + "a");
         projectSearch.sendKeys(Keys.BACK_SPACE);

         Thread.sleep(1000);

         System.out.println("SEARCH CLEARED");
         System.out.println("TEST COMPLETED");

         // small wait for UI update
         Thread.sleep(2000);

            System.out.println("TEST COMPLETED");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    // ================= POLICY =================
    static void selectPolicy(WebDriverWait wait, JavascriptExecutor js, String policy) {

        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(@aria-label,'Sort')]")
                )
        );
        js.executeScript("arguments[0].click();", dropdown);
        sleep(1000);

        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[normalize-space()='" + policy + "']")
                )
        );
        js.executeScript("arguments[0].click();", option);

        System.out.println("Selected Policy: " + policy);
    }

    // ================= NOTES =================
    static void openNotes(WebDriverWait wait, JavascriptExecutor js) {

        WebElement addNotes = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//p[@aria-label='Add Notes']")
                )
        );
        js.executeScript("arguments[0].click();", addNotes);

        System.out.println("Opened Notes");
    }

    static void clearNotes(WebDriverWait wait) {

        WebElement notes = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//textarea[@placeholder='Write your notes']")
                )
        );

        notes.click();
        notes.sendKeys(Keys.CONTROL + "a");
        notes.sendKeys(Keys.BACK_SPACE);

        System.out.println("Notes Cleared");
    }

    static void enterNotes(WebDriverWait wait, Actions actions, String text) {

        WebElement notes = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//textarea[@placeholder='Write your notes']")
                )
        );

        notes.click();
        actions.moveToElement(notes).click().sendKeys(text).perform();

        System.out.println("Notes Entered");
    }

    static void clickAddNotes(WebDriverWait wait, JavascriptExecutor js) {

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[normalize-space()='Add Notes']")
                )
        );

        js.executeScript("arguments[0].click();", btn);

        System.out.println("Clicked Add Notes");
    }

    static void clickApply(WebDriverWait wait, JavascriptExecutor js) {

        try {

            // Scroll to bottom of page
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(2000);

            // Locate Apply License Policy button
            WebElement apply = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//button[normalize-space()='Apply License Policy']")
                    )
            );

            // Scroll button into view
            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    apply
            );

            Thread.sleep(1000);

            // Wait until clickable
            wait.until(ExpectedConditions.elementToBeClickable(apply));

            // Click using JavaScript
            js.executeScript("arguments[0].click();", apply);

            System.out.println("Applied License Policy Successfully");

            Thread.sleep(3000);

        } catch (Exception e) {
            System.out.println("Failed to click Apply License Policy");
            e.printStackTrace();
        }
    }

    static void clickCloseNotes(WebDriverWait wait, JavascriptExecutor js) {

        WebElement close = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[normalize-space()='Close Notes']")
                )
        );

        js.executeScript("arguments[0].click();", close);

        System.out.println("Closed Notes");
    }

    // ================= SEARCH (FIX FOR BOTH SEARCH BOXES) =================
    static void search(WebDriverWait wait, JavascriptExecutor js, Actions actions,
                       String xpath, String value) {

        WebElement box = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))
        );

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", box);
        sleep(500);

        js.executeScript("arguments[0].click();", box);
        sleep(500);

        box.sendKeys(Keys.CONTROL + "a");
        box.sendKeys(Keys.BACK_SPACE);

        sleep(500);

        actions.moveToElement(box).click().sendKeys(value + Keys.ENTER).perform();

        System.out.println("Search Done: " + value);
    }

    static void clearSearch(WebDriverWait wait, JavascriptExecutor js, String xpath) {

        WebElement box = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))
        );

        js.executeScript("arguments[0].value='';", box);
        js.executeScript("arguments[0].dispatchEvent(new Event('input',{bubbles:true}));", box);

        System.out.println("Search Cleared");
    }

    static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (Exception ignored) {}
    }
}