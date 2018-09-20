
package tw.idv.aloha.lineBot.Utli;

import java.util.Map;

public class MessageTemplate {
	public static String textTemplate() {
		String callbackTextTemplate = "";
		return callbackTextTemplate;
	}

	public static String buttonTemplateFromMap(Map<String, Object> locationMap,  String backText) { //single button template

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
								+ "\"text\":\"" + place_URI + "\""
						+ "}]"
					+ "}"
				+ "}]";
		
		return callbackButtonTemplate;
	}
	
	
	public static String buttonTemplateFromMapVerEg(Map<String, Object> locationMap) { //single button template English version

		String callbackButtonTemplate = "";
		double rating = Double.parseDouble(locationMap.get("rating").toString());
		String name = (String) locationMap.get("name");
		String place_id = (String) locationMap.get("place_id");
		String place_latitude = (String) locationMap.get("latitude");
		String place_longitude = (String) locationMap.get("longitude");
		String open_now = (String) locationMap.get("open_now");
		String open_now_Taiwan = "";
		open_now_Taiwan = (open_now.equals("true")) ? "opening" : "break";
		String thumbnailImageUrl = (String) locationMap.get("photo_reference");
		String text = "";
		text = "Name：" + name +"\\nPrice：" + rating + "" + "\\nOpining：" + open_now_Taiwan;

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
								+ "\"label\":\"驚喜(需在台灣)\","
								+ "\"text\":\"" + place_URI + "\""
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

	public static String xxx() {  //backup
		String callbackText = "";
		return callbackText;
	}
}
