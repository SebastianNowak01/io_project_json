package pl.put.poznan.json.logic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonKeyRemover extends JsonTools{
    JsonTools jsonTool;
    String[] filterout;

    /**
     * Creates object JsonKeyRemover
     * @param filterout Array of key names to be removed from the JSON
     */
    public JsonKeyRemover(String[] filterout){
        filterout = filterout;
    }

    /**
     * Return filtered JSON back to client
     * @param json JSON received from client
     * @return JsonNode representing the filtered JSON
     */
    public JsonNode format(JsonNode json) {
        ObjectNode filtered = ((ObjectNode) json).deepCopy();

        for (String key: filterout) {
            filtered.remove(key);
        }

        String jsonFormat = filtered.toString();
        return jsonTool.format(jsonFormat);
    }
}
