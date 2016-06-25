package rmi.dataSource;import java.rmi.RemoteException;import java.rmi.server.UnicastRemoteObject;import java.util.ArrayList;import java.util.HashMap;import manger.WeibotOpenApiManger;import model.ModelType;import org.apache.log4j.Logger;import weibo4j.http.HttpClient;import weibo4j.http.Response;import weibo4j.model.PostParameter;import weibo4j.org.json.JSONObject;public class Campus extends UnicastRemoteObject implements DataSourceRmi {	private static final long serialVersionUID = 1L;	private static Logger CampusLogger = Logger.getLogger(Campus.class);	private String url = "";	private HttpClient httpClient = new HttpClient();	public Campus(String url, String weibotOpenApi) throws Exception {		this.url = url;		this.httpClient.setToken("");		WeibotOpenApiManger.init(weibotOpenApi);	}	@Override	public String getData(HashMap<String, ArrayList<String>> infos)			throws RemoteException {		String retStr = "";		CampusLogger.info("getData\tparameter\t" + infos);		String schoolid = getParameter(infos, "SchoolID");		String uid = getParameter(infos, "UID");		String people = getParameter(infos, "People");		JSONObject jsonResponse = getResponse(schoolid, uid);		ModelType modelType = ModelType.getInstance(jsonResponse, people);				retStr = modelType.getInfo();		CampusLogger.info("getData\tresult\t" + retStr.toString());		return retStr;	}		private String getParameter(HashMap<String, ArrayList<String>> infos,			String key) {		String ret = "";		ArrayList<String> lists = infos.get(key);		if (lists != null) {			if (lists.size() >= 1) {				ret = lists.get(0);			}		}		return ret;	}//end getParameter		private JSONObject getResponse(String schoolid, String uid) {		CampusLogger.info("getResponse\tparameter\tschoolid=" + schoolid				+ "\tuid=" + uid);		JSONObject retJson = null;		PostParameter[] params = new PostParameter[] {				new PostParameter("schoolid", schoolid),				new PostParameter("ip", "10.55.22"),				new PostParameter("uid", uid) };		Response response = null;		try {			response = httpClient.get(url, params);			if (response != null) {				retJson = response.asJSONObject();			}		} catch (Exception e) {			e.printStackTrace();		}		CampusLogger.info("getResponse\tresult\t" + retJson.toString());		return retJson;	}//end getResponse	@Override	public ArrayList<String> getSelection(			HashMap<String, ArrayList<String>> infos, String field)			throws RemoteException {		return null;	}	/**	 * @param args	 */	public static void main(String[] args) throws Exception {		Campus campus = new Campus(				"http://data.i.t.sina.com.cn/schooluser/getschooluser.php?",				"10.210.208.41,1099,WeibotOpenApi");		HashMap<String, ArrayList<String>> infos = new HashMap<String, ArrayList<String>>();		ArrayList<String> schoolsIDs = new ArrayList<String>();		schoolsIDs.add("243966");		infos.put("SchoolID", schoolsIDs);		ArrayList<String> people = new ArrayList<String>();		people.add("帅哥");		infos.put("People", people);		ArrayList<String> uid = new ArrayList<String>();		uid.add("1060715975");		infos.put("UID", uid);		long a = System.currentTimeMillis();		System.out.println(campus.getData(infos));		long b = System.currentTimeMillis();				System.out.println(b-a);	}}