/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dao;

import java.util.List;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import arprast.qiyosq.model.UserModel;

/**
 *
 * @author ari-prasetiyo
 */
@Repository
public class UserDaoEM {

	@Autowired
	private EntityManager em;

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
}
