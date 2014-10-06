package testproject.containers;

import net.thucydides.core.annotations.WhenPageOpens;
import net.thucydides.core.annotations.findby.By;
import net.thucydides.core.annotations.findby.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by pavlo.basiuk on 06/10/2014.
 */
public class GlobalStreamContainer extends AbstractContainer {
    private static final String CONTAINER_LOCATOR = "//div[@id='global_stream']";

    private static final String SPACE_LOCATOR_HREF_TEMPLATE = "//div[@class='spaceswitcher-results']//a[contains(@href, '%s')]";
    private static final String SPACE_LOCATOR_NAME_TEMPLATE = "//div[@class='spaceswitcher-results']//a[contains(., '%s')]";
    private static final String ALL_STREAM_POSTS = "//div[@class='stream-container']/div[@class='stream-posts']/div[@class='stream-post status']";
    private static final String LAST_STREAM_POST = "//div[@class='stream-container']/div[@class='stream-posts']/div[@class='stream-post status']";

    private static final String POST_CONTENT = ".//div[@class='content']";
    private static final String POST_METADATA = ".//div[@class='status-metadata ']";

    @FindBy(id="global_stream")
    private WebElement container;

    @FindBy(id="status_value")
    private WebElement statusField;

    @FindBy(css="div.spaceswitcher-button")
    private WebElement spaceSwitcherButton;

    public GlobalStreamContainer(WebDriver driver) {
        super(driver);
    }

    @WhenPageOpens
    public void waitUntilLoaded() {
        waitForPresenceOf(LAST_STREAM_POST);
    }

    public void postStatusText(String statusText, String networkName) {
        statusField.click();
        statusField.clear();
        statusField.sendKeys(statusText);
        element(spaceSwitcherButton).waitUntilVisible();
        spaceSwitcherButton.click();
        waitForPresenceOf(String.format(SPACE_LOCATOR_NAME_TEMPLATE, networkName));
        container.findElement(By.xpath(String.format(SPACE_LOCATOR_NAME_TEMPLATE, networkName))).click();
    }

    public boolean hasStreamStatusPost(String statusText) {
        List<WebElement> allPosts = container.findElements(By.xpath(ALL_STREAM_POSTS));
        for (WebElement post:allPosts) {
            if (post.getText().contains(statusText))
                return true;
        }

        return false;
    }

    public String getLastStreamStatusPostText(){
        WebElement lastPost = getDriver().findElement(By.xpath(LAST_STREAM_POST));
        WebElement lastPostText = lastPost.findElement(By.xpath(POST_CONTENT));
        return lastPostText.getText();
    }

    public String getLastStreamStatusPostMetadata(){
        WebElement lastPost = getDriver().findElement(By.xpath(LAST_STREAM_POST));
        WebElement lastPostMetadata = lastPost.findElement(By.xpath(POST_METADATA));
        return lastPostMetadata.getText();
    }

    public void waitForContainer() {
        //element(container).waitUntilVisible();
        //element(By.xpath(LAST_STREAM_POST)).waitUntilVisible();
        waitForPresenceOf(LAST_STREAM_POST);
    }
}
