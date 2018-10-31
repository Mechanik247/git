package PO61.Efimov.wdad.learn.rmi;

import PO61.Efimov.wdad.learn.xml.User;
import PO61.Efimov.wdad.learn.xml.Note;

import java.rmi.Remote;
import java.util.List;

public interface XmlDataManager extends Remote
{
    public String getNote(User owner,String title);
    public void updateNote(User owner, String title,StringBuilder newText);
    public void setPrivileges(String noteTitle,User user,int newRights);
    public List<Note> getNotes(User owner);
}
