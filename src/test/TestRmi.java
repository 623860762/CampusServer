/**
 * 
 */
package test;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;

import rmi.dataSource.DataSourceRmi;

/**
 * 
 * 
 * @author MYC
 */
public class TestRmi {

	/**
	 * @param args
	 * @throws Exception 
	 * @throws RemoteException 
	 * @throws AccessException 
	 */
	public static void main(String[] args) throws Exception {
		Registry registry = LocateRegistry.getRegistry("10.210.208.44", 1098);
		DataSourceRmi uri = (DataSourceRmi) registry.lookup("Campus");

		HashMap<String, ArrayList<String>> infos = new HashMap<String, ArrayList<String>>();
		
		ArrayList<String> schoolsIDs = new ArrayList<String>();
		schoolsIDs.add("243966");
		infos.put("SchoolID", schoolsIDs);
		
		ArrayList<String> people = new ArrayList<String>();
		people.add("帅哥");
		infos.put("People", people);
		
		
		ArrayList<String> uid = new ArrayList<String>();
		uid.add("1060715975");
		infos.put("UID", uid);
		
		long a = System.currentTimeMillis();
		System.out.println(uri.getData(infos));
		long b = System.currentTimeMillis();
		System.out.print(b-a);
	}

}
