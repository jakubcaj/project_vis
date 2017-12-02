<html>
<head>
    <title>Police</title>
    <%@include file="../../fragment/resourcesFragment.jsp" %>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css">
    <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.16/js/dataTables.semanticui.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.13/semantic.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/dataTables.semanticui.min.css">
    <script src="/resources/js/crime/searchCrimeController.js"></script>
</head>
<body>
<%@include file="../../fragment/headerFragment.jsp" %>
<div class="row">
    <div class="col-lg-6 col-lg-offset-3 bodyMain">
        <h1>Search crime</h1>
        <div class="row">
            <div class="col-lg-6 col-lg-offset-1">
                <label for="shortDescription">Short description:</label>
                <input id="shortDescription" class="form-control">
            </div>
            <div class="col-lg-6 col-lg-offset-1">
                <button type="button" class="btn btn-success subButton" id="searchButton">Search</button>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-10 col-lg-offset-1">
                <table id="crimeTable" class="ui celled table" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Short description</th>
                        <th>Date committed</th>
                        <th>Edit</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>