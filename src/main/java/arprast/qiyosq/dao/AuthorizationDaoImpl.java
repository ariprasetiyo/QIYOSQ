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

import arprast.qiyosq.dto.RequestData;
import arprast.qiyosq.model.AuthorizationModel;

/**
 *
 * @author ari-prasetiyo
 */

public class AuthorizationDaoImpl {

	@Autowired
	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<AuthorizationModel> listAllAuthorizationMenu(RequestData requestData) {
		return em
				.createQuery(
						"from AuthorizationModel am "
						+ "JOIN FETCH am.sysRoles sr "
						+ "JOIN FETCH am.sysMenu sm "
						+ "where sr.id = :nsysRolesId and sm.menusName is not null")
				.setParameter("nsysRolesId", requestData.getId()).setFirstResult(requestData.getOffset())
				.setMaxResults(requestData.getLimit()).getResultList();
	}

}
