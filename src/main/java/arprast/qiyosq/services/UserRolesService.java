package arprast.qiyosq.services;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import arprast.qiyosq.dao.UserRolesDao;
import arprast.qiyosq.model.UserRolesModel;
import arprast.qiyosq.util.LogsUtil;

@Repository
public class UserRolesService implements UserRolesInterface {
	
	private static Logger logger = LoggerFactory.getLogger(UserRolesService.class);
	
	@Autowired
    private UserRolesDao sysUserRolesDao;
	
	@Autowired
	private EntityManager em;

	@Override
	public UserRolesModel save(UserRolesModel sysUserRoles) {
		return sysUserRolesDao.save(sysUserRoles);
	}

	@Override
	public int deleteByUserId(long userId) {
		int countDelete = em.createQuery("delete from UserRolesModel where sysUser.id = :userId ")
				.setParameter("userId", userId).executeUpdate();
		LogsUtil.logDebug(logger, "delete  UserRolesModel : {} for update", countDelete);
		return countDelete;
	}
}
