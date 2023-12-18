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

    public JsonNode format(String json){
        logger.info("Sending JSON:\n" + json);
        return new ObjectMapper().valueToTree(json);
    }
}
