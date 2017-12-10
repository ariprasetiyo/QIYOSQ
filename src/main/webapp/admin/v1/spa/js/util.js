function getNumberOfRow(offset, count) {
	return offset + 1 + count;
}

function getAlert() {
	alert("Test")
}

function getScreenDataTable() {
	return $(window).height() - 200;
}

/*initDataTable();
function initDataTable() {
	$('#tableUser').DataTable({
		//responsive : true,
		ordering : false,
		searching : true,
		scrollY : getScreenDataTable(),
		scroller : {
			loadingIndicator : true
		}
	});
}*/
