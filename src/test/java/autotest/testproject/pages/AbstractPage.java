package testproject.pages;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

public class AbstractPage  extends PageObject {
    public AbstractPage(WebDriver driver) {
        super(driver);
    }

    public void resize(int w, int h){
        getDriver().manage().window().setPosition(new Point(0, 0));
        getDriver().manage().window().setSize(new Dimension(w, h));
    }

}
