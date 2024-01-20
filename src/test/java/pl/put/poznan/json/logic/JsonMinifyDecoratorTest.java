package pl.put.poznan.json.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonMinifyDecoratorTest {
    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    void empty() {
        var node = mapper.createObjectNode();

        var diff = new JsonMinifyDecorator(new BaseJsonDecorator(node));
        var expected = "{}";

        assertEquals(expected, diff.transform());
    }
    @Test
    void one() {
        var node = mapper.createObjectNode();
        node.put("1", 2);

        var diff = new JsonMinifyDecorator(new BaseJsonDecorator(node));
        var expected = "{\"1\":2}";

        assertEquals(expected, diff.transform());
    }

    @Test
    void two() {
        var node = mapper.createObjectNode();
        node.put("1", 2);
        node.put("2", "hello");


        var diff = new JsonMinifyDecorator(new BaseJsonDecorator(node));
        var expected = "{\"1\":2,\"2\":\"hello\"}";

        assertEquals(expected, diff.transform());
    }

    @Test
    void three() {
        var node = mapper.createObjectNode();
        node.put("1", 2);
        node.put("2", "hello");
        node.put("key", "value");


        var diff = new JsonMinifyDecorator(new BaseJsonDecorator(node));
        var expected = "{\"1\":2,\"2\":\"hello\",\"key\":\"value\"}";

        assertEquals(expected, diff.transform());
    }
}
