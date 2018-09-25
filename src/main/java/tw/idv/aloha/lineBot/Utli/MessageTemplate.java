
package tw.idv.aloha.lineBot.Utli;

import java.util.Map;

import tw.idv.aloha.lineBot.controller.GMapSearch;

public class MessageTemplate {
	public static String textTemplate() {
		String callbackTextTemplate = "";
		return callbackTextTemplate;
	}

	public static String buttonTemplateFromMap(Map<String, Object> locationMap,  String backText) { //single button template
		GMapSearch gMap = new GMapSearch();
		String callbackButtonTemplate = "";
		double rating = gMap.jsonToDouble(locationMap, "rating");		
		String name = gMap.jsonToString(locationMap, "name");
		name = name.replaceAll(" ", "");
		String place_id = gMap.jsonToString(locationMap, "place_id");		
		String place_latitude = gMap.jsonToString(locationMap, "latitude");		
		String place_longitude = gMap.jsonToString(locationMap, "longitude");	
		String open_now = gMap.jsonToString(locationMap, "open_now");
		String open_now_Taiwan = "";
		open_now_Taiwan = (open_now.equals("true")) ? "有開" : "沒開";
		String thumbnailImageUrl = (String) locationMap.get("photo_reference");
		String text = "";
		text = "店名：" + name +"\\n分數：" + rating + "分" + "\\n是否營業：" + open_now_Taiwan;

		String place_URI = "https://www.google.com/maps/search/?api=1&query=" + place_latitude + "," + place_longitude
				+ "&query_place_id=" + place_id;
		thumbnailImageUrl = "https://maps.googleapis.com/maps/api/place/photo?"
				+ "key=AIzaSyB2ZeC9Pb8EW7rWgimJBczrWozGhCLz-u4&" + "photoreference=" + thumbnailImageUrl
				+ "&maxwidth=1024";

		callbackButtonTemplate = "[{\"type\":\"template\",\"altText\":\"" + name + "\","
				+ "\"template\":{"
					+ "\"type\":\"buttons\","
					+ "\"thumbnailImageUrl\":\"" + thumbnailImageUrl + "\","
					+ "\"text\":\"" + text
					+ "\",\"actions\":"
						+ "[{" + "\"type\":\"uri\","
								+ "\"label\":\"查看地圖\","
								+ "\"uri\":\"" + place_URI + "\""
						+ "},"
						+ "{" + "\"type\":\"message\","
								+ "\"label\":\"驚喜(需在台灣)\","
								+ "\"text\":\"" + "=" + backText + "\""
						+ "}]"
					+ "}"
				+ "}]";
		
		return callbackButtonTemplate;
	}
	
	public static String buttonTemplateFromMapSurprise(Map<String, Object> locationMap,  String backText) { //single button template
		GMapSearch gMap = new GMapSearch();
		String callbackButtonTemplate = "";
		double rating = gMap.jsonToDouble(locationMap, "rating");		
		double avg_price = gMap.jsonToDouble(locationMap, "avg_price");
		String name = gMap.jsonToString(locationMap, "name");
		name = name.replaceAll(" ", "");
		if(name.length() >= 7){
			name = name.substring(0, 7);
		}
		String lat = gMap.jsonToString(locationMap, "lat");		
		String lng = gMap.jsonToString(locationMap, "lng");		
		String cover_url = gMap.jsonToString(locationMap, "cover_url");	
		String opening_hours = gMap.jsonToString(locationMap, "opening_hours");		
		String phone = gMap.jsonToString(locationMap, "phone");	
		String message = gMap.jsonToString(locationMap, "message");
		backText = removeChar(backText);
		if((opening_hours.substring(0,4).equals("現正營業"))){
			opening_hours = opening_hours.replace("現正營業", "營業中");
		}
		if((opening_hours.substring(0,4).equals("今日營業"))){
			opening_hours = opening_hours.replace("今日營業", "未營業");
		}
		System.out.println(avg_price);
		String thumbnailImageUrl = "";
		String text = "";
		text = "店名：" + name + "\\n"
			 + "分數：" + rating + "分" + "  平均：" + avg_price + "元\\n"
			 + opening_hours ;
			 
//			 + "平均價格：" + avg_price + "元\\n" 
//			 + "評論：" + message;

		String place_URI = "https://www.google.com/maps/search/?api=1&query=" + lat + "," + lng + "," + name;
		thumbnailImageUrl = cover_url;
		callbackButtonTemplate = "[{\"type\":\"template\",\"altText\":\"" + name + "\","
				+ "\"template\":{"
					+ "\"type\":\"buttons\","
					+ "\"thumbnailImageUrl\":\"" + thumbnailImageUrl + "\","
					+ "\"text\":\"" + text
					+ "\",\"actions\":"
						+ "["
							+ "{" + "\"type\":\"uri\","
									+ "\"label\":\"查看地圖\","
									+ "\"uri\":\"" + place_URI + "\""
							+ "},"
							+ "{" + "\"type\":\"message\","
									+ "\"label\":\"1-200元/人\","
									+ "\"text\":\"" + "l=" + backText + "\""
							+ "},"
							+ "{" + "\"type\":\"message\","
									+ "\"label\":\"201-400元/人\","
									+ "\"text\":\"" + "m=" + backText + "\""
							+ "},"
							+ "{" + "\"type\":\"message\","
									+ "\"label\":\"401-元/人\","
									+ "\"text\":\"" + "h=" + backText + "\""
							+ "}"
						+ "]"
					+ "}"
				+ "}]";
		
		return callbackButtonTemplate;
	}
	
	
	public static String buttonTemplateFromMapVerEg(Map<String, Object> locationMap, String backText) { //single button template English version
		GMapSearch gMap = new GMapSearch();
		String callbackButtonTemplate = "";
		double rating = gMap.jsonToDouble(locationMap, "rating");		
		String name = gMap.jsonToString(locationMap, "name");
		name = name.replaceAll(" ", "");
		String place_id = gMap.jsonToString(locationMap, "place_id");		
		String place_latitude = gMap.jsonToString(locationMap, "latitude");		
		String place_longitude = gMap.jsonToString(locationMap, "longitude");	
		String open_now = gMap.jsonToString(locationMap, "open_now");
		String open_now_Taiwan = "";
		open_now_Taiwan = (open_now.equals("true")) ? "opening" : "break";
		String thumbnailImageUrl = (String) locationMap.get("photo_reference");
		String text = "";
		text = "Name：" + name +"\\n"
			 + "Rating：" + rating + "" + "\\n"
			 + "Opining：" + open_now_Taiwan;

		String place_URI = "https://www.google.com/maps/search/?api=1&query=" + place_latitude + "," + place_longitude
				+ "&query_place_id=" + place_id;
		thumbnailImageUrl = "https://maps.googleapis.com/maps/api/place/photo?"
				+ "key=AIzaSyB2ZeC9Pb8EW7rWgimJBczrWozGhCLz-u4&" + "photoreference=" + thumbnailImageUrl
				+ "&maxwidth=1024";

		callbackButtonTemplate = "[{\"type\":\"template\",\"altText\":\"" + name + "\","
				+ "\"template\":{"
					+ "\"type\":\"buttons\","
					+ "\"thumbnailImageUrl\":\"" + thumbnailImageUrl + "\","
					+ "\"text\":\"" + text
					+ "\",\"actions\":"
						+ "[{" + "\"type\":\"uri\","
								+ "\"label\":\"Location\","
								+ "\"uri\":\"" + place_URI + "\""
						+ "},"
						+ "{" + "\"type\":\"message\","
								+ "\"label\":\"Surprise(Location in Taiwan)\","
								+ "\"text\":\"" + backText + "\""
						+ "}]"
					+ "}"
				+ "}]";
		return callbackButtonTemplate;
	}
	
	public static String buttonTemplateFromMap1(Map<String, Object> locationMap) { //multiple button template English version
		String callbackButtonTemplate = "";
		double rating = Double.parseDouble(locationMap.get("rating").toString());
		String name = (String) locationMap.get("name");
		String place_id = (String) locationMap.get("place_id");
		String place_latitude = (String) locationMap.get("latitude");
		String place_longitude = (String) locationMap.get("longitude");
		String open_now = (String) locationMap.get("open_now");
		String open_now_Taiwan = "";
		open_now_Taiwan = (open_now.equals("true")) ? "有開" : "沒開";
		String thumbnailImageUrl = (String) locationMap.get("photo_reference");
		String text = "";
		text = "店名：" + name +"\\n分數：" + rating + "分" + "\\n是否營業：" + open_now_Taiwan;

		String place_URI = "https://www.google.com/maps/search/?api=1&query=" + place_latitude + "," + place_longitude
				+ "&query_place_id=" + place_id;
		thumbnailImageUrl = "https://maps.googleapis.com/maps/api/place/photo?"
				+ "key=AIzaSyB2ZeC9Pb8EW7rWgimJBczrWozGhCLz-u4&" + "photoreference=" + thumbnailImageUrl
				+ "&maxwidth=1024";

		callbackButtonTemplate ="[{\"type\":\"template\","
			+"\"altText\":\"" + name + "\","
			+"\"template\":{"
				+"\"type\":\"carousel\","
				+"\"columns\": ["
					+"{"
						+"\"thumbnailImageUrl\": \"" + thumbnailImageUrl + "\","
						+"\"title\": \"" + name + "\","
						+"\"text\": \"" + name + "\","
					    +"\"actions\": [{"
						    +"\"type\":\"uri\","
						    +"\"label\":\"查看地圖\","
						    +"\"uri\":\"" + place_URI + "\""
					    +"}]"
					+"},"
					+"{"
					+"\"thumbnailImageUrl\": \"" + thumbnailImageUrl + "\","
						+"\"title\": \"" + name + "\","
						+"\"text\": \"" + name + "\","
					    +"\"actions\": [{"
						    +"\"type\":\"uri\","
						    +"\"label\":\"查看地圖\","
						    +"\"uri\":\"" + place_URI + "\""
					    +"}]"
					+"},"
					+"{"
						+"\"thumbnailImageUrl\": \"" + thumbnailImageUrl + "\","
						+"\"title\": \"" + name + "\","
						+"\"text\": \"" + name + "\","
					    +"\"actions\": [{"
						    +"\"type\":\"uri\","
						    +"\"label\":\"查看地圖\","
						    +"\"uri\":\"" + place_URI + "\""
					    +"}]"
					+"},"
					+"{"
					+"\"thumbnailImageUrl\": \"" + thumbnailImageUrl + "\","
						+"\"title\": \"" + name + "\","
						+"\"text\": \"" + name + "\","
					    +"\"actions\": [{"
						    +"\"type\":\"uri\","
						    +"\"label\":\"查看地圖\","
						    +"\"uri\":\"" + place_URI + "\""
					    +"}]"
					+"},"
					+"{"
					+"\"thumbnailImageUrl\": \"" + thumbnailImageUrl + "\","
						+"\"title\": \"" + name + "\","
						+"\"text\": \"" + name + "\","
					    +"\"actions\": [{"
						    +"\"type\":\"uri\","
						    +"\"label\":\"查看地圖\","
						    +"\"uri\":\"" + place_URI + "\""
					    +"}]"
					+"},"
					+"{"
					+"\"thumbnailImageUrl\": \"" + thumbnailImageUrl + "\","
						+"\"title\": \"" + name + "\","
						+"\"text\": \"" + name + "\","
					    +"\"actions\": [{"
						    +"\"type\":\"uri\","
						    +"\"label\":\"查看地圖\","
						    +"\"uri\":\"" + place_URI + "\""
					    +"}]"
					+"},"
					+"{"
						+"\"thumbnailImageUrl\": \"" + thumbnailImageUrl + "\","
						+"\"title\": \"" + name + "\","
						+"\"text\": \"" + name + "\","
					    +"\"actions\": [{"
						    +"\"type\":\"uri\","
						    +"\"label\":\"查看地圖\","
						    +"\"uri\":\"" + place_URI + "\""
					    +"}]"
					+"},"
					+"{"
						+"\"thumbnailImageUrl\": \"" + thumbnailImageUrl + "\","
						+"\"title\": \"" + name + "\","
						+"\"text\": \"" + name + "\","
					    +"\"actions\": [{"
						    +"\"type\":\"uri\","
						    +"\"label\":\"查看地圖\","
						    +"\"uri\":\"" + place_URI + "\""
					    +"}]"
					+"}"
				+"]"
			 +"}"
		 +"}]";
		return callbackButtonTemplate;
	}

	public static String buttonTemplateSingle(String altText, String text, 
			 								  String type, String label, String typeTitle, String content) {
		String callbackButtonTemplate = "";
		callbackButtonTemplate = "[{"
				+ "\"type\":\"template\","
				+ "\"altText\":\"+" + altText + "+\","
				+ "\"template\":{"
					+ "\"type\":\"buttons\","
					+ "\"text\":\"" + text + "\","
					+ "\"actions\":["
						+ "{" + 
							"\"type\":\""+ type +"\","
							+ "\"label\":\""+ label + "\","
							+ "\""+ typeTitle +"\":\"" + content + "\"}"
						+ "]"
					+ "}"
				+ "}]";
		return callbackButtonTemplate;
	}
	
	public static String comfirmTemplate(String altText, String text, 
										 String yesType, String yesLabel, String yesTypeTitle, String yesContent,
									     String noType, String noLabel, String noTypeTitle, String noContent) {  //confirm(double) template
		String callbackButtonTemplate = "";
		callbackButtonTemplate = "[{"
				+ "\"type\":\"template\","
				+ "\"altText\":\"+" + altText + "+\","
				+ "\"template\":{"
					+ "\"type\":\"confirm\","
					+ "\"text\":\"" + text + "\","
					+ "\"actions\":["
						+ "{" + 
							"\"type\":\""+ yesType +"\","
							+ "\"label\":\""+ yesLabel + "\","
							+ "\""+ yesTypeTitle +"\":\"" + yesContent + 
						"\"},"
						+ "{" + 
							"\"type\":\""+ noType +"\","
							+ "\"label\":\""+ noLabel + "\","
							+ "\""+ noTypeTitle +"\":\"" + noContent + 
						"\"}"
					+ "]"
				+ "}"
			+ "}]";
		return callbackButtonTemplate;
	}	

	public static String textMessage(String text) {  //text message
		String callbackText = "";
		callbackText = "[{\"type\": \"text\", \"text\": \"" + text + "\"}]";
		return callbackText;
	}
	
	public static String removeChar(String backText){
		if(backText.charAt(0) == '='){
			backText = backText.replace("=", "");
		} else if(backText.substring(0, 2).equals("l=")) {
			backText = backText.replace("l=", "");
		} else if(backText.substring(0, 2).equals("m=")) {
			backText = backText.replace("m=", "");
		} else if(backText.substring(0, 2).equals("h=")) {
			backText = backText.replace("h=", "");
		}
		return backText;
	}

	public static String xxx() {  //backup
		String callbackText = "";
		return callbackText;
	}
}
