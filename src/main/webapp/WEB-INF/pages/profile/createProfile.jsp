<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Edit User</title>
    <%@include file="../../fragment/resourcesFragment.jsp" %>
    <script src="/resources/js/profile/createProfileController.js"></script>
</head>
<body>
<%@include file="../../fragment/headerFragment.jsp" %>
<div class="row bodyBackground">
    <div class="col-lg-6 col-lg-offset-3 bodyMain">
        <div class="editForm">
            <div class="row">
                <div class="col-lg-6">
                    <label for="firstName">First Name:</label>
                    <input id="firstName" class="form-control" name="firstName">
                </div>
                <div class="col-lg-6">
                    <label for="lastName">Last Name:</label>
                    <input id="lastName" class="form-control" name="lastName">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <label for="birthDate">Birth Date:</label>
                    <input id="birthDate" type="date" class="form-control" name="birthDate">
                </div>
                <div class="col-lg-6">
                    <label for="deceased">Deceased: </label>
                    <input id="deceased" type="checkbox" name="birthDate">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <label for="email">Email:</label>
                    <input id="email" class="form-control" name="email">
                </div>
                <div class="col-lg-6">
                    <label for="phone">Phone:</label>
                    <input id="phone" class="form-control" name="birthDate">
                </div>
            </div>
            <button id="editFormButton" type="button" class="btn btn-primary subButton">Submit</button>
        </div>
    </div>
</div>
</body>
</html>
