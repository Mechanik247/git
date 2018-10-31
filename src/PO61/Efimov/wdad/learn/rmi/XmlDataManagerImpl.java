package PO61.Efimov.wdad.learn.rmi;

import PO61.Efimov.wdad.learn.xml.Note;
import PO61.Efimov.wdad.learn.xml.User;

import java.util.List;

public class XmlDataManagerImpl implements XmlDataManager {
    @Override
    public String getNote(User owner, String title) {
        return null;
    }

    @Override
    public void updateNote(User owner, String title, StringBuilder newText) {

    }

    @Override
    public void setPrivileges(String noteTitle, User user, int newRights) {

    }

    @Override
    public List<Note> getNotes(User owner) {
        return null;
    }
}
