package SignIn;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.ConfigReader;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import org.testng.annotations.Test;

public class BillingN {

    @Test
    public void BillingNTest() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(40));

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        try {

            // =====================================================
            // BROWSER SETUP
            // =====================================================

            driver.manage().window().maximize();

            driver.get(ConfigReader.getProperty("baseUrl"));

            Thread.sleep(2000);

            // =====================================================
            // LOGIN
            // =====================================================

            driver.get("https://moole.ai/auth/signin");

            Thread.sleep(4000);

            WebElement email = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@type='email']")
                    )
            );

            email.clear();
            email.sendKeys("moole.dev.2@gmail.com");

            WebElement signIn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@data-tour='signup-submit']")
                    )
            );

            signIn.click();

            System.out.println("Sign in button clicked");

            // =====================================================
            // MANUAL OTP
            // =====================================================

            System.out.println("Enter OTP manually...");

            new Scanner(System.in).nextLine();

            Thread.sleep(3000);

            // =====================================================
            // GO TO INTEGRATIONS
            // =====================================================

            driver.get(
                    "https://moole.ai/app/settings/project/integrations"
            );

            Thread.sleep(4000);

            // =====================================================
            // ACTIVE PLAN
            // =====================================================

            WebElement activePlan = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[contains(@href,'plan-details')]")
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    activePlan
            );

            Thread.sleep(1000);

            activePlan.click();

            System.out.println("Clicked Active Plan");

            Thread.sleep(4000);

            // =====================================================
            // BILLING ACTIVITY
            // =====================================================

            WebElement billingActivity = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//button[.//span[normalize-space()='Billing Activity']]"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    billingActivity
            );

            Thread.sleep(1000);

            js.executeScript(
                    "arguments[0].click();",
                    billingActivity
            );

            System.out.println("Billing Activity clicked");

            Thread.sleep(3000);

            // =====================================================
            // ADD YOUR FIRST CARD
            // =====================================================

            WebElement addCard = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//button[normalize-space()='Add Your First Card']"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    addCard
            );

            Thread.sleep(1000);

            js.executeScript(
                    "arguments[0].click();",
                    addCard
            );

            System.out.println("Clicked Add Your First Card");

            Thread.sleep(4000);

            // =====================================================
            // CARD NUMBER
            // =====================================================

            fillStripeField(
                    driver,
                    By.id("payment-numberInput"),
                    "5555555555554444",
                    "Card Number"
            );

            // =====================================================
            // EXPIRY
            // =====================================================

            fillStripeField(
                    driver,
                    By.name("expiry"),
                    "07/31",
                    "Expiry"
            );

            // =====================================================
            // CVC
            // =====================================================

            fillStripeField(
                    driver,
                    By.name("cvc"),
                    "270",
                    "CVC"
            );

            // =====================================================
            // EMAIL
            // =====================================================

            fillStripeField(
                    driver,
                    By.id("payment-linkEmailInput"),
                    "moole@testing.com",
                    "Email"
            );

            // =====================================================
            // PHONE
            // =====================================================

            fillStripeField(
                    driver,
                    By.name("linkMobilePhone"),
                    "2015550123",
                    "Phone"
            );

            // =====================================================
            // NAME
            // =====================================================

            fillPaymentField(
                    driver,
                    By.xpath(
                            "//input[contains(@autocomplete,'name') or @name='name']"
                    ),
                    "John Cena",
                    "Name"
            );

            // =====================================================
            // ADDRESS
            // =====================================================

            System.out.println();
            System.out.println("==========================================");
            System.out.println("ENTERING BILLING ADDRESS");
            System.out.println("==========================================");

            WebElement addressField =
                    findAddressField(driver);

            if (addressField == null) {

                throw new RuntimeException(
                        "Billing address field was NOT FOUND"
                );
            }

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    addressField
            );

            Thread.sleep(1000);

            // Make sure we are interacting with the real address input
            try {
                addressField.click();
            } catch (Exception e) {
                js.executeScript(
                        "arguments[0].click();",
                        addressField
                );
            }

            // Clear existing value
            addressField.sendKeys(
                    Keys.CONTROL,
                    "a"
            );

            addressField.sendKeys(
                    Keys.BACK_SPACE
            );

            Thread.sleep(500);

            // Type address slowly
            String address = "1125 Miller Lane";

            for (char c : address.toCharArray()) {

                addressField.sendKeys(
                        String.valueOf(c)
                );

                Thread.sleep(80);
            }

            System.out.println(
                    "Address typed: " +
                    addressField.getAttribute("value")
            );

            // =====================================================
            // WAIT FOR AUTOCOMPLETE
            // =====================================================

            System.out.println(
                    "Waiting for address suggestions..."
            );

            Thread.sleep(3000);

            // =====================================================
            // SELECT ADDRESS
            // =====================================================

            selectAddressSuggestion(
                    driver,
                    addressField,
                    "1125 Miller Lane"
            );

            // =====================================================
            // VERIFY ADDRESS SELECTION
            // =====================================================

            Thread.sleep(2000);

            String selectedAddress =
                    addressField.getAttribute("value");

            System.out.println(
                    "Address field after selection: "
                            + selectedAddress
            );

            if (selectedAddress == null ||
                    !selectedAddress
                            .toLowerCase()
                            .contains("1125 miller lane")) {

                throw new RuntimeException(
                        "Address suggestion was NOT selected. " +
                        "Current address value: " +
                        selectedAddress
                );
            }

            System.out.println(
                    "Address suggestion selected successfully"
            );

            // =====================================================
            // WAIT FOR ADDRESS AUTOCOMPLETE
            // =====================================================

            Thread.sleep(3000);

            // =====================================================
            // CITY
            // =====================================================

            handleCity(driver);

            // =====================================================
            // STATE
            // =====================================================

            handleState(driver);

            // =====================================================
            // ZIP
            // =====================================================

            handleZip(driver);

            // =====================================================
            // SAVE
            // =====================================================

            System.out.println(
                    "Looking for Save button..."
            );

            WebElement save = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//button[contains(normalize-space(.),'Save')]"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    save
            );

            Thread.sleep(1000);

            js.executeScript(
                    "arguments[0].click();",
                    save
            );

            System.out.println("Save Clicked");

            Thread.sleep(5000);

            // =====================================================
            // TRANSACTION ACTIVITY
            // =====================================================

            WebElement transaction = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//button[.//span[contains(normalize-space(),'Transaction Activity')]]"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    transaction
            );

            Thread.sleep(1000);

            js.executeScript(
                    "arguments[0].click();",
                    transaction
            );

            System.out.println(
                    "Transaction Activity Clicked"
            );

            Thread.sleep(3000);

            // =====================================================
            // DOWNLOAD INVOICE
            // =====================================================

            WebElement invoice = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//a[@aria-label='Download Invoice']"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].click();",
                    invoice
            );

            System.out.println(
                    "Invoice Downloaded"
            );

            Thread.sleep(2000);

            // =====================================================
            // DOWNLOAD RECEIPT
            // =====================================================

            WebElement receipt = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//a[@aria-label='Download Receipt']"
                            )
                    )
            );

            js.executeScript(
                    "arguments[0].click();",
                    receipt
            );

            System.out.println(
                    "Receipt Downloaded"
            );

            System.out.println();
            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "Automation Completed Successfully"
            );

            System.out.println(
                    "=========================================="
            );

        } catch (Exception e) {

            System.out.println();
            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "ERROR: " + e.getMessage()
            );

            System.out.println(
                    "=========================================="
            );

            e.printStackTrace();

            throw new RuntimeException(
                    "Billing automation failed",
                    e
            );

        } finally {

            System.out.println(
                    "Browser Closed"
            );

            // driver.quit();
        }
    }


    // =========================================================
    // FIND ADDRESS FIELD
    // =========================================================

    public static WebElement findAddressField(
            WebDriver driver)
            throws InterruptedException {

        driver.switchTo().defaultContent();

        By addressLocator =
                By.id("billingAddress-addressLine1Input");

        // First search main document
        List<WebElement> elements =
                driver.findElements(addressLocator);

        for (WebElement element : elements) {

            try {

                if (element.isDisplayed()) {

                    System.out.println(
                            "Address field found in main document"
                    );

                    return element;
                }

            } catch (Exception ignored) {
            }
        }

        // Search iframes
        return findAddressInFrames(
                driver,
                addressLocator,
                0
        );
    }


    // =========================================================
    // FIND ADDRESS INSIDE IFRAMES
    // =========================================================

    public static WebElement findAddressInFrames(
            WebDriver driver,
            By locator,
            int depth)
            throws InterruptedException {

        if (depth > 5) {
            return null;
        }

        List<WebElement> elements =
                driver.findElements(locator);

        for (WebElement element : elements) {

            try {

                if (element.isDisplayed()) {

                    System.out.println(
                            "Address field found at iframe depth "
                                    + depth
                    );

                    return element;
                }

            } catch (Exception ignored) {
            }
        }

        List<WebElement> frames =
                driver.findElements(
                        By.tagName("iframe")
                );

        for (int i = 0; i < frames.size(); i++) {

            try {

                List<WebElement> currentFrames =
                        driver.findElements(
                                By.tagName("iframe")
                        );

                if (i >= currentFrames.size()) {
                    continue;
                }

                driver.switchTo().frame(
                        currentFrames.get(i)
                );

                WebElement result =
                        findAddressInFrames(
                                driver,
                                locator,
                                depth + 1
                        );

                if (result != null) {
                    return result;
                }

                driver.switchTo().parentFrame();

            } catch (Exception ignored) {

                try {
                    driver.switchTo().parentFrame();
                } catch (Exception ignored2) {
                }
            }
        }

        return null;
    }


    // =========================================================
    // SELECT ADDRESS SUGGESTION
    // =========================================================

    public static void selectAddressSuggestion(
            WebDriver driver,
            WebElement addressField,
            String expectedAddress)
            throws InterruptedException {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        System.out.println();
        System.out.println(
                "Searching for address dropdown..."
        );

        String expected =
                expectedAddress
                        .toLowerCase()
                        .trim();

        // =====================================================
        // STEP 1 - WAIT UNTIL aria-expanded = true
        // =====================================================

        for (int i = 0; i < 20; i++) {

            try {

                String expanded =
                        addressField.getAttribute(
                                "aria-expanded"
                        );

                System.out.println(
                        "Address dropdown expanded = "
                                + expanded
                );

                if ("true".equalsIgnoreCase(expanded)) {
                    break;
                }

            } catch (Exception ignored) {
            }

            Thread.sleep(500);
        }

        // =====================================================
        // STEP 2 - GET AUTOCOMPLETE CONTAINER
        // =====================================================

        String controls =
                addressField.getAttribute(
                        "aria-controls"
                );

        System.out.println(
                "Address aria-controls = "
                        + controls
        );

        // =====================================================
        // STEP 3 - SEARCH AUTOCOMPLETE CONTAINER
        // =====================================================

        for (int attempt = 0; attempt < 20; attempt++) {

            try {

                driver.switchTo().defaultContent();

                // ---------------------------------------------
                // First: exact aria-controls container
                // ---------------------------------------------

                if (controls != null &&
                        !controls.trim().isEmpty()) {

                    List<WebElement> containers =
                            driver.findElements(
                                    By.id(controls)
                            );

                    System.out.println(
                            "autocomplete-search containers found: "
                                    + containers.size()
                    );

                    for (WebElement container :
                            containers) {

                        if (!container.isDisplayed()) {
                            continue;
                        }

                        System.out.println(
                                "Autocomplete container displayed"
                        );

                        List<WebElement> children =
                                container.findElements(
                                        By.xpath(
                                                ".//*"
                                        )
                                );

                        for (WebElement child :
                                children) {

                            if (!child.isDisplayed()) {
                                continue;
                            }

                            String text =
                                    child.getText()
                                            .trim();

                            if (text.isEmpty()) {
                                continue;
                            }

                            System.out.println(
                                    "Dropdown text: "
                                            + text
                            );

                            if (text.toLowerCase()
                                    .contains(expected)) {

                                clickAddressOption(
                                        driver,
                                        child
                                );

                                return;
                            }
                        }
                    }
                }

                // =================================================
                // STEP 4 - ROLE OPTION
                // =================================================

                List<WebElement> options =
                        driver.findElements(
                                By.cssSelector(
                                        "[role='option']"
                                )
                        );

                System.out.println(
                        "role='option' count = "
                                + options.size()
                );

                for (WebElement option :
                        options) {

                    try {

                        if (!option.isDisplayed()) {
                            continue;
                        }

                        String text =
                                option.getText()
                                        .trim();

                        if (text.isEmpty()) {
                            continue;
                        }

                        System.out.println(
                                "Option found: "
                                        + text
                        );

                        if (text.toLowerCase()
                                .contains(expected)) {

                            clickAddressOption(
                                    driver,
                                    option
                            );

                            return;
                        }

                    } catch (StaleElementReferenceException e) {

                        break;
                    }
                }

                // =================================================
                // STEP 5 - DIV/LIST AUTOCOMPLETE
                // =================================================

                String[] dropdownSelectors = {

                        "#autocomplete-search *",

                        "[id='autocomplete-search'] *",

                        "[role='listbox'] *",

                        "[role='option']",

                        "li",

                        "div"
                };

                for (String selector :
                        dropdownSelectors) {

                    List<WebElement> elements =
                            driver.findElements(
                                    By.cssSelector(
                                            selector
                                    )
                            );

                    for (WebElement element :
                            elements) {

                        try {

                            if (!element.isDisplayed()) {
                                continue;
                            }

                            String text =
                                    element.getText()
                                            .trim();

                            if (text.isEmpty()) {
                                continue;
                            }

                            // Avoid clicking huge containers
                            if (text.length() > 300) {
                                continue;
                            }

                            if (text.toLowerCase()
                                    .contains(expected)) {

                                System.out.println(
                                        "Matching dropdown element found: "
                                                + text
                                );

                                clickAddressOption(
                                        driver,
                                        element
                                );

                                return;
                            }

                        } catch (Exception ignored) {
                        }
                    }
                }

            } catch (Exception e) {

                System.out.println(
                        "Dropdown search attempt "
                                + (attempt + 1)
                                + " failed: "
                                + e.getMessage()
                );
            }

            Thread.sleep(500);
        }

        // =====================================================
        // STEP 6 - KEYBOARD FALLBACK
        // =====================================================

        System.out.println(
                "Dropdown element could not be selected directly."
        );

        System.out.println(
                "Trying keyboard selection..."
        );

        driver.switchTo().defaultContent();

        try {

            addressField.click();

            Thread.sleep(500);

            addressField.sendKeys(
                    Keys.ARROW_DOWN
            );

            Thread.sleep(500);

            addressField.sendKeys(
                    Keys.ENTER
            );

            Thread.sleep(2000);

            String finalValue =
                    addressField.getAttribute(
                            "value"
                    );

            System.out.println(
                    "Address after keyboard selection: "
                            + finalValue
            );

            if (finalValue != null &&
                    finalValue.toLowerCase()
                            .contains(expected)) {

                System.out.println(
                        "Address selected using keyboard."
                );

                return;
            }

        } catch (Exception e) {

            System.out.println(
                    "Keyboard selection failed: "
                            + e.getMessage()
            );
        }

        throw new RuntimeException(
                "Could not select address suggestion: "
                        + expectedAddress
        );
    }


    // =========================================================
    // CLICK ADDRESS OPTION
    // =========================================================

    private static void clickAddressOption(
            WebDriver driver,
            WebElement option)
            throws InterruptedException {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        String text =
                option.getText().trim();

        System.out.println();
        System.out.println(
                "=========================================="
        );

        System.out.println(
                "CLICKING ADDRESS OPTION:"
        );

        System.out.println(text);

        System.out.println(
                "=========================================="
        );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                option
        );

        Thread.sleep(500);

        // =====================================================
        // NORMAL CLICK
        // =====================================================

        try {

            WebDriverWait wait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(5)
                    );

            wait.until(
                    ExpectedConditions.elementToBeClickable(
                            option
                    )
            );

            option.click();

            System.out.println(
                    "Normal Selenium click performed."
            );

        } catch (Exception e) {

            System.out.println(
                    "Normal click failed. Using JavaScript."
            );

            js.executeScript(
                    "arguments[0].click();",
                    option
            );
        }

        Thread.sleep(1500);
    }


    // =========================================================
    // STRIPE FIELD
    // =========================================================

    public static void fillStripeField(
            WebDriver driver,
            By locator,
            String value,
            String fieldName)
            throws InterruptedException {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        System.out.println(
                "Searching field: " + locator
        );

        driver.switchTo().defaultContent();

        List<WebElement> mainElements =
                driver.findElements(locator);

        for (WebElement element :
                mainElements) {

            try {

                if (element.isDisplayed()) {

                    enterValue(
                            element,
                            value,
                            fieldName,
                            js
                    );

                    return;
                }

            } catch (Exception ignored) {
            }
        }

        WebElement result =
                searchFieldInFrames(
                        driver,
                        locator,
                        value,
                        fieldName,
                        0
                );

        driver.switchTo().defaultContent();

        if (result != null) {

            System.out.println(
                    fieldName + " Entered"
            );

            return;
        }

        throw new RuntimeException(
                "Payment field NOT FOUND: "
                        + locator
        );
    }


    // =========================================================
    // SEARCH STRIPE FIELD IN IFRAMES
    // =========================================================

    public static WebElement searchFieldInFrames(
            WebDriver driver,
            By locator,
            String value,
            String fieldName,
            int depth)
            throws InterruptedException {

        if (depth > 5) {
            return null;
        }

        List<WebElement> elements =
                driver.findElements(locator);

        for (WebElement element :
                elements) {

            try {

                if (element.isDisplayed()) {

                    JavascriptExecutor js =
                            (JavascriptExecutor) driver;

                    enterValue(
                            element,
                            value,
                            fieldName,
                            js
                    );

                    return element;
                }

            } catch (Exception ignored) {
            }
        }

        List<WebElement> frames =
                driver.findElements(
                        By.tagName("iframe")
                );

        System.out.println(
                "Depth " + depth +
                        " -> " +
                        frames.size() +
                        " iframe(s)"
        );

        for (int i = 0;
             i < frames.size();
             i++) {

            try {

                List<WebElement> currentFrames =
                        driver.findElements(
                                By.tagName("iframe")
                        );

                if (i >= currentFrames.size()) {
                    continue;
                }

                driver.switchTo().frame(
                        currentFrames.get(i)
                );

                WebElement result =
                        searchFieldInFrames(
                                driver,
                                locator,
                                value,
                                fieldName,
                                depth + 1
                        );

                if (result != null) {
                    return result;
                }

                driver.switchTo().parentFrame();

            } catch (Exception ignored) {

                try {
                    driver.switchTo().parentFrame();
                } catch (Exception ignored2) {
                }
            }
        }

        return null;
    }


    // =========================================================
    // ENTER VALUE
    // =========================================================

    private static void enterValue(
            WebElement element,
            String value,
            String fieldName,
            JavascriptExecutor js)
            throws InterruptedException {

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        Thread.sleep(500);

        try {

            element.click();

        } catch (Exception e) {

            js.executeScript(
                    "arguments[0].click();",
                    element
            );
        }

        element.sendKeys(
                Keys.CONTROL,
                "a"
        );

        element.sendKeys(
                Keys.BACK_SPACE
        );

        element.sendKeys(value);

        System.out.println(
                fieldName + " Entered"
        );
    }


    // =========================================================
    // GENERIC PAYMENT FIELD
    // =========================================================

    public static void fillPaymentField(
            WebDriver driver,
            By locator,
            String value,
            String fieldName)
            throws InterruptedException {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        System.out.println(
                "Searching field: " + locator
        );

        driver.switchTo().defaultContent();

        List<WebElement> elements =
                driver.findElements(locator);

        for (WebElement element :
                elements) {

            try {

                if (element.isDisplayed()) {

                    enterValue(
                            element,
                            value,
                            fieldName,
                            js
                    );

                    return;
                }

            } catch (Exception ignored) {
            }
        }

        WebElement result =
                searchPaymentFieldInFrames(
                        driver,
                        locator,
                        value,
                        fieldName,
                        0
                );

        driver.switchTo().defaultContent();

        if (result != null) {
            return;
        }

        throw new RuntimeException(
                "Payment field NOT FOUND: "
                        + locator
        );
    }


    // =========================================================
    // PAYMENT FIELD IFRAMES
    // =========================================================

    public static WebElement searchPaymentFieldInFrames(
            WebDriver driver,
            By locator,
            String value,
            String fieldName,
            int depth)
            throws InterruptedException {

        if (depth > 5) {
            return null;
        }

        List<WebElement> elements =
                driver.findElements(locator);

        for (WebElement element :
                elements) {

            try {

                if (element.isDisplayed()) {

                    JavascriptExecutor js =
                            (JavascriptExecutor) driver;

                    enterValue(
                            element,
                            value,
                            fieldName,
                            js
                    );

                    return element;
                }

            } catch (Exception ignored) {
            }
        }

        List<WebElement> frames =
                driver.findElements(
                        By.tagName("iframe")
                );

        System.out.println(
                "Depth " + depth +
                        " -> " +
                        frames.size() +
                        " iframe(s)"
        );

        for (int i = 0;
             i < frames.size();
             i++) {

            try {

                List<WebElement> currentFrames =
                        driver.findElements(
                                By.tagName("iframe")
                        );

                if (i >= currentFrames.size()) {
                    continue;
                }

                driver.switchTo().frame(
                        currentFrames.get(i)
                );

                WebElement result =
                        searchPaymentFieldInFrames(
                                driver,
                                locator,
                                value,
                                fieldName,
                                depth + 1
                        );

                if (result != null) {
                    return result;
                }

                driver.switchTo().parentFrame();

            } catch (Exception ignored) {

                try {
                    driver.switchTo().parentFrame();
                } catch (Exception ignored2) {
                }
            }
        }

        return null;
    }


    // =========================================================
    // CITY
    // =========================================================

    public static void handleCity(
            WebDriver driver)
            throws InterruptedException {

        System.out.println(
                "Checking City field..."
        );

        driver.switchTo().defaultContent();

        By cityLocator =
                By.xpath(
                        "//input[" +
                                "contains(@id,'locality') or " +
                                "contains(@id,'city') or " +
                                "@name='locality' or " +
                                "@name='city' or " +
                                "contains(@autocomplete,'address-level2')" +
                                "]"
                );

        WebElement city =
                findOptionalPaymentField(
                        driver,
                        cityLocator
                );

        if (city == null) {

            System.out.println(
                    "City field not found. " +
                            "Address autocomplete may have populated City automatically."
            );

            return;
        }

        String currentValue =
                city.getAttribute("value");

        System.out.println(
                "City current value: "
                        + currentValue
        );

        if (currentValue == null ||
                currentValue.trim().isEmpty()) {

            enterValue(
                    city,
                    "Buffalo Grove",
                    "City",
                    (JavascriptExecutor) driver
            );

        } else {

            System.out.println(
                    "City already populated: "
                            + currentValue
            );
        }
    }


    // =========================================================
    // STATE
    // =========================================================

    public static void handleState(
            WebDriver driver)
            throws InterruptedException {

        System.out.println(
                "Checking State field..."
        );

        driver.switchTo().defaultContent();

        By stateLocator =
                By.xpath(
                        "//select[" +
                                "contains(@id,'administrativeArea') or " +
                                "contains(@id,'state') or " +
                                "@name='administrativeArea' or " +
                                "@name='state'" +
                                "]"
                );

        WebElement stateElement =
                findOptionalPaymentField(
                        driver,
                        stateLocator
                );

        if (stateElement == null) {

            System.out.println(
                    "State field not found. " +
                            "Address autocomplete may have populated State automatically."
            );

            return;
        }

        try {

            Select state =
                    new Select(stateElement);

            String current =
                    state.getFirstSelectedOption()
                            .getText();

            System.out.println(
                    "Current State: "
                            + current
            );

            if (current == null ||
                    current.trim().isEmpty() ||
                    current.equalsIgnoreCase(
                            "Select State"
                    )) {

                state.selectByVisibleText(
                        "Illinois"
                );

                System.out.println(
                        "State Selected"
                );

            } else {

                System.out.println(
                        "State already populated: "
                                + current
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "State already populated or could not be changed: "
                            + e.getMessage()
            );
        }
    }


    // =========================================================
    // ZIP
    // =========================================================

    public static void handleZip(
            WebDriver driver)
            throws InterruptedException {

        System.out.println(
                "Checking ZIP field..."
        );

        driver.switchTo().defaultContent();

        By zipLocator =
                By.xpath(
                        "//input[" +
                                "contains(@id,'postalCode') or " +
                                "contains(@id,'postal') or " +
                                "contains(@id,'zip') or " +
                                "@name='postalCode' or " +
                                "@name='postal' or " +
                                "@name='zip' or " +
                                "contains(@autocomplete,'postal-code')" +
                                "]"
                );

        WebElement zip =
                findOptionalPaymentField(
                        driver,
                        zipLocator
                );

        if (zip == null) {

            System.out.println(
                    "ZIP field not found. " +
                            "Address autocomplete may have populated ZIP automatically."
            );

            return;
        }

        String currentValue =
                zip.getAttribute("value");

        System.out.println(
                "ZIP current value: "
                        + currentValue
        );

        if (currentValue == null ||
                currentValue.trim().isEmpty()) {

            enterValue(
                    zip,
                    "60089",
                    "ZIP",
                    (JavascriptExecutor) driver
            );

        } else {

            System.out.println(
                    "ZIP already populated: "
                            + currentValue
            );
        }
    }


    // =========================================================
    // OPTIONAL PAYMENT FIELD
    // =========================================================

    public static WebElement findOptionalPaymentField(
            WebDriver driver,
            By locator)
            throws InterruptedException {

        driver.switchTo().defaultContent();

        List<WebElement> elements =
                driver.findElements(locator);

        for (WebElement element :
                elements) {

            try {

                if (element.isDisplayed()) {
                    return element;
                }

            } catch (Exception ignored) {
            }
        }

        WebElement result =
                searchOptionalFieldInFrames(
                        driver,
                        locator,
                        0
                );

        driver.switchTo().defaultContent();

        return result;
    }


    // =========================================================
    // OPTIONAL FIELD IFRAMES
    // =========================================================

    public static WebElement searchOptionalFieldInFrames(
            WebDriver driver,
            By locator,
            int depth) {

        if (depth > 5) {
            return null;
        }

        List<WebElement> elements =
                driver.findElements(locator);

        for (WebElement element :
                elements) {

            try {

                if (element.isDisplayed()) {
                    return element;
                }

            } catch (Exception ignored) {
            }
        }

        List<WebElement> frames =
                driver.findElements(
                        By.tagName("iframe")
                );

        for (int i = 0;
             i < frames.size();
             i++) {

            try {

                List<WebElement> currentFrames =
                        driver.findElements(
                                By.tagName("iframe")
                        );

                if (i >= currentFrames.size()) {
                    continue;
                }

                driver.switchTo().frame(
                        currentFrames.get(i)
                );

                WebElement result =
                        searchOptionalFieldInFrames(
                                driver,
                                locator,
                                depth + 1
                        );

                if (result != null) {
                    return result;
                }

                driver.switchTo().parentFrame();

            } catch (Exception ignored) {

                try {
                    driver.switchTo().parentFrame();
                } catch (Exception ignored2) {
                }
            }
        }

        return null;
    }
}