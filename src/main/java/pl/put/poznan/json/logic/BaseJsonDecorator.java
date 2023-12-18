package pl.put.poznan.json.logic;

import com.fasterxml.jackson.databind.JsonNode;

public class BaseJsonDecorator implements IJsonTool {
    public JsonNode data;

    public BaseJsonDecorator(JsonNode data) {
        this.data = data;
    }

    public String transform() {
        return null;
    }

    @Override
    public JsonNode get() {
        return data;
    }
}
