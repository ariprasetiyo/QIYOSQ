package arprast.qiyosq.controller;

import arprast.qiyosq.dao.RolesDao;
import arprast.qiyosq.model.RolesModel;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/v1/user")
public class UserController {

    @Autowired
    RolesDao sysRolesDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("selectroles", viewSysRoleAll());
        return "/admin/v1/user/index";
    }

    private List<RolesModel> viewSysRoleAll() {
        return (List<RolesModel>) sysRolesDao.findAll();
    }
}
