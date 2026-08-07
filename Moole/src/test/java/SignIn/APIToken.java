package SignIn;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.ConfigReader;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

public class APIToken {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
        	driver.get(ConfigReader.getProperty("baseUrl"));
            driver.manage().window().maximize();

            // ---------------- LOGIN ----------------
            driver.get("https://moole.ai/auth/signin");
            driver.manage().window().maximize();
            Thread.sleep(3000);

            WebElement emailField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']"))
            );
            emailField.sendKeys("moole.dev.2@gmail.com");

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

            // ---------------- OPEN API TOKEN PAGE ----------------
            driver.get("https://moole.ai/app/settings/developer/api-token");
            Thread.sleep(4000);

            // ================= STEP 1: GENERATE ORG TOKEN =================
            generateToken(driver, wait, js, "BITBUCKET", "Organization");

            // ================= STEP 2: DELETE ORG TOKEN =================
            deleteToken(driver, wait, js, "BITBUCKET");

            // Ensure we're back on a clean API Token page before starting
            // the Project token flow.
            driver.get("https://moole.ai/app/settings/developer/api-token");
            Thread.sleep(3000);

            // ================= STEP 3: GENERATE PROJECT TOKEN =================
            // (generateToken() already clicks "Done" internally once the
            // token is generated)
            generateToken(driver, wait, js, "BITBUCKET", "Project");

            // ================= STEP 4: OPEN ACTIONS MENU -> DELETE TOKEN -> CANCEL =================
            clickDeleteOption(driver, wait, js);

            WebElement cancelBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@type='button' and contains(.,'Cancel')]")
                    )
            );

            js.executeScript("arguments[0].click();", cancelBtn);
            System.out.println("Clicked Cancel on Project token delete confirmation");

            Thread.sleep(2000);

            // ================= STEP 5: OPEN ACTIONS MENU AGAIN -> REGENERATE TOKEN -> CONFIRM =================
            clickRegenerateOption(driver, wait, js);

            Thread.sleep(1500);

            WebElement confirmRegen = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@type='submit']//span[text()='Regenerate']")
                    )
            );

            confirmRegen.click();
            System.out.println("Token regeneration started...");

            Thread.sleep(2000);

            System.out.println("ALL FLOWS COMPLETED SUCCESSFULLY");

        } catch (Exception e) {
            System.out.println("TEST FAILED");
            e.printStackTrace();
        } finally {
            driver.quit();
            System.out.println("Browser Closed");
        }
    }

    // ================= HIGHLIGHT =================
    public static void highlight(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='4px solid red';", element);
    }

    // ================= GENERATE TOKEN =================
    public static void generateToken(WebDriver driver,
                                     WebDriverWait wait,
                                     JavascriptExecutor js,
                                     String tokenNameText,
                                     String type) throws InterruptedException {

        // Token name field - confirmed exact markup: name="tokenName"
        WebElement tokenName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[contains(@name,'tokenName')]")
                )
        );

        tokenName.clear();
        tokenName.sendKeys(tokenNameText);

        // -------- ORGANIZATION ----------
        if (type.equalsIgnoreCase("Organization")) {

            WebElement orgBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[.//span[text()='Organization-wide']]")
                    )
            );

            js.executeScript("arguments[0].click();", orgBtn);
        }

        // -------- PROJECT ----------
        // Confirmed exact markup: <button>...<span>Project-wide</span></button>
        
     // ================= PROJECT =================
        if (type.equalsIgnoreCase("Project")) {

            try {
                Thread.sleep(2000); // IMPORTANT WAIT FOR UI SWITCH

                WebElement projectBtn = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[.//span[text()='Project-wide']]")
                        )
                );

                js.executeScript("arguments[0].scrollIntoView(true);", projectBtn);
                Thread.sleep(1000); // 👈 extra stability
                js.executeScript("arguments[0].click();", projectBtn);

                System.out.println("Selected Project-wide");

            } catch (Exception e) {
                System.out.println("Project-wide button not found");
            }

            // 👉 EXTRA WAIT FOR UI TO LOAD AFTER CLICK
            Thread.sleep(3000);

            // ================= PROJECT SELECTION (IMPORTANT WAIT) =================
            try {
                Thread.sleep(2000); // 👈 give dropdown time to render

                WebElement projectChevron = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[contains(@class,'cursor-pointer') or contains(@class,'flex')]")
                        )
                );

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", projectChevron);
                Thread.sleep(1000);
                js.executeScript("arguments[0].click();", projectChevron);

                System.out.println("Project selected");

                Thread.sleep(2000); // 👈 IMPORTANT

            } catch (Exception e) {
                System.out.println("Project selection not found: " + e.getMessage());
            }

            // ================= EXPIRY =================
         // ================= EXPIRY =================
            try {

                WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(10));

                // 1. OPEN EXPIRY DROPDOWN
                WebElement expiryDropdown = shortWait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[.//span[contains(text(),'days')]]")
                        )
                );

                expiryDropdown.click();
                System.out.println("Expiry dropdown opened");

                Thread.sleep(1000);

                // 2. SELECT 60 DAYS
                WebElement sixtyDaysOption = shortWait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//*[contains(text(),'60 days')]")
                        )
                );

                sixtyDaysOption.click();
                System.out.println("60 days selected");

                Thread.sleep(1500);

            } catch (Exception e) {
                System.out.println("Expiry selection failed: " + e.getMessage());
            }

            // 🔴 IMPORTANT FIX: CLOSE PROJECT BLOCK HERE
            }
        //----- GENERATE ----------
        String[] generateCandidates = {
                "//button[contains(@class,'bg-gradient') and contains(.,'Generate')]",
                "//button[contains(.,'Generate')]",
                "//button[@type='submit' and contains(.,'Generate')]"
        };

        WebElement generateBtn = null;
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        for (String xpath : generateCandidates) {
            try {
                generateBtn = shortWait.until(
                        ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                if (generateBtn != null) {
                    System.out.println("Generate button matched using: " + xpath);
                    break;
                }
            } catch (Exception ignored) {
                // try next candidate
            }
        }

        if (generateBtn == null) {

            // No Generate button - means a token of this type already exists
            // from a previous run (the page shows "Done"/"Show token" instead
            // of the create-form). Click Done directly instead of crashing.
            try {
                WebElement existingDone = driver.findElement(
                        By.xpath("//button[normalize-space()='Done']"));
                if (existingDone != null && existingDone.isDisplayed()) {
                    System.out.println("A " + type + " token already exists from a previous run - " +
                            "skipping Generate, clicking Done directly.");
                    js.executeScript("arguments[0].click();", existingDone);
                    return;
                }
            } catch (Exception ignored) {
                // no existing Done button either - genuinely missing, fall through to debug dump
            }

            dumpButtons(js, "Generate button not found for type: " + type);
            throw new RuntimeException("Generate button not found for type: " + type);
        }

        js.executeScript("arguments[0].click();", generateBtn);

        System.out.println("Token Generated Successfully");

        Thread.sleep(3000);

        // CLICK DONE
        String[] doneCandidates = {
                "//button[normalize-space()='Done']",
                "//button[contains(.,'Done')]",
                "//button[@type='button' and contains(.,'Done')]"
        };

        WebElement doneBtn = null;
        WebDriverWait shortWaitDone = new WebDriverWait(driver, Duration.ofSeconds(5));

        for (String xpath : doneCandidates) {
            try {
                doneBtn = shortWaitDone.until(
                        ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                if (doneBtn != null) {
                    System.out.println("Done button matched using: " + xpath);
                    break;
                }
            } catch (Exception ignored) {
                // try next candidate
            }
        }

        if (doneBtn == null) {
            dumpButtons(js, "Done button not found for type: " + type);
            throw new RuntimeException("Done button not found for type: " + type);
        }

        js.executeScript("arguments[0].click();", doneBtn);

        System.out.println("Done clicked");
    }

    // ================= OPEN ACTIONS MENU (the 3-dot menu) =================
    // Confirmed exact markup: aria-label="Open actions menu"
    public static void openActionsMenu(WebDriver driver,
                                       WebDriverWait wait,
                                       JavascriptExecutor js) {

        String[] threeDotsCandidates = {
                "//button[@aria-label='Open actions menu']",
                "//button[@aria-label='More options']",
                "//button[@aria-label='Options']",
                "//button[@aria-label='Token options']",
                "//button[contains(@aria-label,'options')]",
                "//button[contains(@aria-label,'menu')]",
                "//button[contains(@aria-label,'More')]"
        };

        WebElement threeDots = null;
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        for (String xpath : threeDotsCandidates) {
            try {
                threeDots = shortWait.until(
                        ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                if (threeDots != null) {
                    System.out.println("Three-dots menu matched using: " + xpath);
                    break;
                }
            } catch (Exception ignored) {
                // try next candidate
            }
        }

        if (threeDots == null) {
            dumpButtons(js, "Three-dots actions menu button not found");
            throw new RuntimeException("Three-dots actions menu button not found");
        }

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", threeDots);
        try {
            Thread.sleep(300);
        } catch (InterruptedException ignored) {
        }

        // FIX: a JS-executed click (arguments[0].click()) can misfire this
        // dropdown component's "click outside" detection and cause the menu
        // to open and then immediately auto-close again - which is exactly
        // why the menu items kept showing as "not visible" even after a
        // 20s wait. A real WebDriver click behaves like an actual user
        // click and avoids that.
        try {
            threeDots.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", threeDots);
        }

        System.out.println("Opened token options menu");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }

    // ================= CLICK "DELETE TOKEN" MENU OPTION =================
    // Confirmed exact markup: <button role="menuitem">...<span>Delete Token</span>...</button>
    public static void clickDeleteOption(WebDriver driver,
                                         WebDriverWait wait,
                                         JavascriptExecutor js) {

        openActionsMenu(driver, wait, js);

        WebElement deleteBtn;
        try {
            deleteBtn = wait.until(d -> {
                List<WebElement> matches = d.findElements(
                        By.xpath("//button[@role='menuitem' and contains(.,'Delete Token')]"));
                for (WebElement el : matches) {
                    if (el.isDisplayed()) {
                        return el;
                    }
                }
                return null;
            });
        } catch (Exception e) {
            dumpButtons(js, "Delete Token menu item not found/visible");
            throw e;
        }

        js.executeScript("arguments[0].click();", deleteBtn);
        System.out.println("Clicked Delete Token option");
    }

    // ================= CLICK "REGENERATE TOKEN" MENU OPTION =================
    // Confirmed exact markup: <button role="menuitem">...<span>Regenerate Token</span>...</button>
    public static void clickRegenerateOption(WebDriver driver,
                                             WebDriverWait wait,
                                             JavascriptExecutor js) {

        openActionsMenu(driver, wait, js);

        WebElement regenerateBtn;
        try {
            regenerateBtn = wait.until(d -> {
                List<WebElement> matches = d.findElements(
                        By.xpath("//button[@role='menuitem' and contains(.,'Regenerate Token')]"));
                for (WebElement el : matches) {
                    if (el.isDisplayed()) {
                        return el;
                    }
                }
                return null;
            });
        } catch (Exception e) {
            dumpButtons(js, "Regenerate Token menu item not found/visible");
            throw e;
        }

        js.executeScript("arguments[0].click();", regenerateBtn);
        System.out.println("Clicked Regenerate Token option");
    }

    // ================= FULL DELETE FLOW (open menu -> Delete -> type name -> Remove) =================
    public static void deleteToken(WebDriver driver,
                                   WebDriverWait wait,
                                   JavascriptExecutor js,
                                   String tokenNameText) {

        clickDeleteOption(driver, wait, js);

        WebElement deleteInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text']"))
        );

        deleteInput.sendKeys(tokenNameText);

        WebElement removeBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@type='submit' and .//span[text()='Remove']]")
                )
        );

        js.executeScript("arguments[0].click();", removeBtn);

        System.out.println("Deleted Token");
    }

    // ================= DEBUG HELPER =================
    public static void dumpButtons(JavascriptExecutor js, String reason) {
        System.out.println(reason);
        try {
            Object buttonsHtml = js.executeScript(
                    "var btns = document.querySelectorAll('button');" +
                    "var result = [];" +
                    "btns.forEach(function(el){ result.push(el.outerHTML); });" +
                    "return result.length ? result.join('\\n---\\n') : '(no buttons found on page at all)';"
            );
            System.out.println("DEBUG - buttons on page:\n" + buttonsHtml);
        } catch (Exception ex) {
            System.out.println("DEBUG dump failed: " + ex.getMessage());
        }
    }
}