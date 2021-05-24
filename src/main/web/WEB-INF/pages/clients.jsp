<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include.jsp" %>
<!DOCTYPE html>

<style><%@ include file="/resources/styles.css" %></style>
<html>
    <title>
        Клиенты
    </title>
    <body>
        <%@ include file="/WEB-INF/pages/header.jsp" %>
        <main>
            <table id="clients-table">
                <caption>
                    <h2>Клиенты</h2>
                    <input class="form-control" type="text" placeholder="Поиск..." id="search-text" onkeyup="tableSearch()">
                </caption>
                <thead>
                    <th>ID</th>
                    <th>ФИО</th>
                    <th>Телефон</th>
                    <th>Номер счета</th>
                </thead>
                <c:forEach var="client" items="${clientsList}">
                    <tr>
                        <td>
                            ${client.id}
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
                        <td>
                            <a href="bill?id=${client.bill.number}">
                                ${client.bill.number}
                            </a>
                        </td>
                        <td>
                            <a href="delete_client?id=${client.id}">
                                <form action="deleteClient.jsp">
                                    <input type="button" value="Удалить"/>
                                </form>
                            </a>
                        </td>
                        <td>
                            <a href="edit_client?id=${client.id}">
                                <form>
                                    <input type="button" value="Изменить"/>
                                </form>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br>
            <a href="add_client" id="add_client">
                <form>
                    <input type="button" value="Добавить..."/>
                </form>
            </a>
        </main>
    </body>
</html>
<script>
    function tableSearch() {
        var phrase = document.getElementById('search-text');
        var table = document.getElementById('clients-table');
        var regPhrase = new RegExp(phrase.value, 'i');
        var flag = false;
        for (var i = 0; i < table.rows.length; i++) {
            flag = false;
            for (var j = table.rows[i].cells.length - 1; j >= 0; j--) {
                flag = regPhrase.test(table.rows[i].cells[j].innerHTML);
                if (flag) break;
            }
            if (flag) {
                table.rows[i].style.display = "";
            } else {
                table.rows[i].style.display = "none";
            }

        }
    }
</script>