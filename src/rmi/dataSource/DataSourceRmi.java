package rmi.dataSource;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface DataSourceRmi extends Remote {
	
	public String getData(final HashMap<String,ArrayList<String>> infos) throws RemoteException;
	
	public ArrayList<String> getSelection(final HashMap<String,ArrayList<String>> infos,
			final String field) throws RemoteException;
	
}
