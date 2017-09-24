<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Spring Security Example</title>
</head>
<body>
<h1>Welcome!</h1>

<p>Click <a th:href="@{/hello}">here</a> to see a greeting.</p>
</body>
</html>