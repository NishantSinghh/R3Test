package test.currency;

import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.Payload;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import properties.ResponseResults;
import utils.APIClient;
import utils.Helper;

import java.io.File;

public class Pricing extends BaseTest {

    APIClient apiClient;
    SoftAssert softAssert;

    @BeforeClass
    public void setup() {
        apiClient = new APIClient();

    }

    @BeforeMethod
    public void setupTest() {
        softAssert = new SoftAssert();
    }


    @Test(description = "verify the pricing")
    public void verifyPricing() {
        Payload payload = Helper.getPayload(Helper.getTemplatePath(this, "/pricing.json"));
        Response response = apiClient.getResponse(payload);
        JsonPath jsonPath = response.getBody().jsonPath();
        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(Helper.getSchemaPath(this, "/pricing.json"))));
        softAssert.assertEquals(jsonPath.getString("result").toLowerCase(), ResponseResults.SUCCESS.toString().toLowerCase());
        float aedRate = jsonPath.getFloat("rates.AED");
        softAssert.assertTrue(aedRate > 3.6);
        softAssert.assertTrue(aedRate < 3.7);
        softAssert.assertTrue(Helper.getPricingDetails(response.getBody().asString()), "Rates contain invalid value");
        softAssert.assertEquals(jsonPath.getInt("rates.size()"), 162);
        softAssert.assertAll();
    }
}
