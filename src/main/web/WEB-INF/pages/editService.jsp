<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include.jsp" %>
<!DOCTYPE html>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setCharacterEncoding("UTF-8"); %>

<html>
  <title>
      Изменение услуги
  </title>
  <body>
    <%@ include file="/WEB-INF/pages/header.jsp" %>
    <main>
      <h2>Изменение услуги</h2>
      <div>
      <form:form modelAttribute="service" method="POST">
        <table>
          <tr>
            <td>
              <form:label path="name">
                Название услуги
              </form:label>
            </td>
            <td>
              <form:input type="text" path="name" id="name" value="${service.name}" charset="UTF-8"/>
            </td>
          </tr>
          <tr>
            <td>
              <form:label path="description">
                Описание услуги
              </form:label>
            </td>
            <td>
              <form:input type="text" path="description" id="description" value="${service.description}"/>
            </td>
          </tr>
          <tr>
            <td>
              <form:label path="calls_min">
                Количество минут
              </form:label>
            </td>
            <td>
              <form:input type="text" path="calls_min" id="calls_min" value="${service.calls_min}"/>
            </td>
          </tr>
          <tr>
            <td>
              <form:label path="sms_number">
                Количество SMS
              </form:label>
            </td>
            <td>
              <form:input type="text" path="sms_number" id="sms_number" value="${service.sms_number}"/>
            </td>
          </tr>
          <tr>
            <td>
              <form:label path="internet_gb">
                Количество интернета (ГБ)
              </form:label>
            </td>
            <td>
              <form:input type="text" path="internet_gb" id="internet_gb" value="${service.internet_gb}"/>
            </td>
          </tr>
          <tr>
            <td>
              <form:label path="cost">
                Стоимость
              </form:label>
            </td>
            <td>
              <form:input type="text" path="cost" id="cost" value="${service.cost}"/>
            </td>
          </tr>
        </table>
        <br>
        <div>
            <c:choose>
                <c:when test = "${service.id == null}">
                    <input type = "submit" value = "добавить"/>
                    <a href = "services">
                      <form>
                        <input type="button" value="отмена"/>
                      </form>
                    </a>
                </c:when>
                <c:otherwise>
                    <input type = "submit" value = "обновить"/>
                    <a href = "services">
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