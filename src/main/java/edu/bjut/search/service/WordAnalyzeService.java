package edu.bjut.search.service;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class WordAnalyzeService {
    private static Logger logger = LoggerFactory.getLogger(WordAnalyzeService.class);

    public Map<String, Map<String, String>> analyze(String text) {
        Map<String, Map<String, String>> map = new HashMap<>();
        IKAnalyzer analyzer = new IKAnalyzer();
        analyzer.setUseSmart(true);
        try {
            TokenStream tokenStream = analyzer.tokenStream("", text);
            tokenStream.addAttribute(OffsetAttribute.class);
            tokenStream.addAttribute(TypeAttribute.class);
            tokenStream.addAttribute(CharTermAttribute.class);
            tokenStream.reset();
            OffsetAttribute offsetAttribute = tokenStream.getAttribute(OffsetAttribute.class);
            TypeAttribute typeAttribute = tokenStream.getAttribute(TypeAttribute.class);
            CharTermAttribute charTermAttribute = tokenStream.getAttribute(CharTermAttribute.class);
            while (tokenStream.incrementToken()) {
                String term = charTermAttribute.toString();
                if (map.containsKey(term)) {
                    map.get(term).put("count", (Integer.valueOf(map.get(term).get("count")) + 1)+"");
                    map.get(term).put("offset", map.get(term).get("offset")+","+offsetAttribute.startOffset());
                }
                else {
                    Map<String, String> termMap = new HashMap<>();
                    termMap.put("count", "1");
                    termMap.put("offset", offsetAttribute.startOffset()+"");
                    map.put(term, termMap);
                }
                logger.info(term + "pos: " + offsetAttribute.startOffset() + ":" + offsetAttribute.endOffset() + " type: " + typeAttribute.type());
            }
            tokenStream.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return map;
    }

}
