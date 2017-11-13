package edu.bjut.search.extactor;

public enum SourceTypeEnum {
    HTML_LOCAL(1, "edu.bjut.search.extactor.LocalHtmlExtractor", "本地HTML"),
    HTML_ONLINE(2, "edu.bjut.search.extactor.OnlineHtmlExtractor", "在线HTML"),
    ;

    private int value;
    private String implClass;
    private String comment;
    SourceTypeEnum(int value, String implClass, String comment) {
        this.value = value;
        this.implClass = implClass;
        this.comment = comment;
    }

    public int getValue() {
        return value;
    }

    public String getImplClass() {
        return implClass;
    }

    public String getComment() {
        return comment;
    }
}
