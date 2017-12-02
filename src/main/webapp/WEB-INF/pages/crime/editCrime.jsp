<html>
<head>
    <title>Police</title>
    <%@include file="../../fragment/resourcesFragment.jsp" %>
    <script src="/resources/js/crime/editCrimeController.js"></script>
</head>
<body>
<%@include file="../../fragment/headerFragment.jsp" %>
<div class="row">
    <input style="display: none" id="crimeId" value="${crimeId}">
    <div class="col-lg-6 col-lg-offset-3 bodyMain">
        <div class="editForm">
            <h1>Edit Crime</h1>
            <div class="row">
                <div class="col-lg-6 col-lg-offset-1">
                    <label for="shortDescription">Short description:</label>
                    <input id="shortDescription" class="form-control" value="${shortDescription}">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6 col-lg-offset-1">
                    <label for="dateCommitted">Date Committed:</label>
                    <input id="dateCommitted" type="date" class="form-control" value="${dateCommitted}">
                </div>
            </div>
            <div class="row">
                <div class="col-lg-10 col-lg-offset-1">
                    <label for="description">Description:</label>
                    <textarea id="description" rows="7" class="form-control">${description}</textarea>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-lg-10 col-lg-offset-1">
                    <button id="addSuspect" type="button" class="btn btn-primary subButton">Add suspect</button>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6 col-lg-offset-1" id="suspectsRow">
                    <c:forEach var="item" items="${suspects}">
                        <div style="display: flex; margin-bottom: 10px">
                            <label for="textBoxToRemove${item.id}">Suspect: </label>
                            <input id="textBoxToRemove${item.id}" value="${item.fullName}" type="text"
                                   class="form-control">
                            <button style="margin-left: 7px" class="btn btn-warning "
                                    onclick="addRemoveSuspect(textBoxToRemove${item.id})">Remove
                            </button>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-lg-10 col-lg-offset-1">
                    <button id="addVictim" type="button" class="btn btn-primary subButton">Add victim</button>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6 col-lg-offset-1" id="victimsRow">
                    <c:forEach var="item" items="${victims}">
                        <div style="display: flex; margin-bottom: 10px">
                            <label for="textBoxViToRemove${item.profile.id}">Suspect: </label>
                            <input id="textBoxViToRemove${item.profile.id}" value="${item.profile.fullName}" type="text"
                                   class="form-control">
                            <button style="margin-left: 7px" class="btn btn-warning "
                                    onclick="addRemoveSuspect(textBoxViToRemove${item.profile.id})">Remove
                            </button>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-lg-3 col-lg-offset-1">
                    <button id="releaseToPublicBtn" type="button" class="btn btn-success subButton">Release to public</button>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

</body>
</html>