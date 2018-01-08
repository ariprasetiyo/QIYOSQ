/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import arprast.qiyosq.model.RolesModel;

/**
 *
 * @author ari-prasetiyo
 */
@Repository
public interface UserGroupDao extends JpaRepository<RolesModel, Long> {
	
}
