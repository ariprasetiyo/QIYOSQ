package arprast.qiyosq.services;

import java.util.List;

import arprast.qiyosq.model.MenusModel;

public interface MenuService {
	List<MenusModel> listOfMenus(String keySearch, int offset, int limit);

	MenusModel saveMenu(MenusModel menus);

	String getScreenMenu(List<String> listAuthoritiesString);

}
