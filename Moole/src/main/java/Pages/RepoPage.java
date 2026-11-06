package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RepoPage {

    WebDriver driver;
    WebDriverWait wait;

    public RepoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    By repoMenu = By.xpath("//a[@href='/project/list-repos' or .//img[contains(@alt,'Repositories')]]");
    By addRepo = By.xpath("//button[.//span[text()='Add Repository']]");
    By search = By.id("repo-filter");

    public void openRepo() {
        wait.until(ExpectedConditions.elementToBeClickable(repoMenu)).click();
    }
    public boolean isRepoAvailable(String repoName) {

        return driver.findElements(
                By.xpath("//*[contains(text(),'" + repoName + "')]")
        ).size() > 0;
    }
    
    public void addRepository(String repoName) {
    	

        wait.until(ExpectedConditions.elementToBeClickable(addRepo)).click();

        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("repo-filter"))
        );

        searchBox.clear();
        searchBox.sendKeys(repoName);

        try { Thread.sleep(2000); } catch (Exception e) {}

        List<WebElement> repos = driver.findElements(
                By.xpath("//*[contains(text(),'" + repoName + "')]")
        );

        // =========================
        // CASE 1: ALREADY EXISTS
        // =========================
        if (repos.size() > 0) {

            System.out.println("Repository already imported: " + repoName);

            List<WebElement> closeBtn = driver.findElements(
                    By.xpath("//button[contains(@class,'cursor-pointer') or contains(@aria-label,'close')]")
            );

            if (!closeBtn.isEmpty()) {
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].click();", closeBtn.get(0));
            }

            System.out.println("Popup closed");

            // 🚨 STOP EVERYTHING HERE
            return;
        }

        // =========================
        // CASE 2: NOT FOUND → IMPORT
        // =========================

        WebElement repo = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[contains(text(),'" + repoName + "')]/ancestor::div[contains(@class,'cursor-pointer')]")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", repo);

        WebElement importBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(.,'Import') and contains(.,'repository')]")
        ));

        importBtn.click();

        System.out.println("Repository imported successfully: " + repoName);
    }
}