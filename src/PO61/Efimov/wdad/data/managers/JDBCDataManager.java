package PO61.Efimov.wdad.data.managers;

import PO61.Efimov.wdad.data.storage.DataSource;
import PO61.Efimov.wdad.data.storage.DataSourceFactory;
import PO61.Efimov.wdad.learn.xml.Note;
import PO61.Efimov.wdad.learn.xml.User;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.*;

public class JDBCDataManager implements DataManager
{

    @Override
    public Note getNote(User owner, String title) throws IOException, ClassNotFoundException, SQLException, ParserConfigurationException, SAXException {
        DataSource ds = DataSourceFactory.createDataSource();
        Connection con = null;
        //todo PreparedStatement;
        Statement stmt = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Note note = new Note();
        User user = new User();
        try {
            con = ds.getConnection();

            statement = con.prepareStatement("SELECT title, creation_date, text, users.name " +
                    "FROM notes INNER JOIN users ON notes.author_id = users.id " +
                    "WHERE notes.title = ? AND users.name = ?");
            statement.setString(1, title);
            statement.setString(2, owner.GetName());

            rs = statement.executeQuery();
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
                if(statement != null) statement.close();
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
        //todo preparedStatement
        Statement stmt = null;
        PreparedStatement statement = null;
        Note note = new Note();
        User user = new User();
        try {
            con = ds.getConnection();

            statement = con.prepareStatement("UPDATE notes " +
                    "SET text = ? " +
                    "WHERE title = ? AND " +
                    "author_id = (SELECT id FROM users WHERE name = ?)");
            statement.setString(1, newText);
            statement.setString(2, title);
            statement.setString(3, owner.GetName());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(statement != null) statement.close();
                if(con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setPrivileges(String noteTitle, User user, int newRights) throws RemoteException, IOException, ClassNotFoundException, SQLException, ParserConfigurationException, SAXException {
        DataSource ds = DataSourceFactory.createDataSource();
        Connection con = null;
        Statement stmt = null;
        PreparedStatement statement = null;
        try {
            con = ds.getConnection();

            statement = con.prepareStatement("UPDATE user_privileges " +
                    "SET privilege = ? " +
                    "WHERE notes_id = (SELECT id FROM notes WHERE title = ?) AND " +
                    "users_id = (SELECT id FROM users WHERE name = ?)");
            statement.setString(1, String.valueOf(newRights));
            statement.setString(2, noteTitle);
            statement.setString(3, user.GetName());

            statement.executeUpdate();
            //todo preparedStatement
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(statement != null) statement.close();
                if(con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
