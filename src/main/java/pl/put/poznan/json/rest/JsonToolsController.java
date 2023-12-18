package pl.put.poznan.json.rest;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.tomcat.util.json.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.json.logic.*;

/**
 * REST controller class, manages paths and requests
 */
@RestController
@RequestMapping("/api")
public class JsonToolsController {

    private static final Logger logger = LoggerFactory.getLogger(JsonToolsController.class);

    /**
     * Handles improper requests
     * @param e Exception
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<Object> handle(HttpMessageConversionException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getLocalizedMessage());
    }

    /**
     * Endpoint for minifying JSON received from client
     * @param json JSON received from client
     * @return String representing JSON
     */
    @RequestMapping(path = "/minify", method = RequestMethod.GET, produces = "text/plain")
    public String minify(@RequestBody JsonNode json) {
        var base = new BaseJsonDecorator(json);
        var minifier = new JsonMinifyDecorator(base);
        return minifier.transform();
    }

    /**
     * Endpoint for formatting JSON received from client
     * @param json JSON received from client
     * @return String representing JSON
     */
    @RequestMapping(path = "/format", method = RequestMethod.GET, produces = "text/plain")
    public String format(@RequestBody JsonNode json) {
        var base = new BaseJsonDecorator(json);
        var formatter = new JsonFormatterDecorator(base);
        return formatter.transform();
    }

    /**
     * Endpoint for removing keys from JSON received from client
     * @param json JSON received from client
     * @param keys Array String of keys to retain in JSON object
     * @return String representing JSON
     * @throws ParseException
     */
    @RequestMapping(path = "/remove", method = RequestMethod.GET, produces = "application/json")
    public String filterOut(@RequestBody JsonNode json, @RequestParam String[] keys) throws ParseException {
        var base = new BaseJsonDecorator(json);
        var filter = new JsonRemoveDecorator(base, keys);
        return filter.transform();
    }

    /**
     * Endpoint for retaining keys in JSON received from client
     * @param json JSON received from client
     * @param keys Array String of keys to retain in JSON object
     * @return String representing JSON
     */
    @RequestMapping(path = "/retain", method = RequestMethod.GET, produces = "application/json")
    public String retain(@RequestBody JsonNode json, @RequestParam String[] keys) {
        var base = new BaseJsonDecorator(json);
        var retainer = new JsonRetainDecorator(base, keys);
        return retainer.transform();
    }

    /**
     *  Endpoint for performing diff on two Json objects
     * @param json
     * @return
     */
    @RequestMapping(path = "/diff", method = RequestMethod.GET, produces = "application/json")
    public String diff(@RequestBody JsonNode json) {
        // Probably just assume the body has two json objects, get them, else throw
        logger.debug(json.toPrettyString());
        return "Unimplemented";
    }
}
