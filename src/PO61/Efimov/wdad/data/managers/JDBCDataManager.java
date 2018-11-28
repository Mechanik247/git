package PO61.Efimov.wdad.data.managers;

import PO61.Efimov.wdad.data.storage.DataSource;
import PO61.Efimov.wdad.data.storage.DataSourceFactory;
import PO61.Efimov.wdad.learn.xml.Note;
import PO61.Efimov.wdad.learn.xml.User;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDataManager implements DataManager
{

    @Override
    public Note getNote(User owner, String title) throws IOException, ClassNotFoundException, SQLException, ParserConfigurationException, SAXException {
        DataSource ds = DataSourceFactory.createDataSource();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Note note = new Note();
        User user = new User();
        try {
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT title, creation_date, text, users.name " +
                    "FROM notes INNER JOIN users ON notes.author_id = users.id " +
                    "WHERE notes.title = '" + title +
                    "' AND users.name = '" + owner.GetName() +"'");
            while(rs.next()){
                note.setTitle(rs.getString("title"));
                note.setCdate(rs.getString("creation_date"));
                note.setText(rs.getString("text"));
                user.SetName(rs.getString("name"));
                note.setOwner(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return note;
    }

    @Override
    public void updateNote(User owner, String title, String newText) throws RemoteException, IOException, ClassNotFoundException, SQLException, ParserConfigurationException, SAXException {
        DataSource ds = DataSourceFactory.createDataSource();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Note note = new Note();
        User user = new User();
        try {
            con = ds.getConnection();
            stmt = con.createStatement();
            stmt.executeUpdate("UPDATE notes " +
                    "SET text = '" + newText + "' " +
                    "WHERE title = '"+title+"' AND " +
                    "author_id = (SELECT id FROM users WHERE name = '"+owner.GetName()+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setPrivileges(String noteTitle, User user, int newRights) throws RemoteException, IOException {

    }
}
