package arprast.qiyosq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import arprast.qiyosq.services.RolesService;

@Controller
@RequestMapping("/admin/v1/main")
public class MainAdminController {

	@Autowired
	RolesService rolesService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("selectroles", rolesService.viewSysRoleAll());
		return "/admin/v1/spa/index";
	}
}
