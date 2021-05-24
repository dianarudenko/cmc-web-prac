<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include.jsp" %>

<html>
    <title>
        My super project!
    </title>
    <body>
        <%@ include file="/WEB-INF/pages/header.jsp" %>
        <div>
            <center>
                <a href="services" id="services">
                    <input type="button" value="Услуги">
                </a>
                <br>
                Все услуги, предоставляемые компанией.
            </center>
        </div>
        <br>
        <div>
            <center>
                <a href="clients" id="clients">
                    <input type="button" value="Клиенты">
                </a>
                <br>
                Клиентская база компании.
            </center>
        </div>
    </body>
</html>