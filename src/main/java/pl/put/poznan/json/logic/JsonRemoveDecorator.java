package pl.put.poznan.json.logic;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class JsonRemoveDecorator extends BaseJsonDecorator {
    List<String> toRemove;

    /**
     * Creates object JsonKeyRemover
     *
     * @param toRemove Array of key names to be removed from the JSON
     */
    public JsonRemoveDecorator(IJsonTool base, String[] toRemove) {
        super(base.get());
        this.toRemove = List.of(toRemove);
    }

    /**
     * Return filtered JSON back to client
     *
     * @return JsonNode representing the filtered JSON
     */
    @Override
    public String transform() {
        ObjectNode filtered = this.get().deepCopy();

        filtered.remove(toRemove);

        return filtered.toString();
    }
}



