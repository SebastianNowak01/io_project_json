package pl.put.poznan.json.logic;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonMinifier extends JsonTools{
    JsonTools jsonTool;
    /**
     * Return minified (no whitespaces) JSON back to client
     * @param json JSON received from client
     * @return JsonNode representing minified JSON
     */
    public JsonNode format(JsonNode json) {
        String jsonMinify = json.toString();
        return jsonTool.format(jsonMinify);

    }
}
