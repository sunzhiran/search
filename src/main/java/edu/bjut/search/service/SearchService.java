package edu.bjut.search.service;

import edu.bjut.search.dao.DocDAO;
import edu.bjut.search.dao.TermDAO;
import edu.bjut.search.entity.DocAttribute;
import edu.bjut.search.entity.TermAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    private static Logger logger = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private DocDAO docDAO;
    @Autowired
    private TermDAO termDAO;

    private static Integer VIEW_NUM = 2;
    private static Integer VIEW_LEN = 50;

    /**
     * 根据关键词搜索
     * @param keywords
     * @return
     * @throws IOException
     */
    public List<DocAttribute> search(String keywords) throws IOException {
        TermAttribute termAttribute = termDAO.queryByTerm(keywords);
        String docList = termAttribute.getDocList();
        String offset = termAttribute.getOffset();
        String[] docListArr = docList.split(",");
        String[] offsetArr = offset.split(";");
        List<DocAttribute> docs = new ArrayList<>();
        for (int i=0; i<docListArr.length; i++) {
            String docId = docListArr[i];
            String offset1 = offsetArr[i];
            DocAttribute docAttribute = docDAO.getById(Integer.valueOf(docId));
            if (docAttribute == null) {
                return null;
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(docAttribute.getLink(), "r");
            String[] offset2 = offset1.split(",");
            StringBuilder viewContent = new StringBuilder();
            for (int j=0; j<Math.min(offset2.length, VIEW_NUM); j++) {
                long offsetValue = Long.valueOf(offset2[j]);
                long start = offsetValue - VIEW_LEN;
                if (start < 0) start = 0;
                long end = start + 100;
                if (end >= randomAccessFile.length()/2) end = randomAccessFile.length()/2;
                randomAccessFile.seek(start*2);
                for (long k=start; k<end; k++) {
                    viewContent.append(randomAccessFile.readChar());
                }
                viewContent.append("...");
            }
            docAttribute.setView(viewContent.toString());
            docs.add(docAttribute);
        }
        return docs;
    }

}
