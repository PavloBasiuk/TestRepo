package testproject.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import testproject.containers.HeaderContainer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

/**
 * Created by pavlo.basiuk on 05/10/2014.
 */
public class AtHeader extends ScenarioSteps {
    public AtHeader(Pages pages) {
        super(pages);
    }

    public HeaderContainer onHeader() {
        return pages().get(HeaderContainer.class);
    }

    @Step
    public void shouldHaveLoggedIn(String userName) {
        assertThat(onHeader().getLoggedInUserName(), containsString(userName));
    }

    @Step
    public void logout() {
        onHeader().clickLogout();
    }

    @Step
    @Title("Should not have header info as logged in user")
    public void shouldBeLoggedOut() {
        onHeader().setWaitForTimeout(10000);
        assertThat(onHeader().getContainer().isPresent(), is(false));
    }
}
