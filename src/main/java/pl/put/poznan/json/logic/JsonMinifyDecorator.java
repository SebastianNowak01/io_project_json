package pl.put.poznan.json.logic;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonMinifyDecorator extends BaseJsonDecorator {

    public JsonMinifyDecorator(IJsonTool base) {
        super(base.get());
    }

    /**
     * Return minified (no whitespaces) JSON back to client
     */
    @Override
    public String transform() {
        return super.get().toString();
    }
}
