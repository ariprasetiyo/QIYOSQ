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
import arprast.qiyosq.mapper.UserMapper;
import arprast.qiyosq.model.UserModel;
import arprast.qiyosq.model.UserRolesModel;
import arprast.qiyosq.ref.ActionType;
import arprast.qiyosq.ref.MessageErrorType;
import arprast.qiyosq.ref.MessageSuccessType;
import arprast.qiyosq.util.LogsUtil;
import fr.xebia.extras.selma.Selma;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final UserMapper userMapper = Selma.mapper(UserMapper.class);

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
	public JsonMessageDto updateUserAndRole(UserDto userDto, Long[] selectRole) {
		JsonMessageDto jsonMessageDto = new JsonMessageDto();
		return jsonMessageDto;
	}

	@Transactional
	public JsonMessageDto saveUserAndRole(UserDto userDto, Long[] selectRole) {

		LogsUtil.logDebug(logger, true, ActionType.SAVE, "{},{}", userDto.toString(), selectRole.toString());

		UserModel user = userMapper.asUserModel(userDto);
		JsonMessageDto jsonMessageDto = new JsonMessageDto();

		int idUser = userDao.findUserByEmail(user.getEmail());
		if (idUser > 0) {
			LogsUtil.logDebug(logger, true, MessageErrorType.DUPLICATE_EMAIL_ERROR, "Duplicate email {}",
					user.getEmail());
			jsonMessageDto.setMessageErrorType(MessageErrorType.DUPLICATE_EMAIL_ERROR);
			return jsonMessageDto;
		}

		user = userDao.save(user);

		if (user == null) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			LogsUtil.logDebug(logger, true, MessageErrorType.SAVE_USER_ERROR, "null");
			jsonMessageDto.setMessageErrorType(MessageErrorType.SAVE_USER_ERROR);
			return jsonMessageDto;
		}

		for (int a = 0; a < selectRole.length; a++) {
			UserRolesModel userRole = new UserRolesModel();
			userRole.setSysUser(user);
			userRole.setSysRoles(selectRole[a]);
			userRole = userRolesDao.save(userRole);
			if (userRole == null) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				LogsUtil.logDebug(logger, true, MessageErrorType.SAVE_ROLE_ERROR, "email : {}", user.getEmail());
				jsonMessageDto.setMessageErrorType(MessageErrorType.SAVE_ROLE_ERROR);
				return jsonMessageDto;
			}
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

		LogsUtil.logDebug(logger, true, "offset={}, limit={}, search={}", offset, limit, keySearch);
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
		LogsUtil.logDebug(logger, true, sysUserHeader.toString());
		return sysUserHeader;
	}
}