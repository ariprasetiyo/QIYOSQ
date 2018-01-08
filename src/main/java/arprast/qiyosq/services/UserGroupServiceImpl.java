package arprast.qiyosq.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arprast.qiyosq.dao.UserGroupDao;
import arprast.qiyosq.dao.UserGroupDaoImpl;
import arprast.qiyosq.dto.RequestData;
import arprast.qiyosq.dto.ResponseData;
import arprast.qiyosq.dto.RolesDto;
import arprast.qiyosq.model.RolesModel;
import arprast.qiyosq.ref.StatusType;

@Service
public class UserGroupServiceImpl implements UserGroupService {

	private static final Logger logger = LoggerFactory.getLogger(UserGroupServiceImpl.class);

	@Autowired
	private UserGroupDaoImpl userGroupDaoImpl;

	@Autowired
	private UserGroupDao userGroupDao;

	@Override
	public ResponseData listUserGroup(RequestData requestData) {

		if (logger.isDebugEnabled()) {
			logger.debug("{}", requestData.toString());
		}

		List<RolesModel> rolesModel = userGroupDaoImpl.listUserGroup(requestData);
		long countRolesModel = userGroupDaoImpl.countUserGroup(requestData);

		ResponseData responseData = new ResponseData();
		responseData.setData(rolesModel);
		responseData.setTotalRecord(countRolesModel);

		if (logger.isDebugEnabled()) {
			logger.debug("{}", responseData.toString());
		}
		return responseData;
	}

	@Override
	public ResponseData saveUserGroup(RolesDto rolesDto) {
		logger.debug("Start save user group {}", rolesDto.toString());

		RolesModel rolesModel = new RolesModel();
		rolesModel.setRoleName(rolesDto.getRoleName());
		rolesModel = userGroupDao.save(rolesModel);
		ResponseData responseData = new ResponseData();
		responseData.setData(rolesModel);

		if (rolesModel.getId() == null) {
			responseData.setStatusType(StatusType.SAVE_USER_GROUP_ERROR);
			responseData.setMessage(StatusType.SAVE_USER_GROUP_ERROR.stringValue);
		} else {
			responseData.setStatusType(StatusType.SAVE_SUCCEED);
		}

		logger.debug("Final save user group {}", responseData.toString());
		return responseData;
	}

	public ResponseData editUserGroup(RolesDto rolesDto) {
		return null;
	}
}
