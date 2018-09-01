package tw.idv.aloha.lineBot.controller;

import java.util.List;
import java.util.Map;

import tw.idv.aloha.lineBot.Utli.MessageTemplate;

public class LocationAction {

	public static String callbackMessageLocation(String lat, String lng) {
		GMapSearch gMap = new GMapSearch();
		String callbackMessage = "";
		String callbackURL = gMap.getURLByLocation(lat, lng);
		callbackMessage = getGMapSearch(callbackURL);
		return callbackMessage;
	}

	public static String getGMapSearch(String callbackURL) {
		GMapSearch gMap = new GMapSearch();
		String locationTemplate = "";
		List<Map<String, Object>> searchList = gMap.gMapSearch(callbackURL);

		// Get BestOne Map Research //最好的一家
		// Map<String, Object> locationMap = GMapSearch.getGMapBest(searchList);

		// Get RandomOne Map Research //隨機一家
		Map<String, Object> locationMap = gMap.getGMapRandom(searchList);
		locationTemplate = MessageTemplate.buttonTemplateFromMap(locationMap);
		return locationTemplate;
	}
}