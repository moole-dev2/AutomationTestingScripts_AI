package Home;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Homepage {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;


        try {
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();
           Thread.sleep(2000);
            // ---------------- Handle Privacy Popup ----------------
            try {
                WebElement privacyOk = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[contains(@class,'rounded-sm bg-indigo') and text()='OK']")));
                js.executeScript("arguments[0].click();", privacyOk);
                System.out.println("Clicked Privacy OK button");
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Privacy popup not found, continuing...");
            }

            //Explore Products 
            WebElement exploreProducts = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(.,'Explore Products')]")));
            js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", exploreProducts);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", exploreProducts);
            Thread.sleep(2000);
            driver.navigate().back();
            Thread.sleep(2000);
            
            System.out.println("Clicked on Explore Products");
            
            //viewuseCases
            WebElement viewUseCases = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(.,'Use cases')]")));
            js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", viewUseCases);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", viewUseCases);
            Thread.sleep(2000);
            driver.navigate().back();
            Thread.sleep(2000);
           System.out.println("Clicked on View Use Cases");
           Thread.sleep(3000);
           
           // Get all 4 Learn More buttons
            List<WebElement> learnMoreButtons = driver.findElements(
                    By.xpath("//button[contains(.,'Learn More')]")
            );

            //Loop through each button
            for (int i = 0; i < learnMoreButtons.size(); i++) {

                // Re-fetch elements every time (avoid stale)
                learnMoreButtons = driver.findElements(
                        By.xpath("//button[contains(.,'Learn More')]")
                );

                WebElement button = learnMoreButtons.get(i);

                // Scroll
                js.executeScript("arguments[0].scrollIntoView({block:'center'});", button);
                Thread.sleep(2000);

                // Click
                try {
                    button.click();
                } catch (Exception e) {
                    js.executeScript("arguments[0].click();", button);
                }

                System.out.println("Clicked Learn More button " + (i + 1));

                // Wait for navigation
                Thread.sleep(4000);

                // Come back to homepage
                driver.navigate().back();

                System.out.println("Returned to Home Page");

                // Wait for home page to reload
                Thread.sleep(4000);
            }
           
         // Step: Scroll down and click on "Read Use Cases"
            try {
                WebElement readUseCases = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[@href='/company/use-cases' and contains(.,'Read Use Cases')]")
                ));

                // Scroll into view
                js.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", readUseCases);
                Thread.sleep(500); // small pause to ensure scroll finished

                // Click using JS (more reliable in case normal click fails)
                js.executeScript("arguments[0].click();", readUseCases);

                System.out.println("Clicked on Read Use Cases successfully!");

                // Optional: wait a few seconds for navigation
                Thread.sleep(3000);

            } catch (Exception e) {
                System.out.println("Failed to click on Read Use Cases: " + e.getMessage());
            }
            // Come back to homepage
            driver.navigate().back();

            System.out.println("Returned to Home Page");

            // Wait for home page to reload
            Thread.sleep(4000);
        
            WebElement exploreIntegrations = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(.,'Explore Integrations')]")));
            js.executeScript("arguments[0].scrollIntoView(true); window.scrollBy(0, -100);", exploreIntegrations);
            Thread.sleep(500);
            js.executeScript("arguments[0].click();", exploreIntegrations);
            Thread.sleep(3000);
            driver.navigate().back();
            Thread.sleep(2000);
            
            try {
                // Wait a bit to ensure page loads after Explore Integrations
                Thread.sleep(2000);

                // Locate the exact Read More button using full class match
                WebElement readMoreBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//button[@type='button' and @class='px-4 py-2 bg-indigo hover:bg-indigo-hover font-medium text-base flex-row-reverse justify-between  cursor-pointer rounded-sm transition duration-300 disabled:opacity-60 disabled:cursor-not-allowed flex items-center gap-2' and contains(.,'Read More')]")
                ));

                // Scroll smoothly to the element so the scroll is visible
                ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", readMoreBtn
                );

                // Wait a bit so the scroll effect is visible
                Thread.sleep(1000);

                // Click using JavaScript
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", readMoreBtn);

                System.out.println("Clicked on the correct Read More button successfully!");

            } catch (Exception e) {
                System.out.println("Failed to click on Read More button: " + e.getMessage());
            }
          // 1️⃣ Navigate back to Home
                driver.get("https://moole.ai/"); 
                driver.manage().window().maximize();
                Thread.sleep(2000);

                // 2️⃣ Scroll down to "Case Studies" button
                WebElement caseStudiesBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//button[contains(@class,'px-5 py-3 text-sm font-semibold rounded-sm') and contains(text(),'Case Studies')]")
                ));
                js.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", caseStudiesBtn);
                Thread.sleep(1000); // optional: see scroll
                js.executeScript("arguments[0].click();", caseStudiesBtn);
                System.out.println("Clicked Case Studies button");
                Thread.sleep(3000); // wait for page to load

                // 3️⃣ Click the specific "Read More" button for supply chain national platform
                WebElement supplyChainReadMore = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[@target='_self' and contains(@href,'resources/case-studies/supply-chain-national-platform')]" +
                             "/button[contains(@class,'px-4 py-2 bg-indigo') and contains(.,'Read More')]")
                ));
                js.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", supplyChainReadMore);
                Thread.sleep(1000); // optional: see scroll
                js.executeScript("arguments[0].click();", supplyChainReadMore);
                System.out.println("Clicked Read More for Supply Chain National Platform");
       
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
          //  driver.quit();
            System.out.println("Browser closed");
        }
	}
}
