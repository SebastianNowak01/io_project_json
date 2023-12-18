package pl.put.poznan.json.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.json.logic.*;

import java.util.Arrays;

/**
 * REST controller class, manages paths and requests
 */
@RestController
@RequestMapping("/api")
public class JsonToolsController {

    private static final Logger logger = LoggerFactory.getLogger(JsonToolsController.class);

    private final JsonTools jsonTool = new JsonTools();

    @ExceptionHandler
    public ResponseEntity<Object> handle(HttpMessageConversionException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
    }

//    @RequestMapping(path = "/minify", method = RequestMethod.GET, produces = "application/json")
//    public JsonNode minify(@RequestBody JsonNode json) {
//        return jsonTool.minify(json);
//    }

    @RequestMapping(path = "/minify", method = RequestMethod.GET, produces = "application/json")
    public JsonNode minify(@RequestBody JsonNode json) {
        return new JsonMinifier().format(json);
    }

//    @RequestMapping(path = "/format", method = RequestMethod.GET, produces = "application/json")
//    public JsonNode format(@RequestBody JsonNode json) {
//        return jsonTool.format(json);
//    }

    @RequestMapping(path = "/format", method = RequestMethod.GET, produces = "application/json")
    public JsonNode format(@RequestBody JsonNode json) {
        return new JsonFormater().format(json);
    }

//    @RequestMapping(path = "/filterout", method = RequestMethod.GET, produces = "application/json")
//    public JsonNode filterOut(@RequestBody JsonNode json, @RequestParam String[] filterout) {
//        return jsonTool.filterOut(json, filterout);
//    }

    @RequestMapping(path = "/filterout", method = RequestMethod.GET, produces = "application/json")
    public JsonNode filterOut(@RequestBody JsonNode json, @RequestParam String[] filterout) {
        return new JsonKeyRemover(filterout).format(json);
    }

//    @RequestMapping(path = "/retain", method = RequestMethod.GET, produces = "application/json")
//    public JsonNode retain(@RequestBody JsonNode json, @RequestParam String[] retain) {
//        return jsonTool.retain(json, retain);
//    }

    @RequestMapping(path = "/retain", method = RequestMethod.GET, produces = "application/json")
    public JsonNode retain(@RequestBody JsonNode json, @RequestParam String[] retain) {
        return new JsonRetainer(retain).format(json);
    }

    @RequestMapping(path = "/diff", method = RequestMethod.GET, produces = "application/json")
    public String diff(@RequestBody JsonNode json) {
        // Probably just assume the body has two json objects, get them, else throw
        logger.debug(json.toPrettyString());
        return "Unimplemented";
    }
}
