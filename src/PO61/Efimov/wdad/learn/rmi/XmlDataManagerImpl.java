package PO61.Efimov.wdad.learn.rmi;

import PO61.Efimov.wdad.learn.xml.Note;
import PO61.Efimov.wdad.learn.xml.User;
import PO61.Efimov.wdad.learn.xml.XmlTask;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlDataManagerImpl implements XmlDataManager {

    XmlTask xmlTask;
    public XmlDataManagerImpl() throws ParserConfigurationException, SAXException, IOException
    {
        xmlTask = new XmlTask();
    }

    @Override
    public Note getNote(User owner, String title)
    {
        return xmlTask.getNote(owner,title);
    }

    @Override
    public void updateNote(User owner, String title, StringBuilder newText) throws IOException {
        xmlTask.updateNote(owner,title,newText);
    }

    @Override
    public void setPrivileges(String noteTitle, User user, int newRights) throws IOException {
        xmlTask.setPrivileges(noteTitle,user,newRights);
    }

    @Override
    public ArrayList<Note> getNotes(User owner) {
        return xmlTask.getNotes(owner);
    }
}
