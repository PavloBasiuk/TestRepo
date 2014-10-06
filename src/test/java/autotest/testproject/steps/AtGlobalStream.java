package testproject.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import testproject.containers.GlobalStreamContainer;
import testproject.containers.HeaderContainer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by pavlo.basiuk on 06/10/2014.
 */
public class AtGlobalStream extends ScenarioSteps {
    public AtGlobalStream(Pages pages) {
        super(pages);
    }

    public GlobalStreamContainer onStream() {
        return pages().get(GlobalStreamContainer.class);
    }

    @Step
    public void postStatusTextAndRefresh(String statusText, String spaceText) {
        onStream().postStatusText(statusText, spaceText);
        refresh();
    }

    @Step
    public void refresh() {
        getDriver().navigate().refresh();
        onStream().waitUntilLoaded();
    }

    @Step
    public void shouldHaveLastStatusPostText(String statusText) {
        String actualStatusText = onStream().getLastStreamStatusPostText();
        assertThat(actualStatusText, containsString(statusText));
    }

    @Step
    public void shouldHaveLastStatusPostMetadata(String metaData) {
        String actualMetadata = onStream().getLastStreamStatusPostMetadata();
        assertThat(actualMetadata, containsString(metaData));
    }

}
