package testproject.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import testproject.pages.LoginPage;

/**
 * Created by pavlo.basiuk on 05/10/2014.
 */
public class AtLoginPage extends ScenarioSteps {
    public AtLoginPage(Pages pages) {
        super(pages);
    }

    public LoginPage onLogin() {
        return pages().get(LoginPage.class);
    }

    @Step
    public void loginAs(String userName, String password, Boolean rememberMe) {
        fillLogin(userName, password, rememberMe);
        doLogin();
    }

    @Step
    public void doLogin() {
        onLogin().clickLoginButton();
    }

    @Step
    public void fillLogin(String userName, String password, Boolean rememberMe) {
        onLogin().fillLogin(userName, password, rememberMe);
    }

    @Step
    @Title("Login page should be displayed")
    public void shouldBePresent() {
        onLogin().shouldBeDisplayed();
    }
}
