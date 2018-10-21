package PO61.Efimov.wdad.learn.xml;

public class User
{
	private String name;
	private String mail;
	private String rights;
	
	public String GetName()
	{
		return name;
	}
	public void SetName(String name)
	{
		this.name = name;
	}
	public String GetMail()
	{
		return mail;
	}
	public void SetMail(String mail)
	{
		this.mail = mail;
	}
	public String GetRights()
	{
		return rights;
	}
	public void SetRights(String rights)
	{
		this.rights = rights;
	}
	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder();
		str.append("Name - ").append(GetName()).append('\n');
		str.append("Mail - ").append(GetMail()).append('\n');
		str.append("Rights - ").append(GetRights()).append('\n');
		return str.toString();
	}
}