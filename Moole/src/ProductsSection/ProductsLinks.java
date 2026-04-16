package ProductsSection;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

public class ProductsLinks {

    public static void main(String[] args) throws Exception {

        WebDriver driver = new ChromeDriver();
       // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // Open website
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();
            Thread.sleep(3000);

            // Click Privacy OK (if present)
            try {
                WebElement ok = driver.findElement(By.xpath("//button[normalize-space()='OK']"));
                js.executeScript("arguments[0].click();", ok);
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("No popup");
            }

         // -------- FUNCTION: SLOW SCROLL --------
            for (int i = 0; i <= 1500; i += 100) {
                js.executeScript("window.scrollBy(0,200)");
                Thread.sleep(1000);
            }

            // ========== STEP 2: Click SCA ==========
            WebElement sca = driver.findElement(By.xpath("//a[contains(@href,'software-composition-analysis')]"));
            js.executeScript("arguments[0].click();", sca);
            Thread.sleep(3000);
            System.out.println("Clicked SCA");

            // Scroll inside About page
            for (int i = 0; i <= 2000; i += 100) {
                js.executeScript("window.scrollBy(0,200)");
                Thread.sleep(1000);
            }

            Thread.sleep(2000);

            // Scroll page
            js.executeScript("window.scrollBy(0,1000)");
            Thread.sleep(4000);


            // ========== STEP 4: Click 2nd link ==========
            WebElement ContainerSecurity = driver.findElement(By.xpath("/html/body/div[2]/footer/div/div[1]/div[2]/ul/li[2]/a"));
            js.executeScript("arguments[0].click();", ContainerSecurity);
            Thread.sleep(3000);
            System.out.println("Clicked ContainerSecurity");

            // Scroll inside About page
            for (int i = 0; i <= 2000; i += 100) {
                js.executeScript("window.scrollBy(0,200)");
                Thread.sleep(1000);
            }

            Thread.sleep(2000);
            
            js.executeScript("window.scrollBy(0,1000)");
            Thread.sleep(4000);

            WebElement SAST = driver.findElement(By.xpath("/html/body/div[2]/footer/div/div[1]/div[2]/ul/li[3]/a"));
            js.executeScript("arguments[0].click();", SAST);
            Thread.sleep(4000);
            System.out.println("Clicked SAST");
            // Scroll inside About page
            for (int i = 0; i <= 2000; i += 100) {
                js.executeScript("window.scrollBy(0,200)");
                Thread.sleep(1000);
            }

            Thread.sleep(2000);
         
            // ========== STEP 6 ==========
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(3000);

            WebElement Vulnerability = driver.findElement(By.xpath("/html/body/div[2]/footer/div/div[1]/div[2]/ul/li[4]/a"));
            js.executeScript("arguments[0].click();", Vulnerability);
            Thread.sleep(4000);
            System.out.println("Clicked Vulnerability");
         // Scroll inside About page
            for (int i = 0; i <= 2000; i += 100) {
                js.executeScript("window.scrollBy(0,200)");
                Thread.sleep(1000);
            }


            Thread.sleep(1000);
            driver.get("https://moole.ai/");
            Thread.sleep(500);
            System.out.println("Back to the HomePage");

            // Done

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Browser closed");
        }
    }
}
