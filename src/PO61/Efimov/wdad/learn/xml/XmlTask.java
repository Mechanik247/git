package PO61.Efimov.wdad.learn.xml;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.DOMBuilder;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.StAXEventBuilder;
import org.jdom2.input.StAXStreamBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlTask
{
    public static void main(String[] args) throws JDOMException, IOException {
        String fileName = "F:/students.xml";
        try {
            // мы можем создать экземпляр JDOM Document из классов DOM, SAX и STAX Builder
            org.jdom2.Document jdomDocument = createJDOMusingDOMParser(fileName);
            Element root = jdomDocument.getRootElement();
            // получаем список всех элементов Student
            List<Element> studListElements = root.getChildren("Student");
            // список объектов Student, в которых будем хранить
            // считанные данные по каждому элементу
            List<Student> students = new ArrayList<>();
            for (Element studentEl : studListElements) {
                Student student = new Student();
                student.setId(Integer.parseInt(studentEl.getAttributeValue("id")));
                student.setAge(Integer.parseInt(studentEl.getChildText("age")));
                student.setName(studentEl.getChildText("name"));
                student.setLanguage(studentEl.getChildText("language"));

                students.add(student);
            }
            // печатаем полученный список объектов Student
            for (Student student : students) {
                System.out.println(student.toString());
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

    }

    public void updateNote(User owner, String title, String newText)
    {

    }

    public void setPrivileges(String noteTitle, User user, int newRights)
    {

    }
}