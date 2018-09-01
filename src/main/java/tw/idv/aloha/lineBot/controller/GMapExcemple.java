package tw.idv.aloha.lineBot.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		
		Boolean isGreater = false;
		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm") ;
		dateFormat.format(date);
		System.out.println(dateFormat.format(date));

		try {
			if(dateFormat.parse(dateFormat.format(date)).after(dateFormat.parse("21:00")))
			{
			    System.out.println("Current time is greater than 12.07");
			}else{
			    System.out.println("Current time is less than 12.07");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

