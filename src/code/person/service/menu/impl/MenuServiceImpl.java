package code.person.service.menu.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code.person.service.menu.MenuService;
import code.person.util.WechatUtil;

import net.sf.json.JSONObject;
@Service("menuService")
public class MenuServiceImpl extends MenuManager implements MenuService{
	

	private WechatUtil wechatUtil;
	
	
	public WechatUtil getWechatUtil() {
		return wechatUtil;
	}
	@Resource
	public void setWechatUtil(WechatUtil wechatUtil) {
		this.wechatUtil = wechatUtil;
	}

	public String getMenuMsg(){
		String msg = "";
		
		return msg;
	}
	
	public String addMenuMsg(String filePath){
		String msg = "";
		Menu menu = super.getMenuFromXml(filePath);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		System.out.println("我"+jsonMenu);
		
		int result = wechatUtil.createMenu(jsonMenu);
		
		msg = Integer.toString(result);
		
		return msg;
	}
	
	public String delMenuMsg(){
		String msg = "";
		int result = wechatUtil.deleteMenu();
		
		msg = Integer.toString(result);
		return msg;
	}

	
}