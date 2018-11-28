package PO61.Efimov.wdad.data.storage;

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
        DataSource ds = DataSourceFactory.createDataSource();

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = ds.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select id, title, creation_date from notes");
            while(rs.next()){
                System.out.println("id: "+rs.getInt("id")+", title: "+rs.getString("title")+", creation_date: "+rs.getString("creation_date"));
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
    }
}
