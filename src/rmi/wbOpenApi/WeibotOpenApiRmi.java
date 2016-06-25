package rmi.wbOpenApi;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import weibo4j.model.DirectMessage;
import weibo4j.model.Status;
import weibo4j.model.Tag;
import weibo4j.model.User;
import weibo4j.org.json.JSONObject;

/**
 * 
 */

/**
 * 
 * 
 * @author MYC
 */
public interface WeibotOpenApiRmi extends Remote {
	
	public JSONObject getNearbyByGeo(String pLat,String pLong,String pCategory,String pAddress) throws RemoteException;
	
	public JSONObject getUserTimelineByUID(String pUid,Integer pPage,Integer pBase_app,Integer pFeature) throws RemoteException;

	public User getUserInfoById(String pUid) throws RemoteException;

	public User getUserInfoByScreenName(String pScreenName) throws RemoteException;
	
	public List<Tag> getUserTagByID(String pUid) throws RemoteException;
	
	public List<Tag> getUserTagByScreenName(String pScreenName) throws RemoteException;
	
	public JSONObject getDMisCapable(String pScreenName) throws RemoteException;
	
	public DirectMessage postDirectMessage(String uid,String text) throws RemoteException;

	public Status postWeibo(String msg) throws RemoteException;
	
	public int userRelationship(String pUidA,String pUidB) throws RemoteException;

}
