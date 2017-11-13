package edu.bjut.search.extactor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class OnlineHtmlExtractor extends TextExtractor {
    private static Logger logger = LoggerFactory.getLogger(OnlineHtmlExtractor.class);

    @Override
    public DocText extractText(String source) throws IOException {
        Document document = Jsoup.connect(source).get();
        DocText docText = new DocText();
        docText.setTitle(document.title());
        docText.setContent(document.text());
        docText.setSource(source);
        return docText;
    }
}
