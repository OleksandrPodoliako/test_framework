package api;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;
import static utils.PropertiesReader.getProperty;

public class ResponseBodyTest {

    @Test(alwaysRun = true)
    @Issue("API-2")
    @Feature("API functionality")
    @Description("Test verifies that city is right")
    public final void testTemperatureIsRight() {
        String expectedTemperature = "Hyderabad";

        JsonPath responseBody = given()
                .when()
                .get(getProperty("api.url"))
                .jsonPath();

        assertTrue(responseBody.get("City").equals(expectedTemperature));
    }
}
