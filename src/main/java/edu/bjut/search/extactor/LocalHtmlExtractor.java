package edu.bjut.search.extactor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class LocalHtmlExtractor extends TextExtractor {

    private static Logger logger = LoggerFactory.getLogger(LocalHtmlExtractor.class);

    @Override
    public DocText extractText(String source) throws IOException {
        Document document = Jsoup.parse(new File(source), "UTF-8");
        DocText docText = new DocText();
        docText.setTitle(document.title());
        docText.setContent(document.text());
        docText.setSource(source);
        return docText;
    }
}
