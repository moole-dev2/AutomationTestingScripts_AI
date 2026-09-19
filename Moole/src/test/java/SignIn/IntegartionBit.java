
package SignIn;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IntegartionBit {

    @Test
    public void IntegrationBitTest() throws InterruptedException {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        try {

            // ================= SIGN IN =================

            driver.get("https://moole.ai/auth/signin");

            WebElement email = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@type='email']")
                    )
            );

            email.sendKeys("moole.dev.2@gmail.com");

            WebElement signIn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@data-tour='signup-submit']")
                    )
            );

            signIn.click();

            System.out.println("Sign in button clicked");

            // ================= OTP =================

            System.out.println(
                    "Enter OTP in browser and press Enter here..."
            );

            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();

            Thread.sleep(1000);

            // ================= INTEGRATIONS =================

            driver.get(
                    "https://moole.ai/app/settings/project/integrations"
            );

            Thread.sleep(3000);

            // ================= BITBUCKET CONNECT =================

            WebElement connect = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//button[@name='provider' and @value='bitbucket']"
                            )
                    )
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    connect
            );

            Thread.sleep(1000);

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    connect
            );

            System.out.println("Bitbucket Connect clicked");

            Thread.sleep(3000);

            System.out.println(
                    "After Connect URL = " + driver.getCurrentUrl()
            );

            System.out.println(
                    "After Connect Title = " + driver.getTitle()
            );

            // ================= BITBUCKET CREDENTIALS =================

            WebElement bitbucketEmail = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("email")
                    )
            );

            bitbucketEmail.clear();
            bitbucketEmail.sendKeys("moole.dev.2@gmail.com");

            System.out.println("Bitbucket email entered");

            WebElement apiTokenField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("apiToken")
                    )
            );

            apiTokenField.clear();

            // IMPORTANT:
            // Replace YOUR_BITBUCKET_TOKEN with your token.
            // Do not commit the real token to GitHub.
            apiTokenField.sendKeys("YOUR_BITBUCKET_TOKEN");

            System.out.println("Bitbucket API token entered");

            // ================= CHECK REDIRECT =================

            System.out.println(
                    "URL immediately after token = "
                    + driver.getCurrentUrl()
            );

            Thread.sleep(1000);

            System.out.println(
                    "URL after 1 second = "
                    + driver.getCurrentUrl()
            );

            // ================= UPDATE CREDENTIALS =================

            List<WebElement> updateButtons = driver.findElements(
                    By.xpath("//button[normalize-space()='Update Credentials']")
            );

            System.out.println(
                    "Update Credentials button count = "
                    + updateButtons.size()
            );

            if (updateButtons.size() > 0) {

                WebElement updateCredentials = updateButtons.get(0);

                System.out.println(
                        "Update Credentials button found"
                );

                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        updateCredentials
                );

                Thread.sleep(500);

                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].click();",
                        updateCredentials
                );

                System.out.println(
                        "Update Credentials clicked"
                );

                Thread.sleep(5000);

            } else {

                System.out.println(
                        "Update Credentials button NOT found"
                );

                System.out.println(
                        "Current URL = " + driver.getCurrentUrl()
                );

                System.out.println(
                        "Current Title = " + driver.getTitle()
                );
            }

            // ================= REPOSITORIES =================

            WebElement repositories = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//a[@href='/app/project/list-repos']"
                            )
                    )
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    repositories
            );

            Thread.sleep(500);

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    repositories
            );

            System.out.println(
                    "Repositories clicked"
            );

            Thread.sleep(3000);

            // ================= ADD REPOSITORY =================

            WebElement addRepository = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//button[contains(normalize-space(.),'Add Repository')]"
                            )
                    )
            );

            addRepository.click();

            System.out.println(
                    "Add Repository clicked"
            );

            // ================= SEARCH REPOSITORY =================

            WebElement search = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(
                                    "//input[@placeholder='Search your repositories']"
                            )
                    )
            );

            search.clear();
            search.sendKeys("node-test");

            System.out.println(
                    "Searching for node-test"
            );

            Thread.sleep(2000);

            // ================= SELECT NODE-TEST =================

            WebElement nodeTest = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//span[contains(normalize-space(.),'node-test')]/ancestor::div[contains(@class,'cursor-pointer')][1]"
                            )
                    )
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    nodeTest
            );

            System.out.println(
                    "node-test selected"
            );

         // ================= IMPORT SELECTED REPOSITORIES =================

         By importButtonLocator = By.xpath(
                 "//button[starts-with(normalize-space(.),'Import ')]"
         );

         // Wait for Import button
         wait.until(
                 ExpectedConditions.presenceOfElementLocated(
                         importButtonLocator
                 )
         );

         System.out.println("Import button is available");

         // Find fresh Import button
         WebElement importButton = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         importButtonLocator
                 )
         );

         // Scroll to Import button
         ((JavascriptExecutor) driver).executeScript(
                 "arguments[0].scrollIntoView({block:'center'});",
                 importButton
         );

         Thread.sleep(1000);

         // Re-find button because the page can refresh/re-render
         importButton = wait.until(
                 ExpectedConditions.elementToBeClickable(
                         importButtonLocator
                 )
         );

         // Click Import
         ((JavascriptExecutor) driver).executeScript(
                 "arguments[0].click();",
                 importButton
         );

         System.out.println("Import button clicked");

         // ================= WAIT FOR IMPORT TO COMPLETE =================

         // Give the application time to start the import
         Thread.sleep(5000);

         System.out.println(
                 "Waiting for repository import to complete..."
         );

         // Wait up to 60 seconds for the Import button to disappear
         // This indicates that the import operation/modal has finished.
         try {

             new WebDriverWait(driver, Duration.ofSeconds(60))
                     .until(
                             ExpectedConditions.invisibilityOfElementLocated(
                                     importButtonLocator
                             )
                     );

             System.out.println(
                     "Import button disappeared. Import operation completed."
             );

         } catch (Exception e) {

             System.out.println(
                     "Import button is still present after 60 seconds."
             );

             System.out.println(
                     "Current URL = " + driver.getCurrentUrl()
             );
         }

         // Additional time for repository data to appear
         Thread.sleep(5000);

         System.out.println(
                 "Current URL after Import = " +
                 driver.getCurrentUrl()
         );

         System.out.println(
                 "Bitbucket repository import completed"
         );
       


        } catch (Exception e) {

            e.printStackTrace();

            Assert.fail(
                    "Bitbucket automation failed: "
                    + e.getMessage()
            );

        } finally {

            // Keep browser open while debugging.
            driver.quit();
        }
    }
}
