package pl.put.poznan.json.logic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.DefaultIndenter;

import java.util.List;

public class JsonDiffDecorator extends BaseJsonDecorator {
    /**
     * Creates object JsonKeyRemover
     * @param toRetain Array of key names to be removed from the JSON
     */
    public JsonDiffDecorator(IJsonTool base){
        super(base.get());
    }

    /**
     * Return filtered JSON back to client
     * @return JsonNode representing the filtered JSON
     */
    @Override
    public String transform() {
        String diff = "";
        ObjectNode json = super.get().deepCopy();
	
	JsonNode aNode = json.get("a");
	JsonNode bNode = json.get("b");

	String a = aNode.toPrettyString();
	String b = bNode.toPrettyString();
	
	String[] aLines = a.split("\n");
	String[] bLines = b.split("\n");

	for (int i=0; i<Math.max(aLines.length, bLines.length); ++i) {
	  if (i > aLines.length-1) {
	    diff += "> " + bLines[i] + "\n";
	    continue;
	  }
	  if (i > bLines.length-1) {
	    diff += "< " + aLines[i] + "\n";
	    continue;
	  }
	  if (!(aLines[i].equals(bLines[i]))) {
	    diff += "< " + aLines[i] + "\n";
	    diff += "> " + bLines[i] + "\n";
	    continue;
	  }
	  diff += "= " + aLines[i] + "\n";
	}

	return diff;
    }
}
