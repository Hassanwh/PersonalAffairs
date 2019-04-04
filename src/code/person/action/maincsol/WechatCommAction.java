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

		System.out.println(type+"类型");
		if(type.equals("GET")){
			doGet(request,response);
		}else{
			doPost(request,response);
		}
		map.addAttribute("success","欢迎您");
		return "wechat";
	}
	
	private void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String signature = request.getParameter("signature");// 微信加密签名         
		String timestamp = request.getParameter("timestamp");// 时间戳         
		String nonce = request.getParameter("nonce");// 随机数         
		String echostr = request.getParameter("echostr");//         //验证    
		System.out.println("微信加密签名 :"+signature);
		System.out.println("时间戳   :"+timestamp);
		System.out.println("随机数 :"+nonce);
		System.out.println("验证   :"+echostr);
		
		
		if (checkSignature(signature, timestamp, nonce)) {   
			System.out.println("验证成功");
			PrintWriter out = response.getWriter(); 
			
			out.print(echostr);
			out.close();       
		}else{
			System.out.println("验证失败");
		}
		 
	}
	
	 /**      * 处理微信服务器发过来的各种消息，包括：文本、图片、地理位置、音乐等等      *       *       */
	private void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String msgSignature = request.getParameter("msg_signature");// 微信加密签名 
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");// 时间戳         
		String nonce = request.getParameter("nonce");// 随机数    
		String encryptType = request.getParameter("encrypt_type");
		System.out.println(msgSignature+":::"+signature+":::"+timestamp+":::"+nonce+":::"+encryptType+"急急急");
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
			
			//加密
			if(encryptType!=null&&encryptType.equals("aes")){
				outMessage = Crypt.encryptMsg(outMessage,timestamp,nonce);
				System.out.println(outMessage+"好好");
			}
			
			out.print(outMessage);
			out.close();
		}catch (Exception e){
			e.getStackTrace();
		}	
		
	}
		
	public boolean checkSignature(String signature,String timestamp,String nonce){
		String token = "weixin";
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

	
}