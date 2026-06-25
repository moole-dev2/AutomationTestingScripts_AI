package Company;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

public class MooleExperienceCenter {
	
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;


        try {
            driver.get("https://moole.ai/");
            driver.manage().window().maximize();
            Thread.sleep(1500);


            // Handle popup
            try {
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[text()='OK']"))).click();
            } catch (Exception e) {
                System.out.println("No popup");
            }

            // Click Company
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[.//span[text()='Company']]"))).click();
            Thread.sleep(1000);

            // Click Moole Experience Center
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[text()='Moole Experience Center']"))).click();
            
            // scroll DOWN slowly
            for (int i = 0; i < 8; i++) {
                js.executeScript("window.scrollBy(0,300)");
                Thread.sleep(700);
            }

            // pause like user reading
            Thread.sleep(1500);

            // scroll UP slowly
            for (int i = 0; i < 8; i++) {
                js.executeScript("window.scrollBy(0,-300)");
                Thread.sleep(700);
            }
           

            System.out.println("Scroll completed in Experience Center");

            // Open Tour
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[.//p[text()='Take the tour']]"))).click();

            // ===== SCROLL INSIDE MODAL =====
            WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.max-h-\\[80vh\\].overflow-auto")
            ));


            // scroll down inside modal
            js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", modal);
            Thread.sleep(1500);

            // scroll back up
            js.executeScript("arguments[0].scrollTop = 0", modal);
            Thread.sleep(1500);

            // Click Close button
            WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@aria-label='Close popup']")
            ));
            closeBtn.click();
            Thread.sleep(1500);
            
         // Click "Get a demo" at the end
            WebElement getDemo = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[.//p[text()='Get a demo']]")
            ));

            getDemo.click();
            Thread.sleep(1500);
            
            System.out.println("Clicked Get a demo successfully");
             
            // scroll DOWN slowly
            for (int i = 0; i < 8; i++) {
                js.executeScript("window.scrollBy(0,300)");
                Thread.sleep(700);
            }

            // pause like user reading
            Thread.sleep(1500);

            // scroll UP slowly
            for (int i = 0; i < 8; i++) {
                js.executeScript("window.scrollBy(0,-300)");
                Thread.sleep(700);
            }

         // back navigation
         driver.navigate().back();
         Thread.sleep(1000);
         
      // Click Watch Video
         WebElement watchVideo = wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("//a[.//p[text()='Watch Video']]")
         ));

         watchVideo.click();
         Thread.sleep(1000);
         
         // scroll DOWN slowly
         for (int i = 0; i < 8; i++) {
             js.executeScript("window.scrollBy(0,300)");
             Thread.sleep(700);
         }

         // pause like user reading
         Thread.sleep(1500);

         // scroll UP slowly
         for (int i = 0; i < 8; i++) {
             js.executeScript("window.scrollBy(0,-300)");
             Thread.sleep(700);
         }

	      // back navigation
	      driver.navigate().back();
	      Thread.sleep(1000);
	   
	      WebElement scheduleDemo = wait.until(ExpectedConditions.presenceOfElementLocated(
	    	        By.xpath("//button[contains(.,'Schedule a Demo')]")
	    	));

	    	js.executeScript("arguments[0].scrollIntoView(true);", scheduleDemo);

	    	wait.until(ExpectedConditions.visibilityOf(scheduleDemo));

	    	js.executeScript("arguments[0].click();", scheduleDemo);

	    	System.out.println("Clicked Schedule a Demo successfully");
	
	      // scroll DOWN slowly
	      for (int i = 0; i < 8; i++) {
	          js.executeScript("window.scrollBy(0,300)");
	          Thread.sleep(700);
	      }
	
	      // pause like user reading
	      Thread.sleep(1500);
	
	      // scroll UP slowly
	      for (int i = 0; i < 8; i++) {
	          js.executeScript("window.scrollBy(0,-300)");
	          Thread.sleep(700);
	      }
	
	   // back navigation
	   driver.navigate().back();
	   Thread.sleep(1000);
   
         // Navigate back
         driver.navigate().back();

         System.out.println("Watch Video flow completed");

            System.out.println("Flow completed successfully");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}