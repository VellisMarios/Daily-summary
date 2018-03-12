/**
 * 
 */
package Weather;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author dhoog
 *
 */

public class challenge {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String jsonString = callURL("http://api.wunderground.com/api/51c3b8e726d8ad79/history_20171030/q/NY/New_York.json");
 
// Replace this try catch block for all below subsequent examples
		try {  
                    
                    JSONObject jsonData = new JSONObject(jsonString);
                    JSONObject jsonData2= jsonData.getJSONObject("history");
                    //System.out.println("\n\njsonString: " + jsonData2.toString());
                    JSONArray jsonArray = jsonData2.getJSONArray("dailysummary");
                    //System.out.println("\n\njsonArray: " + jsonArray.toString(1));
                    JSONObject jsonData3 = jsonArray.getJSONObject(0);
                    System.out.println("\n\njsonString: " + jsonData3.toString());
                    System.out.println("\n\nmaxtempm: " + jsonData3.getString("maxtempm"));
                    System.out.println("\n\nmintempm: " + jsonData3.getString("mintempm"));
                    System.out.println("\n\nHumidity: " + jsonData3.getString("maxhumidity"));
                    System.out.println("\n\nprecipm: " + jsonData3.getString("precipm"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
 
	public static String callURL(String myURL) {
		System.out.println("Requested URL:" + myURL);
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
		in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:"+ myURL, e);
		} 
 
		return sb.toString();

    }
}