package arprast.qiyosq.controller.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import arprast.qiyosq.dto.GlobalDto;
import arprast.qiyosq.dto.AuthorizationDto;
import arprast.qiyosq.dto.RolesDto;
import arprast.qiyosq.services.AuthorizationService;
import arprast.qiyosq.util.LogsUtil;

@RestController
@RequestMapping("/admin/v1/api/authorization")
public class AuthorizationRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
   
    @Autowired
    AuthorizationService authorizationService;

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<GlobalDto> index(@PathVariable("id") Long id,
            @RequestParam(value = "vInsert") boolean vInsert,
            @RequestParam(value = "vUpdate") boolean vUpdate,
            @RequestParam(value = "vDelete") boolean vDelete,
            @RequestParam(value = "vDisable") boolean vDisable) {
        int inUpdate = authorizationService.updateAuthorization(id, vInsert, vUpdate, vDelete, vDisable);

        LogsUtil.logDebug(logger, false,"{} inUpdate {}", id, inUpdate);

        GlobalDto globalDto = new GlobalDto();
        globalDto.setId(id);
        globalDto.setCount(inUpdate);

        return new ResponseEntity<GlobalDto>(globalDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/addMenu/{idRoles}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorizationDto authorizationAddMenu(@PathVariable("idRoles") Long idRole,
            @RequestParam("vInsert") boolean vInsert,
            @RequestParam("vUpdate") boolean vUpdate,
            @RequestParam("vDelete") boolean vDelete,
            @RequestParam("vDisable") boolean vDisable,
            @RequestParam("modelMenuId") Long MenuId,
            @RequestParam("modelParentMenuId") Long parentMenuId) {
        return authorizationService.saveDataMenu(idRole, vInsert, vUpdate,
                vDelete, vDisable, MenuId, parentMenuId);
    }
       
	@RequestMapping(value = "/list/{idRole}", method = RequestMethod.POST)
	public ResponseEntity<List<AuthorizationDto>> viewAuthorizationList(@PathVariable("idRole") Long idRole) {
		return new ResponseEntity<List<AuthorizationDto>>(authorizationService.getAuthorizationList(idRole),
				HttpStatus.OK);
	}
	
    @RequestMapping(value = "/deleteMenu/{idAuthorization}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GlobalDto> deleteMenu(@PathVariable("idAuthorization") Long id) {
    	authorizationService.deleteAuthorization(id);
        GlobalDto globalDto = new GlobalDto();
        globalDto.setId(id);
        return new ResponseEntity<GlobalDto>(globalDto, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/viewRoles/", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public RolesDto viewRoles(@RequestParam("idRole") Long idRoles) {
        //logger.debug("view roles {}", idRoles);
        return null;
    }

}
