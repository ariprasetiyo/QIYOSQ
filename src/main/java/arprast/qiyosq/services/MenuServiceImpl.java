package arprast.qiyosq.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import arprast.qiyosq.dao.AuthorizationDao;
import arprast.qiyosq.dao.MenusDao;
import arprast.qiyosq.dao.MenusDaoEM;
import arprast.qiyosq.dto.ScreenMenuDto;
import arprast.qiyosq.model.MenusModel;
import arprast.qiyosq.model.RolesModel;

@Service
public class MenuServiceImpl implements MenuService {

	private static final String HTML_0 = "<li class=\"treeview\">\n<a href=\"";
	private static final String HTML_1 = "\">\n<i class=\"fa fa-share\"></i> <span>";
	private static final String HTML_2 = "</span>\n<span class=\"pull-right-container\">\n<i class=\"fa fa-angle-left pull-right\"></i>\n</span>\n</a>\n";
	private static final String HTML_3 = "<ul class=\"treeview-menu\">\n";
	private static final String HTML_4 = "</ul></li>\n";
	private static final String HTML_5 = "<li>\n<a href=\"";
	private static final String HTML_6 = "\"><i class=\"fa fa-circle-o\"></i>";
	private static final String HTML_7 = "\n<span class=\"pull-right-container\">\n<i class=\"fa fa-angle-left pull-right\"></i>\n</span>\n</a>\n<ul class=\"treeview-menu\">\n";
	private static final String HTML_8 = "</a></li>\n";
	private static final StringBuilder tmpScript = new StringBuilder();

	@Autowired
	private RolesService rolesService;

	@Autowired
	private MenusDao menusDao;

	@Autowired
	private MenusDaoEM menusDaoEM;

	@Autowired
	private AuthorizationDao authorizationDao;

	public MenusModel saveMenu(MenusModel menus) {
		return menusDao.save(menus);
	}

	public String getScreenMenu(List<String> listAuthoritiesString) {
		// Looking for id from sys_roles
		List<RolesModel> listId = rolesService.getListIdByName(listAuthoritiesString);
		// convert SysRoles.getId to List Long
		List<Long> listLongId = getListAuthorities(listId);

		String resultMenus = getScreenMenu(listLongId, 0, tmpScript).toString();
		tmpScript.delete(0, tmpScript.length());
		return resultMenus;
	}

	private StringBuilder getScreenMenu(List<Long> sysRolesId, long parentId, StringBuilder tmpScript) {

		// looking for parent menus with sign parentid have values null/zero
		List<ScreenMenuDto> listMenu = authorizationDao.listScreenMenu(sysRolesId, parentId);

		for (ScreenMenuDto menu : listMenu) {
			if (menu.getParentId() == 0) {
				tmpScript.append(HTML_0);
				tmpScript.append(menu.getPatternDispatcherUrl());
				tmpScript.append(HTML_1);
				tmpScript.append(menu.getMenuName());
				tmpScript.append(HTML_2);

				// tmpScript.append(menu.getPatternDispatcherUrl() + "\">\n" +
				// "<i class=\"fa fa-share\"></i> <span>"
				// + menu.getMenuName() + "</span>\n" + "<span
				// class=\"pull-right-container\">\n"
				// + "<i class=\"fa fa-angle-left pull-right\"></i>\n" +
				// "</span>\n" + "</a>\n");
			}

			if (menu.getCounts() > 0 && menu.getParentId() == 0) {
				tmpScript.append(HTML_3);
				getScreenMenu(sysRolesId, menu.getId(), tmpScript);
				tmpScript.append(HTML_4);
				// tmpScript.append(" <ul class=\"treeview-menu\">\n");
				// getScreenMenu(sysRolesId, menu.getId(), tmpScript);
				// tmpScript.append("</ul></li>\n");
			} else if (menu.getCounts() > 0 && menu.getParentId() != 0) {
				tmpScript.append(HTML_5);
				tmpScript.append(menu.getPatternDispatcherUrl());
				tmpScript.append(HTML_6);
				tmpScript.append(menu.getMenuName());
				tmpScript.append(HTML_7);

				// tmpScript.append(" <li>\n" + "<a href=\"" +
				// menu.getPatternDispatcherUrl()
				// + "\"><i class=\"fa fa-circle-o\"></i>" + menu.getMenuName()
				// + "\n"
				// + "<span class=\"pull-right-container\">\n" + "<i class=\"fa
				// fa-angle-left pull-right\"></i>\n"
				// + "</span>\n" + "</a>\n" + "<ul class=\"treeview-menu\">\n");
				getScreenMenu(sysRolesId, menu.getId(), tmpScript);
				tmpScript.append(HTML_4);
			} else if (menu.getCounts() == 0 && menu.getParentId() != 0) {
				tmpScript.append(HTML_5);
				tmpScript.append(menu.getPatternDispatcherUrl());
				tmpScript.append(HTML_6);
				tmpScript.append(menu.getMenuName());
				tmpScript.append(HTML_8);
				// tmpScript.append("<li><a href=\"" +
				// menu.getPatternDispatcherUrl()
				// + "\"><i class=\"fa fa-circle-o\"></i>" + menu.getMenuName()
				// + "</a></li>\n");
			}
		}
		return tmpScript;
	}

	public List<MenusModel> listOfMenus(String keySearch, int offset, int limit) {
		return menusDaoEM.listOfMenus(keySearch, offset, limit);
	}

	private List<Long> getListAuthorities(List<RolesModel> listRoles) {
		List<Long> listId = new ArrayList<>();
		for (RolesModel sysRole : listRoles) {
			listId.add(sysRole.getId());
		}
		return listId;
	}

}
