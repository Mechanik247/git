package PO61.Efimov.wdad.learn.rmi;

import PO61.Efimov.wdad.data.managers.PreferencesManager;
import PO61.Efimov.wdad.utils.PreferencesManagerConstants;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client
{
    static private String policyPath;
    static private String codebaseUrl;
    static private int registryPort;
    static final private String EXECUTOR_NAME = "xmlDataManager";

    static private PreferencesManager preferencesManager;
    static private String registryAddres;
    static private String useCodebaseOnly;

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, URISyntaxException {
        preferencesManager = PreferencesManager.getInstance();
        registryPort = Integer.parseInt(preferencesManager.getProperty(PreferencesManagerConstants.REGISTRY_PORT));
        registryAddres = preferencesManager.getProperty(PreferencesManagerConstants.REGISTRY_ADDRESS);
        codebaseUrl = "file://".concat(Client.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());

        policyPath = preferencesManager.getProperty(PreferencesManagerConstants.POLICY_PATH);
        useCodebaseOnly = preferencesManager.getProperty(PreferencesManagerConstants.USE_CODE_BASE_ONLY);

        System.setProperty("java.rmi.server.codebase", codebaseUrl);
        System.setProperty("java.rmi.server.useCodebaseOnly", useCodebaseOnly);
        System.setProperty("java.security.policy", policyPath);

        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(registryAddres, registryPort);
        } catch (RemoteException re) {
            System.err.println("cant locate registry");
            re.printStackTrace();
        }
        if (registry != null) {
            try {
                Remote remote = registry.lookup(EXECUTOR_NAME);
                test((XmlDataManager) remote);
            } catch (RemoteException re) {
                System.err.println("something unbelievable happens");
                re.printStackTrace();
            } catch (Exception e) {
                System.err.println("user input err");
                e.printStackTrace();
            }
        }
    }
}
