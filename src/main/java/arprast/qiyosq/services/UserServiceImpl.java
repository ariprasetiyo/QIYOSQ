package arprast.qiyosq.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import arprast.qiyosq.beans.UserMapper;
import arprast.qiyosq.dao.UserDao;
import arprast.qiyosq.dao.UserDaoImpl;
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

	public UserDto updateUserAndRole(UserDto userDto) {
		LogUtil.logDebugType(logger, true, ActionType.UPADATE, "{}", userDto.toString());
		if (userDto.getOldPassword() != null || !userDto.getOldPassword().isEmpty()) {
			if (isValidPassword(userDto) == false) {
				userDto = new UserDto();
				userDto.setStatusType(StatusType.UPDATE_ERROR);
				userDto.setMessage(StatusType.WRONG_OLD_PASSWORD.stringValue);
				return userDto;
			}

		}
		return saveEditUserAndRole(userDto, true, ActionType.UPADATE, StatusType.UPDATE_ERROR,
				StatusType.UPDATE_SUCCEED);
	}

	private boolean isValidPassword(UserDto userDto) {
		int countUser = userDao.countUserByEmailAndPassword(userDto.getEmail(), userDto.getUsername(),
				userDto.getOldPassword());
		return (countUser >= 1 ? true : false);
	}

	public UserDto saveUserAndRole(UserDto userDto) {
		LogUtil.logDebugType(logger, true, ActionType.SAVE, "{}", userDto.toString());
		return saveEditUserAndRole(userDto, false, ActionType.SAVE, StatusType.SAVE_ERROR, StatusType.SAVE_SUCCEED);
	}

	private UserDto saveEditUserAndRole(UserDto userDto, final boolean isEdit, final ActionType actionType,
			final StatusType messageErrorType, final StatusType messageSuccessType) {

		UserModel user = userMapper.asUserModel(userDto);
		user = userDaoImpl.saveEditUserRole(user, isEdit);
		userDto = userMapper.asUserDTO(user);
		if (user == null) {
			LogUtil.logDebugType(logger, true, messageErrorType, "null");
			userDto = new UserDto();
			userDto.setStatusType(messageErrorType);
			userDto.setMessage(messageErrorType.stringValue);
			return userDto;
		}

		userDto.setStatusType(messageSuccessType);
		userDto.setMessage(messageErrorType.stringValue);
		LogUtil.logDebugType(logger, true, messageSuccessType, user.toString());

		return userDto;
	}

	public UserHeaderDto listUserHeader(int offset, int limit, String keySearch) {

		LogUtil.logDebug(logger, true, "offset={}, limit={}, search={}", offset, limit, keySearch);
		List<UserModel> listSysUser = listUser(offset, limit, keySearch);

		UserHeaderDto sysUserHeader = new UserHeaderDto();

		sysUserHeader.setListUser(userMapper.asUserDTO(listSysUser));
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

	public boolean deleteUser(long idUser) {
		TransactionStatus TransactionStatus = TransactionAspectSupport.currentTransactionStatus();
		userDao.delete(idUser);
		return TransactionStatus.isCompleted();
	}

}
