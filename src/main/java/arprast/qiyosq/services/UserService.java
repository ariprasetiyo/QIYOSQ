package arprast.qiyosq.services;

import arprast.qiyosq.dto.JsonMessageDto;
import arprast.qiyosq.dto.UserHeaderDto;
import arprast.qiyosq.model.UserModel;

public interface UserService {
	void deleteUser(long idUser);

	JsonMessageDto saveUserAndRole(UserModel user, Long[] selectRole);
	
	JsonMessageDto updateUserAndRole(UserModel user, Long[] selectRole);
	
	UserHeaderDto listUserHeader(int offset, int limit, String keySearch);
}
