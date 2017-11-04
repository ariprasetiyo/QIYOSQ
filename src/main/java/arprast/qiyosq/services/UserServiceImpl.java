package arprast.qiyosq.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arprast.qiyosq.dao.UserDao;
import arprast.qiyosq.dao.UserDaoEM;
import arprast.qiyosq.dao.UserRolesDao;
import arprast.qiyosq.dto.RolesDto;
import arprast.qiyosq.dto.UserDto;
import arprast.qiyosq.dto.UserHeaderDto;
import arprast.qiyosq.model.UserModel;
import arprast.qiyosq.model.UserRolesModel;
import arprast.qiyosq.util.LogsUtil;

@Service
public class UserServiceImpl implements UserService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserDaoEM userDaoEM;

	@Autowired
	private UserRolesDao userRolesDao;

	public void deleteUser(long idUser) {
		userDao.delete(idUser);
	}

    @Transactional
	public boolean isSuccessSaveUserAndRole(String textUserName, String textName, String textEmail, String noHp, Long[] selectRole,
			boolean isActiveUser, String textPassword, String idUser) {
		UserModel sysUser = new UserModel();
		boolean isUpdate = false;
		if (idUser != null && !idUser.isEmpty()) {
			sysUser.setId(Long.valueOf(idUser));
			isUpdate = true;
		}

		sysUser.setUsername(textUserName);
		sysUser.setPassword(textPassword);
		sysUser.setName(textName);
		sysUser.setEmail(textEmail);
		sysUser.setNoHp(noHp);
		sysUser.setIsActive(isActiveUser);
		sysUser = userDao.save(sysUser);

		if (isUpdate) {
			// int countDelete = em.createQuery("delete from SysUserRoles where
			// sysUser.id = :userId ")
			// .setParameter("userId", sysUser.getId())
			// .executeUpdate();
			userRolesDao.deleteByUserId(sysUser.getId());
		}

		for (int a = 0; a < selectRole.length; a++) {
			UserRolesModel sysUserRoles = new UserRolesModel();
			sysUserRoles.setSysUser(sysUser);
			sysUserRoles.setSysRoles(selectRole[a]);
			userRolesDao.save(sysUserRoles);
		}
		
		boolean isSuccessSave = false;
		if (sysUser.getId() != null) {
			isSuccessSave = true;
		}
		return isSuccessSave;
	}
	
	public List<UserModel> listUser(int offset, int limit, String keySearch) {
		if (keySearch == null || keySearch.isEmpty()) {
			return userDaoEM.listAllUser(offset, limit);
		} else {
			return userDaoEM.listUserSearchByUserName(offset, limit, keySearch);
		}
	}

	public UserHeaderDto listUserHeader(int offset, int limit, String keySearch) {

		List<UserModel> listSysUser = listUser(offset, limit, keySearch);

		UserHeaderDto sysUserHeader = new UserHeaderDto();
		List<UserDto> listUserDto = new ArrayList<UserDto>();
		for (UserModel sysUser : listSysUser) {

			UserDto sysUserDto = new UserDto();
			sysUserDto.setCreatedTime(sysUser.getCreatedTime());
			sysUserDto.setModifiedTime(sysUser.getModifiedTime());
			sysUserDto.setId(sysUser.getId());
			sysUserDto.setUsername(sysUser.getUsername());
			sysUserDto.setName(sysUser.getName());
			sysUserDto.setEmail(sysUser.getEmail());
			sysUserDto.setNoHp(sysUser.getNoHp());
			sysUserDto.setIsActive(sysUser.isIsActive());

			List<RolesDto> listSysRoles = userRolesDao.listRolesByNameUser(sysUser.getId());
			if (listSysRoles.size() != 0) {
				StringBuilder builderRoleName = new StringBuilder();
				long[] roloIdArrayLong = new long[listSysRoles.size()];
				int tmpPlusPlus = 0;
				for (RolesDto sysRoleDto : listSysRoles) {
					builderRoleName.append(sysRoleDto.getRoleName());
					roloIdArrayLong[tmpPlusPlus] = sysRoleDto.getId();
					LogsUtil.logDebug(logger, true, "{} -> role : {} ", sysUser.getUsername(),
							sysRoleDto.getRoleName());
					if (tmpPlusPlus == (listSysRoles.size() - 1)) {
						continue;
					}
					builderRoleName.append(", ");
					tmpPlusPlus++;
				}
				sysUserDto.setRoleName(builderRoleName.toString());
				sysUserDto.setRoleId(roloIdArrayLong);
			}

			listUserDto.add(sysUserDto);
		}
		sysUserHeader.setListSysUserDto(listUserDto);
		sysUserHeader.setTotalRecord(userDao.count());
		return sysUserHeader;
	}
}
