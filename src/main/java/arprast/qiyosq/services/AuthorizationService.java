package arprast.qiyosq.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import arprast.qiyosq.dao.AuthorizationDao;
import arprast.qiyosq.dao.MenusDao;
import arprast.qiyosq.dao.RolesDao;
import arprast.qiyosq.dto.AuthorizationDto;
import arprast.qiyosq.model.AuthorizationModel;
import arprast.qiyosq.model.MenusModel;
import arprast.qiyosq.model.RolesModel;

@Repository
public class AuthorizationService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AuthorizationDao dsSysAuthorization;

    @Autowired
    AuthorizationDao dsAuthorization;

    @Autowired
    RolesDao dsSysRoles;

    @Autowired
    MenusDao dsSysMenuDao;
    
    public void viewSysRoles(Model model, Long idRoles) {
        model.addAttribute("selectRoleValue", idRoles);
        List<RolesModel> listAllSysRole = (List<RolesModel>) dsSysRoles.findAll();
        model.addAttribute("listRoles", listAllSysRole);
    }

    public void existingMenuInSysMenu(Model model) {
        List<MenusModel> listAllMenu = (List<MenusModel>) dsSysMenuDao.findAll();
        model.addAttribute("listAllMenu", listAllMenu);
    }

    public void existingMenuInAuthorization(Model model, Long idRole) {
        List<AuthorizationModel> listParentMenuAuthorization = dsAuthorization.getForScreenMenu(idRole);
        model.addAttribute("listAllMenuAuthorization", listParentMenuAuthorization);
    }
	
	public AuthorizationDto getAuthorizationList(int offset, int limit, String keySearch) {
		dsSysAuthorization.findAll();
		return null;
	}
	
	public List<AuthorizationDto> getAuthorizationList(Long idRole) {
		if (idRole == null) {
			return null;
		}
		List<AuthorizationModel> sysAuthorizationList = getDataMenu(idRole);
		if (sysAuthorizationList == null) {
			return null;
		}
		List<AuthorizationDto> sysAuthorizationDtoList = new ArrayList<AuthorizationDto>();
		for (AuthorizationModel sysAuthorization : sysAuthorizationList) {
			AuthorizationDto sysAuthorizationDto = new AuthorizationDto();
			sysAuthorizationDto.setId(sysAuthorization.getId());
			sysAuthorizationDto.setMenuName(sysAuthorization.getSysMenu().getMenusName());
			sysAuthorizationDto.setCreatedTime(sysAuthorization.getCreatedTime());
			sysAuthorizationDto.setModifiedTime(sysAuthorization.getModifiedTime());
			sysAuthorizationDto.setIsInsert(sysAuthorization.isIsInsert());
			sysAuthorizationDto.setIsUpdate(sysAuthorization.isIsUpdate());
			sysAuthorizationDto.setIsRead(sysAuthorization.isIsRead());
			sysAuthorizationDto.setIsDelete(sysAuthorization.isIsDelete());
			sysAuthorizationDto.setDisabled(sysAuthorization.isDisabled());
			sysAuthorizationDtoList.add(sysAuthorizationDto);
		}
		return sysAuthorizationDtoList;
	}

	public void viewDataMenu(Model model, Long idRole) {
		model.addAttribute("authorities", getDataMenu(idRole));
	}

	private List<AuthorizationModel> getDataMenu(long idRole) {
		List<AuthorizationModel> SysAuthorities = (List<AuthorizationModel>) dsAuthorization.getForScreenMenu(idRole);
		List<AuthorizationModel> SysAuthoritiesNew = new ArrayList<>();

		StringBuilder parentSign = new StringBuilder();
		long idParent = 0;
		int levelMenu = 0;
		for (AuthorizationModel sysAuthority : SysAuthorities) {

			idParent = (sysAuthority.getParent() == null) ? 0 : sysAuthority.getParent().getId();
			levelMenu = recursifMethodCountParentId(sysAuthority.getId());
			logger.debug("result count parent id : " + sysAuthority.getId() + ". Level menu :" + levelMenu + ". Id : "
					+ idParent);

			parentSign.delete(0, parentSign.length());
			for (int a = 0; a < levelMenu; a++) {
				parentSign.append("--- ");
			}

			MenusModel sysMenu = new MenusModel();
			sysMenu.setMenusName(parentSign.toString() + sysAuthority.getSysMenu().getMenusName());
			sysAuthority.setSysMenu(sysMenu);
			SysAuthoritiesNew.add(sysAuthority);
		}
		return SysAuthoritiesNew;
	}
	
	private int recursifMethodCountParentId(long id) {
        Long a = dsAuthorization.getParentId(id);
        if (a == null) {
            return 0;
        }
        return recursifMethodCountParentId(a) + 1;
    }

    public AuthorizationDto saveDataMenu(Long idRole, boolean vInsert,
            boolean vUpdate, boolean vDelete,
            boolean vDisable, Long MenuId,
            Long parentMenuId) {

        AuthorizationModel dataAuthorization = new AuthorizationModel();
        logger.debug("-add new menu on id " + idRole
                + ", menuId : " + MenuId
                + ", parentId " + parentMenuId
                + " " + vInsert + " " + vUpdate + " " + vDelete + " " + vDisable);
        dataAuthorization.setSysMenu(MenuId);

        if (parentMenuId == null) {
            dataAuthorization.setParent(null);
        } else {
            dataAuthorization.setParent(parentMenuId);
        }

        dataAuthorization.setSysRoles(idRole);
        dataAuthorization.setIsDelete(vDelete);
        dataAuthorization.setIsInsert(vInsert);
        dataAuthorization.setIsUpdate(vUpdate);
        dataAuthorization.setDisabled(vDisable);
        dataAuthorization.setIsRead(true);

        dataAuthorization = dsSysAuthorization.save(dataAuthorization);
        logger.debug("new id menu after add : " + dataAuthorization.getId() + "--");

        AuthorizationDto dataAuthorizations = dsSysAuthorization.getDataAuthorizationById(dataAuthorization.getId());
        logger.debug("new id menu after get :" + dataAuthorizations.getId() + "--");
        logger.debug("menu name after add : " + dataAuthorizations.getMenuName());

        return dataAuthorizations;
    }
}
