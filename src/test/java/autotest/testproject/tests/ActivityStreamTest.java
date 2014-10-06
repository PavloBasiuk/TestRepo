package testproject.tests;

import helpers.DataGenerator;
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

/**
 * Created by pavlo.basiuk on 06/10/2014.
 */
@Story(Application.ActivityStreamTests.class)
@RunWith(ThucydidesRunner.class)
public class ActivityStreamTest {
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

        endUser.login(userInfo);
    }

    @After
    public void Teardown() {
        if (!endUser.reUseBrowserSession()){
            webdriver.quit();
        }
    }

    @Test
    public void createStatusPostInEmployeeNetwork() {
        String statusText = "Status test at " + DataGenerator.getDateTimeString();
        String space = "Employee Network";
        String meta = userInfo.firstname + " " + userInfo.lastname + " in " + space;

        endUser.atStream().postStatusTextAndRefresh(statusText, space);

        endUser.atStream().shouldHaveLastStatusPostText(statusText);
        endUser.atStream().shouldHaveLastStatusPostMetadata(meta);
    }

    @Test
    public void createStatusPostInDemoWorkspace() {
        String statusText = "Status test at " + DataGenerator.getDateTimeString();
        String space = "Demo Workspace";
        String meta = userInfo.firstname + " " + userInfo.lastname + " in " + space;

        endUser.atStream().postStatusTextAndRefresh(statusText, space);

        endUser.atStream().shouldHaveLastStatusPostText(statusText);
        endUser.atStream().shouldHaveLastStatusPostMetadata(meta);
    }

    @Test
    @Pending
    public void createStatusPostWithLink() {}

    @Test
    @Pending
    public void createStatusPostWithFileFromMyComputer() {}

    @Test
    @Pending
    public void createStatusPostWithFileFromCloud() {}

    @Test
    @Pending
    public void createStatusPostWithAnswers() {}

    @Test
    @Pending
    public void createStatusPostMentionIndividuals() {}

    @Test
    @Pending
    public void createStatusPostMentionIndividualsWithFileAndAnswers() {}

    @Test
    @Pending
    public void cancelCreatingStatusPost() {}

    @Test
    @Pending
    public void ensureCantCreateEmptyStatusPost() {}

}

