package edu.bjut.search.extactor;

public class DocText {

    private String title;
    private String content;
    private String source;

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

    @Override
    public String toString() {
        return "DocText{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
