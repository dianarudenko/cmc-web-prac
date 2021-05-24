<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ошибка</title>
</head>
<body>
<%@ include file="/WEB-INF/pages/header.jsp" %>
    <main>
        <div style="text-align: center;">
            <h1>Произошла ошибка!</h1>
        </div>
        <br>
        <c:choose>
            <c:when test="${errors == null}">
                Неверно введены дданые!
            </c:when>
            <c:otherwise>
                <c:forEach var="error" items="${errors}">
                    ${error.defaultMessage}
                    <br>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </main>
</body>
</html>