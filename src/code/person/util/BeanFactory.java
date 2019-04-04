package code.person.util;

import java.io.File;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import code.person.pojo.system.PersonInf;

//bean工厂(方法只执行一次  类不可被继承 获取session的bean)
public final class BeanFactory {	
	public static ApplicationContext context = null;
	public static boolean isWeb=false;
	static {		
//		System.out.println("初始化");
//		File file = new File("D:/workspace/yzcredit/WebRoot/WEB-INF/applicationContext.xml");
//		FileSystemResource fsr = new FileSystemResource(file);
//		factory = new XmlBeanFactory(fsr);	
//		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml,actionContext.xml");
//		ApplicationContext context = new FileSystemXmlApplicationContext(new String[]{"/src/applicationContext.xml","/src/actionContext.xml"});
//		context1 = new FileSystemXmlApplicationContext(new String[]{"classpath:sessionContext.xml"});
//		if(SpringInit.getApplicationContext()!=null){
//			context = SpringInit.getApplicationContext();
//			session = (Session)context.getBean("webSession");
//			isWeb=true;
//		}else{
//			context = new FileSystemXmlApplicationContext(new String[]{"classpath:applicationContext.xml","classpath:serviceContext.xml","classpath:actionContext.xml"});
//			session = (Session)context.getBean("session");
//		}
//		System.out.println("Context是否是WEB："+isWeb);
//		System.out.println("结束");
//		context = new FileSystemXmlApplicationContext(new String[]{"applicationContext.xml"});
//		context = new FileSystemXmlApplicationContext(new String[]{"/src/code/yz/yzcode/applicationContext.xml"});
	}	
	
	public static Object getBean(String beanName){	
		return context.getBean(beanName);	
	}	
	
	public static Session getSession(){
		if(isWeb)
			return (Session)context.getBean("webSession");
		else
			return (Session)context.getBean("session");
	}
	
	public static PersonInf person(){
		return (PersonInf)getSession().get("personInf");
	}
	
	public static String userId(){
		return person().getUserId();
	}
	

}
