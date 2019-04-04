package code.person.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Iterator;

public class Util {

	
	public Util() {

	 }
	 
	 public static void printHexString(String hint, byte[] b) {//输出字符串
	     System.out.print(hint);
	     for (int i = 0; i < b.length; i++) {
	       String hex = Integer.toHexString(b[i] & 0xFF);
	       if (hex.length() == 1) {
	         hex = '0' + hex;
	       }
	       System.out.print(hex.toUpperCase() + " ");
	     }
	     System.out.println("");
	   }

	public static String transferHexString(byte[] b)//转换字符串
	{
		String result="";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			System.out.print(hex.toUpperCase() + " ");
			result+=hex.toUpperCase();
		}
		return result;
	}
	 
	 public static String Bytes2HexString(byte[] b) {//字节的字符串
	     String ret = "";
	     for (int i = 0; i < b.length; i++) {
	       String hex = Integer.toHexString(b[i] & 0xFF);
	       if (hex.length() == 1) {
	         hex = '0' + hex;
	       }
	       ret += hex.toUpperCase();
	     }
	     return ret;
	   }
	 
	 public static byte uniteBytes(byte src0, byte src1) {//统一字节
	  byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
	    .byteValue();
	  _b0 = (byte) (_b0 << 4);
	  byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
	    .byteValue();
	  byte ret = (byte) (_b0 ^ _b1);
	  return ret;
	 }
	 
	 public static byte[] HexString2Bytes(String src) {//字符串的字节
	  byte[] ret = new byte[8];
	  byte[] tmp = src.getBytes();
	  for (int i = 0; i < 8; i++) {
	   ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
	  }
	  return ret;
	 }
	 
	 public static boolean isCardId(String idcard){//判断是否是卡号
		 final int[] wi = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1};
	        final int[] vi = {1,0,'X',9,8,7,6,5,4,3,2};
	        int[] ai = new int[18];
	        int remaining = 0;
	        String x = null ;
	        if (idcard.length()!=0){
	                if (idcard.length() == 15) {
	                        String eightcardid = idcard.substring(0,6);
	                        eightcardid = eightcardid + "19";
	                        eightcardid = eightcardid + idcard.substring(6,15);
	                        if (eightcardid.length() == 18) {
	                                eightcardid = eightcardid.substring(0, 17);
	                        }

	                        if (eightcardid.length() == 17) {
	                                int sum = 0;
	                                for (int i = 0; i < 17; i++) {
	                                        String k = eightcardid.substring(i, i + 1);
	                                        ai[i] = Integer.parseInt(k);
	                                }

	                                for (int i = 0; i < 17; i++) {
	                                        sum = sum + wi[i] * ai[i];
	                                }
	                                remaining = sum % 11;
	                        }
	                        x = remaining == 2 ? "X" : String.valueOf(vi[remaining]);
	                        eightcardid = eightcardid + x;
	                }
	                if (idcard.length() != 18) {
	                        return false;
	                } else
	                {
	                        String verify = idcard.substring(17, 18);
	                        if (idcard.length() == 18) {
	                                idcard = idcard.substring(0, 17);
	                        }

	                        if (idcard.length() == 17) {
	                                int sum = 0;
	                                for (int i = 0; i < 17; i++) {
	                                        String k = idcard.substring(i, i + 1);
	                                        ai[i] = Integer.parseInt(k);
	                                }

	                                for (int i = 0; i < 17; i++) {
	                                        sum = sum + wi[i] * ai[i];
	                                }
	                                remaining = sum % 11;
	                        }
	                        x = remaining == 2 ? "X" : String.valueOf(vi[remaining]);
	                        if (verify.equals(x)) {
	                                return true;
	                        }
	                }
	                return false;
	        }
	        return true;
	 }
	 
	 public static boolean isZipCode(String zipcode)//判断是否是邮政编码
	  {
	          if (zipcode.length()!=0){
	                  Pattern patternZipCode = Pattern.compile("^[1-9]\\d{5}$");
	                  Matcher matcherZipCode = patternZipCode.matcher(zipcode);
	                  if (!matcherZipCode.matches()){
	                          return false;
	                  }
	          }
	                  return true;
	  }
	 
	 public static boolean isPhoneNo(String phoneNo){//判断是否是手机号
		 if(phoneNo.length()==11){
			for(int i=0;i<phoneNo.length();i++){
				if(Character.isLetter(phoneNo.charAt(i))){
					return false;
				}
			}
		}
		return true;
	 }
	 
	 /**
	  * 报文字符串拼接方法:键+值的长度的长度+值的长度+值
	  * 			   KEY+VLL+VL+VALUE
	  * @param hashMap
	  * @return String
	  */
	 public static String subMessage(HashMap hashMap){
		 String result = "";
		 StringBuffer sb = new StringBuffer();
		 Integer vl=0;		//值的长度
		 Integer vll=0;		//值的长度的长度
		 Iterator iter = hashMap.entrySet().iterator();
		 while(iter.hasNext()){
			 Map.Entry entry = (Map.Entry)iter.next();
			 Object key = entry.getKey();
			 Object value = entry.getValue();
			 vl=value.toString().length();
			 vll=vl.toString().length();
			 sb.append(key.toString());
			 sb.append(vll.toString());
			 sb.append(vl.toString());
			 sb.append(value.toString());
		 }
		 result=sb.toString();
		 return result;
	 }
}
