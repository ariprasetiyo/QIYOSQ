package arprast.qiyosq.services;

import java.util.List;

import arprast.qiyosq.model.MenusModel;

public interface MenuService {
	public List<MenusModel> listOfMenus(String keySearch, int offset, int limit);

	public StringBuilder getScreenMenu(List<Long> sysRolesId, long parentId,
			StringBuilder tmpScript);
	
	MenusModel saveMenu(MenusModel menus);
	
}
