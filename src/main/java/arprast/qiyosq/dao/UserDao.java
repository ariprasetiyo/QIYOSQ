/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import arprast.qiyosq.model.UserModel;

/**
 *
 * @author ari-prasetiyo
 */
public interface UserDao extends PagingAndSortingRepository<UserModel, Long > {
    
}
