/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arprast.qiyosq.controller.rest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import arprast.qiyosq.dto.GlobalDto;
import arprast.qiyosq.dto.JsonMessageDto;
import arprast.qiyosq.dto.UserDto;
import arprast.qiyosq.dto.UserHeaderDto;
import arprast.qiyosq.services.UserService;

@RestController
@RequestMapping(value = "/admin/v1/api/user")
@Validated
public class UserRestController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/delete{idUser}", method = RequestMethod.DELETE)
	public ResponseEntity<GlobalDto> deleteUser(@PathVariable("idUser") Long idUser) {
		userService.deleteUser(idUser);
		GlobalDto globalDto = new GlobalDto();
		globalDto.setId(idUser);
		return new ResponseEntity<GlobalDto>(globalDto, HttpStatus.OK);
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Future<ResponseEntity<UserHeaderDto>> getListUser(@RequestParam("offset") int offset,
			@RequestParam("limit") int limit, @RequestParam(value = "search", required = false) String keySearch) {

		/*
		 * concurrent
		 */
		return CompletableFuture.supplyAsync(() -> {
			try {		
				return new ResponseEntity<UserHeaderDto>(userService.listUserHeader(offset, limit, keySearch),
						HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return new ResponseEntity<UserHeaderDto>(new UserHeaderDto(), HttpStatus.NOT_ACCEPTABLE);
		});
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public Future<ResponseEntity<JsonMessageDto>> saveUser(@Valid UserDto user,
			@RequestParam(value = "selectRole[]", required = true) Long[] selectRole) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				return new ResponseEntity<JsonMessageDto>(userService.saveUserAndRole(user, selectRole),
						HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<JsonMessageDto>(new JsonMessageDto(), HttpStatus.NOT_ACCEPTABLE);
		});
	}

}
