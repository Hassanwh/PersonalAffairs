package code.person.util;

import java.util.Date;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import code.person.dao.admin.AccessTokenDao;

//QuartzJobBean �־û�������    �Ե������û���Ψһƾ֤�Ĵ���
@Service("ouartzAccessToken")
public class QuartzAccessToken extends QuartzJobBean {
	
	public static AccessToken accessToken;
	
	private static String token;
	
	private static int createTime;
	
	private static String ticket;
	// �������û�Ψһƾ֤
	private static String appId = SysConfig.appId;
	// �������û�Ψһƾ֤��Կ
	private static String appSecret = SysConfig.appSecret;
	//�ж���ҵ�Ƿ�ִ�е����
	private boolean isRunning = true;
	
	private WechatUtil wechatUtil;
	
	
	
	public WechatUtil getWechatUtil() {
		return wechatUtil;
	}
	@Resource
	public void setWechatUtil(WechatUtil wechatUtil) {
		this.wechatUtil = wechatUtil;
	}

	public  String getAccessToken(){
		System.out.println(token+"���� ");
		if(token==null){
			initAccessToke();
			System.out.println(token+"��ʼ�����");
		}else{
			int newTime = (int)(new Date().getTime()/1000); 
			if(newTime-createTime>7000){
				initAccessToke();
			}
		}
		
		return token;
	}
	
	public  String getJsapTicket(){

		if(ticket==null){
			initAccessToke();
			System.out.println(token+"��ʼ�����");
		}		
		return ticket;
	}
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		if (!isRunning)
		{
			System.out.println("���ýӿڻ�ȡaccess_token"+new Date());
			isRunning = true;
			//����ҵ���߼�����
			accessToken = wechatUtil.getAccessToken(appId, appSecret);
			token = accessToken.getToken();
			ticket = accessToken.getTicket();
			isRunning = false;
		}
		
	}
	
	public  void initAccessToke(){
		
		System.out.println("���ýӿڻ�ȡaccess_token"+new Date());
		/*AccessToken at = new AccessToken();
		at.setId("a");//��һ����¼����ֵΪa
		at = accessTokenDao.selAccessToken(at);
		System.out.println("goodddddddddddddd"+at);
		if(at!=null){
			int dataTime = at.getCreateTime();
			int newTime = (int)(new Date().getTime()/1000); 
			if(newTime-dataTime>7000){
				//����ҵ���߼�����
				accessToken = wechatUtil.getAccessToken(appId, appSecret);
				token = accessToken.getToken();
				ticket = accessToken.getTicket();
				at.setCreateTime(newTime);
				at.setToken(token);
				at.setTicket(ticket);
				accessTokenDao.updAccessToken(at);
				System.out.println("���ݿ��д洢�Ѿ����ڣ����»�ȡ���¡�");
			}else{
				token = at.getToken();
				ticket = at.getTicket();
				System.out.println("ȡ���ݿ��д洢���ơ�");
			}
		}else{*/
			System.out.println("44444444444444444");
			//����ҵ���߼�����
			accessToken = wechatUtil.getAccessToken(appId, appSecret);
			token = accessToken.getToken();
			ticket = accessToken.getTicket();
			int newTime = (int)(new Date().getTime()/1000); 
			accessToken.setCreateTime(newTime);
			accessToken.setId("a");
			//accessTokenDao.addAccessToken(accessToken);
			System.out.println("��ȡ���Ʋ������ݿ⡣");
		}
		
		
	}
	
//}