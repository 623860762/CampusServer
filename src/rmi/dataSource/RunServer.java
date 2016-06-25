package rmi.dataSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import xml.XMLRmiInfo;

public class RunServer {
	
	public static void main(String[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, RemoteException, MalformedURLException{
		
		String[] prompts = {"XML Dir:"}; 
		String xmlDir="";

		if(args.length>=1){
			xmlDir=args[0];
		}else{
			InputStreamReader isr = new InputStreamReader(System.in);        
			BufferedReader br = new BufferedReader(isr);	
			
			int idx=0;
			String sen="";
			while(idx<1){
				try{
					System.out.print(prompts[idx]);
					sen = br.readLine();
					if(sen.trim().length()>2){
						switch(idx){
							case 0: xmlDir=sen; break;						
						}
						idx++;
					}
				}catch(Exception e){
					e.printStackTrace();
				}			
			}
			
		}
		
		XMLRmiInfo xmlInfo = new XMLRmiInfo(xmlDir);
		Map<String, Map<String, String>> rmiInfo = xmlInfo.getClassInfo();
		Iterator iterator = rmiInfo.entrySet().iterator();
		Map.Entry entry = (Map.Entry) iterator.next();
		String className =(String)entry.getKey();
		Map<String, String> classMap = (Map<String, String>)entry.getValue();
		String ip = classMap.get("ip");
		String port = classMap.get("port");
		String name = classMap.get("name");
		
		
		// TODO 需修改问题
		Class newClass = Class.forName(className);
		Class[] classArray ={String.class,String.class};
		Constructor con = newClass.getConstructor(classArray);
		DataSourceRmi dsr = (DataSourceRmi)con.newInstance(classMap.get("url"),classMap.get("wbOpenApi"));
		java.rmi.Naming.rebind("rmi://" + ip + ":" + port + "/" + name, dsr);
		
	
		System.out.println(className+" "+" is Ready on " + ip + ":" + port +"/" + name +" "+new java.util.Date());
	}

}
