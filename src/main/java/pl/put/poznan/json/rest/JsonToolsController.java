package pl.put.poznan.json.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class JsonToolsController {

    private static final Logger logger = LoggerFactory.getLogger(JsonToolsController.class);

    @ExceptionHandler
    public ResponseEntity<Object> handle(HttpMessageConversionException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
    }

    @RequestMapping(path = "/minify", method = RequestMethod.GET, produces = "application/json")
    public JsonNode minify(@RequestBody JsonNode json) {
        logger.debug(json.toPrettyString());
        return new ObjectMapper().valueToTree("Unimplemented");
    }

    @RequestMapping(path = "/format", method = RequestMethod.GET, produces = "application/json")
    public JsonNode format(@RequestBody JsonNode json) {
        logger.debug(json.toPrettyString());
        return new ObjectMapper().valueToTree("Unimplemented");
    }

    @RequestMapping(path = "/filterout", method = RequestMethod.GET, produces = "application/json")
    public JsonNode filterOut(@RequestBody JsonNode json, @RequestParam String[] filterout) {
        logger.debug(json.toPrettyString());
        logger.debug(Arrays.toString(filterout));
        return new ObjectMapper().valueToTree("Unimplemented");
    }

    @RequestMapping(path = "/retain", method = RequestMethod.GET, produces = "application/json")
    public JsonNode retain(@RequestBody JsonNode json, @RequestParam String[] retain) {
        logger.debug(json.toPrettyString());
        logger.debug(Arrays.toString(retain));
        return new ObjectMapper().valueToTree("Unimplemented");
    }

    @RequestMapping(path = "/diff", method = RequestMethod.GET, produces = "application/json")
    public String diff(@RequestBody JsonNode json) {
        // Probably just assume the body has two json objects, get them, else throw
        logger.debug(json.toPrettyString());
        return "Unimplemented";
    }
}
