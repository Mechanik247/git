package PO61.Efimov.wdad.learn.xml;

import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlTask
{
    Element root;
    List<Element> nodeListElements;
    List<Note> notes;
    public XmlTask() throws IOException, SAXException, ParserConfigurationException {
        String fileName = "F:/students.xml";
        org.jdom2.Document jdomDocument = createJDOMusingDOMParser(fileName);
        Element root = jdomDocument.getRootElement();
        try {
            nodeListElements = root.getChildren("Note");
            notes = new ArrayList<>();
            for (Element noteEl : nodeListElements) {
                Note note = new Note();
                note.setTitle(noteEl.getChildText("title"));
                note.setText(noteEl.getChildText("text"));
                Owner owner = new Owner();
                owner.setMail(noteEl.getChild("owner").getAttributeValue("mail"));
                owner.setName(noteEl.getChild("owner").getAttributeValue("name"));
                note.setOwner(owner);

                notes.add(note);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public void updateNote(User owner, String title, String newText)
    {
        for (Element noteEl : nodeListElements) {
            if(noteEl.getChildText("title").equals(title) && noteEl.getChild("owner").getAttributeValue("name").equals(owner.GetName()) && noteEl.getChild("owner").getAttributeValue("mail").equals(owner.GetMail()))
               noteEl.getChild("text").setText(newText);
        }
    }

    public void setPrivileges(String noteTitle, User user, int newRights)
    {

    }
}