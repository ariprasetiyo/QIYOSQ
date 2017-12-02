/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import arprast.qiyosq.model.UserModel;

/**
 *
 * @author ari-prasetiyo
 * @category {@literal If 1 method there are two or more transactionDao, create
 *           here}
 * 
 */
public class UserDaoImpl {

	@Autowired
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private UserDao userDao;

	@SuppressWarnings("unchecked")
	public List<UserModel> listAllUser(int offset, int limit) {
		return em.createQuery("from UserModel order by username asc").setFirstResult(offset).setMaxResults(limit)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<UserModel> listUserSearchByUserName(int offset, int limit, String keySearch) {
		return em.createQuery("from UserModel where username like :searchUserName order by username asc")
				.setParameter("searchUserName", "%" + keySearch + "%").setFirstResult(offset).setMaxResults(limit)
				.getResultList();
	}

	@Modifying
	public int deleteByUserId(long userId) {
		return em.createNativeQuery("delete from sys_user_roles where sys_user_id =:nUserId")
				.setParameter("nUserId", userId).executeUpdate();
	}

	@Transactional(rollbackFor = { Exception.class, Throwable.class, IllegalArgumentException.class }, readOnly = false)
	public UserModel saveEditUserRole(UserModel user, boolean isEdit) {
		TransactionStatus TransactionStatus = TransactionAspectSupport.currentTransactionStatus();
		try {
			if (isEdit) {
				deleteByUserId(user.getId());
			}
			user = userDao.save(user);
			return user;
		} catch (Exception e) {
			TransactionStatus.setRollbackOnly();
			e.printStackTrace();
		}
		return null;
	}

}
