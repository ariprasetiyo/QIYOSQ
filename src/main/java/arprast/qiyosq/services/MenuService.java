package arprast.qiyosq.services;

import java.util.List;

import org.springframework.stereotype.Repository;

import arprast.qiyosq.dao.AuthorizationDao;
import arprast.qiyosq.dto.ScreenMenuDto;

@Repository
public class MenuService {
	public static StringBuilder getScreenMenu(List<Long> sysRolesId, long parentId,
			AuthorizationDao authorizationDao, StringBuilder tmpScript) {

		// looking for parent menus with sign parentid have values null/zero
		List<ScreenMenuDto> listMenu = authorizationDao.listScreenMenu(sysRolesId, parentId);

		for (ScreenMenuDto menu : listMenu) {
			if (menu.getParentId() == 0) {
				tmpScript.append("<li class=\"treeview\">\n" + "<a href=\"" + menu.getPatternDispatcherUrl() + "\">\n"
						+ "<i class=\"fa fa-share\"></i> <span>" + menu.getMenuName() + "</span>\n"
						+ "<span class=\"pull-right-container\">\n" + "<i class=\"fa fa-angle-left pull-right\"></i>\n"
						+ "</span>\n" + "</a>\n");
			}
			if (menu.getCounts() > 0 && menu.getParentId() == 0) {
				tmpScript.append(" <ul class=\"treeview-menu\">\n");
				getScreenMenu(sysRolesId, menu.getId(), authorizationDao, tmpScript);
				tmpScript.append("</ul></li>\n");
			} else if (menu.getCounts() > 0 && menu.getParentId() != 0) {
				tmpScript.append(" <li>\n" + "<a href=\"" + menu.getPatternDispatcherUrl()
						+ "\"><i class=\"fa fa-circle-o\"></i>" + menu.getMenuName() + "\n"
						+ "<span class=\"pull-right-container\">\n" + "<i class=\"fa fa-angle-left pull-right\"></i>\n"
						+ "</span>\n" + "</a>\n" + "<ul class=\"treeview-menu\">\n");
				getScreenMenu(sysRolesId, menu.getId(), authorizationDao, tmpScript);
				tmpScript.append("</ul></li>\n");
			} else if (menu.getCounts() == 0 && menu.getParentId() != 0) {
				tmpScript.append("<li><a href=\"" + menu.getPatternDispatcherUrl()
						+ "\"><i class=\"fa fa-circle-o\"></i>" + menu.getMenuName() + "</a></li>\n");
			}
		}
		return tmpScript;
	}
}
