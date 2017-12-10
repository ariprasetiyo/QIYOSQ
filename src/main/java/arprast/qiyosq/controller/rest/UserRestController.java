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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import arprast.qiyosq.dto.Dto;
import arprast.qiyosq.dto.RequestData;
import arprast.qiyosq.dto.RequestDto;
import arprast.qiyosq.dto.ResponseDto;
import arprast.qiyosq.dto.UserDto;
import arprast.qiyosq.dto.UserHeaderDto;
import arprast.qiyosq.ref.StatusType;
import arprast.qiyosq.services.UserService;

@RestController
@RequestMapping(value = "/admin/v1/api/user")
public class UserRestController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = { "application/json",
			"application/xml" }, consumes = { "application/json", "application/xml" })
	public ResponseEntity<ResponseDto<Dto>> deleteUser(@RequestBody @Valid RequestDto<Dto> user) {
		Dto dto = new Dto();
		dto.setId(user.getRequestData().getId());
		ResponseDto<Dto> responseDto = new ResponseDto<Dto>();
		boolean isSuccessDeleteUser = userService.deleteUser(user.getRequestData().getId());
		if (isSuccessDeleteUser) {
			responseDto = new ResponseDto<Dto>(StatusType.DELETE_SUCCEED, StatusType.DELETE_SUCCEED.stringValue, dto);
		} else {
			responseDto = new ResponseDto<Dto>(StatusType.DELETE_ERROR, StatusType.DELETE_ERROR.stringValue, dto);
		}
		return new ResponseEntity<ResponseDto<Dto>>(responseDto, HttpStatus.OK);
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	public Future<ResponseEntity<ResponseDto<UserHeaderDto>>> getListUser(
			@RequestBody @Valid RequestDto<RequestData> user) {

		return CompletableFuture.supplyAsync(() -> {
			try {
				UserHeaderDto userHeaderDto = userService.listUser(user.getRequestData().getOffset(),
						user.getRequestData().getLimit(), user.getRequestData().getSearch());
				ResponseDto<UserHeaderDto> userHeaderJson = new ResponseDto<UserHeaderDto>(
						userHeaderDto.getStatusType(), userHeaderDto.getMessage(), userHeaderDto);
				return new ResponseEntity<ResponseDto<UserHeaderDto>>(userHeaderJson, HttpStatus.OK);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<ResponseDto<UserHeaderDto>>(new ResponseDto<UserHeaderDto>(),
					HttpStatus.NOT_ACCEPTABLE);
		});
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	public Future<ResponseEntity<ResponseDto<UserDto>>> saveUser(@RequestBody @Valid RequestDto<UserDto> user) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				UserDto userDto = userService.saveUserAndRole(user.getRequestData());
				ResponseDto<UserDto> userDtoJson = new ResponseDto<UserDto>(userDto.getStatusType(),
						userDto.getMessage(), userDto);
				return new ResponseEntity<ResponseDto<UserDto>>(userDtoJson, HttpStatus.OK);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<ResponseDto<UserDto>>(new ResponseDto<UserDto>(), HttpStatus.NOT_ACCEPTABLE);
		});
	}

	@RequestMapping(value = "/editUser", method = RequestMethod.POST, produces = { "application/json",
			"application/xml" }, consumes = { "application/json", "application/xml" })
	public Future<ResponseEntity<ResponseDto<UserDto>>> editUser(@RequestBody @Valid RequestDto<UserDto> user) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				UserDto userDto = userService.updateUserAndRole(user.getRequestData());
				ResponseDto<UserDto> userDtoJson = new ResponseDto<UserDto>(userDto.getStatusType(),
						userDto.getMessage(), userDto);
				return new ResponseEntity<ResponseDto<UserDto>>(userDtoJson, HttpStatus.OK);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<ResponseDto<UserDto>>(new ResponseDto<UserDto>(), HttpStatus.NOT_ACCEPTABLE);
		});
	}

}
