package arprast.qiyosq.services;

import arprast.qiyosq.model.UserRolesModel;

public interface UserRolesInterface {
	public UserRolesModel save(UserRolesModel sysUserRoles);
	public int deleteByUserId(long userId);
}
