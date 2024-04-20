package base;

import config.ConfigManager;
import org.apache.logging.log4j.Logger;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;
import utils.TestLogger;


/**
 * Can be used to create access token and use it across tests
 */
public class BaseTest {

    public static final Logger logger = TestLogger.getLogger();

    @BeforeSuite
    public void setBaseUrl() {
        ConfigManager configManager = new ConfigManager();
        RestAssured.baseURI = configManager.getBaseUrl();
    }

}
