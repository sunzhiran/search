package edu.bjut.search.extactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public abstract class TextExtractor {

    private static Logger logger = LoggerFactory.getLogger(TextExtractor.class);


    /**
     * 抽取文档中的文本内容
     * @param source 文档地址
     * @return 抽取的内容
     */
    abstract DocText extractText(String source) throws IOException;

    /**
     * 抽取文档中的文本内容
     * @param source 文档来源
     * @param typeEnum 文档来源类型
     * @return 抽取的内容
     */
    public static DocText extractText(String source, SourceTypeEnum typeEnum) {
        try {
            Class clazz = Class.forName(typeEnum.getImplClass());
            Object obj = clazz.newInstance();
            if (obj instanceof TextExtractor) {
                TextExtractor extractor = (TextExtractor) obj;
                return extractor.extractText(source);
            }
        } catch (ClassNotFoundException e) {
            logger.error("{} is not implemented.", typeEnum.getImplClass());
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage());
        } catch (InstantiationException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return null;
    }

    public static void main(String[] args) {
        DocText docText = TextExtractor.extractText("C:\\Users\\Administrator\\Desktop\\ddd.html", SourceTypeEnum.OTHERS_LOCAL);
        logger.info(docText.toString());
    }
}
