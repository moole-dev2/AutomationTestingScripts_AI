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
import java.util.Scanner;

public class APIToken {

    public static void main(String[] args) {

        // =====================================================
        // CHROME SETUP
        // =====================================================

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--start-maximized");

        options.addArguments("--remote-allow-origins=*");

        options.addArguments("--disable-notifications");

        options.addArguments("--disable-popup-blocking");

        options.addArguments("--disable-dev-shm-usage");

        options.addArguments("--no-sandbox");

        // Dedicated Selenium Chrome Profile
        options.addArguments(
                "user-data-dir=C:\\SeleniumChromeProfile"
        );

        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(30));

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        try {

            // =====================================================
            // OPEN LOGIN PAGE
            // =====================================================

            driver.get("https://moole.ai/auth/signin");

            Thread.sleep(3000);

            // =====================================================
            // EMAIL FIELD
            // =====================================================

            WebElement emailField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@type='email']")
                    )
            );

            highlight(driver, emailField);

            emailField.sendKeys("moole.dev.2@gmail.com");

            // =====================================================
            // CONTINUE BUTTON
            // =====================================================

            WebElement continueBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(.,'Continue')]")
                    )
            );

            highlight(driver, continueBtn);

            js.executeScript("arguments[0].click();", continueBtn);

            // =====================================================
            // MANUAL OTP
            // =====================================================

            System.out.println(
                    "Enter OTP manually in browser and press ENTER");

            Scanner scanner = new Scanner(System.in);

            scanner.nextLine();

            Thread.sleep(3000);

            // =====================================================
            // OPEN API TOKEN PAGE
            // =====================================================

            driver.get(
                    "https://moole.ai/app/settings/developer/api-token"
            );

            Thread.sleep(5000);

            // =====================================================
            // 1. GENERATE TOKEN
            // =====================================================

            generateToken(
                    driver,
                    wait,
                    js,
                    "BITBUCKET",
                    "Organization"
            );

            // =====================================================
            // 2. DELETE TOKEN
            // =====================================================

            deleteToken(
                    driver,
                    wait,
                    js,
                    "BITBUCKET"
            );

            // =====================================================
            // 3. GENERATE AGAIN
            // =====================================================

            generateToken(
                    driver,
                    wait,
                    js,
                    "BITBUCKET",
                    "Organization"
            );

            // =====================================================
            // 4. REGENERATE TOKEN
            // =====================================================

            regenerateToken(
                    driver,
                    wait,
                    js
            );

            // =====================================================
            // 5. CLICK DELETE
            // =====================================================

            clickDelete(
                    driver,
                    wait,
                    js
            );

            // =====================================================
            // ENTER DELETE INPUT
            // =====================================================

            WebElement deleteInput = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@type='text']")
                    )
            );

            highlight(driver, deleteInput);

            deleteInput.sendKeys("BITBUCKET");

            Thread.sleep(2000);

            // =====================================================
            // CLICK CANCEL
            // =====================================================

            WebElement cancelBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//button[@type='button' and contains(.,'Cancel')]"
                            )
                    )
            );

            highlight(driver, cancelBtn);

            js.executeScript("arguments[0].click();", cancelBtn);

            System.out.println("Clicked Cancel");

            Thread.sleep(3000);

            // =====================================================
            // CLICK DELETE AGAIN
            // =====================================================

            clickDelete(
                    driver,
                    wait,
                    js
            );

            Thread.sleep(2000);

            // =====================================================
            // CLOSE POPUP
            // =====================================================

            WebElement closePopup = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Close popup']")
                    )
            );

            highlight(driver, closePopup);

            js.executeScript("arguments[0].click();", closePopup);

            System.out.println("Closed Popup");

            Thread.sleep(5000);

        } catch (Exception e) {

            System.out.println("TEST FAILED");

            e.printStackTrace();

        } finally {

            driver.quit();

            System.out.println("Browser Closed");
        }
    }

    // =====================================================
    // HIGHLIGHT ELEMENT
    // =====================================================

    public static void highlight(WebDriver driver,
                                 WebElement element) {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].style.border='4px solid red';" +
                        "arguments[0].style.background='yellow';",
                element
        );
    }

    // =====================================================
    // GENERATE TOKEN
    // =====================================================

    public static void generateToken(WebDriver driver,
                                     WebDriverWait wait,
                                     JavascriptExecutor js,
                                     String tokenNameText,
                                     String type)
            throws InterruptedException {

        // =====================================================
        // TOKEN NAME
        // =====================================================

        WebElement tokenName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@name='tokenName']")
                )
        );

        tokenName.clear();

        highlight(driver, tokenName);

        tokenName.sendKeys(tokenNameText);

        Thread.sleep(2000);

        // =====================================================
        // WAIT FOR OVERLAY
        // =====================================================

        try {

            wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(
                            By.xpath(
                                    "//div[contains(@class,'backdrop-blur')]"
                            )
                    )
            );

        } catch (Exception e) {

            System.out.println("Overlay still visible");
        }

        // =====================================================
        // CHOOSE TYPE
        // =====================================================

        WebElement chooseType = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath(
                                "//button[.//span[contains(text(),'Choose Type')]]"
                        )
                )
        );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                chooseType
        );

        Thread.sleep(1000);

        highlight(driver, chooseType);

        js.executeScript("arguments[0].click();", chooseType);

        Thread.sleep(2000);

        // =====================================================
        // SELECT TYPE
        // =====================================================

        WebElement typeOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[text()='" + type + "']")
                )
        );

        highlight(driver, typeOption);

        js.executeScript("arguments[0].click();", typeOption);

        Thread.sleep(2000);

        // =====================================================
        // ORGANIZATION FLOW
        // =====================================================

        if (type.equalsIgnoreCase("Organization")) {

            WebElement orgDropdown = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath(
                                    "//button[.//span[contains(text(),'Choose organization')]]"
                            )
                    )
            );

            highlight(driver, orgDropdown);

            js.executeScript("arguments[0].click();", orgDropdown);

            Thread.sleep(2000);

            WebElement selectOrg = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath(
                                    "//div[contains(text(),'Milky Way-Barnards Star1205')]"
                            )
                    )
            );

            highlight(driver, selectOrg);

            js.executeScript("arguments[0].click();", selectOrg);

            Thread.sleep(2000);
        }

        // =====================================================
        // PROJECT FLOW
        // =====================================================

        if (type.equalsIgnoreCase("Project")) {

            WebElement projectDropdown = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath(
                                    "//button[contains(.,'Choose project')]"
                            )
                    )
            );

            highlight(driver, projectDropdown);

            js.executeScript("arguments[0].click();", projectDropdown);

            Thread.sleep(2000);

            WebElement selectProject = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath(
                                    "//div[contains(text(),'BITBUCKET')]"
                            )
                    )
            );

            highlight(driver, selectProject);

            js.executeScript("arguments[0].click();", selectProject);

            Thread.sleep(2000);
        }

        // =====================================================
        // GENERATE BUTTON
        // =====================================================

        WebElement generateBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath(
                                "//button[@type='submit' and contains(.,'Generate')]"
                        )
                )
        );

        highlight(driver, generateBtn);

        js.executeScript("arguments[0].click();", generateBtn);

        System.out.println("Generated Token");

        Thread.sleep(5000);
    }

    // =====================================================
    // CLICK DELETE
    // =====================================================

    public static void clickDelete(WebDriver driver,
                                   WebDriverWait wait,
                                   JavascriptExecutor js)
            throws InterruptedException {

        WebElement deleteBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[@aria-label='Delete Token']")
                )
        );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                deleteBtn
        );

        Thread.sleep(1000);

        highlight(driver, deleteBtn);

        js.executeScript("arguments[0].click();", deleteBtn);

        System.out.println("Clicked Delete");

        Thread.sleep(3000);
    }

    // =====================================================
    // DELETE TOKEN
    // =====================================================

    public static void deleteToken(WebDriver driver,
                                   WebDriverWait wait,
                                   JavascriptExecutor js,
                                   String tokenNameText)
            throws InterruptedException {

        clickDelete(driver, wait, js);

        WebElement deleteInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@type='text']")
                )
        );

        highlight(driver, deleteInput);

        deleteInput.sendKeys(tokenNameText);

        Thread.sleep(2000);

        WebElement removeBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath(
                                "//button[@type='submit' and .//span[text()='Remove']]"
                        )
                )
        );

        highlight(driver, removeBtn);

        js.executeScript("arguments[0].click();", removeBtn);

        System.out.println("Deleted Token");

        Thread.sleep(5000);
    }

    // =====================================================
    // REGENERATE TOKEN
    // =====================================================

    public static void regenerateToken(WebDriver driver,
                                       WebDriverWait wait,
                                       JavascriptExecutor js)
            throws InterruptedException {

        WebElement regenerateBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath(
                                "//button[@aria-label='Regenerate Token']"
                        )
                )
        );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                regenerateBtn
        );

        Thread.sleep(1000);

        highlight(driver, regenerateBtn);

        js.executeScript("arguments[0].click();", regenerateBtn);

        System.out.println("Regenerated Token");

        Thread.sleep(5000);
    }
}