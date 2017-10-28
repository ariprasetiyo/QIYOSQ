package arprast.qiyosq.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import arprast.qiyosq.dao.UserDao;
import arprast.qiyosq.dao.UserRolesDao;
import arprast.qiyosq.dto.RolesDto;
import arprast.qiyosq.dto.UserDto;
import arprast.qiyosq.dto.UserHeaderDto;
import arprast.qiyosq.model.UserModel;
import arprast.qiyosq.util.LogsUtil;

@Repository
public class UserService implements UserInterface {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserDao sysUserDao;
	
	@Autowired
    private UserRolesDao sysUserRolesDao;
	
	@Autowired
    private EntityManager em;
	
	public void delete(long idUser) {
		sysUserDao.delete(idUser);
	}
	
	public UserModel save(UserModel sysUser) {
		return sysUserDao.save(sysUser);
	}
	
	@SuppressWarnings("unchecked")
	public UserHeaderDto functionSysUserDto(int offset, int limit, String keySearch) {
        List<UserModel> listSysUser = new ArrayList<UserModel>();
        StringBuilder queryListUser = new StringBuilder();
        if (keySearch == null || keySearch.isEmpty()) {
            queryListUser.append("from UserModel order by username asc");
            listSysUser = em.createQuery(queryListUser.toString())
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
        } else {
            queryListUser.append("from UserModel where username like :searchUserName order by username asc");
            listSysUser = em.createQuery(queryListUser.toString())
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .setParameter("searchUserName", "%" + keySearch + "%")
                    .getResultList();
        }
        
        UserHeaderDto sysUserHeader = new UserHeaderDto();
        
        List<UserDto> listUserDto = new ArrayList<UserDto>();
        for (UserModel sysUser : listSysUser) {
            
            UserDto sysUserDto = new UserDto();
            sysUserDto.setCreatedTime(sysUser.getCreatedTime());
            sysUserDto.setModifiedTime(sysUser.getModifiedTime());
            sysUserDto.setId(sysUser.getId());
            sysUserDto.setUsername(sysUser.getUsername());
            sysUserDto.setName(sysUser.getName());
            sysUserDto.setEmail(sysUser.getEmail());
            sysUserDto.setNoHp(sysUser.getNoHp());
            sysUserDto.setIsActive(sysUser.isIsActive());
            
            List<RolesDto> listSysRoles = sysUserRolesDao.listRolesByNameUser(sysUser.getId());
            if (listSysRoles.size() != 0) {
                StringBuilder builderRoleName = new StringBuilder();
                long[] roloIdArrayLong = new long[listSysRoles.size()];
                int tmpPlusPlus = 0;
                for (RolesDto sysRoleDto : listSysRoles) {
                    builderRoleName.append(sysRoleDto.getRoleName());
                    roloIdArrayLong[tmpPlusPlus] = sysRoleDto.getId();
					LogsUtil.logDebug(logger, "{} -> role : {} ", sysUser.getUsername(), sysRoleDto.getRoleName());
					if (tmpPlusPlus == (listSysRoles.size() - 1)) {
						continue;
					}
                    builderRoleName.append(", ");
                    tmpPlusPlus++;
                }
                sysUserDto.setRoleName(builderRoleName.toString());
                sysUserDto.setRoleId(roloIdArrayLong);
            }
            
            listUserDto.add(sysUserDto);
        }
        sysUserHeader.setListSysUserDto(listUserDto);
        sysUserHeader.setTotalRecord(sysUserDao.count());
        return sysUserHeader;
    }
}
