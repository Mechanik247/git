package PO61.Efimov.wdad.learn.xml;

public class Note {
    private String title;
    private String text;
    private String privileges;
    private String cdate;
    private User owner;

    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("Title - ").append(getTitle()).append('\n');
        str.append("Text - ").append(getText()).append('\n');
        str.append("Privileges - ").append(getPrivileges()).append('\n');
        str.append("cdate - ").append(getCdate()).append('\n');
        str.append("Owner:\n").append(getOwner().toString()).append('\n');
        return str.toString();
    }

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