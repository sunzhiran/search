package edu.bjut.search.service;

import edu.bjut.search.dao.DocDAO;
import edu.bjut.search.dao.TermDAO;
import edu.bjut.search.entity.DocAttribute;
import edu.bjut.search.entity.TermAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${search.data.dir}")
    private static String DATA_DIR;

    private static Integer VIEW_NUM = 2;//高亮数目
    private static Integer VIEW_LEN = 5;//高亮前后显示数目

    /**
     * 根据关键词搜索
     * @param keywords
     * @return
     * @throws IOException
     */
    public List<DocAttribute> search(String keywords) throws IOException {
        TermAttribute termAttribute = termDAO.queryByTerm(keywords);
        if (termAttribute == null)
            return null;
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
            RandomAccessFile randomAccessFile = new RandomAccessFile(DATA_DIR + "doc_"+docId+".log", "r");
            String[] offset2 = offset1.split(",");
            StringBuilder viewContent = new StringBuilder();
            for (int j=0; j<Math.min(offset2.length, VIEW_NUM); j++) {
                long offsetValue = Long.valueOf(offset2[j]);
                long start = offsetValue - VIEW_LEN;
                if (start < 0) start = 0;
                long end = offsetValue + VIEW_LEN + keywords.length();
                if (end >= randomAccessFile.length()/2) end = randomAccessFile.length()/2;
                randomAccessFile.seek(start*2);
                for (long k=start; k<end; k++) {
                    if (k == offsetValue+1) {
                        viewContent.append("<font color='red'>");
                    }
                    if (k == offsetValue+keywords.length()+1) {
                        viewContent.append("</font>");
                    }
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
