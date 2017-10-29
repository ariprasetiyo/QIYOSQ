package arprast.qiyosq.services;

import arprast.qiyosq.dto.UserHeaderDto;

public interface UserService {
	void deleteUser(long idUser);

	boolean isSuccessSaveUserAndRole(String textUserName, String textName, String textEmail, String noHp,
			Long[] selectRole, boolean isActiveUser, String textPassword, String idUser);
	
	UserHeaderDto listUserHeader(int offset, int limit, String keySearch);
}
