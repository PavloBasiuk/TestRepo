package testproject.tests;

import helpers.TestDataEntry;
import helpers.TestDataStorage;
import net.thucydides.core.annotations.*;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import testproject.requirements.Application;
import testproject.steps.CommonSteps;

import java.sql.SQLException;

/**
 * Created by pavlo.basiuk on 05/10/2014.
 */

@Story(Application.LoginTests.class)
@RunWith(ThucydidesRunner.class)
public class LoginTest {
    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @ManagedPages(defaultUrl = "https://nextpodio.dk")
    public Pages pages;

    @Steps
    public CommonSteps endUser;

    TestDataEntry userInfo;

    @Before
    public void Setup() throws Exception {
        TestDataStorage testDataStorage = new TestDataStorage();
        testDataStorage.connect();
        userInfo = testDataStorage.getTestDataById("1"); //TODO: review and improve

        if (endUser.reUseBrowserSession() && endUser.session_started()){
            endUser.go_to_start();
        }
        else{
            endUser.open();
        }
    }

    @After
    public void Teardown() {
        if (!endUser.reUseBrowserSession()){
            webdriver.quit();
        }
    }

    @Test
    @Title("Login")
    public void login() {
        endUser.atLoginPage().onLogin().open();
        endUser.atLoginPage().loginAs(userInfo.login, userInfo.password, false);
        endUser.atHeader().shouldHaveLoggedIn(userInfo.firstname);
    }

    @Test
    public void logout() {
        endUser.login(userInfo);
        endUser.atHeader().logout();
        endUser.atLoginPage().shouldBePresent();
    }

    @Test
    @Title("Login with 'remember me' - ON")
    @Pending
    public void loginRememberMeON() {
    }

    @Test
    @Title("Login with 'remember me' - OFF")
    @Pending
    public void loginRememberMeOFF() {
    }

    @Test
    @Pending
    public void forgotPassword() {
    }

    @Test
    @Pending
    public void loginWithGoogle() {
    }

    @Test
    @Pending
    public void loginWithFacebook() {
    }

    @Test
    @Pending
    public void loginWithLive() {
    }

    @Test
    @Pending
    public void loginWithGoToMeeting() {
    }

    @Test
    @Pending
    public void changeLanguage() {
    }

    @Test
    @Pending
    public void checkLoginPageContent() {
    }

}