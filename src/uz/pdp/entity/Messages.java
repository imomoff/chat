package uz.pdp.entity;

import java.io.Serializable;

public class Messages implements Serializable {
    private Integer id;
    private String text;
    private Integer fromId;

    public Messages(Integer id, String text, Integer fromId) {
        this.id = id;
        this.text = text;
        this.fromId = fromId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }
}
