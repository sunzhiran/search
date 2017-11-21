package edu.bjut.search.extactor;

public class DocText {

    private String title;
    private String content;
    private String source;
    private String docType;
    private String view;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    @Override
    public String toString() {
        return "DocText{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
