package pl.put.poznan.json.logic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.json.rest.JsonToolsController;

/**
 * This class is meant to transform Json per user requested path
 */
public class JsonTools {

    private static final Logger logger = LoggerFactory.getLogger(JsonToolsController.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Return minified (no whitespaces) JSON back to client
     * @param json JSON received from client
     * @return JsonNode representing minified JSON
     */
    public JsonNode minify(JsonNode json) {
        String jsonMinify = json.toString();
        logger.info("Send minified JSON" + "\n" + jsonMinify);
        return objectMapper.valueToTree(jsonMinify);
    }

    /**
     * Return formatted JSON back to client
     * @param json JSON received from client
     * @return JsonNode representing formatted JSON
     */
    public JsonNode format(JsonNode json) {
        String jsonFormat = json.toPrettyString();
        logger.info("Send formatted JSON" + "\n" + jsonFormat);
        return new ObjectMapper().valueToTree(jsonFormat);
    }
}
