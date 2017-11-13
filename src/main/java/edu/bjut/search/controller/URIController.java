package edu.bjut.search.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class URIController {

    private static Logger logger = LoggerFactory.getLogger(URIController.class);

    @RequestMapping(value = "/uri/{uri}", method = RequestMethod.GET)
    public @ResponseBody String getUri(@PathVariable String uri) {
        return uri;
    }
}
