package tw.idv.aloha.lineBot.controller;

import tw.idv.aloha.lineBot.Utli.MessageTemplate;
import tw.idv.aloha.lineBot.controller.GMapSearch;

public class TextAction extends LineBotRSController {
	static String[] recommendTypeArray = new String[] { "Aloha推薦飯", "Aloha推薦麵", "Aloha推薦咖哩飯", "Aloha推薦義大利麵",
			"Aloha推薦牛肉麵", "Aloha推薦麵線", "Aloha推薦美食", "Aloha推薦麵飲料", "Aloha推薦牛排", "Aloha推薦火鍋" };

	public static String callbackMessageText(String text) {
		String callbackMessage = "";
		try{
			if (text.toLowerCase().equals("aloha推薦")||text.toLowerCase().equals("好餓")) {
				callbackMessage = isRecommend(text);
				// } else if (text.substring(0 ,7).equals("我想吃最好吃的")){
				// callbackMessage = recommendBestOne(text);
			} else if (text.length() >= 5 && text.substring(0, 5).equals("我想吃很多")) {
				callbackMessage = recommendMutli(text);
			} else if (text.substring(0, 3).equals("我想吃")) {
				callbackMessage = recommend(text);
			}
		}catch(Exception e){
			System.out.println(text);
		}
		return callbackMessage;
	}

	// aloha推薦
	private static String isRecommend(String text) {
		String textTemplate = "";
		//buttonTemplateFromString(String altText, String text, String label, String URI)
		textTemplate = MessageTemplate.buttonTemplateFromString(
			"你在哪ㄋ",
			"你在哪呢？\\n也可輸入：\\n我想吃＋地名＋想吃什麼\\n例：我想吃西門，鐵板燒\\n或者 我想吃很多炒麵",
			"給我位置",
			"line://nv/location"
		);
		return textTemplate;
	}

	private static String recommend(String text) {// 我想吃.....show one
		String textTemplate = "";
		if(text.length() < 7){
			textTemplate = MessageTemplate.textMessage(
				"請輸入正確的格式喔！\\n我想吃＋地名＋食物\\n例如: 我想吃三重，牛排 "
			);
		} else {
			String isComma5 = Character.toString(text.charAt(5)); //地名兩個字
			String isComma6 = Character.toString(text.charAt(6)); //地名三個字
			String isComma7 = Character.toString(text.charAt(7)); //地名四個字
			String callbackURL = ""; //傳回網址
			GMapSearch gMap = new GMapSearch();
			String keyword = text.substring(3, text.length());
			// 綜合清單
			if (isComma5.equals(",") || isComma5.equals("，")) {
				callbackURL = gMap.getURLByText(keyword, 2);
				textTemplate = LocationAction.getGMapSearch(callbackURL);
			} else if (isComma6.equals(",") || isComma6.equals("，")) {
				callbackURL = gMap.getURLByText(keyword ,3);
			} else if (isComma7.equals(",") || isComma7.equals("，")) {
				callbackURL = gMap.getURLByText(keyword ,4);
			} else {
				//textMessage(String text)
				textTemplate = MessageTemplate.textMessage(
					"可以換別的關鍵字搜搜看喔！ 例如: 我想吃三重，牛排 "
				);
			}
			textTemplate = LocationAction.getGMapSearch(callbackURL);
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
