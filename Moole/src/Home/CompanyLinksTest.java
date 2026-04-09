package Home;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class CompanyLinksTest {

    public static void main(String[] args) throws Exception {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();
            
            WebElement privacyOk = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[contains(@class,'rounded-sm bg-indigo') and text()='OK']")));
            js.executeScript("arguments[0].click();", privacyOk);
            System.out.println("Clicked Privacy OK button");
            Thread.sleep(1000);
        
            // -------- FUNCTION: SLOW SCROLL --------
            for (int i = 0; i <= 1500; i += 100) {
                js.executeScript("window.scrollBy(0,200)");
                Thread.sleep(1000);
            }

            // -------- STEP 1: About --------
            WebElement about = driver.findElement(By.xpath("/html/body/div[2]/footer/div/div[1]/div[4]/ul/li[1]/a"));
            js.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", about);
            Thread.sleep(3000);

            js.executeScript("arguments[0].click();", about);
            System.out.println("Clicked About");

            // Scroll inside About page
            for (int i = 0; i <= 2000; i += 100) {
                js.executeScript("window.scrollBy(0,200)");
                Thread.sleep(1000);
            }

            Thread.sleep(2000);

            // -------- STEP 2: Use Cases --------
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(3000);

            WebElement useCases = driver.findElement(By.xpath("//a[@href='https://moole.ai/company/use-cases']"));
            js.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", useCases);
            Thread.sleep(1000);

            js.executeScript("arguments[0].click();", useCases);
            System.out.println("Clicked Use Cases");

            // Scroll inside Use Cases page
            for (int i = 0; i <= 1500; i += 100) {
                js.executeScript("window.scrollBy(0,100)");
                Thread.sleep(1000);
            }

            Thread.sleep(2000);

            // -------- STEP 3: Careers --------
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(5000);

            WebElement careers = driver.findElement(By.xpath("//a[@href='https://moole.ai/company/careers']"));
            js.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", careers);
            Thread.sleep(3000);

            js.executeScript("arguments[0].click();", careers);
            System.out.println("Clicked Careers");

            // Scroll inside Careers page
            for (int i = 0; i <= 1500; i += 100) {
                js.executeScript("window.scrollBy(0,100)");
                Thread.sleep(1000);
            }

            Thread.sleep(2000);

            // -------- STEP 4: Contact Us --------
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(3000);

            WebElement contact = driver.findElement(By.xpath("//a[@href='https://moole.ai/company/contact-us']"));
            js.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", contact);
            Thread.sleep(3000);

            js.executeScript("arguments[0].click();", contact);
            System.out.println("Clicked Contact Us");

            // Scroll inside Contact page
            for (int i = 0; i <= 1500; i += 100) {
                js.executeScript("window.scrollBy(0,100)");
                Thread.sleep(500);
            }

            Thread.sleep(500);

            // -------- STEP 5: Trust Center --------
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(5000);

            WebElement trust = driver.findElement(By.xpath("//a[@href='https://moole.ai/company/trust-center']"));
            js.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", trust);
            Thread.sleep(3000);

            js.executeScript("arguments[0].click();", trust);
            System.out.println("Clicked Trust Center");

            // Scroll inside Trust page
            for (int i = 0; i <= 1500; i += 100) {
                js.executeScript("window.scrollBy(0,100)");
                Thread.sleep(500);
            }

            Thread.sleep(1000);
            
            driver.get("https://moole.ai/");
            Thread.sleep(500);
            System.out.println("Back to the HomePage");


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
           // driver.quit();
            System.out.println("Browser closed");
        }
    }
}