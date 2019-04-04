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
	// ������Ϣ���ͣ��ı�
	public static final String IN_MESSAGE_TYPE_TEXT = "text";
	// ������Ϣ���ͣ�ͼƬ
	public static final String IN_MESSAGE_TYPE_IMAGE = "image";
	// ������Ϣ���ͣ�����
	public static final String IN_MESSAGE_TYPE_VOICE = "voice";
	// ������Ϣ���ͣ���Ƶ
	public static final String IN_MESSAGE_TYPE_VIDEO = "video";
	// ������Ϣ���ͣ�����λ��
	public static final String IN_MESSAGE_TYPE_LOCATION = "location";
	// ������Ϣ���ͣ�����
	public static final String IN_MESSAGE_TYPE_LINK = "link";

	// ������Ϣ���ͣ��¼�����
	public static final String IN_MESSAGE_TYPE_EVENT = "event";

	// �¼����ͣ�subscribe(����)
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	// �¼����ͣ�unsubscribe(ȡ������)
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	// �¼����ͣ�scan(�û��ѹ�עʱ��ɨ���������ά��)
	public static final String EVENT_TYPE_SCAN = "scan";
	// �¼����ͣ�LOCATION(�ϱ�����λ��)
	public static final String EVENT_TYPE_LOCATION = "LOCATION";
	// �¼����ͣ�ɨ�����¼�
	public static final String EVENT_TYPE_SCANCODE_PUSH = "scancode_push";
	// �¼����ͣ�CLICK(�Զ���˵�)
	public static final String EVENT_TYPE_CLICK = "CLICK";
	
	
	// add  wubo
	// �¼����ͣ�ɨ�������¼����ҵ�������Ϣ�����С���ʾ��
	public static final String EVENT_TYPE_SCANCODE_WAITMSG = "scancode_waitmsg";

	
	// ��Ӧ��Ϣ���ͣ��ı�
	public static final String OUT_MESSAGE_TYPE_TEXT = "text";
	// ��Ӧ��Ϣ���ͣ�ͼƬ
	public static final String OUT_MESSAGE_TYPE_IMAGE = "image";
	// ��Ӧ��Ϣ���ͣ�����
	public static final String OUT_MESSAGE_TYPE_VOICE = "voice";
	// ��Ӧ��Ϣ���ͣ���Ƶ
	public static final String OUT_MESSAGE_TYPE_VIDEO = "video";
	// ��Ӧ��Ϣ���ͣ�����
	public static final String OUT_MESSAGE_TYPE_MUSIC = "music";
	// ��Ӧ��Ϣ���ͣ�ͼ��
	public static final String OUT_MESSAGE_TYPE_NEWS = "news";
	// ��Ӧ��Ϣ���ͣ��ͷ���Ϣ
	public static final String TRANSFER_CUSTOMER_SERVICE = "transfer_customer_service";
	
	public static final String DOWNLOAD_IMAGE_FILE="download_image_file";

	/**
	 * ����΢�ŷ���������XML��
	 * 
	 * @param request
	 * @return Map<String, String>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static HashMap getInMessageFromStram(String strXml) throws Exception {
		// ����������洢��HashMap��
		HashMap map = new HashMap();
		

//		// ��ȡ������
//		SAXReader reader = new SAXReader();
//		Document document = reader.read(inputStream);
		Document document = DocumentHelper.parseText(strXml);
		// �õ�xml��Ԫ��
		Element root = document.getRootElement();
		// �õ���Ԫ�ص������ӽڵ�
		List<Element> elementList = root.elements();

		// ���������ӽڵ�
		for (Element e : elementList){
			System.out.println(e.getName()+":::"+e.getText());
			map.put(e.getName(), e.getText());
			/* ���¼�Ϊɨ���ά���¼�ʱ����Ѷ�������ı������£�ScanCodeInfoԪ���»�����Ԫ��
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
		// �ͷ���Դ

		return map;
	}
	
	

	/**
	 * ��չxstreamʹ��֧��CDATA
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// ������xml�ڵ��ת��������CDATA���
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
	 * �ı���Ϣ����ת����xml
	 * 
	 * @param textMessage �ı���Ϣ����
	 * @return xml
	 */
	public static String messageToXml(OutTextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}

	/**
	 * ͼƬ��Ϣ����ת����xml
	 * 
	 * @param imageMessage ͼƬ��Ϣ����
	 * @return xml
	 */
	public static String messageToXml(OutImageMessage imageMessage) {
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}

	/**
	 * ������Ϣ����ת����xml
	 * 
	 * @param voiceMessage ������Ϣ����
	 * @return xml
	 */
	public static String messageToXml(OutVoiceMessage voiceMessage) {
		xstream.alias("xml", voiceMessage.getClass());
		return xstream.toXML(voiceMessage);
	}

	/**
	 * ��Ƶ��Ϣ����ת����xml
	 * 
	 * @param videoMessage ��Ƶ��Ϣ����
	 * @return xml
	 */
	public static String messageToXml(OutVideoMessage videoMessage) {
		xstream.alias("xml", videoMessage.getClass());
		return xstream.toXML(videoMessage);
	}

	/**
	 * ������Ϣ����ת����xml
	 * 
	 * @param musicMessage ������Ϣ����
	 * @return xml
	 */
	public static String messageToXml(OutMusicMessage musicMessage) {
		xstream.alias("xml", musicMessage.getClass());
		return xstream.toXML(musicMessage);
	}

	/**
	 * ͼ����Ϣ����ת����xml
	 * 
	 * @param newsMessage ͼ����Ϣ����
	 * @return xml
	 */
	public static String messageToXml(OutNewsMessage newsMessage) {
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessage);
	}
	
	/**
	 * �ͷ���Ϣ
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
