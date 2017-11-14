package edu.bjut.search.service;

import edu.bjut.search.extactor.DocText;
import edu.bjut.search.extactor.SourceTypeEnum;
import edu.bjut.search.extactor.TextExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ExtractorService {
    private static Logger logger = LoggerFactory.getLogger(ExtractorService.class);

    public DocText getTextFromOnline(String source) {
        //TODO source合法性检测
        return TextExtractor.extractText(source, SourceTypeEnum.OTHERS_ONLINE);
    }

    public DocText getTextFromLocal(String source) {
        return TextExtractor.extractText(source, SourceTypeEnum.OTHERS_LOCAL);
    }

}
