package testproject.containers;

import helpers.PageUtils;
import net.thucydides.core.annotations.findby.By;
import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HeaderContainer extends AbstractContainer {
    private static final String CONTAINER_LOCATOR = "//div[@id='header-global']";

    private static final String MYACCOUNT_LINK_LOCATOR = "//ul[@class='user-functions']/li[contains(@class,'my-account')]/div/div";
    private static final String PROFILENAME_LOCATOR = "//ul[@class='user-functions']//li[contains(@class,'profile-name')]/div[@class='bd']";
    private static final String LOGOUT_LOCATOR = "//ul[@class='user-functions']//div[@class='bd']/a[contains(@href, '/logout')]";

    @FindBy(id="header-global")
    private WebElement container;

    @FindBy(xpath=MYACCOUNT_LINK_LOCATOR)
    private WebElement myAccount;

    @FindBy(xpath=PROFILENAME_LOCATOR)
    private WebElement profileName;

    @FindBy(xpath=LOGOUT_LOCATOR)
    private WebElement logout;

    public HeaderContainer(WebDriver driver) {
        super(driver);
    }

    public WebElementFacade getContainer() {
        return element(container);
    }

    public Boolean isPresent() {
        this.setWaitForTimeout(10000);
        return element(container).isPresent();
    }

    public void waitForContainer() {
        this.waitForAnyRenderedElementOf(By.xpath(CONTAINER_LOCATOR), By.xpath(MYACCOUNT_LINK_LOCATOR));
        element(container).waitUntilVisible();
    }

    public String getLoggedInUserName() {
        waitForContainer();
        element(myAccount).waitUntilVisible();
        waitABit(3000);     //TODO: investigate why myAccount.click(); is not working immediately
        myAccount.click();
        element(profileName).waitUntilVisible();

        return profileName.getText();
    }

    public void clickLogout() {
        waitForContainer();
        element(myAccount).waitUntilVisible();
        waitABit(3000);     //TODO: investigate why myAccount.click(); is not working immediately
        myAccount.click();
        element(logout).waitUntilVisible();
        logout.click();
    }
}
