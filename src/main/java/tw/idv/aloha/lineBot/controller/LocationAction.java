package tw.idv.aloha.lineBot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tw.idv.aloha.lineBot.Utli.MessageTemplate;

public class LocationAction {

	public static String callbackMessageLocation(String lat, String lng) {
		GMapSearch gMap = new GMapSearch();
		String callbackMessage = "";
		String text = "";
		String callbackURL = gMap.getURLByLocation(lat, lng);
		callbackMessage = getGMapSearch(callbackURL, text);
		return callbackMessage;
	}
	
	public static String getGMapSearch(String callbackURL, String text) {
		GMapSearch gMap = new GMapSearch();
		String locationTemplate = "";
		List<Map<String, Object>> searchList = gMap.gMapSearch(callbackURL);
		if(searchList.size() == 0){
			locationTemplate = MessageTemplate.textMessage(
				"拍謝，這個位置附近沒有好吃的，換個地方試試！"
			);
		} else {
			if(text.toLowerCase().charAt(0) == 's'){ //surprise version
				
			} else { // original version
				// Get RandomOne Map Research //隨機一家
				Map<String, Object> locationMap = gMap.getGMapRandom(searchList);
				if(!locationMap.isEmpty()){
					locationTemplate = MessageTemplate.buttonTemplateFromMap(locationMap, text);	
				} else {
					locationTemplate = MessageTemplate.textMessage(
						"拍謝，這個位置附近沒有好吃的，換個地方試試！"
					);
				}	
			}
		}
		return locationTemplate;
		// Get BestOne Map Research //最好的一家
		// Map<String, Object> locationMap = GMapSearch.getGMapBest(searchList);
	}

	public static String getGMapSearchVerEg(String callbackURL, String text) {
		GMapSearch gMap = new GMapSearch();
		String locationTemplate = "";
		List<Map<String, Object>> searchList = gMap.gMapSearch(callbackURL);
		if(searchList.size() == 0){
			locationTemplate = MessageTemplate.textMessage(
				"Sorry, no result, change another keyword and try it！"
			);
		} else {
			// Get RandomOne Map Research //隨機一家
			Map<String, Object> locationMap = gMap.getGMapRandom(searchList);
			if(!locationMap.isEmpty()){
				locationTemplate = MessageTemplate.buttonTemplateFromMapVerEg(locationMap, text);	
			} else {
				locationTemplate = MessageTemplate.textMessage(
					"Sorry, no result, change another keyword and try it！"
				);
			}	
		}
		return locationTemplate;
		// Get BestOne Map Research //最好的一家
		// Map<String, Object> locationMap = GMapSearch.getGMapBest(searchList);
	}
	
	public static String getIfoodieSearch(String callbackURL, String text, String foodName) {
		GMapSearch gMap = new GMapSearch();
		String locationTemplate = "";
		String ifoodieURL = "";
		Map<String, Object> coordinateMap = new HashMap<String, Object>();
		List<Map<String, Object>> coordinateList = gMap.gMapCoordinate(callbackURL);
		if(coordinateList.size() == 0){
			locationTemplate = MessageTemplate.textMessage(
				"拍謝，您輸入的地點怪怪的，再輸入一次好嗎！"
			);
		} else {
			coordinateMap = gMap.gMapCoordinate(callbackURL).get(0);
		}
		ifoodieURL = gMap.getIfoodieURL(coordinateMap, foodName);	
		List<Map<String, Object>> searchList = gMap.ifoodieSearch(ifoodieURL);
		if(searchList.size() == 0){
			locationTemplate = MessageTemplate.textMessage(
				"拍謝，這個位置附近沒有好吃的，換個地方試試！"
			);
		} else {
//			if(text.toLowerCase().charAt(0) == 's'){ //surprise version
//				
//			} else { // original version
			// Get RandomOne Map Research //隨機一家
			Map<String, Object> locationMap = gMap.getIfoodieRandom(searchList, foodName, text);
			if(!locationMap.isEmpty()){
				locationTemplate = MessageTemplate.buttonTemplateFromMapSurprise(locationMap, text);	
			} else {
				locationTemplate = MessageTemplate.textMessage(
					"拍謝，這個位置附近沒有好吃的，換個地方試試！"
				);
			}
		}
		return locationTemplate;
		// Get BestOne Map Research //最好的一家
		// Map<String, Object> locationMap = GMapSearch.getGMapBest(searchList);
	}
}
