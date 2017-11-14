package edu.bjut.search.extactor;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class LocalOthersExtractor extends TextExtractor {
    private static Logger logger = LoggerFactory.getLogger(LocalOthersExtractor.class);

    @Override
    DocText extractText(String source) throws IOException {
        File file = new File(source);
        DocText docText = new DocText();
        if (file.exists() && file.isFile()) {
            Tika tika = new Tika();
            try {
                String content = tika.parseToString(file);
                docText.setTitle(file.getName());
                docText.setContent(content);
                docText.setSource(source);
            } catch (TikaException e) {
                logger.error(e.getMessage());
            }
        }
        return docText;
    }
}
