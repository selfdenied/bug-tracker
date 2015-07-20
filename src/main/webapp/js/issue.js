function handleStatus() {
    if (document.getElementById('status').selectedIndex == 1) {
        document.getElementById('assignee').disabled = true;
        document.getElementById('assignee').required = false;
        document.getElementById('assignee').selectedIndex = 0;
    }
    if (document.getElementById('status').selectedIndex == 2) {
        document.getElementById('assignee').disabled = false;
        document.getElementById('assignee').required = true;
    }
    if (document.getElementById('status').selectedIndex == 0) {
        document.getElementById('assignee').disabled = true;
        document.getElementById('assignee').required = false;
        document.getElementById('assignee').selectedIndex = 0;
    }
}

var idToHide;

function handleProject(id) {
	if (typeof idToHide != 'undefined') {
	document.getElementById(idToHide).style.visibility = 'collapse';
	document.getElementById('select' + idToHide).required = false;
	document.getElementById('select' + idToHide).selectedIndex = 0;
	document.getElementById('select' + idToHide).disabled = true;
	}
	idToHide = id;
	document.getElementById(id).style.visibility = 'visible';
	document.getElementById('select' + id).disabled = false;
	document.getElementById('select' + id).required = true;
}

function hideBuild() {
	if (typeof idToHide != 'undefined') {
		if (document.getElementById('project').selectedIndex == 0) {
			document.getElementById(idToHide).style.visibility = 'collapse';
			document.getElementById('select' + idToHide).required = false;
			document.getElementById('select' + idToHide).selectedIndex = 0;
			document.getElementById('select' + idToHide).disabled = true;
		}
	}
}