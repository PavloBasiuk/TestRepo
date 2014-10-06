package helpers;

import com.google.common.base.Function;
import net.thucydides.core.Thucydides;
import net.thucydides.core.annotations.findby.By;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PageUtils {

    public static void verifyNewTabOpenedAndCloseIt(WebDriver driver, String...expectedText) {
        // 'remember' current window
        String oldWindow = driver.getWindowHandle();
        // get all available windows
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(oldWindow);
        // and then shift to newly created window/tab
        driver.switchTo().window(newTab.get(newTab.size()-1));

        WebDriverWait wait = new WebDriverWait(driver, 30);
        try {
            for (String text:expectedText){
                wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*"), text));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(.,'"+text+"')]")));
            }
        }
        finally {
            WebDriver augmentedDriver = new Augmenter().augment(driver);
            ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
            Thucydides.takeScreenshot();
            // close window/tab
            driver.close();
            // go back to old tab
            driver.switchTo().window(oldWindow);
        }
    }

    public static String getBrowserName(WebDriver driver){
        RemoteWebDriver rDriver = (RemoteWebDriver) ((WebDriverFacade) driver).getProxiedDriver();
        String browser = rDriver.getCapabilities().getBrowserName();
        return browser;
    }

}