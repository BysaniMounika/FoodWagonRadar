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
						<a href="<%=request.getContextPath()%>/sign-up"
							class="float-right btn btn-outline-primary">Sign up</a>
						<h4 class="card-title mb-4 mt-1">Sign in</h4>
						<form method="POST" action="<%=request.getContextPath()%>/sign-in">
							<div class="form-group">
								<label>Your email</label> <input name="email"
									class="form-control" placeholder="Email" type="email">
							</div>

							<div class="form-group">
								<label>Your password</label> <input class="form-control"
									type="password" name="password">
							</div>

							<div class="form-group">
								<button type="submit" class="btn btn-primary btn-block">Login</button>
							</div>
						</form>
					</article>
				</div>
			</div>
		</div>
	</div>
</body>
</html>