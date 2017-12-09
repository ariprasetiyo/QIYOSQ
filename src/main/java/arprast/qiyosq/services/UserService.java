package arprast.qiyosq.services;

import arprast.qiyosq.dto.UserDto;
import arprast.qiyosq.dto.UserHeaderDto;

public interface UserService {
	boolean deleteUser(long idUser);

	UserDto saveUserAndRole(UserDto user);

	UserDto updateUserAndRole(UserDto user);

	UserHeaderDto listUser(int offset, int limit, String keySearch);

}
