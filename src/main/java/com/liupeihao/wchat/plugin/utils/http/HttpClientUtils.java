package com.liupeihao.wchat.plugin.utils.http;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONTokener;
import com.liupeihao.wchat.plugin.utils.string.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 执行Http亲请求
 * 
 * @author LPH
 *
 */
public class HttpClientUtils {


	public static String post(String url, Map<String, String> params) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		// 请求超时 时间限制
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		String body = null;

		HttpPost post = postForm(url, params);

		body = invoke(httpclient, post);

		httpclient.getConnectionManager().shutdown();

		return body;
	}

	public static String post(String url, String paramsXML) {
		String result = null;
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		try {
			if (StringUtils.isNotBlank(paramsXML)) {
				HttpEntity entity;
				entity = new ByteArrayEntity(paramsXML.getBytes("UTF-8"));
				post.setEntity(entity);
			}
			HttpResponse response;
			try {
				response = client.execute(post);
				result = EntityUtils.toString(response.getEntity());
				return result;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Http 发送 json 字符串到url, 并且返回json格式的返回值
	 * 发送json 请求，返回 json
	 * @param url
	 * @param json
	 * @return
	 */
	public static JSONObject post(String url, JSONObject json) {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		JSONObject response = new JSONObject();
		try {
			StringEntity s = new StringEntity(json.toString());
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");
			post.setEntity(s);

			HttpResponse res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				String charset = EntityUtils.getContentCharSet(entity);
				response = new JSONObject(new JSONTokener(new InputStreamReader(entity.getContent(), charset)));
			}
		} catch (Exception e) {
			throw new HttpClientException(e);
		}
		return response;
	}
	
	public static String postJSONAndReturnJSON(String url, String jsonStr) {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		JSONObject response = new JSONObject();
		try {
			StringEntity s = new StringEntity(jsonStr, "UTF-8");
			s.setContentEncoding("UTF-8");
			s.setContentType("application/json");
			post.setEntity(s);

			HttpResponse res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				String charset = EntityUtils.getContentCharSet(entity);
				System.out.println(entity.getContent());
				response = new JSONObject(new JSONTokener(new InputStreamReader(entity.getContent())));
			}
		} catch (Exception e) {
			throw new HttpClientException(e);
		}
		return response.toString();
	}

	public static String get(String url) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 60000);
		String body = null;

		HttpGet get = new HttpGet(url);
		get.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "UTF-8");
		body = invoke(httpclient, get);

		httpclient.getConnectionManager().shutdown();

		return body;
	}

	private static String invoke(DefaultHttpClient httpclient, HttpUriRequest httpost) {

		HttpResponse response = sendRequest(httpclient, httpost);
		String body = paseResponse(response);

		return body;
	}

	private static String paseResponse(HttpResponse response) {
		HttpEntity entity = response.getEntity();

		String charset = EntityUtils.getContentCharSet(entity);

		String body = null;
		try {
			body = EntityUtils.toString(entity, charset);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return body;
	}

	private static HttpResponse sendRequest(DefaultHttpClient httpclient, HttpUriRequest httpost) {
		HttpResponse response = null;

		try {
			response = httpclient.execute(httpost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	private static HttpPost postForm(String url, Map<String, String> params) {

		HttpPost httpost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}

		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return httpost;
	}

	/**
	 * 发送请求到http服务然后接收返回报文 注意此次发送的报文是以流的方式进行发送的
	 * 
	 * @param urlPath
	 *            请求的http服务的路径
	 * @param streamStr
	 *            请求的字符串
	 * @return
	 */
	public static String sendHttpWithStream(String urlPath, String streamStr) {
		String returnMsgStr = "";// 保存调用http服务后的响应信息
		try {
			// 得到请求报文的二进制数据
			byte[] data = streamStr.getBytes();
			java.net.URL url = new java.net.URL(urlPath);
			// openConnection() 返回一个 URLConnection 对象，它表示到 URL 所引用的远程对象的连接
			try {
				java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");// 设置post方式请求
				conn.setConnectTimeout(5 * 1000);// 设置连接超时时间为5秒 JDK1.5以上才有此方法
				conn.setReadTimeout(20 * 1000);// 设置读取超时时间为20秒 JDK1.5以上才有此方法
				// 打算使用 URL 连接进行输出，则将 DoOutput 标志设置为 true
				conn.setDoOutput(true);
				// 这里只设置内容类型与内容长度的头字段根据传送内容决定
				// 内容类型Content-Type:
				// application/x-www-form-urlencoded、text/xml;charset=GBK
				// 内容长度Content-Length: 38
				conn.setRequestProperty("Content-Type", "text/xml;charset=GBK");
				conn.setRequestProperty("Content-Length", String.valueOf(data.length));
				OutputStream outStream = conn.getOutputStream();// 返回写入到此连接的输出流
				// 把二进制数据写入是输出流
				outStream.write(data);
				// 内存中的数据刷入
				outStream.flush();
				// 关闭流
				outStream.close();

				// 如果请求响应码是200，则表示成功
				if (conn.getResponseCode() == 200) {
					// HTTP服务端返回的编码是UTF-8,故必须设置为UTF-8,保持编码统一,否则会出现中文乱码
					BufferedReader in = new BufferedReader(
							new InputStreamReader((InputStream) conn.getInputStream(), "UTF-8"));// 返回从此打开的连接读取的输入流
					returnMsgStr = in.readLine();
					in.close();
				}
				conn.disconnect();// 断开连接
				return returnMsgStr;
			} catch (IOException e) {
				e.printStackTrace();
			} // 打开一个连接
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return returnMsgStr;
	}
}
