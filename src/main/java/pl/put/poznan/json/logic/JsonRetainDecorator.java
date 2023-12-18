package pl.put.poznan.json.logic;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class JsonRetainDecorator extends BaseJsonDecorator {
    List<String> toRetain;
    /**
     * Creates object JsonKeyRemover
     * @param toRetain Array of key names to be removed from the JSON
     */
    public JsonRetainDecorator(IJsonTool base, String[] toRetain){
        super(base.get());
        this.toRetain = List.of(toRetain);
    }

    /**
     * Return filtered JSON back to client
     * @return JsonNode representing the filtered JSON
     */
    @Override
    public String transform() {
        ObjectNode filtered = super.get().deepCopy();

        filtered.retain(this.toRetain);

        return filtered.toString();
    }
}
