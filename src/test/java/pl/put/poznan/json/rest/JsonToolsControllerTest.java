package pl.put.poznan.json.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.json.logic.BaseJsonDecorator;
import pl.put.poznan.json.logic.JsonRetainDecorator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JsonToolsControllerTest {
    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    void retainMock() {
        BaseJsonDecorator mock = mock(BaseJsonDecorator.class);
        var node = mapper.createObjectNode();
        node.put("key", "value").put("value", "key");

        when(mock.get()).thenReturn(node);

        var list = new String[] {"key"};
        var retain = new JsonRetainDecorator(mock, list);

        var expected = "{\"key\":\"value\"}";

        assertEquals(expected, retain.transform());
    }
}
