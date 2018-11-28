package PO61.Efimov.wdad.data.managers;

import PO61.Efimov.wdad.learn.xml.Note;
import PO61.Efimov.wdad.learn.xml.User;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface DataManager
{
    public Note getNote(User owner, String title) throws IOException, ClassNotFoundException, SQLException, ParserConfigurationException, SAXException;

    public void updateNote(User owner, String title,StringBuilder newText) throws RemoteException, IOException;
    public void setPrivileges(String noteTitle,User user,int newRights) throws RemoteException, IOException;
}
