/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.dao;

import arprast.qiyosq.dto.RolesDto;
import arprast.qiyosq.model.UserRolesModel;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;

/**
 *
 * @author ari-prasetiyo
 */
public interface UserRolesDao extends PagingAndSortingRepository<UserRolesModel, Long> {

    @Query("select x from  UserRolesModel x where sysRoles.roleName = :nRoleName order by id desc")
    public Page<UserRolesModel> getByName(@Param("nRoleName") String roleName, @PageableDefault(value = 1) Pageable pageable);

    @Query("select id from UserRolesModel where sysRoles.roleName = :nRolesName order by id desc")
    public long getIdByName(@Param("nRolesName") String roleName);

    @Query("from UserRolesModel where sysRoles.roleName = :nRolesName order by id desc")
    public UserRolesModel getIdByNameFeedbackAll(@Param("nRolesName") String roleName);

    @Query("from UserRolesModel where sysUser.username = :nUsername order by id")
    public List<UserRolesModel> listUserRolesByNameUser(@Param("nUsername") String userName);

    @Query("select new arprast.qiyosq.dto.RolesDto(sysRoles.id as id, sysRoles.roleName as roleName) from UserRolesModel where sysUser.id = :nId order by id")
    public List<RolesDto> listRolesByNameUser(@Param("nId") Long nId);

    @Query("select count(id) from UserRolesModel where sysUser.id = :userId ")
    public Integer countIdByUser(@Param("userId") Long userId);
    
    @Query("delete from UserRolesModel where sysUser.id = :nUserId")
    public Integer deleteByUserId(@Param("nUserId") Long userId);
    
//    @Query("delete from SysUserRoles where sysUser.id = :userId")
//    public int deleteUserRoles(@Param("userId") long userId);

}
