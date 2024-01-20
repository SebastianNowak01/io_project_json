package pl.put.poznan.json.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonRemoveDecoratorTest {
    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    void removeOne() {
        var node = mapper.createObjectNode();
        node.put("key1", "100");
        node.put("x", 200);

        String[] l = {"key1"};
        var rm = new JsonRemoveDecorator(new BaseJsonDecorator(node), l);

        var expected = "{" +
                "\"x\":200" +
                "}";

        assertEquals(expected, rm.transform());
    }
    @Test
    void removeAll() {
        var node = mapper.createObjectNode();
        node.put("key1", "100");
        node.put("x", 200);

        String[] l = {"key1", "x"};
        var rm = new JsonRemoveDecorator(new BaseJsonDecorator(node), l);

        var expected = "{}";

        assertEquals(expected, rm.transform());
    }
    @Test
    void removeNone() {
        var node = mapper.createObjectNode();
        node.put("key1", "100");
        node.put("x", 200);

        String[] l = {};
        var rm = new JsonRemoveDecorator(new BaseJsonDecorator(node), l);
        var expected = "{\"key1\":\"100\",\"x\":200}";
        assertEquals(expected, rm.transform());
    }
}
