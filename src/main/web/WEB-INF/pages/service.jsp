<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include.jsp" %>
<!DOCTYPE html>

<html>
    <title>
        ${service.name}
    </title>
    <body>
        <main>
            <h2>${service.name}</h2>
            <div>
                <h4>Описание:</h4>
                <p>${service.description}</p>
            </div>
            <div>
                <h4>Количество минут/SMS/интернета(ГБ) в пакете:</h4>
                <p>${service.calls_min} / ${service.sms_number} / ${service.internet_gb}</p>
            </div>
            <div>
                <h4>Количесво интернета(ГБ) в пакете:</h4>
                <p>${service.internet_gb}</p>
            </div>
            <div>
                <h4>Количесво SMS в пакете:</h4>
                <p>${service.sms_number}</p>
            </div>
            <div>
                <h4>Стоимость:</h4>
                <p>${service.cost}</p>
            </div>
        </main>
    </body>
</html>