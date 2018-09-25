package tw.idv.aloha.lineBot.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import tw.idv.aloha.lineBot.Utli.DateTimeUtil;

public class GMapSearch {

	// 經緯度＋關鍵字搜尋
	public static String getURLByText(String latitude, String longitude, String content) {
		String URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?"
				+ "key=AIzaSyB2ZeC9Pb8EW7rWgimJBczrWozGhCLz-u4" + "&location=" + latitude + "," + longitude + ","
				+ content + "&rankby=distance&type=restaurant&language=zh-TW";
		return URL;
		//
	}

	// 經緯度搜尋
	public String getURLByLocation(String latitude, String longitude) {
		String URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?"
				+ "key=AIzaSyB2ZeC9Pb8EW7rWgimJBczrWozGhCLz-u4" + "&location=" + latitude + "," + longitude
				+ "&rankby=distance&type=restaurant&language=zh-TW";
		return URL;
		// https://maps.googleapis.com/maps/api/place/textsearch/json?
		// key=AIzaSyB2ZeC9Pb8EW7rWgimJBczrWozGhCLz-u4&location=0,0&rankby=distance&type=restaurant&language=zh-TW
	}
	
	//完整資訊GMap
	public String getGMapDetailURL(String placeId) {
		String URL = "https://maps.googleapis.com/maps/api/place/details/json?"
				+ "placeid=" + placeId
				+ "&key=AIzaSyAYmC8oUYc9DGAZn8hqZKakFeclhAbTRSI"
				+ "&rankby=distance&type=restaurant&language=zh-TW";
		return URL;
		//https://maps.googleapis.com/maps/api/place/details/json?
		//placeid=ChIJ-6uctZ6pQjQRbOssmdGcz9k&key=AIzaSyAYmC8oUYc9DGAZn8hqZKakFeclhAbTRSI
		//&rankby=distance&type=restaurant&language=zh-TW";
	}
	
	//取得東北西南座標
	public String getGmapCoordinateURL(String content, int placeLength){
		String placeName = "";
		placeName = content.substring(0, placeLength);// 西門

		String URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?"
				+ "query=台灣" + placeName
				+ "&key=AIzaSyAYmC8oUYc9DGAZn8hqZKakFeclhAbTRSI";
		return URL;

		//https://maps.googleapis.com/maps/api/place/textsearch/json?
		//query=%E8%A5%BF%E9%96%80&
		//key=AIzaSyAYmC8oUYc9DGAZn8hqZKakFeclhAbTRSI
	}
	
	//取得foodName
	public String getfoodName(String content, int placeLength){
		String foodName = "";
		foodName = content.substring(placeLength+1, content.length());// 鐵板燒	

		return foodName;
	}
	
	//取得coordinate map
	
	//取得IfoodieURL
	public String getIfoodieURL(Map<String, Object> coordinateMap, String foodName){
		String neLat = "",neLng = "",swLat = "",swLng = "";
		neLat = (String) coordinateMap.get("neLat");
		neLng = (String) coordinateMap.get("neLng");
		swLat = (String) coordinateMap.get("swLat");
		swLng = (String) coordinateMap.get("swLng");
		String URL = "https://ifoodie.tw/api/restaurant/explore/?"
				+ "q=" + foodName
				+ "&sw=" + swLat + "," + swLng
				+ "&ne=" + neLat + "," + neLng
				+ "&offset=0&limit=100";
		return URL;
		//https://ifoodie.tw/api/restaurant/explore/?
		//q=%E7%89%9B%E6%8E%92
		//&sw=25.0465547,121.5091539
		//&ne=25.0416013,121.502824
		//&offset=0&limit=20
	}

	// 關鍵字搜尋
	public String getURLByText(String content, int placeLength) { // 西門，鐵板燒
		String placeName = "";
		String foodName = "";
		placeName = content.substring(0, placeLength);// 西門,北屯區，香格里拉
		foodName = content.substring(placeLength+1, content.length());// 鐵板燒	
		String URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?"
				+ "key=AIzaSyB2ZeC9Pb8EW7rWgimJBczrWozGhCLz-u4" + "&query=" + placeName + "+" + foodName;
		return URL;
		
//		if(placeLength == 2){
//			placeName = content.substring(0, 2);// 西門
//			foodName = content.substring(3, content.length());// 鐵板燒
//		}
		// https://maps.googleapis.com/maps/api/place/textsearch/json?
		// query=%E8%A5%BF%E9%96%80+%E9%90%B5%E6%9D%BF%E7%87%92&language=zh-TW&key=AIzaSyAYmC8oUYc9DGAZn8hqZKakFeclhAbTRSI
	}
	
	public Map<String, Object> gMapOneDetailSearch(String URL){
		Gson gson = new Gson();
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

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
				String opening_hours = "", reviewText = "";
				JsonObject obj = jObj.get("result").getAsJsonObject();
				if (obj.has("opening_hours"))
					opening_hours = obj.getAsJsonObject("opening_hours").getAsJsonArray("weekday_text").toString();
				if (obj.has("reviews"))	
					reviewText = String.valueOf(obj.getAsJsonArray("reviews").get(0).getAsJsonObject().get("text"));
					
					
//					if (obj.has("place_id"))
//						place_id = obj.get("place_id").getAsString();
//					if (obj.has("rating"))
//						rating = String.valueOf(obj.get("rating").getAsFloat());
//					if (obj.has("geometry")) {
//						longitude = String.valueOf(obj.get("geometry").getAsJsonObject().get("location")
//								.getAsJsonObject().get("lng").getAsDouble());
//						latitude = String.valueOf(obj.get("geometry").getAsJsonObject().get("location")
//								.getAsJsonObject().get("lat").getAsDouble());
//					}
//					if (obj.has("opening_hours"))
//						open_now = String.valueOf(obj.get("opening_hours").getAsJsonObject().get("open_now"));
//
//					if (obj.has("photos")) {
//						JsonArray jArrayphotos = obj.get("photos").getAsJsonArray();
//						JsonObject objPhotos = jArrayphotos.get(0).getAsJsonObject();
//						photo_reference = String.valueOf(objPhotos.get("photo_reference"));
//						photo_reference = photo_reference.replaceAll("\"", "");
//					}
//					resultMap.put("name", name);
//					resultMap.put("place_id", place_id);
//					resultMap.put("rating", rating);
//					resultMap.put("longitude", longitude);
//					resultMap.put("latitude", latitude);
//					resultMap.put("open_now", open_now);
//					resultMap.put("photo_reference", photo_reference);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultMap;
	}
	
	public List<Map<String, Object>> gMapSearch(String URL) {
		Gson gson = new Gson();
		List<Map<String, Object>> searchList = new ArrayList<Map<String, Object>>();

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
					Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
					JsonObject obj = jArray.get(i).getAsJsonObject();
					String name = "", place_id = "", rating = "", latitude = "", longitude = "", open_now = "",
							photo_reference = "";
					if (obj.has("name"))
						name = obj.get("name").getAsString();
					if (obj.has("place_id"))
						place_id = obj.get("place_id").getAsString();
					if (obj.has("rating"))
						rating = String.valueOf(obj.get("rating").getAsFloat());
					if (obj.has("geometry")) {
						longitude = String.valueOf(obj.get("geometry").getAsJsonObject().get("location")
								.getAsJsonObject().get("lng").getAsDouble());
						latitude = String.valueOf(obj.get("geometry").getAsJsonObject().get("location")
								.getAsJsonObject().get("lat").getAsDouble());
					}
					if (obj.has("opening_hours"))
						open_now = String.valueOf(obj.get("opening_hours").getAsJsonObject().get("open_now"));

					if (obj.has("photos")) {
						JsonArray jArrayphotos = obj.get("photos").getAsJsonArray();
						JsonObject objPhotos = jArrayphotos.get(0).getAsJsonObject();
						photo_reference = String.valueOf(objPhotos.get("photo_reference"));
						photo_reference = photo_reference.replaceAll("\"", "");
					}
					resultMap.put("name", name);
					resultMap.put("place_id", place_id);
					resultMap.put("rating", rating);
					resultMap.put("longitude", longitude);
					resultMap.put("latitude", latitude);
					resultMap.put("open_now", open_now);
					resultMap.put("photo_reference", photo_reference);
					searchList.add(resultMap);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return searchList;
	}
	
	public List<Map<String, Object>> gMapCoordinate(String URL) {
		Gson gson = new Gson();
		List<Map<String, Object>> searchList = new ArrayList<Map<String, Object>>();

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
					Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
					JsonObject obj = jArray.get(i).getAsJsonObject();
					String northeastLat = "", northeastLng = "", southwestLat = "", southwestLng = "";
					if (obj.has("geometry")) {
						northeastLat = String.valueOf(obj.get("geometry").getAsJsonObject().get("viewport")
								.getAsJsonObject().getAsJsonObject().get("northeast")
								.getAsJsonObject().get("lat").getAsDouble());
						northeastLng = String.valueOf(obj.get("geometry").getAsJsonObject().get("viewport")
								.getAsJsonObject().getAsJsonObject().get("northeast")
								.getAsJsonObject().get("lng").getAsDouble());
						southwestLat = String.valueOf(obj.get("geometry").getAsJsonObject().get("viewport")
								.getAsJsonObject().getAsJsonObject().get("southwest")
								.getAsJsonObject().get("lat").getAsDouble());
						southwestLng = String.valueOf(obj.get("geometry").getAsJsonObject().get("viewport")
								.getAsJsonObject().getAsJsonObject().get("southwest")
								.getAsJsonObject().get("lng").getAsDouble());
					}
					resultMap.put("neLat", northeastLat);
					resultMap.put("neLng", northeastLng);
					resultMap.put("swLat", southwestLat);
					resultMap.put("swLng", southwestLng);
					searchList.add(resultMap);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return searchList;
	}
	
	public List<Map<String, Object>> ifoodieSearch(String URL) {
		Gson gson = new Gson();
		List<Map<String, Object>> searchList = new ArrayList<Map<String, Object>>();

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
				JsonArray jArray = jObj.get("response").getAsJsonArray();
				for (int i = 0; i < jArray.size(); i++) {
					Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
					JsonObject obj = jArray.get(i).getAsJsonObject();
					String name = "", rating = "", opening_hours = "", open_now="", avg_price = "",
							   lat = "", lng = "", phone = "", cover_url="", message="";
					if (obj.has("name"))
						name = getJsonValue(obj, "name");
					if (obj.has("rating"))
						rating = getJsonValue(obj, "rating");
					if (obj.has("opening_hours"))
						opening_hours = getJsonValue(obj, "opening_hours");
					if (obj.has("open_now"))
						open_now = getJsonValue(obj, "open_now");
					if (obj.has("avg_price"))
						avg_price = getJsonValue(obj, "avg_price");
					if (obj.has("lat"))
						lat = getJsonValue(obj, "lat");
					if (obj.has("lng"))
						lng = getJsonValue(obj, "lng");
					if (obj.has("phone"))
						phone = getJsonValue(obj, "phone");
					if (obj.has("cover_url"))
						cover_url = getJsonValue(obj, "cover_url");
					if (obj.has("primary_checkin"))
						message = "";
//						message = String.valueOf(obj.get("primary_checkin").getAsJsonObject().get("message").getAsString());
					resultMap.put("name", name);
					resultMap.put("rating", rating);
					resultMap.put("opening_hours", opening_hours);
					resultMap.put("open_now", open_now);
					resultMap.put("avg_price", avg_price);
					resultMap.put("lat", lat);
					resultMap.put("lng", lng);
					resultMap.put("phone", phone);
					resultMap.put("cover_url", cover_url);
					resultMap.put("message", message);
					searchList.add(resultMap);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return searchList;
	}

	// Get BestOne Map Research //最好的一家
	public static Map<String, Object> getGMapBest(List<Map<String, Object>> searchList) {
		double rating = 0.0;
		double ratingBest = 0.0;
		for (Map<String, Object> callbackMap : searchList) {
			rating = Double.parseDouble(callbackMap.get("rating").toString());
			if (rating > ratingBest) {
				ratingBest = rating;
				return callbackMap;
			}
		}
		return null;
	}

	// Get RandomOne Map Research //隨機的一家
	public Map<String, Object> getGMapRandom(List<Map<String, Object>> searchList) {
		Random random = new Random();
		DateTimeUtil dateTimeUtil = new DateTimeUtil();
		List<Map<String, Object>> randomList = new ArrayList<Map<String, Object>>();
		Boolean isBewteen = dateTimeUtil.isBetweenTime("20", "11");
		double ratingBetter = 3.5;
		int length = 0;
		int randomIndex = 0;
		Map<String, Object> randomMap = new HashMap<String, Object>();
		for (Map<String, Object> callbackMap : searchList) {
			Double rating = Double.parseDouble(callbackMap.get("rating").toString());
			if (rating > ratingBetter) { // 如果在評分大於3.5
				if (isBewteen) {
					if(callbackMap.get("open_now").equals("true")) { // 如果在11~20 顯示開的
						randomList.add(callbackMap);
						continue;
					}
				} else { // 如果不在11~20 顯示開與不開的
					randomList.add(callbackMap);
					continue;
				}
			} else {		
			}
		}
		if(randomList.size() == 0){
			for (Map<String, Object> callbackMap : searchList) {
				Double rating = Double.parseDouble(callbackMap.get("rating").toString());
				if (rating > ratingBetter) { // 如果在評分大於3.5
						randomList.add(callbackMap);
						continue;
				} else {		
				}
			}
			
		}
		length = randomList.size();
		randomIndex = random.nextInt(length);
		randomMap = randomList.get(randomIndex);
		return randomMap;
	}
	
	// Get RandomOne Map Research //隨機的一家 (Ifoodie)
	public Map<String, Object> getIfoodieRandom(List<Map<String, Object>> searchList, String foodName, String text) {
		Random random = new Random();
		DateTimeUtil dateTimeUtil = new DateTimeUtil();
		List<Map<String, Object>> randomList = new ArrayList<Map<String, Object>>();
		Map<String, Object> matchLevelMap = new HashMap<String, Object>();
		int length = 0;
		int randomIndex = 0;
		Map<String, Object> randomMap = new HashMap<String, Object>();
		
		Boolean isBewteen = dateTimeUtil.isBetweenTime("20", "11");
		double ratingBetter = 3.5;
		for (Map<String, Object> callbackMap : searchList) {
			Double rating = jsonToDouble(callbackMap, "rating");
			if (rating > ratingBetter) { // 如果在評分小於3.5 就是蠢
				if (isBewteen) {
					if(callbackMap.get("open_now").equals("true")) { // 如果在11~20 顯示開的
						matchLevelMap = matchLevel(callbackMap, text);
						if (matchLevelMap != null) {
							randomList.add(matchLevelMap);
						}
						continue;
					}
				} else { // 如果不在11~20 顯示開與不開的
					matchLevelMap = matchLevel(callbackMap, text);
					if (matchLevelMap != null) {
						randomList.add(matchLevelMap);
					}
					continue;
				}
			} else {	
//				matchLevelMap = matchLevel(callbackMap, text);
//				if (!matchLevelMap.isEmpty()) {
//					randomList.add(matchLevelMap);
//				}
//				continue;
			}
		}
		if(randomList.size() != 0){
			length = randomList.size();
			randomIndex = random.nextInt(length);
			randomMap = randomList.get(randomIndex);
		}
		return randomMap;
	}
	
	private Map<String, Object> matchLevel (Map<String, Object> getMap, String text){
		Map<String, Object> matchLevelMap = new HashMap<String, Object>();
		double avg_price = jsonToDouble(getMap, "avg_price");
		Character firstChar = text.charAt(0);
		if((avg_price > 1 && avg_price < 200) && firstChar == 'l'){
			matchLevelMap = getMap;
			return matchLevelMap;
		} else if ((avg_price > 200 && avg_price < 400) && firstChar == 'm') {
			matchLevelMap = getMap;
			return matchLevelMap;
		} else if ((avg_price > 400) && firstChar == 'h') {
			matchLevelMap = getMap;
			return matchLevelMap;
		} else if(firstChar == '='){
			matchLevelMap = getMap;
			return matchLevelMap;
		}
		return null;
	}
	
	private String getJsonValue(JsonObject jsonObj, String key){
		String value = "";
		if(!jsonObj.get(key).toString().isEmpty() && !"".equals(jsonObj.get(key).toString()) && !jsonObj.get(key).isJsonNull()){
			value = jsonObj.get(key).getAsString();
		}
		return value;
	}
	
	public Double jsonToDouble(Map<String, Object> callbackMap, String value){
		Double callbackValue = 0.0;
		if(!"".equals(callbackMap.get(value)) && callbackMap.containsKey(value)){
			callbackValue = Double.parseDouble(callbackMap.get(value).toString());
		}
		return callbackValue;
	}
	
	public String jsonToString(Map<String, Object> callbackMap, String value){
		String callbackValue = "";
		if(!"".equals(callbackMap.get(value)) && callbackMap.containsKey(value)){
			callbackValue = callbackMap.get(value).toString();
		}
		return callbackValue;
	}
}
