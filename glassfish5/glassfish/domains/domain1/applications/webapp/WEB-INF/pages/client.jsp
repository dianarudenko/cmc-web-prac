<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include.jsp" %>
<!DOCTYPE html>

<style><%@ include file="/resources/styles.css" %></style>
<html>
  <title>
      ${client.surname} ${client.name} ${client.middle_name}
  </title>
  <body>
    <%@ include file="/WEB-INF/pages/header.jsp" %>
    <main>
      <h2>${client.surname} ${client.name} ${client.middle_name}</h2>
      <div>
        <h4>Номер телефона:</h4>
        <p>${client.phone_number}</p>
      </div>
      <div>
        <div>
          <h4>Номер счета:</h4>
          <a href="bill?id=${client.bill.number}">
            <p id="bill">${client.bill.number}</p>
          </a>
        </div>
        <div>
        <table id="active-service">
          <caption><h3>Активная услуга</h3></caption>
          <tr>
            <th>ID</th>
            <th>Название</th>
            <th>Дата приобретения услуги</th>
            <th>Дата окончания действия услуги</th>
          </tr>
          <c:forEach var="service" items="${activeServices}">
            <tr>
                <td>${service.service.id}</td>
                <td>
                  <a href="service?id=${service.service.id}">
                    ${service.service.name}
                  </a>
                </td>
                <td>
                  <center>${service.start_date}</center>
                </td>
                <td>
                  <c:choose>
                    <c:when test="${service.end_date == null}">
                      <center>-</center>
                    </c:when>
                    <c:otherwise>
                      <center>${service.end_date}</center>
                    </c:otherwise>
                  </c:choose>
                </td>
                <td>
                  <a href="disable_service?id=${client.id}">
                    <input type="button" id="disable" value="Отключить">
                  </a>
                </td>
            </tr>
          </c:forEach>
        </table>
      </div>
      <div>
        <table>
          <caption><h3>История услуг</h3></caption>
          <tr>
            <th>ID</th>
            <th>Название</th>
            <th>Дата приобретения услуги</th>
            <th>Дата окончания действия услуги</th>
          </tr>
          <c:forEach var="service" items="${servicesList}">
            <tr>
              <td>${service.service.id}</td>
              <td>
                <a href="service?id=${service.service.id}">
                  ${service.service.name}
                </a>
              </td>
              <td>
                <center>${service.start_date}</center>
              </td>
              <td>
                <c:choose>
                  <c:when test="${service.end_date == null}">
                    <center>-</center>
                  </c:when>
                  <c:otherwise>
                    <center>${service.end_date}</center>
                  </c:otherwise>
                </c:choose>
              </td>
            </tr>
          </c:forEach>
        </table>
      </div>
      <br>
      <div>
        <a href="edit_client?id=${client.id}">
          <input type="button" id="edit" value="Редактировать">
        </a>
      </div>
    </main>
  </body>
</html>