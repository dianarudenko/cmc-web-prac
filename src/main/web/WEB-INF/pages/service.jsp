<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include.jsp" %>
<!DOCTYPE html>

<style><%@ include file="/resources/styles.css" %></style>
<html>
    <title>
        ${service.name}
    </title>
    <body>
        <%@ include file="/WEB-INF/pages/header.jsp" %>
        <main>
            <h2>${service.name}</h2>
            <div>
                <h4>Описание:</h4>
                <p id="description">${service.description}</p>
            </div>
            <div>
                <h4>Количество минут / SMS в пакете:</h4>
                <p id="calls-sms">${service.calls_min} / ${service.sms_number}</p>
            </div>
            <div>
                <h4>Количесво интернета(ГБ) в пакете:</h4>
                <p id="internet-gb">${service.internet_gb}</p>
            </div>
            <div>
                <h4>Стоимость:</h4>
                <p id="cost">${service.cost}</p>
            </div>
            <div>
                <a href="edit_service?id=${service.id}">
                <input type="button" id="edit" value="Редактировать">
                </a>
            </div>
        </main>
    </body>
</html>