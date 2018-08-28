package tw.idv.aloha.lineBot.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GMaps_Text_Search {
	// 關鍵字搜尋
	
	public static String getURL(String latitude, String longitude){
		 String URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?"
				+ "key=AIzaSyB2ZeC9Pb8EW7rWgimJBczrWozGhCLz-u4"
				+ "&location=" + latitude + "," + longitude
				+ "&rankby=distance&type=restaurant&language=zh-TW";
		 return URL;
	}
	
	public static List<Map> gMapSearch(String URL){
		Gson gson = new Gson();
		List<Map> searchList = new ArrayList<Map>();
		Map<String, String> resultMap = new HashMap<String, String>();
		StringBuilder sb;
		try {
			InputStream is = new URL(URL).openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			sb = new StringBuilder();
			String str;
			while ((str = br.readLine()) != null)
				sb.append(str);
			br.close();
			if (sb.length() > 0) {
				JsonObject jObj = gson.fromJson(sb.toString(), JsonObject.class);
				JsonArray jArray = jObj.get("results").getAsJsonArray();
				for (int i = 0; i < jArray.size(); i++) {
					JsonObject obj = jArray.get(i).getAsJsonObject();
					String name="", place_id="", rating="", latitude="", longitude="";
					if (obj.has("name"))
						name = obj.get("name").getAsString();
					if (obj.has("place_id"))
						place_id = obj.get("place_id").getAsString();
					if (obj.has("rating"))
						rating = String.valueOf(obj.get("rating").getAsFloat());
					if (obj.has("geometry")) {
						longitude = String.valueOf(obj.get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lng").getAsDouble());
						latitude = String.valueOf(obj.get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lat").getAsDouble());
					}
					resultMap.put("name", name);
					resultMap.put("place_id", place_id);
					resultMap.put("rating", rating);
					resultMap.put("longitude", longitude);
					resultMap.put("latitude", latitude);
					searchList.add(resultMap);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return searchList;
	}
}
