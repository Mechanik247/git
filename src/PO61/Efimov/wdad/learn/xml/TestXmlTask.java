package PO61.Efimov.wdad.learn.xml;

import PO61.Efimov.wdad.data.managers.PreferencesManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TestXmlTask {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        String res = "";
        XmlTask xmlTask = new XmlTask();
        User owner = new User();
        owner.SetName("Name1");
        owner.SetMail("Mail1");
        res = xmlTask.getNoteText(owner,"Title1");
        //System.out.print(owner.GetName() + " " + owner.GetMail() + '\n');
        System.out.println(res);
        //xmlTask.PrintList();

        xmlTask.updateNote(owner, "Title1", "NewText2");

        User user = new User();
        user.SetName("Name31");
        user.SetMail("Mail31");
        xmlTask.setPrivileges("Title13",user,1);

        PreferencesManager preferencesManager = new PreferencesManager();
        System.out.println(preferencesManager.GetCreateRegistry());
        preferencesManager.SetCreateRegistry("yes");
    }
}
