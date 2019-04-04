package code.person.util;

import java.util.Date;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import code.person.dao.admin.AccessTokenDao;

//QuartzJobBean 持久化的作用    对第三方用户的唯一凭证的处理
@Service("ouartzAccessToken")
public class QuartzAccessToken extends QuartzJobBean {
	
	public static AccessToken accessToken;
	
	private static String token;
	
	private static int createTime;
	
	private static String ticket;
	// 第三方用户唯一凭证
	private static String appId = SysConfig.appId;
	// 第三方用户唯一凭证密钥
	private static String appSecret = SysConfig.appSecret;
	//判断作业是否执行的旗标
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
		System.out.println(token+"世界 ");
		if(token==null){
			initAccessToke();
			System.out.println(token+"初始化完成");
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
			System.out.println(token+"初始化完成");
		}		
		return ticket;
	}
	
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		if (!isRunning)
		{
			System.out.println("调用接口获取access_token"+new Date());
			isRunning = true;
			//调用业务逻辑方法
			accessToken = wechatUtil.getAccessToken(appId, appSecret);
			token = accessToken.getToken();
			ticket = accessToken.getTicket();
			isRunning = false;
		}
		
	}
	
	public  void initAccessToke(){
		
		System.out.println("调用接口获取access_token"+new Date());
		/*AccessToken at = new AccessToken();
		at.setId("a");//就一条记录主键值为a
		at = accessTokenDao.selAccessToken(at);
		System.out.println("goodddddddddddddd"+at);
		if(at!=null){
			int dataTime = at.getCreateTime();
			int newTime = (int)(new Date().getTime()/1000); 
			if(newTime-dataTime>7000){
				//调用业务逻辑方法
				accessToken = wechatUtil.getAccessToken(appId, appSecret);
				token = accessToken.getToken();
				ticket = accessToken.getTicket();
				at.setCreateTime(newTime);
				at.setToken(token);
				at.setTicket(ticket);
				accessTokenDao.updAccessToken(at);
				System.out.println("数据库中存储已经过期，重新获取更新。");
			}else{
				token = at.getToken();
				ticket = at.getTicket();
				System.out.println("取数据库中存储令牌。");
			}
		}else{*/
			System.out.println("44444444444444444");
			//调用业务逻辑方法
			accessToken = wechatUtil.getAccessToken(appId, appSecret);
			token = accessToken.getToken();
			ticket = accessToken.getTicket();
			int newTime = (int)(new Date().getTime()/1000); 
			accessToken.setCreateTime(newTime);
			accessToken.setId("a");
			//accessTokenDao.addAccessToken(accessToken);
			System.out.println("获取令牌插入数据库。");
		}
		
		
	}
	
//}