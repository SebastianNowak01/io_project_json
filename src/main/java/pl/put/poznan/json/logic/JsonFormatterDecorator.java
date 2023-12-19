package pl.put.poznan.json.logic;

public class JsonFormatterDecorator extends BaseJsonDecorator {
    public JsonFormatterDecorator(IJsonTool tool) {
        super(tool.get());
    }

    /**
     * Return formatted JSON back to client
     *
     * @return JsonNode representing formatted JSON
     */
    @Override
    public String transform() {
        return super.get().toPrettyString();
    }
}
