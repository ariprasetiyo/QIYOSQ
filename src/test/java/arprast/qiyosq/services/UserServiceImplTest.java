package arprast.qiyosq.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import arprast.qiyosq.dao.UserDao;
import arprast.qiyosq.dto.UserDto;
import arprast.qiyosq.model.UserModel;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

	@Autowired
	UserService userServiceImpl;
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void saveUser() {
		String email = "prasetiyoooo@gmail.com";
		UserModel userModel = userDao.findUserByEmail(email);
		//userServiceImpl.deleteUser(userModel.getId());
		
		UserDto userDto = new UserDto();
		userDto.setUsername("Ari Praseti");
		userDto.setPassword("123456");
		userDto.setEmail(email);
		userDto.setIsActive(true);
		userDto.setNoHp("08564548111");
		//userDto.setRoleName("admin");
		userDto.setName("ddddddddddd");
		//userDto.setId(userModel.getId());
		long[] selectRole = { 1L};
		//userDto.setRoleId(selectRole);
		userServiceImpl.updateUserAndRole(userDto);
	}
	
	@Test
	public void editUser() {
		String email = "prasetiyoooo@gmail.com";
		UserModel userModel = userDao.findUserByEmail(email);
		System.out.println(userModel.getId());
		UserDto userDto = new UserDto();
		userDto.setUsername("Ari Praseti");
		userDto.setPassword("123456");
		userDto.setEmail(email);
		userDto.setIsActive(true);
		userDto.setNoHp("08564548222");
		//userDto.setRoleName("admin");
		userDto.setName("ccccccccc");
		userDto.setId(userModel.getId());
		long[] selectRole = { 1L};
		//userDto.setRoleId(selectRole);
		userServiceImpl.updateUserAndRole(userDto);
	}
}
