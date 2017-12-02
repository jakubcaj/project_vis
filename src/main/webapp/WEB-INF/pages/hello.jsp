<html>
<head>
    <title>Police</title>
    <%@include file="../fragment/resourcesFragment.jsp" %>
</head>
<body>
<%@include file="../fragment/headerFragment.jsp" %>
<div class="row">
    <div class="col-lg-6 col-lg-offset-3 bodyMain">
        <c:forEach var="crime" items="${crimes}">
            <div class="crime">
                <h2>${crime.shortDescription}</h2>
                <h4>${crime.dateCommitted.toLocaleString()}</h4>
                <span>${crime.description}</span>
                <h4>Suspects:</h4>
                <c:forEach var="suspect" items="${crime.suspects}">
                    <span>Name: ${suspect.fullName}</span>
                </c:forEach>
                <h4>Victims:</h4>
                <c:forEach var="victim" items="${crime.victims}">
                    <span>Name: ${victim.profile.fullName}</span>
                </c:forEach>
                <hr/>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>