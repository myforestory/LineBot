//package tw.idv.aloha.lineBot.Utli;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import android.location.Location;
//
//public class AndroidFuc {
//
//	
//	public Map<String, Object> getCloestOne(List<Map<String, Object>> resultList) {
//	
//	    double earthRadius = 3958.75; // in miles, change to 6371 for kilometer output
//	    for (Map<String, Object> callbackMap : resultList) {
//	    	
//	    	
//		}
//	    double dLat = Math.toRadians(lat2-lat1);
//	    double dLng = Math.toRadians(lng2-lng1);
//	
//	    double sindLat = Math.sin(dLat / 2);
//	    double sindLng = Math.sin(dLng / 2);
//	
//	    double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
//	        * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
//	
//	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
//	
//	    double dist = earthRadius * c;
//	
//	    return dist; // output distance, in MILES
//	}
//}
