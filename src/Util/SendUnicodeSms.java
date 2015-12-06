package Util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.commons.codec.binary.Base64;


@SuppressWarnings("deprecation")
public class SendUnicodeSms {
	private HttpClient client;
	private String from = "9790340545";
	private String sid = "roomteam";
	private String auth_key = "f9e27104346b9c7a2eaf80294e5b0cc49612c99d";

	public boolean send_message(String to,String msg) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClientProtocolException, IOException {
		Logger.log.info("...Trying to send an SMS to " + to + " with text '" + msg + "' EOM...");
		boolean sent = false;
		String out = new String(msg.getBytes("UTF-8"), "ISO-8859-1");
		client = new DefaultHttpClient();

		ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		postParameters.add(new BasicNameValuePair("From", from));
		postParameters.add(new BasicNameValuePair("To", to));
		postParameters.add(new BasicNameValuePair("Body", out));

		String authStr = sid + ":" + auth_key;
		String url = "https://" + authStr + "@twilix.exotel.in/v1/Accounts/" + sid + "/Sms/send";
		byte[] authEncBytes = Base64.encodeBase64(authStr.getBytes());
		String authStringEnc = new String(authEncBytes);
		HttpPost post = new HttpPost(url);
		post.setHeader("Authorization", "Basic " + authStringEnc);

		try {
			post.setEntity(new UrlEncodedFormEntity(postParameters));
		} catch (UnsupportedEncodingException e) {
			Logger.log.severe("Error while trying to encode post params to send SMS");
			Logger.log.severe(e.getMessage());
			e.printStackTrace();
		}

		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();

		int httpStatusCode = response.getStatusLine().getStatusCode();
		Logger.log.info("Message sent and http status code is: " + httpStatusCode);

		if(httpStatusCode == 200){
			Logger.log.info("Response Entity: " + EntityUtils.toString(entity));
			sent = true;
		}
		else{
			Logger.log.severe("Response Entity: " + EntityUtils.toString(entity));
		}

		return sent;
	}
}
