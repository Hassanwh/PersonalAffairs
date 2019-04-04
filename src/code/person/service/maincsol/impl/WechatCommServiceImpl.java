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
		
		System.out.println("Ϊʲô");
		// ��token��timestamp��nonce���ֵ�����
		String[] paramArr = new String[] { token, timestamp, nonce };
		Arrays.sort(paramArr);

		// �������Ľ��ƴ�ӳ�һ���ַ���
		String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);

		String ciphertext = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			// �ԽӺ���ַ�������sha1����
			byte[] digest = md.digest(content.toString().getBytes());
			ciphertext = Utility.byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		System.out.println(ciphertext);
		System.out.println(signature.toUpperCase());
		// ��sha1���ܺ���ַ�����signature���жԱ�
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
			outTextMessage.setContent("���ã���΢�ź��Ѿ����������Ƚ�����");
			
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
			if("����".equals(inMessage.get("Content"))){
				outText.setToUserName(inTextMessage.getFromUserName());
				outText.setFromUserName(inTextMessage.getToUserName());
				outText.setCreateTime(new Date().getTime());
				outText.setMsgType(super.OUT_MESSAGE_TYPE_TEXT);
				outText.setContent("��˧��");
				outMessage = messageToXml(outText);
			}
			if("ʯ����".equals(inMessage.get("Content"))){
				outText.setToUserName(inTextMessage.getFromUserName());
				outText.setFromUserName(inTextMessage.getToUserName());
				outText.setCreateTime(new Date().getTime());
				outText.setMsgType(super.OUT_MESSAGE_TYPE_TEXT);
				outText.setContent("����˧");
				outMessage = messageToXml(outText);
			}
			if("����".equals(inMessage.get("Content"))){
				outText.setToUserName(inTextMessage.getFromUserName());
				outText.setFromUserName(inTextMessage.getToUserName());
				outText.setCreateTime(new Date().getTime());
				outText.setMsgType(super.OUT_MESSAGE_TYPE_TEXT);
				outText.setContent("��˧��");
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
					article.setTitle("\"��ɨ���ά���ע�����������Ⱥ\"");
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
					outText.setContent("��ע�����ںź�����֪ʶ��Ŀ������������ʣ�[Yeah!]\n����ʹ������������������˻������еĻ�Աע�ᣬע��ɹ��󼴿�ʹ�ã�/:rose\n�������������ϵ���ǣ�[Smirk]\nΪ�˸��������Ĳ��㾡���½⣡/:8*");
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
					outText.setContent("��ӭ����������������ϵͳ��/:hug/:hug/:hug");
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
			 * ����ͼƬ��ͨ��FTP�ϴ����ļ�������
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
		if(content.equals("?")||content.equals("��")){
			resultContent="��Ҫ��ʲô��";
		}else if(content.equals("���")){
			resultContent="��ã��Һã���Һã���Һò�����ĺ�";
		}else if(content.equals("test")){
			resultContent="�������<a href=\"http://58.18.253.138/wechat/test.jsp\">������ҳ</a>!";
		}else if(content.equals("���Կͷ�")){
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
			hm.put("content", "�ͷ�ldh˵��"+msgContent);			
			JSONObject msg = new JSONObject();
			msg.put("touser", openId);
			msg.put("msgtype", "text");
			msg.put("text", hm);
			String msgJson = wechatUtil.sendMsg(msg.toString());
			System.out.println(msgJson);
			return "";
		}else if(content.equals("����ģ����Ϣ")){
			HashMap first = new HashMap();
			first.put("value", "���㷢��ģ����Ϣ");
			first.put("color", "#CCCCCC");
			HashMap productType = new HashMap();
			productType.put("value", "���ͣ���");
			productType.put("color", "#CCCCCC");
			HashMap time = new HashMap();
			time.put("value", "����");
			time.put("color", "#CCCCCC");
			HashMap type = new HashMap();
			type.put("value", "�տ�");
			type.put("color", "#CCCCCC");
			HashMap number = new HashMap();
			number.put("value", "100000000000");
			number.put("color", "#CE0000");
			HashMap remark = new HashMap();
			remark.put("value", "���Գɹ�");
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
			resultContent="�Ҳ����������˼";
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
			resultContent="������88888888888Ԫ";
		}else if(eventKey.equals("insert")){
			resultContent="�������ģ��治��";
		}else if(eventKey.equals("update")){
			resultContent="�����������ô�����ô����";
		}else if(eventKey.equals("delete")){
			resultContent="ת�˳ɹ�";
		}else if(eventKey.equals("GOOD")){
			resultContent="лл�����";
		}else{
			resultContent="�������ף�����ɶ������";
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