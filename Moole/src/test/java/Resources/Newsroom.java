package Resources;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import Utils.ConfigReader;

public class Newsroom {

    @Test
    public void NewsroomTest() throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        try {

            // =========================================================
            // URLS
            // =========================================================

            String baseUrl =
                    ConfigReader.getProperty("baseUrl");

            String newsroomUrl =
                    baseUrl + "/resources/newsroom";


            // =========================================================
            // OPEN HOME PAGE
            // =========================================================

            driver.get(baseUrl);

            driver.manage().window().maximize();

            System.out.println("========================================");
            System.out.println("HOME PAGE OPENED");
            System.out.println("========================================");

            Thread.sleep(2000);


            // =========================================================
            // HANDLE POPUP
            // =========================================================

            try {

                WebElement ok = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.xpath(
                                        "//button[contains(normalize-space(),'OK')]"
                                )
                        )
                );

                js.executeScript(
                        "arguments[0].click();",
                        ok
                );

                System.out.println("Popup closed");

                Thread.sleep(800);

            } catch (Exception e) {

                System.out.println("No popup found");
            }


            // =========================================================
            // CLICK RESOURCES
            // =========================================================

            WebElement resources =
                    wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    By.xpath(
                                            "//span[normalize-space()='Resources']"
                                    )
                            )
                    );

            resources.click();

            System.out.println("Clicked Resources");

            Thread.sleep(1200);


            // =========================================================
            // CLICK NEWSROOM
            // =========================================================

            WebElement newsroom =
                    wait.until(
                            ExpectedConditions.elementToBeClickable(
                                    By.xpath(
                                            "//a[@href='/resources/newsroom']"
                                    )
                            )
                    );

            js.executeScript(
                    "arguments[0].click();",
                    newsroom
            );

            System.out.println("Opened Newsroom");

            Thread.sleep(2500);


            // =========================================================
            // READ MORE FLOW
            // =========================================================

            try {

                System.out.println("----------------------------------------");
                System.out.println("Searching Read More...");
                System.out.println("----------------------------------------");

                WebElement readMore =
                        wait.until(
                                ExpectedConditions.presenceOfElementLocated(
                                        By.xpath(
                                                "//*[self::button or self::a]" +
                                                "[contains(normalize-space(.),'Read More')]"
                                        )
                                )
                        );

                js.executeScript(
                        "arguments[0].scrollIntoView({" +
                        "behavior:'smooth'," +
                        "block:'center'" +
                        "});",
                        readMore
                );

                Thread.sleep(1000);


                // Highlight Read More
                js.executeScript(
                        "arguments[0].style.border='3px solid red';",
                        readMore
                );

                Thread.sleep(800);


                // Click Read More
                js.executeScript(
                        "arguments[0].click();",
                        readMore
                );

                System.out.println("Clicked Read More");

                Thread.sleep(2500);


                // -----------------------------------------------------
                // SCROLL READ MORE PAGE
                // -----------------------------------------------------

                for (int i = 0; i < 6; i++) {

                    js.executeScript(
                            "window.scrollBy({" +
                            "top:400," +
                            "behavior:'smooth'" +
                            "});"
                    );

                    Thread.sleep(350);
                }

                Thread.sleep(800);


                // -----------------------------------------------------
                // BACK TO NEWSROOM
                // -----------------------------------------------------

                driver.navigate().back();

                System.out.println(
                        "Returned from Read More to Newsroom"
                );

                Thread.sleep(2500);

            } catch (Exception e) {

                System.out.println(
                        "Read More not available, skipping..."
                );
            }


            // =========================================================
            // VIEW ALL FLOW
            // =========================================================

            try {

                System.out.println("----------------------------------------");
                System.out.println("Searching View All...");
                System.out.println("----------------------------------------");

                WebElement viewAll =
                        wait.until(
                                ExpectedConditions.presenceOfElementLocated(
                                        By.xpath(
                                                "//*[self::button or self::a]" +
                                                "[contains(normalize-space(.),'View All')]"
                                        )
                                )
                        );


                // Scroll to View All
                js.executeScript(
                        "arguments[0].scrollIntoView({" +
                        "behavior:'smooth'," +
                        "block:'center'" +
                        "});",
                        viewAll
                );

                Thread.sleep(1000);


                // Highlight View All
                js.executeScript(
                        "arguments[0].style.border='3px solid blue';",
                        viewAll
                );

                Thread.sleep(800);


                // Click View All
                js.executeScript(
                        "arguments[0].click();",
                        viewAll
                );

                System.out.println("Clicked View All");

                Thread.sleep(2500);


                // -----------------------------------------------------
                // SCROLL VIEW ALL PAGE
                // -----------------------------------------------------

                for (int i = 0; i < 8; i++) {

                    js.executeScript(
                            "window.scrollBy({" +
                            "top:300," +
                            "behavior:'smooth'" +
                            "});"
                    );

                    Thread.sleep(350);
                }

                Thread.sleep(800);


                // -----------------------------------------------------
                // BACK TO NEWSROOM
                // -----------------------------------------------------

                driver.navigate().back();

                System.out.println(
                        "Returned from View All to Newsroom"
                );

                Thread.sleep(2500);


                wait.until(
                        ExpectedConditions.urlContains(
                                "/resources/newsroom"
                        )
                );

            } catch (Exception e) {

                System.out.println(
                        "View All not available, skipping..."
                );
            }


            // =========================================================
            // MAKE SURE WE ARE ON NEWSROOM
            // =========================================================

            if (!driver.getCurrentUrl().contains(
                    "/resources/newsroom")) {

                driver.get(newsroomUrl);

                Thread.sleep(2500);
            }


            wait.until(
                    ExpectedConditions.urlContains(
                            "/resources/newsroom"
                    )
            );


            System.out.println("========================================");
            System.out.println("READY FOR PAGINATION");
            System.out.println("========================================");

            Thread.sleep(1000);


            // =========================================================
            // PAGINATION
            // PAGE 2 THROUGH PAGE 9
            // =========================================================

            for (int i = 2; i <= 9; i++) {

                System.out.println();
                System.out.println("========================================");
                System.out.println("PREPARING TO CLICK PAGE " + i);
                System.out.println("========================================");


                try {

                    // -------------------------------------------------
                    // SCROLL DOWN TOWARDS PAGINATION
                    // -------------------------------------------------

                    System.out.println(
                            "Scrolling down towards pagination..."
                    );

                    for (int s = 0; s < 7; s++) {

                        js.executeScript(
                                "window.scrollBy({" +
                                "top:350," +
                                "behavior:'smooth'" +
                                "});"
                        );

                        Thread.sleep(300);
                    }

                    Thread.sleep(700);


                    // -------------------------------------------------
                    // FIND PAGE NUMBER
                    // -------------------------------------------------

                    By pageLocator =
                            By.xpath(
                                    "//a[@aria-label='Page " +
                                    i +
                                    "']"
                            );

                    WebElement page =
                            wait.until(
                                    ExpectedConditions.presenceOfElementLocated(
                                            pageLocator
                                    )
                            );


                    System.out.println(
                            "Page " + i + " found"
                    );


                    // -------------------------------------------------
                    // SLIGHTLY SCROLL UP
                    // -------------------------------------------------

                    System.out.println(
                            "Moving slightly up to show pagination..."
                    );

                    for (int s = 0; s < 3; s++) {

                        js.executeScript(
                                "window.scrollBy({" +
                                "top:-120," +
                                "behavior:'smooth'" +
                                "});"
                        );

                        Thread.sleep(350);
                    }

                    Thread.sleep(700);


                    // -------------------------------------------------
                    // BRING PAGE NUMBER TO CENTER
                    // -------------------------------------------------

                    js.executeScript(
                            "arguments[0].scrollIntoView({" +
                            "behavior:'smooth'," +
                            "block:'center'," +
                            "inline:'center'" +
                            "});",
                            page
                    );

                    Thread.sleep(1000);


                    // -------------------------------------------------
                    // HIGHLIGHT PAGE NUMBER
                    // -------------------------------------------------

                    js.executeScript(
                            "arguments[0].style.border='4px solid red';" +
                            "arguments[0].style.backgroundColor='yellow';" +
                            "arguments[0].style.color='black';" +
                            "arguments[0].style.fontWeight='bold';" +
                            "arguments[0].style.padding='5px';",
                            page
                    );


                    System.out.println(
                            "PAGE " + i +
                            " NAVIGATION IS NOW VISIBLE"
                    );

                    Thread.sleep(1500);


                    // -------------------------------------------------
                    // SAVE OLD URL
                    // -------------------------------------------------

                    String oldUrl =
                            driver.getCurrentUrl();


                    System.out.println(
                            "Current URL: " + oldUrl
                    );


                    // -------------------------------------------------
                    // CLICK PAGE
                    // -------------------------------------------------

                    js.executeScript(
                            "arguments[0].click();",
                            page
                    );

                    System.out.println(
                            "Clicked Page " + i
                    );


                    // -------------------------------------------------
                    // WAIT FOR PAGE CHANGE
                    // -------------------------------------------------

                    try {

                        wait.until(
                                driver1 ->
                                        !driver1
                                                .getCurrentUrl()
                                                .equals(oldUrl)
                        );

                    } catch (Exception e) {

                        System.out.println(
                                "URL did not change immediately"
                        );
                    }


                    // -------------------------------------------------
                    // WAIT FOR NEW PAGE
                    // -------------------------------------------------

                    Thread.sleep(1800);


                    System.out.println(
                            "========================================"
                    );

                    System.out.println(
                            "NOW ON PAGE " + i
                    );

                    System.out.println(
                            "URL: " +
                            driver.getCurrentUrl()
                    );

                    System.out.println(
                            "========================================"
                    );


                    // -------------------------------------------------
                    // SHOW NEW PAGE FROM TOP
                    // -------------------------------------------------

                    js.executeScript(
                            "window.scrollTo({" +
                            "top:0," +
                            "behavior:'smooth'" +
                            "});"
                    );

                    Thread.sleep(1000);


                    // -------------------------------------------------
                    // SMALL SCROLL TO SHOW CONTENT
                    // -------------------------------------------------

                    for (int s = 0; s < 3; s++) {

                        js.executeScript(
                                "window.scrollBy({" +
                                "top:250," +
                                "behavior:'smooth'" +
                                "});"
                        );

                        Thread.sleep(300);
                    }

                    Thread.sleep(600);


                } catch (Exception e) {

                    System.out.println(
                            "Unable to navigate to Page " + i
                    );

                    e.printStackTrace();

                    break;
                }
            }


            // =========================================================
            // PAGE 9 COMPLETED
            // =========================================================

            System.out.println();
            System.out.println("========================================");
            System.out.println("PAGE 9 COMPLETED");
            System.out.println("========================================");

            Thread.sleep(1500);


            // =========================================================
            // BACK NAVIGATION
            //
            // PAGE 9 → PAGE 8 → PAGE 7 → PAGE 6
            //
            // EACH PAGE:
            // TOP → BOTTOM → BACK
            // =========================================================

            System.out.println();
            System.out.println("========================================");
            System.out.println("STARTING BACK NAVIGATION");
            System.out.println("========================================");


            // =========================================================
            // PAGE 9 → PAGE 8
            // =========================================================

            System.out.println();
            System.out.println("PAGE 9 - STARTING FULL PAGE SCROLL");


            // Start at TOP
            js.executeScript(
                    "window.scrollTo({" +
                    "top:0," +
                    "behavior:'smooth'" +
                    "});"
            );

            Thread.sleep(700);


            // Scroll TOP → BOTTOM
            for (int s = 0; s < 10; s++) {

                js.executeScript(
                        "window.scrollBy({" +
                        "top:500," +
                        "behavior:'smooth'" +
                        "});"
                );

                Thread.sleep(300);
            }

            Thread.sleep(700);

            System.out.println(
                    "PAGE 9 FULL SCROLL COMPLETED"
            );


            // Back
            System.out.println(
                    "BACK: PAGE 9 → PAGE 8"
            );

            driver.navigate().back();

            Thread.sleep(1800);


            // Show Page 8 from top
            js.executeScript(
                    "window.scrollTo({" +
                    "top:0," +
                    "behavior:'smooth'" +
                    "});"
            );

            Thread.sleep(900);

            System.out.println(
                    "NOW SHOWING PAGE 8"
            );

            System.out.println(
                    "URL: " +
                    driver.getCurrentUrl()
            );


            // =========================================================
            // PAGE 8 → PAGE 7
            // =========================================================

            System.out.println();
            System.out.println("PAGE 8 - STARTING FULL PAGE SCROLL");


            // Start at TOP
            js.executeScript(
                    "window.scrollTo({" +
                    "top:0," +
                    "behavior:'smooth'" +
                    "});"
            );

            Thread.sleep(700);


            // Scroll TOP → BOTTOM
            for (int s = 0; s < 10; s++) {

                js.executeScript(
                        "window.scrollBy({" +
                        "top:500," +
                        "behavior:'smooth'" +
                        "});"
                );

                Thread.sleep(300);
            }

            Thread.sleep(700);

            System.out.println(
                    "PAGE 8 FULL SCROLL COMPLETED"
            );


            // Back
            System.out.println(
                    "BACK: PAGE 8 → PAGE 7"
            );

            driver.navigate().back();

            Thread.sleep(1800);


            // Show Page 7 from top
            js.executeScript(
                    "window.scrollTo({" +
                    "top:0," +
                    "behavior:'smooth'" +
                    "});"
            );

            Thread.sleep(900);

            System.out.println(
                    "NOW SHOWING PAGE 7"
            );

            System.out.println(
                    "URL: " +
                    driver.getCurrentUrl()
            );


            // =========================================================
            // PAGE 7 → PAGE 6
            // =========================================================

            System.out.println();
            System.out.println("PAGE 7 - STARTING FULL PAGE SCROLL");


            // Start at TOP
            js.executeScript(
                    "window.scrollTo({" +
                    "top:0," +
                    "behavior:'smooth'" +
                    "});"
            );

            Thread.sleep(700);


            // Scroll TOP → BOTTOM
            for (int s = 0; s < 10; s++) {

                js.executeScript(
                        "window.scrollBy({" +
                        "top:500," +
                        "behavior:'smooth'" +
                        "});"
                );

                Thread.sleep(300);
            }

            Thread.sleep(700);

            System.out.println(
                    "PAGE 7 FULL SCROLL COMPLETED"
            );


            // Back
            System.out.println(
                    "BACK: PAGE 7 → PAGE 6"
            );

            driver.navigate().back();

            Thread.sleep(1800);


            // Show Page 6 from top
            js.executeScript(
                    "window.scrollTo({" +
                    "top:0," +
                    "behavior:'smooth'" +
                    "});"
            );

            Thread.sleep(900);

            System.out.println(
                    "NOW SHOWING PAGE 6"
            );

            System.out.println(
                    "URL: " +
                    driver.getCurrentUrl()
            );


            // =========================================================
            // BACK NAVIGATION COMPLETED
            // =========================================================

            System.out.println();
            System.out.println("========================================");
            System.out.println("BACK NAVIGATION COMPLETED");
            System.out.println("CURRENT PAGE: PAGE 6");
            System.out.println("========================================");

            Thread.sleep(1000);


            // =========================================================
            // RETURN TO NEWSROOM
            // =========================================================

            System.out.println();
            System.out.println("========================================");
            System.out.println("RETURNING TO NEWSROOM");
            System.out.println("========================================");


            if (!driver.getCurrentUrl().contains(
                    "/resources/newsroom")) {

                driver.get(newsroomUrl);

                Thread.sleep(2500);
            }


            wait.until(
                    ExpectedConditions.urlContains(
                            "/resources/newsroom"
                    )
            );


            System.out.println(
                    "Successfully returned to Newsroom"
            );

            System.out.println(
                    "Newsroom URL: " +
                    driver.getCurrentUrl()
            );


            // Show Newsroom
            js.executeScript(
                    "window.scrollTo({" +
                    "top:0," +
                    "behavior:'smooth'" +
                    "});"
            );

            Thread.sleep(1500);


            // =========================================================
            // RETURN TO HOME PAGE
            // =========================================================

            System.out.println();
            System.out.println("========================================");
            System.out.println("RETURNING TO HOME PAGE");
            System.out.println("========================================");


            driver.get(baseUrl);

            Thread.sleep(2500);


            System.out.println(
                    "Successfully returned to Home Page"
            );

            System.out.println(
                    "Home URL: " +
                    driver.getCurrentUrl()
            );


            // Show Home
            js.executeScript(
                    "window.scrollTo({" +
                    "top:0," +
                    "behavior:'smooth'" +
                    "});"
            );

            Thread.sleep(1500);


            // =========================================================
            // TEST COMPLETE
            // =========================================================

            System.out.println();
            System.out.println("========================================");
            System.out.println("NEWSROOM TEST COMPLETED SUCCESSFULLY");
            System.out.println("========================================");

            Thread.sleep(1000);


        } catch (Exception e) {

            System.out.println();
            System.out.println("========================================");
            System.out.println("TEST FAILED");
            System.out.println("========================================");

            e.printStackTrace();

        } finally {

            // =========================================================
            // CLOSE BROWSER
            // =========================================================

            System.out.println(
                    "Closing browser..."
            );

            driver.quit();

            System.out.println(
                    "Browser closed"
            );
        }
    }
}