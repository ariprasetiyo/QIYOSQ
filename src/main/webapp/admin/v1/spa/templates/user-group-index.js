$(function() {
	var csrfToken = $('#csrfToken').val();
	_screenTabSize();

	$("#saveRole").validate({
		rules : {
			inputRoleName : {
				minlength : 8,
				required : true
			}
		}
	});

	// Get data from table
	// Edit data
	var idTmpUserGroupButtonEdit = null;
	var isEdit = false;
	$("#tableUser").on('click', '.editButton', function() {
		if (idTmpUserGroupButtonEdit != null) {
			_showModalMessage("Attention", "Couldn't edit before another process saved or cancelled");
			return;
		}

		$("#inputRoleInput").attr("disabled", "disabled");
		$("#cancelEdit").removeAttr("disabled");
		$(this).attr("disabled", "disabled");
		idTmpUserGroupButtonEdit = $(this).attr("id").replace("editAuth", "");
		var isActive = $("#disabled"+idTmpUserGroupButtonEdit).is(":checked");
	
		$("#checkBoxIsActive").prop("checked", isActive);
		
		getDataOnTable(this);
		isEdit = true;
	});

	// save or edit button
	$("#saveUserGroup").on('click', function() {
		var url;
		if (!isEdit) {
			url = '/admin/v1/api/userGroup/saveUserGroup';
		} else {
			url = '/admin/v1/api/userGroup/editUserGroup';
		}
		saveEditUser(idTmpUserGroupButtonEdit, url);
		resetTagHtml();
	});

	// cancel button
	$("#cancelEdit").on('click', function() {
		resetTagHtml();
		idTmpUserGroupButtonEdit = null;
	});

	function resetTagHtml() {
		$("#inputRoleInput").val("");
		$("#checkBoxIsActive").prop("checked", false);
		$("#cancelEdit").attr("disabled", "disabled");
		$("#inputRoleInput").removeAttr("disabled");
		$("#editAuth" + idTmpUserGroupButtonEdit).removeAttr("disabled");
		idTmpUserGroupButtonEdit = null;
		isEdit = false;
	}

	function jsonRequestDataEditSaver(id) {
		var roleName, isActive;
		roleName = $("#inputRoleInput").val();
		isActive = $("#checkBoxIsActive").is(':checked');

		var jsonRequest = {};
		var jsonData = {};

		jsonData["id"] = id;
		jsonData["roleName"] = roleName;
		jsonData["disabled"] = isActive;
		jsonRequest["requestData"] = jsonData;
		return JSON.stringify(jsonRequest);
	}

	function saveEditUser(id, url) {
		$.ajax({
			type : 'POST',
			url : url,
			contentType : 'application/json',
			data : jsonRequestDataEditSaver(id),
			headers : {
				'X-XSRF-TOKEN' : csrfToken
			},/*
				 * , data : { username : textUsername, name : textName, email :
				 * textEmail, noHp : textNoHp, roleId : selectRole, isActive :
				 * checkBoxIsActive, password : textPassword, id : id },
				 */
			datatype : 'json',
			success : function(data, textStatus, jqXHR) {
				var message = data.message;
				var messageInfo = data.statusType;
				if (message != undefined) {
					messageInfo += " " + message;
				}
				$("#infoSaveUser").text(messageInfo);
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

	function listDataTable(data, callback, settings) {
		$
				.ajax({
					async : true,
					type : 'POST',
					contentType : 'application/json',
					url : '/admin/v1/api/userGroup/list',
					headers : {
						'X-XSRF-TOKEN' : csrfToken
					},
					/*
					 * data : { limit : data.length, offset : data.start, search :
					 * data.search.value },
					 */
					data : _jsonRequestListData(data, _idRole()),
					dataType : "json",
					beforeSend : function() {

					},
					success : function(dataResponse, textStatus, jqXHR) {
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
									+ idUser
									+ '" class = "btn btn-primary editButton" > Edit </button> ';
						}

						for (var i = 0, ien = dataResponse.responseData.data.length; i < ien; i++) {
							idUser = dataResponse.responseData.data[i].id;
							out
									.push([
											_getNumberOfRow(data.start, i),
											dataResponse.responseData.data[i].createdTime,
											dataResponse.responseData.data[i].modifiedTime,
											dataResponse.responseData.data[i].roleName,
											_checkBoxCustom(
													idUser,
													dataResponse.responseData.data[i].disabled,
													"disabled"),
											buttonAction(i, idUser) ]);
						}

						setTimeout(
								function() {
									callback({
										draw : data.draw,
										data : out,
										recordsTotal : dataResponse.responseData.totalRecord,
										recordsFiltered : dataResponse.responseData.totalRecord
									});
								}, 50);
					},
					complete : function() {
					},
					error : function(jqXHR, textStatus, errorThrown) {
					}
				});
	}

	// dataTables ajax logic
	var tableUserr = $('#tableUser').DataTable({
		/*
		 * l - Length changing f - Filtering input t - The table! i -
		 * Information p - Pagination r - pRocessing < and > - div elements
		 * <"class" and > - div with a class Examples: <"wrapper"flipt>, <lf<t>ip>
		 */
		"sDom" : '<"top"fl>rt<"bottom"p><"clear">',
		serverSide : true,
		ordering : false,
		searching : true,
		ajax : function(data, callback, settings) {
			listDataTable(data, callback, settings);
		},
		scrollY : _getScreenDataTableTab(),
		scroller : {
			loadingIndicator : true
		}
	});

	$("#submitAction").on('click', function() {
		tableUserr.ajax.reload();
	});

	/*
	 * $('#tableUser').on('search.dt', function() { var value =
	 * $('.dataTables_filter input').val(); });
	 */

	var idUserForDelete = null;
	$("#tableUser").on(
			"click",
			".deleteButton",
			function() {
				// get value table with spesific tr and td
				// var val1 =$(t).find('tr:eq(2) td:eq(4)').text();
				var idButtonDelete = $(this).attr("id").replace("deleteAuth",
						"").trim();
				var userName;
				// loop the column of per row
				var $row = $(this).closest("tr");
				var $tds = $row.find("td");
				var loopColumn = 1;
				$.each($tds, function() {
					if (loopColumn === 6) {
						userName = $(this).text();
						return false;
					}
					loopColumn += 1;
				});
				idUserForDelete = $(".idDataHide" + idButtonDelete).attr("id");
				idUserForDelete = idUserForDelete.replace("idData", "").trim();
				$("#modalDeleteUser").modal('show');
				$("#messageDelete").text(
						"Are you sure delete this data username " + userName
								+ " ?");
			});

	$("#deleteUser").on("click", function() {
		if (idUserForDelete !== null) {
			deleteDataUser(idUserForDelete);
		}
		idUserForDelete = null;
	});

	function deleteDataUser(idMenu) {
		$.ajax({
			type : "DELETE",
			contentType : "application/json",
			url : "/admin/v1/api/user/delete",
			headers : {
				'X-XSRF-TOKEN' : csrfToken
			},
			data : _jsonRequestDeleteData(idMenu),
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

	function getDataOnTable(varThis) {

		// find the row
		var $row = $(varThis).closest("tr");
		var $tds = $row.find("td");
		var loopColumn = 1;

		// loop the column of per row
		$.each($tds, function() {
			 if (loopColumn === 4) {
				$("#inputRoleInput").val($(this).text());
			}
			loopColumn += 1;
		});
	}
});
