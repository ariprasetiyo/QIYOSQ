package arprast.qiyosq.services;

import java.util.List;
import org.springframework.ui.Model;
import arprast.qiyosq.dto.AuthorizationDto;
import arprast.qiyosq.model.AuthorizationModel;
import arprast.qiyosq.model.MenusModel;

public interface AuthorizationService {

	public void viewSysRoles(Long idRoles);

	public List<MenusModel> listMenu();

	public List<AuthorizationModel> listMenuAuthorization(Long idRole);

	public AuthorizationDto getAuthorizationList(int offset, int limit, String keySearch);

	public String getAuthorizationJson(Long idRole);
	
	public List<AuthorizationDto> getAuthorizationList(Long idRole);

	public void viewDataMenu(Model model, Long idRole);

	public AuthorizationDto saveDataMenu(Long idRole, boolean vInsert, boolean vUpdate, boolean vDelete,
			boolean vDisable, Long MenuId, Long parentMenuId);

	int updateAuthorization(Long id, AuthorizationDto authorizationDto);

	void deleteAuthorization(Long id);
}
