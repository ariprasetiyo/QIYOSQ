package arprast.qiyosq.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import arprast.qiyosq.dao.UserDao;
import arprast.qiyosq.dao.UserDaoEM;
import arprast.qiyosq.dao.UserRolesDao;
import arprast.qiyosq.dto.JsonMessageDto;
import arprast.qiyosq.dto.RolesDto;
import arprast.qiyosq.dto.UserDto;
import arprast.qiyosq.dto.UserHeaderDto;
import arprast.qiyosq.model.UserModel;
import arprast.qiyosq.model.UserRolesModel;
import arprast.qiyosq.ref.MessageErrorType;
import arprast.qiyosq.ref.MessageSuccessType;
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
	public JsonMessageDto updateUserAndRole(UserModel user, Long[] selectRole) {
		JsonMessageDto jsonMessageDto = new JsonMessageDto();
		return jsonMessageDto;
	}

	@Transactional
	public JsonMessageDto saveUserAndRole(UserModel user, Long[] selectRole) {

		JsonMessageDto jsonMessageDto = new JsonMessageDto();

		int idUser = userDao.findUserByEmail(user.getEmail());
		if (idUser > 0) {
			LogsUtil.logDebug(logger, true, "Duplicate email {}", user.getEmail());
			jsonMessageDto.setMessageErrorType(MessageErrorType.DUPLICATE_EMAIL_ERROR);
			return jsonMessageDto;
		}

		user = userDao.save(user);

		for (int a = 0; a < selectRole.length; a++) {
			UserRolesModel userRole = new UserRolesModel();
			userRole.setSysUser(user);
			userRole.setSysRoles(selectRole[a]);
			userRole = userRolesDao.save(userRole);
			if (userRole != null && userRole.getId() != null) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				LogsUtil.logDebug(logger, true, MessageErrorType.SAVE_ROLE_ERROR, userRole.toString());
				jsonMessageDto.setMessageErrorType(MessageErrorType.SAVE_ROLE_ERROR);
				return jsonMessageDto;
			}
		}

		if (user != null && user.getId() != null) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			LogsUtil.logDebug(logger, true, MessageErrorType.SAVE_USER_ERROR, user.toString());
			jsonMessageDto.setMessageErrorType(MessageErrorType.SAVE_USER_ERROR);
			return jsonMessageDto;
		}

		jsonMessageDto.setMessageSuccessType(MessageSuccessType.SAVE_SUCCEED);
		LogsUtil.logDebug(logger, true, MessageSuccessType.SAVE_SUCCEED, user.toString());
		return jsonMessageDto;
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
