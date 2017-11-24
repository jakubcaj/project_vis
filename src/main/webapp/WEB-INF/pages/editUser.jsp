<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Edit User</title>
    <%@include file="../fragment/resourcesFragment.jsp" %>
    <script src="/resources/js/userController.js"></script>
</head>
<body>
<%@include file="../fragment/headerFragment.jsp" %>
<div class="row bodyBackground">
    <div class="col-lg-6 col-lg-offset-3 bodyMain">
        <div class="editForm">
            <label for="firstName">First Name:</label>
            <input id="firstName" class="form-control" value="${firstName}" name="firstName">
            <label for="lastName">Last Name:</label>
            <input id="lastName" class="form-control" value="${lastName}" name="lastName">
            <label for="email">Email:</label>
            <input id="email" class="form-control" value="${email}" name="email">
            <label for="username">Username:</label>
            <input id="username" class="form-control" value="${username}" name="username">
            <button id="editFormButton" type="button" class="btn btn-primary subButton">Submit</button>
        </div>
    </div>
</div>
</body>
</html>
