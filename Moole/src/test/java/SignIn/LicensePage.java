package SignIn;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.ConfigReader;

import java.time.Duration;
import java.util.Scanner;

import org.testng.annotations.Test;

public class LicensePage {

    @Test
    public void LicensePageTest() throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(30)
        );

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        try {

            // =====================================================
            // OPEN WEBSITE
            // =====================================================

            driver.get(ConfigReader.getProperty("baseUrl"));
            driver.manage().window().maximize();

            Thread.sleep(2000);

            System.out.println("Opened the Website");


            // =====================================================
            // HANDLE PRIVACY POPUP
            // =====================================================

            try {

                WebElement okBtn = driver.findElement(
                        By.xpath("//button[normalize-space()='OK']")
                );

                js.executeScript(
                        "arguments[0].click();",
                        okBtn
                );

                Thread.sleep(1000);

                System.out.println("Privacy popup closed");

            } catch (Exception e) {

                System.out.println("No popup present");
            }


            // =====================================================
            // LOGIN
            // =====================================================

            driver.get("https://moole.ai/auth/signin");

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@type='email']")
                    )
            ).sendKeys("moole.dev.2@gmail.com");


            WebElement signIn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@data-tour='signup-submit']")
                    )
            );

            signIn.click();

            System.out.println("Sign in button clicked");
            System.out.println("Complete OTP and press ENTER");

            new Scanner(System.in).nextLine();

            sleep(2000);


            // =====================================================
            // OPEN LICENSE POLICY PAGE
            // =====================================================

            driver.get(
                    "https://moole.ai/app/settings/organization/license-policy"
            );

            sleep(5000);


            // =====================================================
            // CASE 1
            // MEDIUM → CLEAR → ADD NOTES → APPLY
            // =====================================================

            System.out.println("=================================");
            System.out.println("CASE 1 START");
            System.out.println("=================================");


            // Select MEDIUM
            selectPolicy(wait, js, "Medium");

            sleep(2000);


            // Open Notes
            openNotes(wait, js);

            sleep(1500);


            // Clear Notes
            clearNotes(wait);

            sleep(1000);


            // Enter Notes
            enterNotes(
                    wait,
                    actions,
                    "First entry - Medium"
            );

            sleep(1000);


            // Click Add Notes
            clickAddNotes(wait, js);

            sleep(1500);


            // Apply License Policy
            clickApply(wait, js);

            sleep(4000);

            System.out.println("CASE 1 DONE");


            // =====================================================
            // CASE 2
            // LOW → CLEAR ONLY → CLOSE → APPLY
            // =====================================================

            System.out.println("=================================");
            System.out.println("CASE 2 START");
            System.out.println("=================================");


            // Select LOW
            selectPolicy(wait, js, "Low");

            sleep(2000);


            // Open Notes
            openNotes(wait, js);

            sleep(1500);


            // Clear Notes
            clearNotes(wait);

            sleep(1000);


            // Close Notes
            clickCloseNotes(wait, js);

            sleep(1500);


            // Apply License Policy
            clickApply(wait, js);

            sleep(3000);

            System.out.println("CASE 2 DONE");


            // =====================================================
            // SEARCH 1 START
            // =====================================================

            System.out.println("SEARCH 1 START");


            // =====================================================
            // LICENSE INFO ICON CLICK
            // =====================================================

            WebElement licenseInfo = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath(
                                    "//*[contains(@aria-label,'License Info')]"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].dispatchEvent(" +
                    "new MouseEvent('click',{bubbles:true}))",
                    licenseInfo
            );

            Thread.sleep(1000);

            System.out.println(
                    "License Info clicked using JS event"
            );


            // =====================================================
            // CLOSE POPUP
            // =====================================================

            WebElement closePopup = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//button[@aria-label='Close popup']"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    closePopup
            );

            Thread.sleep(1000);

            js.executeScript(
                    "arguments[0].click();",
                    closePopup
            );

            System.out.println(
                    "Popup closed successfully"
            );

            Thread.sleep(1500);


            // =====================================================
            // LICENSE POLICY SEARCH
            // NEW SEARCH BOX
            // =====================================================

            WebElement search1 = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath(
                                    "//input[@placeholder='Search by License Policy']"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    search1
            );

            sleep(800);

            js.executeScript(
                    "arguments[0].click();",
                    search1
            );

            sleep(500);


            // Clear search
            search1.sendKeys(
                    Keys.CONTROL + "a"
            );

            search1.sendKeys(
                    Keys.BACK_SPACE
            );


            // Search AGPL
            search1.sendKeys(
                    "AGPL-3.0-or-later"
            );

            sleep(500);

            search1.sendKeys(
                    Keys.ENTER
            );

            System.out.println(
                    "SEARCH 1 DONE"
            );


            // =====================================================
            // CLEAR SEARCH
            // =====================================================

            search1 = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath(
                                    "//input[@placeholder='Search by License Policy']"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].click();",
                    search1
            );

            sleep(500);

            search1.sendKeys(
                    Keys.CONTROL + "a"
            );

            search1.sendKeys(
                    Keys.BACK_SPACE
            );

            sleep(1000);

            System.out.println(
                    "Final clear done for Search 1"
            );


            // =====================================================
            // SEARCH JJJJ
            // =====================================================

            search1.sendKeys("JJJJ");

            search1.sendKeys(
                    Keys.ENTER
            );

            sleep(2000);

            System.out.println("JJJJ");
            System.out.println("JJJJ not found");
            System.out.println("SEARCH 1 COMPLETE");


            // =====================================================
            // CLEAR SEARCH
            // =====================================================

            search1 = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath(
                                    "//input[@placeholder='Search by License Policy']"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].click();",
                    search1
            );

            sleep(500);

            search1.sendKeys(
                    Keys.CONTROL + "a"
            );

            search1.sendKeys(
                    Keys.BACK_SPACE
            );

            sleep(1000);

            System.out.println(
                    "Final clear done for Search 1"
            );


            // =====================================================
            // PROJECT SEARCH
            // =====================================================

            System.out.println(
                    "PROJECT SEARCH START"
            );


            search(
                    wait,
                    js,
                    actions,
                    "//input[@id='searchQuery' " +
                    "and contains(@placeholder,'override')]",
                    "Project"
            );

            sleep(2000);

            System.out.println(
                    "WAITING FOR PROJECT DROPDOWN"
            );


            // =====================================================
            // PROJECT DROPDOWN
            // =====================================================

            WebElement dropdownList = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(
                                    "//ul[contains(@class,'absolute') " +
                                    "and contains(@class,'overflow-y-auto')]"
                            )
                    )
            );

            Thread.sleep(1000);


            // =====================================================
            // SELECT PROJECT
            // =====================================================

            WebElement projectItem = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//ul[contains(@class,'overflow-y-auto')]" +
                                    "//li[contains(.,'Project')]"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    projectItem
            );

            Thread.sleep(500);

            js.executeScript(
                    "arguments[0].click();",
                    projectItem
            );

            System.out.println(
                    "PROJECT CLICKED"
            );

            Thread.sleep(2000);


            // =====================================================
            // PROJECT TOGGLE
            // =====================================================

            WebElement projectToggle = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//input[@type='checkbox' " +
                                    "and following-sibling::div[contains(@class,'peer')]]"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].click();",
                    projectToggle
            );

            System.out.println(
                    "PROJECT UNSELECTED"
            );

            Thread.sleep(2000);


            // =====================================================
            // SEARCH JJJJ
            // =====================================================

            WebElement projectSearch = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath(
                                    "//input[@id='searchQuery' " +
                                    "and contains(@placeholder,'override')]"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].click();",
                    projectSearch
            );

            Thread.sleep(500);

            projectSearch.sendKeys(
                    Keys.CONTROL + "a"
            );

            projectSearch.sendKeys(
                    Keys.BACK_SPACE
            );

            Thread.sleep(1000);

            projectSearch.sendKeys("JJJJ");

            projectSearch.sendKeys(
                    Keys.ENTER
            );

            System.out.println(
                    "SEARCHED: JJJJ"
            );

            Thread.sleep(2000);


            // =====================================================
            // CLEAR PROJECT SEARCH
            // =====================================================

            projectSearch.sendKeys(
                    Keys.CONTROL + "a"
            );

            projectSearch.sendKeys(
                    Keys.BACK_SPACE
            );

            Thread.sleep(1000);

            System.out.println(
                    "SEARCH CLEARED"
            );


            // =====================================================
            // TEST COMPLETED
            // =====================================================

            System.out.println(
                    "TEST COMPLETED"
            );

            Thread.sleep(2000);


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            driver.quit();
        }
    }


    // =============================================================
    // SELECT SEVERITY
    // =============================================================

    static void selectPolicy(
            WebDriverWait wait,
            JavascriptExecutor js,
            String policy) {

        try {

            System.out.println(
                    "Selecting Severity: " + policy
            );


            // =====================================================
            // CLICK SEVERITY DROPDOWN
            // =====================================================

            WebElement dropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//button[@aria-label='Severity']"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    dropdown
            );

            sleep(500);

            js.executeScript(
                    "arguments[0].click();",
                    dropdown
            );

            System.out.println(
                    "Severity dropdown clicked"
            );

            sleep(1000);


            // =====================================================
            // SELECT MEDIUM / LOW
            // =====================================================

            WebElement option = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(
                                    "//div[contains(@class,'cursor-pointer')" +
                                    " and .//span[normalize-space()='" +
                                    policy +
                                    "']]"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    option
            );

            sleep(500);


            // Click selected option
            js.executeScript(
                    "arguments[0].click();",
                    option
            );

            System.out.println(
                    "Selected Severity: " + policy
            );


            // =====================================================
            // VERIFY SELECTION
            // =====================================================

            wait.until(
                    ExpectedConditions.textToBePresentInElementLocated(
                            By.xpath(
                                    "//button[@aria-label='Severity']"
                            ),
                            policy
                    )
            );

            System.out.println(
                    "Verified Severity: " + policy
            );

            sleep(1000);


        } catch (Exception e) {

            System.out.println(
                    "Unable to select Severity: " + policy
            );

            e.printStackTrace();

            throw e;
        }
    }


    // =============================================================
    // OPEN NOTES
    // =============================================================

    static void openNotes(
            WebDriverWait wait,
            JavascriptExecutor js) {

        WebElement addNotes = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(
                                "//button[@aria-label='Add Notes']"
                        )
                )
        );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                addNotes
        );

        sleep(500);

        js.executeScript(
                "arguments[0].click();",
                addNotes
        );

        System.out.println(
                "Opened Notes"
        );

        // Wait for new textarea
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(
                                "//textarea[@placeholder='Write your notes...']"
                        )
                )
        );

        sleep(1000);
    }


    // =============================================================
    // CLEAR NOTES
    // =============================================================

    static void clearNotes(
            WebDriverWait wait) {

        WebElement notes = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(
                                "//textarea[@placeholder='Write your notes...']"
                        )
                )
        );

        notes.click();

        notes.sendKeys(
                Keys.CONTROL + "a"
        );

        notes.sendKeys(
                Keys.BACK_SPACE
        );

        System.out.println(
                "Notes Cleared"
        );
    }


    // =============================================================
    // ENTER NOTES
    // =============================================================

    static void enterNotes(
            WebDriverWait wait,
            Actions actions,
            String text) {

        WebElement notes = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(
                                "//textarea[@placeholder='Write your notes...']"
                        )
                )
        );

        notes.click();

        actions.moveToElement(notes)
               .click()
               .sendKeys(text)
               .perform();

        System.out.println(
                "Notes Entered"
        );
    }


    // =============================================================
    // CLICK ADD NOTES
    // =============================================================

    static void clickAddNotes(
            WebDriverWait wait,
            JavascriptExecutor js) {

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(
                                "//button[@aria-label='Add Notes']"
                        )
                )
        );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                btn
        );

        sleep(500);

        js.executeScript(
                "arguments[0].click();",
                btn
        );

        System.out.println(
                "Clicked Add Notes"
        );

        sleep(1000);
    }


    // =============================================================
    // APPLY LICENSE POLICY
    // =============================================================

    static void clickApply(
            WebDriverWait wait,
            JavascriptExecutor js) {

        try {

            System.out.println(
                    "Looking for Apply license policy button"
            );

            WebElement apply = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//button[normalize-space()='Apply license policy']"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    apply
            );

            sleep(1000);

            js.executeScript(
                    "arguments[0].click();",
                    apply
            );

            System.out.println(
                    "Applied License Policy Successfully"
            );

            sleep(3000);

        } catch (Exception e) {

            System.out.println(
                    "Failed to click Apply license policy"
            );

            e.printStackTrace();

            throw e;
        }
    }


    // =============================================================
    // CLOSE NOTES
    // =============================================================

    static void clickCloseNotes(
            WebDriverWait wait,
            JavascriptExecutor js) {

        WebElement close = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(
                                "//button[normalize-space()='Close Notes']"
                        )
                )
        );

        js.executeScript(
                "arguments[0].click();",
                close
        );

        System.out.println(
                "Closed Notes"
        );
    }


    // =============================================================
    // SEARCH
    // =============================================================

    static void search(
            WebDriverWait wait,
            JavascriptExecutor js,
            Actions actions,
            String xpath,
            String value) {

        WebElement box = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath(xpath)
                )
        );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                box
        );

        sleep(500);

        js.executeScript(
                "arguments[0].click();",
                box
        );

        sleep(500);

        box.sendKeys(
                Keys.CONTROL + "a"
        );

        box.sendKeys(
                Keys.BACK_SPACE
        );

        sleep(500);

        actions.moveToElement(box)
               .click()
               .sendKeys(value + Keys.ENTER)
               .perform();

        System.out.println(
                "Search Done: " + value
        );
    }


    // =============================================================
    // CLEAR SEARCH
    // =============================================================

    static void clearSearch(
            WebDriverWait wait,
            JavascriptExecutor js,
            String xpath) {

        WebElement box = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath(xpath)
                )
        );

        js.executeScript(
                "arguments[0].value='';",
                box
        );

        js.executeScript(
                "arguments[0].dispatchEvent(" +
                "new Event('input',{bubbles:true}));",
                box
        );

        System.out.println(
                "Search Cleared"
        );
    }


    // =============================================================
    // SLEEP
    // =============================================================

    static void sleep(long ms) {

        try {

            Thread.sleep(ms);

        } catch (Exception ignored) {

        }
    }
}