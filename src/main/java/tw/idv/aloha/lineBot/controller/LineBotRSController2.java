//package tw.idv.aloha.lineBot.controller;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.linecorp.bot.client.LineMessagingClient;
//import com.linecorp.bot.client.LineMessagingClientBuilder;
//import com.linecorp.bot.model.PushMessage;
//import com.linecorp.bot.model.event.Event;
//import com.linecorp.bot.model.event.MessageEvent;
//import com.linecorp.bot.model.event.message.TextMessageContent;
//import com.linecorp.bot.model.message.ImagemapMessage;
//import com.linecorp.bot.model.message.ImagemapMessage.ImagemapMessageBuilder;
//import com.linecorp.bot.model.message.Message;
//import com.linecorp.bot.model.message.TextMessage;
//import com.linecorp.bot.model.message.imagemap.ImagemapAction;
//import com.linecorp.bot.model.message.imagemap.ImagemapArea;
//import com.linecorp.bot.model.message.imagemap.MessageImagemapAction;
//import com.linecorp.bot.spring.boot.annotation.EventMapping;
//import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
//
//import tw.idv.aloha.lineBot.dao.JdbcDAO;
//
//@Controller
//public class LineBotRSController2 {
//
//	@Resource(name = "jdbcDao")
//	private JdbcDAO jdbcDAO;
//	// ==== ngrok ====
//	private String accessToken = "uN4Adp2siY1bd4k2vcCzyN9wKGkHTONWuHmNAk+TcQE3GkKhUkOKab9CmbVNiV+K+bsDgqtq78ApPUfAq8b6RWYl1SBHrhnVNbvg37FQSaH4UAR51RGGNM+PouklBcquzR8wkeuAd0m5LppYZGtAdwdB04t89/1O/w1cDnyilFU=";
//
//	// ==== heroku ====
////    private String accessToken = "0kIC5VtMwqnF7zFuqAZLasS8fJp5nt5JDG6xon92Bv1OHw2gMPi7B8RvAdGC+18uJ0Do2VMyUg1etgigLszNGKJZMvggJijfX9JBs190jglt1ere6dXDj8gOIV1vLGjlx38cGtB2T2bYJSejYsSbOQdB04t89/1O/w1cDnyilFU=";
//	
//	
////	@ResponseBody
////	@RequestMapping(value = "/callback")
////	protected void callback(String replyToken, Message message) {
////		System.out.println(replyToken);
////		System.out.println(message);
////		LineMessagingClient client = new LineMessagingClientBuilder(accessToken).build();
////		List<Message> list = new ArrayList<>();
////		list.add(new TextMessage("GOooood"));
////		list.add(new TextMessage("Yesssssssss"));
////		ReplyMessage msg = new ReplyMessage(replyToken, list); 
////		client.replyMessage(msg);
////	}
//	
//
//    @EventMapping
//	@ResponseBody
//	@RequestMapping(value = "/callback")
//    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
//        System.out.println("event: " + event);
//        return new TextMessage(event.getMessage().getText());
//    }
//
//    @EventMapping
//    public void handleDefaultMessageEvent(Event event) {
//        System.out.println("event: " + event);
//    }
//	
//	
//	@ResponseBody
//	@RequestMapping(value = "/push")
//	public void push() {
//		LineMessagingClient client = new LineMessagingClientBuilder(accessToken).build();
//		List<Message> list = new ArrayList<>();
////		list.add(new TextMessage("GOooood"));
////		list.add(new TextMessage("Yesssssssss"));
//		
//		List<ImagemapAction> actions = new ArrayList<>();
//		actions.add(new MessageImagemapAction("today",new ImagemapArea(0,0,0,0)));
//		
//		ImagemapMessage imgMap = new ImagemapMessage(baseUrl, altText, imagemapBaseSize, actions );
//		list.add(imgMap);
//		
//		PushMessage msg = new PushMessage("Ue504bccb322175463f344bbf90dad918", list);
//		client.pushMessage(msg);
//		try {
//			jdbcDAO.query();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
