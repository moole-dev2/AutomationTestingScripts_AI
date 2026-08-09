package Company;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.ConfigReader;

import org.testng.annotations.Test;

public class TrustCenter {

    static WebDriver driver;
    static WebDriverWait wait;
    static JavascriptExecutor js;

    @Test
    public void trustCenterTest() throws InterruptedException {

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        js = (JavascriptExecutor) driver;

        try {

            // -------- Open Website --------
            driver.get(ConfigReader.getProperty("baseUrl"));
            driver.manage().window().maximize();

            Thread.sleep(2000);

            // -------- Handle Privacy Popup --------
            try {

                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[text()='OK']")
                        )
                ).click();

                System.out.println("Privacy popup closed");

            } catch (Exception e) {

                System.out.println("No popup");

            }

            // ---------- Click Company (hover sometimes required) ----------
            WebElement companyMenu = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//button[.//span[text()='Company']]")
                    )
            );

            Actions actions = new Actions(driver);

            actions.moveToElement(companyMenu).perform();

            Thread.sleep(1000);

            js.executeScript(
                    "arguments[0].click();",
                    companyMenu
            );

            System.out.println("Clicked Company menu");

            // ---------- Click Trust Center ----------
            WebElement trustCenter = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//span[text()='Trust Center']")
                    )
            );

            js.executeScript(
                    "arguments[0].click();",
                    trustCenter
            );

            System.out.println("Opened Trust Center");

            Thread.sleep(3000);

            // ---------- Click SVG Icon at Start ----------
            try {

                WebElement svgElement = wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                By.xpath("(//*[name()='svg'])[1]")
                        )
                );

                WebElement parent = svgElement.findElement(
                        By.xpath(
                                "ancestor::*[self::button or self::a or self::div][1]"
                        )
                );

                js.executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        parent
                );

                Thread.sleep(1000);

                try {

                    parent.click();

                } catch (Exception e) {

                    js.executeScript(
                            "arguments[0].click();",
                            parent
                    );
                }

                System.out.println("SVG clicked at start");

                Thread.sleep(2000);

            } catch (Exception e) {

                System.out.println(
                        "SVG icon not found. Continuing execution..."
                );
            }

            // ---------- Our Security Principles ----------
            clickSidebar("Our Security Principles");

            scrollDown();

            scrollUp();

            click(
                    "//button[contains(text(),'Secure By Design')]",
                    "Secure By Design"
            );

            // ---------- Privacy Policy ----------
            clickSidebar("Privacy Policy");

            scrollDown();

            scrollUp();

            click(
                    "//button[contains(text(),'Technical and Usage Information')]",
                    "Technical Info"
            );

            // ---------- Terms of Service ----------
            clickSidebar("Terms of Service");

            scrollDown();

            scrollUp();

            // ---------- Security & Compliance ----------
            clickSidebar(
                    "Moole Trust Centre/Security & Compliance"
            );

            scrollDown();

            scrollUp();

            // ---------- Responsible Disclosure ----------
            clickSidebar(
                    "Responsible Disclosure & Security Research Policy"
            );

            scrollDown();

            System.out.println(
                    "Completed full Trust Center homepage flow"
            );

            // =====================================================
            // ---------- Navigate Back to Home Page ---------------
            // =====================================================

            driver.get(
                    ConfigReader.getProperty("baseUrl")
            );

            wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.tagName("body")
                    )
            );

            Thread.sleep(3000);

            System.out.println(
                    "Navigated back to Home Page"
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );

            e.printStackTrace();

        } finally {

            driver.quit();

            System.out.println(
                    "Browser closed"
            );
        }
    }

    // =============================================================
    // ---------- Generic Click ------------------------------------
    // =============================================================

    public static void click(
            String xpath,
            String name
    ) throws InterruptedException {

        WebElement el = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(xpath)
                )
        );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                el
        );

        Thread.sleep(500);

        js.executeScript(
                "arguments[0].click();",
                el
        );

        System.out.println(
                "Clicked: " + name
        );

        Thread.sleep(2000);
    }

    // =============================================================
    // ---------- Sidebar Click ------------------------------------
    // =============================================================

    public static void clickSidebar(
            String text
    ) throws InterruptedException {

        String xpath =
                "//button[contains(@class,'w-full') and contains(.,'"
                        + text
                        + "')]";

        click(xpath, text);
    }

    // =============================================================
    // ---------- Scroll Down --------------------------------------
    // =============================================================

    public static void scrollDown()
            throws InterruptedException {

        long height = (long) js.executeScript(
                "return document.body.scrollHeight"
        );

        for (int i = 0; i < height; i += 300) {

            js.executeScript(
                    "window.scrollBy(0,300)"
            );

            Thread.sleep(400);
        }

        System.out.println(
                "Scrolled Down"
        );
    }

    // =============================================================
    // ---------- Scroll Up ----------------------------------------
    // =============================================================

    public static void scrollUp()
            throws InterruptedException {

        long height = (long) js.executeScript(
                "return document.body.scrollHeight"
        );

        for (int i = 0; i < height; i += 300) {

            js.executeScript(
                    "window.scrollBy(0,-300)"
            );

            Thread.sleep(400);
        }

        System.out.println(
                "Scrolled Up"
        );
    }
}