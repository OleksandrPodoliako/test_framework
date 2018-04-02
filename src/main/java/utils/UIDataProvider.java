package utils;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

public class UIDataProvider {

    private static final Logger LOGGER = Logger.getLogger(UIDataProvider.class);

    @DataProvider(name = "requests")
    public static Object[][] getRequests() {
        return new Object[][]{
                {"Cat"},
                {"Dog"},
                {"Mouse"}
        };
    }
}
