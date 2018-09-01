package tw.idv.aloha.lineBot.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GMapExcemple {
	// 關鍵字搜尋
	private static final String TEXT_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?"
			+ "query=中壢+鐵板燒" 
			+ "&language=zh-TW" 
			+ "&key=AIzaSyAYmC8oUYc9DGAZn8hqZKakFeclhAbTRSI";

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
			JsonArray jArray = jObj.get("results").getAsJsonArray();
			for (int i = 0; i < jArray.size(); i++) {
				JsonObject obj = jArray.get(i).getAsJsonObject();
				System.out.println("店名： " + obj.get("name").getAsString());
				System.out.println("ID： " + obj.get("place_id").getAsString());
				if (obj.has("rating"))
					System.out.println("評分： " + obj.get("rating").getAsFloat());
				
				System.out.println("經度： " + obj.get("geometry").getAsJsonObject().get("location").getAsJsonObject()
						.get("lng").getAsDouble());
				System.out.println("緯度： " + obj.get("geometry").getAsJsonObject().get("location").getAsJsonObject()
						.get("lat").getAsDouble());
				
				System.out.println();
			}
		}
	}

}

