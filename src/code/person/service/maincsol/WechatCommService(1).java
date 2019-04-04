package code.person.service.maincsol;

import java.util.HashMap;

public interface WechatCommService{
	
	public boolean checkSignature(String signature,String timestamp,String nonce);
	
	public HashMap getHashMapFromStram(String result) throws Exception;
	
	public String doTrade(HashMap inMessage) throws Exception;
	
}