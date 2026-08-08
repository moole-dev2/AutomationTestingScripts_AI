package Home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import Utils.ConfigReader;
import org.testng.annotations.Test;



public class LaunchURL {

    @Test
    public void LaunchURLTest() throws InterruptedException {

		//System.setProperty("webdriver.chrome.driver","C:\\Selenium WebDriver\\ChromeDriver\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
    	driver.get(ConfigReader.getProperty("baseUrl"));
		driver.manage().window().maximize();
		  try {
	           Thread.sleep(2000);
	        } catch (InterruptedException e) {
	     
	        	e.printStackTrace();
	        }
	       
	       // Close browser at the end
	       driver.quit();
}
}
