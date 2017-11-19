package arprast.qiyosq.beans;

import arprast.qiyosq.dto.UserDto;
import arprast.qiyosq.model.UserModel;
import fr.xebia.extras.selma.Mapper;

/**
 * {@code SelmaMapper mapper = Selma.mapper(SelmaMapper.class); 
 * OutBean res = mapper.asOutBean(in); 
 * Or OutBean dest = dao.getById(42); 
 * OutBean res = mapper.updateOutBean(in, dest); }
 * 
 * @author ARI-PRASETIYO
 * @see https://github.com/ariprasetiyo/selma
 */
/**
 * @author root
 *
 */
@Mapper(withIgnoreFields = { 
		"arprast.qiyosq.dto.UserDto.roleid",
		"arprast.qiyosq.dto.UserDto.createdtime",
		"arprast.qiyosq.dto.UserDto.modifiedtime",
		"arprast.qiyosq.dto.UserDto.rolename", 
		"arprast.qiyosq.dto.UserDto.url" }
)
public interface UserMapper {

	/**
	 * 
	 * @param source
	 * @return UserDto
	 */
	UserDto asUserDTO(UserModel source);

	/**
	 * 
	 * @param source
	 * @return UserModel
	 */
	UserModel asUserModel(UserDto source);
}
