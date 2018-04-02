package web_ui;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;
import utils.UIDataProvider;
import utils.WebUITestRunner;

public class FirstLinkContainsRequestTest extends WebUITestRunner{

    @Test(alwaysRun = true, dataProvider = "requests",dataProviderClass = UIDataProvider.class)
    @Issue("UI-1")
    @Feature("UI functionality")
    @Description("Test verifies that first link contains request")
    public final void testFirstLinkContainsRequest(String request) {
        pageFactory
                .openGogleMainPage()
                .searchFor(request)
                .getFirstLink()
                .shouldHave(Condition.text(request));
    }
}
