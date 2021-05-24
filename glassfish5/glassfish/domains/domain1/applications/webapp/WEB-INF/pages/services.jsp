 
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include.jsp" %>
<!DOCTYPE html>

<style><%@ include file="/resources/styles.css" %></style>
<html>
    <head>
        <title>Услуги</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/pages/header.jsp" %>
        <main>
            <table id="services-table">
                <caption>
                    <h2>Услуги</h2>
                    <input class="form-control" type="text" placeholder="Поиск..." id="search-text" onkeyup="tableSearch()">
                </caption>
                <thead>
                    <th>ID</th>
                    <th>Название</th>
                    <th>Краткое описание</th>
                    <th>Стоимость</th>
                </thead>
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
                        <td>
                            ${service.description}
                        </td>
                        <td>
                            ${service.cost}
                        </td>
                        <td>
                            <a href="delete_service?id=${service.id}">
                                <form>
                                    <input type="button" value="Удалить">
                                </form>
                            </a>
                        </td>
                        <td>
                            <a href="edit_service?id=${service.id}">
                                <form>
                                    <input type="button" value="Изменить">
                                </form>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br>
            <a href="add_service">
                <input type="button" id="add_service" value="Добавить...">
            </a>
        </main>
    </body>
</html>
<script>
    function tableSearch() {
        var phrase = document.getElementById('search-text');
        var table = document.getElementById('services-table');
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