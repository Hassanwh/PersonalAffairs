package code.person.service.maincsol.impl;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import code.person.pojo.message.Article;
import code.person.pojo.message.OutImageMessage;
import code.person.pojo.message.OutMusicMessage;
import code.person.pojo.message.OutNewsMessage;
import code.person.pojo.message.OutServiceMessage;
import code.person.pojo.message.OutTextMessage;
import code.person.pojo.message.OutVideoMessage;
import code.person.pojo.message.OutVoiceMessage;


public class WechatTrade {
	// 请求消息类型：文本
	public static final String IN_MESSAGE_TYPE_TEXT = "text";
	// 请求消息类型：图片
	public static final String IN_MESSAGE_TYPE_IMAGE = "image";
	// 请求消息类型：语音
	public static final String IN_MESSAGE_TYPE_VOICE = "voice";
	// 请求消息类型：视频
	public static final String IN_MESSAGE_TYPE_VIDEO = "video";
	// 请求消息类型：地理位置
	public static final String IN_MESSAGE_TYPE_LOCATION = "location";
	// 请求消息类型：链接
	public static final String IN_MESSAGE_TYPE_LINK = "link";

	// 请求消息类型：事件推送
	public static final String IN_MESSAGE_TYPE_EVENT = "event";

	// 事件类型：subscribe(订阅)
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	// 事件类型：unsubscribe(取消订阅)
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	// 事件类型：scan(用户已关注时的扫描带参数二维码)
	public static final String EVENT_TYPE_SCAN = "scan";
	// 事件类型：LOCATION(上报地理位置)
	public static final String EVENT_TYPE_LOCATION = "LOCATION";
	// 事件类型：扫描推事件
	public static final String EVENT_TYPE_SCANCODE_PUSH = "scancode_push";
	// 事件类型：CLICK(自定义菜单)
	public static final String EVENT_TYPE_CLICK = "CLICK";
	
	
	// add  wubo
	// 事件类型：扫码推送事件，且弹出“消息接受中”提示框
	public static final String EVENT_TYPE_SCANCODE_WAITMSG = "scancode_waitmsg";

	
	// 响应消息类型：文本
	public static final String OUT_MESSAGE_TYPE_TEXT = "text";
	// 响应消息类型：图片
	public static final String OUT_MESSAGE_TYPE_IMAGE = "image";
	// 响应消息类型：语音
	public static final String OUT_MESSAGE_TYPE_VOICE = "voice";
	// 响应消息类型：视频
	public static final String OUT_MESSAGE_TYPE_VIDEO = "video";
	// 响应消息类型：音乐
	public static final String OUT_MESSAGE_TYPE_MUSIC = "music";
	// 响应消息类型：图文
	public static final String OUT_MESSAGE_TYPE_NEWS = "news";
	// 响应消息类型：客服消息
	public static final String TRANSFER_CUSTOMER_SERVICE = "transfer_customer_service";
	
	public static final String DOWNLOAD_IMAGE_FILE="download_image_file";

	/**
	 * 解析微信发来的请求（XML）
	 * 
	 * @param request
	 * @return Map<String, String>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static HashMap getInMessageFromStram(String strXml) throws Exception {
		// 将解析结果存储在HashMap中
		HashMap map = new HashMap();
		

//		// 读取输入流
//		SAXReader reader = new SAXReader();
//		Document document = reader.read(inputStream);
		Document document = DocumentHelper.parseText(strXml);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		for (Element e : elementList){
			System.out.println(e.getName()+":::"+e.getText());
			map.put(e.getName(), e.getText());
			/* 当事件为扫描二维码事件时，腾讯发过来的报文如下：ScanCodeInfo元素下还有子元素
			<ScanCodeInfo>
				<ScanType><![CDATA[qrcode]]></ScanType>
				<ScanResult><![CDATA[W20161021000015]]></ScanResult>
			</ScanCodeInfo>*/
			if("ScanCodeInfo".equals(e.getName())){  
				List<Element> scancodeelement = e.elements();
				for (Element e1 : scancodeelement){
					map.put(e1.getName(), e1.getText());
				}
			}
		}
		// 释放资源

		return map;
	}
	
	

	/**
	 * 扩展xstream使其支持CDATA
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("unchecked")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	/**
	 * 文本消息对象转换成xml
	 * 
	 * @param textMessage 文本消息对象
	 * @return xml
	 */
	public static String messageToXml(OutTextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	/**
	 * 图片消息对象转换成xml
	 * 
	 * @param imageMessage 图片消息对象
	 * @return xml
	 */
	public static String messageToXml(OutImageMessage imageMessage) {
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}

	/**
	 * 语音消息对象转换成xml
	 * 
	 * @param voiceMessage 语音消息对象
	 * @return xml
	 */
	public static String messageToXml(OutVoiceMessage voiceMessage) {
		xstream.alias("xml", voiceMessage.getClass());
		return xstream.toXML(voiceMessage);
	}

	/**
	 * 视频消息对象转换成xml
	 * 
	 * @param videoMessage 视频消息对象
	 * @return xml
	 */
	public static String messageToXml(OutVideoMessage videoMessage) {
		xstream.alias("xml", videoMessage.getClass());
		return xstream.toXML(videoMessage);
	}

	/**
	 * 音乐消息对象转换成xml
	 * 
	 * @param musicMessage 音乐消息对象
	 * @return xml
	 */
	public static String messageToXml(OutMusicMessage musicMessage) {
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}

	/**
	 * 图文消息对象转换成xml
	 * 
	 * @param newsMessage 图文消息对象
	 * @return xml
	 */
	public static String messageToXml(OutNewsMessage newsMessage) {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}
	
	/**
	 * 客服消息
	 * @param a
	 * @param b
	 */
	public static String messageToXml(OutServiceMessage outMessageService){
		xstream.alias("xml", outMessageService.getClass());
		return xstream.toXML(outMessageService);
	}
	
	public void evalMapToBean(HashMap a,Object b){
		Class mold = b.getClass();
		this.dgMapToBean(a, b, mold);
	}
	
	private void dgMapToBean(HashMap a,Object b,Class mold){
		Method[] methods = mold.getDeclaredMethods();
		for(Method method:methods){
			String methodName = method.getName();
			String actMetName = "";
			String key = "";
			String type = "";
			if(methodName.length()>3&&methodName.substring(0, 3).equals("set")){
				if(method.getParameterTypes().length>0){
					type = method.getParameterTypes()[0].getName();
				}
				key = methodName.substring(3, methodName.length());
			}else{
				continue;
			}		
			try {
				if(!a.containsKey(key))
					continue;
				
				Object obj = a.get(key);					
				if(obj!=null){
					Object objSet = changeType(type,(String)obj);	
					method.invoke(b, objSet);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				continue;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				continue;
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				continue;
			}
		}
		if(mold.getGenericSuperclass()!=null){
			Class superAction = mold.getSuperclass();
			this.dgMapToBean(a, b, superAction);
		}
	}	
	
	private Object changeType(String type,String value){
		Object object = null;
		if(type.equals("java.lang.String")){
			object = value;
		}else if(type.equals("double")&&!value.equals("")){			
			object = Double.valueOf(value);
		}else if(type.equals("java.lang.Double")&&!value.equals("")){
			object = Double.valueOf(value);
		}else if(type.equals("int")&&!value.equals("")){
			object = Integer.valueOf(value);
		}else if(type.equals("long")&&!value.equals("")){
			object = Long.valueOf(value);
		}else if(type.equals("java.lang.Integer")&&!value.equals("")){
			object = Integer.valueOf(value);
		}else if(type.equals("boolean")&&!value.equals("")){
			object = Boolean.valueOf(value);
		}else if(type.equals("[Ljava.lang.String;")){
			String[] valueArray = value.split("\\|");
			object = valueArray;
		}else if(type.equals("java.util.Date")){
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				if(value.length()==8)
					object =  sdf.parse(value);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}		
		return object;		
	}
	
}
