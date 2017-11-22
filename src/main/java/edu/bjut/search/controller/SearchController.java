package edu.bjut.search.controller;

import edu.bjut.search.entity.DocAttribute;
import edu.bjut.search.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class SearchController {
    private static Logger logger = LoggerFactory.getLogger(URIController.class);

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/api/search", method = RequestMethod.GET)
    public String search(HttpServletRequest request, String keyword) throws IOException {
        if (keyword == null)
            return "error";
        List<DocAttribute> docs = searchService.search(keyword);
        request.setAttribute("docs", docs);
        return "index";
    }
}
