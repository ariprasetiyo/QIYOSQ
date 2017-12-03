$(function() {
	var csrfToken = $('#csrfToken').val();

	$(".role-select2-multiple").select2({
		width : 75 + "%",
		maximumSelectionLength : 3
	});

	// http://www.texotela.co.uk/code/jquery/numeric/
	$("#TextNoHp").numeric();
	$("#adduser").validate({
		rules : {
			TextOldPassword : {
				minlength : 8
			},
			TextNewPassword : {
				minlength : 8
			},
			TextVerifyPassword : {
				minlength : 8,
				equalTo : "#TextNewPassword"
			},
			TextEmail : {
				required : true,
				email : true
			},
			TextUsername : {
				required : true
			},
			TextName : {
				required : true
			},
			TextNoHp : {
				required : true,
				minlength : 10,
				maxlength : 13
			},
			SelectRole : {
				required : true
			}
		}
	});

	// edit user button
	$("#editUser").on('click', function() {
		var id = $("#idUserNya").val();
		var url = '/admin/v1/api/user/editUser';
		saveEditUser(id, url);
	});

	// save user button
	$("#saveModalAddMenu").on('click', function() {
		var url = '/admin/v1/api/user/saveUser';
		saveEditUser(null, url);
	});

	function saveEditUser(id, url, message) {
		var textUsername, textName, textEmail, textNoHp, selectRole, checkBoxIsActive, textOldPassword, textNewPassword, idUSerNya;
		textUsername = $("#TextUsername").val();
		textName = $("#TextName").val();
		textEmail = $("#TextEmail").val();
		textNoHp = $("#TextNoHp").val();
		selectRole = $("#SelectRole").select2('data');
		var textSelectRole = $("#SelectRole").text();
		textNewPassword = $("#TextNewPassword").val();
		textOldPassword = $("#TextOldPassword").val();
		checkBoxIsActive = $("#CheckBoxIsActive").is(':checked');

		var jsonData = {};
		var jsonObj = [];
		for (var a = 0; a < selectRole.length; a++) {
			item = {}
			item["id"] = selectRole[a].id;
			item["roleName"] = selectRole[a].text;
			jsonObj.push(item);

		}
		jsonData["username"] = textUsername;
		jsonData["name"] = textName;
		jsonData["email"] = textEmail;
		jsonData["noHp"] = textNoHp;
		jsonData["roles"] = jsonObj;
		jsonData["isActive"] = checkBoxIsActive;
		jsonData["oldPassword"] = textOldPassword;
		jsonData["password"] = textNewPassword;
		jsonData["id"] = id;
		var jsonDataResult = JSON.stringify(jsonData);
		console.log(jsonDataResult);

		$.ajax({
			type : 'POST',
			url : url,
			contentType : 'application/json',
			data : jsonDataResult,
			headers : {
				'X-XSRF-TOKEN' : csrfToken
			},/*
				 * , data : { username : textUsername, name : textName, email :
				 * textEmail, noHp : textNoHp, roleId : selectRole, isActive :
				 * checkBoxIsActive, password : textPassword, id : id },
				 */
			datatype : 'json',
			success : function(data, textStatus, jqXHR) {
				removeModalInputUser();
				$("#infoSaveUser").text(data.statusType);
				$("#infoSaveUser").attr('class', 'success-message');
				$('#tableUser').DataTable().ajax.reload();
			},
			complete : function() {
			},
			error : function(jqXHR, textStatus, errorThrown) {
				var objJson = JSON.parse(jqXHR.responseText);
				var errorMessage = "";
				for (a = 0; a < objJson.fieldErrors.length; a++) {
					errorMessage += objJson.fieldErrors[a].objectName;
					errorMessage += " : ";
					errorMessage += objJson.fieldErrors[a].defaultMessage;
					if ((objJson.fieldErrors.length - 1) == a) {
						errorMessage += ".";
						continue;
					}
					errorMessage += ", ";
				}
				$("#infoSaveUser").attr('class', 'warning-message')
				$("#infoSaveUser").text(errorMessage);
			}
		});
	}

	function removeModalInputUser() {
		$("#TextUsername").val("");
		$("#TextName").val("");
		$("#TextEmail").val("");
		$("#TextNoHp").val("");
		$("#SelectRole").val("");
		$("#TextPassword").val("");
		$("#CheckBoxIsActive").is(':unchecked');
	}
	$(".buttonClose").on("click", function() {
		$("#infoSaveUser").attr('class', '').text("");
	});

	// dataTables ajax logic
	$('#tableUser')
			.DataTable(
					{
						/*
						 * l - Length changing f - Filtering input t - The
						 * table! i - Information p - Pagination r - pRocessing <
						 * and > - div elements <"class" and > - div with a
						 * class Examples: <"wrapper"flipt>, <lf<t>ip>
						 */
						"sDom" : '<"top"fl>rt<"bottom"p><"clear">',
						serverSide : true,
						ordering : false,
						searching : true,
						ajax : function(data, callback, settings) {
							$
									.ajax({
										async : true,
										type : 'POST',
										url : '/admin/v1/api/user/list',
										headers : {
											'X-XSRF-TOKEN' : csrfToken
										},
										data : {
											limit : data.length,
											offset : data.start,
											search : data.search.value
										},
										dataType : "json",
										beforeSend : function() {

										},
										success : function(dataResponse,
												textStatus, jqXHR) {
											var out = [];
											var idUser = null;

											function buttonAction(i, idUser) {
												return '<input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}" />'
														+ '<input type = "hidden" id = "idData'
														+ idUser
														+ '" class="idDataHide'
														+ i
														+ '" /> '
														+ '<button type = "submit" id = "editAuth'
														+ i
														+ '" class = "btn btn-primary editButton" > Edit </button> '
														+ '<button type = "submit" id = "deleteAuth'
														+ i
														+ '" class = "btn btn-primary deleteButton" > Delete </button>';
											}

											for (var i = 0, ien = dataResponse.listSysUserDto.length; i < ien; i++) {
												idUser = dataResponse.listSysUserDto[i].id;
												out
														.push([
																getNumberOfRow(
																		data.start,
																		i),
																dataResponse.listSysUserDto[i].id,
																dataResponse.listSysUserDto[i].createdTime,
																dataResponse.listSysUserDto[i].modifiedTime,
																dataResponse.listSysUserDto[i].username,
																dataResponse.listSysUserDto[i].name,
																dataResponse.listSysUserDto[i].email,
																dataResponse.listSysUserDto[i].noHp,
																rolesName(dataResponse.listSysUserDto[i].roles),
																dataResponse.listSysUserDto[i].isActive,
																buttonAction(i,
																		idUser) ]);
											}

											setTimeout(
													function() {
														callback({
															draw : data.draw,
															data : out,
															recordsTotal : dataResponse.totalRecord,
															recordsFiltered : dataResponse.totalRecord
														});
													}, 50);
										},
										complete : function() {
										},
										error : function(jqXHR, textStatus,
												errorThrown) {
										}
									});
						},
						scrollY : getScreenDataTable(),
						scroller : {
							loadingIndicator : true
						}
					});

	$('#tableUser').on('search.dt', function() {
		var value = $('.dataTables_filter input').val();
	});

	var idUserForDelete = null;
	$("#tableUser").on(
			"click",
			".deleteButton",
			function() {
				// get value table with spesific tr and td
				// var val1 =$(t).find('tr:eq(2) td:eq(4)').text();
				var idButtonDelete = $(this).attr("id").replace("deleteAuth",
						"").trim();
				idUserForDelete = $(".idDataHide" + idButtonDelete).attr("id");
				idUserForDelete = idUserForDelete.replace("idData", "").trim();
				$("#modalDeleteUser").modal('show');
				// idData
			});

	$("#deleteUser").on("click", function() {
		if (idUserForDelete !== null) {
			deleteDataUser(idUserForDelete);
		}
		idUserForDelete = null;
	});

	function rolesName(roles) {
		var rolesNameTmp = "";
		for (a = 0; a < roles.length; a++) {
			rolesNameTmp += roles[a].roleName;
			if (a == (roles.length - 1)) {
				continue;
			}
			rolesNameTmp += ", ";
		}
		return rolesNameTmp;
	}

	function deleteDataUser(idMenu) {
		$.ajax({
			type : "DELETE",
			url : "/admin/v1/api/user/delete" + idMenu,
			headers : {
				'X-XSRF-TOKEN' : csrfToken
			},
			dataType : "json",
			success : function(data, textStatus, jqXHR) {
				// alert(data.id);
				// $('rowId'+data.id).remove();
				var tr = $('#rowId' + data.id).closest('tr');
				tr.css("background-color", "#00acd6");
				tr.fadeOut(400, function() {
					tr.remove();
				});
				$("#modalDeleteUser").modal('hide');
				$('#tableUser').DataTable().ajax.reload();
				return false;
			}
		});
	}

	getRoleList();
	function getRoleList() {
		$.get("/admin/v1/api/role/list", function(data) {
			for (var a = 0; a < data.length; a++) {
				$("#SelectRole").append(
						"<option value=" + data[a].id + ">" + data[a].roleName
								+ "</option>");
			}
		});
	}

	// Add new user
	$("#addNewUser").on('click', function() {
		$("#formInputUser").text("Add new user");
		$("#oldPassword").attr("hidden", "hidden");
		$("#TextEmail").removeAttr("disabled");
		$("#TextUsername").removeAttr("disabled");
		$("#editUser").attr("disabled", "disabled");
		$("#saveModalAddMenu").removeAttr("disabled");
	});

	// Get data from table
	// Edit data
	$("#tableUser").on('click', '.editButton', function() {
		$("#formInputUser").text("Edit user");
		$("#oldPassword").removeAttr("hidden");
		$("#TextEmail").attr("disabled", "disabled");
		$("#TextUsername").attr("disabled", "disabled");
		$("#editUser").removeAttr("disabled");
		$("#saveModalAddMenu").attr("disabled", "disabled");
		getDataOnTable(this);
	});

	function getDataOnTable(varThis) {
		var idEditButton = $(varThis).attr('id');
		// disable button edit
		// $('#' + idEditButton).attr("disabled", true);
		var id = idEditButton.replace("editAuth", "");

		var textUserName, textName, textEmail, textNoHp, textSelectRole, textPassword, CheckBoxIsActive;

		// find the row
		var $row = $(varThis).closest("tr");
		var $tds = $row.find("td");
		var loopColumn = 1;

		// loop the column of per row
		$.each($tds, function() {
			if (loopColumn === 2) {
				$("#idUserNya").val($(this).text());
			}
			if (loopColumn === 5) {
				$("#TextUsername").val($(this).text());
			} else if (loopColumn === 6) {
				$("#TextName").val($(this).text());
			} else if (loopColumn === 7) {
				$("#TextEmail").val($(this).text());
			} else if (loopColumn === 8) {
				$("#TextNoHp").val($(this).text());
			} else if (loopColumn === 9) {
				// begin1 : get authorization and set to select2

				// split data from table, example admin, approval, public
				var test = $(this).text();
				var splitData = test.split(",");

				var mapSelect2 = {};
				for (var a = 0; a < splitData.length; a++) {
					mapSelect2[splitData[a]] = splitData[a];
				}

				var arrayTempVal = [];
				$('select#SelectRole').find('option').each(function() {
					var intNumberSelect2 = parseInt($(this).val()) - 1;
					var key;
					for (key in mapSelect2) {
						if ($(this).text().trim() === key.trim()) {
							/*
							 * get number for to set select2 select2 just
							 * recieve val,not text
							 */
							arrayTempVal.push($(this).val());
						}
					}
				});
				$("#SelectRole").val(arrayTempVal).trigger('change');
				// end1
			} else if (loopColumn === 10) {
				if ($(this).text().trim() === 'true') {
					$("#CheckBoxIsActive").prop("checked", true);
				} else {
					$("#CheckBoxIsActive").prop('checked', false);
				}
			}
			// Password not show
			$("#TextNewPassword").val("");
			loopColumn += 1;
		});
		enableForm(id);
	}

	function enableForm(id) {
		$('#myModal').modal('show');
	}
});
