package arprast.qiyosq.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import arprast.qiyosq.beans.UserMapper;
import arprast.qiyosq.dao.UserDao;
import arprast.qiyosq.dao.UserDaoEM;
import arprast.qiyosq.dao.UserRolesDao;
import arprast.qiyosq.dto.JsonMessageDto;
import arprast.qiyosq.dto.UserDto;
import arprast.qiyosq.dto.UserHeaderDto;
import arprast.qiyosq.model.UserModel;
import arprast.qiyosq.model.UserRolesModel;
import arprast.qiyosq.ref.ActionType;
import arprast.qiyosq.ref.MessageErrorType;
import arprast.qiyosq.ref.MessageSuccessType;
import arprast.qiyosq.util.LogUtil;
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
	
	
	public JsonMessageDto saveEditUserAndRole(UserDto userDto) throws Exception, IllegalArgumentException  {

		boolean isEdit = (userDto.getId() != null ? true : false);

		ActionType actionType = ActionType.SAVE;
		MessageErrorType messageErrorType = MessageErrorType.SAVE_ERROR;
		MessageSuccessType messageSuccessType = MessageSuccessType.SAVE_SUCCEED;
		if (isEdit) {
			actionType = ActionType.UPADATE;
			messageErrorType = MessageErrorType.UPDATE_ERROR;
			messageSuccessType = MessageSuccessType.UPDATE_SUCCEED;
		}

		LogUtil.logDebugType(logger, true, actionType, "{}", userDto.toString());
		UserModel user = userMapper.asUserModel(userDto);

		if (isEdit) {
			userDaoEM.deleteByUserId(user.getId());
		}
		user = saveUser(user);

		JsonMessageDto jsonMessageDto = new JsonMessageDto();
		if (user == null) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			LogUtil.logDebugType(logger, true, messageErrorType, "null");
			jsonMessageDto.setMessageErrorType(messageErrorType);
			return jsonMessageDto;
		}

		jsonMessageDto.setMessageSuccessType(messageSuccessType);
		LogUtil.logDebugType(logger, true, messageSuccessType, user.toString());
		if (1 == 1) {
			//throw new IllegalArgumentException("Cobba");
		}
		return jsonMessageDto;
	}

	// @Transactional(propagation = Propagation.REQUIRED)
	public UserModel saveUser(UserModel user) {
		user = userDao.save(user);
		return user;
	}

	// @Transactional(propagation = Propagation.REQUIRED)
	/*
	 * private void deleteUser(UserModel user) { //
	 * userRolesDao.deleteByUserId(user.getId()); }
	 */

	// @Transactional(propagation = Propagation.REQUIRED)
//	private UserRolesModel saveUserRole(UserRolesModel userRolesModel) {
//		return userRolesDao.save(userRolesModel);
//	}

	public List<UserModel> listUser(int offset, int limit, String keySearch) {
		if (keySearch == null || keySearch.isEmpty()) {
			return userDaoEM.listAllUser(offset, limit);
		} else {
			return userDaoEM.listUserSearchByUserName(offset, limit, keySearch);
		}
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

	public void deleteUser(long idUser) {
		userDao.delete(idUser);
	}

	/*
	 * private void deleteUserRole(long idUser) {
	 * userRolesDao.deleteByUserId(idUser); }
	 */


	@Transactional(rollbackFor = { Exception.class, Throwable.class, IllegalArgumentException.class }, readOnly = false)
	public JsonMessageDto updateUserAndRole(UserDto userDto) {
		try{
			return saveEditUserAndRole(userDto);	
		}catch(Exception x){
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			x.printStackTrace();
		}
		return null;
	}


	@Transactional(rollbackFor = { Exception.class, Throwable.class, IllegalArgumentException.class }, readOnly = false)
	public JsonMessageDto saveUserAndRole(UserDto userDto) {
		try{
			return saveEditUserAndRole(userDto);	
		}catch(Exception x){
			x.printStackTrace();
		}
		return null;
	}
}
