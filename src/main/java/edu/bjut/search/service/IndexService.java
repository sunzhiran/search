package edu.bjut.search.service;

import edu.bjut.search.dao.DocDAO;
import edu.bjut.search.dao.TermDAO;
import edu.bjut.search.entity.DocAttribute;
import edu.bjut.search.entity.TermAttribute;
import edu.bjut.search.extactor.DocText;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class IndexService {

    private static Logger logger = LoggerFactory.getLogger(IndexService.class);

    @Autowired
    private DocDAO docDAO;

    @Autowired
    private TermDAO termDAO;

    @Autowired
    private WordAnalyzeService wordAnalyzeService;

    /**
     * 对文档建立索引
     *
     * @param docText
     */
    public void index(DocText docText) throws IOException {
        DocAttribute docAttribute = new DocAttribute();
        docAttribute.setTitle(docText.getTitle());
        docAttribute.setLink(docText.getSource());
        docAttribute.setDocType(docText.getDocType());
        // 更新正向索引
        Integer docId = docDAO.insertDoc(docAttribute);
        // 存原始数据
        FileUtils.write(new File("doc/doc_" + docId + ".log"), docText.getContent());
        // 分词
        Map<String, Map<String, String>> map = wordAnalyzeService.analyze(docText.getContent());
        // 更新反向索引
        for (Map.Entry<String, Map<String, String>> entry : map.entrySet()) {
            String term = entry.getKey();
            Map<String, String> termMap = entry.getValue();
            TermAttribute termAttribute = new TermAttribute();
            termAttribute.setTerm(term);
            TermAttribute termQ = termDAO.queryByTerm(term);
            if (termQ == null) {
                termAttribute.setDocList("" + docId);
                termAttribute.setFrequency("" + termMap.get("count"));
                termAttribute.setOffset("" + termMap.get("offset"));
                termDAO.insertTerm(termAttribute);
            } else {
                termAttribute.setDocList(termQ.getDocList() + "," + docId);
                termAttribute.setFrequency(termQ.getFrequency() + "," + termMap.get("count"));
                termAttribute.setOffset(termQ.getOffset() + ";" + termMap.get("offset"));
                termDAO.updateByTerm(termAttribute);
            }
        }
    }

}
