package PO61.Efimov.wdad.learn.rmi;

import PO61.Efimov.wdad.data.managers.PreferencesManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Server
{
    static private PreferencesManager preferencesManager;
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        preferencesManager = PreferencesManager.getInstance();

    }
}
