<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include.jsp" %>
<!DOCTYPE html>

<style><%@ include file="/resources/styles.css" %></style>
<html>
    <head>
        <title>Счет ${bill.number}</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/pages/header.jsp" %>
        <main>
            <h2>Счет № ${bill.number}</h2>
            <dev>
                <h3>Клиент:</h3>
                <a href="client?id=${client.id}">
                    ${client.surname}
                    ${client.name}
                    ${client.middle_name}
                </a>
            </dev>
            <dev>
                <h3>Средств на счете:</h3>
                ${bill.funds} руб.
            </dev>
            <table>
                <caption><h2>Транзакции</h2></caption>
                <tr>
                    <th>ID</th>
                    <th>Тип</th>
                    <th>Сумма</th>
                    <th>Дата</th>
                </tr>
                <c:forEach var="transaction" items="${transactions}">
                    <tr>
                        <td>
                            ${transaction.id}
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${transaction.type == 'incoming'}">
                                    Пополнение
                                </c:when>
                                <c:otherwise>
                                    Списание
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            ${transaction.sum}
                        </td>
                        <td>
                            ${transaction.date}
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </main>
    </body>
</html>