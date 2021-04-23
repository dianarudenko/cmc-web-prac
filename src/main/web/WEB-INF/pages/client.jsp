<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include.jsp" %>
<!DOCTYPE html>

<html>
  <title>
      ${client.surname} ${client.name} ${client.middle_name}
  </title>
  <body>
    <main>
      <h2>${client.surname} ${client.name} ${client.middle_name}</h2>
      <div>
        <h4>Номер телефона:</h4>
        <p>${client.phone_number}</p>
      </div>
      <div>
        <table>
          <caption><h3>Активные услуги</h3></caption>
          <tr>
            <th>ID</th>
            <th>Название</th>
            <th>Дата приобретения услуги</th>
            <th>Дата окончания действия услуги</th>
          </tr>
          <c:forEach var="service" items="${activeServices}">
            <tr>
                <td>${service.id}</td>
                <td>
                  <a href="service?id=${service.id}">
                    ${service.name}
                  </a>
                </td>
                <td>${service.start_date}</td>
                <td>${service.end_date}</td>
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
              <td>${service.start_date}</td>
              <td>${service.end_date}</td>
            </tr>
          </c:forEach>
        </table>
      </div>
    </main>
  </body>
</html>