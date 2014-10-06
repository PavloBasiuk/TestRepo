package testproject.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.findby.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by pavlo.basiuk on 05/10/2014.
 */
@DefaultUrl("https://nextpodio.dk/login")
public class LoginPage extends AbstractPage {
    @FindBy(name="email")
    private WebElement emailField;

    @FindBy(name="password")
    private WebElement passwordField;

    @FindBy(name="remember_me")
    private WebElement rememberMeField;

    @FindBy(name="commit")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void fillLogin(String userName, String password, Boolean rememberMe) {
        emailField.clear();
        emailField.sendKeys(userName);
        passwordField.clear();
        passwordField.sendKeys(password);
        setCheckbox(rememberMeField, rememberMe);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

}
