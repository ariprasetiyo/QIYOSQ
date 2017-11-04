/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.controller.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import arprast.qiyosq.dto.GlobalDto;
import arprast.qiyosq.model.UserModel;
import arprast.qiyosq.services.UserService;
import arprast.qiyosq.util.LogsUtil;

@RestController
@RequestMapping(value = "/admin/v1/api/user")
public class UserRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/delete{idUser}", method = RequestMethod.DELETE)
	public ResponseEntity<GlobalDto> deleteUser(@PathVariable("idUser") Long idUser) {
		userService.deleteUser(idUser);
		GlobalDto globalDto = new GlobalDto();
		globalDto.setId(idUser);
		return new ResponseEntity<GlobalDto>(globalDto, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Future<ResponseEntity<UserModel>> getListUser(@RequestParam("offset") int offset,
			@RequestParam("limit") int limit, @RequestParam(value = "search", required = false) String keySearch) {

		/*
		 * concurrent
		 */
		return CompletableFuture.supplyAsync(() -> {
			try {
				LogsUtil.logDebug(logger, false, "offset : {}, limit : {}, search : {}", offset, limit, keySearch);
				return new ResponseEntity(userService.listUserHeader(offset, limit, keySearch), HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		});
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public Map<String, Object> saveUser(@RequestParam("textUserName") String textUserName,
			@RequestParam("textName") String textName, @RequestParam("textEmail") String textEmail,
			@RequestParam("textNoHp") String noHp, @RequestParam("selectRole[]") Long[] selectRole,
			@RequestParam("checkBoxIsActive") boolean isActiveUser, @RequestParam("textPassword") String textPassword,
			@RequestParam(value = "idUSerNya", required = false) String idUser) {
		LogsUtil.logDebug(logger, false,
				" textUserName : {}, textPassword : {},textName : {}, textEmail : {},"
						+ " noHp : {}, selectRole : {}, isActiveUser : {}, idUSerNya : {}",
				textUserName, textPassword, textName, textEmail, noHp, selectRole[0], isActiveUser, idUser);

		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("isSuccessSave", userService.isSuccessSaveUserAndRole(textUserName, textName, textEmail, noHp,
				selectRole, isActiveUser, textPassword, idUser));
		return mapJson;
	}

}
