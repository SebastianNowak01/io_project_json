package pl.put.poznan.json.logic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFormater extends JsonTools{
    JsonTools jsonTool;
    /**
     * Return formatted JSON back to client
     * @param json JSON received from client
     * @return JsonNode representing formatted JSON
     */
    public JsonNode format(JsonNode json) {
        String jsonFormat = json.toPrettyString();
        return jsonTool.format(jsonFormat);
    }
}
