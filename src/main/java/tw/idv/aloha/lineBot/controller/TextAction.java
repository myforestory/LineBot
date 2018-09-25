package tw.idv.aloha.lineBot.controller;

import tw.idv.aloha.lineBot.Utli.MessageTemplate;
import tw.idv.aloha.lineBot.controller.GMapSearch;

public class TextAction extends LineBotRSController {
	static String[] recommendTypeArray = new String[] { "Aloha推薦飯", "Aloha推薦麵", "Aloha推薦咖哩飯", "Aloha推薦義大利麵",
			"Aloha推薦牛肉麵", "Aloha推薦麵線", "Aloha推薦美食", "Aloha推薦麵飲料", "Aloha推薦牛排", "Aloha推薦火鍋" };

	public static String callbackMessageText(String text) {
		String callbackMessage = "";
		try{
			if (text.toLowerCase().equals("aloha推薦") || text.toLowerCase().equals("好餓")) {
				callbackMessage = isRecommend(text);
			// } else if (text.substring(0 ,7).equals("我想吃最好吃的")){
			// callbackMessage = recommendBestOne(text);
			} else if (text.toLowerCase().equals("english") || text.trim().toLowerCase().equals("recommendation")) {
				callbackMessage = englishVersion(text);
			} else if (text.length() >= 5 && text.substring(0, 5).equals("我想吃很多")) {
				callbackMessage = recommendMutli(text);
			} else if (text.substring(0, 3).equals("我想吃")) {
				callbackMessage = recommend(text);
			} else if (text.length() >= 5 && text.toLowerCase().substring(0, 5).equals("wanna")) {
				callbackMessage = recommendVerEg(text);
			} else if (text.substring(0, 4).equals("=我想吃")) {
				callbackMessage = recommendSurprise(text);
			} else if (text.substring(0, 5).equals("l=我想吃") || text.substring(0, 5).equals("m=我想吃") || text.substring(0, 5).equals("h=我想吃")) {
				callbackMessage = recommendSurpriseByPrice(text);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return callbackMessage;
	}

	// aloha推薦
	private static String isRecommend(String text) {
		String textTemplate = "";
		//buttonTemplateFromString(String altText, String text, String label, String URI)
		textTemplate = MessageTemplate.comfirmTemplate(
			"你在哪ㄋ", //altText
			"你在哪呢？\\n也可輸入：\\n我想吃＋地名＋想吃什麼\\n例：我想吃西門，鐵板燒\\n或者 我想吃很多炒麵", //text
			"uri", //yesType
			"給我位置", //yesLabel
			"uri", //yesTypeTitle
			"line://nv/location", //yesContent
			"message", //noType
			"English", //noLabel
			"text", //noTypeTitle
			"english" //noContent
		);
		return textTemplate;
	}
	
	private static String englishVersion(String text){
		String textTemplate = "";
		//buttonTemplateFromString(String altText, String text, String label, String URI)
		textTemplate = MessageTemplate.buttonTemplateSingle(
//			"你在哪ㄋ", //altText
//			"你在哪呢？\\n也可輸入：\\n我想吃＋地名＋想吃什麼\\n例：我想吃西門，鐵板燒\\n或者 我想吃很多炒麵", //text
//			"uri", //yesType
//			"給我位置", //yesLabel
//			"uri", //yesTypeTitle
//			"line://nv/location", //yesContent
//			"postback", //noType
//			"English", //noLabel
//			"data", //noTypeTitle
//			"english" //noContent
			"Where are you", //altText
			"Give me location\\nOr Type in\\nWanna+placename+food\\nex:Wanna Seattle,Steak", //text
			"uri", //type
			"Location", //label
			"uri", //typeTitle
			"line://nv/location" //content
		);
		return textTemplate;
	}
	
	
	
	private static String recommendVerEg(String text) {// Wanna Seattle,steak.....show one
		String textTemplate = "";
		if(text.length() < 8){
			textTemplate = MessageTemplate.textMessage(
				"I need the sentence like:\\nWanna＋placename＋food\\nex: Wanna Seattle,steak "
			);
		} else {
			int count = -1;
			String callbackURL = ""; //傳回網址
			GMapSearch gMap = new GMapSearch();
			String keyword = text.substring(6, text.length());
			for(int i=5; i<text.length(); i++){
				if(Character.toString(text.charAt(i)).equals(",") || Character.toString(text.charAt(i)).equals("，")){
					callbackURL = gMap.getURLByText(keyword, i-6);
					textTemplate = LocationAction.getGMapSearchVerEg(callbackURL, text);
					count = i;
					break;
				}
			}
			if(count <= 0){
				textTemplate = MessageTemplate.textMessage(
					"Try to change another sentence, like:\\nWanna＋placename＋food\\nex: Wanna Seattle,steak "
				);
			}
		}	
		return textTemplate;
	}
	
	private static String recommend(String text) { // 我想吃.....show one
		String textTemplate = "";
		if(text.length() < 7){
			textTemplate = MessageTemplate.textMessage(
				"請輸入正確的格式喔！\\n我想吃＋地名＋食物\\n例如: 我想吃三重，牛排 "
			);
		} else {
			int count = -1;
			String callbackURL = ""; //傳回網址
			GMapSearch gMap = new GMapSearch();
			String keyword = text.substring(3, text.length());
			for(int i=5; i<text.length(); i++){
				if(Character.toString(text.charAt(i)).equals(",") || Character.toString(text.charAt(i)).equals("，")){
					callbackURL = gMap.getURLByText(keyword, i-3);
					textTemplate = LocationAction.getGMapSearch(callbackURL, text);
					count = i;
					break;
				}
			}
			if(count <= 0){
				textTemplate = MessageTemplate.textMessage(
					"可以換別的關鍵字搜搜看喔！ 例如: 我想吃三重，牛排 "
				);
			}
		}	
		return textTemplate;
	}
	
	private static String recommendSurprise(String text) { // s我想吃.....show one, surprise version
		String textTemplate = "";
		if(text.length() < 8){
			textTemplate = MessageTemplate.textMessage(
				"請輸入正確的格式喔！\\n我想吃＋地名＋食物\\n例如: =我想吃三重，牛排 "
			);
		} else {
			int count = -1;
			String callbackURL = ""; //傳回網址
			String foodName = "";
			GMapSearch gMap = new GMapSearch();
			String keyword = text.substring(4, text.length());
			for(int i=5; i<text.length(); i++){
				if(Character.toString(text.charAt(i)).equals(",") || Character.toString(text.charAt(i)).equals("，")){
					callbackURL = gMap.getGmapCoordinateURL(keyword, i-4);
					foodName = gMap.getfoodName(keyword, i-4);
					textTemplate = LocationAction.getIfoodieSearch(callbackURL, text, foodName);
					count = i;
					break;
				}
			}
			if(count <= 0){
				textTemplate = MessageTemplate.textMessage(
					"可以換別的關鍵字搜搜看喔！ 例如: 我想吃三重，牛排 "
				);
			}
		}	
		return textTemplate;
	}
	
	private static String recommendSurpriseByPrice(String text){
		String textTemplate = "";
		if(text.length() < 9){
			textTemplate = MessageTemplate.textMessage(
				"請輸入正確的格式喔！\\n我想吃＋地名＋食物\\n例如: s我想吃三重，牛排 "
			);
		} else {
			int count = -1;
			String callbackURL = ""; //傳回網址
			String foodName = "";
			GMapSearch gMap = new GMapSearch();
			String keyword = text.substring(5, text.length());
			for(int i=5; i<text.length(); i++){
				if(Character.toString(text.charAt(i)).equals(",") || Character.toString(text.charAt(i)).equals("，")){
					callbackURL = gMap.getGmapCoordinateURL(keyword, i-5);
					foodName = gMap.getfoodName(keyword, i-5);
					textTemplate = LocationAction.getIfoodieSearch(callbackURL, text, foodName);
					count = i;
					break;
				}
			}
			if(count <= 0){
				textTemplate = MessageTemplate.textMessage(
					"可以換別的關鍵字搜搜看喔！ 例如: 我想吃三重，牛排 "
				);
			}
		}	
		return textTemplate;
	}

	// 我想吃很多.....show list
	private static String recommendMutli(String text) {
		String textTemplate = "";
		String keyword = text.substring(5, text.length());
		// 綜合清單
		String place_URI = "https://www.google.com/maps/search/?api=1&query=" + keyword;
		//buttonTemplateFromString(String altText, String text, String label, String URI)
		textTemplate = MessageTemplate.buttonTemplateSingle(
			"不要吃得太撐喔！", //altText
			"祝你吃到撐", //text
			"uri", //type
			"祝你吃到撐", //label
			"uri", //typeTitle
			place_URI //content
		);
		return textTemplate;
	}

	// private static Boolean isRecommendType(String message){
	// Boolean check = false;
	// for(int i = 0; i < recommendTypeArray.length; i++){
	// if(message.equals(recommendTypeArray[i])){
	// check = true;
	// }
	// }
	// return check;
	// }
	//
	// private static String recommendTypeMsg(String text){
	// text = text.substring(6, text.length() - 1);
	// String textTemplate =
	// "[{\"type\":\"template\",\"altText\":\"你在哪ㄋ\",\"template\":{"
	// + "\"type\":\"buttons\",\"text\":\"你在哪ㄋ\",\"actions\":[{"
	// +
	// "\"type\":\"uri\",\"label\":\"給我位置\",\"uri\":\"line://nv/location\"}]}}]";
	// return textTemplate;
	// }

	// //我想吃最好吃的.....show best one
	// private static String recommendBestOne(String text){
	// GMapSearch gMap = new GMapSearch();
	// String keyword = text.substring(7 ,text.length());
	// String place_URI = gMap.getURLByText(keyword);
	// System.out.println(place_URI);
	// String textTemplate = "";
	//
	// // 綜合清單
	//// String place_URI = "https://www.google.com/maps/search/?api=1&query="
	//// + keyword;
	// textTemplate = "[{\"type\":\"template\",\"altText\":\"" + "123"
	// +"\",\"template\":{"
	// + "\"type\":\"buttons\",\"text\":\"店名："+ "456" +"，分數：" + "789"
	// +"分\",\"actions\":[{"
	// + "\"type\":\"uri\",\"label\":\"查看地圖\",\"uri\":\"" + place_URI
	// +"\"}]}}]";
	//// String place_URI = gMap.getURLByText(keyword);
	// //https://www.google.com/maps/search/?api=1&query=pizza+seattle+wa
	// return textTemplate;
	// }

}
