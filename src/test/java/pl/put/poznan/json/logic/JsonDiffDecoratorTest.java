package pl.put.poznan.json.logic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class JsonDiffDecoratorTest {
    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    void testSame() {
        var nodeA = mapper.createObjectNode();
        nodeA.put("d", "100");
        nodeA.put("x", "200");

        ObjectNode node = mapper.createObjectNode();
        node.put("a", nodeA);
        node.put("b", nodeA);

        var diff = new JsonDiffDecorator(new BaseJsonDecorator(node));
        var expected = "= {\n" +
                "=   \"d\" : \"100\",\n" +
                "=   \"x\" : \"200\"\n" +
                "= }\n";
        assertEquals(expected, diff.transform());
    }

    @Test
    void testDifferent() {
        var nodeA = mapper.createObjectNode();
        nodeA.put("f", "1410");
        nodeA.put("x", "200");

        var nodeB = mapper.createObjectNode();
        nodeB.put("f", "1683");
        nodeB.put("x", "200");

        ObjectNode node = mapper.createObjectNode();
        node.put("a", nodeA);
        node.put("b", nodeB);

        var diff = new JsonDiffDecorator(new BaseJsonDecorator(node));
        var expected = "= {\n" +
                "<   \"f\" : \"1410\",\n" +
                ">   \"f\" : \"1683\",\n" +
                "=   \"x\" : \"200\"\n" +
                "= }\n";
        assertEquals(expected, diff.transform());
    }

    @Test
    void testEmpty() {
        var nodeA = mapper.createObjectNode();

        ObjectNode node = mapper.createObjectNode();
        node.put("a", nodeA);
        node.put("b", nodeA);

        System.out.println(node);

        var diff = new JsonDiffDecorator(new BaseJsonDecorator(node));
        var expected = "= { }\n";
        assertEquals(expected, diff.transform());
    }
}
