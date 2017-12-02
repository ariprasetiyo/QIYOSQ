package arprast.qiyosq.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arprast.qiyosq.beans.UserMapper;
import arprast.qiyosq.dao.UserDao;
import arprast.qiyosq.dao.UserDaoImpl;
import arprast.qiyosq.dto.JsonMessageDto;
import arprast.qiyosq.dto.UserDto;
import arprast.qiyosq.dto.UserHeaderDto;
import arprast.qiyosq.model.UserModel;
import arprast.qiyosq.ref.ActionType;
import arprast.qiyosq.ref.StatusType;
import arprast.qiyosq.util.LogUtil;
import fr.xebia.extras.selma.Selma;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final UserMapper userMapper = Selma.mapper(UserMapper.class);

	@Autowired
	private UserDaoImpl userDaoImpl;

	@Autowired
	private UserDao userDao;

	public JsonMessageDto updateUserAndRole(UserDto userDto) {
		return saveEditUserAndRole(userDto);
	}

	public JsonMessageDto saveUserAndRole(UserDto userDto) {
		return saveEditUserAndRole(userDto);
	}

	private JsonMessageDto saveEditUserAndRole(UserDto userDto) {

		boolean isEdit = (userDto.getId() != null ? true : false);

		ActionType actionType = ActionType.SAVE;
		StatusType messageErrorType = StatusType.SAVE_ERROR;
		StatusType messageSuccessType = StatusType.SAVE_SUCCEED;
		if (isEdit) {
			actionType = ActionType.UPADATE;
			messageErrorType = StatusType.UPDATE_ERROR;
			messageSuccessType = StatusType.UPDATE_SUCCEED;
		}

		LogUtil.logDebugType(logger, true, actionType, "{}", userDto.toString());
		UserModel user = userMapper.asUserModel(userDto);

		user = userDaoImpl.saveEditUserRole(user, isEdit);

		JsonMessageDto jsonMessageDto = new JsonMessageDto();
		if (user == null) {
			LogUtil.logDebugType(logger, true, messageErrorType, "null");
			jsonMessageDto.setStatusType(messageErrorType);
			return jsonMessageDto;
		}

		jsonMessageDto.setStatusType(messageSuccessType);
		LogUtil.logDebugType(logger, true, messageSuccessType, user.toString());

		return jsonMessageDto;
	}

	public UserHeaderDto listUserHeader(int offset, int limit, String keySearch) {

		LogUtil.logDebug(logger, true, "offset={}, limit={}, search={}", offset, limit, keySearch);
		List<UserModel> listSysUser = listUser(offset, limit, keySearch);

		UserHeaderDto sysUserHeader = new UserHeaderDto();

		sysUserHeader.setListSysUserDto(userMapper.asUserDTO(listSysUser));
		sysUserHeader.setTotalRecord(userDao.count());
		LogUtil.logDebug(logger, true, sysUserHeader.toString());
		return sysUserHeader;
	}

	private List<UserModel> listUser(int offset, int limit, String keySearch) {
		if (keySearch == null || keySearch.isEmpty()) {
			return userDaoImpl.listAllUser(offset, limit);
		} else {
			return userDaoImpl.listUserSearchByUserName(offset, limit, keySearch);
		}
	}

	public void deleteUser(long idUser) {
		userDao.delete(idUser);
	}

}
