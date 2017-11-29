<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <ul class="nav navbar-nav navbar-left">
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                       aria-haspopup="true" aria-expanded="false">Admin
                                        <span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="/admin">Edit Users</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </sec:authorize>
                        <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_PROFILES')">
                            <ul class="nav navbar-nav navbar-left">
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                                       aria-haspopup="true" aria-expanded="false">Profiles
                                        <span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="/profile">Add profile</a></li>
                                        <li><a href="/crime">Add profile</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </sec:authorize>
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
                                        <li><a href="/user">Edit Profile</a></li>
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
