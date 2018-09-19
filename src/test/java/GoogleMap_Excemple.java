
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GoogleMap_Excemple {
	
	// 關鍵字搜尋
//	private static final String TEXT_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?"
//			+ "query=台北+飯" 
//			+ "&language=zh-TW" 
//			+ "&key=AIzaSyAYmC8oUYc9DGAZn8hqZKakFeclhAbTRSI";
	private static final String TEXT_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/details/json?placeid=ChIJ-6uctZ6pQjQRbOssmdGcz9k&key=AIzaSyAYmC8oUYc9DGAZn8hqZKakFeclhAbTRSI&rankby=distance&type=restaurant&language=zh-TW";
//	https://maps.googleapis.com/maps/api/place/textsearch/json?
//		key=AIzaSyB2ZeC9Pb8EW7rWgimJBczrWozGhCLz-u4
//		&query=咖哩飯
//		&rankby=distance&type=restaurant&language=zh-TW
	

	public static void main(String[] args) throws Exception {
		Gson gson = new Gson();
		// 讀回json字串
		InputStream is = new URL(TEXT_SEARCH_URL).openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String str;
		while ((str = br.readLine()) != null)
			sb.append(str);
		br.close();

		// 解析json內容
		if (sb.length() > 0) {
			JsonObject jObj = gson.fromJson(sb.toString(), JsonObject.class);
//			System.out.println(jObj);
			JsonObject obj = jObj.get("result").getAsJsonObject();
			System.out.println("店名： " + obj.getAsJsonObject("opening_hours").getAsJsonArray("weekday_text"));
//			JsonArray jArray = jObj.get("results").getAsJsonArray();
			
//			for (int i = 0; i < jArray.size(); i++) {
//				JsonObject obj = jArray.get(i).getAsJsonObject();
				System.out.println("店名： " + obj.getAsJsonObject("opening_hours").getAsJsonArray("weekday_text"));
				if (obj.has("reviews"))	
//				String reviewText = String.valueOf(obj.getAsJsonArray("reviews").get(0).getAsJsonObject().get("text"));
//				System.out.println(reviewText);
//				System.out.println("ID： " + obj.get("place_id").getAsString());
//				if (obj.has("rating"))
//					System.out.println("評分： " + obj.get("rating").getAsFloat());
//				
//				System.out.println("經度： " + obj.get("geometry").getAsJsonObject().get("location").getAsJsonObject()
//						.get("lng").getAsDouble());
//				System.out.println("緯度： " + obj.get("geometry").getAsJsonObject().get("location").getAsJsonObject()
//						.get("lat").getAsDouble());
//				
				System.out.println();
//			}
		}
	}

}
