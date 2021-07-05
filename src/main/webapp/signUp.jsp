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
	<div class="container">
		<div class="row h-100">
			<div class="col-sm-12 my-auto">
				<div class="card mx-auto" style="width: 400px">
					<article class="card-body">
						<h4 class="card-title mb-4 mt-1">Sign Up</h4>
						<form method="POST" action="<%=request.getContextPath()%>/sign-up">
							<div class="form-group">
								<label>Email</label> <input name="email" class="form-control"
									type="email">
							</div>

							<div class="form-group">
								<label>FirstName</label> <input name="firstName"
									class="form-control" type="text">
							</div>

							<div class="form-group">
								<label>Last Name</label> <input name="lastName"
									class="form-control" type="text">
							</div>

							<div class="form-group">
								<label>Gender</label>
								<div class="form-check">
									<input class="form-check-input" id="gender-male" type="radio"
										name="gender" value="0"> <label
										class="form-check-label" for="gender-male"> Male </label>
								</div>
								<div class="form-check">
									<input class="form-check-input" id="gender-female" type="radio"
										name="gender" value="1"> <label
										class="form-check-label" for="gender-female"> Female </label>
								</div>
							</div>

							<div class="form-group">
								<label>Your password</label> <input class="form-control"
									type="password" name="password">
							</div>

							<div class="form-group">
								<button type="submit" class="btn btn-primary btn-block">SignUp</button>
							</div>
						</form>
					</article>
				</div>
			</div>
		</div>
	</div>
</body>
</html>