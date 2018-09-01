package tw.idv.aloha.lineBot.controller;

import tw.idv.aloha.lineBot.Utli.MessageTemplate;
import tw.idv.aloha.lineBot.controller.GMapSearch;

public class TextAction extends LineBotRSController {
	static String[] recommendTypeArray = new String[] { "Aloha推薦飯", "Aloha推薦麵", "Aloha推薦咖哩飯", "Aloha推薦義大利麵",
			"Aloha推薦牛肉麵", "Aloha推薦麵線", "Aloha推薦美食", "Aloha推薦麵飲料", "Aloha推薦牛排", "Aloha推薦火鍋" };

	public static String callbackMessageText(String text) {
		String callbackMessage = "";
		if (text.toLowerCase().equals("aloha推薦")) {
			callbackMessage = isRecommend(text);
			// } else if (text.substring(0 ,7).equals("我想吃最好吃的")){
			// callbackMessage = recommendBestOne(text);
		} else if (text.substring(0, 5).equals("我想吃很多")) {
			callbackMessage = recommendMutli(text);
		} else if (text.substring(0, 3).equals("我想吃")) {
			callbackMessage = recommend(text);
		}
		return callbackMessage;
	}

	// aloha推薦
	private static String isRecommend(String text) {
		String textTemplate = "";
		//buttonTemplateFromString(String altText, String text, String label, String URI)
		textTemplate = MessageTemplate.buttonTemplateFromString(
			"你在哪ㄋ",
			"也可輸入：我想吃＋地名＋想吃什麼 例：我想吃西門，咖哩飯， or 我想吃 很多/最好吃的 咖哩飯",
			"你在哪ㄋ 給我位置",
			"line://nv/location"
		);
		return textTemplate;
	}

	// 我想吃.....show one
	private static String recommend(String text) {
		String textTemplate = "";
		String isComma = Character.toString(text.charAt(5));
		if (isComma.equals(",") || isComma.equals("，")) {
			GMapSearch gMap = new GMapSearch();
			String keyword = text.substring(3, text.length());
			String callbackURL = gMap.getURLByText(keyword);
			textTemplate = LocationAction.getGMapSearch(callbackURL);
			// 綜合清單
		} else {
			//textMessage(String text)
			textTemplate = MessageTemplate.textMessage(
				"地名只能有兩個字喔！ 例如: 我想吃三重，牛排 "
			);
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
		textTemplate = MessageTemplate.buttonTemplateFromString(
			"不要吃得太撐喔！",
			"祝你吃到撐",
			"查看地圖",
			place_URI
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
