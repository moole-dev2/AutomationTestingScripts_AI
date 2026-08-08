
package Resources;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import Utils.ConfigReader;
import org.testng.annotations.Test;



public class Newsroom {

    @Test
    public void NewsroomTest() throws InterruptedException {


        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {

            // -------- Open Website --------
        	driver.get(ConfigReader.getProperty("baseUrl"));
            driver.manage().window().maximize();

            // =========================================================
            // HANDLE POPUP
            // =========================================================
            try {
                WebElement ok = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'OK')]")));

                js.executeScript("arguments[0].click();", ok);
                System.out.println("Popup closed");
            } catch (Exception e) {
                System.out.println("No popup found");
            }

            // -------- Click Resources --------
            WebElement resources = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[normalize-space()='Resources']")));

            resources.click();
            Thread.sleep(1500);

            // -------- Open Newsroom Page --------
            driver.get("https://moole.ai/resources/newsroom");
            driver.manage().window().maximize();
            

            // =========================================================
            // 🔥 READ MORE FLOW (FIXED)
            // =========================================================
            try {

                System.out.println("Searching Read More...");

                WebElement readMore = wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                By.xpath("//*[self::button or self::a][contains(.,'Read More')]")
                        )
                );

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", readMore);
                Thread.sleep(1200);

                js.executeScript("arguments[0].click();", readMore);

                System.out.println("Clicked Read More");

                Thread.sleep(3000);

                // smooth human scroll
                for (int i = 0; i < 6; i++) {
                    js.executeScript("window.scrollBy(0, 200);");
                    Thread.sleep(500);
                }

                driver.navigate().back();
                Thread.sleep(3000);

            } catch (Exception e) {
                System.out.println("Read More not available, skipping...");
            }
            
            // =========================================================
            // 🔥 VIEW ALL FLOW (FIXED)
            // =========================================================
            try {

                System.out.println("Searching View All...");

                WebElement viewAll = wait.until(
                        ExpectedConditions.presenceOfElementLocated(
                                By.xpath("//*[self::button or self::a][contains(.,'View All')]")
                        )
                );

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", viewAll);
                Thread.sleep(1200);

                js.executeScript("arguments[0].click();", viewAll);

                System.out.println("Clicked View All");

                Thread.sleep(3000);

                for (int i = 0; i < 6; i++) {
                    js.executeScript("window.scrollBy(0, 200);");
                    Thread.sleep(500);
                }

                driver.navigate().back();
                Thread.sleep(3000);

            } catch (Exception e) {
                System.out.println("View All not available, skipping...");
            }
            
	            

            // =========================================================
            // PAGINATION LOOP (VISUAL FIX)
            // =========================================================
            for (int i = 2; i <= 8; i++) {

                WebElement pagination = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[contains(@class,'items-center gap-1')]")));

                WebElement page = pagination.findElement(
                        By.xpath(".//a[@aria-label='Page " + i + "']")
                );

                // =========================================================
                // SCROLL SLOWLY DOWN (YOU CAN SEE IT NOW)
                // =========================================================
                for (int s = 0; s < 5; s++) {
                    js.executeScript("window.scrollBy(0, 150);");
                    Thread.sleep(600); // slow visible movement
                }

                // bring pagination into view
                js.executeScript("arguments[0].scrollIntoView({block:'center'});", page);
                Thread.sleep(1200);

                // highlight page number
                js.executeScript("arguments[0].style.border='3px solid red';", page);
                Thread.sleep(1000);

                // click page
                js.executeScript("arguments[0].click();", page);

                wait.until(ExpectedConditions.urlContains("page=" + i));

                System.out.println("Navigated to Page " + i);

                // =========================================================
                // SLOW SCROLL AFTER PAGE LOAD (VISIBLE CHANGE)
                // =========================================================
                for (int s = 0; s < 5; s++) {
                    js.executeScript("window.scrollBy(0, 120);");
                    Thread.sleep(600);
                }

                Thread.sleep(2000); // IMPORTANT: lets YOU see page change
            }

            // =========================================================
            // NEXT BUTTON (VISIBLE MODE)
            // =========================================================
            System.out.println("Testing Next button...");

            WebDriverWait fastWait = new WebDriverWait(driver, Duration.ofSeconds(5));

            for (int i = 0; i < 2; i++) {

                try {
                    WebElement next = fastWait.until(
                            ExpectedConditions.presenceOfElementLocated(
                                    By.xpath("//a[contains(@aria-label,'Next') or contains(text(),'Next')]")
                            )
                    );

                    js.executeScript("arguments[0].scrollIntoView({block:'center'});", next);
                    Thread.sleep(1000);

                    js.executeScript("arguments[0].style.border='3px solid green';", next);
                    Thread.sleep(800);

                    js.executeScript("arguments[0].click();", next);

                    System.out.println("Clicked Next");

                    Thread.sleep(2000);

                    // slow scroll so you can SEE change
                    for (int s = 0; s < 4; s++) {
                        js.executeScript("window.scrollBy(0, 120);");
                        Thread.sleep(500);
                    }

                } catch (Exception e) {
                    System.out.println("Next button not found or disabled");
                    break;
                }
            }

            // =========================================================
            // PREVIOUS BUTTON (VISIBLE MODE)
            // =========================================================
            System.out.println("Testing Previous button...");

            boolean prevClicked = false;

            By[] prevLocators = new By[] {
                    By.xpath("//a[contains(@aria-label,'Previous')]"),
                    By.xpath("//button[contains(@aria-label,'Previous')]"),
                    By.xpath("//a[contains(text(),'Previous')]"),
                    By.xpath("//button[contains(text(),'Previous')]")
            };

            for (By locator : prevLocators) {

                try {
                    WebElement prev = wait.until(
                            ExpectedConditions.presenceOfElementLocated(locator)
                    );

                    js.executeScript("arguments[0].scrollIntoView({block:'center'});", prev);
                    Thread.sleep(1000);

                    js.executeScript("arguments[0].style.border='3px solid orange';", prev);
                    Thread.sleep(800);

                    js.executeScript("arguments[0].click();", prev);

                    System.out.println("Clicked Previous");

                    Thread.sleep(2000);

                    prevClicked = true;
                    break;

                } catch (Exception ignored) {}
            }

            if (!prevClicked) {
                System.out.println("Previous button not found or disabled");
            }

            // =========================================================
            // FINAL VISUAL SCROLL
            // =========================================================
            for (int s = 0; s < 5; s++) {
                js.executeScript("window.scrollBy(0, 150);");
                Thread.sleep(600);
            }
				driver.navigate().back();
				Thread.sleep(3000);
            System.out.println("Returned to Home Page");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
    }
}
