<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include.jsp" %>
<!DOCTYPE html>

<html>
    <title>
        Клиенты
    </title>
    <body>
        <main>
            <table>
                <caption><h2>Клиенты</h2></caption>
                <tr>
                    <th>ID</th>
                    <th>ФИО</th>
                    <th>Телефон</th>
                    <th>Номер счета</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="client" items="${clientsList}">
                    <tr>
                        <td>
                            <a href="client?id=${client.id}">
                                ${client.id}
                            </a>
                        </td>
                        <td>
                            <a href="client?id=${client.id}">
                                ${client.surname} ${client.name} ${client.middle_name}
                            </a>
                        </td>
                        <td>
                            <a href="client?id=${client.id}">
                                ${client.phone_number}
                            </a>
                        </td>
                        <!-- <td>
                            <fmt:formatDate value="${workPosition.client_id.hire_date}" pattern="dd.MM.yyyy г"/>
                        </td> -->
                        <td>
                            <a href="bill?id=${client.bill.number}">
                                ${client.bill.number}
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </main>
    </body>
</html>