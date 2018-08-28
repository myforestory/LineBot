package tw.idv.aloha.lineBot.controller;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tw.idv.aloha.lineBot.model.Event;
import tw.idv.aloha.lineBot.model.EventWrapper;
import tw.idv.aloha.lineBot.model.GMaps_Text_Search;

@Controller
public class LineBotRSController {
	//==== ngrok  ====
//    private String accessToken="uN4Adp2siY1bd4k2vcCzyN9wKGkHTONWuHmNAk+TcQE3GkKhUkOKab9CmbVNiV+K+bsDgqtq78ApPUfAq8b6RWYl1SBHrhnVNbvg37FQSaH4UAR51RGGNM+PouklBcquzR8wkeuAd0m5LppYZGtAdwdB04t89/1O/w1cDnyilFU=";
    //==== heroku ====
    private String accessToken="0kIC5VtMwqnF7zFuqAZLasS8fJp5nt5JDG6xon92Bv1OHw2gMPi7B8RvAdGC+18uJ0Do2VMyUg1etgigLszNGKJZMvggJijfX9JBs190jglt1ere6dXDj8gOIV1vLGjlx38cGtB2T2bYJSejYsSbOQdB04t89/1O/w1cDnyilFU=";
    
    @ResponseBody
    @RequestMapping(value="/callback")
    public void callback(@RequestBody EventWrapper events) {  
    	System.out.println("========333==========");
        for(Event event:events.getEvents()){
        	System.out.println("========35==========");
            switch(event.getType()){
               case "join":
            	   System.out.println("========36==========");
            	   String messageJoin = "{\"type\": \"text\", \"text\": \"好開心進來了" + event.getSource().getType() + "，我是阿囉哈哈哈\" }";
            	   sendResponseMessages(event.getReplyToken(), messageJoin);
            	   break;
               case "message": //當event為message時進入此case執行，其他event(如follow、unfollow、leave等)的case在此省略，您可自己增加           
                   System.out.print("This is a message event!");
                   switch(event.getMessage().getType()){
                   case "text": //當message type為text時，進入此case執行，目前子是將使用者傳來的文字訊息在其前加上echo字串後，回傳給使用者
                	   typeIsMessage(event.getReplyToken(), event.getMessage().getText());
                       System.out.println("This is a text message. It's replytoken is "+event.getReplyToken());
                       break;
                   case "image"://當message type為image時，進入此case執行，
                       System.out.println("This is a image message. It's replytoken is "+event.getReplyToken());
                       break;
                   case "audio"://當message type為audio時，進入此case執行，
                       System.out.println("This is a audio message. It's replytoken is "+event.getReplyToken());
                       break;
                   case "video"://當message type為video時，進入此case執行，
                       System.out.println("This is a video message. It's replytoken is "+event.getReplyToken());
                       break;
                   case "file"://當message type為file時，進入此case執行，
                       System.out.println("This is a file message. It's replytoken is "+event.getReplyToken());
                       break;
                   case "sticker"://當message type為sticker時，進入此case執行，
                       System.out.println("This is a sticker message. It's replytoken is "+event.getReplyToken());
                       
                   case "location"://當message type為location時，進入此case執行，
                	   typeIsLocation(event.getReplyToken(), event.getMessage().getLatitude(), event.getMessage().getLongitude());
                       System.out.println("This is a location message. It's replytoken is "+event.getReplyToken());
                       break;
                   default:
                	   System.out.println("============default===============");
                	   break;
                   }
                   
                   break;
               }
        }
    }
    
    private void sendResponseMessages(String replyToken, String message) {
        try {    	
            message = "{\"replyToken\":\""+replyToken+"\",\"messages\":" + message + "}"; //回傳的json格式訊息
            System.out.println();
        	System.out.println(message);
            URL myurl = new URL("https://api.line.me/v2/bot/message/reply"); //回傳的網址
            HttpsURLConnection con = (HttpsURLConnection)myurl.openConnection(); //使用HttpsURLConnection建立https連線
            con.setRequestMethod("POST");//設定post方法
            con.setRequestProperty("Content-Type", "application/json; charset=utf-8"); //設定Content-Type為json
            con.setRequestProperty("Authorization", "Bearer "+this.accessToken); //設定Authorization
            con.setDoOutput(true);
            con.setDoInput(true);
            DataOutputStream output = new DataOutputStream( con.getOutputStream() ); //開啟HttpsURLConnection的連線
            output.write( message.getBytes(Charset.forName("utf-8")) );  //回傳訊息編碼為utf-8
            output.close();
            System.out.println("Resp Code:"+con.getResponseCode()+"; Resp Message:"+con.getResponseMessage()); //顯示回傳的結果，若code為200代表回傳成功
        } catch (MalformedURLException e) {
            System.out.println("Message: "+e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Message: "+e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void typeIsMessage(String replyToken, String message){
    	if(message.equals("餐廳推薦")){
    		String messageTemplate = "[{\"type\":\"template\",\"altText\":\"你在哪ㄋ\",\"template\":{"
    				+ "\"type\":\"buttons\",\"text\":\"你在哪ㄋ\",\"actions\":[{"
    				+ "\"type\":\"uri\",\"label\":\"給我位置\",\"uri\":\"line://nv/location\"}]}}]";
    		sendResponseMessages(replyToken, messageTemplate);
    	}
//    	message = "[{\"type\":\"text\",\"text\":\""+message+"\"}]";
//    	sendResponseMessages(replyToken, message);
    }
    
    private void typeIsLocation(String replyToken, String latitude, String longitude){
    	String URL = GMaps_Text_Search.getURL(latitude, longitude);
    	List<Map> searchList = GMaps_Text_Search.gMapSearch(URL);
    	double rating = 0.0;
    	double ratingBest = 0.0;
    	String name = "";
    	String place_id = "";
    	String place_latitude = "";
    	String place_longitude = "";
    	//最好的一家
    	for(Map map : searchList){
    		rating = Double.parseDouble(map.get("rating").toString()) ;
    		if (rating > ratingBest){
    			ratingBest = rating;
    			name = (String) map.get("name");
    			place_id = (String) map.get("place_id");
    			place_latitude = (String) map.get("latitude");
    			place_longitude = (String) map.get("longitude");
    		}
    	}
    	String place_URI = "https://www.google.com/maps/search/?api=1&query="
    						+ place_latitude + "," + place_longitude
    						+ "&query_place_id=" + place_id;
    	String messageTemplate = "[{\"type\":\"template\",\"altText\":\"" + name +"\",\"template\":{"
                + "\"type\":\"buttons\",\"text\":\"店名："+ name +"，分數：" + rating +"分\",\"actions\":[{"
                + "\"type\":\"uri\",\"label\":\"查看地圖\",\"uri\":\"" + place_URI +"\"}]}}]";
		sendResponseMessages(replyToken, messageTemplate);
    }
}
