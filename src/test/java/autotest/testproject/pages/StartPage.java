package testproject.pages;

import helpers.PageUtils;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.WhenPageOpens;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DefaultUrl("https://nextpodio.com")
public class StartPage extends AbstractPage {

    public StartPage(WebDriver driver) {
        super(driver);
    }

    @WhenPageOpens
    public void waitUntilTitleAppears() {

    }

}
