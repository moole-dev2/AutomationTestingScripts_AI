package Home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Utils.ConfigReader;

public class LaunchURL {

	public static void main(String[] args) {
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
