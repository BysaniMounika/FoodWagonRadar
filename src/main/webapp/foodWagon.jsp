<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<div class="container-fluid">
		<h1>${foodWagon.applicant}</h1>
		<p>${foodWagon.optionalText}</p>
		<div class="container-fluid">
			<div class="row">Day Open: ${foodWagon.dayOrder}</div>
			<div class="row">StartTime: ${foodWagon.startTime}</div>
			<div class="row">endTime: ${foodWagon.endTime}</div>
			<div class="row">Permit Number: ${foodWagon.permit}</div>
			<div class="row">Permit Location: ${foodWagon.permitLocation}</div>
			<div class="row">Location Description:
				${foodWagon.locationDescription}</div>
			<div class="row">IsColdtruck: ${foodWagon.coldTruck}</div>
			<div class="row">Block: ${foodWagon.block}</div>
			<div class="row">Lot: ${foodWagon.lot}</div>
			<!--  <img
				src="http://maps.googleapis.com/maps/api/staticmap?center=${foodWagon.latitude},${foodWagon.longitude}&zoom=11&size=200x200&sensor=false&key=AIzaSyCq_jrFlv4dQWZGxF35Q_4iS1WZU9T4PJI">-->
			<br> <br>
			<c:if test="${sessionScope.userId != null}">
				<c:if test="${!isFavorite}">
					<div class="btn-group">
						<button type="button" class="btn btn-primary"
							onclick=addToFavorite()>Add To Favorite</button>
					</div>
				</c:if>
				<div class="btn-group">
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#addRatingModal">Add Rating</button>
					<!-- The Modal -->
					<div class="modal" id="addRatingModal">
						<div class="modal-dialog">
							<div class="modal-content">

								<!-- Modal Header -->
								<div class="modal-header">
									<h4 class="modal-title">Add Rating</h4>
									<button type="button" class="close" data-dismiss="modal">&times;</button>
								</div>

								<!-- Modal body -->
								<div class="modal-body">
									<form method="POST"
										action="<%=request.getContextPath()%>/food-wagon/rating">
										<div class="form-group">
											<div class="form-check">
												<input class="form-check-input" id="rating-1" type="hidden"
													name="id" value="${foodWagon.id}">
											</div>
											<div class="form-check">
												<input class="form-check-input" id="rating-1" type="radio"
													name="rating" value="1"> <label
													class="form-check-label" for="rating-1"> Very Bad </label>
											</div>
											<div class="form-check">
												<input class="form-check-input" id="rating-2" type="radio"
													name="rating" value="2"> <label
													class="form-check-label" for="rating-2"> Poor </label>
											</div>
											<div class="form-check">
												<input class="form-check-input" id="rating-3" type="radio"
													name="rating" value="3"> <label
													class="form-check-label" for="rating-3"> OK </label>
											</div>
											<div class="form-check">
												<input class="form-check-input" id="rating-4" type="radio"
													name="rating" value="4"> <label
													class="form-check-label" for="rating-4"> Good </label>
											</div>
											<div class="form-check">
												<input class="form-check-input" id="rating-5" type="radio"
													name="rating" value="5"> <label
													class="form-check-label" for="rating-5"> Excellent
												</label>
											</div>
										</div>
										<div class="form-group">
											<button type="submit" class="btn btn-primary btn-block">Add</button>
										</div>
									</form>
								</div>

								<!-- Modal footer -->
								<div class="modal-footer">
									<button type="button" class="btn btn-danger"
										data-dismiss="modal">Close</button>
								</div>

							</div>
						</div>
					</div>

				</div>

				<div class="btn-group">
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#addReviewModal">Post Review</button>
					<div class="modal" id="addReviewModal">
						<div class="modal-dialog">
							<div class="modal-content">

								<!-- Modal Header -->
								<div class="modal-header">
									<h4 class="modal-title">Post Review</h4>
									<button type="button" class="close" data-dismiss="modal">&times;</button>
								</div>

								<!-- Modal body -->
								<div class="modal-body">
									<form method="POST"
										action="<%=request.getContextPath()%>/food-wagon/review">
										<div class="form-check">
											<input class="form-check-input" id="rating-1" type="hidden"
												name="id" value="${foodWagon.id}">
										</div>
										<div class="form-group">
											<textarea class="form-control" id="review" name="review"
												rows="4"></textarea>
										</div>
										<div class="form-group">
											<button type="submit" class="btn btn-primary btn-block">Post</button>
										</div>
									</form>
								</div>

								<!-- Modal footer -->
								<div class="modal-footer">
									<button type="button" class="btn btn-danger"
										data-dismiss="modal">Close</button>
								</div>

							</div>
						</div>
					</div>

				</div>
			</c:if>
			<h4>Rating Statistics</h4>
			<c:if test="${totalRatings == 0}">
				<p>Zero Ratings</p>
			</c:if>
			<c:if test="${totalRatings != 0}">
				<div>
					<span class="badge badge-danger"> Very Bad </span> <span
						class="badge badge-danger badge-pill"> ${ratingCounts[0]} </span>
				</div>
				<div>
					<span class="badge badge-warning"> Poor </span> <span
						class="badge badge-warning badge-pill"> ${ratingCounts[1]}
					</span>
				</div>
				<div>
					<span class="badge badge-info"> Ok </span> <span
						class="badge badge-info badge-pill"> ${ratingCounts[2]} </span>
				</div>
				<div>
					<span class="badge badge-primary"> Good </span> <span
						class="badge badge-primary badge-pill"> ${ratingCounts[3]}
					</span>
				</div>
				<div>
					<span class="badge badge-success"> Excellent </span> <span
						class="badge badge-success badge-pill"> ${ratingCounts[4]}
					</span>
				</div>
			</c:if>
			<br> <br>
			<h4>Reviews</h4>
			<c:if test="${totalReviews == 0}">
				<p>No Reviews</p>
			</c:if>
			<c:if test="${totalReviews != 0}">
				<ul class="list-group">
					<c:forEach var="r" items="${reviews}">
						<li
							class="list-group-item d-flex justify-content-between align-items-center">
							Review By: ${r.user.lastName} ${r.user.firstName} <br>
							Review: ${r.review}
						</li>
					</c:forEach>
				</ul>
			</c:if>
		</div>
	</div>
	<script>
		function addToFavorite() {
			const queryString = window.location.search;
			const urlParams = new URLSearchParams(queryString);
			let newUrl = window.location.href.split('?')[0] + "/add-to-favorite?" + urlParams.toString();
			console.log(newUrl);
			window.location.replace(newUrl);
		}
	</script>
</body>
</html>