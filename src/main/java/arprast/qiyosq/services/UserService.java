package arprast.qiyosq.services;

import arprast.qiyosq.dto.JsonMessageDto;
import arprast.qiyosq.dto.UserDto;
import arprast.qiyosq.dto.UserHeaderDto;

public interface UserService {
	void deleteUser(long idUser);

	JsonMessageDto saveUserAndRole(UserDto user);

	JsonMessageDto updateUserAndRole(UserDto user);

	UserHeaderDto listUserHeader(int offset, int limit, String keySearch);
	
}
