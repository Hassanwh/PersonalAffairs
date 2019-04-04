package code.person.service.maincsol.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code.person.pojo.cusservice.PpSendMessage;
import code.person.pojo.customer.CusInfo;
import code.person.pojo.message.Article;
import code.person.pojo.message.Image;
import code.person.pojo.message.InImageMessage;
import code.person.pojo.message.InLinkMessage;
import code.person.pojo.message.InLocationMessage;
import code.person.pojo.message.InTextMessage;
import code.person.pojo.message.InVideoMessage;
import code.person.pojo.message.InVoiceMessage;
import code.person.pojo.message.LocationEvent;
import code.person.pojo.message.MenuEvent;
import code.person.pojo.message.OutImageMessage;
import code.person.pojo.message.OutNewsMessage;
import code.person.pojo.message.OutTextMessage;
import code.person.pojo.message.ScanCodeEvent;
import code.person.pojo.message.ScanCodePushEvent;
import code.person.pojo.message.SubscribeEvent;
import code.person.pojo.message.UnSubscribeEvent;
import code.person.service.maincsol.WechatCommService;
import code.person.util.Utility;
import code.person.util.WechatUtil;



/**
 * Description:
 * <br/>Copyright (C), 2001-2011, 
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:	2012-11-05
 * @author  ldh
 * @version  2.0
 */
 @Service("wechatCommService")
public class WechatCommServiceImpl extends WechatTrade implements WechatCommService{
	
	Utility util = Utility.getInstance();
	
	private WechatUtil wechatUtil;
	
	
	public WechatUtil getWechatUtil() {
		return wechatUtil;
	}
	@Resource
	public void setWechatUtil(WechatUtil wechatUtil) {
		this.wechatUtil = wechatUtil;
	}

	private static String token="dlyz1005";
	
	public boolean checkSignature(String signature,String timestamp,String nonce){
		
		System.out.println("为什么");
		// 对token、timestamp和nonce按字典排序
		String[] paramArr = new String[] { token, timestamp, nonce };
		Arrays.sort(paramArr);

		// 将排序后的结果拼接成一个字符串
		String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);

		String ciphertext = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			// 对接后的字符串进行sha1加密
			byte[] digest = md.digest(content.toString().getBytes());
			ciphertext = Utility.byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		System.out.println(ciphertext);
		System.out.println(signature.toUpperCase());
		// 将sha1加密后的字符串与signature进行对比
		return ciphertext != null ? ciphertext.equals(signature.toUpperCase()) : false;
	}
	
	public HashMap getHashMapFromStram(String strXml) throws Exception{
		return getInMessageFromStram(strXml);
	}
		
	
	
	public String doTrade(HashMap inMessage) throws Exception{
		String openId = (String)inMessage.get("FromUserName");
		System.out.println(openId);
		/*if(true){
			String meid = (String)inMessage.get("ToUserName");
			OutTextMessage outTextMessage = new OutTextMessage();
			outTextMessage.setToUserName(openId);
			outTextMessage.setFromUserName(meid);
			outTextMessage.setCreateTime((new Date()).getTime());
			outTextMessage.setMsgType(super.OUT_MESSAGE_TYPE_TEXT);
			outTextMessage.setContent("您好，此微信号已经被锁，请先解锁！");
			
			HashMap hm = new HashMap();
			hm.put("content", outTextMessage.getContent());			
			JSONObject msg = new JSONObject();
			msg.put("touser", openId);
			msg.put("msgtype", "text");
			msg.put("text", hm);
			String msgJson = wechatUtil.sendMsg(msg.toString());
			
			return messageToXml(outTextMessage);
		}*/

		String outMessage = inMessageTrade(inMessage);
		return outMessage;

	}
	
	private String inMessageTrade(HashMap inMessage) throws Exception{
		String outMessage="";
		OutTextMessage outText = new OutTextMessage();
		OutNewsMessage outNews = new OutNewsMessage();
		Image image = new Image();
		String msgType = (String)inMessage.get("MsgType");
		String event = (String)inMessage.get("Event");
		System.out.println(msgType+"::::::::");
		System.out.println(event+"::::::::::");
		
		if(msgType.equals(super.IN_MESSAGE_TYPE_TEXT)){
			InTextMessage inTextMessage = new InTextMessage();
			evalMapToBean(inMessage,inTextMessage);
			if("王鹤".equals(inMessage.get("Content"))){
				outText.setToUserName(inTextMessage.getFromUserName());
				outText.setFromUserName(inTextMessage.getToUserName());
				outText.setCreateTime(new Date().getTime());
				outText.setMsgType(super.OUT_MESSAGE_TYPE_TEXT);
				outText.setContent("大帅哥");
				outMessage = messageToXml(outText);
			}
			if("石永庆".equals(inMessage.get("Content"))){
				outText.setToUserName(inTextMessage.getFromUserName());
				outText.setFromUserName(inTextMessage.getToUserName());
				outText.setCreateTime(new Date().getTime());
				outText.setMsgType(super.OUT_MESSAGE_TYPE_TEXT);
				outText.setContent("你最帅");
				outMessage = messageToXml(outText);
			}
			if("刘敏".equals(inMessage.get("Content"))){
				outText.setToUserName(inTextMessage.getFromUserName());
				outText.setFromUserName(inTextMessage.getToUserName());
				outText.setCreateTime(new Date().getTime());
				outText.setMsgType(super.OUT_MESSAGE_TYPE_TEXT);
				outText.setContent("大帅哥");
				outMessage = messageToXml(outText);
			}
		}else if(msgType.equals(super.IN_MESSAGE_TYPE_IMAGE)){
			InImageMessage inImageMessage = new InImageMessage();
			evalMapToBean(inMessage,inImageMessage);
			//outMessage = inImageMessageService.addInImageMessage(inImageMessage);
		}else if(msgType.equals(super.IN_MESSAGE_TYPE_VOICE)){
			InVoiceMessage inVoiceMessage = new InVoiceMessage();
			evalMapToBean(inMessage,inVoiceMessage);
			//outMessage = inVoiceMessageService.addInVoiceMessage(inVoiceMessage);
			//outMessage = onlineService(inVoiceMessage,msgType);
		}else if(msgType.equals(super.IN_MESSAGE_TYPE_VIDEO)){
			InVideoMessage inVideoMessage = new InVideoMessage();
			evalMapToBean(inMessage,inVideoMessage);
			//outMessage = inVideoMessageService.addInVideoMessage(inVideoMessage);
		}else if(msgType.equals(super.IN_MESSAGE_TYPE_LOCATION)){
			InLocationMessage inLocationMessage = new InLocationMessage();
			evalMapToBean(inMessage,inLocationMessage);
			//outMessage = inLocationMessageService.addInLocationMessage(inLocationMessage);
		}else if(msgType.equals(super.IN_MESSAGE_TYPE_LINK)){
			InLinkMessage inLinkMessage = new InLinkMessage();
			evalMapToBean(inMessage,inLinkMessage);
			//outMessage = inLinkMessageService.addInLinkMessage(inLinkMessage);
		}else if(msgType.equals(super.IN_MESSAGE_TYPE_EVENT)){
			if(event.equals(super.EVENT_TYPE_CLICK)){
				MenuEvent menuEvent = new MenuEvent();			
				evalMapToBean(inMessage,menuEvent);
				String eventKey = menuEvent.getEventKey();
				if("3003".equals(eventKey)){
					outNews.setToUserName(menuEvent.getFromUserName());
					outNews.setFromUserName(menuEvent.getToUserName());
					outNews.setCreateTime(new Date().getTime());
					outNews.setMsgType(super.OUT_MESSAGE_TYPE_NEWS);
					List<Article> list=new ArrayList<Article>();
					Article article=new Article();
					article.setDescription("");
					article.setTitle("\"请扫描二维码关注个人事务管理群\"");
					article.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/ERYiawYDIsodXn7ibk7UxUncG9OIlny5ISiaVuohdl1P5cGglicOvaqjEMvicq2x46iaanKwn8H3gBuiakWqfTyn5lepg/0");
					article.setUrl("http://personalaffairs.51vip.biz/PersonalAffairs/aa.do");				
					list.add(article);
					outNews.setArticleCount(list.size());
					outNews.setArticles(list);
					outMessage = messageToXml(outNews);
				}
				if("3004".equals(eventKey)){
					outText.setToUserName(menuEvent.getFromUserName());
					outText.setFromUserName(menuEvent.getToUserName());
					outText.setCreateTime(new Date().getTime());
					outText.setMsgType(super.OUT_MESSAGE_TYPE_TEXT);
					outText.setContent("关注本公众号后语言知识栏目您可以随意访问！[Yeah!]\n如需使用其他功能请您点击账户管理中的会员注册，注册成功后即可使用！/:rose\n如需帮助请点击联系我们！[Smirk]\n为此给您带来的不便尽请谅解！/:8*");
					outMessage = messageToXml(outText);
				}
				//outMessage = menuEventService.addMenuEvent(menuEvent);
			}else if(event.equals(super.EVENT_TYPE_SUBSCRIBE)){
				SubscribeEvent inObject = new SubscribeEvent();
				evalMapToBean(inMessage,inObject);
					outText.setToUserName(inObject.getFromUserName());
					outText.setFromUserName(inObject.getToUserName());
					outText.setCreateTime(new Date().getTime());
					outText.setMsgType(super.OUT_MESSAGE_TYPE_TEXT);
					outText.setContent("欢迎您加入个人事务管理系统！/:hug/:hug/:hug");
					outMessage = messageToXml(outText);
					
				//outMessage = cusInfoService.subscribeEvent(inObject);
			}else if(event.equals(super.EVENT_TYPE_UNSUBSCRIBE)){
				UnSubscribeEvent inObject = new UnSubscribeEvent();
				evalMapToBean(inMessage,inObject);
				//outMessage = cusInfoService.unSubscribeEvent(inObject);
			}else if(event.equals(super.EVENT_TYPE_SCAN)){
				ScanCodeEvent scanCodeEvent = new ScanCodeEvent();
				evalMapToBean(inMessage,scanCodeEvent);
				//outMessage = scanCodeEventService.addScanCodeEvent(scanCodeEvent);
			}else if(event.equals(super.EVENT_TYPE_LOCATION)){
				LocationEvent locationEvent = new LocationEvent();
				evalMapToBean(inMessage,locationEvent);
				//outMessage = locationEventService.addLocationEvent(locationEvent);
			}else if(event.equals(super.EVENT_TYPE_SCANCODE_WAITMSG)){
				ScanCodePushEvent scanCodePushEvent = new ScanCodePushEvent();
				evalMapToBean(inMessage,scanCodePushEvent);
				//outMessage = gameWiningService.getGameWiningInformation(scanCodePushEvent);
			}
		}
		
		
		
		return outMessage;
	}
	
	
	private String onlineService(Object inObject,String msgType){
		PpSendMessage ppSendMessage = new PpSendMessage();
		if(msgType.equals(super.IN_MESSAGE_TYPE_TEXT)){
			InTextMessage inTextMessage = (InTextMessage)inObject;
			ppSendMessage.setMsgId(inTextMessage.getFromUserName());
			ppSendMessage.setCjid(inTextMessage.getFromUserName());
			ppSendMessage.setMsgType("00");
			ppSendMessage.setMsgContent(inTextMessage.getContent());
			long createTime = new Date().getTime();
			ppSendMessage.setCreateTime(Long.toString(createTime));
		}else if(msgType.equals(super.IN_MESSAGE_TYPE_IMAGE)){
			InImageMessage inImageMessage = (InImageMessage)inObject;
			ppSendMessage.setMsgId(inImageMessage.getFromUserName());
			ppSendMessage.setCjid(inImageMessage.getFromUserName());
			ppSendMessage.setMsgType("01");
			ppSendMessage.setMsgurl(inImageMessage.getFromUserName()+"&"+inImageMessage.getCreateTime()+".jpg");
			System.out.println(inImageMessage.getPicUrl());
			long createTime = new Date().getTime();
			ppSendMessage.setCreateTime(Long.toString(createTime));
			/**
			 * 下载图片并通过FTP上传至文件服务器
			 */
			wechatUtil.getMultimedia(inImageMessage.getPicUrl(),inImageMessage.getFromUserName()+"&"+inImageMessage.getCreateTime());
		}else if(msgType.equals(super.IN_MESSAGE_TYPE_VOICE)){
			InVoiceMessage inVoiceMessage = (InVoiceMessage)inObject;
			ppSendMessage.setMsgId(inVoiceMessage.getFromUserName());
			ppSendMessage.setCjid(inVoiceMessage.getFromUserName());
			ppSendMessage.setMsgType("00");
			ppSendMessage.setMsgContent(inVoiceMessage.getRecognition());
			long createTime = new Date().getTime();
			ppSendMessage.setCreateTime(Long.toString(createTime));
		}
		
		//httpTradeService.sendMessage(ppSendMessage);
		return "";
	}

	private String messageTrade(InTextMessage inMessage){
		String outMessage = "";
		String content = inMessage.getContent();
		String resultContent = "";
		if(content.equals("?")||content.equals("？")){
			resultContent="你要问什么？";
		}else if(content.equals("你好")){
			resultContent="你好，我好，大家好，大家好才是真的好";
		}else if(content.equals("test")){
			resultContent="点击后面<a href=\"http://58.18.253.138/wechat/test.jsp\">测试网页</a>!";
		}else if(content.equals("测试客服")){
			HashMap hm = new HashMap();
			String msgContent = inMessage.getFromUserName();
			hm.put("content", msgContent);			
			JSONObject msg = new JSONObject();
			msg.put("touser", "oNgRquPB_u2FcoGvXA696pYPh2Nc");
			msg.put("msgtype", "text");
			msg.put("text", hm);
			String msgJson = wechatUtil.sendMsg(msg.toString());
			System.out.println(msgJson);
			return "";
		}else if(content.length()>31&&content.substring(0, 3).equals("ldh")){
			HashMap hm = new HashMap();
			String msgContent = content.substring(31);
			String openId = content.substring(3, 31);
			System.out.println(openId);
			hm.put("content", "客服ldh说："+msgContent);			
			JSONObject msg = new JSONObject();
			msg.put("touser", openId);
			msg.put("msgtype", "text");
			msg.put("text", hm);
			String msgJson = wechatUtil.sendMsg(msg.toString());
			System.out.println(msgJson);
			return "";
		}else if(content.equals("测试模版消息")){
			HashMap first = new HashMap();
			first.put("value", "给你发个模版消息");
			first.put("color", "#CCCCCC");
			HashMap productType = new HashMap();
			productType.put("value", "类型：卡");
			productType.put("color", "#CCCCCC");
			HashMap time = new HashMap();
			time.put("value", "今天");
			time.put("color", "#CCCCCC");
			HashMap type = new HashMap();
			type.put("value", "收款");
			type.put("color", "#CCCCCC");
			HashMap number = new HashMap();
			number.put("value", "100000000000");
			number.put("color", "#CE0000");
			HashMap remark = new HashMap();
			remark.put("value", "测试成功");
			remark.put("color", "#000000");
			
			HashMap data = new HashMap();
			data.put("first", first);
			data.put("productType", productType);
			data.put("time", time);
			data.put("type", type);
			data.put("number", number);
			data.put("remark", remark);

			String openId = "oNgRquPB_u2FcoGvXA696pYPh2Nc";
			JSONObject msg = new JSONObject();
			msg.put("touser", openId);
			msg.put("template_id", "619cG4x1O9RtabLP4YlFw3Bp3l83_EF_ljfEYA4JBIQ");
			msg.put("url", "http://58.18.253.138/wechat/test.jsp");
			msg.put("topcolor", "#FF0000");
			msg.put("data", data);
			System.out.println(msg.toString());
			String msgJson = wechatUtil.sendTemplateMsg(msg.toString());
			System.out.println(msgJson);
			return "";
		}else{
			resultContent="我不明白你的意思";
		}		
		OutTextMessage outTextMessage = new OutTextMessage();
		outTextMessage.setToUserName(inMessage.getFromUserName());
		outTextMessage.setFromUserName(inMessage.getToUserName());
		outTextMessage.setCreateTime((new Date()).getTime());
		outTextMessage.setMsgType(super.OUT_MESSAGE_TYPE_TEXT);
		outTextMessage.setContent(resultContent);
		outMessage = messageToXml(outTextMessage);
		return outMessage;
	}
	
	private String messageTrade(InImageMessage inMessage){
		String outMessage = "";
		
		return outMessage;
	}
	
	private String messageTrade(InVoiceMessage inMessage){
		String outMessage = "";
		String content = inMessage.getRecognition();
		InTextMessage im = new InTextMessage();
		im.setContent(content);
		im.setFromUserName(inMessage.getFromUserName());
		im.setToUserName(inMessage.getToUserName());
		outMessage = messageTrade(im);
		return outMessage;
	}
	
	private String messageTrade(InVideoMessage inMessage){
		String outMessage = "";
		
		return outMessage;
	}
	
	private String messageTrade(InLocationMessage inMessage){
		String outMessage = "";
		
		return outMessage;
	}
	
	private String messageTrade(InLinkMessage inMessage){
		String outMessage = "";
		
		return outMessage;
	}
	
	private String messageTrade(MenuEvent inMessage){
		String outMessage = "";
		
		String eventKey = inMessage.getEventKey();
		String resultContent = "";
		if(eventKey.equals("select")){
			resultContent="卡内余额：88888888888元";
		}else if(eventKey.equals("insert")){
			resultContent="你想存款哪，存不了";
		}else if(eventKey.equals("update")){
			resultContent="你想改余额，你怎么想得那么美呢";
		}else if(eventKey.equals("delete")){
			resultContent="转账成功";
		}else if(eventKey.equals("GOOD")){
			resultContent="谢谢你的赞";
		}else{
			resultContent="整不明白，这是啥动作。";
		}
		
		OutTextMessage outTextMessage = new OutTextMessage();
		outTextMessage.setToUserName(inMessage.getFromUserName());
		outTextMessage.setFromUserName(inMessage.getToUserName());
		outTextMessage.setCreateTime((new Date()).getTime());
		outTextMessage.setMsgType(super.OUT_MESSAGE_TYPE_TEXT);
		outTextMessage.setContent(resultContent);
		outMessage = messageToXml(outTextMessage);
		
		return outMessage;
	}
	
	private String messageTrade(SubscribeEvent inMessage){
		String outMessage = "";
		System.out.println(inMessage.getFromUserName());
		System.out.println(inMessage.getToUserName());
		System.out.println(inMessage.getMsgType());
		System.out.println(inMessage.getEvent());
		
		CusInfo cusInfo = new CusInfo();
		cusInfo.setOpenid(inMessage.getFromUserName());
		//cusInfoService.delCusInfo(cusInfo);
		//cusInfoService.addCusInfo(cusInfo);
		
		return outMessage;
	}
	
	private String messageTrade(UnSubscribeEvent inMessage){
		String outMessage = "";
		CusInfo cusInfo = new CusInfo();
		cusInfo.setOpenid(inMessage.getFromUserName());
		//cusInfoService.delCusInfo(cusInfo);
		return outMessage;
	}
	
	private String messageTrade(ScanCodeEvent inMessage){
		String outMessage = "";
		
		return outMessage;
	}
	
	private String messageTrade(LocationEvent inMessage){
		String outMessage = "";
		
		return outMessage;
	}
	
	
}