package arprast.qiyosq.controller.rest;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import arprast.qiyosq.dto.AuthorizationDto;
import arprast.qiyosq.dto.RequestData;
import arprast.qiyosq.dto.RequestDto;
import arprast.qiyosq.dto.ResponseData;
import arprast.qiyosq.dto.ResponseDto;
import arprast.qiyosq.services.AuthorizationService;

@RestController
@RequestMapping("/admin/v1/api/authorization")
public class AuthorizationRestController {

	@Autowired
	AuthorizationService authorizationService;

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public ResponseEntity<ResponseDto> authorizationUpdate(@PathVariable("id") Long id,
			@RequestBody RequestDto<AuthorizationDto> requestDto) {

		int inUpdate = authorizationService.updateAuthorization(id, requestDto.getRequestData());

		ResponseDto responseDto = new ResponseDto();
		ResponseData responseData = new ResponseData();
		responseData.setId(id);
		responseData.setTotalRecord(inUpdate);
		responseDto.setResponseData(responseData);

		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value = "/addMenu/{idRoles}", method =
	 * RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes
	 * = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE })
	 * public AuthorizationDto authorizationAddMenu(@PathVariable("idRoles")
	 * Long idRole,
	 * 
	 * @RequestParam("vInsert") boolean vInsert, @RequestParam("vUpdate")
	 * boolean vUpdate,
	 * 
	 * @RequestParam("vDelete") boolean vDelete, @RequestParam("vDisable")
	 * boolean vDisable,
	 * 
	 * @RequestParam("modelMenuId") Long
	 * MenuId, @RequestParam("modelParentMenuId") Long parentMenuId) { return
	 * authorizationService.saveDataMenu(idRole, vInsert, vUpdate, vDelete,
	 * vDisable, MenuId, parentMenuId); }
	 */

	@RequestMapping(value = "/addMenu", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public AuthorizationDto authorizationSave(@RequestBody RequestDto<AuthorizationDto> requestDto) {
		AuthorizationDto authorizationDto = new AuthorizationDto();
		authorizationDto = requestDto.getRequestData();
		return authorizationService.saveMenu(authorizationDto);
	}

	/*
	 * @RequestMapping(value = "/list/{idRole}", method = RequestMethod.POST,
	 * consumes = { MediaType.APPLICATION_JSON_VALUE,
	 * MediaType.APPLICATION_XML_VALUE }, produces = {
	 * MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	 * public ResponseEntity<ResponseDto<ResponseData>>
	 * authorizationList(@PathVariable("idRole") Long idRole,
	 * 
	 * @RequestBody RequestDto<RequestData> requestDto) {
	 * 
	 * ResponseDto<ResponseData> responseDto = new ResponseDto<ResponseData>();
	 * ResponseData responseData = new ResponseData();
	 * 
	 * ExecutorService executorService = Executors.newSingleThreadExecutor();
	 * executorService.submit(() -> {
	 * responseData.setTotalRecord(authorizationService.countAuthorization());
	 * });
	 * 
	 * executorService.submit(() -> {
	 * System.out.println("mulai---------------"); List<AuthorizationDto>
	 * authorizationDto =
	 * authorizationService.getAuthorizationList(requestDto.getRequestData());
	 * responseData.setJsonMessage(authorizationDto);
	 * System.out.println(responseData.toString()); });
	 * 
	 * try { executorService.awaitTermination(3, TimeUnit.SECONDS); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * responseDto.setResponseData(responseData); return new
	 * ResponseEntity<ResponseDto<ResponseData>>(responseDto, HttpStatus.OK); }
	 */

	@RequestMapping(value = "/list/{idRole}", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseDto> authorizationList(@PathVariable("idRole") Long idRole,
			@RequestBody RequestDto<RequestData> requestDto) {

		ResponseDto responseDto = new ResponseDto();
		ResponseData responseData = new ResponseData();

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.submit(() -> {
			responseData.setTotalRecord(authorizationService.countAuthorization(requestDto.getRequestData().getId()));
		});

		List<AuthorizationDto> authorizationList = authorizationService
				.getAuthorizationList(requestDto.getRequestData());
		responseData.setData(authorizationList);

		responseDto.setResponseData(responseData);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteMenu/{idAuthorization}", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ResponseDto> authorizationDelete(@PathVariable("idAuthorization") Long id,
			@RequestBody RequestDto<AuthorizationDto> requestDto) {

		authorizationService.deleteAuthorization(requestDto.getRequestData().getId());
		ResponseDto responseDto = new ResponseDto();
		ResponseData responseData = new ResponseData();
		responseData.setId(id);
		responseDto.setResponseData(responseData);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
	}

}
