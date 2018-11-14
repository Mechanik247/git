package PO61.Efimov.wdad.learn.rmi;

import PO61.Efimov.wdad.learn.xml.User;
import PO61.Efimov.wdad.learn.xml.Note;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface XmlDataManager extends Remote
{
    public Note getNote(User owner,String title) throws RemoteException;

    public void updateNote(User owner, String title,StringBuilder newText) throws RemoteException, IOException;
    public void setPrivileges(String noteTitle,User user,int newRights) throws RemoteException, IOException;
    //public ArrayList<Note> getNotes(User owner);
}
