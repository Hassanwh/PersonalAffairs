package code.person.action.menu;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import code.person.service.menu.MenuService;


@Controller
public class MenuAction{

	MenuService menuService;

	String filePath;
	String tip;

	
	public MenuService getMenuService() {
		return menuService;
	}
	@Resource
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@RequestMapping("/addMenu.do")
	public String addMenu(ModelMap map) throws Exception {
		if (filePath == null) {
			filePath = "C:/menu.xml";
		}
		String msg = menuService.addMenuMsg(filePath);
		tip = "add result:" + msg;
		System.out.println("add result:" + msg);
		map.addAttribute("addMenu","菜单创建成功");
		return "addMenu";
	}
	@RequestMapping("/delMenu.do")
	public String delMenu(ModelMap map) throws Exception {
		String msg = menuService.delMenuMsg();
		tip = "delete result:" + msg;
		System.out.println("delete result:" + msg);
		map.addAttribute("delMenu","菜单删除成功");
		return "delMenu";
	}
}