package arprast.qiyosq.services;

import java.util.List;
import org.springframework.ui.Model;
import arprast.qiyosq.dto.AuthorizationDto;

public interface AuthorizationService {

	public void viewSysRoles(Model model, Long idRoles);

	public void existingMenuInSysMenu(Model model);

	public void existingMenuInAuthorization(Model model, Long idRole);

	public AuthorizationDto getAuthorizationList(int offset, int limit, String keySearch);

	public List<AuthorizationDto> getAuthorizationList(Long idRole);

	public void viewDataMenu(Model model, Long idRole);

	public AuthorizationDto saveDataMenu(Long idRole, boolean vInsert, boolean vUpdate, boolean vDelete,
			boolean vDisable, Long MenuId, Long parentMenuId);
	
	int updateAuthorization(Long id, boolean vInsert, boolean vUpdate, boolean vDelete, boolean vDisable);
	
	void deleteAuthorization(Long id);
}
