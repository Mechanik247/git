package PO61.Efimov.wdad.data.storage;

import PO61.Efimov.wdad.data.managers.PreferencesManager;
import PO61.Efimov.wdad.utils.PreferencesManagerConstants;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DataSourceFactory
{
    static private PreferencesManager preferencesManager;

    static private String className;
    static private String driverType;
    static private String hostName;
    static private String port;
    static private String DBName;
    static private String user;
    static private String pass;

    public static DataSource createDataSource() throws ClassNotFoundException, SQLException, IOException, SAXException, ParserConfigurationException {
        preferencesManager = PreferencesManager.getInstance();
        className = preferencesManager.getProperty(PreferencesManagerConstants.CLASS_NAME);
        driverType = preferencesManager.getProperty(PreferencesManagerConstants.DRIVER_TYPE);
        hostName = preferencesManager.getProperty(PreferencesManagerConstants.HOST_NAME);
        port = preferencesManager.getProperty(PreferencesManagerConstants.PORT);
        DBName = preferencesManager.getProperty(PreferencesManagerConstants.DB_NAME);
        user = preferencesManager.getProperty(PreferencesManagerConstants.USER);
        pass = preferencesManager.getProperty(PreferencesManagerConstants.PASS);
        //todo здесь нужно использовать класс, реализующий интерфейс DataSource, из комплекта jdbc твоего вендора СУБД
        DataSource dataSource = new DataSource();
        dataSource.setUrl("jdbc:" + driverType + "://" + hostName + ":" + port + "/" + DBName +
                "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC");
        dataSource.setLogin(user);
        dataSource.setPassword(pass);
        return dataSource;
    }
    public static DataSource createDataSource(String className, String
            driverType, String hostName, int port, String DBName, String user, String pass)
    {
        //todo здесь нужно использовать класс, реализующий интерфейс DataSource, из комплекта jdbc твоего вендора СУБД
        DataSource dataSource = new DataSource();
        dataSource.setUrl("jdbc:" + driverType + "://" + hostName + ":" + port + "/" + DBName +
                "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC");
        dataSource.setLogin(user);
        dataSource.setPassword(pass);
        return dataSource;
    }
}
