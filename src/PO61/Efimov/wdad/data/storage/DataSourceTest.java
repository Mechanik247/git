package PO61.Efimov.wdad.data.storage;

import PO61.Efimov.wdad.data.managers.JDBCDataManager;
import PO61.Efimov.wdad.learn.xml.Note;
import PO61.Efimov.wdad.learn.xml.User;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSourceTest
{
    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParserConfigurationException, SAXException, IOException {

        testDataSource();

    }
    private static void testDataSource() throws ClassNotFoundException, SQLException, ParserConfigurationException, SAXException, IOException {
        JDBCDataManager jdbcDataManager = new JDBCDataManager();
        User user = new User();
        user.SetName("Kalyvan");
        //Note note = jdbcDataManager.getNote(user,"Kalymim");

        User owner = new User();
        owner.SetName("Kalyvan");
        jdbcDataManager.updateNote(owner,"Kalymim","newwww");
        //System.out.println(note.toString());
    }
}
