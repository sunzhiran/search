package edu.bjut.search.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocAttribute {

    private static Logger logger = LoggerFactory.getLogger(DocAttribute.class);

    private Integer docId;
    private String link;
    private String title;
    private String docType;
    private String view;

    public Integer getDocId() {
        return docId;
    }

    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}
