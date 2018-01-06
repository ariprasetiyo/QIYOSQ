function _getNumberOfRow(offset, count) {
	return offset + 1 + count;
}

function _getAlert() {
	alert("Test")
}

function _getScreenDataTable() {
	return $(window).height() - 175;
}

function _enabledDisabledButton(idEnableButton, idDisableButton) {

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

function _idRole() {
	return $('select[name=roles_id]').val();
}
/**
 * Replace text
 * 
 * @param idTag
 * @param replaceTo
 * @returns
 */
function _replaceText(idTag, replaceTo) {
	$("" + idTag).text(replaceTo);
}

function _replaceId(idTag, replaceTo) {
	$("" + idTag).attr("id", replaceTo);
}

function _replaceClass(idTag, className, replaceTo) {
	$("" + idTag).removeClass(className);
	$("" + idTag).addClass(replaceTo);
}

/**
 * Function highlight check tr
 * 
 * @param thisVar
 * @returns
 */
function _checkHighlightTr(thisVar) {
	$(thisVar).closest("tr").addClass('checkTr');
}

function _startLoading() {
	ajaxindicatorstart('Please wait..');
	window.setTimeout(ajaxindicatorstart('Please wait..'), 500);
}

function _finishLoading() {
	timeout = setTimeout(ajaxindicatorstop(), 9000);
	clearTimeout(timeout);
}

function _jsonRequestListData(data) {
	var jsonRequest = {};
	var request = {};
	request["limit"] = data.length;
	request["offset"] = data.start;
	request["search"] = data.search.value;
	jsonRequest["requestData"] = request;
	return JSON.stringify(jsonRequest);
}

function _jsonRequestListData(data, id) {
	var jsonRequest = {};
	var request = {};
	request["id"] = id;
	request["limit"] = data.length;
	request["offset"] = data.start;
	request["search"] = data.search.value;
	jsonRequest["requestData"] = request;
	return JSON.stringify(jsonRequest);
}

function _jsonRequestDeleteData(id) {
	var jsonRequest = {};
	var request = {};
	request["id"] = id;
	jsonRequest["requestData"] = request;
	return JSON.stringify(jsonRequest);
}
