package xml;

import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jdom.Element;


public class XMLRmiInfo extends XMLFile {
	
	private Map<String, Map<String, String>> classInfo = new IdentityHashMap<String, Map<String, String>>();
	
	public XMLRmiInfo(String xmlPath){
		readClassNameInfo(xmlPath);
	}

	private void readClassNameInfo(String xmlPath){
		readFile(xmlPath);
		List list = root.getChildren();
		for(int i=0;i<list.size();i++){
			Element element=(Element)list.get(i);
			String className = element.getAttributeValue("className");
			IdentityHashMap<String, String> info = new IdentityHashMap<String, String>();
			List childList = element.getChildren();
			Iterator iter = childList.iterator();
			while(iter.hasNext()){
				Element el = (Element)iter.next();
				info.put(el.getName(), el.getTextTrim());
			}
			classInfo.put(className, info);
			
		}
	}
	
	public Map<String, Map<String, String>> getClassInfo(){
		return classInfo;
	}
	
	public static void main(String[] args){
		
		XMLRmiInfo xmlRmiInfo = new XMLRmiInfo("conf/RmiTag.xml");
		
		Map<String, Map<String, String>> classInfo = xmlRmiInfo.getClassInfo();
		Iterator infoClassMap = classInfo.entrySet().iterator();
		while(infoClassMap.hasNext()){
			Map.Entry entry = (Map.Entry)infoClassMap.next();
			System.out.println(entry.getKey()+" "+ entry.getValue());
		}
		
		System.out.println();

	}
	
}
