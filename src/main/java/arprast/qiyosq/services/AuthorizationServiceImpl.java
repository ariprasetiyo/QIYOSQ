package arprast.qiyosq.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import arprast.qiyosq.dao.AuthorizationDao;
import arprast.qiyosq.dao.MenusDao;
import arprast.qiyosq.dao.RolesDao;
import arprast.qiyosq.dto.AuthorizationDto;
import arprast.qiyosq.model.AuthorizationModel;
import arprast.qiyosq.model.MenusModel;
import arprast.qiyosq.ref.ActionType;
import arprast.qiyosq.util.LogUtil;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

	private static final Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);
	private static final ObjectMapper jsonMapper = new ObjectMapper();

	private static final TypeReference<List<AuthorizationDto>> typeRef = new TypeReference<List<AuthorizationDto>>() {
	};
	private static final ObjectWriter authorizationWriter = jsonMapper.writerFor(typeRef);

	@Autowired
	AuthorizationDao authorizationDao;

	@Autowired
	RolesDao dsSysRoles;

	@Autowired
	MenusDao dsSysMenuDao;

	public int updateAuthorization(Long id, AuthorizationDto authorizationDto) {
		LogUtil.logDebugType(logger, true, ActionType.VIEW, "{}", authorizationDto.toString());
		return authorizationDao.updateAuthorization(id, authorizationDto.isInsert(), authorizationDto.isUpdate(),
				authorizationDto.isDelete(), authorizationDto.isDisabled());
	}

	public void deleteAuthorization(Long id) {
		LogUtil.logDebugType(logger, true, ActionType.DELETE, "Delete menu {}", id);
		authorizationDao.delete(id);
	}

	public void viewSysRoles(Long idRoles) {
		// model.addAttribute("selectRoleValue", idRoles);
		// List<RolesModel> listAllSysRole = (List<RolesModel>)
		// dsSysRoles.findAll();
		// model.addAttribute("listRoles", listAllSysRole);
	}

	public List<MenusModel> listMenu() {
		return (List<MenusModel>) dsSysMenuDao.findAll();
	}

	public List<AuthorizationModel> listMenuAuthorization(Long idRole) {
		return authorizationDao.getForScreenMenu(idRole);
	}

	public AuthorizationDto getAuthorizationList(int offset, int limit, String keySearch) {
		authorizationDao.findAll();
		return null;
	}

	/**
	 * @deprecated
	 */
	public String getAuthorizationJson(Long idRole) {
		try {
			return authorizationWriter.writeValueAsString(getAuthorizationList(idRole));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
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
			sysAuthorizationDto.setInsert(sysAuthorization.isIsInsert());
			sysAuthorizationDto.setUpdate(sysAuthorization.isIsUpdate());
			sysAuthorizationDto.setRead(sysAuthorization.isIsRead());
			sysAuthorizationDto.setDelete(sysAuthorization.isIsDelete());
			sysAuthorizationDto.setDisabled(sysAuthorization.isDisabled());
			sysAuthorizationDtoList.add(sysAuthorizationDto);
		}
		return sysAuthorizationDtoList;
	}

	public void viewDataMenu(Model model, Long idRole) {
		model.addAttribute("authorities", getDataMenu(idRole));
	}

	private List<AuthorizationModel> getDataMenu(long idRole) {
		List<AuthorizationModel> SysAuthorities = (List<AuthorizationModel>) authorizationDao.getForScreenMenu(idRole);
		List<AuthorizationModel> SysAuthoritiesNew = new ArrayList<>();

		StringBuilder parentSign = new StringBuilder();
		long idParent = 0;
		int levelMenu = 0;
		for (AuthorizationModel sysAuthority : SysAuthorities) {

			idParent = (sysAuthority.getParent() == null) ? 0 : sysAuthority.getParent().getId();
			levelMenu = recursifMethodCountParentId(sysAuthority.getId());

			LogUtil.logDebugType(logger, true, ActionType.VIEW, "result count parent id={}, Level menu={}, Id={}",
					sysAuthority.getId(), levelMenu, idParent);

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
		Long a = authorizationDao.getParentId(id);
		if (a == null) {
			return 0;
		}
		return recursifMethodCountParentId(a) + 1;
	}

	public AuthorizationDto saveDataMenu(Long idRole, boolean vInsert, boolean vUpdate, boolean vDelete,
			boolean vDisable, Long MenuId, Long parentMenuId) {

		AuthorizationModel dataAuthorization = new AuthorizationModel();
		/*
		 * logger.debug("-add new menu on id " + idRole + ", menuId : " + MenuId
		 * + ", parentId " + parentMenuId + " " + vInsert + " " + vUpdate + " "
		 * + vDelete + " " + vDisable);
		 */

		LogUtil.logDebugType(logger, true, ActionType.SAVE, "{}", dataAuthorization.toString());

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

		dataAuthorization = authorizationDao.save(dataAuthorization);
		AuthorizationDto dataAuthorizations = authorizationDao.getDataAuthorizationById(dataAuthorization.getId());

		return dataAuthorizations;
	}
}
