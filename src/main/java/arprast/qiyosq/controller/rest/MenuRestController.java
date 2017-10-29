/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.controller.rest;

import arprast.qiyosq.model.MenusModel;
import arprast.qiyosq.services.MenuService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ari-prasetiyo
 */
@RestController
@RequestMapping("/admin/v1/api/screen_menu")
public class MenuRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private MenuService menuService;

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<List<MenusModel>> listMenu(
            @RequestParam("offset") int offset,
            @RequestParam("limit") int limit,
            @RequestParam(value = "search", required = false) String keySearch
	) {
		return new ResponseEntity(menuService.listOfMenus(keySearch, offset, limit), HttpStatus.OK);
	}

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Map<String, Object> saveMenu(
            @RequestParam("textMenuName") String textNameMenu,
            @RequestParam("textUrl") String textUrl,
            @RequestParam("checkBoxIsActive") Boolean checkBoxIsActive,
            @RequestParam(value = "idMenu", required = false) Long idMenu
    ) {
        logger.debug("textNameMenu : " + textNameMenu + ", textUrl : " + textUrl + ", checkBoxIsActive : " + checkBoxIsActive + ", idMenu : " + idMenu);
        MenusModel sysMenus = new MenusModel();
        sysMenus.setMenusName(textNameMenu);
        sysMenus.setUrl(textUrl);
        sysMenus.setDisabled(checkBoxIsActive);
        if (idMenu == null) {
            sysMenus.setId(idMenu);
        }
        sysMenus.setId(idMenu);
        sysMenus = menuService.saveMenu(sysMenus);

        boolean isSuccessSave = false;
        if (sysMenus.getModifiedTime() != null) {
            isSuccessSave = true;
        }

        Map<String, Object> mapJson = new HashMap<String, Object>();
        mapJson.put("isSuccessSave", isSuccessSave);
        return mapJson;
    }
}
