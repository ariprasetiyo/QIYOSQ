/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ari-prasetiyo
 */
@Controller
@RequestMapping("/admin/v1/view/menu")
public class MenuController {
    
    @RequestMapping(value = "", method=RequestMethod.GET)
    public String index(){
        return "/admin/v1/pages/menu-index";
    }
}
