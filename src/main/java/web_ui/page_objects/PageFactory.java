package web_ui.page_objects;

import static com.codeborne.selenide.Selenide.open;
import static utils.PropertiesReader.getProperty;

public class PageFactory {

    public GoogleMainPage openGogleMainPage() {
        open(getProperty("google.main.link"));
        return new GoogleMainPage();
    }
}
