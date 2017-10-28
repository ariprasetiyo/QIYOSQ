package arprast.qiyosq.services;

import arprast.qiyosq.model.UserModel;

public interface UserInterface {
	public void delete(long idUser);
	public UserModel save(UserModel sysUser);
}
