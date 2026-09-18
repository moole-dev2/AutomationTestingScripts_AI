package Home;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.ConfigReader;

public class AskMoole {

    @Test
    public void GetDemoTest() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {

            // =========================================================
            // OPEN APPLICATION
            // =========================================================

            driver.get(ConfigReader.getProperty("baseUrl"));
            driver.manage().window().maximize();

            System.out.println("Application opened successfully.");


            // =========================================================
            // CLOSE POPUP - SAFE
            // =========================================================

            try {

                WebElement okBtn = new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//button[contains(normalize-space(),'OK')]")
                        ));

                js.executeScript("arguments[0].click();", okBtn);

                System.out.println("Popup closed.");
                Thread.sleep(2000);

            } catch (Exception e) {

                System.out.println("No popup found.");
            }


            // =========================================================
            // CLICK "ASK MOOLE"
            // =========================================================

            By askMooleButton = By.xpath(
                    "//button[.//span[normalize-space()='Ask Moole']]"
            );

            WebElement askMoole = wait.until(
                    ExpectedConditions.elementToBeClickable(askMooleButton)
            );

            js.executeScript("arguments[0].click();", askMoole);

            System.out.println("Clicked 'Ask Moole' button.");
            Thread.sleep(1000);


            // =========================================================
            // WAIT FOR MOOLE AI CHAT WINDOW
            // =========================================================

            By chatWindow = By.xpath(
                    "//div[contains(@class,'fixed') and .//span[normalize-space()='Moole ai']]"
            );

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(chatWindow)
            );

            System.out.println("Moole AI chat window opened.");
            Thread.sleep(2000);


            // =========================================================
            // VERIFY WELCOME MESSAGE
            // =========================================================

            WebElement welcomeMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h2[normalize-space()='Welcome to Moole ai']")
                    )
            );

            Assert.assertTrue(
                    welcomeMessage.isDisplayed(),
                    "Moole AI welcome message is not displayed."
            );

            System.out.println("Welcome message verified.");
            Thread.sleep(2000);


            // =========================================================
            // CLICK PREDEFINED QUESTION
            // =========================================================

            String questionText = "What services does Moole offer?";

            By questionButton = By.xpath(
                    "//button[normalize-space()='" + questionText + "']"
            );

            WebElement question = wait.until(
                    ExpectedConditions.elementToBeClickable(questionButton)
            );

            js.executeScript("arguments[0].click();", question);

            System.out.println(
                    "Selected question: " + questionText
            );

            Thread.sleep(10000);


            // =========================================================
            // WAIT FOR QUESTION TO APPEAR
            // =========================================================

            wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath(
                                    "//*[normalize-space()='" + questionText + "']"
                            )
                    )
            );

            System.out.println("Question submitted successfully.");

            Thread.sleep(5000);


            // =========================================================
            // GET CHAT CONTENT
            // =========================================================

            By chatAreaLocator = By.xpath(
                    "//div[contains(@class,'overflow-y-auto')]"
            );

            WebElement chatArea = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            chatAreaLocator
                    )
            );

            String chatText = chatArea.getText().trim();


            // =========================================================
            // PRINT CHAT CONTENT
            // =========================================================

            System.out.println();
            System.out.println("========================================");
            System.out.println("MOOLE AI CHAT");
            System.out.println("========================================");
            System.out.println(chatText);
            System.out.println("========================================");
            System.out.println();


            // =========================================================
            // VERIFY AI RESPONSE
            // =========================================================

            Assert.assertTrue(
                    chatText.length() > 50,
                    "AI response was not received or response is too short."
            );

            Assert.assertTrue(
                    chatText.contains(questionText),
                    "Submitted question was not found in chat."
            );

            System.out.println("AI response received successfully.");
            Thread.sleep(5000);


            // =========================================================
            // ASK SECOND QUESTION
            // =========================================================

            By chatInput = By.xpath(
                    "//input[@placeholder='Ask Moole AI...']"
            );

            WebElement input = wait.until(
                    ExpectedConditions.elementToBeClickable(chatInput)
            );

            input.sendKeys("How can Moole help my business?");

            System.out.println("Typed custom question.");
            Thread.sleep(2000);


            // =========================================================
            // CLICK SEND MESSAGE
            // =========================================================

            By sendButton = By.xpath(
                    "//button[@aria-label='Send message']"
            );

            WebElement sendMessage = wait.until(
                    ExpectedConditions.elementToBeClickable(sendButton)
            );

            js.executeScript(
                    "arguments[0].click();",
                    sendMessage
            );

            System.out.println("Clicked Send Message.");

            Thread.sleep(5000);


            // =========================================================
            // PRINT UPDATED CHAT
            // =========================================================

            chatText = chatArea.getText().trim();

            System.out.println();
            System.out.println("========================================");
            System.out.println("UPDATED MOOLE AI CHAT");
            System.out.println("========================================");
            System.out.println(chatText);
            System.out.println("========================================");


            // =========================================================
            // VERIFY SECOND QUESTION
            // =========================================================

            Assert.assertTrue(
                    chatText.contains("How can Moole help my business?"),
                    "Custom question was not found in chat."
            );

            System.out.println(
                    "Second question submitted successfully."
            );
            
            Thread.sleep(10000);


            // =========================================================
            //  MINIMIZE CHAT
            // =========================================================

            By minimizeChatButton = By.xpath(
                    "//button[@aria-label='Minimize chat']"
            );

            WebElement minimizeChat = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            minimizeChatButton
                    )
            );

            js.executeScript(
                    "arguments[0].click();",
                    minimizeChat
            );

            System.out.println("Chat minimized successfully.");

            Thread.sleep(2000);


            // =========================================================
            //  CLICK ASK MOOLE AGAIN
            // =========================================================

            WebElement askMooleAgain = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            askMooleButton
                    )
            );

            js.executeScript(
                    "arguments[0].click();",
                    askMooleAgain
            );

            System.out.println(
                    "Clicked 'Ask Moole' again."
            );

            Thread.sleep(2000);


            // =========================================================
            //  VERIFY CHAT REOPENED
            // =========================================================

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            chatWindow
                    )
            );

            System.out.println(
                    "Chat reopened successfully."
            );


            // =========================================================
            //  CLICK END CHAT ICON
            // =========================================================

            By endChatIcon = By.cssSelector(
                    "button[aria-label='End chat']"
            );

            WebElement endChat = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            endChatIcon
                    )
            );

            js.executeScript(
                    "arguments[0].click();",
                    endChat
            );

            System.out.println(
                    "Clicked End Chat icon."
            );

            Thread.sleep(1500);


            // =========================================================
            // CLICK CANCEL
            // =========================================================

            By cancelButton = By.xpath(
                    "//button[normalize-space()='Cancel']"
            );

            WebElement cancel = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            cancelButton
                    )
            );

            js.executeScript(
                    "arguments[0].click();",
                    cancel
            );

            System.out.println(
                    "Clicked Cancel button successfully."
            );

            Thread.sleep(2000);


            // =========================================================
            //  CLICK END CHAT ICON AGAIN
            // =========================================================

            WebElement endChatAgain = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            endChatIcon
                    )
            );

            js.executeScript(
                    "arguments[0].click();",
                    endChatAgain
            );

            System.out.println(
                    "Clicked End Chat icon again."
            );

            Thread.sleep(1500);


            // =========================================================
            // CLICK END CHAT CONFIRMATION
            // =========================================================

            By endChatButton = By.xpath(
                    "//button[normalize-space()='End Chat']"
            );

            WebElement endChatConfirm = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            endChatButton
                    )
            );

            js.executeScript(
                    "arguments[0].click();",
                    endChatConfirm
            );

            System.out.println(
                    "Clicked End Chat confirmation successfully."
            );

            Thread.sleep(3000);


            // =========================================================
            // TEST COMPLETED
            // =========================================================

            System.out.println();
            System.out.println("========================================");
            System.out.println("MOOLE AI TEST COMPLETED SUCCESSFULLY");
            System.out.println("========================================");


        } catch (Exception e) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("TEST FAILED");
            System.out.println("========================================");

            e.printStackTrace();

            Assert.fail(
                    "Ask Moole test failed: " + e.getMessage()
            );

        } finally {

            driver.quit();

            System.out.println("Browser closed.");
        }
    }
}