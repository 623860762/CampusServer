package xml;

import java.io.FileOutputStream; 
import java.io.IOException; 

import org.jdom.Document; 
import org.jdom.Element; 
import org.jdom.JDOMException; 
import org.jdom.input.SAXBuilder; 
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter; 


public class XMLFile {
	protected String  xmlfile;
	protected Document xmldoc;
	protected Element root;
	

	public boolean readFile(String xmlpath){ 
		SAXBuilder builder=new SAXBuilder(false); 
		try { 
			xmlfile = xmlpath;
			xmldoc=builder.build(xmlpath); 
			root=xmldoc.getRootElement(); 
			
			/*
			List booklist=books.getChildren("book");			
			for (Iterator iter = booklist.iterator(); iter.hasNext();) { 
				Element book = (Element) iter.next(); 
				String email=book.getAttributeValue("email"); 
				System.out.println(email); 
				String name=book.getChildTextTrim("name"); 
				System.out.println(name); 
				book.getChild("name").setText("alterrjzjh"); 
			} 
			XMLOutputter outputter=new XMLOutputter(); 
			outputter.output(doc,new FileOutputStream(xmlpath));
			*/ 
		} catch (JDOMException e) { 
			e.printStackTrace(); 
			return false;
		} catch (IOException e) { 
			e.printStackTrace();
			return false;
		} 

		return true;
	} 
	
	public boolean newXmlFile(String xmlpath,String rootName){
		this.xmlfile = xmlpath;
		this.xmldoc = new Document();   

		this.root = new Element(rootName); 
		this.xmldoc = new Document(root); 
		
		Format format = Format.getCompactFormat();
        format.setEncoding("utf-8"); 
        format.setLineSeparator("\\n");
        format.setIndent("    "); 
        
		return true;
	}
	
	public boolean saveFile(String savepath){
		try{
			XMLOutputter outputter=new XMLOutputter(); 
			outputter.output(xmldoc,new FileOutputStream(savepath)); 
		} catch (IOException e) { 
			e.printStackTrace();
			return false;
		} 

		return true;		
	}
	public  boolean saveFile(){
		return saveFile(xmlfile);
	}
	public Element getXMLRoot(){
		return this.root;
	}
	
}
