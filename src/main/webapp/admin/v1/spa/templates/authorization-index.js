$(function() {
	$("#submitRoles").on("click", function() {
		alert($('select[name=roles_id]').val());
		viewAuthorizationList($('select[name=roles_id]').val());
	});

	$("#tableAuthorizationa").DataTable({
		"sDom" : '<"top"fl>rt<"bottom"p><"clear">',
		"iDisplayLength" : 25,
		scrollY : 360,
		"scrollX" : "100%",
		"columnDefs" : [ {
			"width" : "130px",
			"targets" : 8
		} ],
		scroller : {
			loadingIndicator : true
		}
	});

	if (roleForAdd != null) {
		$("#idRoleAdd").val(roleForAdd);
	}

	$("#saveModalAddMenu").on(
			"click",
			function() {
				addDataMenu(roleIdForAdd, $('#modalIsCreate').is(':checked'),
						$('#modalIsUpdate').is(':checked'), $('#modalIsDelete')
								.is(':checked'), $('#modalIsDisabled').is(
								':checked'), $('#modalMenuid').val(), $(
								'#modalParentMenuid').val());
			});
	$(".select2").select2();

	// logic button if load page for DOM
	$('#tableAuthorization').on('click', '.deleteButton', function() {
		// $(".deleteButton").on("click", function () {
		var id = $(this).attr('id').replace('deleteAuth', '');
		deleteDataMenu($("#idTAU" + id).val());
		isEnableTriggerButtonSaveAndDelete(id);
	});

	$('#tableAuthorization').on(
			'click',
			'.editButton',
			function() {
				// $(".editButton").on("click", function () {

				var idAuth = $(this).attr('id');
				var id = idAuth.replace("editAuth", "");
				var enabledButton = [ "#isInsert" + id, "#isDelete" + id,
						"#isUpdate" + id, "#disabled" + id, "#saveAuth" + id,
						"#deleteAuth" + id ];
				var disableButton = [ "#editAuth" + id ];
				enabledDisabledButton(enabledButton, disableButton);
			});
	$('#tableAuthorization').on(
			'click',
			'.saveButton',
			function() {
				// $(".saveButton").on("click", function () {
				var idSaveAuth = $(this).attr('id');
				var id = idSaveAuth.replace('saveAuth', '');
				var vInsert, vDelete, vUpdate, vDisable, idTAU;
				idTAU = $("#idTAU" + id).val();
				editData(idTAU, $("#isInsert" + id).is(':checked'), $(
						"#isDelete" + id).is(':checked'), $("#isUpdate" + id)
						.is(':checked'), $("#disabled" + id).is(':checked'));
				isEnableTriggerButtonSaveAndDelete(id);
			});

	function getLastNumberDataTables() {
		var table = $('#tableAuthorization').DataTable();
		var xx = table.row(':last').data();
		var result = xx[0].toString().replace('<div class="center">', '')
				.replace('</div>', '');
		return result;
	}
	function isEnableTriggerButtonSaveAndDelete(id) {
		var disableButton = [ "#isInsert" + id, "#isDelete" + id,
				"#isUpdate" + id, "#disabled" + id, "#saveAuth" + id,
				"#deleteAuth" + id ];
		var enabledButton = [ "#editAuth" + id ];
		enabledDisabledButton(enabledButton, disableButton);
	}

	function enabledDisabledButton(idEnableButton, idDisableButton) {

		if (idEnableButton !== null) {
			for (var b = 0; b < idEnableButton.length; b++) {
				$(idEnableButton[b]).removeAttr("disabled");
			}
		}

		if (idDisableButton !== null) {
			for (var a = 0; a < idDisableButton.length; a++) {
				$(idDisableButton[a]).attr("disabled", true);
			}
		}
	}

	function deleteDataMenu(idMenu) {
		$.ajax({
			type : "POST",
			url : "api/authorization/deleteMenu/" + idMenu,
			headers : {
				'X-XSRF-TOKEN' : $("#csrf").val()
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
				return false;
			}
		});
	}
	viewAuthorizationList(1);

	function viewAuthorizationList(idRole) {
		// dataTables ajax logic
		$('#tableAuthorization')
				.DataTable(
						{
							/*
							 * l - Length changing f - Filtering input t - The
							 * table! i - Information p - Pagination r -
							 * pRocessing < and > - div elements <"class" and > -
							 * div with a class Examples: <"wrapper"flipt>, <lf<t>ip>
							 */
							"sDom" : '<"top"fl>rt<"bottom"p><"clear">',
							serverSide : true,
							ordering : false,
							searching : false,
							ajax : function(data, callback, settings) {
								$
										.ajax({
											type : "POST",
											url : "../api/authorization/list/"
													+ idRole,
											headers : {
												'X-XSRF-TOKEN' : $("#csrf")
														.val()
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
												for (var i = 0, ien = dataResponse.length; i < ien; i++) {
													idUser = dataResponse[i].id;
													out
															.push([
																	i + 1,
																	dataResponse[i].menuName,
																	dataResponse[i].createdTime,
																	dataResponse[i].modifiedTime,
																	dataResponse[i].isInsert,
																	dataResponse[i].isUpdate,
																	dataResponse[i].isDelete,
																	dataResponse[i].disabled,
																	buttonAction(
																			i,
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
							scrollY : 360,
							scroller : {
								loadingIndicator : true
							}
						});
	}

	/*
	 * function viewAuthorizationList(idRole){ $.ajax({ type: "POST", url:
	 * "api/authorization/list/"+idRole, headers: {'X-XSRF-TOKEN':
	 * $("#csrf").val()}, dataType: "json", success: function (dataResponse,
	 * callback, jqXHR) { var out = []; var idUser = null;
	 * 
	 * function buttonAction(i, idUser) { return '<input type = "hidden" name =
	 * "${_csrf.parameterName}" value = "${_csrf.token}" />' + '<input type =
	 * "hidden" id = "idData' + idUser + '" class="idDataHide' + i + '" /> ' + '<button
	 * type = "submit" id = "editAuth' + i + '" class = "btn btn-primary
	 * editButton" > Edit </button> ' + '<button type = "submit" id =
	 * "deleteAuth' + i + '" class = "btn btn-primary deleteButton" > Delete
	 * </button>'; }
	 * 
	 * for (var i = 0, ien = dataResponse.length; i < ien; i++) { idUser =
	 * dataResponse[i].id; out.push([i + 1, dataResponse[i].menuName,
	 * dataResponse[i].createdTime, dataResponse[i].modifiedTime,
	 * dataResponse[i].isInsert, dataResponse[i].isUpdate,
	 * dataResponse[i].isDelete, dataResponse[i].isDisabled, buttonAction(i,
	 * idUser) ]); }
	 * 
	 * setTimeout(function () { callback({ draw: data.draw, data: out,
	 * recordsTotal: dataResponse.totalRecord, recordsFiltered:
	 * dataResponse.totalRecord }); }, 50); } }); }
	 */

	function addDataMenu(id, vInsert, vDelete, vUpdate, vDisable, menuId,
			parentId) {
		var csrfToken = $("#csrf").val();
		if ((parentId == '') || (parentId == '-')) {
			parentId = null;
		}
		// alert(parentId);
		$
				.ajax({
					type : "POST",
					url : "api/authorization/addMenu/" + id,
					headers : {
						'X-XSRF-TOKEN' : csrfToken
					},
					data : {
						modelMenuId : menuId,
						modelParentMenuId : parentId,
						parentId : parentId,
						vInsert : vInsert,
						vDelete : vDelete,
						vUpdate : vUpdate,
						vDisable : vDisable
					},
					dataType : "json",
					beforeSend : function() {
						ajaxindicatorstart('loading data.. please wait..');
						window
								.setTimeout(
										ajaxindicatorstart('loading data.. please wait..'),
										500);
					},
					success : function(data, textStatus, jqXHR) {

						// $('#tableAuthorization').add("<tr><td>sasa</td></tr>");
						var numberRow = getLastNumberDataTables();
						var table = $("#tableAuthorization").DataTable();
						var rowNode = table.row
								.add(
										[
												function() {
													return '<div class="center">'
															+ (parseInt(numberRow) + 1)
															+ '</div>';
												},
												data.menuName,
												function() {
													return '<div class="center">'
															+ data.createTime
															+ '</div>';
												},
												function() {
													return '<div class="center">'
															+ data.modifyTime
															+ '</div>';
												},
												function() {
													return '<div class="center" ><input type="checkbox"  id="isInsert'
															+ numberRow
															+ '" disabled checked>'
															+ ' <label for="isInsert'
															+ numberRow
															+ '"></label>   </div>';
												},
												function() {
													return '<div class="center" ><input type="checkbox"  id="isUpdate'
															+ numberRow
															+ '" disabled checked>'
															+ ' <label for="isUpdate'
															+ numberRow
															+ '"></label>   </div>';
												},
												function() {
													return '<div class="center" ><input type="checkbox"  id="isDelete'
															+ numberRow
															+ '" disabled checked>'
															+ ' <label for="isDelete'
															+ numberRow
															+ '"></label>   </div>';
												},
												function() {
													return '<div class="center" ><input type="checkbox"  id="disabled'
															+ numberRow
															+ '" disabled checked>'
															+ ' <label for="disabled'
															+ numberRow
															+ '"></label>   </div>';
												},
												function() {
													return '<div style="white-space: nowrap;"><input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}" />'
															+ '<input type = "hidden" id = "idTAU'
															+ numberRow
															+ '" value = "'
															+ data.id
															+ '" />'
															+ '<button type = "submit" id = "editAuth'
															+ numberRow
															+ '" class = "btn btn-primary editButton" > Edit </button>'
															+ ' <button type = "submit" id = "saveAuth'
															+ numberRow
															+ '" class = "btn btn-primary saveButton" disabled > Save </button>'
															+ ' <button type = "submit" id = "deleteAuth'
															+ numberRow
															+ '" class = "btn btn-primary deleteButton" disabled > Delete </button></div>';
												} ]).draw().node();
						$(rowNode).css('color', 'red').animate({
							color : 'black'
						});
					},
					complete : function() {
						timeout = setTimeout(ajaxindicatorstop(), 9000);
						clearTimeout(timeout);
					},
					error : function(jqXHR, textStatus, errorThrown) {
					}
				});
	}

	function editData(id, vInsert, vDelete, vUpdate, vDisable) {
		var csrfToken = $("#csrf").val();
		$
				.ajax({
					type : "POST",
					url : "api/authorization/update/" + id,
					headers : {
						'X-XSRF-TOKEN' : csrfToken
					},
					data : {
						vInsert : vInsert,
						vDelete : vDelete,
						vUpdate : vUpdate,
						vDisable : vDisable
					},
					dataType : "json",
					beforeSend : function() {
						ajaxindicatorstart('loading data.. please wait..');
						window
								.setTimeout(
										ajaxindicatorstart('loading data.. please wait..'),
										500);
					},
					success : function(data, textStatus, jqXHR) {

					},
					complete : function() {
						timeout = setTimeout(ajaxindicatorstop(), 9000);
						clearTimeout(timeout);
					},
					error : function(jqXHR, textStatus, errorThrown) {
						// window.location.href =
						// "http://localhost:8080/sitepat-satelit/";
						// window.location.replace("logout.ari");
					}
				});
	}
});