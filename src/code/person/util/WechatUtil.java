package code.person.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.annotation.Resource;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import code.person.pojo.message.OnlineKfList;




/**
 * 公众平台通用接口工具类
 *
 */
@Service("wechatUtil")
public class WechatUtil {
	private static Logger log = LoggerFactory.getLogger(WechatUtil.class);
	
	private QuartzAccessToken quartzAccessToken;
	

	public QuartzAccessToken getQuartzAccessToken() {
		return quartzAccessToken;
	}

	@Resource
	public void setQuartzAccessToken(QuartzAccessToken quartzAccessToken) {
		this.quartzAccessToken = quartzAccessToken;
	}


	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			String filePath = null;
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (outputStr != null) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码

				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			System.out.println(ce.getMessage());
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}
	
	
	/**
	 * 发起http请求(报文格式为xml，返回报文也为xml)并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	/*public XmlHttpResponseMessage httpRequestXML(String requestUrl, String requestMethod, String outputStr) {
		XmlHttpResponseMessage xmlObject = null;
		XmlHttpResponseMessageFactory xmlHttpResponseMessageFactory = null;
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");//; 
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			String filePath = null;
			httpUrlConn.connect();
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (outputStr != null) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
//				OutputStreamWriter outr = new OutputStreamWriter(outputStream);
//			     // 写入请求参
//				 outr.write(outputStr);
//			     outr.close();
//				 outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			xmlHttpResponseMessageFactory=new SimpleXmlHttpResponseMessageFactory();
			System.out.println("客服返回报文："+buffer.toString());
			xmlObject = xmlHttpResponseMessageFactory.parseMessage(buffer.toString().getBytes("UTF-8"));
		} catch (ConnectException ce) {
			System.out.println(ce.getMessage());
			log.error("kefu server connection timed out.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error("https request error:{}", e);
		}
		return xmlObject;
	}*/
	
	public JSONObject uploadFile(File file,String type) {
		
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+quartzAccessToken.getAccessToken()+"&type="+type;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod("POST");
			
			httpUrlConn.setRequestProperty("Connection", "Keep-Alive");
			httpUrlConn.setRequestProperty("Charset", "utf-8");
			String BOUNDARY="----------"+System.currentTimeMillis();
			httpUrlConn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+BOUNDARY);
			StringBuffer sb = new StringBuffer();
			sb.append("--");
			sb.append(BOUNDARY);
			sb.append("\r\n");
			sb.append("Content-Disposition:form-data;name=\"file\";filename=\""+file.getName()+"\"\r\n");
			sb.append("Content-Type:application/octet-stream\r\n\r\n");
			byte[] head = sb.toString().getBytes("utf-8");
			
			// 当有数据需要提交时
			OutputStream outputStream = new DataOutputStream(httpUrlConn.getOutputStream());
			// 注意编码格式，防止中文乱码

			outputStream.write(head);
			
			FileInputStream fis = new FileInputStream(file);
			
			byte[] data = new byte[fis.available()];
			fis.read(data);
			fis.close();
			outputStream.write(data);
			
			byte[] foot=("\r\n--"+BOUNDARY+"--\r\n").getBytes("utf-8");
			outputStream.write(foot);
			outputStream.flush();
			outputStream.close();
				
				
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}

			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			System.out.println(ce.getMessage());
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}
	
	
	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public final static String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	/**
	 * 获取access_token
	 * 
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */
	public AccessToken getAccessToken(String appid, String appsecret) {
		AccessToken accessToken = null;
			
		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		System.out.println(requestUrl);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		System.out.println(jsonObject+"你好");
		
		// 如果请求成功
		if (jsonObject != null) {
			try {
				accessToken = new AccessToken();
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
				
				String url = jsapi_ticket_url.replace("ACCESS_TOKEN", accessToken.getToken());
				JSONObject jsonObjectTicket = httpRequest(url, "GET", null);
				if(jsonObjectTicket!=null){
					accessToken.setTicket(jsonObjectTicket.getString("ticket"));
				}
				
				System.out.println(jsonObject.getString("access_token")+":::"+new Date());
				System.out.println(jsonObjectTicket.getString("ticket"));
			} catch (JSONException e) {
				accessToken = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return accessToken;
	}
	
	// 菜单创建（POST） 限100（次/天）
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	/**
	 * 创建菜单
	 * 
	 * @param menu 菜单实例
	 * @param accessToken 有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public int createMenu(String jsonMenu) {
		int result = 0;
		String accessToken = quartzAccessToken.getAccessToken();
		// 拼装创建菜单的url
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		System.out.println(url);
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);
	
		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
	
		return result;
	}
	
	public static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	
	public int deleteMenu() {
		int result = 0;
		System.out.println("not "+"good");
		String accessToken = quartzAccessToken.getAccessToken();
		System.out.println(accessToken+"good");
		// 拼装创建菜单的url
		String url = menu_delete_url.replace("ACCESS_TOKEN", accessToken);
		System.out.println(url);
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "GET", "");
	
		if (null != jsonObject) {
			if (0 != jsonObject.getInt("errcode")) {
				result = jsonObject.getInt("errcode");
				log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
	
		return result;
	}
	
	public static String user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	public JSONObject getUserInfo(String openId){
		String accessToken = quartzAccessToken.getAccessToken();
		String url = user_info_url.replace("ACCESS_TOKEN", accessToken);
		url = url.replace("OPENID", openId);
		//获得客户信息
		JSONObject jsonObject =  httpRequest(url, "GET", "");
		
		return jsonObject;
	}
	
	public static String online_kf = "https://api.weixin.qq.com/cgi-bin/customservice/getonlinekflist?access_token=ACCESS_TOKEN";
	
	public OnlineKfList getOnlineKf(){
		String accessToken = quartzAccessToken.getAccessToken();
		String url = online_kf.replace("ACCESS_TOKEN", accessToken);
		//获得在线客服
		JSONObject jsonObject =  httpRequest(url, "GET", "");
		OnlineKfList okl = (OnlineKfList)JSONObject.toBean(jsonObject,OnlineKfList.class);		
		return okl;
	}
	
	public String sendMsg(String msgContent){
		String msg = "";
		// 客服请求url
		String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
		
		String token = quartzAccessToken.getAccessToken();
		
		url = url+token;
		System.out.println(msgContent);
		JSONObject jsonObject = httpRequest(url, "POST", msgContent);
		msg = jsonObject.toString();
		System.out.println(msg);
		return msg;
	}
	
	public String sendTemplateMsg(String msgContent){
		String msg = "";
		
		// 客服请求url
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
		String token = quartzAccessToken.getAccessToken();
		url = url+token;
		JSONObject jsonObject = httpRequest(url, "POST", msgContent);
		msg = jsonObject.toString();
		
		return msg;
	}
	
	
	public JSONObject getSucai(String sc){
		String token = quartzAccessToken.getAccessToken();
		String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=";
		url = url+token;
		//获取素材列表
		JSONObject jsonObject = httpRequest(url,"POST",sc);
		return jsonObject;
	}
	
//	public JSONObject getSingleSucai(String media_id){
//		String token = quartzAccessToken.getAccessToken();
//		String url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=";
//		url = url+token;
//		//获取素材列表
//		JSONObject jsonObject = httpRequest(url,"POST",media_id);
//		return jsonObject;
//	}
	
	public JSONObject massSend(String sendMsg){
		String token = quartzAccessToken.getAccessToken();
		String url =  "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=";
		url = url+token;
		//按openId群发消息
		JSONObject jsonObject = httpRequest(url,"POST",sendMsg);
		return jsonObject;
	}
	
	public JSONObject getMaterial(String mediaId){
		String sendMsg = "";
		JSONObject jo = new JSONObject();
		jo.put("media_id", mediaId);
		sendMsg = jo.toString();
		System.out.println(sendMsg);
		String token = quartzAccessToken.getAccessToken();
		String url =  "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=";
		url = url+token;
		//按openId群发消息
		JSONObject jsonObject = httpRequest(url,"POST",sendMsg);
		return jsonObject;
	}
	
	public String getMultimedia(String image_url,String fileName){
		System.out.println("从微信公众平台下载图片开始---------------------------------------------------------------");
		String file = null;
		String requesturl  = image_url;
		URL url;
		try {
			url = new URL(requesturl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			//String filePath = "C:\\image\\"+fileName+".jpg";
			String filePath = "/home/ebank/app/IBM/WebSphere/AppServer/profiles/wechat/images_send/"+fileName+".jpg";
			InputStream inputStream = httpUrlConn.getInputStream();
			/**
			 * zch
			 */
			BufferedInputStream bis =  new BufferedInputStream(inputStream);
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[2048];
			int size = 0;
			while((size = bis.read(buf)) != -1){
				fos.write(buf,0,size);
			}
			fos.close();
			bis.close();
			// 释放资源
			httpUrlConn.disconnect();
			System.out.println("从微信公众平台下载图片成功结束---------------------------------------------------------------");
			// 通过ftp上传图片至文件服务器
			System.out.println("FTP上传图片至文件服务器开始-----------------------------------------------------------------");
			try {
				FileInputStream in = new FileInputStream(new File(filePath));
				//FTPUtil f = new FTPUtil("200.5.1.187", 21, "bankfile", "pass");
				FTPUtil f = new FTPUtil("10.0.102.28", 21, "root", "1qaz@WSX");
				f.uploadFile("/opt/IBM/WebSphere/AppServer/profiles/AppSrv01/installedApps/web-2Cell01/agentim_war.ear/agentim.war/upload/", 
						fileName+".jpg", in, "cover");
				System.out.println("FTP上传图片至文件服务器成功结束---------------------------------------------------------------------");	
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}
	
	
	
}