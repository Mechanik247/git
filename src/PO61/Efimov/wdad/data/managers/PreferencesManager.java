package PO61.Efimov.wdad.data.managers;

import PO61.Efimov.wdad.utils.PreferencesManagerConstants;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public final class PreferencesManager {
    private static PreferencesManager instance;
    Element root;
    List<Element> nodeListElements;
    XMLOutputter xmlOut;
    org.jdom2.Document jdomDocument;
    String fileName = "";

    public PreferencesManager() throws IOException, SAXException, ParserConfigurationException {
        fileName = "C:\\Users\\User\\IdeaProjects\\git\\src\\PO61\\Efimov\\wdad\\resources\\configuration\\appconfig.xml";
        jdomDocument = createJDOMusingDOMParser(fileName);
        xmlOut = new XMLOutputter();
        root = jdomDocument.getRootElement();
    }

    public static PreferencesManager getInstance() throws ParserConfigurationException, SAXException, IOException {
        if (instance == null) {
            instance = new PreferencesManager();
        }
        return instance;
    }

    private static org.jdom2.Document createJDOMusingDOMParser(String fileName)
            throws ParserConfigurationException, SAXException, IOException {
        //создаем DOM Document
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        documentBuilder = dbFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(new File(fileName));
        DOMBuilder domBuilder = new DOMBuilder();

        return domBuilder.build(doc);

    }

    @Deprecated
    public String GetCreateRegistry()
    {
        return root.getChild("rmi").getChild("server").getChild("registry").getChildText("createregistry");
    }
    @Deprecated
    public void SetCreateRegistry(String createregistry) throws IOException {
        root.getChild("rmi").getChild("server").getChild("registry").getChild("createregistry").setText(createregistry);
        xmlOut.setFormat(Format.getPrettyFormat());
        xmlOut.output(jdomDocument, new FileWriter(fileName));
    }
    @Deprecated
    public String GetRegistryAddress()
    {
        return root.getChild("rmi").getChild("server").getChild("registry").getChildText("registryaddress");
    }
    @Deprecated
    public void SetRegistryAddress(String registryaddress) throws IOException {
        root.getChild("rmi").getChild("server").getChild("registry").getChild("registryaddress").setText(registryaddress);
        xmlOut.setFormat(Format.getPrettyFormat());
        xmlOut.output(jdomDocument, new FileWriter(fileName));
    }
    @Deprecated
    public String GetRegistryPort()
    {
        return root.getChild("rmi").getChild("server").getChild("registry").getChildText("registryport");
    }
    @Deprecated
    public void SetRegistryPort(String registryport) throws IOException {
        root.getChild("rmi").getChild("server").getChild("registry").getChild("registryport").setText(registryport);
        xmlOut.setFormat(Format.getPrettyFormat());
        xmlOut.output(jdomDocument, new FileWriter(fileName));
    }
    @Deprecated
    public String GetPolicyPath()
    {
        return root.getChild("rmi").getChild("client").getChildText("policypath");
    }
    @Deprecated
    public void SetPolicyPath(String policypath) throws IOException {
        root.getChild("rmi").getChild("client").getChild("policypath").setText(policypath);
        xmlOut.setFormat(Format.getPrettyFormat());
        xmlOut.output(jdomDocument, new FileWriter(fileName));
    }
    @Deprecated
    public String GetUseCodeBaseOnly()
    {
        return root.getChild("rmi").getChild("client").getChildText("usecodebaseonly");
    }
    @Deprecated
    public void SetUseCodeBaseOnly(String usecodebaseonly) throws IOException {
        root.getChild("rmi").getChild("client").getChild("usecodebaseonly").setText(usecodebaseonly);
        xmlOut.setFormat(Format.getPrettyFormat());
        xmlOut.output(jdomDocument, new FileWriter(fileName));
    }
    @Deprecated
    public String GetClassProvider()
    {
        return root.getChild("rmi").getChildText("classprovider");
    }
    @Deprecated
    public void SetClassProvider(String classprovider) throws IOException {
        root.getChild("rmi").getChild("classprovider").setText(classprovider);
        xmlOut.setFormat(Format.getPrettyFormat());
        xmlOut.output(jdomDocument, new FileWriter(fileName));
    }


    public void setProperty(String key, String value)
    {
        String[] tags = key.split(".");
        Element element = null;
        for(String s : tags)
        {
            element = root.getChild(s);
        }
        element.setText(value);
    }
    public String getProperty(String key)
    {
        return "null";
    }
    public void setProperties(Properties prop)
    {
        prop.stringPropertyNames().forEach(s -> setProperty(s,prop.getProperty(s)));
    }
    public Properties getProperties()
    {
        Properties properties = new Properties();
        String[] keys = {PreferencesManagerConstants.CLASS_PROVIDER,PreferencesManagerConstants.CREATE_REGISTRY,
                PreferencesManagerConstants.POLICY_PATH, PreferencesManagerConstants.REGISTRY_ADDRESS,
                PreferencesManagerConstants.USE_CODE_BASE_ONLY, PreferencesManagerConstants.REGISTRY_PORT};
        for(String s : keys){
            properties.setProperty(s,document.getElementsByTagName(lastElementKey(s)).item(0).getTextContent());
        }
        return properties;
    }
    public void addBindedObject(String name, String className)
    {

    }
    public void removeBindedObject(String name)
    {

    }
}