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

function handleStatusRes() {
    if (document.getElementById('status').selectedIndex == 1) {
        document.getElementById('resolution').disabled = true;
        document.getElementById('resolution').required = false;
        document.getElementById('resolution').selectedIndex = 0;
    }
    if (document.getElementById('status').selectedIndex == 2) {
        document.getElementById('resolution').disabled = false;
        document.getElementById('resolution').required = true;
        document.getElementById('resolution').selectedIndex = 0;
    }
    if (document.getElementById('status').selectedIndex == 3) {
        document.getElementById('resolution').disabled = false;
        document.getElementById('resolution').required = true;
        document.getElementById('resolution').selectedIndex = 0;
    }
    if (document.getElementById('status').selectedIndex == 0) {
        document.getElementById('resolution').disabled = true;
        document.getElementById('resolution').required = false;
        document.getElementById('resolution').selectedIndex = 0;
    }
}

function handleStatusClose() {
    if (document.getElementById('status').selectedIndex == 0) {
        document.getElementById('submit').disabled = true;
        document.getElementById('summary').disabled = true;
        document.getElementById('description').disabled = true;
        document.getElementById('type').disabled = true;
        document.getElementById('type').selectedIndex = 0;
        document.getElementById('priority').disabled = true;
        document.getElementById('priority').selectedIndex = 0;
        document.getElementById('project').disabled = true;
        document.getElementById('project').selectedIndex = 0;
        document.getElementById(idToHide).style.visibility = 'collapse';
        document.getElementById('select' + idToHide).required = false;
        document.getElementById('select' + idToHide).selectedIndex = 0;
        document.getElementById('select' + idToHide).diabled = true;
    }
    if (document.getElementById('status').selectedIndex == 1) {
        document.getElementById('summary').disabled = false;
        document.getElementById('description').disabled = false;
        document.getElementById('type').disabled = false;
        document.getElementById('type').selectedIndex = 0;
        document.getElementById('priority').disabled = false;
        document.getElementById('priority').selectedIndex = 0;
        document.getElementById('project').disabled = false;
        document.getElementById('project').selectedIndex = 0;
        document.getElementById('submit').disabled = false;
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