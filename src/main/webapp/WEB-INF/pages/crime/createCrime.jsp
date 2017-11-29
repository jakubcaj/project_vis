<html>
<head>
    <title>Police</title>
    <%@include file="../../fragment/resourcesFragment.jsp" %>
    <script src="/resources/js/crime/createCrimeController.js"></script>
</head>
<body>
<%@include file="../../fragment/headerFragment.jsp" %>
<div class="row">
    <div class="col-lg-6 col-lg-offset-3 bodyMain">
        <div class="editForm">
            <h1>Create Crime</h1>
            <div class="row">
                <div class="col-lg-6 col-lg-offset-1">
                    <label for="dateCommitted">Date Committed:</label>
                    <input id="dateCommitted" type="date" class="form-control">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-10 col-lg-offset-1">
                    <label for="description">Description:</label>
                    <textarea id="description" rows="7" class="form-control"></textarea>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-10 col-lg-offset-1">
                    <button id="addSuspect" type="button" class="btn btn-primary subButton">Add suspect</button>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6 col-lg-offset-1" id="suspectsRow">

                </div>
            </div>
            <div class="row">
                <div class="col-lg-3 col-lg-offset-1">
                    <button id="createCrimeButton" type="button" class="btn btn-primary subButton">Create Crime</button>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>