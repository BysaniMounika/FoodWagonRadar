<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>Food Wagon Radar</title>
</head>
<body>
	<nav class="navbar bg-dark navbar-dark">
		<!-- Brand -->
		<a class="navbar-brand"
			href="<%=request.getContextPath()%>/food-wagons">Food Wagon Radar</a>

		<c:if test="${sessionScope.userId == null}">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/sign-in">SignIn</a></li>
			</ul>
		</c:if>
		<c:if test="${sessionScope.userId != null}">
			<!-- Toggler/collapsibe Button -->
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>

			<!-- Navbar links -->
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/food-wagons/favorites">My
							Favorites</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/logout">Logout</a></li>
				</ul>
			</div>
		</c:if>
	</nav>
	<div class="container mt-3">
		<h2>Food Wagons</h2>

		<div class="btn-toolbar justify-content-between" role="toolbar">
			<div class="input-group">
				<input id="serach-value" type="text" class="form-control"
					placeholder="SearchWord" value="${serachValue != null ? serachValue : ''}">
				<div class="input-group-append">
					<!-- <button class="btn btn-primary"
						onclick="location.href='<%=request.getContextPath()%>/food-wagons'">Search</button> -->
					<button class="btn btn-primary" onclick=serach()>Search</button>
				</div>
			</div>
			<div class="btn-group" role="group">
				<button id="sortByDropDown" type="button"
					class="btn btn-secondary dropdown-toggle" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false">SortBy</button>
				<div class="dropdown-menu" aria-labelledby="sortByDropDown">
					<a class="dropdown-item" href="#"
						onclick="reloadPage('sortBy', 'id')">Default</a> <a
						class="dropdown-item" href="#"
						onclick="reloadPage('sortBy', 'applicant')">Applicant</a> <a
						class="dropdown-item" href="#"
						onclick="reloadPage('sortBy', 'day_order')">DayOrder</a> <a
						class="dropdown-item" href="#"
						onclick="reloadPage('sortBy', 'average_rating')">Average
						Rating</a>
				</div>
			</div>
		</div>

		<ul class="list-group">
			<c:forEach var="foodWagon" items="${foodWagons}">
				<li
					class="list-group-item d-flex justify-content-between align-items-center"
					onclick="location.href='<%=request.getContextPath()%>/food-wagon?id=${foodWagon.id}';">
					Applicant: ${foodWagon.applicant} <br> DayOrder:
					${foodWagon.dayOrder} <br> StartTime: ${foodWagon.startTime} <br>
					EndTime: ${foodWagon.endTime} <br> AverageRating:
					${foodWagon.averageRating}
				</li>
			</c:forEach>
		</ul>


		<br> <br>


		<div class="btn-toolbar justify-content-between" role="toolbar">
			<c:if test="${page != 1}">
				<a href="<%=request.getContextPath()%>/food-wagons?page=${page - 1}"
					class="btn btn-primary" role="button">Previous</a>
			</c:if>
			<c:if test="${page lt pageCount}">
				<!-- <a href="<%=request.getContextPath()%>/food-wagons?page=${page + 1}"
					class="btn btn-primary" role="button">Next</a>
					-->
				<a href="#" onclick="reloadPage('page', ${page + 1})"
					class="btn btn-primary" role="button">Next</a>
			</c:if>
		</div>
	</div>
	<script>
		function serach() {
			SearchValueTextField = document.getElementById("serach-value");
			reloadPage("serachValue", SearchValueTextField.value);
		}
	
		function reloadPage(paramKey, paramValue) {
			const queryString = window.location.search;
			let urlParams = new URLSearchParams(queryString);
			
			if (paramKey == "page") {
				urlParams.set("page", paramValue);
			} else if (paramKey == "serachValue") {
				urlParams.set("serachValue", paramValue);
				urlParams.delete("page");
			} else if (paramKey == "sortBy") {
				urlParams.set("sortBy", paramValue);
				urlParams.delete("page");
			}
			
			let newUrl = window.location.href.split('?')[0] + "?" + urlParams.toString();
			console.log(newUrl);
			window.location.replace(newUrl);
		}
	</script>
</body>
</html>