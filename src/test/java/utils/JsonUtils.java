package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Payload;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * Methods related to json
 */
public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Parse the json string
     *
     * @param jsonString
     * @return
     */
    public static ObjectNode parseJsonString(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, ObjectNode.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Parse json array string
     *
     * @param jsonString
     * @return
     */
    public static List<ObjectNode> parseJsonStringArray(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, new TypeReference<List<ObjectNode>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Change json node to map
     *
     * @param jsonData
     * @return
     */
    public static Map<String, Object> jsonNodeToMap(JsonNode jsonData) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(jsonData, Map.class);

    }

    /**
     * get the json string and deserialise as payload class
     *
     * @param jsonString
     * @return
     */
    public static Payload getRequestPayload(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, Payload.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Read json data from a file
     *
     * @param file
     * @return
     */
    public static String readJsonAsString(String file) {
        try {
            return new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Read json as map
     *
     * @param filePath
     * @return
     */
    public static Map<String, Object> getJsonAsMap(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        File fileObj = new File(filePath);
        Map<String, Object> userData = null;
        try {
            userData = mapper.readValue(
                    fileObj, new TypeReference<Map<String, Object>>() {
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userData;
    }

}
