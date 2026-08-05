package tests;

import Base.BaseTest;
import Pages.LoginPage;
import Pages.NotificationOrg;

import java.util.Scanner;

public class NotificationOrgTest extends BaseTest {

    public static void main(String[] args) {

        NotificationOrgTest test = new NotificationOrgTest();
        test.run();
    }

    public void run() {

        driver = initDriver();

        driver.get("https://moole.ai/auth/signin");
        // ✅ LOGIN STEP (MISSING IN YOUR CODE)
        LoginPage login = new LoginPage(driver);
        login.login("moole.dev.2@gmail.com");

        // OTP pause
        System.out.println("Enter OTP...");
        new Scanner(System.in).nextLine();

        NotificationOrg page = new NotificationOrg(driver);

        page.openNotifications();
        page.clickNotificationsMenu();
        page.ensureNotificationEnabled();
        page.clickUpdate();

        page.openActionsMenu();
        page.editEmail();
        page.enterEmails("a@gmail.com,b@gmail.com");
        page.clickSave();

        page.openActionsMenu();
        page.editEmail();
        page.clickCancel();

       // tearDown();
    }
}