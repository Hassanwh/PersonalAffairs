package code.person.action.base;

import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import code.person.util.JsApi;
import code.person.util.Oauth2;
import code.person.util.OauthToken;
import code.person.util.QuartzAccessToken;
import code.person.util.SysConfig;
import code.person.util.Utility;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


/**
 * Description:
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class WechatBaseAction extends ActionSupport
{
	protected String xmlReq;	
	private String code;
	private String state;
	protected String openid="";
	private String tradeName="微信银行交易";
	private String lastUrl="#";
	private String confirmMessage = null;
	//private String webUrl = "https://ebank.ordosbank.com/wechat/";
	private String webUrl = "http://18050r54q3.51mypc.cn/PersonalAffairs/wechat.action";
	private JsApi jsApi;
	//@Autowired
	//private CusInfoService cusInfoService;
	//@Autowired
	//private LockInfoService lockInfoService;
	private QuartzAccessToken quartzAccessToken;
	private Oauth2 oauth2;
	
	
	
	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	protected void setConfirmMessage(String m) {
		confirmMessage = m;
	}
	
	public String getConfirmMessage() {
		return confirmMessage;
	}
	
	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getXmlReq() {
		return xmlReq;
	}

	public void setXmlReq(String xmlReq) {
		this.xmlReq = xmlReq;		
	}	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	} 
	
	public JsApi getJsApi(HttpServletRequest request) {
		jsApi = createJsApi(request);
		return jsApi;
	}

	public void setJsApi(JsApi jsApi) {
		this.jsApi = jsApi;
	}

	public HashMap getReqHmFromXml() throws Exception {
		// 将解析结果存储在HashMap中
		HashMap map = new HashMap();
		xmlReq = "<xmlreq>"+xmlReq+"</xmlreq>";
		//System.out.println(xmlReq);
		// 读取输入流
		Document document = DocumentHelper.parseText(xmlReq);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		for (Element e : elementList){
			map.put(e.getName(), e.getText());
			//System.out.println(e.getName()+"  "+e.getText());
		}

		return map;
	}
	
	public void setSession() throws Exception{
		OauthToken ot = null;
		try {
			ot = oauth2.getOauthToken(code);
			openid = ot.getOpenid();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(openid == null){
			openid = getOpenid();
			if(openid==null)
				throw new Exception("对不起!您没有访问该功能的权限!");
		}
		//if(!cusInfoService.isExitTest(openid)){
			//System.out.println(openid);
			//System.out.println("ddd");
		//	throw new Exception("您好，此微信公众号还在测试阶段，暂时不能为您提供服务，敬请谅解！");
		//}
		//if(lockInfoService.isNotLock(openid)){
		//	throw new Exception("您好，此微信号已经被锁，请先解锁！");
		//}
		
		System.out.println("here input openid into ActionContext:"+openid);
		ActionContext ctx = ActionContext.getContext();
		ctx.getSession().put("wechatId", openid);
		ctx.getSession().put("token", ot.getToken());
	}
	
	public String getSession(String sessionKey){
		String result = "";
		ActionContext ctx = ActionContext.getContext();
		result = (String)ctx.getSession().get(sessionKey);
		
		return result;
	}
	
	public Object getSessionObj(String sessionKey){
		ActionContext ctx = ActionContext.getContext();
		
		return  ctx.getSession().get(sessionKey);
	}
	
	public void setSessionObj(String key,Object value){
		ActionContext ctx = ActionContext.getContext();
		ctx.getSession().put(key, value);
	}
	
	public String getOpenid(){
		
		String openid = (String)ActionContext.getContext().getSession().get("wechatId");
		if(ActionContext.getContext().getSession().containsKey("wechatId"))
		{
			System.out.println("there is openid:"+openid);
		}
		return openid;
	}


	public String getLastUrl() {			
		return lastUrl;
	}


	public void setLastUrl(HttpServletRequest request) {
		String tempUrl = "#";
		if(request!=null){
			tempUrl =request.getRequestURL().toString();
		}
		ActionContext ctx = ActionContext.getContext();
		String url = (String)ctx.getSession().get("lastUrl");
		if(url!=null){
			this.lastUrl = url;
		}
		ctx.getSession().put("lastUrl", tempUrl);
	}
	
	public JsApi createJsApi(HttpServletRequest request){
		JsApi jsApi = new JsApi();
		String nonceStr = RandomStringUtils.randomAlphanumeric(16);
		Long timestamp = System.currentTimeMillis();
		String url = request.getRequestURL().toString();
	
		String string1 = "jsapi_tecket="+quartzAccessToken.getJsapTicket()+"&noncestr="
			+nonceStr+"&timestamp="+timestamp+"&url="+url;
		//System.out.println(string1);
		String signature = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			// 对接后的字符串进行sha1加密
			byte[] digest = md.digest(string1.toString().getBytes());
			signature = Utility.byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		jsApi.setAppId(SysConfig.appId);
		jsApi.setNonceStr(nonceStr);
		jsApi.setSignature(signature);
		jsApi.setTimestamp(timestamp);
		
		return jsApi;
	}

}