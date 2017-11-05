/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import arprast.qiyosq.model.UserModel;

/**
 *
 * @author ari-prasetiyo
 */
@Repository
public interface UserDao extends JpaRepository<UserModel, Long> {

	@Query("select count(id) from UserModel where email = :nEmail ")
	public int findUserByEmail(@Param("nEmail") String email);
}
