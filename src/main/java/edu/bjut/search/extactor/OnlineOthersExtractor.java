package edu.bjut.search.extactor;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;

public class OnlineOthersExtractor extends TextExtractor {

    private static Logger logger = LoggerFactory.getLogger(OnlineOthersExtractor.class);


    @Override
    public DocText extractText(String source) throws IOException {
        Tika tika = new Tika();
        DocText docText = new DocText();
        try {
            URL url = new URL(source);
            String content = tika.parseToString(url);
            docText.setTitle(url.getFile());
            docText.setContent(content);
            docText.setSource(source);
        } catch (TikaException e) {
            logger.error(e.getMessage());
        }
        return docText;
    }
}
