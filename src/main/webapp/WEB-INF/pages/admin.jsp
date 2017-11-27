<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<html>
<head>
    <%@include file="../fragment/resourcesFragment.jsp" %>
    <script src="/resources/js/adminController.js"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css">
    <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.16/js/dataTables.semanticui.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/dataTables.semanticui.min.css">
</head>
<body>
<%@include file="../fragment/headerFragment.jsp" %>
<div class="row">
    <div class="col-lg-6 col-lg-offset-3 bodyMain">
        <h1>Search User</h1>
        <div class="row">
            <div class="col-lg-6">
                <label for="firstName">First Name: </label>
                <input class="form-control" id="firstName">
            </div>
            <div class="col-lg-6">
                <label for="lastName">Last Name: </label>
                <input class="form-control" id="lastName">
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6">
                <label for="email">Email: </label>
                <input class="form-control" id="email">
            </div>
            <div class="col-lg-6">
                <label for="usernameS">Username: </label>
                <input class="form-control" id="usernameS">
            </div>
        </div>
        <div class="row">
            <button type="button" id="searchUserBtn" style="float: right" class="btn btn-primary subButton">Search
            </button>
        </div>
        <div class="row">
            <div class="col-lg-10 col-lg-offset-1">
                <table id="usersTable" class="ui celled table" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>UserName</th>
                        <th>Role</th>
                        <th>Edit</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
    <div id="modalwindow" style="display: none">
        <div class="row">
            <div class="col-lg-4">
                <label for='firstNameModal'>First Name: </label>
            </div>
            <div class="col-lg-8">
                <input id='firstNameModal' class='form-control'>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4">
                <label for='lastNameModal'>Last Name: </label>
            </div>
            <div class="col-lg-8">
                <input id='lastNameModal' class='form-control'>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4" style="margin-bottom: 15px">
                <label for='emailModal'>Email: </label>
            </div>
            <div class="col-lg-8">
                <input id='emailModal' class='form-control'>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4">
                <label for='usernameModal'>Username: </label>
            </div>
            <div class="col-lg-8">
                <input id='usernameModal' class='form-control'>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4">
                <label for='roleModal'>Role: </label>
            </div>
            <div class="col-lg-8">
                <div class="form-group">
                    <select id="roleModal" class="form-control" multiple>
                        <c:forEach var="item" items="${roles}">
                            <option value="${item.name()}">${item.name()}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>