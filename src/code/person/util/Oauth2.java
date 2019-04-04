package code.person.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
/*
 * OAUTHЭ��Ϊ�û���Դ����Ȩ�ṩ��һ����ȫ�ġ����Ŷ��ּ��׵ı�׼��
 * ����������Ȩ��ʽ��֮ͬ����OAUTH����Ȩ����ʹ�������������û����ʺ���Ϣ�����û��������룩��
 * ������������ʹ���û����û���������Ϳ��������ø��û���Դ����Ȩ�����OAUTH�ǰ�ȫ�ġ�oAuth��Open Authorization�ļ�д��
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