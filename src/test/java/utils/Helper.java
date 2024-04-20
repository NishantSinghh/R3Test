package utils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import config.ConfigManager;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.testng.Assert;
import properties.*;
import models.Payload;
import org.json.JSONObject;
import org.testng.SkipException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.Instant;


/**
 * Helper method specific to different tests
 */
public class Helper {

    private static final Logger logger = TestLogger.getLogger();


    /**
     * Get the schema files path based on the test class
     *
     * @param object
     * @param filename
     * @return
     */
    public static String getSchemaPath(Object object, String filename) {
        String classPath = object.getClass().getCanonicalName();
        String path = classPath.substring(classPath.indexOf(".") + 1, classPath.lastIndexOf("."));
        path = path.replaceAll("\\.", "/");
        return Constants.SCHEMA_FULL_PATH + "/" + path + filename;
    }


    /**
     * Get the data file path based on test class
     *
     * @param object
     * @param filename
     * @return
     */
    public static String getDataPath(Object object, String filename) {
        String classPath = object.getClass().getCanonicalName();
        String path = classPath.substring(classPath.indexOf(".") + 1, classPath.lastIndexOf("."));
        path = path.replaceAll("\\.", "/");
        return Constants.DATA_FULL_PATH + "/" + path + filename;
    }

    /**
     * Get the template files path based on test class
     *
     * @param object
     * @param filename
     * @return
     */
    public static String getTemplatePath(Object object, String filename) {
        String classPath = object.getClass().getCanonicalName();
        String path = classPath.substring(classPath.indexOf(".") + 1, classPath.lastIndexOf("."));
        path = path.replaceAll("\\.", "/");
        return Constants.TEMPLATE_FULL_PATH + "/" + path + filename;
    }


    /**
     * Create the request payload using only the template file
     *
     * @param templateFileName
     * @return
     */
    public static Payload getPayload(String templateFileName) {
        String template = JsonUtils.readJsonAsString(templateFileName);
        Payload payload = JsonUtils.getRequestPayload(template);
        logger.debug(payload);
        return payload;
    }


    /**
     * Check the rates of currency for invalid values
     *
     * @param responseBody
     * @return
     */
    public static boolean getPricingDetails(String responseBody) {

        JSONObject jsonObject = new JSONObject(responseBody);
        JSONObject jsonRates = jsonObject.getJSONObject("rates");
        Iterator<String> iterator = jsonRates.keys();
        while (iterator.hasNext()) {
            String key = iterator.next();
            if (isNullOrEmpty(jsonRates, key)) {
                logger.debug("value for field rates.{} is invalid", key);
                return false;
            }
            if (jsonRates.getInt(key) == 0) {
                logger.info("value for rates.{} is zero", key);
            }
        }
        return true;
    }


    /**
     * Method to be used for checking null or empty json fields
     *
     * @param json
     * @param key
     * @return
     */
    public static boolean isNullOrEmpty(JSONObject json, String key) {
        // Check if the key exists
        if (!json.has(key)) {
            return true;
        }

        // Check for null or JSON specific NULL
        Object value = json.get(key);
        if (value == null || value == JSONObject.NULL) {
            return true;
        }

        // Check for empty string
        if (value instanceof String && ((String) value).isEmpty()) {
            return true;
        }

        // Check for empty JSONArray
        if (value instanceof JSONArray && ((JSONArray) value).isEmpty()) {
            return true;
        }

        // Check for empty JSONObject
        if (value instanceof JSONObject && ((JSONObject) value).isEmpty()) {
            return true;
        }

        return false;
    }
}

