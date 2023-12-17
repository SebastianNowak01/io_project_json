package pl.put.poznan.json.logic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
  
    /**
     * Return filtered JSON back to client
     * @param json JSON received from client
     * @param filterout Array of key names to be removed from the JSON
     * @return JsonNode representing the filtered JSON
     */
    public JsonNode filterOut(JsonNode json, String[] filterout) {
        ObjectNode filtered = ((ObjectNode) json).deepCopy();
	
        for (String key: filterout) {
	  filtered.remove(key);
        }

	String jsonFormat = filtered.toString();
        logger.info("Send filtered JSON:\n" + jsonFormat);
        return new ObjectMapper().valueToTree(jsonFormat);
    }

    /**
     * Return filtered JSON back to client. Only provided key names are preserved
     * @param json JSON received from client
     * @param retain Array of key names to be retained in the JSON
     * @return JsonNode representing the filtered JSON
     */
    public JsonNode retain(JsonNode json, String[] retain) {
        ObjectNode retained = new ObjectMapper().createObjectNode();

	logger.info("log1 <<<<<<<<<<<<<<<<<<<,");
        for (String key: retain) {
	  if (json.get(key) != null)
	    retained.put(key, json.get(key));
	}

	String jsonFormat = retained.toString();
        logger.info("Send retained JSON:\n" + jsonFormat);
        return new ObjectMapper().valueToTree(jsonFormat);
    }
}
