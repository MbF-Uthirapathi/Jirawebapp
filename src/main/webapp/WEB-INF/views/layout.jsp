<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="//code.jquery.com/jquery-2.2.0.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.3.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
	<link rel="stylesheet" type="text/css"
		href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css"></link>
		
	<!-- <link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css"> 
 <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script> --> 
<style>


/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
    padding-top: 60px;
}

/* Modal Content/Box */
.modal-content {
    background-color: #fefefe;
    margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
    border: 1px solid #888;
    width: 40%; /* Could be more or less, depending on screen size */
}

/* The Close Button (x) */
.close {
    position: absolute;
    right: 35px;
    top: 15px;
    color: #000;
    font-size: 40px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: red;
    cursor: pointer;
}

/* Clear floats */
.clearfix::after {
    content: "";
    clear: both;
    display: table;
}

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
    .cancelbtn, .signupbtn {
       width: 100%;
    }
}
html, body {
	overflow-x: hidden;
}

.counterCell:before {
	content: counter(tableCount);
	counter-increment: tableCount;
}

body {
	padding-top: 100px;
}

footer {
	padding: 30px 0;
}

@media screen and (max-width: 767px) {
	.row-offcanvas {
		position: relative;
		-webkit-transition: all .25s ease-out;
		-o-transition: all .25s ease-out;
		transition: all .25s ease-out;
	}
	.row-offcanvas-right {
		right: 0;
	}
	.row-offcanvas-left {
		left: 0;
	}
	.row-offcanvas-right
		  .sidebar-offcanvas {
		right: -50%; /* 6 columns */
	}
	.row-offcanvas-left
		  .sidebar-offcanvas {
		left: -50%; /* 6 columns */
	}
	.row-offcanvas-right.active {
		right: 50%; /* 6 columns */
	}
	.row-offcanvas-left.active {
		left: 50%; /* 6 columns */
	}
	.sidebar-offcanvas {
		position: absolute;
		top: 0;
		width: 50%; /* 6 columns */
	}
}

.select_join {
	width: 170px;
	height: 28px;
	overflow: hidden;
	background: url('http://s24.postimg.org/lyhytocf5/dropdown.png')
		no-repeat right #FEFEFE;
	border: #FEFEFE 1px solid;
	-webkit-border-radius: 3px;
	border-radius: 3px;
	-webkit-box-shadow: inset 0px 0px 10px 1px #FEFEFE;
	box-shadow: inset 0px 0px 10px 1px #FEFEFE;
}

.select_join select {
	background: transparent;
	width: 170px;
	font-size: 7pt;
	color: grey;
	border: 0;
	border-radius: 0;
	height: 28px;
	-webkit-appearance: none;
}

.select_join select:focus {
	outline: none;
}
</style>
<script>
	$("#btnExport").click(function(e) {
		/* window.open('data:application/vnd.ms-excel,' + $('#dvData').html());
		e.preventDefault(); */
		var a = document.createElement('a');
		//getting data from our div that contains the HTML table
		var data_type = 'data:application/vnd.ms-excel';
		var table_div = document.getElementById('dvData');
		var table_html = table_div.outerHTML.replace(/ /g, '%20');
		a.href = data_type + ', ' + table_html;
		//setting the file name
		a.download = 'download.xlsx';
		//triggering the function
		a.click();
		//just in case, prevent default behaviour
		e.preventDefault();
	});
	var modal = document.getElementById('id01');

	window.onclick = function(event) {
	    if (event.target == modal) {

	        modal.style.display = "none";
	    }

	}
	$(document).ready(function() {
		$('#example').DataTable({
			"pagingType" : "full_numbers"
		});
	});

</script>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">

		<div class="container">

			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<ul class="nav navbar-nav">
				<%-- <li><a href="${pageContext.request.contextPath}/frame">Project
							 </a></li> --%>
					<li id="project"><a href="${pageContext.request.contextPath}/project">Project
							Create </a></li>
					<%-- <li><a href="${pageContext.request.contextPath}/projectList">List Project
							</a></li>
					<li><a href="${pageContext.request.contextPath}/jiraCreate">Create
							New JiraUser</a></li>
					<li><a href="${pageContext.request.contextPath}/jiraList">Jira
							List Users</a></li>
					<li><a href="${pageContext.request.contextPath}/create">Create
							New User</a></li>
					<li><a href="${pageContext.request.contextPath}/list">List
							Users</a></li> --%>
					<li id="issue"><a href="${pageContext.request.contextPath}/issueJsp">Issue</a></li>
				</ul>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<form class="navbar-form navbar-left" action="search" method="get">
					<!-- <div class="form-group">
						<input type="text" placeholder="First or last name"
							name="searchstring" class="form-control">
					</div>
					<button type="submit" class="btn btn-success">Search</button> -->
				</form>
				<ul class="nav navbar-nav">
					<li id="logout"><a href="${pageContext.request.contextPath}/logout">logout</a></li>
				</ul>
			</div>
		</div>
	</nav>
	${param.body}
</body>
</html>