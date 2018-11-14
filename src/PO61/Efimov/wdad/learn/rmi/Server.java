package PO61.Efimov.wdad.learn.rmi;

import PO61.Efimov.wdad.data.managers.PreferencesManager;
import PO61.Efimov.wdad.utils.PreferencesManagerConstants;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server
{
    private static XmlDataManagerImpl xmlDataManager;
    static private PreferencesManager preferencesManager;
    static final private String EXECUTOR_NAME = "xmlDataManager";

    static private String createRegistry;
    static private String policyPath;
    static private String codebaseUrl;
    static private int registryPort;
    static private String registryAddres;
    static private int executorPort;
    static private String useCodebaseOnly;
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, URISyntaxException {
        preferencesManager = PreferencesManager.getInstance();
        xmlDataManager = new XmlDataManagerImpl();
        policyPath = preferencesManager.getProperty(PreferencesManagerConstants.POLICY_PATH);
        useCodebaseOnly = preferencesManager.getProperty(PreferencesManagerConstants.USE_CODE_BASE_ONLY);
        registryPort = Integer.parseInt(preferencesManager.getProperty(PreferencesManagerConstants.REGISTRY_PORT));
        registryAddres = preferencesManager.getProperty(PreferencesManagerConstants.REGISTRY_ADDRESS);
        createRegistry = preferencesManager.getProperty(PreferencesManagerConstants.CREATE_REGISTRY);
        codebaseUrl = "file://".concat(Server.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        xmlDataManager = new XmlDataManagerImpl();
        System.setProperty("java.rmi.server.codebase", codebaseUrl);
        System.setProperty("java.rmi.server.useCodebaseOnly", useCodebaseOnly);
        System.setProperty("java.rmi.server.logCalls", "true");
        System.setProperty("java.security.policy", policyPath);
        System.setProperty("java.rmi.server.hostname", registryAddres);
        System.setSecurityManager(new SecurityManager());
        Registry registry = null;
        try{
            if(preferencesManager.getProperty(PreferencesManagerConstants.CREATE_REGISTRY).equals("yes"))
                registry = LocateRegistry.createRegistry(registryPort);
            else registry = LocateRegistry.getRegistry(registryPort);
        }catch (RemoteException e){
            e.printStackTrace();
        }

        try {
            System.out.println("exporting object...");
            UnicastRemoteObject.exportObject(xmlDataManager, executorPort);
            registry.rebind(EXECUTOR_NAME, xmlDataManager);
            System.out.println("idl-ing");
        } catch (RemoteException re) {
            System.err.println("cant export or bind object");
            re.printStackTrace();
        }
    }
}
