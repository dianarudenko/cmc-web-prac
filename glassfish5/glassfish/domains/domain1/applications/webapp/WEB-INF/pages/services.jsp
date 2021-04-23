 
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include.jsp" %>
<!DOCTYPE html>

<html>
    <head>
        <title>Услуги</title>
    </head>
    <body>
        <main>
            <table>
                <caption><h2>Услуги</h2></caption>
                <tr>
                    <th>ID</th>
                    <th>Название</th>
                    <th>Краткое описание</th>
                    <th>Стоимость</th>
                    <th></th>
                    <th></th>
                </tr>
                <%-- <tr>
                    <td><input type="text" id="id" onkeyup="FilterFunction(0, id)" placeholder="Поиск по id..."></td>
                    <td><input type="text" id="name" onkeyup="FilterFunction(1, id)" placeholder="Поиск по названию..."></td>
                    <td><input type="text" id="description" onkeyup="FilterFunction(2, id)" placeholder="Поиск по описанию..."></td>
                    <td><input type="text" id="cost" onkeyup="FilterFunction(3, id)" placeholder="Поиск по цене..."></td>
                </tr> --%>
                <c:forEach var="service" items="${servicesList}">
                    <tr>
                        <td>
                            <a href="service?id=${service.id}">
                                ${service.id}
                            </a>
                        </td>
                        <td>
                            <a href="service?id=${service.id}">
                                ${service.name}
                            </a>
                        </td>
                        <%-- <td>
                            <fmt:formatDate value="${workPosition.service_id.hire_date}" pattern="dd.MM.yyyy г"/>
                        </td> --%>
                        <td>
                            ${service.description}
                        </td>
                        <td>
                            ${service.cost}
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </main>
    </body>
</html>
<script>
    function FilterFunction(id, input) {
        var filter, table, tr, td, i, txtValue;
        filter = document.getElementById(input).value.toUpperCase();
        table = document.getElementById("WorkerTable");
        tr = table.getElementsByTagName("tr");
        for (i = 2; i < tr.length; ++i) {
            td = tr[i].getElementsByTagName("td")[id];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
</script>