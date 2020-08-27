package API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Tools.JsonReader;


/**
 * The class used to interface with UVI API
 */
public class UVIAPI {

	/**
	 * URL to get the UVI value from repository
	 */
	private final String url = "https://api.data.gov.sg/v1/environment/uv-index";
	
	//Singleton design pattern
	/**
	 *  The UVIAPI object instance, used as singleton
	 */
	private static final UVIAPI INSTANCE = new UVIAPI();

	/**
	 * The function to get the UVIAPI object
	 * @return UVIAPI object
	 */
	public static UVIAPI getInstance() {
		return INSTANCE;
	}

	/**
	 * A function to obtain UVI value from repository
	 * @return UVI value
	 */
	public int requestUVIndex() {
		
		try {
			JSONObject result = JsonReader.readJsonFromUrl(this.url);
			
			if (result.length() == 0) {
				return -1;
			}
			
			JSONArray items = (JSONArray) result.get("items");
			JSONObject item = (JSONObject) items.get(0);
			JSONArray indices = (JSONArray) item.get("index");
			JSONObject latest = (JSONObject) indices.get(0);
			return latest.getInt("value");
		} 
		catch (JSONException e) {
			e.printStackTrace();
			return -1;
		}
		
	}

//	Testing and debugging
//	public static void main(String[] args) throws IOException, JSONException, RequestError {
//		System.out.println(UVIAPI.getInstance().requestUVIndex());
//	}
	
}
