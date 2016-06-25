package weibo4j.model;

import weibo4j.Weibo;
import weibo4j.http.Response;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;
import weibo4j.util.WeiboConfig;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.HashMap;

public class ShortUrl extends WeiboResponse implements java.io.Serializable {
	private static final long serialVersionUID = -332738032647843485L;
	HashMap<String,String>urlMap = new HashMap<String,String>(); 
	
	/*package*/
	public ShortUrl(Response res) throws WeiboException {
		super(res);
		JSONObject json =res.asJSONObject();
		try {
			JSONArray urls = json.getJSONArray("urls");
			
			if(urls!=null){
				for(int i=0;i<urls.length();i++){
					JSONObject shortUrl = (JSONObject)urls.get(i);
					String url_long = shortUrl.getString("url_long"); 
					String url_short = shortUrl.getString("url_short");
					urlMap.put(url_long, url_short);
				}
			}else{
				throw new WeiboException(json.toString());
			}
			
		} catch (JSONException je) {
			throw new WeiboException(je.getMessage() + ":" + json.toString(), je);
		}
	}

	public ShortUrl(JSONObject json)throws WeiboException, JSONException{
		try {
			JSONArray urls = json.getJSONArray("urls");
			
			if(urls!=null){
				for(int i=0;i<urls.length();i++){
					JSONObject shortUrl = (JSONObject)urls.get(i);
					String url_long = shortUrl.getString("url_long"); 
					String url_short = shortUrl.getString("url_short");
					urlMap.put(url_long, url_short);
				}
			}else{
				throw new WeiboException(json.toString());
			}
			
		} catch (JSONException je) {
			throw new WeiboException(je.getMessage() + ":" + json.toString(), je);
		}
	}

	public ShortUrl(String str) throws WeiboException, JSONException {
		// StatusStream uses this constructor
		super();
		JSONObject json = new JSONObject(str);
		try {
			JSONArray urls = json.getJSONArray("urls");
			
			if(urls!=null){
				for(int i=0;i<urls.length();i++){
					JSONObject shortUrl = (JSONObject)urls.get(i);
					String url_long = shortUrl.getString("url_long"); 
					String url_short = shortUrl.getString("url_short");
					urlMap.put(url_long, url_short);
				}
			}else{
				throw new WeiboException(json.toString());
			}
			
		} catch (JSONException je) {
			throw new WeiboException(je.getMessage() + ":" + json.toString(), je);
		}
	}
	
	public final HashMap<String,String> getShortUrlMap(){
		return this.urlMap;
	}
	
	public String getShortUrl(String longurl){
		return this.urlMap.get(longurl);
	}

	public static ShortUrl createShortUrl(ArrayList<String> longurls) throws WeiboException {
		PostParameter[] pars =  new PostParameter[longurls.size()];
		for(int i=0;i<longurls.size();i++){
			pars[i] = new PostParameter("url_long", longurls.get(i));
		}
		return new ShortUrl(
				Weibo.client.post(
					WeiboConfig.getValue("baseURL")	+ "short_url/shorten.json",//http://i2.api.weibo.com/2/short_url/shorten.json
					pars
					)
				);
	}
	
}
