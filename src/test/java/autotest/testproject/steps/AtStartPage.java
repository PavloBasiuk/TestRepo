package testproject.steps;

import helpers.PageUtils;
import net.thucydides.core.Thucydides;
import net.thucydides.core.annotations.Screenshots;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.WebElement;
import testproject.pages.StartPage;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class AtStartPage extends ScenarioSteps {

    public AtStartPage(Pages pages) {
        super(pages);
    }

    public StartPage onStartPage() {
        return pages().get(StartPage.class);
    }

}
