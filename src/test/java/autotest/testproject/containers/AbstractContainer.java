package testproject.containers;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebDriver;

public class AbstractContainer extends PageObject {
    public AbstractContainer(WebDriver driver) {
        super(driver);
    }
}
