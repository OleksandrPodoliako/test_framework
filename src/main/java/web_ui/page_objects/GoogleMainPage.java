package web_ui.page_objects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GoogleMainPage {

    private SelenideElement input = $(By.xpath("//input[@id = 'lst-ib']"));
    private SelenideElement searchButton = $(By.xpath("(//input[@type= 'lsb'])[1]"));
    private ElementsCollection linkList = $$(By.xpath("//div[@class = 'rc']/h3"));

    @Step("Search for [0]")
    public final GoogleMainPage searchFor(String request) {
        input
                .waitUntil(Condition.appear,2000,500)
                .sendKeys(request + Keys.ENTER);
        return this;
    }

    @Step("")
    public final SelenideElement getFirstLink() {
        return linkList
                .get(0)
                .waitUntil(Condition.appear,2000,500);
    }

}
