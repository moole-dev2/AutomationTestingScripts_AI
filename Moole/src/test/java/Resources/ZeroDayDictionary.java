
package Resources;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import Utils.ConfigReader;
import org.testng.annotations.Test;



public class ZeroDayDictionary {

    @Test
    public void ZeroDayDictionaryTest() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {

            // =====================================================
            // STEP 1: OPEN HOME PAGE
            // =====================================================
        	driver.get(ConfigReader.getProperty("baseUrl"));
            driver.manage().window().maximize();
            System.out.println("Home page opened");

            Thread.sleep(2000);

            // =====================================================
            // STEP 2: HANDLE POPUP
            // =====================================================
            try {
                WebElement okBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[text()='OK']")));

                js.executeScript("arguments[0].click();", okBtn);
                System.out.println("Popup Closed");

                Thread.sleep(1500);

            } catch (Exception e) {
                System.out.println("No Popup Found");
            }

            // =====================================================
            // STEP 3: OPEN GLOSSARY PAGE
            // =====================================================
            driver.get("https://moole.ai/resources/glossary");
            driver.manage().window().maximize();
            System.out.println("Glossary page opened");

            Thread.sleep(3000);

            // =====================================================
            // STEP 4: CLICK A-Z SEQUENTIALLY
            // =====================================================
            String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

            for (char ch : letters.toCharArray()) {

                clickLetter(driver, wait, js, String.valueOf(ch));

                Thread.sleep(1800);
            }

            System.out.println("A-Z completed");

            // =====================================================
            // STEP 5: CLICK S, R, A + EXPAND SECTIONS
            // =====================================================
            List<String> testLetters = Arrays.asList("S", "R", "A");

            for (String letter : testLetters) {

                clickLetter(driver, wait, js, letter);

                System.out.println("Focused Letter: " + letter);

                Thread.sleep(1500);

                // Expand related sections
                expandSections(driver, wait, js, letter);

                Thread.sleep(2000);
            }

            System.out.println("S, R, A expansion completed");

            // =====================================================
            // STEP 6: BACK TO HOME PAGE
            // =====================================================
            driver.get("https://moole.ai/");
            System.out.println("Back to Home page");

            Thread.sleep(3000);

        } catch (Exception e) {

            System.out.println("Execution Failed: " + e.getMessage());

        } finally {

            // =====================================================
            // STEP 7: CLOSE BROWSER
            // =====================================================
            driver.quit();
            System.out.println("Browser Closed");
        }
    }

			    public static void clickLetter(WebDriver driver,
			            WebDriverWait wait,
			            JavascriptExecutor js,
			            String letter) {
			
			int retry = 0;
			
			while (retry < 3) {
			
			try {
			
			//  UPDATED LOCATOR (NEW UI STRUCTURE)
			By locator = By.cssSelector(
			 "aside a[href='#section-" + letter + "']"
			);
			
			WebElement element = wait.until(
			 ExpectedConditions.presenceOfElementLocated(locator)
			);
			
			// scroll into view (important because sticky header + horizontal scroll)
			js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
			
			Thread.sleep(500);
			
			// highlight (debug)
			js.executeScript("arguments[0].style.border='3px solid red';", element);
			
			Thread.sleep(300);
			
			// click using JS (more stable for sticky UI + flex layout)
			js.executeScript("arguments[0].click();", element);
			
			Thread.sleep(1200);
			
			System.out.println("Clicked Letter: " + letter);
			
			return;
			
			} catch (StaleElementReferenceException e) {
			retry++;
			System.out.println("Retrying letter (stale): " + letter);
			
			} catch (TimeoutException e) {
			System.out.println("Letter NOT FOUND in UI: " + letter);
			return;
			
			} catch (Exception e) {
			System.out.println("Failed letter " + letter + ": " + e.getMessage());
			return;
			}
			}
			}

    // =====================================================
    // EXPAND ACCORDION SECTIONS
    // =====================================================
    public static void expandSections(WebDriver driver,
                                      WebDriverWait wait,
                                      JavascriptExecutor js,
                                      String letter) {

        try {

            if (letter.equals("S")) {

                clickAccordion(wait, js,
                        "//button[.//span[contains(text(),'SAST')]]");
                Thread.sleep(1000);

                clickAccordion(wait, js,
                        "//button[.//span[contains(text(),'Supply Chain Security')]]");

            }

            else if (letter.equals("R")) {

            	clickAccordion(wait, js,
            		    "//button[.//span[contains(normalize-space(),'Red') and contains(normalize-space(),'Team')]]"
            		);
            }

            else if (letter.equals("A")) {

                clickAccordion(wait, js,
                        "//button[.//span[contains(text(),'AI Red Teaming')]]");

                Thread.sleep(800);

                clickAccordion(wait, js,
                        "//button[.//span[contains(text(),'Attack Surface')]]");
            }

        } catch (Exception e) {
            System.out.println("Accordion expand failed for " + letter + ": " + e.getMessage());
        }
    }

    // =====================================================
    // SAFE ACCORDION CLICK
    // =====================================================
    public static void clickAccordion(WebDriverWait wait,
                                      JavascriptExecutor js,
                                      String xpath) {

        try {

            WebElement btn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(xpath))
            );

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", btn);

            Thread.sleep(700);

            js.executeScript("arguments[0].click();", btn);

            System.out.println("Expanded: " + btn.getText());

        } catch (Exception e) {
            System.out.println("Failed accordion: " + xpath);
        }
    }
}
