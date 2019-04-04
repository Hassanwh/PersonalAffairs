package test;

import code.person.pojo.message.SubscribeEvent;

public class Test {
	/*public static void main(String[] args) {
		String msg = a("1");
		System.out.println(msg);
	}
	public static String a(String test){
		String msg = "";
		if("1".equals(test)){
			msg = "1";
			System.out.println(msg+"1");
			return msg;
		}else if ("2".equals(test)){
			msg = "2";
			System.out.println(msg+"2");
		}else{
			msg = "3";
			System.out.println(msg+"3");
		}
		System.out.println("ÄãºÃ");
		return msg ;
	}*/
	public static void main(String[] args) {
		SubscribeEvent s = new SubscribeEvent();
		System.out.println(s.getClass()+":::"+SubscribeEvent.class);
	}
}
