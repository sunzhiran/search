package edu.bjut.search.service;

import edu.bjut.search.extactor.DocText;
import edu.bjut.search.extactor.SourceTypeEnum;
import edu.bjut.search.extactor.TextExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ExtractorService {
    private static Logger logger = LoggerFactory.getLogger(ExtractorService.class);

    public DocText getTextFromOnline(String source) {
        if ( ! checkSource(source)) {
            logger.warn("数据源地址不合法。");
            return null;
        }
        return TextExtractor.extractText(source, SourceTypeEnum.OTHERS_ONLINE);
    }

    public DocText getTextFromLocal(String source) {
        return TextExtractor.extractText(source, SourceTypeEnum.OTHERS_LOCAL);
    }


    private boolean checkSource(String source) {
        Pattern pattern = Pattern.compile("(http|https|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]");
        Matcher matcher = pattern.matcher(source);
        return matcher.matches();
    }

}
