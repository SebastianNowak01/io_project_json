package pl.put.poznan.json.logic;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * A base interface for constructing the decorator pattern
 */
public interface IJsonTool {
    /**
     * Get the underlying json
     * @return JsonNode the Json data
     */
    public JsonNode get();
}
