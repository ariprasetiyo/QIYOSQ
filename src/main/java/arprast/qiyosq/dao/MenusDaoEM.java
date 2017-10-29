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
import arprast.qiyosq.model.MenusModel;

/**
 *
 * @author ari-prasetiyo
 */
@Repository
public class MenusDaoEM {

	@Autowired
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<MenusModel> listOfMenus(String keySearch, int offset, int limit) {
		return em.createQuery("from MenusModel where menusName like :searchUserName order by menusName asc")
				.setParameter("searchUserName", "%" + keySearch + "%").setFirstResult(offset).setMaxResults(limit)
				.getResultList();
	}

}
