<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include.jsp" %>
<!DOCTYPE html>

<html>
  <title>
    <c:choose>
      <c:when test = "${type == 'add'}">
          Добавление данных работника
      </c:when>
      <c:otherwise>
          Изменение данных работника
      </c:otherwise>
    </c:choose>
  </title>
  <body>
    <%@ include file="/WEB-INF/pages/header.jsp" %>
    <main>
      <h2>
        <c:choose>
          <c:when test = "${type == 'add'}">
              Добавление данных работника
          </c:when>
          <c:otherwise>
              Изменение данных работника
          </c:otherwise>
        </c:choose>
      </h2>
      <c:choose>
        <c:when test = "${type == 'edit'}">
          <h>Активная услуга:    </h>
          <c:choose>
            <c:when test="${active_service != null}">
              <a href="service?id=${active_service.service.id}">
                ${active_service.service.name}
              </a>
            </c:when>
            <c:otherwise>
              Нет
            </c:otherwise>
          </c:choose>
          <br>
          <br>
        </c:when>
      </c:choose>
      <form:form modelAttribute="client" method="POST">
        <table>
          <tr>
            <td>
              <form:label path="surname">
                Фамилия
              </form:label>
            </td>
            <td>
              <form:input type="text" path="surname" id="surname" value="${client.surname}"/>
            </td>
          </tr>
          <tr>
            <td>
              <form:label path="name">
                Имя
              </form:label>
            </td>
            <td>
              <form:input type="text" path="name" id="name" value="${client.name}"/>
            </td>
          </tr>
          <tr>
            <td>
              <form:label path="middle_name">
                Отчество
              </form:label>
            </td>
            <td>
              <form:input type="text" path="middle_name" id="middle_name" value="${client.middle_name}"/>
            </td>
          </tr>
          <tr>
            <td>
              <form:label path="phone_number">
                Номер телефона
              </form:label>
            </td>
            <td>
              <form:input type="tel" path="phone_number" id="phone_number" value="${client.phone_number}" placeholder="+7(***)***-**-**"/>
            </td>
          </tr>
          <tr>
            <td>
              <form:label path="active_service.service">
                Подключить услугу
              </form:label>
            </td>
            <td>
              <form:select path="active_service.service" id="active-service">
                <option value="${null}">-</option>
                <c:forEach var="service" items="${services}">
                  <form:option value="${service.id}">${service.name}</form:option>
                </c:forEach>
              </form:select>
            </td>
          </tr>
        </table>
        <br>
        <c:choose>
          <c:when test="${client.bill == null}">
            <form:checkbox path="bill" id="bill" value="${bill.number}" checked="checked"/>
              Назначить счет
          </c:when>
          <c:otherwise>
            <form:input type="hidden" path="bill" value="${client.bill.number}"/>
          </c:otherwise>
        </c:choose>
        <br>
        <div>
            <c:choose>
                <c:when test = "${type == 'add'}">
                    <input type = "submit" value = "добавить"/>
                    <a href = "clients">
                      <form>
                        <input type="button" value="отмена"/>
                      </form>
                    </a>
                </c:when>
                <c:otherwise>
                    <input type = "submit" value = "обновить"/>
                    <a href = "clients">
                      <form>
                        <input type="button" value="отмена"/>
                      </form>
                    </a>
                </c:otherwise>
            </c:choose>
        </div>
    </form:form>
    </main>
  </body>
</html>