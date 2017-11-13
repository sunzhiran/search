package edu.bjut.search.extactor;

public enum SourceTypeEnum {
    HTML_LOCAL(1, "", "本地HTML"),
    HTML_ONLINE(2, "", "在线HTML");

    int value;
    String implClass;
    String comment;
    SourceTypeEnum(int value, String implClass, String comment) {
        this.value = value;
        this.implClass = implClass;
        this.comment = comment;
    }
}
