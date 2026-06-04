package SignIn;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Scanner;

public class APIToken {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {

            // ---------------- LOGIN ----------------
            driver.get("https://moole.ai/auth/signin");
            driver.manage().window().maximize();
            Thread.sleep(3000);

            WebElement emailField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']"))
            );
            emailField.sendKeys("moole.dev.2@gmail.com");

            WebElement continueBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Continue')]"))
            );
            continueBtn.click();

            System.out.println("Enter OTP manually...");
            new Scanner(System.in).nextLine();
            Thread.sleep(2000);

            // ---------------- OPEN PAT PAGE ----------------
            driver.get("https://moole.ai/app/settings/developer/api-token");
            Thread.sleep(4000);

            // =====================================================
            // 1st TOKEN → ORGANIZATION
            // =====================================================
            generateToken(driver, wait, js, "BITBUCKET", "Organization");

            // DELETE TOKEN
            deleteToken(driver, wait, js, "BITBUCKET");

            // =====================================================
            // 2nd TOKEN → PROJECT
            // =====================================================
            generateToken(driver, wait, js, "BITBUCKET", "Project");

            System.out.println("ALL FLOWS COMPLETED SUCCESSFULLY");

            // =====================================================
            // 4. REGENERATE TOKEN
            // =====================================================

            regenerateToken(
                    driver,
                    wait,
                    js
            );

            // =====================================================
            // 5. CLICK DELETE
            // =====================================================

            clickDelete(
                    driver,
                    wait,
                    js
            );

            // =====================================================
            // ENTER DELETE INPUT
            // =====================================================

            WebElement deleteInput = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@type='text']")
                    )
            );

            highlight(driver, deleteInput);

            deleteInput.sendKeys("BITBUCKET");

            Thread.sleep(2000);

            // =====================================================
            // CLICK CANCEL
            // =====================================================

            WebElement cancelBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath(
                                    "//button[@type='button' and contains(.,'Cancel')]"
                            )
                    )
            );

            highlight(driver, cancelBtn);

            js.executeScript("arguments[0].click();", cancelBtn);

            System.out.println("Clicked Cancel");

            Thread.sleep(3000);

            // =====================================================
            // CLICK DELETE AGAIN
            // =====================================================

            clickDelete(
                    driver,
                    wait,
                    js
            );

            Thread.sleep(2000);

            // =====================================================
            // CLOSE POPUP
            // =====================================================

            WebElement closePopup = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@aria-label='Close popup']")
                    )
            );

            highlight(driver, closePopup);

            js.executeScript("arguments[0].click();", closePopup);

            System.out.println("Closed Popup");

            Thread.sleep(5000);

        } catch (Exception e) {

            System.out.println("TEST FAILED");

            e.printStackTrace();

        } finally {

            driver.quit();

            System.out.println("Browser Closed");
        }
    }

    // =====================================================
    // HIGHLIGHT ELEMENT
    // =====================================================

    public static void highlight(WebDriver driver,
                                 WebElement element) {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].style.border='4px solid red';" +
                        "arguments[0].style.background='yellow';",
                element
        );
    }
    public static void generateToken(WebDriver driver,
            WebDriverWait wait,
            JavascriptExecutor js,
            String tokenNameText,
            String type) throws InterruptedException {

			// ================= TOKEN NAME =================
			WebElement tokenName = wait.until(
			ExpectedConditions.visibilityOfElementLocated(
			By.xpath("//input[contains(@name,'tokenName')]")
			)
			);
			
			js.executeScript("arguments[0].scrollIntoView(true);", tokenName);
			tokenName.clear();
			tokenName.sendKeys(tokenNameText);
			
			Thread.sleep(1000);
			
			// ================= TYPE DROPDOWN =================
			WebElement chooseType = wait.until(
			ExpectedConditions.elementToBeClickable(
			By.xpath("//button[contains(.,'Choose Type')]")
			)
			);
			
			js.executeScript("arguments[0].click();", chooseType);
			Thread.sleep(1500);
			
			// ================= SELECT TYPE =================
			WebElement typeOption = wait.until(
			ExpectedConditions.elementToBeClickable(
			By.xpath("//div[contains(text(),'" + type + "')]")
			)
			);
			
			js.executeScript("arguments[0].click();", typeOption);
			Thread.sleep(1500);
			
			// =========================================================
			// ORGANIZATION FLOW
			// =========================================================
			if (type.equalsIgnoreCase("Organization")) {
			
			WebElement orgDropdown = wait.until(
			ExpectedConditions.elementToBeClickable(
			   By.xpath("//button[contains(.,'Choose organization')]")
			)
			);
			
			js.executeScript("arguments[0].click();", orgDropdown);
			Thread.sleep(1500);
			
			WebElement org = wait.until(
			ExpectedConditions.elementToBeClickable(
			   By.xpath("//div[contains(text(),'Milky Way-Barnards Star1205')]")
			)
			);
			
			js.executeScript("arguments[0].click();", org);
			Thread.sleep(1500);
			}
			
			// =========================================================
			// PROJECT FLOW (FIXED - IMPORTANT)
			// =========================================================
			if (type.equalsIgnoreCase("Project")) {
			
			// Open dropdown first
			WebElement projectDropdown = wait.until(
			ExpectedConditions.elementToBeClickable(
			   By.xpath("//button[contains(.,'Choose project')]")
			)
			);
			
			js.executeScript("arguments[0].click();", projectDropdown);
			Thread.sleep(2000);
			
			// IMPORTANT: use CONTAINS instead of full text
			WebElement projectOption = wait.until(
			ExpectedConditions.elementToBeClickable(
			   By.xpath("//div[contains(text(),'Milky Way-Barnards Star1205')]")
			)
			);
			
			js.executeScript("arguments[0].click();", projectOption);
			Thread.sleep(1500);
			}
			
			// ================= GENERATE =================
			WebElement generateBtn = wait.until(
			ExpectedConditions.elementToBeClickable(
			By.xpath("//button[contains(.,'Generate')]")
			)
			);
			
			js.executeScript("arguments[0].click();", generateBtn);
			
			System.out.println("Token Generated Successfully");
			
			Thread.sleep(4000);
			}

    // =====================================================
    // CLICK DELETE
    // =====================================================

    public static void clickDelete(WebDriver driver,
                                   WebDriverWait wait,
                                   JavascriptExecutor js)
            throws InterruptedException {

        WebElement deleteBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//button[@aria-label='Delete Token']")
                )
        );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                deleteBtn
        );

        Thread.sleep(1000);

        highlight(driver, deleteBtn);

        js.executeScript("arguments[0].click();", deleteBtn);

        System.out.println("Clicked Delete");

        Thread.sleep(3000);
    }

    // =====================================================
    // DELETE TOKEN
    // =====================================================

    public static void deleteToken(WebDriver driver,
                                   WebDriverWait wait,
                                   JavascriptExecutor js,
                                   String tokenNameText)
            throws InterruptedException {

        clickDelete(driver, wait, js);

        WebElement deleteInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@type='text']")
                )
        );

        highlight(driver, deleteInput);

        deleteInput.sendKeys(tokenNameText);

        Thread.sleep(2000);

        WebElement removeBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath(
                                "//button[@type='submit' and .//span[text()='Remove']]"
                        )
                )
        );

        highlight(driver, removeBtn);

        js.executeScript("arguments[0].click();", removeBtn);

        System.out.println("Deleted Token");

        Thread.sleep(5000);
    }
		
		 // =====================================================
		 // REGENERATE TOKEN (FIXED FLOW)
		 // =====================================================
		
		 public static void regenerateToken(WebDriver driver,
		                                    WebDriverWait wait,
		                                    JavascriptExecutor js)
		         throws InterruptedException {
		
			 // --- Step 10: Click Regenerate Token icon ---
		     WebElement regenerateIcon = wait.until(ExpectedConditions.elementToBeClickable(
		             By.xpath("//button[@aria-label='Regenerate Token']")));
		     regenerateIcon.click();
		     Thread.sleep(2000);
		
		     // --- Step 11: Click Regenerate confirmation button ---
		     WebElement regenerateConfirm = wait.until(ExpectedConditions.elementToBeClickable(
		             By.xpath("//button[@type='submit']//span[text()='Regenerate']")));
		     regenerateConfirm.click();
		
		     System.out.println("Token regeneration started...");
		
		     // --- Step 12: Wait for 20 seconds (as requested) ---
		     Thread.sleep(2000);
		 }
		
		    
		}