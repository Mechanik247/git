package PO61.Efimov.wdad.learn.xml;

import java.io.Serializable;

public class Note implements Serializable {
    private String title;
    private String text;
    private String privileges;
    private String cdate;
    private User owner;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getPrivileges() {
        return privileges;
    }

    public String getCdate() {
        return cdate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }
}