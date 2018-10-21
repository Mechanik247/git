package PO61.Efimov.wdad.learn.xml;

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
import java.util.ArrayList;
import java.util.List;

public class XmlTask
{
    Element root;
    List<Element> nodeListElements;
    XMLOutputter xmlOut;
    org.jdom2.Document jdomDocument;
    String fileName = "";
    public XmlTask() throws IOException, SAXException, ParserConfigurationException {
        fileName = "C:\\Users\\User\\IdeaProjects\\git\\src\\PO61\\Efimov\\wdad\\learn\\xml\\notes.xml";
        jdomDocument = createJDOMusingDOMParser(fileName);
        xmlOut = new XMLOutputter();
        Element root = jdomDocument.getRootElement();
        try {
            nodeListElements = root.getChildren("note");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void PrintList()
    {
        Note note = null;
        for (Element noteEl : nodeListElements) {

            note = new Note();
            note.setTitle(noteEl.getChildText("title"));
            note.setText(noteEl.getChildText("text"));
            User owner = new User();
            owner.SetMail(noteEl.getChild("owner").getAttributeValue("mail"));
            owner.SetName(noteEl.getChild("owner").getAttributeValue("name"));
            note.setOwner(owner);
            System.out.println(note.toString());
        }

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

    public String getNoteText(User owner, String title)
    {
        String text = "";
        for (Element noteEl : nodeListElements) {
            if(noteEl.getChildText("title").equals(title) && noteEl.getChild("owner").getAttributeValue("name").equals(owner.GetName()) && noteEl.getChild("owner").getAttributeValue("mail").equals(owner.GetMail()))
            text = noteEl.getChildText("text");
        }


        return text;
    }

    public void updateNote(User owner, String title, String newText) throws IOException {
        for (Element noteEl : nodeListElements) {
            if(noteEl.getChildText("title").equals(title) && noteEl.getChild("owner").getAttributeValue("name").equals(owner.GetName()) && noteEl.getChild("owner").getAttributeValue("mail").equals(owner.GetMail()))
               noteEl.getChild("text").setText(newText);
        }
        xmlOut.setFormat(Format.getPrettyFormat());
        xmlOut.output(jdomDocument, new FileWriter(fileName));
    }

    public void setPrivileges(String noteTitle, User user, int newRights) throws IOException {
        for (Element noteEl : nodeListElements) {
            List<Element> usersElements = noteEl.getChild("privileges").getChildren("user");
            for(Element userEl : usersElements) {
                if (noteEl.getChildText("title").equals(noteTitle) && userEl.getAttributeValue("name").equals(user.GetName()) && userEl.getAttributeValue("mail").equals(user.GetMail()))
                    if (newRights == 0) {
                        userEl.detach();
                        break;
                    } else if (newRights == 1) {
                        userEl.getAttribute("rights").setValue("R");
                    } else if (newRights == 3) {
                        userEl.getAttribute("rights").setValue("RW");
                    }
            }
        }
        xmlOut.setFormat(Format.getPrettyFormat());
        xmlOut.output(jdomDocument, new FileWriter(fileName));
    }
}