package edu.bjut.search.service;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;

@Service
public class WordAnalyzeService {
    private static Logger logger = LoggerFactory.getLogger(WordAnalyzeService.class);

    public static String analyze(String text) {
        IKAnalyzer analyzer = new IKAnalyzer();
        analyzer.setUseSmart(true);
        try {
            TokenStream tokenStream = analyzer.tokenStream("", text);
            tokenStream.addAttribute(CharTermAttribute.class);
            tokenStream.reset();
            CharTermAttribute charTermAttribute = tokenStream.getAttribute(CharTermAttribute.class);
            while (tokenStream.incrementToken()) {
                logger.info(charTermAttribute.toString());
            }
            tokenStream.close();
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return "";
    }

    public static void main(String[] args) {
        analyze("我的家在东北，松花江上，那里有漂亮的小姐姐。");
    }
}
