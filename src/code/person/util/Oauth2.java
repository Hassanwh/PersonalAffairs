package code.person.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
/*
 * OAUTH协议为用户资源的授权提供了一个安全的、开放而又简易的标准。
 * 与以往的授权方式不同之处是OAUTH的授权不会使第三方触及到用户的帐号信息（如用户名与密码），
 * 即第三方无需使用用户的用户名与密码就可以申请获得该用户资源的授权，因此OAUTH是安全的。oAuth是Open Authorization的简写。
 */
@Service("oauth2")
public class Oauth2{
	
	private static String oauthUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	
	private static String oauthTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	private static String refreshTokenUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	
	private static String checkUrl = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";
	
	@Autowired
	private WechatUtil wechatUtil;
	
	
	public String getOauthUrl(String url,String state){
		try {
			url = SysConfig.appUrl + url;
			url = java.net.URLEncoder.encode(url, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(url+"::::::::::::"); 
		String urlResult = oauthUrl.replace("APPID", SysConfig.appId).replace("REDIRECT_URI", url).replace("STATE", state).replace("SCOPE", "snsapi_base");		
		
		return urlResult;
	}
	
	public OauthToken getOauthToken(String code){
		String requestUrl = oauthTokenUrl.replace("APPID", SysConfig.appId).replace("SECRET", SysConfig.appSecret).replace("CODE", code);
		JSONObject jsonObject = wechatUtil.httpRequest(requestUrl, "GET", null);
		OauthToken ot = null;
		if (jsonObject != null) {
			try {
				ot = new OauthToken();
				ot.setToken(jsonObject.getString("access_token"));
				ot.setExpiresIn(jsonObject.getInt("expires_in"));
				ot.setRefreshToken(jsonObject.getString("refresh_token"));
				ot.setScope(jsonObject.getString("scope"));
				ot.setOpenid(jsonObject.getString("openid"));
			} catch (JSONException e) {
				e.getMessage();
			}
		}
		return ot;
	}
	
}