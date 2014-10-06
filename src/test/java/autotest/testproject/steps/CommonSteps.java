package testproject.steps;

import helpers.TestDataEntry;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.webdriver.javascript.JavascriptExecutorFacade;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import testproject.pages.*;
import net.thucydides.core.Thucydides;
import net.thucydides.core.annotations.Screenshots;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.findby.By;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.steps.StepFactory;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CommonSteps extends ScenarioSteps {

    public CommonSteps(Pages pages) {
        super(pages);
    }

    public AtStartPage atStartPage(){
        return new StepFactory(getPages()).getStepLibraryFor(AtStartPage.class);
    }

    public AtLoginPage atLoginPage(){
        return new StepFactory(getPages()).getStepLibraryFor(AtLoginPage.class);
    }

    public AtHeader atHeader(){ return new StepFactory(getPages()).getStepLibraryFor(AtHeader.class); }

    public AtGlobalStream atStream(){ return new StepFactory(getPages()).getStepLibraryFor(AtGlobalStream.class); }

    @Step
    public void navigate_back(){
        getDriver().navigate().back();
    }

    @Step
    @Screenshots(onlyOnFailures = true)
    public void browser(String msg){}

    @Step
    public String go_to_new_window() {
        WebDriver driver = getDriver();
        String oldWindow = driver.getWindowHandle();
        ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
        newTab.remove(oldWindow);
        driver.switchTo().window(newTab.get(newTab.size()-1));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body")));
        return oldWindow;
    }

    @Step
    public void go_to_window(String windowHandle) {
        WebDriver driver = getDriver();

        // close current window
        driver.close();
        // and go back to old tab/window
        driver.switchTo().window(windowHandle);
    }

    @Step
    public void stop_test(String msg){
        Thucydides.pendingStep(msg);
    }

    @Step
    public void report_ignore_message(String msg){
        Thucydides.ignoredStep(msg);
    }

    @Step
    public void log(String msg) {}

    public boolean reUseBrowserSession() {
        String flag = System.getProperty("browser.session.reuse", "false");
        return Boolean.parseBoolean(flag);
    }

    public boolean browser_started(){
        boolean result = false;
        if (getDriver() instanceof WebDriverFacade) {
            result = ((WebDriverFacade) getDriver()).isInstantiated();
        } else {
            result = !StringUtils.isEmpty(getDriver().getCurrentUrl());
        }
        return result;
    }

    public boolean session_started() {
        String currentUrl = getDriver().getCurrentUrl();
        return session_started(currentUrl);
    }

    public boolean session_started(String url) {
        if (url.equals("about:blank") ||
            url.equals("data:,") ||
            url.startsWith("http://localhost:") ||
            url.startsWith("applewebdata://") ||
            !(url.startsWith("https://www")))
        {
            return false;
        }
        return true;
    }

    public void go_to_start() {
        URL aURL;
        try{
            aURL = new URL(getDriver().getCurrentUrl());
            getDriver().get(aURL.getProtocol() + "://" + aURL.getAuthority() + "/");
        }
        catch (MalformedURLException e) {
            log(e.getMessage());
        }
    }

    public void open() {
        StartPage startPage = pages().getPage(StartPage.class);
        startPage.open();
    }

    @Step
    public void resize(int w, int h) {
        getDriver().manage().window().setPosition(new Point(0, 0));
        getDriver().manage().window().setSize(new Dimension(w, h));

        JavascriptExecutorFacade js = new JavascriptExecutorFacade(getDriver());
        js.executeScript("window.scrollTo(0,0);");
        waitABit(500);
        Thucydides.takeScreenshot();
    }

    @Step
    @Title("Login")
    public void login(TestDataEntry userInfo) {
        atLoginPage().onLogin().open();
        atLoginPage().fillLogin(userInfo.login, userInfo.password, false);
        atLoginPage().doLogin();
        atHeader().onHeader().waitForContainer();
        atStream().onStream().waitForContainer();
    }
}