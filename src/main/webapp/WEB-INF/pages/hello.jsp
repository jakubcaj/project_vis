<%@taglib prefix="sec"
          uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.js"
        integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
        integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
<link href="/resources/css/general.css" rel="stylesheet">
<script src="/resources/js/indexController.js" rel="script"></script>
<body>
<div class="header">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#headerNav"
                        aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="collapse navbar-collapse" id="headerNav">
                <div class="row">
                    <div class="col-lg-3">
                        <a class="navbar-brand" href="/">Police</a>
                    </div>
                    <div class="col-lg-6">
                        <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
                            <form class="navbar-form navbar-right">
                                <button class="btn btn-primary" type="button" data-toggle="popover">Log In</button>
                            </form>
                        </sec:authorize>
                        <sec:authorize access="!hasRole('ROLE_ANONYMOUS')">
                            <sec:authentication var="principal" property="principal"/>
                            <ul class="nav navbar-nav navbar-right">
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                       aria-haspopup="true" aria-expanded="false">${principal.username}
                                        <span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="/editProfile">Edit Profile</a></li>
                                        <li><a href="/logout">Log out</a></li>
                                        <li><a href="#">Something else here</a></li>
                                        <li role="separator" class="divider"></li>
                                        <li><a href="#">Separated link</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </sec:authorize>
                    </div>
                </div>
            </div>
        </div>
    </nav>
</div>
<div class="row">
    <div class="col-lg-6 col-lg-offset-3 bodyMain">

    </div>
</div>
<div style="display: none" id="loginPopOver">
    <ul class="nav nav-tabs">
        <li class="active"><a data-toggle="tab" href="#loginTab">Log In</a></li>
        <li><a data-toggle="tab" href="#registerTab">Register</a></li>
    </ul>
    <div class="tab-content">
        <div id="loginTab" class="tab-pane fade in active">
            <form class="form-group" name="loginForm" action="/auth" method="post" onsubmit="return validateLoginForm()">
                <label for="username">Username:</label>
                <input class="form-control" type="text" id="username" name="username">
                <label for="password">Password:</label>
                <input class="form-control" type="password" id="password" name="password">
                <button class="btn btn-primary subButton" type="submit">Submit</button>
            </form>
        </div>
        <div id="registerTab" class="tab-pane fade">
            <label for="regFirstname">Firstname:</label>
            <input class="form-control" type="text" id="regFirstname">
            <label for="regLastname">Lastname:</label>
            <input class="form-control" type="text" id="regLastname" >
            <label for="regEmail">Email:</label>
            <input class="form-control" type="email" id="regEmail" >
            <label for="regUsername">Username:</label>
            <input class="form-control" type="text" id="regUsername" >
            <label for="regPassword">Password:</label>
            <input class="form-control" type="password" id="regPassword">
            <button type="button" class="btn btn-primary subButton" onclick="registerForm()" id="regSubmit">Submit</button>
        </div>
    </div>
</div>
</body>
</html>