package tests;

import Base.BaseTest;
import Pages.*;

public class IntegrationTest extends BaseTest {

    public static void main(String[] args) {

        IntegrationTest test = new IntegrationTest();
        test.runTest();
    }

    public void runTest() {

        driver = initDriver();

        driver.get("https://moole.ai/auth/signin");

        LoginPage login = new LoginPage(driver);
        IntegrationPage integration = new IntegrationPage(driver);
        RepoPage repo = new RepoPage(driver);

        // Login
        login.login("moole.dev.2@gmail.com");

        System.out.println("Complete OTP manually...");
        
        new java.util.Scanner(System.in).nextLine();

        driver.get("https://moole.ai/app/settings/project/integrations");

        // Integration flow
        integration.openBitbucket();
        integration.clickNext();
        integration.enterToken("YOUR_TOKEN");
        integration.update();

        // Repo flow
        repo.openRepo();
        repo.addRepository("node-test");

        System.out.println("Test Completed");
        driver.quit();
    }
}