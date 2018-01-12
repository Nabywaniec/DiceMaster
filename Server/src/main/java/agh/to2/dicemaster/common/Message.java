package agh.to2.dicemaster.common;

public class Message {
    private String content;
    private Class contentClass;

    public Message(String content, Class contentClass) {
        this.content = content;
        this.contentClass = contentClass;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Class getContentClass() {
        return contentClass;
    }

    public void setContentClass(Class contentClass) {
        this.contentClass = contentClass;
    }
}
