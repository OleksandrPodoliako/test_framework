package api;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;
import static utils.PropertiesReader.getProperty;

public class RequestSuccessTest {

    @Test(alwaysRun = true)
    @Issue("API-1")
    @Feature("API functionality")
    @Description("Test verifies that request is success")
    public final void testRequestIsSuccess() {

        Response response = given().
                when().
                get(getProperty("api.url"));

        assertTrue(response.getStatusCode() == 200);
    }
}
