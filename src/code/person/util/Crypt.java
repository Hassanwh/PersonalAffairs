package code.person.util;

import code.person.util.SysConfig;

import com.qq.weixin.mp.aes.WXBizMsgCrypt;



public class Crypt {
public Crypt(){
		
	}
	
	public static String encryptMsg(String srcStr,String timestamp,String nonce) throws Exception{
		String result = "";
		String token = SysConfig.taken;
		String appId = SysConfig.appId;
		String encodingAesKey = SysConfig.encodingAesKey;
		WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
		result = pc.encryptMsg(srcStr, timestamp, nonce);
		
		return result;
	}
	
	public static String decryptMsg(String signature,String timestamp,String nonce,String srcStr) throws Exception{
		String result = "";
		String token = SysConfig.taken;
		String appId = SysConfig.appId;
		String encodingAesKey = SysConfig.encodingAesKey;
		
		WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
		result = pc.decryptMsg(signature, timestamp, nonce, srcStr);
		
		return result;
	}
}
