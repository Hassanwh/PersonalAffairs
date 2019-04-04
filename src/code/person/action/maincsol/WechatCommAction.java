package code.person.action.maincsol;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import code.person.service.maincsol.WechatCommService;
import code.person.util.Crypt;
import code.person.util.Utility;





@Controller
public class WechatCommAction
{
	private WechatCommService wechatCommService;
	
	
	public WechatCommService getWechatCommService() {
		return wechatCommService;
	}
	@Resource
	public void setWechatCommService(WechatCommService wechatCommService) {
		this.wechatCommService = wechatCommService;
	}

	@RequestMapping("/wechat.do")
	public String wechat(HttpServletRequest request,
			HttpServletResponse response,ModelMap map) throws Exception {
		HttpSession session = request.getSession();
		session.setAttribute("name", "ldh");
		String type = request.getMethod();

		System.out.println(type+"����");
		if(type.equals("GET")){
			doGet(request,response);
		}else{
			doPost(request,response);
		}
		map.addAttribute("success","��ӭ��");
		return "wechat";
	}
	
	private void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String signature = request.getParameter("signature");// ΢�ż���ǩ��         
		String timestamp = request.getParameter("timestamp");// ʱ���         
		String nonce = request.getParameter("nonce");// �����         
		String echostr = request.getParameter("echostr");//         //��֤    
		System.out.println("΢�ż���ǩ�� :"+signature);
		System.out.println("ʱ���   :"+timestamp);
		System.out.println("����� :"+nonce);
		System.out.println("��֤   :"+echostr);
		
		
		if (checkSignature(signature, timestamp, nonce)) {   
			System.out.println("��֤�ɹ�");
			PrintWriter out = response.getWriter(); 
			
			out.print(echostr);
			out.close();       
		}else{
			System.out.println("��֤ʧ��");
		}
		 
	}
	
	 /**      * ����΢�ŷ������������ĸ�����Ϣ���������ı���ͼƬ������λ�á����ֵȵ�      *       *       */
	private void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String msgSignature = request.getParameter("msg_signature");// ΢�ż���ǩ�� 
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");// ʱ���         
		String nonce = request.getParameter("nonce");// �����    
		String encryptType = request.getParameter("encrypt_type");
		System.out.println(msgSignature+":::"+signature+":::"+timestamp+":::"+nonce+":::"+encryptType+"������");
		InputStream is=request.getInputStream();
		String ldh="";
		try{
			String str = "";
			BufferedReader readerStr = new BufferedReader(new InputStreamReader(is,"utf-8"));
			StringBuffer sb = new StringBuffer();
			while((str=readerStr.readLine())!=null){
				sb.append(str).append("\n");
			}
			String srcStr = sb.toString();
			if(encryptType!=null&&encryptType.equals("aes")){
				srcStr =  Crypt.decryptMsg(msgSignature,timestamp,nonce,srcStr); 
			}
			HashMap inMessage = wechatCommService.getHashMapFromStram(srcStr);

			is.close();
			is = null;

			PrintWriter out = response.getWriter(); 

			//out.print("");
			//out.close();
			
			String outMessage = wechatCommService.doTrade(inMessage);
			
			//����
			if(encryptType!=null&&encryptType.equals("aes")){
				outMessage = Crypt.encryptMsg(outMessage,timestamp,nonce);
				System.out.println(outMessage+"�ú�");
			}
			
			out.print(outMessage);
			out.close();
		}catch (Exception e){
			e.getStackTrace();
		}	
		
	}
		
	public boolean checkSignature(String signature,String timestamp,String nonce){
		String token = "weixin";
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

	
}