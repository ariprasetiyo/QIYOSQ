package arprast.qiyosq.beans;


import arprast.qiyosq.dto.UserDto;
import arprast.qiyosq.model.UserModel;
import fr.xebia.extras.selma.Field;
import fr.xebia.extras.selma.Mapper;

/**
 * {@code SelmaMapper mapper = Selma.mapper(SelmaMapper.class); 
 * OutBean res = mapper.asOutBean(in); 
 * Or OutBean dest = dao.getById(42); 
 * OutBean res = mapper.updateOutBean(in, dest); }
 * 
 * @author ARI-PRASETIYO
 * @see https://github.com/ariprasetiyo/selma
 * @see https://github.com/xebia-france/selma/issues/23
 * @See http://www.selma-java.org/#custom-mapper
 * @see http://www.selma-java.org/#abstract-mapper
 */
@Mapper(withIgnoreFields = { 
		"arprast.qiyosq.dto.UserDto.createdtime",
		"arprast.qiyosq.dto.UserDto.modifiedtime",
		"arprast.qiyosq.dto.UserDto.url", 
		"arprast.qiyosq.dto.RolesDto.createdtime",
		"arprast.qiyosq.dto.RolesDto.modifiedtime",
		"arprast.qiyosq.model.UserRolesModel.id"},
		withCustomFields = {
				@Field({"userRolesModel","roles"}),
				@Field({"arprast.qiyosq.model.UserRolesModel.sysroles.rolename","arprast.qiyosq.dto.RolesDto.rolename"}),
				@Field({"arprast.qiyosq.model.UserRolesModel.sysroles.id","arprast.qiyosq.dto.RolesDto.id"})
    }
)

public interface UserMapper {

	/**
	 * 
	 * @param source
	 * @return UserDto
	 */
//	@Maps(withCustomFields = { @Field({ "userRolesModel", "roles" }) })
	UserDto asUserDTO(UserModel source);

	/**
	 * 
	 * @param source
	 * @return UserModel
	 */
	UserModel asUserModel(UserDto source);
}
