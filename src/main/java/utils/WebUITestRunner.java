package utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import web_ui.page_objects.PageFactory;

import java.net.MalformedURLException;

import static utils.PropertiesReader.getProperty;

public class WebUITestRunner {
    protected PageFactory pageFactory;

    @BeforeMethod
    public final void setUp() throws MalformedURLException {
        final String browser = getProperty("browser");
        final String browserVersion = getProperty("browser.version");
        final String browserSize = getProperty("browser.size");

        Configuration.browser = browser;
        Configuration.browserVersion = browserVersion;
        Configuration.startMaximized = true;

        pageFactory = new PageFactory();
    }

    @AfterMethod
    public final void close() throws MalformedURLException {
        WebDriverRunner.getWebDriver().close();
    }
}
