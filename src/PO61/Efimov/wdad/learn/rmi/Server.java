package PO61.Efimov.wdad.learn.rmi;

import PO61.Efimov.wdad.data.managers.PreferencesManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Server
{
    private static XmlDataManagerImpl xmlDataManager;
    static private PreferencesManager preferencesManager;

    static private String createRegistry;
    static private String policyPath;
    static private String codebaseUrl;
    static private String registryPort;
    static private String registryAddres;
    static private int executorPort;
    static private String useCodebaseOnly;
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        preferencesManager = PreferencesManager.getInstance();
        xmlDataManager = new XmlDataManagerImpl();
        policyPath = preferencesManager.GetPolicyPath();
        codebaseUrl = preferencesManager.GetUseCodeBaseOnly();

        registryPort = preferencesManager.GetRegistryPort();
        registryAddres = preferencesManager.GetRegistryAddress();
        createRegistry = preferencesManager.GetCreateRegistry();

        xmlDataManager = new XmlDataManagerImpl();
    }
}
