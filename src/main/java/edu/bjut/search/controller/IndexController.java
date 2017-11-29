package edu.bjut.search.controller;

import edu.bjut.search.extactor.DocText;
import edu.bjut.search.extactor.SourceTypeEnum;
import edu.bjut.search.extactor.TextExtractor;
import edu.bjut.search.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@EnableAutoConfiguration
public class IndexController {
    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IndexService indexService;

    @RequestMapping(value = "/api/index/offline", method = RequestMethod.POST)
    public String indexForOffline(HttpServletRequest request, String source) {
        DocText docText = TextExtractor.extractText(source, SourceTypeEnum.OTHERS_LOCAL);
        try {
            indexService.index(docText);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return "/pages/search.jsp";
    }

    @RequestMapping(value = "/api/index/offline/batch", method = RequestMethod.POST)
    public String indexForOfflineBatch(HttpServletRequest request, String source) {
        File file = new File(source);
        batchIndex(file);
        return "/pages/search.jsp";
    }

    private void batchIndex(File file) {
        if (!file.exists())
            return;
        if (file.isFile()) {
            DocText docText = TextExtractor.extractText(file.getAbsolutePath(), SourceTypeEnum.OTHERS_LOCAL);
            try {
                indexService.index(docText);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                batchIndex(f);
            }
        }
    }

    @RequestMapping(value = "/api/index/online", method = RequestMethod.POST)
    public String indexForOnline(HttpServletRequest request, String source) {
        DocText docText = TextExtractor.extractText(source, SourceTypeEnum.OTHERS_ONLINE);
        try {
            indexService.index(docText);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return "/pages/search.jsp";
    }

}
