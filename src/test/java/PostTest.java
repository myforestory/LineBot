import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

public class PostTest {
	private static String accessToken = "0kIC5VtMwqnF7zFuqAZLasS8fJp5nt5JDG6xon92Bv1OHw2gMPi7B8RvAdGC+18uJ0Do2VMyUg1etgigLszNGKJZMvggJijfX9JBs190jglt1ere6dXDj8gOIV1vLGjlx38cGtB2T2bYJSejYsSbOQdB04t89/1O/w1cDnyilFU=";

	
	public static void main(String[] args) throws ClientProtocolException, IOException{
		HttpPost httpPost = new HttpPost("https://api.line.me/v2/bot/message/push");
		String message = "";
		
		message = "[{\"type\":\"template\",\"altText\":\"" + 123 + "\","
				+ "\"template\":{"
					+ "\"type\":\"buttons\","
					+ "\"text\":\"" + 789
					+ "\",\"actions\":"
						+ "[{" + "\"type\":\"message\","
								+ "\"label\":\"查看地圖\","
								+ "\"text\":\"" + "123" + "\""
						+ "},"
						+ "{" + "\"type\":\"message\","
								+ "\"label\":\"查看地圖\","
								+ "\"text\":\"" + "123" + "\""
						+ "}]"
					+ "}"
				+ "}]";
		message = "{\"to\":\"" + "Ue504bccb322175463f344bbf90dad918" + "\",\"messages\":" + message + "}"; // 回傳的json格式訊息
		 
		httpPost.setHeader("Authorization", "Bearer " + accessToken);
		httpPost.setHeader("Content-Type", "application/json; charset=utf-8");

	    System.out.println("Good");
		httpPost.setEntity(new StringEntity(message, "UTF8"));
		
		CloseableHttpResponse reponse = HttpClients.createDefault().execute(httpPost);
		System.out.println(reponse);
	}
}
 